/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt001.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
@SuppressWarnings("rawtypes")
public interface YahobitWalletDAO {
	
	public static final String NAMESPACE = YahobitWalletDAO.class.getName();

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_CODE_INFO 테이블에서 특정 CODE_ID에 해당하는 Code 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfoByCodeId
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfoVO> selectCodeInfoByCodeId(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 은행계좌 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectExchangeBankAccountInfo
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectExchangeBankAccountInfo(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 은행계좌 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberBankAccntInfo
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectMemberBankAccntInfo(Map paramMap) throws SQLException;
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 은행계좌 정보를 넣는다
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertMemberBankAccntInfo
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param memberBankAccntInfoVo
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> insertMemberBankAccntInfo(MemberBankAccntInfoVO memberBankAccntInfoVo) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_MKT_ITEM_CODE_INFO 테이블과 TB_ITEM_CODE_MAST 테이블에서 거래소별 상장된 코인정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinInfoByExchangeId
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
	public List<Map<String, Object>> selectCoinInfoByExchangeId(Map paramMap) throws SQLException;
	
	/**
	 * <pre>
	 * 1. 개요 : TB_MKT_ITEM_CODE_INFO 테이블과 TB_ITEM_CODE_MAST 테이블에서 거래소별 상장된 코인정보를 가져온다.
	 * 2. 처리내용 : TRADE_STAT_CD 에만 영향을 받음
	 * </pre>
	 * @Method Name : selectCoinInfoByTradeStatCd
	 * @date : 2019. 8. 19.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 19.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectCoinInfoByTradeStatCd(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_BALANCE 테이블에서 사용자의 코인 보유내역을 가져온다.
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
	 * 1. 개요 : TB_COIN_MGT_REF_INFO 테이블에서 코인관리 기준정보의 특정코인 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinMgtRefInfoByCoinNo
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param coinNo
	 * @return
	 * @throws SQLException
	 */
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws SQLException;
	
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_DEPOSIT_WALLET_INFO 테이블에서 회원의 코인별 지갑주소 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinDepositWalletInfoByUserIdAndCoinNo
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectCoinDepositWalletInfoByUserIdAndCoinNo(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자별 출금 가능수량을 PR_WAS_CHECK_WITHDRAW 프로시저를 통해 얻어온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : call_PR_WAS_CHECK_WITHDRAW
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
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 코인별 1회출금가능수량을 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkOnceWthrwQty
	 * @date : 2019. 12. 06.
	 * @author : 강호경
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 06.		강호경				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> checkOnceWthrwQty(Map paramMap) throws SQLException;
	
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map paramMap) throws SQLException;
	
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
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_MEMBER_INFO 테이블에서 사용자정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberInfo
	 * @date : 2019. 4. 30.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public MemberInfoVO selectMemberInfo(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자가 출금신청 취소를 했을때 TB_MEMBER_AUTH_MGR 테이블에서 인증번호를 삭제한다.
	 * 2. 처리내용 : TRANSACTION_KEY_VAL 를 키값으로 삭제한다.
	 * </pre>
	 * @Method Name : deleteMemberAuthMgrByTxKeyBal
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
	public int deleteMemberAuthMgrByTxKeyBal(Map paramMap) throws SQLException;
	
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
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : PR_WLL_INSERT_DEPOSIT_REQUEST 프로시저를 호출하여 TB_DEPOSIT_WITHDRAW_MGR 테이블에 데이터를 저장한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : call_PR_WLL_INSERT_DEPOSIT_REQUEST
	 * @date : 2019. 5. 6.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 6.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> call_PR_WLL_INSERT_DEPOSIT_REQUEST(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_DEPOSIT_WITHDRAW_MGR 테이블에 수동입력 (원화입금의 경우 프로시저가 없어 수동입력)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertDepositWithdrawManager
	 * @date : 2019. 5. 23.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param dwMgrVo
	 * @return
	 * @throws SQLException
	 */
	public int insertDepositWithdrawManager(DepositWithdrawMgrVO dwMgrVo) throws SQLException;
	
	public Map<String, Object> getFirstDepositDateTime(Map paramMap) throws SQLException;
}
