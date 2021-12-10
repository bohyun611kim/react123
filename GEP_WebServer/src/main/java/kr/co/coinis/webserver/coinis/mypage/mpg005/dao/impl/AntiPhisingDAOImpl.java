/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg005.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.mypage.mpg005.dao.AntiPhisingDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.dao.impl.DepositWithdrawalsDAOImpl;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg005.dao.impl 
 *    |_ AntiPhisingDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 1:40:33
 * @version : 
 * @author : yeonseoo
 */
@Repository("antiPhisingDAO")
public class AntiPhisingDAOImpl extends AbstractDefaultMapper implements AntiPhisingDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsDAOImpl.class);

	@Override
	public String getNamespace() {
		return AntiPhisingDAO.NAMESPACE;
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_SET_FISH_ANTI_CODE(Map<String, Object> paramMap) throws SQLException {
		LOG.debug("[securitySetupDAO] >> call_PR_WAS_SET_FISH_ANTI_CODE ");
		
		// OUT DATA SET
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		
		this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".call_PR_WAS_SET_FISH_ANTI_CODE", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue());
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
		return resultMap;
	}

	@Override
	public String selectUserRegisterDT(ExchangeIDUserIDPairVO param) {
		return this.getSqlSession().selectOne(this.getNamespace() + ".selectUserRegisterDT", param);
	}
	
}
