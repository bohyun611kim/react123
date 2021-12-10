/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.HttpSender;
import kr.co.coinis.webserver.common.utils.KCPUtils;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.service.HoldportInfoService;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.UserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.service.HoldportOpenApiService;
import kr.co.coinis.webserver.holdport.mypage.mpg003.vo.OpenApiVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.service.HoldportWalletService;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.MemberInfoVO;
//import whois.whoisSMS;



/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.controller
 *    |_ InfoController.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오전 10:22:03
 * @version :
 * @author : Seongcheol
 */
@Controller
public class HoldportInfoController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportInfoController.class);

	@Autowired
	private CommService commService;

	@Autowired
	private HoldportInfoService holdportInfoService;

	@Autowired
	private HoldportWalletService holdportWalletService;

	@Autowired
	private HoldportOpenApiService holdportOpenApiService;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Resource
	private RedisSessionRepository redisSessionRepository;


	@RequestMapping(value = "site/api/info")
	@ResponseBody
	public Map<String, Object> walletInfo(HttpServletRequest request, HttpSession session) {
		ModelAndView model = this.moveOutstandingOrders(request, session, null);
		return model.getModel();
	}

	@RequestMapping(value = "/site/info_new", method=RequestMethod.GET)
	public ModelAndView moveOutstandingOrders_new(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "tab", defaultValue = "", required = false) String strTabIndex) {

		ModelAndView model = new ModelAndView();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			model.setViewName(CommonUtils.getSitePackageKey(request) + "/mypage/mpg001/info_new");
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return model;
	}


	@RequestMapping(value = "/site/info", method=RequestMethod.GET)
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "tab", defaultValue = "", required = false) String strTabIndex) {

		UserInfoVO result = new UserInfoVO();
		ModelAndView model = new ModelAndView();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			ReqUserInfoVO reqUserInfoVO = new ReqUserInfoVO();
			reqUserInfoVO.setExchangeId(strExchangeId);
			reqUserInfoVO.setUserId(strUserId);

			result = holdportInfoService.selectUserInfo(reqUserInfoVO);

			logger.info("userID["+result.getUserId()+"], userName["+result.getUserName()+"]");

			result.setMobile(StringUtils.mobileMasking(result.getMobile()));

			if(strExchangeId.equalsIgnoreCase("HOLDPORT"))
				strExchangeId = "HOLDPORT";

			String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);

			if(result.getOtpYn() == 0) {
				String strOtpKey = CommonUtils.generateOTPKey(32);
				//model.addObject("googleOtpKey", "otpauth://totp/" + strExchangeId + "(" + CommonUtils.maskingUserId(strUserId) + ")?secret=" + strOtpKey);
				model.addObject("googleOtpKey", "otpauth://totp/" + CommonUtils.maskingUserId(strUserId) + "?secret=" + strOtpKey + "&issuer=Bita500");
				model.addObject("newOtpKey", strOtpKey);
				model.addObject("otpYn", "0");
			} else {
				model.addObject("googleOtpKey", "otpauth://totp/" + strExchangeId + "(" + CommonUtils.maskingUserId(strUserId) + ")?secret=" + "ALREADY-REGISTERED999");
				model.addObject("newOtpKey", "이미등록되었습니다.");
				model.addObject("otpYn", "1");
			}

			// 인증 레벨이 4 이상일 경우 직업 코드 정보 조회하기
			if(result.getAuthLevel() >= 4) {
				Map<String, Object> param = new HashMap<>();
				param.put("codeId", "C0064");
				param.put("langCd", "ko");

				model.addObject("jobCd", commService.selectCodeInfoList(param));
			}

			// api 리스트 정보 조회
			List<OpenApiVO> apiList = holdportOpenApiService.selectApiList(reqUserInfoVO);

			model.setViewName(CommonUtils.getSitePackageKey(request) + "/mypage/mpg001/info");
			model.addObject("info", result);
			model.addObject("tab", strTabIndex);
			model.addObject("now", DateUtils.getDate("0"));

			model.addObject("apiList", apiList);

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return model;
	}

	@RequestMapping(value = "/site/info/recommend", method=RequestMethod.POST)
	@ResponseBody
	public RecommendInfoVO getRecommendInfoVO(HttpServletRequest request, HttpSession session) {

		RecommendInfoVO result = new RecommendInfoVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			if(CommonUtils.isLogin(user)) {
				ReqUserInfoVO reqUserInfoVO = new ReqUserInfoVO();
				reqUserInfoVO.setExchangeId(CommonUtils.getNewExchangeId(request));
				reqUserInfoVO.setUserId(user.getUserId());

				result = holdportInfoService.selectRecommendInfo(reqUserInfoVO);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return result;
	}

	@RequestMapping(value = "/site/info/pwChange", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO pwChange(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid PwChangeVO vo
			, BindingResult bindingResult) {

		ResultVO result = new ResultVO();

		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

				if(CommonUtils.isLogin(user)) {
					vo.setExchangeId(CommonUtils.getNewExchangeId(request));
					vo.setUserId(user.getUserId());

					if(StringUtils.checkPassword(vo.getNewPw())) {
						result = holdportInfoService.changePassword(vo);

						if(result.getRtnCd() == 0) {
							SessionInformation sessionId = sessionRegistry.getSessionInformation(session.getId());

							if (sessionId != null) {
								sessionId.expireNow();
							}

							session.invalidate();
						}
					} else {
						result.setRtnCd(-5048);
						result.setRtnMsg("비밀번호는 대소문자, 숫자, 특수문자 포함 8자리 이상 25자리 이하입니다");
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5019);
			result.setRtnMsg("비밀번호 변경에 실패하였습니다");
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(vo.getExchangeId());
			insertHistoryVO.setUserId(vo.getUserId());
			insertHistoryVO.setReqOperCd(11);
			insertHistoryVO.setOs(vo.getOs());
			insertHistoryVO.setPrivateIp(vo.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(vo.getBrowser());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("사용자 이력 저장 실패");
				logger.error(e.getMessage());
			}
		}

		return result;
	}

	@RequestMapping(value = "/site/info/changeAuthTypeToSms", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO changeAuthTypeToSms(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqAuthMeansVO reqAuthMeansVO
			, BindingResult bindingResult) {

		ResultVO result = new ResultVO();

		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				if(CommonUtils.isLogin(user)) {
					reqAuthMeansVO.setExchangeId(CommonUtils.getNewExchangeId(request));
					reqAuthMeansVO.setUserId(user.getUserId());
					reqAuthMeansVO.setType(2);
					result = holdportInfoService.changeAuthMeansCdToSms(reqAuthMeansVO);

					if(result.getRtnCd() == 0) {
						user.setAuthMeansCd(reqAuthMeansVO.getType());
						session.setAttribute("userInfo", user);
						redisSessionRepository.save(user);
					} else {
						result.setRtnCd(result.getRtnCd());
						result.setRtnMsg(result.getRtnMsg());
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5020);
			result.setRtnMsg("보안 인증 수단 변경에 실패하였습니다");
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			insertHistoryVO.setUserId(user.getUserId());
			insertHistoryVO.setReqOperCd( reqAuthMeansVO.getType() == 1 ? 8:9 );
			insertHistoryVO.setOs(reqAuthMeansVO.getOs());
			insertHistoryVO.setPrivateIp(reqAuthMeansVO.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(reqAuthMeansVO.getBrowser());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("사용자 이력 저장 실패");
				logger.error(e.getMessage());
			}
		}


		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/site/info/requestSmsAuthNumber", method = RequestMethod.POST)
	public Map<String, Object> requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);

			// 인증코드 저장처리 프로시저 호출
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);				// 인증목적 식별코드	1: 핸드폰 인증, 2: 출금인증, 3: 비밀번호 변경, 4: 회원탈퇴, 5: 클라우드세일
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// 휴대폰 번호
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", 0);
			Map<String, Object> insertAuthCodeMap = holdportWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
			int rtnCd = Double.valueOf(CommonUtils.strNlv(insertAuthCodeMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				resultMap.clear();
				resultMap.put("rtnCd", rtnCd);
				resultMap.put("rtnMsg", insertAuthCodeMap.get("V_RTN_MSG"));
				return resultMap;
			}

			String strAuthCode = insertAuthCodeMap.get("V_AUTH_CODE").toString();
			SendSmsVO sendSmsVo = new SendSmsVO();
			sendSmsVo.setExchangeId(strExchangeId);
			sendSmsVo.setUserId(strUserId);
			sendSmsVo.setTo(memberInfoVo.getMOBILE());
			String smsMsg = String.format("[BITA500] 본인확인 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지", strAuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");

			boolean sendFlag = HttpSender.httpSmsRequest(memberInfoVo.getMOBILE(), smsMsg);

			//sendFlag = true;
			resultMap.clear();

			if(sendFlag) {
				resultMap.put("rtnCd", 0);
				resultMap.put("rtnMsg", "인증번호 발송에 성공하였습니다");
			}else {
				resultMap.put("rtnCd", -5009);
				resultMap.put("rtnMsg", "인증번호 발송에 실패하였습니다");
			}

			return resultMap;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resultMap.clear();
			resultMap.put("rtnCd", -5009);
			resultMap.put("rtnMsg", CommonUtils.getPrintStackTrace(e));
			return resultMap;
		}
	}

	@RequestMapping(value = "/site/info/changeAuthTypeToOtp", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO changeAuthTypeToOtp(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqAuthMeansVO reqAuthMeansVO
			, BindingResult bindingResult) {

		ResultVO result = new ResultVO();

		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				if(CommonUtils.isLogin(user)) {
					reqAuthMeansVO.setExchangeId(CommonUtils.getNewExchangeId(request));
					reqAuthMeansVO.setUserId(user.getUserId());
					reqAuthMeansVO.setType(1);
					result = holdportInfoService.changeAuthMeansCdToOtp(reqAuthMeansVO);

					if(result.getRtnCd() == 0) {
						user.setAuthMeansCd(reqAuthMeansVO.getType());
						session.setAttribute("userInfo", user);
						redisSessionRepository.save(user);
					} else {
						result.setRtnCd(result.getRtnCd());
						result.setRtnMsg(result.getRtnMsg());
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5020);
			result.setRtnMsg("보안 인증 수단 변경에 실패하였습니다");
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			insertHistoryVO.setUserId(user.getUserId());
			insertHistoryVO.setReqOperCd( reqAuthMeansVO.getType() == 1 ? 8:9 );
			insertHistoryVO.setOs(reqAuthMeansVO.getOs());
			insertHistoryVO.setPrivateIp(reqAuthMeansVO.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(reqAuthMeansVO.getBrowser());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("사용자 이력 저장 실패");
				logger.error(e.getMessage());
			}
		}

		return result;
	}

	@RequestMapping(value = "/site/info/marketing", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO updateMarketing(HttpServletRequest request, HttpSession session) {

		ResultVO result = new ResultVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			if(CommonUtils.isLogin(user)) {
				ReqMarketingAgreeVO vo = new ReqMarketingAgreeVO();
				vo.setExchangeId(CommonUtils.getNewExchangeId(request));
				vo.setUserId(user.getUserId());
				vo.setAgree(Integer.parseInt(request.getParameter("agree")));

				result = holdportInfoService.updateMarketingAgree(vo);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5021);
			result.setRtnMsg("마케팅 동의 수정에 실패하였습니다");
		}

		return result;
	}

	@RequestMapping(value = "/site/info/getAccessLog", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccessLog(HttpServletRequest request, HttpSession session
			, ReqAccessLogVO reqAccessLogVO) {

		Map<String, Object> result = new HashMap<>();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			int pageNum = reqAccessLogVO.getPageNum();

			reqAccessLogVO.setPageNum((pageNum - 1) * 10);
			reqAccessLogVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			reqAccessLogVO.setUserId(user.getUserId());

			result = holdportInfoService.selectAccessLog(reqAccessLogVO);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return result;
	}

	@RequestMapping(value = "/site/auth/smsAuthForm", method=RequestMethod.GET)
	public ModelAndView moveSureMobileAuth(HttpSession session, HttpServletRequest request
			, @RequestParam(value = "os", required = true) String os
			, @RequestParam(value = "browser", required = true) String browser
			, @RequestParam(value = "privateIp", required = true) String privateIp) throws Exception {

		ModelAndView model = new ModelAndView();

		try {

			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);

			if(CommonUtils.isLogin(user)) {

				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());

				logger.info("GET request["+user.getUserId()+"], os["+os+"], browser["+browser+"], privateIp["+privateIp+"]");

				boolean result = holdportInfoService.checkLastAuthDateTime(param);

				if(result) {

					model.setViewName("/holdport/auth/smsAuthForm");
					model.addObject("dateTime", DateUtils.getNow());
					model.addObject("os", os);
					model.addObject("browser", browser);
					model.addObject("privateIp", privateIp);
				} else {
					model.setViewName("/holdport/auth/kcpValid");
				}
			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/site/auth/sureMobileAuthSms", method = RequestMethod.POST)
	public Map<String, Object> sureMobileAuthSms(HttpSession session, HttpServletRequest request) throws Exception {

		boolean resultFlag = false;
		Map<String, Object> resultMap = new HashMap<>();

		//ModelAndView model = new ModelAndView();

		try {

			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			if(CommonUtils.isLogin(user)) {

				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());

				String phoneNo = request.getParameter("phoneNo");

				logger.info("Auth request["+user.getUserId()+"], phoneNo["+phoneNo+"]");
				logger.info("Post request["+user.getUserId()+"], os["+request.getParameter("os")+"], browser["+request.getParameter("browser")+"], privateIp["+request.getParameter("privateIp")+"]");

				boolean checkFlag = holdportInfoService.checkLastAuthDateTime(param);
				if(checkFlag) {

					//1. send auth number
					String authNum = String.format("%06d", (int)(Math.random()*1000000%1000000));

					logger.info("authNum["+authNum+"]");

					String message="[BITA500] 본인확인 인증번호는 "+authNum+" 입니다.";

					boolean sendFlag = HttpSender.httpSmsRequest(phoneNo, message);

					//sendFlag = true;

					if( sendFlag) {

						logger.info("Sms Send Success ["+authNum+"]");

						redisSessionRepository.put(user.getUserId()+"#AUTH_STEP2", authNum);

						logger.info("Redis update Success key["+redisSessionRepository.get(user.getUserId()+"#AUTH_STEP2")+"]");

						resultFlag = true;

					}else {
						logger.info("Sms Send Fail ["+authNum+"]");
					}

				}
			}
			if(resultFlag) {

				resultMap.clear();
				resultMap.put("rtnCd", 0);
				resultMap.put("rtnMsg", "메시지발송성공");

				logger.info("response data["+resultMap.toString()+"]");
				return resultMap;


			}else {
				resultMap.clear();
				resultMap.put("rtnCd", -5009);
				resultMap.put("rtnMsg", "메시지발송실패");

				logger.info("response data["+resultMap.toString()+"]");
				return resultMap;


			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			resultMap.clear();
			resultMap.put("rtnCd", -5009);
			resultMap.put("rtnMsg", CommonUtils.getPrintStackTrace(e));

			logger.info("response data["+resultMap.toString()+"]");
			return resultMap;


		}

		//model.setViewName("/holdport/auth/smsAuthForm");
		//model.addObject("result", result);


	}

	@ResponseBody
	@RequestMapping(value = "/site/auth/sureMobileAuthReq", method = RequestMethod.POST)
	public Map<String, Object> sureMobileAuthReq(HttpServletRequest request, HttpSession session) throws Exception {

		boolean resultFlag= false;

		ResultVO result = new ResultVO();
		Map<String, Object> resultMap = new HashMap<>();

		//ModelAndView model = new ModelAndView();

		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());





		KcpAuthVO kcpAuthVO = new KcpAuthVO();

		try {

			if(CommonUtils.isLogin(user)) {

				kcpAuthVO.setExchangeId(CommonUtils.getNewExchangeId(request));
				kcpAuthVO.setUserId(user.getUserId());

				kcpAuthVO.setOs(request.getParameter("os"));
				kcpAuthVO.setBrowser(request.getParameter("browser"));
				kcpAuthVO.setPrivateIp(request.getParameter("privateIp"));

				kcpAuthVO.setPublicIp(CommonUtils.getClientIpAddr(request));
				kcpAuthVO.setPhoneNo(request.getParameter("phoneNo"));

				logger.info("Auth request["+user.getUserId()+"], phoneNo["+request.getParameter("phoneNo")+"]");
				logger.info("Post request["+user.getUserId()+"], os["+request.getParameter("os")+"], browser["+request.getParameter("browser")+"], privateIp["+request.getParameter("privateIp")+"]");

				String redisAuthNumber = redisSessionRepository.get(user.getUserId()+"#AUTH_STEP2");
				String requestAuthNumber = request.getParameter("authNum");

				logger.info("Redis get key["+redisAuthNumber+"], request["+requestAuthNumber+"]");

				if(redisAuthNumber!=null) {

					if(requestAuthNumber.equals(redisAuthNumber)) {

						logger.info("same auth number ["+result.getRtnCd()+"]");

						result = holdportInfoService.updatePhoneNo(kcpAuthVO);

						logger.info("db updateflag ["+result.getRtnCd()+"]");

						if(result.getRtnCd() == 0) {

							user.setAuthLevel(2);
							redisSessionRepository.update(user);
							logger.info("final Level update success ["+result.getRtnCd()+"]");
							resultFlag=true;

						}
					}
				}
			}

			if(resultFlag) {

				resultMap.clear();
				resultMap.put("rtnCd", 0);
				resultMap.put("rtnMsg", "휴대폰 인증에 성공하였습니다");

				logger.info("success response data["+resultMap.toString()+"]");

				return resultMap;


			}else {
				resultMap.clear();
				resultMap.put("rtnCd", -5022);
				resultMap.put("rtnMsg", "본인 인증에 실패하였습니다");

				logger.info("fail response data["+resultMap.toString()+"]");

				return resultMap;
			}


		} catch(Exception e) {

			logger.error(CommonUtils.getPrintStackTrace(e));
			resultMap.clear();
			resultMap.put("rtnCd", -5022);
			resultMap.put("rtnMsg", "본인 인증에 실패하였습니다");

			logger.info("response data["+resultMap.toString()+"]");

			return resultMap;

		} finally {

			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			insertHistoryVO.setUserId(user.getUserId());
			insertHistoryVO.setReqOperCd(10);
			insertHistoryVO.setOs(kcpAuthVO.getOs());
			insertHistoryVO.setPrivateIp(kcpAuthVO.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(kcpAuthVO.getBrowser());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("사용자 이력 저장 실패");
				logger.error(e.getMessage());
			}
		}

		//model.setViewName("/holdport/auth/smsAuthForm");
		//model.addObject("result", result);

	}

	@RequestMapping(value = "/site/auth/kcp", method=RequestMethod.GET)
	public ModelAndView moveKcp(HttpSession session, HttpServletRequest request
			, @RequestParam(value = "os", required = true) String os
			, @RequestParam(value = "browser", required = true) String browser
			, @RequestParam(value = "privateIp", required = true) String privateIp) throws Exception {

		ModelAndView model = new ModelAndView();

		try {

			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			logger.info(" /site/auth/kcp GET Request Start ");

			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());

				boolean result = holdportInfoService.checkLastAuthDateTime(param);

				if(result) {
					model.setViewName("holdport/auth/kcpStart");
					//model.setViewName("holdport/auth/kcpcert_start");
					model.addObject("dateTime", DateUtils.getNow());
					model.addObject("os", os);
					model.addObject("browser", browser);
					model.addObject("privateIp", privateIp);
					model.addObject("sessionId", request.getRequestedSessionId());
				} else {
					model.setViewName("holdport/auth/kcpValid");
				}
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return model;
	}

	@RequestMapping(value="/site/auth/kcp", method = RequestMethod.POST)
	public ModelAndView processAuthKcp(HttpServletRequest request, HttpServletResponse response) throws Exception {


		logger.info(" processAuthKcp POST Request Start ");
		logger.info("sessionID["+request.getRequestedSessionId()+"]");

		ModelAndView model = new ModelAndView();
		ResultVO result = new ResultVO();

		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getParameter("param_opt_3"));

		logger.info(" userID["+user.getUserId()+"]");

		KcpAuthVO kcpAuthVO = new KcpAuthVO();

		try {

			if(CommonUtils.isLogin(user)) {

				String siteCd = request.getParameter("site_cd");
				String certNo = request.getParameter("cert_no");
				String encCertData = request.getParameter("enc_cert_data2");

				logger.info("siteCd["+siteCd+"],certNo["+certNo+"],encCertData["+encCertData+"]");

				kcpAuthVO = KCPUtils.decryptNewData(siteCd, certNo, encCertData);

				logger.info(">> userName["+kcpAuthVO.getUserName()+"],phoneNo["+kcpAuthVO.getPhoneNo()+"], CIValue["+kcpAuthVO.getCiValue()+"]");

				kcpAuthVO.setExchangeId(CommonUtils.getNewExchangeId(request));
				kcpAuthVO.setUserId(user.getUserId());
				kcpAuthVO.setOs(request.getParameter("param_opt_1"));
				kcpAuthVO.setBrowser(request.getParameter("param_opt_2"));
				kcpAuthVO.setPrivateIp("");
				kcpAuthVO.setPublicIp(CommonUtils.getClientIpAddr(request));

				/*
				 * if("02".equals(kcpAuthVO.getLocalCode())) { result.setRtnCd(-5068);
				 * result.setRtnMsg("외국인 사용자는 사이트 이용이 불가능합니다"); } else
				 */if(DateUtils.checkAdult19(kcpAuthVO.getBirthDay())) {
					logger.error(">>checkAdult19:"+user.getUserId());
					result.setRtnCd(-5069);
					result.setRtnMsg("만 19세 미만의 사용자는 사이트 이용이 불가능합니다");
				} else {
					logger.info(">>updatePhoneNo userName["+kcpAuthVO.getUserName()+"],phoneNo["+kcpAuthVO.getPhoneNo()+"]");
					result = holdportInfoService.updatePhoneNo(kcpAuthVO);

					if(result.getRtnCd() == 0) {

						user.setAuthLevel(2);
						redisSessionRepository.update(user);

						CustomUserDetails copyUser= user;
						copyUser.setSessionId(request.getRequestedSessionId());
						redisSessionRepository.update(copyUser);

						// XRP 1개 지급
						logger.debug(">> registration_level2_"+user.getUserId());

						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("ARG_REQ_IP", "255.255.255.200");
						paramMap.put("ARG_REQ_CHNL_CD", "WAS");
						paramMap.put("ARG_EXCHANGE_ID", "HOLDPORT");
						paramMap.put("ARG_USER_ID", user.getUserId());
						paramMap.put("ARG_FROM", "registration_level2");
						paramMap.put("ARG_TO", "registration_level2");
						paramMap.put("ARG_TAG", "");
						paramMap.put("ARG_INFO", "registration_level2");
						paramMap.put("ARG_COIN_NO", 150); // XRP
						paramMap.put("ARG_REQ_QTY", 1.0);
						paramMap.put("ARG_TXID", String.format("registration_level2_%s", user.getUserId()) );
						// TXID 중복일경우 입금 처리 되지 않음. 아이디에대해 여러번 처리해도 한번 밖에 처리 안됨.

						holdportWalletService.call_PR_WLL_INSERT_DEPOSIT_REQUEST2(paramMap);


					} else {
						logger.error(">>failed updatePhoneNo userName["+kcpAuthVO.getUserName()+"],phoneNo["+kcpAuthVO.getPhoneNo()+"],  code["+ result.getRtnCd()+"], msg["+result.getRtnMsg()+"]");
					}
				}
			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5022);
			result.setRtnMsg("KCP 본인 인증에 실패하였습니다");
		} finally {

			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			insertHistoryVO.setUserId(user.getUserId());
			insertHistoryVO.setReqOperCd(10);
			insertHistoryVO.setOs(kcpAuthVO.getOs());
			insertHistoryVO.setPrivateIp(kcpAuthVO.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(kcpAuthVO.getBrowser());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("사용자 이력 저장 실패");
				logger.error(e.getMessage());
			}

		}

		model.setViewName("holdport/auth/kcpEnd");
		model.addObject("result", result);

		return model;

	}

	@RequestMapping(value = "/site/auth/kcpcert_proc_req.do", method = RequestMethod.GET)
	public ModelAndView kcpRequestGet(HttpSession session, HttpServletRequest request) throws Exception {

		logger.info(">> kcpcert_proc_req.do:: GET :: kcpRequestGet ");

		logger.info("request sessionID["+request.getRequestedSessionId()+"]");
		logger.info("server sessionID["+session.getId()+"]");


		ModelAndView model = new ModelAndView();
		model.setViewName("holdport/auth/kcpcert_proc_req");

		return model;
	}

	@RequestMapping(value = "/site/auth/kcpcert_proc_req.do", method = RequestMethod.POST)
	public ModelAndView kcpRequestPost(HttpSession session, HttpServletRequest request) throws Exception {

		logger.info(">> kcpcert_proc_req.do:: POST :: kcpRequestPost ");

		logger.info("request sessionID["+request.getRequestedSessionId()+"]");
	    logger.info("server sessionID["+session.getId()+"]");

		ModelAndView model = new ModelAndView();

		model.setViewName("holdport/auth/kcpcert_proc_req");

		return model;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/site/auth/kcpcert_proc_res.do", method = RequestMethod.POST)
	public ModelAndView kcpResponse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	    //response.addHeader("X-Frame-Options", "SAMEORIGIN");


	    logger.info(">> kcpcert_proc_res.do:: POST :: kcpResponse ");

	    logger.info("sessionID["+request.getRequestedSessionId()+"]");

		ModelAndView model = new ModelAndView();
		model.setViewName("holdport/auth/kcpcert_proc_res");
		return model;

	}

}
