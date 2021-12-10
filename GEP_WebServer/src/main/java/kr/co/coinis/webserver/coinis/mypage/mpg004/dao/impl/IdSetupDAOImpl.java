/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */

package kr.co.coinis.webserver.coinis.mypage.mpg004.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.mypage.mpg004.dao.IdSetupDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.CorpInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.ExtraUserInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.dao.impl.DepositWithdrawalsDAOImpl;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.dao.impl 
 *    |_ IdSetupDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 16. 오전 11:03:22
 * @version : 
 * @author : yeonseoo
 */
@Repository("idSetupDAO")
public class IdSetupDAOImpl extends AbstractDefaultMapper implements IdSetupDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsDAOImpl.class);

	@Override
	public String getNamespace() {
		return IdSetupDAO.NAMESPACE;
	}

	@Override
	public List<String> selectCountryList() {
		return getSqlSession().selectList(this.getNamespace() + ".selectCountryList");
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_ID_AUTH_INFO(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[idSetupDAO] >> call_PR_WAS_INSERT_ID_AUTH_INFO ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_ID_AUTH_INFO", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_UPDATE_MEMBER_INFO_EXTRA(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[idSetupDAO] >> call_PR_WAS_UPDATE_MEMBER_INFO_EXTRA ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_UPDATE_MEMBER_INFO_EXTRA", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public ExtraUserInfoVO selectExtraUserInfo(ExchangeIDUserIDPairVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectExtraUserInfo", param);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO(Map<String, Object> paramMap)
			throws SQLException {
		LOG.debug("[idSetupDAO] >> call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;		
	}

	@Override
	public CorpInfoVO selectCorpInfo(Map<String, Object> paramMap) {
		return getSqlSession().selectOne(getNamespace() + ".selectCorpInfo", paramMap);
	}

	@Override
	public int updateMemberInfoExtraIDAuthInfo(ReqIdCardVO param) throws SQLException {
		return getSqlSession().update(this.getNamespace() + ".updateMemberInfoExtraIDAuthInfo", param);
	}
}
