/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.mypage.mpg003.service.SecuritySetupService;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.CountryInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.SecuritySetStatusVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.controller 
 *    |_ SecuritySetupController.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 13. 오전 11:40:15
 * @version :
 * @author : yeonseoo
 */
@Controller
public class SecuritySetupController {

	private static final Logger logger = LoggerFactory.getLogger(SecuritySetupController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private SecuritySetupService securitySetupService;

	@Autowired
	private CommService commService;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@RequestMapping(value = "coinis/securitySetup")
	public ModelAndView moveSecuritySetup(HttpServletRequest request, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg003/securitySetup");
		// 국가 코드 조회
		List<CountryInfoVO> countryInfoList = securitySetupService.selectCountryInfo();

		// 인증 설정 상태 조회
		ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		param.setExchange_id(exchange_id);
		param.setUser_id(userInfo.getUserId());

		SecuritySetStatusVO securitySetStatus = securitySetupService.selectSecuritySetStatus(param);

		// otp 발급
		try {
			if (securitySetStatus.getOtp_yn() == 0) {
				String strOtpKey = CommonUtils.generateOTPKey(32);
				model.addObject("googleOtpKey", "otpauth://totp/" + exchange_id + "("
						+ CommonUtils.maskingUserId(userInfo.getUserId()) + ")?secret=" + strOtpKey);
				model.addObject("newOtpKey", strOtpKey);
				model.addObject("otpYn", "0");
			} else {
				model.addObject("googleOtpKey", "otpauth://totp/" + exchange_id + "("
						+ CommonUtils.maskingUserId(userInfo.getUserId()) + ")?secret=" + "ALREADY-REGISTERED999");
				model.addObject("newOtpKey", "이미등록되었습니다.");
				model.addObject("otpYn", "1");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		model.addObject("countryInfoList", countryInfoList);
		model.addObject("securitySetStatus", securitySetStatus);

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "coinis/sendSMS")
	public Map<String, Object> sendSMS(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "mobile", required = true) String mobile) {

		/////////////////////////////////////////////////////////////////////////////////
		// TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("was-request-withdraw-auth-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// OTP 해제 인증용 SMS 발송시 회원정보에서 모바일 로드
		if( mobile.equals("-1") ) {
			// 회원 mobile 로드
			ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
			param.setExchange_id(exchange_id);
			param.setUser_id(userInfo.getUserId());
			SecuritySetStatusVO securitySetStatus = securitySetupService.selectSecuritySetStatus(param);
			String tel_cd = securitySetStatus.getTel_cd();
			mobile = tel_cd + securitySetStatus.getMobile();
		}
			
		try {
			// 인증코드 저장 프로시저 호출
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", exchange_id);
			paramMap.put("ARG_USER_ID", userInfo.getUserId());
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2); // 인증목적 식별코드 1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("ARG_AUTH_MEANS_CD", 2); // 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", mobile); // 휴대폰 번호
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60); // 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", 0); // 요청일련번호
			Map<String, Object> insertAuthCodeMap = securitySetupService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
			int rtn_cd = Double.valueOf(CommonUtils.strNlv(insertAuthCodeMap.get("V_RTN_CD"), "-1")).intValue();

			if (rtn_cd != 0) {
				resultMap.clear();
				resultMap.put("res_cd", rtn_cd);
				resultMap.put("res_msg", (String) insertAuthCodeMap.get("V_RTN_MSG"));
				// Roll-back Transaction
				transactionManager.rollback(status);

				return resultMap;
			}

			// SMS 전송
			String AuthCode = insertAuthCodeMap.get("V_AUTH_CODE").toString();
			SendSmsVO sendSmsVo = new SendSmsVO();
			sendSmsVo.setExchangeId(exchange_id);
			sendSmsVo.setUserId(userInfo.getUserId());
			sendSmsVo.setTo(mobile);
			String smsMsg = String.format("[COINIS] OTP 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지", AuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");

			ResultVO resultVo = commService.sendSms(sendSmsVo);

			if (resultVo.getRtnCd() != 0) {
				resultMap.clear();
				resultMap.put("res_cd", rtn_cd);
				resultMap.put("res_msg", (String) insertAuthCodeMap.get("V_RTN_MSG"));
				// Roll-back Transaction
				transactionManager.rollback(status);

				return resultMap;
			}
			transactionManager.commit(status);

			resultMap.clear();
			resultMap.put("res_cd", resultVo.getRtnCd());
			resultMap.put("res_msg", (String) resultVo.getRtnMsg());

			return resultMap;

		} catch (Exception e) {
			e.printStackTrace();

			// Roll-back Transaction
			transactionManager.rollback(status);
			// Return Error Message (stack trace)
			resultMap.clear();
			resultMap.put("res_cd", -9999);
			resultMap.put("res_msg", CommonUtils.getPrintStackTrace(e));
			return resultMap;
		}
	}

	@ResponseBody
	@RequestMapping(value = "coinis/setupSMS")
	public Map<String, Object> setupSMS(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "tel_cd", required = true) String tel_cd,
			@RequestParam(value = "auth_code", required = true) String auth_code) {

		/////////////////////////////////////////////////////////////////////////////////
		// TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("was-request-withdraw-auth-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
			// 회원 reg_dt 로드
			param.setExchange_id(exchange_id);
			param.setUser_id(userInfo.getUserId());
			SecuritySetStatusVO securitySetStatus = securitySetupService.selectSecuritySetStatus(param);
			String reg_dt = securitySetStatus.getReg_dt();

			// SMS 인증코드 유효성 검사
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", exchange_id);
			paramMap.put("ARG_USER_ID", userInfo.getUserId());
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2); // 인증목적 식별코드 1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("ARG_AUTH_MEANS_CD", 2); // 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", tel_cd+mobile); // 휴대폰 번호
			paramMap.put("ARG_AUTH_CODE", auth_code); // 인증 번호
			Map<String, Object> checkAuthCodeMap = securitySetupService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
			int res_cd = Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue();

			// 인증코드 불일치.
			if (res_cd != 0) {
				resultMap.clear();
				resultMap.put("res_cd", res_cd);
				resultMap.put("res_msg", checkAuthCodeMap.get("V_RTN_MSG"));
				// Roll-back Transaction
				transactionManager.rollback(status);

				return resultMap;
			}

			// use_sms 업데이트
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", exchange_id);
			paramMap.put("ARG_USER_ID", userInfo.getUserId());
			paramMap.put("ARG_SMS_USE_YN", 1);
			paramMap.put("ARG_TEL_CD", tel_cd);
			paramMap.put("ARG_MOBILE", mobile);
			paramMap.put("ARG_KEY1", userInfo.getUserId());
			paramMap.put("ARG_KEY2", reg_dt);
			Map<String, Object> SMSUseUpdateResultMap = securitySetupService.call_PR_WAS_SET_SMS_AUTH(paramMap);
			res_cd = Double.valueOf(SMSUseUpdateResultMap.get("V_RTN_CD").toString()).intValue();

			resultMap.clear();
			resultMap.put("res_cd", res_cd);
			resultMap.put("res_msg", CommonUtils.strNlv(SMSUseUpdateResultMap.get("V_RTN_MSG"), "SMS 보안설정이 완료되었습니다."));

			if (res_cd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				return resultMap;
			}
			transactionManager.commit(status);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();

			// Roll-back Transaction
			transactionManager.rollback(status);
			// Return Error Message (stack trace)
			resultMap.clear();
			resultMap.put("res_cd", -9999);
			resultMap.put("res_msg", CommonUtils.getPrintStackTrace(e));
			return resultMap;
		}

	}
	
	  @ResponseBody
	  @RequestMapping(value = "coinis/setupOTP") 
	  public Map<String, Object> setupOTP(HttpServletRequest request, HttpSession session, 
			  @RequestParam(value = "loginPW", required = true) String loginPW,
			  @RequestParam(value = "otpkey", required = true) String otpkey,
			  @RequestParam(value = "otp_auth_code", required = true) String otp_auth_code) {
		  
			/////////////////////////////////////////////////////////////////////////////////
			// TRANSACTION MANAGEMENT
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setName("was-request-withdraw-auth-transaction");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			TransactionStatus status = transactionManager.getTransaction(def);
			/////////////////////////////////////////////////////////////////////////////////
			
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			try {
				ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
				// 회원 reg_dt 로드
				param.setExchange_id(exchange_id);
				param.setUser_id(userInfo.getUserId());
				SecuritySetStatusVO securitySetStatus = securitySetupService.selectSecuritySetStatus(param);
				String reg_dt = securitySetStatus.getReg_dt();
				
				// login PW 확인
				paramMap.put("exchange_id", exchange_id);
				paramMap.put("user_id", userInfo.getUserId());
				paramMap.put("loginPW", loginPW);
				int pw_check = Double.valueOf(CommonUtils.strNlv(securitySetupService.checkPWMatch(paramMap), "-1")).intValue();
				if ( pw_check != 1 ) {
					resultMap.clear(); resultMap.put("res_cd", -9999); 
					resultMap.put("res_msg", "로그인 비밀번호가 일치하지 않습니다."); 
					
					return resultMap;
				}
				
				// OTP code 확인
				boolean otp_check = GoogleOTP.checkCode(otp_auth_code, otpkey);
				
				if(!otp_check) {
					resultMap.clear(); resultMap.put("res_cd", -9999); 
					resultMap.put("res_msg", "OTP 인증번호가 일치하지 않습니다."); 
					  
					return resultMap; 
				}
				  
				// otp_yn 설정 상태로 업데이트
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", exchange_id);
				paramMap.put("ARG_USER_ID", userInfo.getUserId());
				paramMap.put("ARG_OTP_YN", 1);
				paramMap.put("ARG_OTP_KEY_VAL", otpkey);
				paramMap.put("ARG_KEY1", userInfo.getUserId());
				paramMap.put("ARG_KEY2", reg_dt);
				Map<String, Object> OTPUseUpdateResultMap = securitySetupService.call_PR_WAS_SET_OTP_AUTH(paramMap);
				int res_cd = Double.valueOf(OTPUseUpdateResultMap.get("V_RTN_CD").toString()).intValue();
				
				resultMap.clear();
				resultMap.put("res_cd", res_cd);
				resultMap.put("res_msg", CommonUtils.strNlv(OTPUseUpdateResultMap.get("V_RTN_MSG"), "OTP 보안설정이 완료되었습니다."));
				
				if (res_cd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);
					
					return resultMap;
				}
				transactionManager.commit(status);
				
				return resultMap;
			} catch (Exception e) {
				e.printStackTrace();
			
				// Roll-back Transaction
				transactionManager.rollback(status);
				// Return Error Message (stack trace)
				resultMap.clear();
				resultMap.put("res_cd", -9999);
				resultMap.put("res_msg", CommonUtils.getPrintStackTrace(e));
				return resultMap;
			}
		  
	  }
	  
	  @ResponseBody
	  @RequestMapping(value = "coinis/unSetOTP") 
	  public Map<String, Object> unSetOTP(HttpServletRequest request, HttpSession session, 
			  @RequestParam(value = "loginPW", required = true) String loginPW,
			  @RequestParam(value = "sms_auth_code", required = true) String sms_auth_code,
			  @RequestParam(value = "otp_auth_code", required = true) String otp_auth_code) {
		  
			/////////////////////////////////////////////////////////////////////////////////
			// TRANSACTION MANAGEMENT
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setName("was-request-withdraw-auth-transaction");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			TransactionStatus status = transactionManager.getTransaction(def);
			/////////////////////////////////////////////////////////////////////////////////
			
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			try {
				ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
				// 회원 reg_dt, mobile 로드
				param.setExchange_id(exchange_id);
				param.setUser_id(userInfo.getUserId());
				SecuritySetStatusVO securitySetStatus = securitySetupService.selectSecuritySetStatus(param);
				String reg_dt = securitySetStatus.getReg_dt();
				String mobile = securitySetStatus.getTel_cd()+securitySetStatus.getMobile();
				int sms_use_yn = securitySetStatus.getSms_use_yn();
				
				if( sms_use_yn == 0) {
					resultMap.clear(); 
					resultMap.put("res_cd", -9999); 
					resultMap.put("res_msg", "최소 하나의 인증 수단은 설정되어있어야 합니다.\n현재 sms 미사용 중"); 
					
					return resultMap;
				}
				
				// otp_key 로드
				String otp_key_val = securitySetStatus.getOtp_key_val();
				
				// login PW 확인
				paramMap.put("exchange_id", exchange_id);
				paramMap.put("user_id", userInfo.getUserId());
				paramMap.put("loginPW", loginPW);
				int pw_check = Double.valueOf(CommonUtils.strNlv(securitySetupService.checkPWMatch(paramMap), "-1")).intValue();
				if ( pw_check != 1 ) {
					resultMap.clear(); resultMap.put("res_cd", -9999); 
					resultMap.put("res_msg", "로그인 비밀번호가 일치하지 않습니다."); 
					
					return resultMap;
				}
				
				// SMS code 확인
				// SMS 인증코드 유효성 검사
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", exchange_id);
				paramMap.put("ARG_USER_ID", userInfo.getUserId());
				paramMap.put("ARG_AUTH_PURPOSE_CD", 2); 				// 인증목적 식별코드 1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
				paramMap.put("ARG_AUTH_MEANS_CD", 2); 					// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
				paramMap.put("ARG_AUTH_MEANS_KEY_VAL", mobile); 		// 휴대폰 번호
				paramMap.put("ARG_AUTH_CODE", sms_auth_code); 			// 인증 번호
				Map<String, Object> checkAuthCodeMap = securitySetupService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
				int res_cd = Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue();

				// 인증코드 불일치.
				if (res_cd != 0) {
					resultMap.clear();
					resultMap.put("res_cd", res_cd);
					resultMap.put("res_msg", checkAuthCodeMap.get("V_RTN_MSG"));
					// Roll-back Transaction
					transactionManager.rollback(status);

					return resultMap;
				}
				
				// OTP code 확인
				boolean otp_check = GoogleOTP.checkCode(otp_auth_code, otp_key_val);
				
				if(!otp_check) {
					resultMap.clear(); resultMap.put("res_cd", -9999); 
					resultMap.put("res_msg", "OTP 인증번호가 일치하지 않습니다."); 
					  
					return resultMap; 
				}
				  
				// otp_yn 설정 해제상태로 업데이트
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", exchange_id);
				paramMap.put("ARG_USER_ID", userInfo.getUserId());
				paramMap.put("ARG_OTP_YN", 0);
				paramMap.put("ARG_OTP_KEY_VAL", otp_key_val);
				paramMap.put("ARG_KEY1", userInfo.getUserId());
				paramMap.put("ARG_KEY2", reg_dt);
				Map<String, Object> OTPUseUpdateResultMap = securitySetupService.call_PR_WAS_SET_OTP_AUTH(paramMap);
				res_cd = Double.valueOf(OTPUseUpdateResultMap.get("V_RTN_CD").toString()).intValue();
				
				resultMap.clear();
				resultMap.put("res_cd", res_cd);
				resultMap.put("res_msg", CommonUtils.strNlv(OTPUseUpdateResultMap.get("V_RTN_MSG"), "OTP 보안설정해제가 완료되었습니다."));
				
				if (res_cd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);
					
					return resultMap;
				}
				transactionManager.commit(status);
				
				return resultMap;
			} catch (Exception e) {
				e.printStackTrace();
			
				// Roll-back Transaction
				transactionManager.rollback(status);
				// Return Error Message (stack trace)
				resultMap.clear();
				resultMap.put("res_cd", -9999);
				resultMap.put("res_msg", CommonUtils.getPrintStackTrace(e));
				return resultMap;
			}
		  
	  }
	  
	 
}
