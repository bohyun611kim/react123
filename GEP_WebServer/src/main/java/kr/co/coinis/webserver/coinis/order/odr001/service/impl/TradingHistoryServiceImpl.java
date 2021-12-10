/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr001.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.order.odr001.dao.TradingHistoryDAO;
import kr.co.coinis.webserver.coinis.order.odr001.service.TradingHistoryService;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ItemListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.MarketListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ReqContractedListVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.ord001.service.impl 
 *    |_ TradingHistoryServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:22:37
 * @version : 
 * @author : yeonseoo
 */
@Service("tradingHistoryService")
public class TradingHistoryServiceImpl implements TradingHistoryService {

	@Resource(name="tradingHistoryDAO")
	private TradingHistoryDAO tradingHistoryDAO;

	@Override
	public List<MarketListVO> retrieveMarketList(String ExchangeID) {
		return tradingHistoryDAO.retrieveMarketList(ExchangeID);
	}

	@Override
	public List<ItemListVO> retrieveItemList(String MarketID) {
		return tradingHistoryDAO.retrieveItemList(MarketID);
	}

	/*
	 * @Override public List<ContractedListVO>
	 * retrieveContractedList(ReqContractedListVO reqContractedListVO) { return
	 * tradingHistoryDAO.retrieveContractedList(reqContractedListVO); }
	 */
	
	@Override
	public Map<String, Object> retrieveContractedList(ReqContractedListVO reqContractedListVO) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("size", tradingHistoryDAO.retrieveContractedListCount(reqContractedListVO));
		result.put("data", tradingHistoryDAO.retrieveContractedList(reqContractedListVO));
		
		return result;	
	}


}
