/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.yahobit.anal.service.YahobitAnalService;
import kr.co.coinis.webserver.yahobit.anal.vo.CoinMarketCapVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopAmtVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopDaebiVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TopDaebiRankVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.controller 
 *    |_ YahobitAnalController.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 9:13:13
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class YahobitAnalController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitAnalController.class);

	@Autowired
	YahobitAnalService yahobitAnalService;
	
	@RequestMapping(value = "/alibit/anal", method=RequestMethod.GET)
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getPageKey(request) + "/anal/anal");
		
		return model;
	}
	
	@RequestMapping(value = "/alibit/anal/getTodayTopDaebi", method=RequestMethod.POST)
	@ResponseBody
	public List<TodayTopDaebiVO> getTodayTopDaebi(HttpServletRequest request, HttpSession session) {
		
		List<TodayTopDaebiVO> result = null;
		
		try {
			String exchangeId = CommonUtils.getExchangeId(request);
			
			result = yahobitAnalService.selectTodayTopDaebi(exchangeId);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/anal/getTodayTopAmt", method=RequestMethod.POST)
	@ResponseBody
	public List<TodayTopAmtVO> getTodayTopAmt(HttpServletRequest request, HttpSession session) {
		
		List<TodayTopAmtVO> result = null;
		
		try {
			String exchangeId = CommonUtils.getExchangeId(request);
			
			result = yahobitAnalService.selectTodayTopAmt(exchangeId);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/anal/getTopDaebiRank", method=RequestMethod.POST)
	@ResponseBody
	public List<TopDaebiRankVO> getTopDaebiRank(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "fromDt", defaultValue = "", required = true) String fromDt) {
		
		List<TopDaebiRankVO> result = null;
		
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("fromDt", fromDt);
			
			result = yahobitAnalService.selectTopDaebiRank(param);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/alibit/anal/getCoinMarketCap", method=RequestMethod.POST)
	@ResponseBody
	public List<CoinMarketCapVO> getCoinMarketCap(HttpServletRequest request, HttpSession session) {
		
		List<CoinMarketCapVO> result = null;
		
		try {
			result = yahobitAnalService.selectCoinMarketCap();
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/anal/getCoinNews", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCoinNews(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "page", defaultValue = "", required = true) int page) {
		
		Map<String, Object> result = null;
		
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("page", page);
			
			result = yahobitAnalService.selectCoinNews(param);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
}
