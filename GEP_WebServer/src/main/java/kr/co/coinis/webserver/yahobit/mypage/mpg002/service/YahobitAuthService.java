/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg002.service;

import java.sql.SQLException;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg002.service 
 *    |_ YahobitAuthService.java
 * 
 * </pre>
 * @date : 2019. 5. 8. 오전 10:37:29
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
public interface YahobitAuthService {
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 OTP Key값을 등록한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : call_PR_WAS_SET_OTP_AUTH
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : OPT 사용설정 및 인증레벨정보를 업데이트 한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateMemberInfoAtOtp
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public int updateMemberInfoAtOtp(Map paramMap) throws SQLException;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 비밀번호가 맞는지 여부를 검사한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkUserPassword
	 * @date : 2019. 5. 9.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 9.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUserPassword(Map paramMap) throws SQLException;
	
	/**
	 * <pre>
	 * 1. 개요 : 4레벨 인증 요청 가능 여부를 확인 하기 위한 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAuthLevel4Info
	 * @date : 2019. 7. 1.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 1.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public AuthLevel4VO selectAuthLevel4Info(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 신분증 자료 제출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uploadIdCard
	 * @date : 2019. 5. 14.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 14.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 */
	public ResultVO uploadIdCard(ReqIdCardVO reqIdCardVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 신분증 자료 제출 (법인회원)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uploadCorpIdCard
	 * @date : 2019. 5. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 * @throws Exception
	 */
	public ResultVO uploadCorpIdCard(ReqIdCardVO reqIdCardVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 5레벨 인증정보 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : proveResidence
	 * @date : 2019. 7. 8.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqAuthLevel5VO
	 * @return
	 * @throws Exception
	 */
	public ResultVO proveResidence(ReqAuthLevel5VO reqAuthLevel5VO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 5레벨 인증 대상 여부 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAuthLevel5Info
	 * @date : 2019. 7. 8.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public AuthLevel5VO selectAuthLevel5Info(Map<String, Object> param);
}
