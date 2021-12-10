/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.yeprp.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.yeprp.dao.YahobitYepRpDAO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.yeprp.dao.impl 
 *    |_ YahobitYepRpDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오후 3:45:31
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
@Repository("yahobitYepRpDAO")
public class YahobitYepRpDAOImpl extends AbstractDefaultMapper implements YahobitYepRpDAO {

	@Override
	public String getNamespace() {
		return YahobitYepRpDAO.NAMESPACE;
	}

	@Override
	public Map<String, Object> selectStakeInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectStakeInfo", paramMap);
	}
	
	@Override
	public Map<String, Object> selectMarketTradeVolume(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMarketTradeVolume", paramMap);
	}

	@Override
	public Map<String, Object> selectDistributionStatus() throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectDistributionStatus");
	}

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Mining Info 페이지에 보여줄 데이터 처리 함수 */
	@Override
	public Map<String, Object> selectMiningStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMiningStatus", paramMap);
	}
	@Override
	public Map<String, Object> selectMiningDistributionInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMiningDistributionInfo", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectTodayMinersList(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectTodayMinersList", paramMap);
	}
	@Override
	public Map<String, Object> selectMyMiningStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMyMiningStatus", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectMonthlyMiningList(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectMonthlyMiningList", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectMiningListByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectMiningListByDate", paramMap);
	}
	@Override
	public Map<String, Object> selectKrwPossessionInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectKrwPossessionInfo", paramMap);
	}
	@Override
	public Map<String, Object> selectYepClosePrice() throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectYepClosePrice");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> requestAutoMining(Map paramMap) throws SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			paramMap.put("V_DURATION", 0);
			paramMap.put("V_TRADED_QTY", 0);
			paramMap.put("V_TRADED_AMT", 0);
			paramMap.put("V_TRADED_SELL_FEE", 0);
			paramMap.put("V_TRADED_BUY_FEE", 0);
			paramMap.put("V_RTN_CD", 0);
			paramMap.put("V_RTN_MSG", "");
			getSqlSession().selectOne(this.getNamespace() + ".call_PR_WAS_PROC_AUTO_MINING_ONETIME", paramMap);
			
			resultMap.put("V_DURATION", paramMap.get("V_DURATION"));
			resultMap.put("V_TRADED_QTY", paramMap.get("V_TRADED_QTY"));
			resultMap.put("V_TRADED_AMT", paramMap.get("V_TRADED_AMT"));
			resultMap.put("V_TRADED_SELL_FEE", paramMap.get("V_TRADED_SELL_FEE"));
			resultMap.put("V_TRADED_BUY_FEE", paramMap.get("V_TRADED_BUY_FEE"));
			resultMap.put("V_RTN_CD", paramMap.get("V_RTN_CD"));
			resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));
			return resultMap;
		} catch(Exception e) {
			throw e;
		}
	}
	// YEP Mining Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Staking Info 페이지에 보여줄 데이터 처리 함수 */
	@Override
	public Map<String, Object> selectStakingStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectStakingStatus", paramMap);
	}
	@Override
	public Map<String, Object> selectStakingInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectStakingInfo", paramMap);
	}
	@Override
	public Map<String, Object> selectMyStakingStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMyStakingStatus", paramMap);
	}
	@Override
	public Map<String, Object> selectAvailableStakingQty(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAvailableStakingQty", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectStakingRankerList(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectStakingRankerList", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectUnstakingHistoryList(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectUnstakingHistoryList", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectMonthlyStakingInfoByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectMonthlyStakingInfoByDate", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectStakingHistoryByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectStakingHistoryByDate", paramMap);
	}
	@Override
	public void checkStakingAmount(Map paramMap) throws SQLException {
		getSqlSession().selectList(this.getNamespace() + ".call_PR_WAS_STAKING_ACTIVATE_CHECK", paramMap);
	}
	@Override
	public void stakingActivateRequest(Map paramMap) throws SQLException {
		getSqlSession().selectList(this.getNamespace() + ".call_PR_WAS_STAKING_ACTIVATE_REQUEST", paramMap);
	}
	@Override
	public void insertStakingDetailRecord(Map paramMap) throws SQLException {
		getSqlSession().selectList(this.getNamespace() + ".call_PR_WAS_STAKING_DETAIL_RECORD_INSERT", paramMap);
	}
	@Override
	public void stakingDeActivateRequest(Map paramMap) throws SQLException {
		getSqlSession().selectList(this.getNamespace() + ".call_PR_WAS_STAKING_DEACTIVATE_REQUEST", paramMap);
	}
	@Override
	public void stakingActivateBalanceUpdate(Map paramMap) throws SQLException {
		getSqlSession().selectList(this.getNamespace() + ".call_PR_WAS_STAKING_ACTIVATE_BALANCE_UPDATE", paramMap);
	}
	@Override
	public double selectYepAvgPriceByBc(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectYepAvgPriceByBc", paramMap);
	}
	// YEP Staking Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Airdrop Info 페이지에 보여줄 데이터 처리 함수 */
	@Override
	public Map<String, Object> selectAirDropStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAirDropStatus", paramMap);
	}
	@Override
	public Map<String, Object> selectAirDropInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAirDropInfo", paramMap);
	}
	@Override
	public Map<String, Object> selectMiningStakingContributionInfo(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMiningStakingContributionInfo", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectMonthlyAirdropInfoByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectMonthlyAirdropInfoByDate", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectAirdropHistoryByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectAirdropHistoryByDate", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectEtcAirdropHistoryByDate(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectEtcAirdropHistoryByDate", paramMap);
	}
	// YEP Airdrop Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////
	/** YEP Freezing Info 페이지에 보여줄 데이터 처리 함수 */
	@Override
	public Map<String, Object> selectFreezingStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectFreezingStatus", paramMap);
	}
	@Override
	public Map<String, Object> selectDailyFreezingStatus(Map paramMap) throws SQLException {
		return getSqlSession().selectOne(this.getNamespace() + ".selectDailyFreezingStatus", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectDailyFreezingStatusList(Map paramMap) throws SQLException {
		return getSqlSession().selectList(this.getNamespace() + ".selectDailyFreezingStatusList", paramMap);
	}
	// YEP Freezing Info 페이지에 보여줄 데이터 처리 함수 END
	/////////////////////////////////////////////////////////////////////////////////

}
