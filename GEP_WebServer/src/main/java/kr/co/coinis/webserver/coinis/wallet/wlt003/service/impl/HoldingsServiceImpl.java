/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.wallet.wlt003.dao.HoldingsDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.service.HoldingsService;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.AirdropInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.CompensatedRewardInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.DailyLimitQtyVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.EstimatedValueVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.ReqHoldingsVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.service.impl 
 *    |_ HoldingsServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 9:00:12
 * @version : 
 * @author : yeonseoo
 */
@Service("holdingsService")
public class HoldingsServiceImpl implements HoldingsService {

	@Resource(name="holdingsDAO")
	private HoldingsDAO holdingsDAO;

	@Override
	public EstimatedValueVO retrieveEstimatedValue(ReqHoldingsVO param) {
		return holdingsDAO.retrieveEstimatedValue(param);
	}

	@Override
	public DailyLimitQtyVO retrieveDailyLimitQty(ReqHoldingsVO param) {
		return holdingsDAO.retrieveDailyLimitQty(param);
	}

	@Override
	public double retrieveTotalInUse(ReqHoldingsVO param) {
		return holdingsDAO.retrieveTotalInUse(param);
	}

	@Override
	public List<PossessionInfoVO> retrievePossessionInfo(ReqHoldingsVO param) {
		return holdingsDAO.retrievePossessionInfo(param);
	}

	@Override
	public Map<String, Object> retrieveAirdropInfo(ReqHoldingsVO param) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("size", holdingsDAO.retrieveAirdropInfoCount(param));
		result.put("data", holdingsDAO.retrieveAirdropInfo(param));
		
		return result;
	}

	@Override
	public Map<String, Object> retrieveCompensatedRewardInfo(ReqHoldingsVO param) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("size", holdingsDAO.retrieveCompensatedRewardInfoCount(param));
		result.put("data", holdingsDAO.retrieveCompensatedRewardInfo(param));
		
		return result;
	}

}
