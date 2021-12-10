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
 * @date : 2019. 3. 21. 오후 3:08:55
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
			// 세션정보가 있을 경우 사이트 초기 페이지로 이동
			model.setViewName("redirect:/");
		} else if(userDetails != null) {
			// 추가 인증 처리 여부 확인
			if(userDetails.getAuthMeansCd() == 1) {
				// otp 인증
				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr005/otpOuth");
			} else if(userDetails.getAuthMeansCd() == 2) {
				// sms 인증
				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr004/mobileAuth");
			} else {
				// 세션 제거 후 로그인 화면 이동
				SessionInformation sessionId = sessionRegistry.getSessionInformation(session.getId());

				if (sessionId != null) { sessionId.expireNow(); }

				if(userDetails != null) {
					redisSessionRepository.delete(userDetails.getSessionId());
				}

				session.invalidate();

				model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr002/login");
			}
		} else {
			// 세션이 없을 경우 login page 이동
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

		/* 2019.12.06 YAHOBIT 리브랜딩으로 인하여 ALIBIT으로 변경(strPageKey) */
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
					// 비밀 번호 검사
					result = yahobitLoginService.selectLoginMatch(vo);

					// 비밀번호가 일치 했을 경우의 처리
					if(result.getRtnCd() > -1) {
						// 로그인 상태 확인을 위한 변수
						int status = result.getRtnCd() == 0 ? 1:0;
						//int status = 1;
						//result.setRtnCd(0);

						// 세션 객체 생성후 세션 값, 로그인 상태 값 셋팅
						CustomUserDetails customUserDetails = new CustomUserDetails();
						customUserDetails.setSessionId(SessionUtil.getSessionId());
						customUserDetails.setUserId(vo.getId());
						customUserDetails.setLoginYn(status);
						customUserDetails.setAuthMeansCd(result.getRtnCd());

						// 로그인 처리 시 인증 여부 확인을 위해 session 및  redis에 추가인증여부 상태를 저장해둔다
						session.setAttribute("userInfo", customUserDetails);
						redisSessionRepository.save(customUserDetails);

						// 비밀번호 실패 횟수 초기화
						FailCntVO failCntVO = new FailCntVO();
						failCntVO.setProcFlag("L");
						failCntVO.setExchangeId(vo.getExchangeId());
						failCntVO.setUserId(vo.getId());

						failCntVO = yahobitLoginService.procSetFailCnt(failCntVO);

						if(failCntVO.getRtnCd() != 0) {
							logger.error("로그인 실패 횟수 초기화 실패");
						}
					} else {
						result.setRtnCd(result.getRtnCd());
						result.setRtnMsg(result.getRtnMsg());
					}
				} else {
					result.setRtnCd(-5006);
					result.setRtnMsg("잘못된 이메일 주소입니다.");
				}
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5008);
			result.setRtnMsg("로그인에 실패하였습니다");
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
				logger.error("사용자 이력 저장 실패");
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
				String smsMsg = String.format("[ALIBIT] 로그인 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지"
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
			result.setRtnMsg("인증번호 발송에 실패하였습니다");
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

				// 로그인 처리 시 인증 여부 확인을 위해 session 및  redis에 추가인증여부 상태를 저장해둔다
				session.setAttribute("userInfo", userDetails);
				redisSessionRepository.save(userDetails);
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5010);
			result.setRtnMsg("인증번호 인증에 실패하였습니다");
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

				// 로그인 처리 시 인증 여부 확인을 위해 session 및  redis에 추가인증여부 상태를 저장해둔다
				session.setAttribute("userInfo", userDetails);
				redisSessionRepository.save(userDetails);
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5010);
			result.setRtnMsg("인증번호 인증에 실패하였습니다");
		}

		return result;
	}

}
