/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.yeprp.service;

import java.util.Map;

import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.yeprp.service 
 *    |_ YahobitYepRpService.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오후 3:46:04
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
public interface YahobitYepRpService {

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
	public Map<String, Object> selectStakeInfo(Map paramMap) throws Exception;
	
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
	public Map<String, Object> selectDistributionStatus() throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 YEP Mining 현황정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMiningStatus
	 * @date : 2019. 6. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectYepMiningStatus(Map paramMap) throws Exception;
	/** 일별 Mining 내역 리스트 요청응답 */
	public Map<String, Object> selectYepMiningDailyList(Map paramMap) throws Exception;
	/** 일별 나의 Mining 내역 리스트 요청응답 */
	public Map<String, Object> selectYepMyMiningDailyList(Map paramMap) throws Exception;
	/**	YEP Auto Mining을 위한 Close Price 조회*/
	public double selectYepClosePrice() throws Exception;
	/** 나의 KRW 잔고정보를 얻어온다. */
	public Map<String, Object> selectKrwPossessionInfo(Map paramMap) throws Exception;
	/** Auto Mining을 요청한다.*/
	public Map<String, Object> requestAutoMining(Map paramMap) throws Exception;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 YEP Staking 현황정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepStakingStatus
	 * @date : 2019. 6. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectYepStakingStatus(Map paramMap) throws Exception;
	/** 일별 Staking 내역 리스트 요청응답 */
	public Map<String, Object> selectYepStakingDailyList(Map paramMap) throws Exception;
	/** 일별 나의 Staking 내역 리스트 요청응답 */
	public Map<String, Object> selectYepMyStakingDailyList(Map paramMap) throws Exception;
	/** 일별 나의 Staking 가능 금액 요청응답 */
	public Map<String, Object> selectYepMyStakingAvailableInfo(Map paramMap) throws Exception;
	/** 나의 Unstaking 이력 리스트 조회 요청응답 */
	public Map<String, Object> selectYepUnstakingHistoryList(Map paramMap) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 YEP AirDrop 현황정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepStakingStatus
	 * @date : 2019. 6. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectYepAirDropStatus(Map paramMap) throws Exception;
	/** 해당월의 Airdrop summary 일별 리스트를 가져온다. */
	public Map<String, Object> selectYepAirdropDailyHistoryList(Map paramMap) throws Exception;
	/** 나의 일별 Airdrop 내역 리스트 조회 */
	public Map<String, Object> selectYepAirdropMyDailyHistoryList(Map paramMap) throws Exception;
	/** 나의 일별 기타 Airdrop 내역 리스트 조회 */
	public Map<String, Object> selectYepAirdropMyEtcDailyHistoryList(Map paramMap) throws Exception;
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 YEP Freezing 현황정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepFreezingStatus
	 * @date : 2019. 6. 24.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 24.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectYepFreezingStatus(Map paramMap) throws Exception;
	/** 일별 Freezing 내역 리스트 요청응답 */
	public Map<String, Object> selectYepFreezingDailyList(Map paramMap) throws Exception;
	
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
	public Map<String, Object> selectMarketTradeVolume(Map paramMap) throws Exception;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 Staking 요청에대해 처리한다.
	 * 2. 처리내용 : Staking 요청 가능수량 체크후 Staking 요청
	 * </pre>
	 * @Method Name : processStakingRequest
	 * @date : 2019. 7. 7.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 7.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return ResultVO
	 * @throws Exception
	 */
	public ResultVO processStakingRequest(Map paramMap) throws Exception;
	/** UnStaking 요청을 진행 */
	public ResultVO processUnStakingRequest(Map paramMap) throws Exception;
	
}
