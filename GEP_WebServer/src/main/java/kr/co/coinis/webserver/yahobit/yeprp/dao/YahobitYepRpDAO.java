/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.yeprp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.yeprp.dao 
 *    |_ YahobitYepRpDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오후 3:45:41
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
public interface YahobitYepRpDAO {

	public static final String NAMESPACE = YahobitYepRpDAO.class.getName();
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 Staking 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectStakeInfo
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectStakeInfo(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : KRW, YEP 마켓별 거래량, 거래대금정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMarketTradeVolume
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> selectMarketTradeVolume(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : YEP 유통현황 데이터를 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDistributionStatus
	 * @date : 2019. 6. 20.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 20.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectDistributionStatus() throws SQLException;

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Mining Info 페이지에 보여줄 데이터 처리 함수 */
	public Map<String, Object> selectMiningStatus(Map paramMap) throws SQLException;
	public Map<String, Object> selectMiningDistributionInfo(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectTodayMinersList(Map paramMap) throws SQLException;
	public Map<String, Object> selectMyMiningStatus(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectMonthlyMiningList(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectMiningListByDate(Map paramMap) throws SQLException;
	public Map<String, Object> selectKrwPossessionInfo(Map paramMap) throws SQLException;
	public Map<String, Object> selectYepClosePrice() throws SQLException;
	public Map<String, Object> requestAutoMining(Map paramMap) throws SQLException;
	// YEP Mining Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Staking Info 페이지에 보여줄 데이터 처리 함수 */
	public Map<String, Object> selectStakingStatus(Map paramMap) throws SQLException;
	public Map<String, Object> selectStakingInfo(Map paramMap) throws SQLException;
	public Map<String, Object> selectMyStakingStatus(Map paramMap) throws SQLException;
	public Map<String, Object> selectAvailableStakingQty(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectStakingRankerList(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectUnstakingHistoryList(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectMonthlyStakingInfoByDate(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectStakingHistoryByDate(Map paramMap) throws SQLException;
	// Staking 가능수량 체크 프로시저 call
	public void checkStakingAmount(Map paramMap) throws SQLException;
	// Staking 요청 프로시저 call
	public void stakingActivateRequest(Map paramMap) throws SQLException;
	// Staking Detail Record Insert 프로시저 call
	public void insertStakingDetailRecord(Map paramMap) throws SQLException;
	// Staking 요청에 대한 Balance 업데이트 프로시저 call
	public void stakingActivateBalanceUpdate(Map paramMap) throws SQLException;
	// UnStaking 요청 프로시저 call
	public void stakingDeActivateRequest(Map paramMap) throws SQLException;
	// YEP 평균매수가를 가져온다.
	public double selectYepAvgPriceByBc(Map paramMap) throws SQLException;
	// YEP Staking Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Airdrop Info 페이지에 보여줄 데이터 처리 함수 */
	public Map<String, Object> selectAirDropStatus(Map paramMap) throws SQLException;
	public Map<String, Object> selectAirDropInfo(Map paramMap) throws SQLException;
	public Map<String, Object> selectMiningStakingContributionInfo(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectMonthlyAirdropInfoByDate(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectAirdropHistoryByDate(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectEtcAirdropHistoryByDate(Map paramMap) throws SQLException;
	// YEP Airdrop Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Freezing Info 페이지에 보여줄 데이터 처리 함수 */
	public Map<String, Object> selectFreezingStatus(Map paramMap) throws SQLException;
	public Map<String, Object> selectDailyFreezingStatus(Map paramMap) throws SQLException;
	public List<Map<String, Object>> selectDailyFreezingStatusList(Map paramMap) throws SQLException;
	// YEP Freezing Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

}
