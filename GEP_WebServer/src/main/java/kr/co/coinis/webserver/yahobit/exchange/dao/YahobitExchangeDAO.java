/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.BalanceVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.HogaVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeListVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.MarketInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.NonContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.RealContractVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.dao 
 *    |_ YahobitExchangeDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:15:58
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitExchangeDAO {

	public static final String NAMESPACE = YahobitExchangeDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : 종목 기본정보 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getDefaultItemCode
	 * @date : 2019. 4. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public ItemCodeInfoVO selectDefaultItemCode(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 종목 기본정보 가져오기(mktId, mktGrpId parameter 포함)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectItemCodeInfo
	 * @date : 2019. 5. 4.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public ItemCodeInfoVO selectItemCodeInfo(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : coinNo에 해당하는 itemCode들을 조회한다
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDefaultItemCode
	 * @date : 2019. 4. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<ItemCodeListVO> selectItemCodeOfCoin(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 코인 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinInfo
	 * @date : 2019. 4. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param coinNo
	 * @return
	 */
	public CoinInfoVO selectCoinInfo(int coinNo);
	
	/**
	 * <pre>
	 * 1. 개요 : 마켓 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMarket
	 * @date : 2019. 4. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<MarketInfoVO> selectMarket(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 프리 마켓 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectFreeMarket
	 * @date : 2019. 5. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<MarketInfoVO> selectFreeMarket(Map<String, String> param);

	/**
	 * <pre>
	 * 1. 개요 : 보유잔고조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalance
	 * @date : 2019. 4. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<BalanceVO> selectBalance(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 보유잔고조회(프리 마켓:YEP)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalance
	 * @date : 2019. 5. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<BalanceVO> selectFreeBalance(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : toCoin, fromCoin에 해당하는 잔고만 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalanceByCoinNo
	 * @date : 2019. 4. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public double selectBalanceByCoinNo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 매도 10호가 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSellHogaList
	 * @date : 2019. 4. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<HogaVO> selectSellHogaList(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 매수 10호가 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBuyHogaList
	 * @date : 2019. 4. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<HogaVO> selectBuyHogaList(Map<String, String> param);

	/**
	 * <pre>
	 * 1. 개요 : 실시간 체결내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectRealContract
	 * @date : 2019. 4. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<RealContractVO> selectRealContract(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 일별 시세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDailySise
	 * @date : 2019. 5. 2.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<DailySiseVO> selectDailySise(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 회원 체결 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectContract
	 * @date : 2019. 5. 2.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<ContractVO> selectContract(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 미체결 데이터 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContract
	 * @date : 2019. 5. 2.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<NonContractVO> selectNonContract(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 취소 주문에 필요한 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOrderCancelInfo
	 * @date : 2019. 5. 3.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 매도, 매수 전체 취소에 필요한 데이터 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOrderCancelInfo
	 * @date : 2019. 5. 3.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<SendOrderCancelVO> selectOrderCancelAllInfo(Map<String, Object> param);
}
