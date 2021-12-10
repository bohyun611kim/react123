/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.service;

import java.util.Map;

import kr.co.coinis.webserver.coinis.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.ReqNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.service 
 *    |_ NoticeService.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:15
 * @version : 
 * @author : Seongcheol
 */
public interface NoticeService {

	/**
	 * <pre>
	 * 1. 개요 : 공지사항 조회(거래소 아이디, 페이지 번호에 해당하는 데이터), 사이즈 포함
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNoticeListWithSize
	 * @date : 2019. 4. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqNoticeVO
	 * @return
	 */
	public Map<String, Object> selectNoticeListWithSize(ReqNoticeVO reqNoticeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 조회(거래소 아이디, 페이지 번호에 해당하는 데이터)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNoticeList
	 * @date : 2019. 4. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqNoticeVO
	 * @return
	 */
	public Map<String, Object> selectNoticeList(ReqNoticeVO reqNoticeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 한 건 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNoticeOne
	 * @date : 2019. 4. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqNoticeVO
	 * @return
	 */
	public NoticeVO selectNoticeOne(ReqNoticeVO reqNoticeVO);
}
