/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt002.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.wallet.wlt002.dao.YahobitInvestDAO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt002.dao.impl 
 *    |_ YahobitInvestDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 3:00:25
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Repository("yahobitInvestDao")
public class YahobitInvestDAOImpl extends AbstractDefaultMapper implements YahobitInvestDAO {

	private static final Logger LOG = LoggerFactory.getLogger(YahobitInvestDAOImpl.class);
	
	@Override
	public String getNamespace() {
		return YahobitInvestDAO.NAMESPACE;
	}

	@Override
	public List<Map<String, Object>> retrievePossessionInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> retrievePossessionInfo ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrievePossessionInfo", paramMap);
	}

	@Override
	public Map<String, Object> retrieveUserEstimatedPossessionInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> retrieveUserEstimatedPossessionInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".retrieveUserEstimatedPossessionInfo", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserPossessionCoinList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserPossessionCoinList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserPossessionCoinList", paramMap);
	}
	@Override
	public Map<String, Object> selectUserPossessionCoinListCount(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserPossessionCoinListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserPossessionCoinListCount", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserTradingHistoryList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserTradingHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserTradingHistoryList", paramMap);
	}

	@Override
	public Map<String, Object> selectUserTradingHistoryListCount(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserTradingHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserTradingHistoryListCount", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUsrNonContractList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserTradingHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUsrNonContractList", paramMap);
	}

	@Override
	public Map<String, Object> selectUsrNonContractListCount(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectUserTradingHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUsrNonContractListCount", paramMap);
	}

	@Override
	public SendOrderCancelVO selectNonContractInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectNonContractInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectOrderCancelInfo", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectCoinBalanceChangeHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinBalanceChangeHistoryList", paramMap);
	}

	@Override
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(Map paramMap) throws SQLException {
		LOG.debug("[YahobitInvestDAO] >> selectCoinBalanceChangeHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCoinBalanceChangeHistoryListCount", paramMap);
	}

}
