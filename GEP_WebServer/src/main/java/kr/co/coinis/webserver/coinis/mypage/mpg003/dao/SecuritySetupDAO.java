/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.CountryInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.SecuritySetStatusVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.dao 
 *    |_ SecuritySetupDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:11:47
 * @version : 
 * @author : yeonseoo
 */
public interface SecuritySetupDAO {

	public static final String NAMESPACE = SecuritySetupDAO.class.getName();
	
	/**
	 * 보안 인증 설정 상태 조회
	 * <pre>
	 * 1. 개요 : 회원의 보안 인증 설정 상태 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSecuritySetStatus
	 * @date : 2019. 5. 13.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  13.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public SecuritySetStatusVO selectSecuritySetStatus(ExchangeIDUserIDPairVO param);
	
	/**
	 * 국가 영문명/국가 번호 조회
	 * <pre>
	 * 1. 개요 : 사용 중인 국가 영문명, 국가 번호 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCountryInfo
	 * @date : 2019. 5. 13.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  13.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<CountryInfoVO> selectCountryInfo();
	
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	
	public Map<String, Object> call_PR_WAS_SET_SMS_AUTH(Map<String, Object> paramMap) throws SQLException;
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map<String, Object> paramMap) throws SQLException;
	
	/**
	 * 로그인 비밀번호 검증
	 * <pre>
	 * 1. 개요 : 로그인 비밀번호 검증
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkPWMatch
	 * @date : 2019. 5. 15.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  15.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public int checkPWMatch(Map<String, Object>  paramMap);
}
