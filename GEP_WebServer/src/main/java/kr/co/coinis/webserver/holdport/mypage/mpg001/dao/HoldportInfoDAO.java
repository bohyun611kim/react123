/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.AccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthLevelVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqSmsAuthVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.UserInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.dao
 *    |_ InfoDAO.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오전 10:22:55
 * @version :
 * @author : Seongcheol
 */
public interface HoldportInfoDAO {

	public static final String NAMESPACE = HoldportInfoDAO.class.getName();

	/**
	 * <pre>
	 * 1. 개요 : 마이페이지 개인정보 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectUserInfo
	 * @date : 2019. 4. 22.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 22.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqUserInfoVO
	 * @return
	 */
	public UserInfoVO selectUserInfo(ReqUserInfoVO reqUserInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : 현재 사용자의 추천인 정보를 가져온다
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectRecommendInfo
	 * @date : 2019. 4. 22.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 22.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqUserInfoVO
	 * @return
	 */
	public RecommendInfoVO selectRecommendInfo(ReqUserInfoVO reqUserInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : 입력받은 비밀번호와 현재 비밀번호 일치 여부 확인
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkPassword
	 * @date : 2019. 4. 26.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param pwChangeVO
	 * @return
	 */
	public int checkPassword(PwChangeVO pwChangeVO);

	/**
	 * <pre>
	 * 1. 개요 : 비밀번호 변경 처리
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procChangePassword
	 * @date : 2019. 4. 26.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param pwChangeVO
	 * @return
	 */
	public PwChangeVO procChangePassword(PwChangeVO pwChangeVO);

	/**
	 * <pre>
	 * 1. 개요 : 보안 인증 수단 변경 처리
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procChangeAuthMeansCd
	 * @date : 2019. 5. 15.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 15.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqAuthMeansVO
	 * @return
	 */
	public ReqAuthMeansVO procChangeAuthMeansCd(ReqAuthMeansVO reqAuthMeansVO);

	/**
	 * <pre>
	 * 1. 개요 : update marketing agreement
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updateMarketingAgree
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param agree
	 * @return
	 */
	public int updateMarketingAgree(ReqMarketingAgreeVO reqMarketingAgreeVO);

	/**
	 * <pre>
	 * 1. 개요 : 모바일 번호 중복 여부 확인
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkMobileDuplication
	 * @date : 2019. 6. 25.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 25.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param kcpAuthVO
	 * @return
	 */
	public int checkMobileDuplication(KcpAuthVO kcpAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증 레벨 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAuthLevel
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @return
	 */
	public UserInfoVO selectAuthLevel(KcpAuthVO kcpAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 마지막 인증요청 성공 시간 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectLastAuthDateTime
	 * @date : 2019. 6. 17.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 17.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param param
	 * @return
	 */
	public String selectLastAuthDateTime(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 회원 추가 정보 저장(이름, 생년월일)
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procUpdateMemberInfoExtra
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param kcpAuthVO
	 * @return
	 */
	public KcpAuthVO procUpdateMemberInfoExtra(KcpAuthVO kcpAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증 레벨 변경
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procSetAuthLevel
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqAuthLevelVO
	 * @return
	 */
	public int updateMemberInfo(ReqAuthLevelVO reqAuthLevelVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증수단 sms 변경 처리
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procSetSmsAuth
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqSmsAuthVO
	 * @return
	 */
	public ReqSmsAuthVO procSetSmsAuth(ReqSmsAuthVO reqSmsAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 모바일 번호 업데이트
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updatePhoneNo
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param kcpAuthVO
	 * @return
	 */
	public int updatePhoneNo(KcpAuthVO kcpAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 접속 관리 정보 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAccessLog
	 * @date : 2019. 5. 11.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 11.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqAccessLogVO
	 * @return
	 */
	public List<AccessLogVO> selectAccessLog(ReqAccessLogVO reqAccessLogVO);

	/**
	 * <pre>
	 * 1. 개요 : 접속 관리 정보 조회(총 건수)
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectAccessLogCnt
	 * @date : 2019. 5. 11.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 11.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqAccessLogVO
	 * @return
	 */
	public int selectAccessLogCnt(ReqAccessLogVO reqAccessLogVO);

	/**
	 * <pre>
	 * 1. 개요 : sms 인증번호 확인
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : procCheckAuthCode
	 * @date : 2019. 6. 14.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 14.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO);

	/**
	 * <pre>
	 * 1. 개요 : otp key 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectOtpKey
	 * @date : 2019. 6. 14.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 14.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param apram
	 * @return
	 */
	public String selectOtpKey(Map<String, Object> param);

}
