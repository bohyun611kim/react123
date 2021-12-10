/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt002.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt002.dao 
 *    |_ TransactionDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:11:31
 * @version : 
 * @author : Seongcheol
 */
public interface TransactionDAO {

	public static final String NAMESPACE = TransactionDAO.class.getName();
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 입출금 기록 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDepositWithdrawList
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectDepositWithdrawList(Map paramMap) throws SQLException;
	public Map<String, Object> selectDepositWithdrawListCount(Map paramMap) throws SQLException;

}
