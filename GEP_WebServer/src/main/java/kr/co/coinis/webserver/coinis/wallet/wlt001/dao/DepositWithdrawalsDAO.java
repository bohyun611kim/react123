/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinPossessionVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.DepositWithdrawInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.ReqDepositWithdrawInfoVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinBalanceVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt001.dao 
 *    |_ DepositWithdrawalsDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오전 10:46:07
 * @version : 
 * @author : yeonseoo
 */
public interface DepositWithdrawalsDAO {

	public static final String NAMESPACE = DepositWithdrawalsDAO.class.getName();
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소별 거래 코인 리스트를 얻어온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectExchangeCoinList
	 * @date : 2019. 6. 15.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public List<Map<String, Object>> selectExchangeCoinList(String exchangeId) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_BALANCE 테이블에서 사용자의 코인 보유내역을 가져온다. <전체 보유수량 정보>
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinBalanceByUserId
	 * @date : 2019. 4. 27.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 27.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_BALANCE 테이블에서 사용자의 코인 보유내역을 가져온다. <사용가능 보유수량 정보>
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserAvailablePossessionInfo
	 * @date : 2019. 4. 27.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 27.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectUserAvailablePossessionInfo(Map paramMap) throws SQLException;

	/**
	 * 코인종목/보유수량 조회
	 * <pre>
	 * 1. 개요 : 거래소의 전체 코인과 코인별 회원의 보유 수량 조회
	 * 2. 처리내용 : 보유하지 않은 종목의 수량은 0
	 * </pre>
	 * @Method Name : retrieveCoinPossession
	 * @date : 2019. 4. 30.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  30.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<CoinPossessionVO> retrieveCoinPossession(ExchangeIDUserIDPairVO param) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_MGT_REF_INFO 테이블에서 코인관리 기준정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinMgtRefInfoByCoinNo
	 * @date : 2019. 4. 27.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 27.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param coinNo
	 * @return
	 * @throws Exception
	 */
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_MGT_REF_INFO 테이블에서 코인관리 기준정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinMgtRefInfoList
	 * @date : 2019. 4. 27.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 27.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws SQLException;

	/**
	 * 입금/출금 정보 조회
	 * <pre>
	 * 1. 개요 : 선택 코인의 입/출금화면 표시여부와 출금에 필요한 세부정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveCoinPossession
	 * @date : 2019. 5. 2.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  2.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public DepositWithdrawInfoVO retrieveDepositWithdrawInfo(ReqDepositWithdrawInfoVO param);

	/**
	 * 출금가능수량 초과여부 체크
	 * <pre>
	 * 1. 개요 : 출금 요청 유효성 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkWithdraw
	 * @date : 2019. 5. 7.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  7.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map<String, Object> paramMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 기본정보를 얻어온다. 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberInfo
	 * @date : 2019. 6. 17.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 17.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectMemberInfo(Map<String, Object> paramMap) throws SQLException;
	
	public String getMemberMobile(Map<String, Object> paramMap) throws SQLException;
	public String getMemberMeansCD(Map<String, Object> paramMap) throws SQLException;
	public String getMemberOTPCD(Map<String, Object> paramMap) throws SQLException;
	public int checkMemberExist(ExchangeIDUserIDPairVO param);
	
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map<String, Object>  paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object>  paramMap) throws SQLException;
	
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map<String, Object> paramMap) throws SQLException;
	
	
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object>  paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map<String, Object>  paramMap) throws SQLException;

}
