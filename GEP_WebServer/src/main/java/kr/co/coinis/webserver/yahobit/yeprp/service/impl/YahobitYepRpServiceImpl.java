/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.yeprp.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.coinis.webserver.common.exception.ErrorResultException;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.yeprp.controller.YahobitYepRpController;
import kr.co.coinis.webserver.yahobit.yeprp.dao.YahobitYepRpDAO;
import kr.co.coinis.webserver.yahobit.yeprp.service.YahobitYepRpService;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.yeprp.service.impl 
 *    |_ YahobitYepRpServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오후 3:45:52
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
@Repository("yahobitYepRpService")
public class YahobitYepRpServiceImpl implements YahobitYepRpService {

	private static final Logger logger = LoggerFactory.getLogger(YahobitYepRpService.class);

	@Resource(name="yahobitYepRpDAO")
	YahobitYepRpDAO yahobitYepRpDAO;
	
	@Override
	public Map<String, Object> selectStakeInfo(Map paramMap) throws Exception {
		return yahobitYepRpDAO.selectStakeInfo(paramMap);
	}

	@Override
	public Map<String, Object> selectMarketTradeVolume(Map paramMap) throws Exception {
		return yahobitYepRpDAO.selectMarketTradeVolume(paramMap);
	}

	@Override
	public Map<String, Object> selectDistributionStatus() throws Exception {
		return yahobitYepRpDAO.selectDistributionStatus();
	}

	@Override
	public Map<String, Object> selectYepMiningStatus(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();

			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_DATE", paramMap.get("BASE_DATE").toString());
			
			// 오늘날짜, 어제날짜 계산
			String strCurDt = paramMap.get("BASE_DATE").toString().replaceAll("[.]", "").replaceAll("-", "");
			
			Date dtBase = new SimpleDateFormat("yyyyMMdd").parse(strCurDt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtBase);
			cal.add(Calendar.DATE, -1);
			Date prvDt = cal.getTime();
			String strPrevDt = new SimpleDateFormat("yyyyMMdd").format(prvDt);
			
			// 1. 상단 종합 Status 자료 select
			Map<String, Object> minigStatusMap = yahobitYepRpDAO.selectMiningStatus(params);
			
			// 2. 어제 및 오늘의 발생수수료 대비 실 배분량 자료 select
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> prevDateMinigDistribInfoMap = yahobitYepRpDAO.selectMiningDistributionInfo(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> curDateMinigDistribInfoMap = yahobitYepRpDAO.selectMiningDistributionInfo(params);
			
			// 3. 오늘의 YEP Mining 형황 리스트 조회
			params.put("LIST_SIZE", 50);
			List<Map<String, Object>> todayMinersList = yahobitYepRpDAO.selectTodayMinersList(params);
			// 4. 나의 Mining Status 조회
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> myPrevDateMiningStatusInfoMap = yahobitYepRpDAO.selectMyMiningStatus(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> myTodayMiningStatusInfoMap = yahobitYepRpDAO.selectMyMiningStatus(params);
			
			// 5. 일 Mining 내역 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> monthlyMiningStatusList = yahobitYepRpDAO.selectMonthlyMiningList(params);
			// 6. 나의 Mining 내역 리스트 조회
			List<Map<String, Object>> myMinigInfoByDateList = yahobitYepRpDAO.selectMiningListByDate(params);
			
			// 7. 나의 KRW 잔고 조회
			Map<String, Object> myKrwPossessInfoMap = yahobitYepRpDAO.selectKrwPossessionInfo(params);
			
			resultMap.put("TOTAL_MINING_STATUS_MAP", minigStatusMap);
			resultMap.put("PREV_DATE_MINING_DISTRIBUTION_MAP", prevDateMinigDistribInfoMap);
			resultMap.put("CUR_DATE_MINING_DISTRIBUTION_MAP", curDateMinigDistribInfoMap);
			resultMap.put("TODAY_TOP50_YEP_MINERS_LIST", todayMinersList);
			resultMap.put("MY_YESTERDAY_MINING_STATUS_MAP", myPrevDateMiningStatusInfoMap);
			resultMap.put("MY_TODAY_MINING_STATUS_MAP", myTodayMiningStatusInfoMap);
			resultMap.put("MONTHLY_MINING_LIST", monthlyMiningStatusList);
			resultMap.put("MY_MINING_INFO_LIST", myMinigInfoByDateList);
			resultMap.put("MY_KRW_POSSES_MAP", myKrwPossessInfoMap);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepStakingStatus(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();

			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_DATE", paramMap.get("BASE_DATE").toString());
			
			// 오늘날짜, 어제날짜 계산
			String strCurDt = paramMap.get("BASE_DATE").toString().replaceAll("[.]", "").replaceAll("-", "");
			
			Date dtBase = new SimpleDateFormat("yyyyMMdd").parse(strCurDt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtBase);
			cal.add(Calendar.DATE, -1);
			Date prvDt = cal.getTime();
			String strPrevDt = new SimpleDateFormat("yyyyMMdd").format(prvDt);
			
			// 1. 상단 종합 Status 자료 select
			Map<String, Object> stakingStatusMap = yahobitYepRpDAO.selectStakingStatus(params);
			
			// 2. 어제 및 오늘의 Staking 참여, Freezing 자료 select
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> prevDateStakingInfoMap = yahobitYepRpDAO.selectStakingInfo(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> curDateStakingInfoMap = yahobitYepRpDAO.selectStakingInfo(params);
			
			// 3. 오늘의 YEP Staking 기여도 상위자 리스트 조회
			params.put("LIST_SIZE", 50);
			List<Map<String, Object>> todayStakingRankerList = yahobitYepRpDAO.selectStakingRankerList(params);
			// 4. 나의 Staking 현황 조회
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> myPrevDateStakingStatusInfoMap = yahobitYepRpDAO.selectMyStakingStatus(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> myTodayStakingStatusInfoMap = yahobitYepRpDAO.selectMyStakingStatus(params);
			
			// 5. 나의 Staking 설정 (YEP RP 참여) 데이터 조회
			Map<String, Object> availableStakingQtyInfoMap = yahobitYepRpDAO.selectAvailableStakingQty(params);
			// 5.1 나의 Unstaking 이력 리스트 조회
			List<Map<String, Object>> myUnstakingHistoryList = yahobitYepRpDAO.selectUnstakingHistoryList(params);
			
			// 6. 일별 Staking 현황 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> monthlyStakingInfoByDateList = yahobitYepRpDAO.selectMonthlyStakingInfoByDate(params);
			
			// 7. 나의 일별 Staking 내역 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> myStakingHistoryByDateList = yahobitYepRpDAO.selectStakingHistoryByDate(params);
			
			resultMap.put("TOTAL_STAKING_STATUS_MAP", stakingStatusMap);
			resultMap.put("PREV_DATE_STAKING_INFO_MAP", prevDateStakingInfoMap);
			resultMap.put("CUR_DATE_STAKING_INFO_MAP", curDateStakingInfoMap);
			resultMap.put("TODAY_TOP50_YEP_STAKING_RANKER_LIST", todayStakingRankerList);
			resultMap.put("MY_YESTERDAY_STAKING_STATUS_MAP", myPrevDateStakingStatusInfoMap);
			resultMap.put("MY_TODAY_STAKING_STATUS_MAP", myTodayStakingStatusInfoMap);
			resultMap.put("MY_AVAILABLE_STAKING_INFO_MAP", availableStakingQtyInfoMap);
			resultMap.put("MY_UNSTAKING_HISTORY_LIST", myUnstakingHistoryList);
			resultMap.put("MONTHLY_STAKING_LIST", monthlyStakingInfoByDateList);
			resultMap.put("MY_STAKING_INFO_LIST", myStakingHistoryByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public Map<String, Object> selectYepAirDropStatus(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_DATE", paramMap.get("BASE_DATE").toString());
			
			// 오늘날짜, 어제날짜 계산
			String strCurDt = paramMap.get("BASE_DATE").toString().replaceAll("[.]", "").replaceAll("-", "");
			
			Date dtBase = new SimpleDateFormat("yyyyMMdd").parse(strCurDt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtBase);
			cal.add(Calendar.DATE, -1);
			Date prvDt = cal.getTime();
			String strPrevDt = new SimpleDateFormat("yyyyMMdd").format(prvDt);
			
			// 1. 상단 종합 AirDrop 현황 자료 select
			Map<String, Object> airDropStatusMap = yahobitYepRpDAO.selectAirDropStatus(params);
			
			// 2. 어제 및 오늘의 거래수소료, Staking Reward, 기여보너스 자료 select
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> prevDateAirdropInfoMap = yahobitYepRpDAO.selectAirDropInfo(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> curDateAirdropInfoMap = yahobitYepRpDAO.selectAirDropInfo(params);
			
			// 3. 나의 Mining/Staking 기여도 현황 조회
			Map<String, Object> todayMiningStakingContribInfoMap = yahobitYepRpDAO.selectMiningStakingContributionInfo(params);
			
			// 4. 일별 Airdrop 현황 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> monthlyAirdropInfoByDateList = yahobitYepRpDAO.selectMonthlyAirdropInfoByDate(params);
			
			// 5. 나의 일별 Airdrop 내역 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> myAirdropHistoryByDateList = yahobitYepRpDAO.selectAirdropHistoryByDate(params);
			// 6. 나의 일별 기타  Airdrop 내역 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> myEtcAirdropHistoryByDateList = yahobitYepRpDAO.selectEtcAirdropHistoryByDate(params);
			
			resultMap.put("TOTAL_AIRDROP_STATUS_MAP", airDropStatusMap);
			resultMap.put("PREV_DATE_AIRDROP_INFO_MAP", prevDateAirdropInfoMap);
			resultMap.put("CUR_DATE_AIRDROP_INFO_MAP", curDateAirdropInfoMap);
			resultMap.put("TODAY_MINING_STAKING_CONTRIBUTION_INFO_MAP", todayMiningStakingContribInfoMap);
			resultMap.put("MONTHLY_AIRDROP_LIST", monthlyAirdropInfoByDateList);
			resultMap.put("MY_AIRDROP_INFO_LIST", myAirdropHistoryByDateList);
			resultMap.put("MY_ETC_AIRDROP_INFO_LIST", myEtcAirdropHistoryByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public Map<String, Object> selectYepFreezingStatus(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_DATE", paramMap.get("BASE_DATE").toString());
			
			// 오늘날짜, 어제날짜 계산
			String strCurDt = paramMap.get("BASE_DATE").toString().replaceAll("[.]", "").replaceAll("-", "");
			
			Date dtBase = new SimpleDateFormat("yyyyMMdd").parse(strCurDt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtBase);
			cal.add(Calendar.DATE, -1);
			Date prvDt = cal.getTime();
			String strPrevDt = new SimpleDateFormat("yyyyMMdd").format(prvDt);
			
			// 1. 상단 종합 Freezing 현황 자료 select
			Map<String, Object> freezingStatusMap = yahobitYepRpDAO.selectFreezingStatus(params);
			
			// 2. 어제 및 오늘의 어제/오늘 Freezing 수량 및 누계  정보 자료 select
			params.put("BASE_DATE", strPrevDt);		// 기준일이 어제
			Map<String, Object> prevDateFreezingInfoMap = yahobitYepRpDAO.selectDailyFreezingStatus(params);
			params.put("BASE_DATE", strCurDt);		// 기준일이 오늘
			Map<String, Object> curDateFreezingInfoMap = yahobitYepRpDAO.selectDailyFreezingStatus(params);
			
			// 3. 일별 Freezing 내역 리스트 조회
			params.put("BASE_MONTH", new SimpleDateFormat("yyyyMM").format(dtBase));
			List<Map<String, Object>> monthlyFreezingInfoByDateList = yahobitYepRpDAO.selectDailyFreezingStatusList(params);
			
			resultMap.put("TOTAL_FREEZING_STATUS_MAP", freezingStatusMap);
			resultMap.put("PREV_DATE_FREEZING_INFO_MAP", prevDateFreezingInfoMap);
			resultMap.put("CUR_DATE_FREEZING_INFO_MAP", curDateFreezingInfoMap);
			resultMap.put("MONTHLY_FREEZING_LIST", monthlyFreezingInfoByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepFreezingDailyList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 Freezing 내역 리스트 조회
			List<Map<String, Object>> monthlyFreezingInfoByDateList = yahobitYepRpDAO.selectDailyFreezingStatusList(params);
			
			resultMap.put("MONTHLY_FREEZING_LIST", monthlyFreezingInfoByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResultVO processStakingRequest(Map param) throws ErrorResultException {
		
		ResultVO result = new ResultVO();
		result.setRtnCd(-9999);
		
		try {
			Map<String, Object> paramMap = new HashMap<>();
			
			// 1. Staking 가능 잔고 체크
			paramMap.put("ARG_EXCHANGE_ID", param.get("EXCHANGE_ID").toString() );
			paramMap.put("ARG_USER_ID", param.get("USER_ID").toString() );
			paramMap.put("ARG_COIN_NO", 430 );
			paramMap.put("ARG_REQ_QTY", Double.valueOf(param.get("STAKING_AMT").toString()).doubleValue() );
			paramMap.put("V_RTN_CD", -1 );
			paramMap.put("V_RTN_MSG", "" );
			
			yahobitYepRpDAO.checkStakingAmount(paramMap);
			
			int rtnCd = Double.valueOf(paramMap.get("V_RTN_CD").toString()).intValue();
			
			if(rtnCd == 0) {
				// 2. Staking Activate 요청
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", param.get("EXCHANGE_ID").toString() );
				paramMap.put("ARG_USER_ID", param.get("USER_ID").toString() );
				paramMap.put("ARG_COIN_NO", 430 );
				paramMap.put("ARG_AVG_BUY_PRICE", yahobitYepRpDAO.selectYepAvgPriceByBc(param) );			// 평군 매수가격
				paramMap.put("ARG_REQ_QTY", Double.valueOf(param.get("STAKING_AMT").toString()).doubleValue() );
				paramMap.put("V_REQ_SEQ_NO", 0 );
				paramMap.put("V_RTN_CD", -1 );
				paramMap.put("V_RTN_MSG", "" );
				
				yahobitYepRpDAO.stakingActivateRequest(paramMap);
				
				rtnCd = Double.valueOf(paramMap.get("V_RTN_CD").toString()).intValue();
				if(rtnCd == 0) {
					// 3. Staking Detail Record Table Insert
					long lReqSeqNo = Double.valueOf(paramMap.get("V_REQ_SEQ_NO").toString()).longValue();
					paramMap.put("ARG_REQ_SEQ_NO", lReqSeqNo);
					yahobitYepRpDAO.insertStakingDetailRecord(paramMap);
					
					rtnCd = Double.valueOf(paramMap.get("V_RTN_CD").toString()).intValue();
					
					if(rtnCd == 0) {
						// 4.  스테이킹 활성화 코인에 대한 잔고 변경처리
						long lStakeMgtNo = Double.valueOf(paramMap.get("V_STAKING_MGT_NO").toString()).longValue();
						paramMap.clear();
						paramMap.put("ARG_STAKING_MGT_NO", lStakeMgtNo );		// 스테이킹관리번호
						paramMap.put("V_RTN_CD", -1 );
						paramMap.put("V_RTN_MSG", "" );
						
						yahobitYepRpDAO.stakingActivateBalanceUpdate(paramMap);
						
						rtnCd = Double.valueOf(paramMap.get("V_RTN_CD").toString()).intValue();
						if(rtnCd == 0) {
							result.setRtnCd(0);
							result.setRtnMsg("Staking 요청 성공");
						} else {
							result.setRtnCd(rtnCd);
							result.setRtnMsg(paramMap.get("V_RTN_MSG").toString());
							throw new ErrorResultException(rtnCd, paramMap.get("V_RTN_MSG").toString());
						}
					} else {
						result.setRtnCd(rtnCd);
						result.setRtnMsg(paramMap.get("V_RTN_MSG").toString());
						throw new ErrorResultException(rtnCd, paramMap.get("V_RTN_MSG").toString());
					}
				} else {
					result.setRtnCd(rtnCd);
					result.setRtnMsg(paramMap.get("V_RTN_MSG").toString());
					throw new ErrorResultException(rtnCd, paramMap.get("V_RTN_MSG").toString());
				}
			} else {
				result.setRtnCd(rtnCd);
				result.setRtnMsg(paramMap.get("V_RTN_MSG").toString());
				throw new ErrorResultException(rtnCd, paramMap.get("V_RTN_MSG").toString());
			}
		} catch(ErrorResultException e) {
			throw e;
		} catch(SQLException e1) {
			logger.error(CommonUtils.getPrintStackTrace(e1));
			result.setRtnCd(-9999);
			result.setRtnMsg(e1.getMessage());
			return result;
			//throw new ErrorResultException(-9999, e1.getMessage());
		}
		return result;
	}

	@Override
	public ResultVO processUnStakingRequest(Map param) throws Exception {
		ResultVO result = new ResultVO();
		result.setRtnCd(-9999);
		try {
			Map<String, Object> paramMap = new HashMap<>();
			
			// 1. Un Staking 요청
			paramMap.put("ARG_STAKING_MGT_NO", Double.valueOf(param.get("STAKING_MGT_NO").toString()).longValue() );
			paramMap.put("ARG_REQ_QTY", Double.valueOf(param.get("REQ_QTY").toString()).doubleValue() );
			paramMap.put("V_RTN_CD", -1 );
			paramMap.put("V_RTN_MSG", "" );
			
			yahobitYepRpDAO.stakingDeActivateRequest(paramMap);
			
			int rtnCd = Double.valueOf(paramMap.get("V_RTN_CD").toString()).intValue();
			if(rtnCd == 0) {
				result.setRtnCd(0);
				result.setRtnMsg("Staking 해제 요청 성공");
			} else {
				result.setRtnCd(rtnCd);
				result.setRtnMsg(paramMap.get("V_RTN_MSG").toString());
				throw new ErrorResultException(rtnCd, paramMap.get("V_RTN_MSG").toString());
			}
		} catch(ErrorResultException e) {
			throw e;
		} catch(SQLException e1) {
			logger.error(CommonUtils.getPrintStackTrace(e1));
			result.setRtnCd(-9999);
			result.setRtnMsg(e1.getMessage());
			return result;
		}
		return result;
	}

	@Override
	public Map<String, Object> selectYepMiningDailyList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 Mining 내역 리스트 조회
			List<Map<String, Object>> monthlyMiningStatusList = yahobitYepRpDAO.selectMonthlyMiningList(params);
			
			resultMap.put("MONTHLY_MINING_LIST", monthlyMiningStatusList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepMyMiningDailyList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 나의 Mining 내역 리스트 조회
			List<Map<String, Object>> monthlyMiningStatusList = yahobitYepRpDAO.selectMiningListByDate(params);
			
			resultMap.put("MY_MINING_INFO_LIST", monthlyMiningStatusList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepStakingDailyList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 Staking 내역 리스트 조회
			List<Map<String, Object>> monthlyStakingStatusList = yahobitYepRpDAO.selectMonthlyStakingInfoByDate(params);
			
			resultMap.put("MONTHLY_STAKING_LIST", monthlyStakingStatusList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepMyStakingDailyList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 나의 Staking 내역 리스트 조회
			List<Map<String, Object>> monthlyMyStakingStatusList = yahobitYepRpDAO.selectStakingHistoryByDate(params);
			
			resultMap.put("MY_STAKING_INFO_LIST", monthlyMyStakingStatusList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepMyStakingAvailableInfo(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			
			// 1. 나의 Staking 설정 (YEP RP 참여) 데이터 조회
			Map<String, Object> availableStakingQtyInfoMap = yahobitYepRpDAO.selectAvailableStakingQty(params);
			
			resultMap.put("MY_AVAILABLE_STAKING_INFO_MAP", availableStakingQtyInfoMap);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	@Override
	public Map<String, Object> selectYepUnstakingHistoryList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			
			// 1. 나의 Unstaking 이력 리스트 조회
			List<Map<String, Object>> myUnstakingHistoryList = yahobitYepRpDAO.selectUnstakingHistoryList(params);
			
			resultMap.put("MY_UNSTAKING_HISTORY_LIST", myUnstakingHistoryList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> selectYepAirdropDailyHistoryList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 일별 Airdrop 내역 리스트 조회
			List<Map<String, Object>> monthlyAirdropInfoByDateList = yahobitYepRpDAO.selectMonthlyAirdropInfoByDate(params);
			
			resultMap.put("MONTHLY_AIRDROP_LIST", monthlyAirdropInfoByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	@Override
	public Map<String, Object> selectYepAirdropMyDailyHistoryList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 나의 일별 Airdrop 내역 리스트 조회
			List<Map<String, Object>> monthlyMyAirdropInfoByDateList = yahobitYepRpDAO.selectAirdropHistoryByDate(params);
			
			resultMap.put("MY_AIRDROP_INFO_LIST", monthlyMyAirdropInfoByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	@Override
	public Map<String, Object> selectYepAirdropMyEtcDailyHistoryList(Map paramMap) throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			
			Map<String, Object> params = new HashMap<>();
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("BASE_MONTH", paramMap.get("BASE_MONTH").toString());
			
			// 1. 나의 일별 기타 Airdrop 내역 리스트 조회
			List<Map<String, Object>> monthlyMyEtcAirdropInfoByDateList = yahobitYepRpDAO.selectEtcAirdropHistoryByDate(params);
			
			resultMap.put("MY_ETC_AIRDROP_INFO_LIST", monthlyMyEtcAirdropInfoByDateList);
			
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public double selectYepClosePrice() throws Exception {
		try {
			Map<String, Object> closePriceMap = yahobitYepRpDAO.selectYepClosePrice();
			double closePrice = Double.valueOf(closePriceMap.get("CLOSE_PRICE").toString()).doubleValue();
			return closePrice;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Map<String, Object> requestAutoMining(Map paramMap) throws Exception {
		try {
			Map<String, Object> callResponse = yahobitYepRpDAO.requestAutoMining(paramMap);
			return callResponse;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> selectKrwPossessionInfo(Map paramMap) throws Exception {
		try {
			// 나의 KRW 잔고 조회
			Map<String, Object> myKrwPossessInfoMap = yahobitYepRpDAO.selectKrwPossessionInfo(paramMap);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("MY_KRW_POSSES_MAP", myKrwPossessInfoMap);
			return resultMap;
		} catch(Exception e) {
			return null;
		}
	}

}
