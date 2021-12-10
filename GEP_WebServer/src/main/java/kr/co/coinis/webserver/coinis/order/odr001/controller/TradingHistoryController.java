/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr001.controller;

import java.util.ArrayList;
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

import kr.co.coinis.webserver.coinis.member.mbr001.controller.RegisterController;
import kr.co.coinis.webserver.coinis.order.odr001.service.TradingHistoryService;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ContractedListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ItemListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.MarketListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ReqContractedListVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.order.ord001.controller 
 *    |_ TradingHistoryController.java
 * 
 * </pre>
 * 
 * @date : 2019. 3. 21. 오후 1:23:25
 * @version :
 * @author : yeonseoo
 */
@Controller
public class TradingHistoryController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private TradingHistoryService tradingHistoryService;

	@RequestMapping(value = "coinis/tradingHistory")
	public ModelAndView getMarketList(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		try {
			List<MarketListVO> mktIDList = tradingHistoryService.retrieveMarketList(CommonUtils.getExchangeId(request));

			model.setViewName(CommonUtils.getPageKey(request) + "/order/odr001/tradingHistory");
			model.addObject("marketList", mktIDList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/coinis/tradingHistory/getItemList", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemListVO> getItemList(HttpServletRequest request, @RequestParam("market") String market) {

		List<ItemListVO> itemList = new ArrayList<ItemListVO>();
		
		try {
			String mkt_id = CommonUtils.getExchangeId(request) + "_" + market;
			itemList = tradingHistoryService.retrieveItemList(mkt_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemList;
	}

	@RequestMapping(value = "/coinis/tradingHistory/doSearch")
	@ResponseBody
	public Map<String, Object> getContractedList(HttpServletRequest request
			, HttpSession session
			, @RequestParam Map<String, Object> paramMap) {

		ReqContractedListVO vo = new ReqContractedListVO();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CustomUserDetails userInfo = (CustomUserDetails) session.getAttribute("userInfo");

		try {
			int startIndex = (Integer.parseInt((String) paramMap.get("startIndex")) - 1) * 15;
			
			vo.setUser_id(userInfo.getUserId());
			vo.setExchange_id(CommonUtils.getExchangeId(request));
			vo.setitem_nm((String) paramMap.get("item"));
			vo.setMarket((String) paramMap.get("market"));
			vo.setMkt_id(CommonUtils.getExchangeId(request) + ("_") + (String) paramMap.get("market"));
			vo.setSell_buy_recog_cd_buy((String) paramMap.get("buy"));
			vo.setSell_buy_recog_cd_sell((String) paramMap.get("sell"));
			vo.setDay_s((String) paramMap.get("startDate"));
			vo.setDay_e((String) paramMap.get("endDate"));
			vo.setStartIndex(startIndex);
			vo.setQueryDataNum(Integer.parseInt((String) paramMap.get("queryDataNum"))); 
			
			//contractedList = tradingHistoryService.retrieveContractedList(vo);
			resultMap = tradingHistoryService.retrieveContractedList(vo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// resultMap.put("size", value)
		return resultMap;
	}

}
