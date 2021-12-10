/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.home.service.HomeService;
import kr.co.coinis.webserver.coinis.home.vo.HomeTopItemVO;
import kr.co.coinis.webserver.coinis.home.vo.MarketsByExchangeVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;

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
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "coinis/home")
	public ModelAndView moveHome(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/home/home");
		
		String exchange_id = CommonUtils.getExchangeId(request);

		Map<String, List<HomeTopItemVO>> top5ItemMap = new HashMap<String, List<HomeTopItemVO>>();
		
		List<HomeTopItemVO> topVolumeList = new ArrayList<HomeTopItemVO>();
		List<HomeTopItemVO> topGainList = new ArrayList<HomeTopItemVO>();
		List<HomeTopItemVO> newListingList = new ArrayList<HomeTopItemVO>();
		
		try {
			topVolumeList = homeService.retrieveTopVolumeList(exchange_id);
			topGainList = homeService.retrieveTopGainList(exchange_id);
			newListingList = homeService.retrieveNewListingList(exchange_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		top5ItemMap.put("topVolumeList", topVolumeList);
		top5ItemMap.put("topGainList", topGainList);
		top5ItemMap.put("newListingList", newListingList);

		List<MarketsByExchangeVO> marketList = new ArrayList<MarketsByExchangeVO>();
		
		try {
			marketList = homeService.retrieveMarketsByExchange(exchange_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("HomeTopItem", top5ItemMap);
		model.addObject("marketList", marketList);
		
		return model;
	}
	
}
