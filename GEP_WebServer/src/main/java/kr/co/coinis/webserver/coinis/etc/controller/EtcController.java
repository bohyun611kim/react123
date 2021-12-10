/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.etc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.etc.service.EtcService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.etc.controller 
 *    |_ EtcController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:01:47
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class EtcController {

	@Autowired
	private EtcService etcService;
	
	@RequestMapping(value = "coinis/termsConditions")
	public ModelAndView moveTermsConditions(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/etc/etc001/termsConditions");
		
		return model;
	}
	
	@RequestMapping(value = "coinis/privacyStatements")
	public ModelAndView movePrivacyStatements(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/etc/etc002/privacyStatements");
		
		return model;
	}
	
	@RequestMapping(value = "coinis/fees")
	public ModelAndView moveFees(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/etc/etc003/fees");
		
		return model;
	}
}
