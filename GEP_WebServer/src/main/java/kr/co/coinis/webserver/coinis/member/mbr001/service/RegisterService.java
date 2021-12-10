/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.service;

import java.util.List;

import kr.co.coinis.webserver.coinis.member.mbr001.vo.CountryCodeVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqInsertMemberInfoVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.service 
 *    |_ RegisterService.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 4:00:49
 * @version : 
 * @author : Seongcheol
 */
public interface RegisterService {
	
	/**
	 * <pre>
	 * 1. 개요 : 국가 코드 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryCode
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<CountryCodeVO> selectCountryCode();
	
	/**
	 * <pre>
	 * 1. 개요 : 개인회원 가입 요청
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : doRegist
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqInsertMemberInfoVO
	 * @return
	 */
	public ResultVO doRegist(ReqInsertMemberInfoVO reqInsertMemberInfoVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 회원 가입 후 이메일 인증 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : registerEmailAuth
	 * @date : 2019. 4. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqEmailAuthVO
	 * @return
	 */
	public ResultVO registerEmailAuth(ReqEmailAuthVO reqEmailAuthVO);
}
