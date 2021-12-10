/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.mypage.mpg001.service.InfoService;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.MemberProfileInfoVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.ReqUpdateCodeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.controller 
 *    |_ InfoController.java
 * 
 * </pre>
 * @date : 2019. 5. 10. 오전 10:22:03
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class InfoController {
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private InfoService infoService;
	
	@RequestMapping(value = "coinis/info")
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg001/info");
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		
		ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
		param.setExchange_id(exchange_id);
		param.setUser_id(user_id);
		
		MemberProfileInfoVO memberProfileInfoVO = infoService.selectMemberProfileInfo(param);
		
		model.addObject("MemberProfileInfo", memberProfileInfoVO);
		
		return model;
	}
	
	@RequestMapping(value = "coinis/info/updateAuthMeansCD")
	@ResponseBody
	public ResultVO updateAuthMeansCD(HttpServletRequest request, HttpSession session
			, ReqUpdateCodeVO param) {
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		
		param.setExchange_id(exchange_id);
		param.setUser_id(user_id);
		
		ResultVO result = infoService.updateAuthMeansCD(param);
		
		return result;
	}
	
	@RequestMapping(value = "coinis/info/updateMarketingAgree")
	public ResultVO updateMarketingAgree(HttpServletRequest request, HttpSession session
			, ReqUpdateCodeVO param) {
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		
		param.setExchange_id(exchange_id);
		param.setUser_id(user_id);
		
		ResultVO result = infoService.updateMarketingAgree(param);
		
		return result;
	}
}
