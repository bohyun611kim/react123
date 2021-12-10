/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt002.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.common.dao.CommDAO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt002.dao.YahobitInvestDAO;
import kr.co.coinis.webserver.yahobit.wallet.wlt002.service.YahobitInvestService;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt002.service.impl 
 *    |_ YahobitInvestServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 2:56:35
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("yahobitInvestService")
public class YahobitInvestServiceImpl implements YahobitInvestService {

	private static final Logger LOG = LoggerFactory.getLogger(YahobitInvestServiceImpl.class);

	@Resource(name="yahobitInvestDao")
	private YahobitInvestDAO yahobitInvestDao;
	
	@Resource(name="commDAO")
	private CommDAO commonDao;

	@Override
	public List<Map<String, Object>> retrievePossessionInfo(Map paramMap) throws SQLException {
		return yahobitInvestDao.retrievePossessionInfo(paramMap);
	}

	@Override
	public Map<String, Object> retrieveUserEstimatedPossessionInfo(Map paramMap) throws SQLException {
		return yahobitInvestDao.retrieveUserEstimatedPossessionInfo(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserPossessionCoinList(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUserPossessionCoinList(paramMap);
	}
	@Override
	public Map<String, Object> selectUserPossessionCoinListCount(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUserPossessionCoinListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserTradingHistoryList(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUserTradingHistoryList(paramMap);
	}

	@Override
	public Map<String, Object> selectUserTradingHistoryListCount(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUserTradingHistoryListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUsrNonContractList(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUsrNonContractList(paramMap);
	}

	@Override
	public Map<String, Object> selectUsrNonContractListCount(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectUsrNonContractListCount(paramMap);
	}

	@Override
	public SendOrderCancelVO selectNonContractInfo(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectNonContractInfo(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectCoinBalanceChangeHistoryList(paramMap);
	}

	@Override
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(Map paramMap) throws SQLException {
		return yahobitInvestDao.selectCoinBalanceChangeHistoryListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCodeInfo(Map paramMap) throws SQLException {
		return commonDao.selectCodeInfo(paramMap);
	}

}
