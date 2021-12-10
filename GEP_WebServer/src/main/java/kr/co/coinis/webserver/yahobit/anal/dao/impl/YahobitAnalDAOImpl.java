/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.anal.dao.YahobitAnalDAO;
import kr.co.coinis.webserver.yahobit.anal.vo.CoinMarketCapVO;
import kr.co.coinis.webserver.yahobit.anal.vo.CoinNewsVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopAmtVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopDaebiVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TopDaebiRankVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.dao.impl 
 *    |_ YahobitAnalDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 9:13:28
 * @version : 
 * @author : Seongcheol
 */
@Repository("yahobitAnalDAO")
public class YahobitAnalDAOImpl extends AbstractDefaultMapper implements YahobitAnalDAO {

	@Override
	public String getNamespace() {
		return YahobitAnalDAO.NAMESPACE;
	}
	
	@Override
	public List<TodayTopDaebiVO> selectTodayTopDaebi(String exchangeId) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectTodayTopDaebi", exchangeId);
	}

	@Override
	public List<TodayTopAmtVO> selectTodayTopAmt(String exchangeId) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectTodayTopAmt", exchangeId);
	}
	
	@Override
	public List<TopDaebiRankVO> selectTopDaebiRank(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectTopDaebiRank", param);
	}
	
	@Override
	public List<CoinMarketCapVO> selectCoinMarketCap() throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectCoinMarketCap");
	}
	
	@Override
	public int selectCoinNewsSize(Map<String, Object> param) throws Exception {
		return getSqlSession().selectOne(this.getNamespace() + ".selectCoinNewsSize", param);
	}

	@Override
	public List<CoinNewsVO> selectCoinNewsList(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectCoinNewsList", param);
	}
}
