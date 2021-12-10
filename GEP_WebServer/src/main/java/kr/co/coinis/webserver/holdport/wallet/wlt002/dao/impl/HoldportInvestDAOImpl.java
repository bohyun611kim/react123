/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.wallet.wlt002.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.wallet.wlt002.dao.HoldportInvestDAO;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.wallet.wlt002.dao.impl
 *    |_ HoldportInvestDAOImpl.java
 *
 * </pre>
 * @date : 2019. 5. 2. 오후 3:00:25
 * @version :
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Repository("holdportInvestDao")
public class HoldportInvestDAOImpl extends AbstractDefaultMapper implements HoldportInvestDAO {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportInvestDAOImpl.class);

	@Override
	public String getNamespace() {
		return HoldportInvestDAO.NAMESPACE;
	}

	@Override
	public List<Map<String, Object>> retrievePossessionInfo(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> retrievePossessionInfo ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrievePossessionInfo", paramMap);
	}

	@Override
	public Map<String, Object> retrieveUserEstimatedPossessionInfo(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> retrieveUserEstimatedPossessionInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".retrieveUserEstimatedPossessionInfo", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserPossessionCoinList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserPossessionCoinList ");

		if(Integer.valueOf(CommonUtils.strNlv(paramMap.get("HIDE_SMALL_ASSET"), "0")) == 0) {
			return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserPossessionCoinList", paramMap);
		} else {
			return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserPossessionCoinListOfNonSmallAsset", paramMap);
		}
	}
	@Override
	public Map<String, Object> selectUserPossessionCoinListCount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserPossessionCoinListCount ");

		if(Integer.valueOf(CommonUtils.strNlv(paramMap.get("HIDE_SMALL_ASSET"), "0")) == 0) {
			return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserPossessionCoinListCount", paramMap);
		} else {
			return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserPossessionCoinListCountOfNonSmallAsset", paramMap);
		}
	}

	@Override
	public List<Map<String, Object>> selectUserTradingHistoryList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserTradingHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserTradingHistoryList", paramMap);
	}

	@Override
	public Map<String, Object> selectUserTradingHistoryListCount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserTradingHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserTradingHistoryListCount", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUsrNonContractList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserTradingHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUsrNonContractList", paramMap);
	}

	@Override
	public Map<String, Object> selectUsrNonContractListCount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectUserTradingHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUsrNonContractListCount", paramMap);
	}

	@Override
	public SendOrderCancelVO selectNonContractInfo(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectNonContractInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectOrderCancelInfo", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectCoinBalanceChangeHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinBalanceChangeHistoryList", paramMap);
	}

	@Override
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportInvestDAO] >> selectCoinBalanceChangeHistoryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCoinBalanceChangeHistoryListCount", paramMap);
	}

}
