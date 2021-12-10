/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.service;

import java.util.List;

import kr.co.coinis.webserver.coinis.home.vo.HomeTopItemVO;
import kr.co.coinis.webserver.coinis.home.vo.MarketsByExchangeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.home.service 
 *    |_ HomeService.java
 * 
 * </pre>
 * @date : 2019. 4. 15. 오후 3:07:41
 * @version : 
 * @author : yeonseoo
 */
public interface HomeService {
	/*거래량 최고 종목 조회*/
	public List<HomeTopItemVO> retrieveTopVolumeList(String ExchangeID);
	
	/*상승폭 최고 종목 1, 2 순위 조회*/
	public List<HomeTopItemVO> retrieveTopGainList(String ExchangeID);

	/*최근 상장 종목 1, 2 순위 조회*/
	public List<HomeTopItemVO> retrieveNewListingList(String ExchangeID);
	
	/*마켓 리스트 조회 후 마켓 별 종목 시세 정보 조회*/
	public List<MarketsByExchangeVO> retrieveMarketsByExchange(String ExchangeID);
}