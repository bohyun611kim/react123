/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt001.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.dao.YahobitWalletDAO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CodeInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinBalanceVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.DepositWithdrawMgrVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberBankAccntInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt001.dao 
 *    |_ YahobitWalletDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 5:52:17
 * @version : 
 * @author : kangn
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Repository("yahobitWalletDAO")
public class YahobitWalletDAOImpl extends AbstractDefaultMapper implements YahobitWalletDAO {

	private static final Logger LOG = LoggerFactory.getLogger(YahobitWalletDAOImpl.class);
	
	@Override
	public String getNamespace() {
		return YahobitWalletDAO.NAMESPACE;
	}

	@Override
	public List<CodeInfoVO> selectCodeInfoByCodeId(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCodeInfoByCodeId ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCodeInfoByCodeId", paramMap);
	}

	@Override
	public Map<String, Object> selectExchangeBankAccountInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectExchangeBankAccountInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectExchangeBankAccountInfo", paramMap);
	}
	
	@Override
	public Map<String, Object> selectMemberBankAccntInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectMemberBankAccntInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberBankAccountInfo", paramMap);
	}
	@Override
	public Map<String, Object> insertMemberBankAccntInfo(MemberBankAccntInfoVO memberBankAccntInfoVo) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectMemberBankAccntInfo ");
		
		// 회원의 REG_DT 를 가져온다.
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", memberBankAccntInfoVo.getEXCHANGE_ID());
		paramMap.put("USER_ID", memberBankAccntInfoVo.getUSER_ID());
		MemberInfoVO userInfoVo = this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberInfo", paramMap);
		
		paramMap.clear();
		paramMap.put("ARG_EXCHANGE_ID", memberBankAccntInfoVo.getEXCHANGE_ID());
		paramMap.put("ARG_USER_ID", memberBankAccntInfoVo.getUSER_ID());
		paramMap.put("ARG_BANK_CD", memberBankAccntInfoVo.getBANK_CD());
		paramMap.put("ARG_BANK_ACCNT_NO", memberBankAccntInfoVo.getBANK_ACCNT_NO());
		paramMap.put("ARG_ACCNT_HOLDER_NM", memberBankAccntInfoVo.getACCNT_HOLDER_NM());
		paramMap.put("ARG_KEY1", memberBankAccntInfoVo.getUSER_ID());
		paramMap.put("ARG_KEY2", userInfoVo.getREG_DT());
		Map<String, Object> resMap = this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_BANK_ACCNT_INFO", paramMap);
		return paramMap;
	}

	@Override
	public List<Map<String, Object>> selectCoinInfoByExchangeId(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinInfoByExchangeId ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinInfoByExchangeId", paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectCoinInfoByTradeStatCd(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinInfoByTradeStatCd ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinInfoByTradeStatCd", paramMap);
	}

	@Override
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinBalanceByUserId ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinBalanceByUserId", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinMgtRefInfoList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinMgtRefInfoList", paramMap);
	}

	@Override
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinMgtRefInfoByCoinNo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCoinMgtRefInfoByCoinNo", coinNo);
	}

	@Override
	public Map<String, Object> selectCoinDepositWalletInfoByUserIdAndCoinNo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectCoinDepositWalletInfoByUserIdAndCoinNo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCoinDepositWalletInfoByUserIdAndCoinNo", paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_CHECK_WITHDRAW ");
		paramMap.put("V_WTDRW_POSS_QTY", 0);
		paramMap.put("V_DAILIY_LIMIT_MAX_QTY", 0);
		paramMap.put("V_DAILIY_LIMIT_LEFT_QTY", 0);
		paramMap.put("V_MONTHLY_LIMIT_LEFT_QTY", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_CHECK_WITHDRAW", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_WTDRW_POSS_QTY", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_WTDRW_POSS_QTY"), "0")).doubleValue());
		resultMap.put("V_DAILIY_LIMIT_MAX_QTY", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_DAILIY_LIMIT_MAX_QTY"), "0")).doubleValue());
		resultMap.put("V_DAILIY_LIMIT_LEFT_QTY", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_DAILIY_LIMIT_LEFT_QTY"), "0")).doubleValue());
		resultMap.put("V_MONTHLY_LIMIT_LEFT_QTY", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_MONTHLY_LIMIT_LEFT_QTY"), "0")).doubleValue());
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).doubleValue());
		resultMap.put("V_RTN_MSG", CommonUtils.strNlv(paramMap.get("V_RTN_CD"), ""));
		return resultMap;
	}

	@Override
	public Map<String, Object> checkOnceWthrwQty(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> checkOnceWthrwQty ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".checkOnceWthrwQty", paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectDepositWithdrawList(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectDepositWithdrawList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectDepositWithdrawList", paramMap);
	}

	@Override
	public Map<String, Object> selectDepositWithdrawListCount(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectDepositWithdrawListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectDepositWithdrawListCount", paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_INSERT_DW_TRANSACTION_HIST ");
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
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_INSERT_WITHDRAW_REQUEST ");
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
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_INSERT_AUTH_CODE ");
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
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_CHECK_AUTH_CODE ");
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_CHECK_AUTH_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD ");
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
	public MemberInfoVO selectMemberInfo(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectMemberInfo ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberInfo", paramMap);
	}

	@Override
	public int deleteMemberAuthMgrByTxKeyBal(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> deleteMemberAuthMgrByTxKeyBal ");
		return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteMemberAuthMgrByTxKeyBal", paramMap);
	}

	@Override
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> selectMemberAuthMgrTxKeyBal ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectMemberAuthMgrTxKeyBal", paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WLL_INSERT_DEPOSIT_REQUEST(Map paramMap) throws SQLException {
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WLL_INSERT_DEPOSIT_REQUEST", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "0")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public int insertDepositWithdrawManager(DepositWithdrawMgrVO dwMgrVo) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> insertDepositWithdrawManager ");
		return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insertDepositWithdrawManager", dwMgrVo);
	}

	@Override
	public Map<String, Object> getFirstDepositDateTime(Map paramMap) throws SQLException {
		LOG.debug("[YahobitWalletDAO] >> getFirstDepositDateTime ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".getFirstDepositDateTime", paramMap);
	}

}
