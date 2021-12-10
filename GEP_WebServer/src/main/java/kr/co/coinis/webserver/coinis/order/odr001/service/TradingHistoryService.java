/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr001.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.order.odr001.vo.ContractedListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ItemListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.MarketListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ReqContractedListVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.ord001.service 
 *    |_ TradingHistoryService.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:21:49
 * @version : 
 * @author : yeonseoo
 */
public interface TradingHistoryService {
	/*마켃 리스트 조회*/
	public List<MarketListVO> retrieveMarketList(String ExchangeID);
	
	/*종목 리스트 조회*/
	public List<ItemListVO> retrieveItemList(String MarketID);

	/*거래내역 조회*/
	public Map<String, Object> retrieveContractedList(ReqContractedListVO reqContractedListVO);

}