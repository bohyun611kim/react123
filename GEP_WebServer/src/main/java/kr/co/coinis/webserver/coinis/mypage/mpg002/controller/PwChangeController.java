/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.controller;

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

import kr.co.coinis.webserver.coinis.mypage.mpg002.service.PwChangeService;
import kr.co.coinis.webserver.coinis.mypage.mpg002.vo.ReqChangePwVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg002.controller 
 *    |_ PwChangeController.java
 * 
 * </pre>
 * 
 * @date : 2019. 3. 21. 오후 1:14:43
 * @version :
 * @author : Seongcheol
 */
@Controller
public class PwChangeController {

	private static final Logger logger = LoggerFactory.getLogger(PwChangeController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Autowired
	private PwChangeService pwChangeSrevice;

	@RequestMapping(value = "coinis/pwChange")
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg002/pwChange");

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String userid = userDetails.getUserId();
		model.addObject("userid", userid);

		return model;
	}

	@RequestMapping(value = "/coinis/pwChange/checkPW", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO doPwCheck(HttpServletRequest request, HttpSession session, @ModelAttribute @Valid ReqChangePwVO vo,
			BindingResult bindingResult) {
		ResultVO result = new ResultVO();

		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(-1);
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

				vo.setExchangeId(CommonUtils.getExchangeId(request));
				vo.setUserID(userDetails.getUserId());

				// 비밀 번호 변경
				result = pwChangeSrevice.PwChange(vo);

				if (result.getRtnCd() == 0) {
					SessionInformation sessionId = sessionRegistry.getSessionInformation(session.getId());

					if (sessionId != null) {
						sessionId.expireNow();
					}

					session.invalidate();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setRtnCd(-1);
			result.setRtnMsg("작업중 오류가 발생하였습니다");
		}

		return result;
	}
}
