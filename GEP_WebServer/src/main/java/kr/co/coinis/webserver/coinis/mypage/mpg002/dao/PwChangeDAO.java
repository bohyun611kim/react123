/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.dao;

import kr.co.coinis.webserver.coinis.mypage.mpg002.vo.ReqChangePwVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg002.dao 
 *    |_ PwChangeDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:23:59
 * @version : 
 * @author : Seongcheol
 */
public interface PwChangeDAO {

	public static final String NAMESPACE = PwChangeDAO.class.getName();
	
	/**
	 * 패스워드 검사
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
	public int reqPwMatch(ReqChangePwVO reqChangePwVO);
	

	/**
	 * 패스워드 변경
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : PwChange
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
	public ReqChangePwVO reqPwChange(ReqChangePwVO reqChangePwVO);
}
