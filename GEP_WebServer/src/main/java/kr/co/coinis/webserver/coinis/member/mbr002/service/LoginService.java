/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.service;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.ReqLoginVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.service 
 *    |_ LoginService.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:23
 * @version : 
 * @author : Seongcheol
 */
public interface LoginService {

	/**
	 * 로그인 정보 검사
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectLoginMatch
	 * @date : 2019. 4. 1.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 1.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqLoginVO
	 * @return
	 */
	public ResultVO selectLoginMatch(ReqLoginVO reqLoginVO);
	
	/**
	 * 회원 정보 조회
	 * <pre>
	 * 1. 개요 : 로그인 처리 후 session에 담길 회원 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberInfo
	 * @date : 2019. 4. 5.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 5.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqLoginVO
	 * @return
	 */
	public CustomUserDetails selectMemberInfo(ReqLoginVO reqLoginVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 실패 카운트 초기화
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procSetFailCnt
	 * @date : 2019. 8. 14.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 14.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public FailCntVO procSetFailCnt(FailCntVO failCntVO);
}
