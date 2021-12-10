/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.yahobit.home.service.YahobitHomeService;

/**
 * <pre>
 * kr.co.coinis.webserver.home.controller 
 *    |_ HomeController.java
 * 
 * </pre>
 * @date : 2019. 4. 15. 오전 9:38:41
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class YahobitHomeController {

	@Autowired
	private YahobitHomeService yahobitHomeService;
	
	@RequestMapping(value = "/alibit/home")
	public ModelAndView moveHome(HttpServletRequest request) {
		
		String exchangeId = CommonUtils.getExchangeId(request);
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getPageKey(request) + "/home/home");
		//model.addObject("event", yahobitHomeService.selectBannerList(exchangeId));
		model.addObject("notice", yahobitHomeService.selectNoticeList(exchangeId));
		model.addObject("event", yahobitHomeService.selectBanner(exchangeId));
		model.addObject("topinfluencer", yahobitHomeService.selectInfluencerTop10List());
		
		return model;
	}
	
}
