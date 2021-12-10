/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.service;

import java.text.ParseException;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.UserInfoVO;


/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.service
 *    |_ InfoService.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오전 10:23:19
 * @version :
 * @author : Seongcheol
 */
public interface HoldportInfoService {

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
	 * 1. 개요 : 비밀번호 변경 처리
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : changePassword
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
	public ResultVO changePassword(PwChangeVO pwChangeVO);

	/**
	 * <pre>
	 * 1. 개요 : 보안 인증 수단 변경
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : changeAuthMeansCd
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
	public ResultVO changeAuthMeansCdToSms(ReqAuthMeansVO reqAuthMeansVO);
	public ResultVO changeAuthMeansCdToOtp(ReqAuthMeansVO reqAuthMeansVO);

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
	public ResultVO updateMarketingAgree(ReqMarketingAgreeVO reqMarketingAgreeVO);

	/**
	 * <pre>
	 * 1. 개요 : 휴대폰 인 증 가능 여부 확인
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkLastAuthDateTime
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
	public boolean checkLastAuthDateTime(Map<String, Object> param) throws NumberFormatException, ParseException;

	/**
	 * <pre>
	 * 1. 개요 : 휴대전화 번호 업데이트
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
	public ResultVO updatePhoneNo(KcpAuthVO kcpAuthVO) throws Exception;

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
	public Map<String, Object> selectAccessLog(ReqAccessLogVO reqAccessLogVO);
}
