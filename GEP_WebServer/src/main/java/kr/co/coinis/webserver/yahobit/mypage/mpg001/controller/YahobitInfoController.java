/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg001.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import kr.co.coinis.webserver.common.utils.KCPUtils;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.service.YahobitInfoService;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.UserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.service.YahobitOpenApiService;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.OpenApiVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.service.YahobitWalletService;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

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
public class YahobitInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitInfoController.class);

	@Autowired
	private CommService commService;
	
	@Autowired
	private YahobitInfoService yahobitInfoService;
	
	@Autowired
	private YahobitWalletService yahobitWalletService;
	
	@Autowired
	private YahobitOpenApiService yahobitOpenApiService;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@RequestMapping(value = "/alibit/info", method=RequestMethod.GET)
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "tab", defaultValue = "", required = false) String strTabIndex) {
		
		UserInfoVO result = new UserInfoVO();
		ModelAndView model = new ModelAndView();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			ReqUserInfoVO reqUserInfoVO = new ReqUserInfoVO();
			reqUserInfoVO.setExchangeId(strExchangeId);
			reqUserInfoVO.setUserId(strUserId);
			
			result = yahobitInfoService.selectUserInfo(reqUserInfoVO);
			result.setMobile(StringUtils.mobileMasking(result.getMobile()));
			
			if(strExchangeId.equalsIgnoreCase("YAHOBIT"))
				strExchangeId = "ALIBIT";
			
			if(result.getOtpYn() == 0) {
				String strOtpKey = CommonUtils.generateOTPKey(32);
				//model.addObject("googleOtpKey", "otpauth://totp/" + strExchangeId + "(" + CommonUtils.maskingUserId(strUserId) + ")?secret=" + strOtpKey);
				model.addObject("googleOtpKey", "otpauth://totp/" + CommonUtils.maskingUserId(strUserId) + "?secret=" + strOtpKey + "&issuer=" + strExchangeId);
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
			List<OpenApiVO> apiList = yahobitOpenApiService.selectApiList(reqUserInfoVO);

			model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg001/info");
			model.addObject("info", result);
			model.addObject("tab", strTabIndex);
			model.addObject("now", DateUtils.getDate("0"));
			model.addObject("apiList", apiList);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return model;
	}
	
	@RequestMapping(value = "/alibit/info/recommend", method=RequestMethod.POST)
	@ResponseBody
	public RecommendInfoVO getRecommendInfoVO(HttpServletRequest request, HttpSession session) {
		
		RecommendInfoVO result = new RecommendInfoVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				ReqUserInfoVO reqUserInfoVO = new ReqUserInfoVO();
				reqUserInfoVO.setExchangeId(CommonUtils.getExchangeId(request));
				reqUserInfoVO.setUserId(user.getUserId());
				
				result = yahobitInfoService.selectRecommendInfo(reqUserInfoVO);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/info/pwChange", method=RequestMethod.POST)
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
					vo.setExchangeId(CommonUtils.getExchangeId(request));
					vo.setUserId(user.getUserId());
					
					if(StringUtils.checkPassword(vo.getNewPw())) {
						result = yahobitInfoService.changePassword(vo);
						
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
	
	@RequestMapping(value = "/alibit/info/changeAuthTypeToSms", method=RequestMethod.POST)
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
					reqAuthMeansVO.setExchangeId(CommonUtils.getExchangeId(request));
					reqAuthMeansVO.setUserId(user.getUserId());
					reqAuthMeansVO.setType(2);
					result = yahobitInfoService.changeAuthMeansCdToSms(reqAuthMeansVO);
					
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
			insertHistoryVO.setExchangeId(CommonUtils.getExchangeId(request));
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
	@RequestMapping(value = "/alibit/info/requestSmsAuthNumber", method = RequestMethod.POST)
	public Map<String, Object> requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);

			// 인증코드 저장처리 프로시저 호출
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);				// 인증목적 식별코드	1: 핸드폰 인증, 2: 출금인증, 3: 비밀번호 변경, 4: 회원탈퇴, 5: 클라우드세일
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// 휴대폰 번호
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", 0);
			Map<String, Object> insertAuthCodeMap = yahobitWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
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
			String smsMsg = String.format("[ALIBIT] 본인확인 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지", strAuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");
			
			ResultVO resultVo = commService.sendSms(sendSmsVo);
			
			resultMap.clear();
			resultMap.put("rtnCd", resultVo.getRtnCd());
			resultMap.put("rtnMsg", resultVo.getRtnMsg());
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
	
	@RequestMapping(value = "/alibit/info/changeAuthTypeToOtp", method=RequestMethod.POST)
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
					reqAuthMeansVO.setExchangeId(CommonUtils.getExchangeId(request));
					reqAuthMeansVO.setUserId(user.getUserId());
					reqAuthMeansVO.setType(1);
					result = yahobitInfoService.changeAuthMeansCdToOtp(reqAuthMeansVO);
					
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
			insertHistoryVO.setExchangeId(CommonUtils.getExchangeId(request));
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
	
	@RequestMapping(value = "/alibit/info/marketing", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO updateMarketing(HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				ReqMarketingAgreeVO vo = new ReqMarketingAgreeVO();
				vo.setExchangeId(CommonUtils.getExchangeId(request));
				vo.setUserId(user.getUserId());
				vo.setAgree(Integer.parseInt(request.getParameter("agree")));
				
				result = yahobitInfoService.updateMarketingAgree(vo);
			} 
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5021);
			result.setRtnMsg("마케팅 동의 수정에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/info/getAccessLog", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccessLog(HttpServletRequest request, HttpSession session
			, ReqAccessLogVO reqAccessLogVO) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			int pageNum = reqAccessLogVO.getPageNum();
			
			reqAccessLogVO.setPageNum((pageNum - 1) * 10);
			reqAccessLogVO.setExchangeId(CommonUtils.getExchangeId(request));
			reqAccessLogVO.setUserId(user.getUserId());
			
			result = yahobitInfoService.selectAccessLog(reqAccessLogVO);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/auth/kcp", method=RequestMethod.GET)
	public ModelAndView moveKcp(HttpSession session, HttpServletRequest request
			, @RequestParam(value = "os", required = true) String os
			, @RequestParam(value = "browser", required = true) String browser
			, @RequestParam(value = "privateIp", required = true) String privateIp) throws Exception {

		ModelAndView model = new ModelAndView();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				
				boolean result = yahobitInfoService.checkLastAuthDateTime(param);
				
				if(result) {
					model.setViewName("yahobit/auth/kcpStart");
					model.addObject("dateTime", DateUtils.getNow());
					model.addObject("os", os);
					model.addObject("browser", browser);
					model.addObject("privateIp", privateIp);
				} else {
					model.setViewName("yahobit/auth/kcpValid");
				}
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return model;
	}
	
	@RequestMapping(value = "/alibit/auth/kcp", method = RequestMethod.POST)
	public ModelAndView getInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		ModelAndView model = new ModelAndView();
		ResultVO result = new ResultVO();
		
		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		KcpAuthVO kcpAuthVO = new KcpAuthVO();
		
		try {
			if(CommonUtils.isLogin(user)) {
				String siteCd = request.getParameter("site_cd");
				String certNo = request.getParameter("cert_no");
				String encCertData = request.getParameter("enc_cert_data");
			
				kcpAuthVO = KCPUtils.decrypt(siteCd, certNo, encCertData);
				kcpAuthVO.setExchangeId(CommonUtils.getExchangeId(request));
				kcpAuthVO.setUserId(user.getUserId());
				kcpAuthVO.setOs(request.getParameter("param_opt_1"));
				kcpAuthVO.setBrowser(request.getParameter("param_opt_2"));
				kcpAuthVO.setPrivateIp(request.getParameter("param_opt_3"));
				kcpAuthVO.setPublicIp(CommonUtils.getClientIpAddr(request));
				
				/*
				 * if("02".equals(kcpAuthVO.getLocalCode())) { result.setRtnCd(-5068);
				 * result.setRtnMsg("외국인 사용자는 사이트 이용이 불가능합니다"); } else
				 */if(DateUtils.checkAdult19(kcpAuthVO.getBirthDay())) {
					result.setRtnCd(-5069);
					result.setRtnMsg("만 19세 미만의 사용자는 사이트 이용이 불가능합니다");
				} else {
					result = yahobitInfoService.updatePhoneNo(kcpAuthVO);
					
					if(result.getRtnCd() == 0) {
						user.setAuthLevel(2);
						
						redisSessionRepository.update(user);
					}
				}
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5022);
			result.setRtnMsg("KCP 본인 인증에 실패하였습니다");
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getExchangeId(request));
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
		
		model.setViewName("yahobit/auth/kcpEnd");
		model.addObject("result", result);
		
		return model;
	}
	
	@RequestMapping(value = "/alibit/auth/kcpcert_proc_req.do")
	public ModelAndView kcpRequest2(HttpSession session, HttpServletRequest request) throws Exception {

		ModelAndView model = new ModelAndView();
		model.setViewName("yahobit/auth/kcpcert_proc_req");
		
		return model;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/alibit/auth/kcpcert_proc_res.do", method = RequestMethod.POST)
	public ModelAndView kcpResponse(HttpSession session, HttpServletResponse response) throws Exception {

		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");

		ModelAndView model = new ModelAndView();
		model.setViewName("yahobit/auth/kcpcert_proc_res");
		return model;
	}

}
