/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.wallet.wlt002.service.impl;

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
import kr.co.coinis.webserver.holdport.wallet.wlt002.dao.HoldportInvestDAO;
import kr.co.coinis.webserver.holdport.wallet.wlt002.service.HoldportInvestService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.wallet.wlt002.service.impl
 *    |_ HoldportInvestServiceImpl.java
 *
 * </pre>
 * @date : 2019. 5. 2. 오후 2:56:35
 * @version :
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("holdportInvestService")
public class HoldportInvestServiceImpl implements HoldportInvestService {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportInvestServiceImpl.class);

	@Resource(name="holdportInvestDao")
	private HoldportInvestDAO holdportInvestDao;

	@Resource(name="commDAO")
	private CommDAO commonDao;

	@Override
	public List<Map<String, Object>> retrievePossessionInfo(Map paramMap) throws SQLException {
		return holdportInvestDao.retrievePossessionInfo(paramMap);
	}

	@Override
	public Map<String, Object> retrieveUserEstimatedPossessionInfo(Map paramMap) throws SQLException {
		return holdportInvestDao.retrieveUserEstimatedPossessionInfo(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserPossessionCoinList(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUserPossessionCoinList(paramMap);
	}
	@Override
	public Map<String, Object> selectUserPossessionCoinListCount(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUserPossessionCoinListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserTradingHistoryList(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUserTradingHistoryList(paramMap);
	}

	@Override
	public Map<String, Object> selectUserTradingHistoryListCount(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUserTradingHistoryListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUsrNonContractList(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUsrNonContractList(paramMap);
	}

	@Override
	public Map<String, Object> selectUsrNonContractListCount(Map paramMap) throws SQLException {
		return holdportInvestDao.selectUsrNonContractListCount(paramMap);
	}

	@Override
	public SendOrderCancelVO selectNonContractInfo(Map paramMap) throws SQLException {
		return holdportInvestDao.selectNonContractInfo(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(Map paramMap) throws SQLException {
		return holdportInvestDao.selectCoinBalanceChangeHistoryList(paramMap);
	}

	@Override
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(Map paramMap) throws SQLException {
		return holdportInvestDao.selectCoinBalanceChangeHistoryListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCodeInfo(Map paramMap) throws SQLException {
		return commonDao.selectCodeInfo(paramMap);
	}

}
