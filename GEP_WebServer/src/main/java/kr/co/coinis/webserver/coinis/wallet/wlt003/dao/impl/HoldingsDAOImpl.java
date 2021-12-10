/*
\ * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.wallet.wlt003.dao.HoldingsDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.AirdropInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.CompensatedRewardInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.DailyLimitQtyVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.EstimatedValueVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.ReqHoldingsVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.dao.impl 
 *    |_ HoldingsDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 5:18:58
 * @version : 
 * @author : Seongcheol
 */
@Repository("holdingsDAO")
public class HoldingsDAOImpl extends AbstractDefaultMapper implements HoldingsDAO {

	@Override
	public String getNamespace() {
		return HoldingsDAO.NAMESPACE;
	}

	@Override
	public EstimatedValueVO retrieveEstimatedValue(ReqHoldingsVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveEstimatedValue", param);
	}

	@Override
	public DailyLimitQtyVO retrieveDailyLimitQty(ReqHoldingsVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveDailyLimitQty", param);
	}

	@Override
	public double retrieveTotalInUse(ReqHoldingsVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveTotalInUse", param);
	}

	@Override
	public List<PossessionInfoVO> retrievePossessionInfo(ReqHoldingsVO param) {
		return getSqlSession().selectList(this.getNamespace() + ".retrievePossessionInfo", param);
	}

	@Override
	public List<AirdropInfoVO> retrieveAirdropInfo(ReqHoldingsVO param) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveAirdropInfo", param);
	}

	@Override
	public int retrieveAirdropInfoCount(ReqHoldingsVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveAirdropInfoCount", param);
	}

	@Override
	public List<CompensatedRewardInfoVO> retrieveCompensatedRewardInfo(ReqHoldingsVO param) {
		return getSqlSession().selectList(this.getNamespace() + ".retrieveCompensatedRewardInfo", param);
	}

	@Override
	public int retrieveCompensatedRewardInfoCount(ReqHoldingsVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".retrieveCompensatedRewardInfoCount", param);
	}

	
}
