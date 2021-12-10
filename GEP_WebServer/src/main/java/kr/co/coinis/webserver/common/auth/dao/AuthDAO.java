/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.auth.dao;

import kr.co.coinis.webserver.coinis.member.mbr001.dao.RegisterDAO;
import kr.co.coinis.webserver.common.auth.vo.ReqChekAuthCodeVO;
import kr.co.coinis.webserver.common.auth.vo.ReqInsertAuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.auth.dao 
 *    |_ AuthDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 3:09:08
 * @version : 
 * @author : Seongcheol
 */
public interface AuthDAO {
	
	public static final String NAMESPACE = RegisterDAO.class.getName();
	
	/**
	 * 인증 코드 생성
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : gnrtVrfctnCd
	 * @date : 2019. 3. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqinsertAuthCodeVO
	 * @return
	 */
	public ResultVO gnrtVrfctnCd(ReqInsertAuthCodeVO reqInsertAuthCodeVO);
	
	/**
	 * 인증 코드 확인
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : cnfrmVrfctnCd
	 * @date : 2019. 3. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param ReqChekAuthCodeVO
	 * @return
	 */
	public ResultVO cnfrmVrfctnCd(ReqChekAuthCodeVO ReqChekAuthCodeVO);
}
