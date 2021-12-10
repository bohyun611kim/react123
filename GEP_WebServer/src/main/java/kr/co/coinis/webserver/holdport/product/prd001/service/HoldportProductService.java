/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd001.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.holdport.product.prd001.vo.EventVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.NoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.prd001.service
 *    |_ NoticeService.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:15
 * @version :
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
public interface HoldportProductService {

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_NOTICE 테이블에서 공지사항 리스트를 얻어온다.
	 * 2. 처리내용 : TOP_DISP_YN DESC, URGENT_YN DESC 정렬, DISP_START_DT 가 오늘날짜보다 작은것 가져온다.
	 * </pre>
	 * @Method Name : selectNoticeList
	 * @date : 2019. 4. 24.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> selectNoticeList(Map paramMap) throws Exception;
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws Exception;

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_EVENT 테이블에서 이벤트 리스트를 얻어온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectEventList
	 * @date : 2019. 5. 17.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 17.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<EventVO> selectEventList(Map paramMap) throws Exception;

	public List<EventVO> selectEventListCount(Map paramMap) throws Exception;

	public HashMap<String, Object> updatePointConvertRequest(HashMap<String, Object> paramMap) throws Exception;

	public HashMap<String, Object> procCoinToPointConvert(HashMap<String, Object> paramMap) throws Exception;


	public Map<String, Object> selectCoinBalance(Map paramMap) throws Exception;

	public HashMap<String, Object> selectCoinTradeAmout(HashMap paramMap) throws Exception;

	public List<Map<String, Object>> selectPointConvertList(Map paramMap) throws Exception;



}



