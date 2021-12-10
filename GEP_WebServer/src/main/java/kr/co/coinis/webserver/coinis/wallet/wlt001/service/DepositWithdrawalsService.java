/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.service;

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
 * kr.co.coinis.webserver.wallet.wlt001.service 
 *    |_ DepositWithdrawalsService.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오전 10:46:07
 * @version : 
 * @author : yeonseoo
 */
public interface DepositWithdrawalsService {

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
	public List<Map<String, Object>> selectExchangeCoinList(String exchangeId) throws Exception;
	
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
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws Exception;

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
	public List<Map<String, Object>> selectUserAvailablePossessionInfo(Map paramMap) throws Exception;
	
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
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자 출금관련된 종합정보를 얻어온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserWithdrawalInfo
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
	 * @throws Exception
	 */
	public Map<String, Object> selectUserWithdrawalInfo(Map paramMap) throws Exception;
	
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
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws Exception;
	
	/*코인종목/보유수량 조회*/
	public List<CoinPossessionVO> retrieveCoinPossession(ExchangeIDUserIDPairVO param);
	
	/*입출금 정보 조회*/
	public DepositWithdrawInfoVO retrieveDepositWithdrawInfo(ReqDepositWithdrawInfoVO param);
	
	/*출금 요청 유효성 확인*/
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map<String, Object> paramMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Coin의 지갑주소의 Validation을 체크한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkValidateionUserCoinAddress
	 * @date : 2019. 5. 15.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 15.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public boolean checkValidateionUserCoinAddress(Map<String, Object> paramMap) throws Exception;
	
	/*입금 주소 신규 발금*/
	public Map<String, Object> createNewCoinAddress(Map<String, Object> paramMap) throws SQLException;
	
	public String getMemberMobile(Map<String, Object> paramMap) throws SQLException;
	public String getMemberMeansCD(Map<String, Object> paramMap) throws SQLException;
	public String getMemberOTPCD(Map<String, Object> paramMap) throws SQLException;
	public int checkMemberExist(ExchangeIDUserIDPairVO param);
	
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_MEMBER_AUTH_MGR 테이블에서 사용자 출금신청 TRANSACTION_KEY_VAL 값을 가져온다
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberAuthMgrTxKeyBal
	 * @date : 2019. 5. 1.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 1.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map<String, Object> paramMap) throws SQLException;
	
	
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map<String, Object> paramMap) throws SQLException;
}
