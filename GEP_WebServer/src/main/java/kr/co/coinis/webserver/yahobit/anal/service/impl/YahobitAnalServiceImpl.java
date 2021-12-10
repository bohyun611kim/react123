/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.yahobit.anal.dao.YahobitAnalDAO;
import kr.co.coinis.webserver.yahobit.anal.service.YahobitAnalService;
import kr.co.coinis.webserver.yahobit.anal.vo.CoinMarketCapVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopAmtVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopDaebiVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TopDaebiRankVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.service.impl 
 *    |_ YahobitAnalServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 9:13:45
 * @version : 
 * @author : Seongcheol
 */
@Service("yahobitAnalService")
public class YahobitAnalServiceImpl implements YahobitAnalService {

	@Resource(name="yahobitAnalDAO")
	YahobitAnalDAO yahobitAnalDAO;
	
	@Override
	public List<TodayTopDaebiVO> selectTodayTopDaebi(String exchangeId) throws Exception {
		return yahobitAnalDAO.selectTodayTopDaebi(exchangeId);
	}
	
	@Override
	public List<TodayTopAmtVO> selectTodayTopAmt(String exchangeId) throws Exception {
		return yahobitAnalDAO.selectTodayTopAmt(exchangeId);
	}
	
	@Override
	public List<TopDaebiRankVO> selectTopDaebiRank(Map<String, Object> param) throws Exception {
		return yahobitAnalDAO.selectTopDaebiRank(param);
	}
	
	@Override
	public List<CoinMarketCapVO> selectCoinMarketCap() throws Exception {
		return yahobitAnalDAO.selectCoinMarketCap();
	}
	
	@Override
	public Map<String, Object> selectCoinNews(Map<String, Object> param) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		result.put("size", yahobitAnalDAO.selectCoinNewsSize(param));
		result.put("list", yahobitAnalDAO.selectCoinNewsList(param));
		
		return result;
	}
}
