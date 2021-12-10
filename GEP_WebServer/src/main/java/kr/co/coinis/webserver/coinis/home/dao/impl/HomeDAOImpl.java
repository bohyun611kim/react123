/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.home.dao.HomeDAO;
import kr.co.coinis.webserver.coinis.home.vo.HomeTopItemVO;
import kr.co.coinis.webserver.coinis.home.vo.MarketsByExchangeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.home.dao.impl 
 *    |_ HomeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 15. 오전 9:40:59
 * @version : 
 * @author : yeonseoo
 */
@Repository("homeDAO")
public class HomeDAOImpl extends AbstractDefaultMapper implements HomeDAO {

	@Override
	public String getNamespace() {
		return HomeDAO.NAMESPACE;
	}

	@Override
	public List<HomeTopItemVO> retrieveTopVolumeList(String ExchangeID) {
		 return getSqlSession().selectList(this.getNamespace() + ".retrieveTopVolumeList", ExchangeID);
	}

	@Override
	public List<HomeTopItemVO> retrieveTopGainList(String ExchangeID) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveTopGainList", ExchangeID);
	}

	@Override
	public List<HomeTopItemVO> retrieveNewListingList(String ExchangeID) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveNewListingList", ExchangeID);
	}

	@Override
	public List<MarketsByExchangeVO> retriveMarketsByExchange(String ExchangeID) {
		return getSqlSession().selectList(this.getNamespace() + ".retriveMarketsByExchange", ExchangeID);
	}
}
