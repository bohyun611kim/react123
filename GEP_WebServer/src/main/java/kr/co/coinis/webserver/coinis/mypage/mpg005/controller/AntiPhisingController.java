/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg005.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.mypage.mpg005.service.AntiPhisingService;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg005.controller 
 *    |_ AntiPhisingController.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 1:40:14
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class AntiPhisingController {
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private AntiPhisingService antiPhisingService;

	@RequestMapping(value="/coinis/antiPhising", method=RequestMethod.GET)
	public ModelAndView moveExchange(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg005/antiPhising");
		
		return model;
	}
	
	@RequestMapping(value="/coinis/antiPhising/setCode", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setAntiPhisingCode(HttpServletRequest request, HttpSession session
			, @RequestParam("anti_phishing_code") String anti_phishing_code) {
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		
		ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
		param.setExchange_id(exchange_id);
		param.setUser_id(user_id);
		
		String reg_dt = antiPhisingService.selectUserRegisterDT(param);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ARG_EXCHANGE_ID", exchange_id);
		paramMap.put("ARG_USER_ID", user_id);
		paramMap.put("ARG_FISH_ANTI_CODE_VAL", anti_phishing_code);
		paramMap.put("ARG_KEY1", user_id);
		paramMap.put("ARG_KEY2", reg_dt);
		
		Map<String, Object> resultMap = new HashMap<>();
		// 20190401143915
		try {
			resultMap = antiPhisingService.call_PR_WAS_SET_FISH_ANTI_CODE(paramMap);
			int res_cd = Double.valueOf(resultMap.get("V_RTN_CD").toString()).intValue();
			
			resultMap.put("res_cd", res_cd);
			
			if (res_cd != 0)
				resultMap.put("res_msg", CommonUtils.strNlv(resultMap.get("V_RTN_MSG"), "처리 중 오류가 발생하였습니다."));
			else
				resultMap.put("res_msg", CommonUtils.strNlv(resultMap.get("V_RTN_MSG"), "Anti phishing code is successfully registered"));
			
			return resultMap;
		} catch (SQLException e) {
			e.printStackTrace();
			
			resultMap.put("res_cd", -9999);
			resultMap.put("res_msg", CommonUtils.getPrintStackTrace(e) );
			return resultMap;
		}
	}
	
	
	
}
