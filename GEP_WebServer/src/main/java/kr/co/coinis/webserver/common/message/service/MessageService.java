/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.message.service;

import kr.co.coinis.webserver.common.message.vo.MessageVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.message.service 
 *    |_ MessageService.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 2:24:40
 * @version : 
 * @author : Seongcheol
 */
public interface MessageService {

	/**
	 * <pre>
	 * 1. 개요 : 인터페이스 결과 수신 대기(설정파일의 timeOut 시간 만큼 대기)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getDeferredResult
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
	public MessageVO getDeferredResult(String ifTransactionId) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 인터페이스 결과 수신 대기(입력한 timeOut 시간 만큼 대기)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getDeferredResult
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
	 * @param timeOut
	 * @return
	 * @throws Exception
	 */
	public MessageVO getDeferredResult(String ifTransactionId, int timeOut) throws Exception;
}
