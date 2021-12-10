/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.CountryInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.SecuritySetStatusVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.service 
 *    |_ SecuritySetupService.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오후 3:14:47
 * @version : 
 * @author : yeonseoo
 */
public interface SecuritySetupService {
	
	/* 회원의 보안 인증 설정 상태 조회 */
	public SecuritySetStatusVO selectSecuritySetStatus(ExchangeIDUserIDPairVO param);
	
	/* 국가 영문명/국가 번호 조회 */
	public List<CountryInfoVO> selectCountryInfo();
	
	/* 인증 코드 요청 후 SMS 전송*/
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	
	/* 인증 코드 확인 후 보안 설정 상태 업데이트 */
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException;
	
	/* SMS 인증 설정상태 업데이트 */
	public Map<String, Object> call_PR_WAS_SET_SMS_AUTH(Map<String, Object> paramMap) throws SQLException;
	
	/* OTP 인증 설정상태 업데이트 */
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map<String, Object> paramMap) throws SQLException;
	
	/* 로그인 비밀번호 확인 */
	public int checkPWMatch(Map<String, Object> paramMap);

}
