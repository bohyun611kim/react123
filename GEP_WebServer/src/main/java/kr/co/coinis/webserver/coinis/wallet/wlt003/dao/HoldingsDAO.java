/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.AirdropInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.CompensatedRewardInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.DailyLimitQtyVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.EstimatedValueVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.ReqHoldingsVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.dao 
 *    |_ HoldingsDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 5:19:09
 * @version : 
 * @author : yeonseoo
 */
public interface HoldingsDAO {

	public static final String NAMESPACE = HoldingsDAO.class.getName();
	
	/**
	 * BTC 환산 자산 총계
	 * <pre>
	 * 1. 개요 : 회원의 자산 총계를 BTC 환산가와 거래소 통화화폐코드로 환산한 금액으로 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveEstimatedValue
	 * @date : 2019. 4. 26.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  26.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public EstimatedValueVO retrieveEstimatedValue(ReqHoldingsVO param);
	
	/**
	 * BTC 환산 일일 출금 한도
	 * <pre>
	 * 1. 개요 : 회원의 일일 출금 한도를 BTC 환산가로 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveDailyLimitQty
	 * @date : 2019. 4. 26.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  26.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public DailyLimitQtyVO retrieveDailyLimitQty(ReqHoldingsVO param);
	
	/**
	 * BTC 환산 사용중 수량
	 * <pre>
	 * 1. 개요 : 회원의 사용중 수량을 BTC 환산가로 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveTotalInUse
	 * @date : 2019. 4. 26.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  26.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public double retrieveTotalInUse(ReqHoldingsVO param);
	
	/**
	 * 전체 보유 현황
	 * <pre>
	 * 1. 개요 : 회원의 전체 보유 현황 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrievePossessionInfo
	 * @date : 2019. 4. 26.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  26.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<PossessionInfoVO> retrievePossessionInfo(ReqHoldingsVO param);
	
	/**
	 * 전체 보유 현황
	 * <pre>
	 * 1. 개요 : 회원의 에어드랍 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveAirdropInfo
	 * @date : 2019. 4. 29.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  29.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<AirdropInfoVO> retrieveAirdropInfo(ReqHoldingsVO param);
	
	// Paging
	public int retrieveAirdropInfoCount(ReqHoldingsVO param);
	
	/**
	 * 전체 보유 현황
	 * <pre>
	 * 1. 개요 : 회원의 보상코인 지급내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : retrieveCompensatedRewardInfo
	 * @date : 2019. 4. 29.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4.  29.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<CompensatedRewardInfoVO> retrieveCompensatedRewardInfo(ReqHoldingsVO param);
	
	// Paging
	public int retrieveCompensatedRewardInfoCount(ReqHoldingsVO param);
	
}
