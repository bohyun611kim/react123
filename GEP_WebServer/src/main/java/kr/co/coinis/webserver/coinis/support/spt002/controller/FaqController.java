/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt002.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.support.spt002.service.FaqService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt002.controller 
 *    |_ FaqController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 2:40:39
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class FaqController {

	@Autowired
	private FaqService faqService;
	
	@RequestMapping(value = "coinis/faq")
	public ModelAndView moveFaq(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/support/spt002/faq");
		
		return model;
	}
}
