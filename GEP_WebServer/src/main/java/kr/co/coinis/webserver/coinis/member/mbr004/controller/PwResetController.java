/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr004.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.member.mbr004.service.PwResetService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr004.controller 
 *    |_ PwResetController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:11:01
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class PwResetController {

	@Autowired
	private PwResetService pwResetService;
	
	@RequestMapping(value = "coinis/pwReset")
	public ModelAndView movePwReset(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr004/pwReset");
		
		return model;
	}
}
