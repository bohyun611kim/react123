/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.mypage.mpg003.dao.SecuritySetupDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.CountryInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.SecuritySetStatusVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.dao.impl.DepositWithdrawalsDAOImpl;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.dao.impl 
 *    |_ SecuritySetupDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5.  13. 오후 1:12:13
 * @version : 
 * @author : yeonseoo
 */
@Repository("securitySetupDAO")
public class SecuritySetupDAOImpl extends AbstractDefaultMapper implements SecuritySetupDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsDAOImpl.class);
	
	@Override
	public String getNamespace() {
		return SecuritySetupDAO.NAMESPACE;
	}

	@Override
	public SecuritySetStatusVO selectSecuritySetStatus(ExchangeIDUserIDPairVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectSecuritySetStatus", param);
	}
	
	@Override
	public List<CountryInfoVO> selectCountryInfo() {
		return getSqlSession().selectList(this.getNamespace() + ".selectCountryInfo");
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[securitySetupDAO] >> call_PR_WAS_INSERT_AUTH_CODE ");
		
		// OUT DATA SET
		paramMap.put("V_ENCRYPT_AUTH_CODE", "");
		paramMap.put("V_AUTH_CODE", "");
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_INSERT_AUTH_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_ENCRYPT_AUTH_CODE", paramMap.get("V_ENCRYPT_AUTH_CODE"));
		resultMap.put("V_AUTH_CODE", paramMap.get("V_AUTH_CODE"));
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[securitySetupDAO] >> call_PR_WAS_CHECK_AUTH_CODE ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_CHECK_AUTH_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_SMS_AUTH(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[securitySetupDAO] >> call_PR_WAS_SET_SMS_AUTH ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_SET_SMS_AUTH", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[securitySetupDAO] >> call_PR_WAS_SET_OTP_AUTH ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_SET_OTP_AUTH", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public int checkPWMatch(Map<String, Object> paramMap) {
		return getSqlSession().selectOne(this.getNamespace() + ".checkPWMatch", paramMap);
	}

}
