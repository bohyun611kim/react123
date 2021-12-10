/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.home.dao.HomeDAO;
import kr.co.coinis.webserver.coinis.home.service.HomeService;
import kr.co.coinis.webserver.coinis.home.vo.HomeTopItemVO;
import kr.co.coinis.webserver.coinis.home.vo.MarketsByExchangeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.home.service.impl 
 *    |_ HomeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 15. 오후 3:07:23
 * @version : 
 * @author : yeonseoo
 */
@Service("homeService")
public class HomeServiceImpl implements HomeService {
	
	@Resource(name="homeDAO")
	private HomeDAO homeDAO;

	@Override
	public List<HomeTopItemVO> retrieveTopVolumeList(String ExchangeID) {
		return homeDAO.retrieveTopVolumeList(ExchangeID);
	}

	@Override
	public List<HomeTopItemVO> retrieveTopGainList(String ExchangeID) {
		return homeDAO.retrieveTopGainList(ExchangeID);
	}

	@Override
	public List<HomeTopItemVO> retrieveNewListingList(String ExchangeID) {
		return homeDAO.retrieveNewListingList(ExchangeID);
	}

	@Override
	public List<MarketsByExchangeVO> retrieveMarketsByExchange(String ExchangeID) {
		return homeDAO.retriveMarketsByExchange(ExchangeID);
	}
}