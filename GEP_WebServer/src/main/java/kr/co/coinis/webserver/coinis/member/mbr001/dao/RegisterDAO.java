/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.member.mbr001.vo.CountryCodeVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqInsertMemberInfoVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.dao 
 *    |_ RegisterDAO.java
 *  * </pre>
 * @date : 2019. 3. 25. 오후 4:01:12
 * @version : 
 * @author : Seongcheol
 */
public interface RegisterDAO {

	public static final String NAMESPACE = RegisterDAO.class.getName();
	
	/**
	 * 국가코드 조회
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryCode
	 * @date : 2019. 3. 29.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 29.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<CountryCodeVO> selectCountryCode();
	
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
	public ResultVO updateEmailAuth(ReqEmailAuthVO reqEmailAuthVO);
}
