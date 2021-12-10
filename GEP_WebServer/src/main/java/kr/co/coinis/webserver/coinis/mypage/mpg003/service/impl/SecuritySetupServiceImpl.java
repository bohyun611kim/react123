/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.mypage.mpg003.dao.SecuritySetupDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.service.SecuritySetupService;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.CountryInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg003.vo.SecuritySetStatusVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.service.impl 
 *    |_ SecuritySetupServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:14:16
 * @version : 
 * @author : Seongcheol
 */
@Service("securitySetupService")
public class SecuritySetupServiceImpl implements SecuritySetupService {

	@Resource(name="securitySetupDAO")
	private SecuritySetupDAO securitySetupDAO;

	@Override
	public SecuritySetStatusVO selectSecuritySetStatus(ExchangeIDUserIDPairVO param) {
		return securitySetupDAO.selectSecuritySetStatus(param);
	}

	@Override
	public List<CountryInfoVO> selectCountryInfo() {
		return securitySetupDAO.selectCountryInfo();
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		return securitySetupDAO.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		return securitySetupDAO.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_SET_SMS_AUTH(Map<String, Object> paramMap) throws SQLException {
		return securitySetupDAO.call_PR_WAS_SET_SMS_AUTH(paramMap);
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map<String, Object> paramMap) throws SQLException {
		return securitySetupDAO.call_PR_WAS_SET_OTP_AUTH(paramMap);
	}

	@Override
	public int checkPWMatch(Map<String, Object> paramMap) {
		return securitySetupDAO.checkPWMatch(paramMap);
	}
}
