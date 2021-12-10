/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg002.dao;

import java.sql.SQLException;
import java.util.Map;

import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg002.dao
 *    |_ HoldportAuthDAO.java
 *
 * </pre>
 * @date : 2019. 5. 8. 오전 10:38:13
 * @version :
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
public interface HoldportAuthDAO {

	public static final String NAMESPACE = HoldportAuthDAO.class.getName();

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
	 * 1. 개요 : 사용자 정보 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectUserInfo
	 * @date : 2019. 5. 15.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 15.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param param
	 * @return
	 */
	public String selectUserInfo(ReqIdCardVO reqIdCardVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증 레벨 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAuthLevel
	 * @date : 2019. 5. 15.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 15.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param param
	 * @return
	 */
	public String selectAuthLevel(ReqIdCardVO reqIdCardVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증 레벨 조회 (법인 회원)
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAuthLevelCorp
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
	 */
	public String selectAuthLevelCorp(ReqIdCardVO reqIdCardVO);

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
	 * 1. 개요 : 보안인증 4레벨 정보 업데이트
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updateLevel4Request
	 * @date : 2019. 6. 28.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 28.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqIdCardVO
	 * @return
	 */
	public int updateLevel4Request(ReqIdCardVO reqIdCardVO);

	/**
	 * <pre>
	 * 1. 개요 : 신중증 파일 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procinsertIdAuthInfo
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
	 */
	public Map<String, Object> procInsertIdAuthInfo(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 사업자인증파일정보
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procInsertMemberCorpAuthInfo
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
	 */
	public Map<String, Object> procInsertMemberCorpAuthInfo(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 5레벨 인증 요청 가능 여부를 확인 하기 위한 정보 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAuthLevel5Info
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
	public AuthLevel5VO selectAuthLevel5Info(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 5레벨 인증 정보 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procMemberLevel5Mgr
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
	 */
	public ReqAuthLevel5VO procMemberLevel5Mgr(ReqAuthLevel5VO reqAuthLevel5VO);

	/**
	 * <pre>
	 * 1. 개요 : 주소 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updateExtraAddress
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
	 */
	public int updateExtraAddress(ReqAuthLevel5VO reqAuthLevel5VO);

}
