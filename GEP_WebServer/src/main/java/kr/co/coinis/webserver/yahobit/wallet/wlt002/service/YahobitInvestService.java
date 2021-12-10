/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt002.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt002.service 
 *    |_ YahobitInvestService.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 2:56:08
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
public interface YahobitInvestService {

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유자산 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrievePossessionInfo
	 * @date : 2019. 5. 2.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> retrievePossessionInfo(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유코인의 평가금액/평가손익을 계산하여 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveUserEstimatedPossessionInfo
	 * @date : 2019. 5. 3.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> retrieveUserEstimatedPossessionInfo(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유코인별 수량, 평가금액등을 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserPossessionCoinList
	 * @date : 2019. 5. 3.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectUserPossessionCoinList(Map paramMap) throws SQLException;
	// Paging count
	public Map<String, Object> selectUserPossessionCoinListCount(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 거래내역을 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserTradingHistoryList
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectUserTradingHistoryList(Map paramMap) throws SQLException;
	// Paging count
	public Map<String, Object> selectUserTradingHistoryListCount(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 미체결 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUsrNonContractList
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectUsrNonContractList(Map paramMap) throws SQLException;
	// Paging count
	public Map<String, Object> selectUsrNonContractListCount(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 취소주문을 위해 취소주문 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContractInfo
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public SendOrderCancelVO selectNonContractInfo(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 잔고변경 이력을 보여준다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinBalanceChangeHistoryList
	 * @date : 2019. 5. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(Map paramMap) throws SQLException;
	/* Count 정보 */
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_CODE_INFO 테이블에서 CODE_ID에 해당하는 정보 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfo
	 * @date : 2019. 5. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectCodeInfo(Map paramMap) throws SQLException;
}
