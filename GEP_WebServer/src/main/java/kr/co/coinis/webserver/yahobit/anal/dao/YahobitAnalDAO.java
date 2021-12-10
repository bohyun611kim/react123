/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.yahobit.anal.vo.CoinMarketCapVO;
import kr.co.coinis.webserver.yahobit.anal.vo.CoinNewsVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopAmtVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TodayTopDaebiVO;
import kr.co.coinis.webserver.yahobit.anal.vo.TopDaebiRankVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.dao 
 *    |_ YahobitAnalDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 9:13:38
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitAnalDAO {

	public static final String NAMESPACE = YahobitAnalDAO.class.getName();
	
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
	 * 1. 개요 : 코인 뉴스 총 데이터 수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinNewsSize
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int selectCoinNewsSize(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 코인뉴스 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinNewsList
	 * @date : 2019. 5. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<CoinNewsVO> selectCoinNewsList(Map<String, Object> param) throws Exception;
}
