/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr001.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.order.odr001.dao.TradingHistoryDAO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ContractedListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ItemListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.MarketListVO;
import kr.co.coinis.webserver.coinis.order.odr001.vo.ReqContractedListVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.order.ord001.dao.impl 
 *    |_ TradingHistoryDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:22:11
 * @version : 
 * @author : yeonseoo
 */
@Repository("tradingHistoryDAO")
public class TradingHistoryDAOImpl extends AbstractDefaultMapper implements TradingHistoryDAO {

	@Override
	public String getNamespace() {
		return TradingHistoryDAO.NAMESPACE;
	}

	@Override
	public List<MarketListVO> retrieveMarketList(String ExchangeID) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveMarketList", ExchangeID);
	}

	@Override
	public List<ItemListVO> retrieveItemList(String MarketID) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveItemList", MarketID);
	}

	@Override
	public List<ContractedListVO> retrieveContractedList(ReqContractedListVO reqContractedListVO) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveContractedList", reqContractedListVO);
	}
	
	@Override
	public int retrieveContractedListCount(ReqContractedListVO reqContractedListVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveContractedListCount", reqContractedListVO);
	}
}
