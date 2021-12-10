/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.service;

import kr.co.coinis.webserver.coinis.mypage.mpg002.vo.ReqChangePwVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg002.service 
 *    |_ PwChangeService.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:24:49
 * @version : 
 * @author : Seongcheol
 */
public interface PwChangeService {

	/**
	 * PW 검사
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : PwMatch
	 * @date : 2019. 4. 29.
	 * @author : Jungjea
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		Jungjea						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqChangePwVO
	 * @return
	 */
	public ResultVO PwChange(ReqChangePwVO reqChangePwVO);

}
