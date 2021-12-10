/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt002.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.wallet.wlt002.dao.TransactionDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt002.service.TransactionService;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt002.service.impl 
 *    |_ TransactionServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:11:45
 * @version : 
 * @author : Seongcheol
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Resource(name="transactionDAO")
	private TransactionDAO transactionDAO;

	@Override
	public List<Map<String, Object>> selectDepositWithdrawList(Map paramMap) throws SQLException {
		return transactionDAO.selectDepositWithdrawList(paramMap);
	}

	@Override
	public Map<String, Object> selectDepositWithdrawListCount(Map paramMap) throws SQLException {
		return transactionDAO.selectDepositWithdrawListCount(paramMap);
	} 
	
}
