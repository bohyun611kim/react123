/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt002.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.wallet.wlt002.dao.TransactionDAO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.wallet.wlt002.controller.YahobitInvestController;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt002.dao.impl 
 *    |_ TransactionDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:11:15
 * @version : 
 * @author : Seongcheol
 */
@Repository("transactionDAO")
public class TransactionDAOImpl extends AbstractDefaultMapper implements TransactionDAO {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionDAO.class);

	@Override
	public String getNamespace() {
		return TransactionDAO.NAMESPACE;
	}

	@Override
	public List<Map<String, Object>> selectDepositWithdrawList(Map paramMap) throws SQLException {
		LOG.debug("[TransactionDAO] >> selectDepositWithdrawList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectDepositWithdrawList", paramMap);
	}

	@Override
	public Map<String, Object> selectDepositWithdrawListCount(Map paramMap) throws SQLException {
		LOG.debug("[TransactionDAO] >> selectDepositWithdrawListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectDepositWithdrawListCount", paramMap);
	}
}
