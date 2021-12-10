/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr001.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.order.odr001.vo.ContractedListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ItemListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.MarketListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ReqContractedListVO;


/**
 * <pre>
 * kr.co.coinis.webserver.order.ord001.dao 
 *    |_ TradingHistoryDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:21:58
 * @version : 
 * @author : Seongcheol
 */
public interface TradingHistoryDAO {

	public static final String NAMESPACE = TradingHistoryDAO.class.getName();
	
	/**
	 * 마켓 리스트 조회
	 * <pre>
	 * 1. 개요 : 거래소의 마켓 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryCode
	 * @date : 2019. 4. 4.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  4.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<MarketListVO> retrieveMarketList(String ExchangeID);
	
	/**
	 * 종목 리스트 조회
	 * <pre>
	 * 1. 개요 : 선택한 마켓의 종목 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryCode
	 * @date : 2019. 4. 4.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  4.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<ItemListVO> retrieveItemList(String MarketID);
	
	/**
	 * 거래 내역 조회
	 * <pre>
	 * 1. 개요 : 선택한 마켓의 종목 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryCode
	 * @date : 2019. 4. 4.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  4.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<ContractedListVO> retrieveContractedList(ReqContractedListVO reqContractedListVO);
	
	public int retrieveContractedListCount(ReqContractedListVO reqContractedListVO);
}
