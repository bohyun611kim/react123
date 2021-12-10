/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr002.dao;

import java.util.Map;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.AuthInfoVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.LoginMatchResultVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.ReqLoginVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.dao 
 *    |_ LoginDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:10
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitLoginDAO {

	public static final String NAMESPACE = YahobitLoginDAO.class.getName();
	
	/**
	 * 로그인 정보 검사
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : doLogin
	 * @date : 2019. 4. 1.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 1.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public LoginMatchResultVO selectLoginMatch(ReqLoginVO reqLoginVO);
	
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
	 * 1. 개요 : 최종 로그인 정보 갱신
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateLastLogin
	 * @date : 2019. 5. 6.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 6.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> updateLastLogin(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 사용자 인증 수단 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserMeansCd
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthInfoVO selectUserAuthInfo(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : SMS 인증번호 요청코드 생성 및 저장 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertAuthCode
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procInsertAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : sms 인증번호 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procCheckAuthCode
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 실패 카운트 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateFailCnt
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
	public int updateFailCnt(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 실패 횟수 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectFailCnt
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
	public int selectFailCnt(Map<String, Object> param);
	
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
