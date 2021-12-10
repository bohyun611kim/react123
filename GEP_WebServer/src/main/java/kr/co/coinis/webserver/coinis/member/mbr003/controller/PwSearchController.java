/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr003.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.member.mbr003.service.PwSearchService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr003.controller 
 *    |_ PwSearchController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:34
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class PwSearchController {

	@Autowired
	private PwSearchService pwSearchService;
	
	@RequestMapping(value = "coinis/pwSearch")
	public ModelAndView movePwSearch(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr003/pwSearch");
		
		return model;
	}
}
