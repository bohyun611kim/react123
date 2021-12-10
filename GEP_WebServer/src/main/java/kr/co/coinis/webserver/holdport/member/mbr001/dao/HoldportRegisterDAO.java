/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr001.dao;

import java.util.Map;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.holdport.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.holdport.member.mbr001.vo.ReqInsertMemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.dao
 *    |_ RegisterDAO.java
 *  * </pre>
 * @date : 2019. 3. 25. 오후 4:01:12
 * @version :
 * @author : Seongcheol
 */
public interface HoldportRegisterDAO {

	public static final String NAMESPACE = HoldportRegisterDAO.class.getName();

	/**
	 * 회원가입
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : doRegist
	 * @date : 2019. 3. 26.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 26.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @return
	 */
	public ReqInsertMemberInfoVO insertMemberInfo(ReqInsertMemberInfoVO reqInsertMemberInfoVO);

	/**
	 * 회원 extra 정보 입력
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : insertMemberInfoExtra
	 * @date : 2019. 4. 1.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 1.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqInsertMemberInfoVO
	 * @return
	 */
	public ReqInsertMemberInfoVO insertMemberInfoExtra(ReqInsertMemberInfoVO reqInsertMemberInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : 회원가입일시 가져오기
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectJoinDt
	 * @date : 2019. 4. 22.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 22.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqInsertMemberInfoVO
	 * @return
	 */
	public String selectJoinDt(ReqInsertMemberInfoVO reqInsertMemberInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : 법인 회원 법인 정보 입력
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : insertMemberInfoExtra
	 * @date : 2019. 4. 22.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 22.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqInsertMemberInfoVO
	 * @return
	 */
	public ReqInsertMemberInfoVO insertMemberCorpInfo(ReqInsertMemberInfoVO reqInsertMemberInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : 전달 받은 값에 일치하는 가입 진행 중인 회원 찾기
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectEmailAuth
	 * @date : 2019. 4. 25.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqEmailAuthVO
	 * @return
	 */
	public AuthCodeVO procEmailAuth(AuthCodeVO authCodeVO);

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
	public ReqEmailAuthVO updateMemberStatus(ReqEmailAuthVO reqEmailAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 인증 수단 이메일로 변경
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updateMemberAuthStatus
	 * @date : 2019. 5. 10.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 10.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param reqEmailAuthVO
	 * @return
	 */
	public ReqEmailAuthVO updateMemberAuthStatus(ReqEmailAuthVO reqEmailAuthVO);

	/**
	 * <pre>
	 * 1. 개요 : 사용자 인증레벨 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectMemberStatCd
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
	public int selectMemberStatCd(Map<String, Object> param);

}
