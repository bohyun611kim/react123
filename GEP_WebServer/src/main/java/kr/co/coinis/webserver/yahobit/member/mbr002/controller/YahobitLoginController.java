/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr002.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.SessionUtil;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.service.YahobitLoginService;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.ReqLoginVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.controller
 *    |_ LoginController.java
 *
 * </pre>
 * @date : 2019. 3. 21. ?????? 3:08:55
 * @version :
 * @author : Seongcheol
 */
@Controller
public class YahobitLoginController {

	private static final Logger logger = LoggerFactory.getLogger(YahobitLoginController.class);

	@Autowired
	private CommService commService;

	@Autowired
	private YahobitLoginService yahobitLoginService;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@RequestMapping(value = "/alibit/login", method = RequestMethod.GET)
	public ModelAndView moveLogin(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();

		CustomUserDetails userDetails = null;

		String sessionKey = request.getRequestedSessionId();

		if(sessionKey != null) {
			userDetails = (CustomUserDetails) redisSessionRepository.findBySession(sessionKey);
		}

		//String path = CommonUtils.getPageKey(request);

		if(CommonUtils.isLogin(userDetails)) {
			// ??????????????? ?????? ?????? ????????? ?????? ???????????? ??????
			model.setViewName("redirect:/");
		} else if(userDetails != null) {
			// ?????? ?????? ?????? ?????? ??????
			if(userDetails.getAuthMeansCd() == 1) {
				// otp ??????
				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr005/otpOuth");
			} else if(userDetails.getAuthMeansCd() == 2) {
				// sms ??????
				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr004/mobileAuth");
			} else {
				// ?????? ?????? ??? ????????? ?????? ??????
				SessionInformation sessionId = sessionRegistry.getSessionInformation(session.getId());

				if (sessionId != null) { sessionId.expireNow(); }

				if(userDetails != null) {
					redisSessionRepository.delete(userDetails.getSessionId());
				}

				session.invalidate();

				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr002/login");
			}
		} else {
			// ????????? ?????? ?????? login page ??????
			model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr002/login");
		}

		return model;
	}

	@RequestMapping(value = "/alibit/logout", method = RequestMethod.GET)
	public ModelAndView moveLogout(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		CustomUserDetails userDetails = null;

		SessionInformation sessionId = sessionRegistry.getSessionInformation(session.getId());

		String sessionKey = request.getRequestedSessionId();

		if(sessionKey != null) {
			userDetails = (CustomUserDetails) redisSessionRepository.findBySession(sessionKey);
		}

		if (sessionId != null) {
			sessionId.expireNow();
		}

		if(userDetails != null) {
			redisSessionRepository.delete(userDetails.getSessionId());
		}

		session.invalidate();

		/* 2019.12.06 YAHOBIT ?????????????????? ????????? ALIBIT?????? ??????(strPageKey) */
		String strPageKey = CommonUtils.getExchangeId(request).toLowerCase();
		if(strPageKey.equalsIgnoreCase("yahobit"))
			strPageKey = "alibit";

		model.setViewName("redirect:/" + strPageKey + "/login");

		return model;
	}

	@RequestMapping(value = "/alibit/login/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO doLoginCheck(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqLoginVO vo
			, BindingResult bindingResult) {
		ResultVO result = new ResultVO();

		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				vo.setExchangeId(CommonUtils.getExchangeId(request));
				vo.setPublicIp(CommonUtils.getClientIpAddr(request));

				if(StringUtils.chekcEmailAddress(vo.getId())) {
					// ?????? ?????? ??????
					result = yahobitLoginService.selectLoginMatch(vo);

					// ??????????????? ?????? ?????? ????????? ??????
					if(result.getRtnCd() > -1) {
						// ????????? ?????? ????????? ?????? ??????
						int status = result.getRtnCd() == 0 ? 1:0;
						//int status = 1;
						//result.setRtnCd(0);

						// ?????? ?????? ????????? ?????? ???, ????????? ?????? ??? ??????
						CustomUserDetails customUserDetails = new CustomUserDetails();
						customUserDetails.setSessionId(SessionUtil.getSessionId());
						customUserDetails.setUserId(vo.getId());
						customUserDetails.setLoginYn(status);
						customUserDetails.setAuthMeansCd(result.getRtnCd());

						// ????????? ?????? ??? ?????? ?????? ????????? ?????? session ???  redis??? ?????????????????? ????????? ???????????????
						session.setAttribute("userInfo", customUserDetails);
						redisSessionRepository.save(customUserDetails);

						// ???????????? ?????? ?????? ?????????
						FailCntVO failCntVO = new FailCntVO();
						failCntVO.setProcFlag("L");
						failCntVO.setExchangeId(vo.getExchangeId());
						failCntVO.setUserId(vo.getId());

						failCntVO = yahobitLoginService.procSetFailCnt(failCntVO);

						if(failCntVO.getRtnCd() != 0) {
							logger.error("????????? ?????? ?????? ????????? ??????");
						}
					} else {
						result.setRtnCd(result.getRtnCd());
						result.setRtnMsg(result.getRtnMsg());
					}
				} else {
					result.setRtnCd(-5006);
					result.setRtnMsg("????????? ????????? ???????????????.");
				}
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5008);
			result.setRtnMsg("???????????? ?????????????????????");
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(CommonUtils.getExchangeId(request));
			insertHistoryVO.setUserId(vo.getId());
			insertHistoryVO.setReqOperCd(11);
			insertHistoryVO.setOs(vo.getOs());
			insertHistoryVO.setPrivateIp(vo.getPrivateIp());
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(vo.getAppInfo());
			insertHistoryVO.setResultCd(result.getRtnCd());
			insertHistoryVO.setLogContents(result.getRtnMsg());

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				logger.error("????????? ?????? ?????? ??????");
				logger.error(e.getMessage());
			}

		}

		return result;
	}

	@RequestMapping(value = "/alibit/login/requestSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO requestSmsCode(HttpServletRequest request, HttpSession session) {
		ResultVO result = new ResultVO();

		try {
			String exchangeId = CommonUtils.getExchangeId(request);

			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(exchangeId);
			authCodeVO.setUserId(userDetails.getUserId());
			authCodeVO.setAuthPurposeCd(2);
			authCodeVO.setAuthMeansCd(2);
			authCodeVO.setExpireSec(3 * 60);

			authCodeVO = yahobitLoginService.requestSmsCode(authCodeVO);

			if(authCodeVO.getRtnCd() == 0) {
				SendSmsVO sendSmsVo = new SendSmsVO();
				sendSmsVo.setExchangeId(exchangeId);
				sendSmsVo.setUserId(userDetails.getUserId());
				sendSmsVo.setTo(authCodeVO.getAuthMeansKeyVal());
				String smsMsg = String.format("[ALIBIT] ????????? ???????????? [%s] (????????????????????????) ???????????? ????????????"
									, authCodeVO.getAuthCd());
				sendSmsVo.setMsg(smsMsg);
				sendSmsVo.setMsgOption("sms");

				result = commService.sendSms(sendSmsVo);
			} else {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5009);
			result.setRtnMsg("???????????? ????????? ?????????????????????");
		}

		return result;
	}

	@RequestMapping(value = "/alibit/login/confirmSmsAuthCode", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO confirmSmsAuthCode(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "code", required = true) String code) {
		ResultVO result = new ResultVO();

		try {
			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

			int authMeansCd = userDetails.getAuthMeansCd();

			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(CommonUtils.getExchangeId(request));
			authCodeVO.setUserId(userDetails.getUserId());
			authCodeVO.setAuthPurposeCd(2);
			authCodeVO.setAuthMeansCd(authMeansCd);
			authCodeVO.setAuthCd(code);

			authCodeVO = yahobitLoginService.confirmSmsAuthCode(authCodeVO);

			if(authCodeVO.getRtnCd() != 0) {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			} else {
				userDetails.setLoginYn(1);

				// ????????? ?????? ??? ?????? ?????? ????????? ?????? session ???  redis??? ?????????????????? ????????? ???????????????
				session.setAttribute("userInfo", userDetails);
				redisSessionRepository.save(userDetails);
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5010);
			result.setRtnMsg("???????????? ????????? ?????????????????????");
		}

		return result;
	}

	@RequestMapping(value = "/alibit/login/confirmOtpAuthCode", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO confirmOtpAuthCode(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "code", required = true) String code) {
		ResultVO result = new ResultVO();

		try {
			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

			int authMeansCd = userDetails.getAuthMeansCd();

			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(CommonUtils.getExchangeId(request));
			authCodeVO.setUserId(userDetails.getUserId());
			authCodeVO.setAuthPurposeCd(2);
			authCodeVO.setAuthMeansCd(authMeansCd);
			authCodeVO.setAuthCd(code);

			result = yahobitLoginService.confirmOtpAuthCode(authCodeVO);

			if(result.getRtnCd() != 0) {
				result.setRtnCd(result.getRtnCd());
				result.setRtnMsg(result.getRtnMsg());
			} else {
				userDetails.setLoginYn(1);

				// ????????? ?????? ??? ?????? ?????? ????????? ?????? session ???  redis??? ?????????????????? ????????? ???????????????
				session.setAttribute("userInfo", userDetails);
				redisSessionRepository.save(userDetails);
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5010);
			result.setRtnMsg("???????????? ????????? ?????????????????????");
		}

		return result;
	}

}
