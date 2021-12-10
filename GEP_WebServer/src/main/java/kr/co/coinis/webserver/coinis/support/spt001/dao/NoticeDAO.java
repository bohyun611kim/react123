/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.ReqNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.dao 
 *    |_ NoticeDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:27
 * @version : 
 * @author : Seongcheol
 */
public interface NoticeDAO {

	public static final String NAMESPACE = NoticeDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 size 조회(거래소 아이디, 페이지 번호에 해당하는 데이터)
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
	public int selectNoticeSize(ReqNoticeVO reqNoticeVO);
	
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
	public List<NoticeVO> selectNoticeList(ReqNoticeVO reqNoticeVO);
	
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
