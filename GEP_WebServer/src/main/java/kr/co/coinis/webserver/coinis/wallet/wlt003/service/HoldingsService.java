/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.DailyLimitQtyVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.EstimatedValueVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.ReqHoldingsVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.service 
 *    |_ HoldingsService.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 9:00:12
 * @version : 
 * @author : yeonseoo
 */
public interface HoldingsService {
	
	/* BTC 환산 자산 총계 조회 */
	public EstimatedValueVO retrieveEstimatedValue(ReqHoldingsVO param);
	
	/* BTC 환산 일일 출금 한도 조회 */
	public DailyLimitQtyVO retrieveDailyLimitQty(ReqHoldingsVO param);
	
	/* BTC 환산 사용중 수량 조회 */
	public double retrieveTotalInUse(ReqHoldingsVO param);
	
	/* 총자산보유 현황 조회 */
	public List<PossessionInfoVO> retrievePossessionInfo(ReqHoldingsVO param);
	
	/* 에어드롭 내역 조회 */
	public Map<String, Object> retrieveAirdropInfo(ReqHoldingsVO param);
	
	/* 보상코인 지급내역 조회 */
	public Map<String, Object> retrieveCompensatedRewardInfo(ReqHoldingsVO param);

}
