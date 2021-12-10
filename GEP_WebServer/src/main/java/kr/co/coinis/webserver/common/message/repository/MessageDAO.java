/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.message.repository;

import kr.co.coinis.webserver.common.message.vo.MessageVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.message.repository 
 *    |_ MessageDao.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 8:52:31
 * @version : 
 * @author : Seongcheol
 */
public interface MessageDAO {

	/**
	 * <pre>
	 * 1. 개요 : redis message template 에 인터페이스 결과 입력
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : save
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param messageVO
	 * @throws Exception
	 */
	public void save(MessageVO messageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : redis message template 에서 해당 데이터 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : get
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param ifTransactionId
	 * @return
	 * @throws Exception
	 */
	public MessageVO get(String ifTransactionId) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : redis message template 에서 해당 데이터 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : delete
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param ifTransactionId
	 * @throws Exception
	 */
	public void delete(String ifTransactionId) throws Exception;
}
