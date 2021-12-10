/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.yahobit.anal.vo.CoinMarketCapVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopAmtVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopDaebiVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TopDaebiRankVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.service 
 *    |_ YahobitAnalService.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 9:13:55
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitAnalService {

	/**
	 * <pre>
	 * 1. 개요 : 오늘 상승율 TOP 10 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTodayTopDaebi
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TodayTopDaebiVO> selectTodayTopDaebi(String exchangeId) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 오늘 거래대금 TOP 10 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTodayTopAmt
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TodayTopAmtVO> selectTodayTopAmt(String exchangeId) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 기간별 상승율 상위 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTopDaebiRank
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TopDaebiRankVO> selectTopDaebiRank(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 코인마켓캠 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinMarketCap
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CoinMarketCapVO> selectCoinMarketCap() throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 코인뉴스 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinNews
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCoinNews(Map<String, Object> param) throws Exception;
}
