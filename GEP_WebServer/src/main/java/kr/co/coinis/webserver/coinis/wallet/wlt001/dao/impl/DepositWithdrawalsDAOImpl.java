/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.wallet.wlt001.dao.DepositWithdrawalsDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinPossessionVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.DepositWithdrawInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.ReqDepositWithdrawInfoVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinBalanceVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt001.dao.impl 
 *    |_ DepositWithdrawalsDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 5:16:21
 * @version : 
 * @author : yeonseoo
 */
@Repository("depositWithdrawalsDAO")
public class DepositWithdrawalsDAOImpl extends AbstractDefaultMapper implements DepositWithdrawalsDAO {

	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsDAOImpl.class);
	
	@Override
	public String getNamespace() {
		return DepositWithdrawalsDAO.NAMESPACE;
	}

	@Override
	public List<CoinPossessionVO> retrieveCoinPossession(ExchangeIDUserIDPairVO param) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveCoinPossession", param);
	}

	@Override
	public DepositWithdrawInfoVO retrieveDepositWithdrawInfo(ReqDepositWithdrawInfoVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveDepositWithdrawInfo", param);
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map<String, Object> paramMap) {
		paramMap.put("V_WTDRW_POSS_QTY", 0);
		paramMap.put("V_DAILIY_LIMIT_MAX_QTY", 0);
		paramMap.put("V_DAILIY_LIMIT_LEFT_QTY", 0);
		paramMap.put("V_MONTHLY_LIMIT_LEFT_QTY", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		getSqlSession().selectOne(this.getNamespace() + ".call_PR_WAS_CHECK_WITHDRAW", paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("V_WTDRW_POSS_QTY", paramMap.get("V_WTDRW_POSS_QTY"));
		resultMap.put("V_DAILIY_LIMIT_MAX_QTY", paramMap.get("V_DAILIY_LIMIT_MAX_QTY"));
		resultMap.put("V_DAILIY_LIMIT_LEFT_QTY", paramMap.get("V_DAILIY_LIMIT_LEFT_QTY"));
		resultMap.put("V_MONTHLY_LIMIT_LEFT_QTY", paramMap.get("V_MONTHLY_LIMIT_LEFT_QTY"));
		resultMap.put("V_RTN_CD", paramMap.get("V_RTN_CD"));
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public String getMemberMobile(Map<String, Object> paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".getMemberMobile", paramMap);
	}
	
	@Override
	public String getMemberMeansCD(Map<String, Object> paramMap) throws SQLException {
		return this.getSqlSession().selectOne(this.getNamespace() + ".getMemberMeansCD", paramMap);
	}

	@Override
	public String getMemberOTPCD(Map<String, Object> paramMap) throws SQLException {
		return this.getSqlSession().selectOne(this.getNamespace() + ".getMemberOTPCD", paramMap);
	}
	
	@Override
	public int checkMemberExist(ExchangeIDUserIDPairVO param) {
		return this.getSqlSession().selectOne(this.getNamespace() + ".checkMemberExist", param);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map<String, Object> paramMap) {
		LOG.debug("[DepositWithdrawalsDAO] >> call_PR_WAS_INSERT_DW_TRANSACTION_HIST ");
		paramMap.put("V_TRANSACTION_IDX", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_DW_TRANSACTION_HIST", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_TRANSACTION_IDX", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_TRANSACTION_IDX"), "0")).doubleValue());
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map<String, Object>  paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> call_PR_WAS_INSERT_WITHDRAW_REQUEST ");
		paramMap.put("V_REQ_SEQ_NO", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_WITHDRAW_REQUEST", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_REQ_SEQ_NO", Long.valueOf(CommonUtils.strNlv(paramMap.get("V_REQ_SEQ_NO"), "0")).longValue());
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object>  paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> call_PR_WAS_INSERT_AUTH_CODE ");
		paramMap.put("V_ENCRYPT_AUTH_CODE", "");
		paramMap.put("V_AUTH_CODE", "");
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_AUTH_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_ENCRYPT_AUTH_CODE", paramMap.get("V_ENCRYPT_AUTH_CODE"));
		resultMap.put("V_AUTH_CODE", paramMap.get("V_AUTH_CODE"));
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectMemberAuthMgrTxKeyBal ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberAuthMgrTxKeyBal", paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object>  paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> call_PR_WAS_CHECK_AUTH_CODE ");
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_CHECK_AUTH_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD ");
		paramMap.put("V_AUTO_DEPOSIT_YN", 0);
		paramMap.put("V_AUTO_WTDRW_YN", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_SET_DW_REQ_STAT_PROC_CD", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_AUTO_DEPOSIT_YN", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_AUTO_DEPOSIT_YN"), "0")).intValue());
		resultMap.put("V_AUTO_WTDRW_YN", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_AUTO_WTDRW_YN"), "0")).intValue());
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> selectExchangeCoinList(String exchangeId) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectExchangeCoinList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectExchangeCoinList", exchangeId);
	}

	@Override
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectCoinBalanceByUserId ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinBalanceByUserId", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectUserAvailablePossessionInfo(Map paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectUserAvailablePossessionInfo ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrievePossessionInfo", paramMap);
	}

	@Override
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectCoinMgtRefInfoByCoinNo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCoinMgtRefInfoByCoinNo", coinNo);
	}

	@Override
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectCoinMgtRefInfoList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinMgtRefInfoList", paramMap);
	}

	@Override
	public Map<String, Object> selectMemberInfo(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[DepositWithdrawalsDAO] >> selectMemberInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberInfo", paramMap);
	}


}
