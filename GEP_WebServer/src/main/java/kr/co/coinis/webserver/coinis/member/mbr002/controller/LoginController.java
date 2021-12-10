/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.member.mbr002.service.LoginService;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.ReqLoginVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.SessionUtil;
import kr.co.coinis.webserver.common.vo.ResultVO;

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
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@RequestMapping(value = "coinis/login", method = RequestMethod.GET)
	public ModelAndView moveLogin(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		if(CommonUtils.isLogin(userDetails)) {
			// 세션정보가 있을 경우 사이트 초기 페이지로 이동
			model.setViewName("redirect:home");
		} else {
			// 세션이 없을 경우 login page 이동
			model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr002/login");
		}
		
		return model;
	}
	
	@RequestMapping(value = "coinis/logout", method = RequestMethod.GET)
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
		
		model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr002/login");
		
		return model;
	}
	
	@RequestMapping(value = "/coinis/login/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO doLoginCheck(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqLoginVO vo
			, BindingResult bindingResult) {
		ResultVO result = new ResultVO();
		
		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);
				
				result.setRtnCd(-1);
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				vo.setExchangeId(CommonUtils.getExchangeId(request));
				
				// 비밀 번호 검사
				result = loginService.selectLoginMatch(vo);
				
				// 비밀번호가 일치 했을 경우의 처리
				if(result.getRtnCd() > -1) {
					// 로그인 상태 확인을 위한 변수
					//int status = result.getRtnCd() == 0 ? 1:0;
					int status = 1;
					result.setRtnCd(0);

					// 세션 객체 생성후 세션 값, 로그인 상태 값 셋팅 
					CustomUserDetails customUserDetails = new CustomUserDetails();
					customUserDetails.setSessionId(SessionUtil.getSessionId());
					customUserDetails.setUserId(vo.getId());
					customUserDetails.setLoginYn(status);
					customUserDetails.setAuthMeansCd(result.getRtnCd());
					
					// 로그인 처리 시 인증 여부 확인을 위해 session 및  redis에 추가인증여부 상태를 저장해둔다 
					session.setAttribute("userInfo", customUserDetails);
					redisSessionRepository.save(customUserDetails);
				}
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
			result.setRtnCd(-1);
			result.setRtnMsg("작업중 오류가 발생하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/coinis/login/auth", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO doAuthCheck(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqLoginVO vo
			, BindingResult bindingResult) {
		ResultVO result = new ResultVO();
		
		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);
				
				result.setRtnCd(-1);
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				vo.setExchangeId(CommonUtils.getExchangeId(request));
				
				
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
			result.setRtnCd(-1);
			result.setRtnMsg("작업중 오류가 발생하였습니다");
		}
		
		return result;
	}
}
