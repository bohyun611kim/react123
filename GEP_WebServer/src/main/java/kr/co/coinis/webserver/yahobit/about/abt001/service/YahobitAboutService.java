/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.about.abt001.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.about.abt001.service 
 *    |_ YahobitAboutService.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:04:58
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
public interface YahobitAboutService {

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectInquiryList
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectInquiryList(Map paramMap) throws SQLException;
	
	public Map<String, Object> selectInquiryListCount(Map paramMap) throws SQLException;

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의에 대한 답변내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectInquiryReplyList
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectInquiryReplyList(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의 등록 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertInquiry
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> insertInquiry(Map paramMap) throws SQLException;
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의 등록 첨부파일 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertInquiryAttachFile
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> insertInquiryAttachFile(Map paramMap, List<Map<String, String>> paramList) throws SQLException;
	
}
