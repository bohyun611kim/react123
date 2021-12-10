/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.home.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.yahobit.home.vo.BannerVO;
import kr.co.coinis.webserver.yahobit.home.vo.TopNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.home.dao 
 *    |_ YahoBitHomeDAO.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오전 9:10:44
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitHomeDAO {

	public static final String NAMESPACE = YahobitHomeDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : 홈 화면 배너 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBannerList
	 * @date : 2019. 5. 21.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public List<BannerVO> selectBannerList(String exchangeId);
	
	/**
	 * <pre>
	 * 1. 개요 : 홈 화면 배너 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBannerList
	 * @date : 2019. 5. 21.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public BannerVO selectBanner(String exchangeId);
	
	/**
	 * <pre>
	 * 1. 개요 : 최근 공지사항 5개 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNoticeList
	 * @date : 2019. 8. 6.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 6.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public List<TopNoticeVO> selectNoticeList(String exchangeId);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 초기화면 인플루언서 이벤트 Top 10 리스트 얻어오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectInfluencerTop10List
	 * @date : 2019. 7. 23.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 23.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<Map> selectInfluencerTop10List();
}
