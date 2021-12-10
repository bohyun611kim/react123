/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.home.vo.HomeTopItemVO;
import kr.co.coinis.webserver.coinis.home.vo.MarketsByExchangeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.home.dao 
 *    |_ HomeDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 15. 오전 9:41:10
 * @version : 
 * @author : yeonseoo
 */
public interface HomeDAO {

	public static final String NAMESPACE = HomeDAO.class.getName();
	
	/**
	 * 거래량 최고 종목 조회
	 * <pre>
	 * 1. 개요 : 거래소의 거래량 최고 종목 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveTopVolumeList
	 * @date : 2019. 4. 15.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  15.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<HomeTopItemVO> retrieveTopVolumeList(String ExchangeID);

	/**
	 * 상승폭 최고 종목 1, 2 순위 조회
	 * <pre>
	 * 1. 개요 : 거래소의 상승폭 최고 종목 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveTopGainList
	 * @date : 2019. 4. 15.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  15.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<HomeTopItemVO> retrieveTopGainList(String ExchangeID);
	
	/**
	 * 최근 상장 종목 1, 2 순위 조회
	 * <pre>
	 * 1. 개요 : 거래소의 최근 상장 종목 1, 2 순위 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveNewListingList
	 * @date : 2019. 4. 15.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  15.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<HomeTopItemVO> retrieveNewListingList(String ExchangeID);
	
	/**
	 * 마켓 목록 조회
	 * <pre>
	 * 1. 개요 : 거래소의 마켓 목록 조회
	 * 2. 처리내용 : 마켓 조회 후 마켓별 종목 시세 정보 조회
	 * </pre>
	 * @Method Name : retriveMarketsByExchange
	 * @date : 2019. 4. 17.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  17.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<MarketsByExchangeVO> retriveMarketsByExchange(String ExchangeID);

}
