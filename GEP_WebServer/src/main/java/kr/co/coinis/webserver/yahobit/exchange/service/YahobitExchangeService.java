/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.RealContractVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.service 
 *    |_ YahobitExchangeService.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:16:28
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitExchangeService {

	/**
	 * <pre>
	 * 1. 개요 : 종목 기본정보 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectItemCodeInfo
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
	public Map<String, Object> selectItemCodeInfo(Map<String, String> param) throws Exception;
	
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
	 * @throws Exception
	 */
	public Map<String, Object> selectMktItemCodeInfo(Map<String, String> param) throws Exception;
	
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
	 * 1. 개요 : 마켓 정보, 보유코인 정보 가져오기
	 * 2. 처리내용 : 로그인 되어 있을 경우에만 보유코인 가져가기
	 * </pre>
	 * @Method Name : getMarket
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
	public Map<String, Object> getMarket(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 실시간 체결내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectRealContract
	 * @date : 2019. 5. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<RealContractVO> selectRealContract(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 일별시세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDailySise
	 * @date : 2019. 5. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 22.		Seongcheol				최초 작성 
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
	 * @date : 2019. 5. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<ContractVO> selectContract(Map<String, String> param);
	
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
	 * @Method Name : selectOrderCancelAllInfo
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
