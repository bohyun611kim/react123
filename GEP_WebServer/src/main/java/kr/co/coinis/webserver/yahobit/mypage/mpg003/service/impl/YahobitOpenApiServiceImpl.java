/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg003.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.dao.YahobitOpenApiDAO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.service.YahobitOpenApiService;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.CreateApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.ModApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.OpenApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.UserAuthInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg003.service.impl 
 *    |_ YahobitOpenApiServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 7. 9. 오후 2:34:29
 * @version : 
 * @author : kangn
 */
@Service("yahobitOpenApiService")
public class YahobitOpenApiServiceImpl implements YahobitOpenApiService {

	@Resource(name="yahobitOpenApiDAO")
	private YahobitOpenApiDAO yahobitOpenApiDAO;
	
	@Override
	public List<OpenApiVO> selectApiList(ReqUserInfoVO reqUserInfoVO) throws Exception {
		return yahobitOpenApiDAO.selectApiList(reqUserInfoVO);
	}
	
	@Override
	public AuthCodeVO requestSmsCode(AuthCodeVO authCodeVO) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", authCodeVO.getExchangeId());
		param.put("userId", authCodeVO.getUserId());
		
		UserAuthInfoVO userAuthInfoVO = yahobitOpenApiDAO.selectUserAuthInfo(param);
		
		if(userAuthInfoVO.getMobile() != null && !"".equals(userAuthInfoVO.getMobile())) {
			authCodeVO.setAuthMeansKeyVal(userAuthInfoVO.getMobile());
			authCodeVO = yahobitOpenApiDAO.procInsertAuthCode(authCodeVO);
		} else {
			authCodeVO.setRtnCd(-5015);
			authCodeVO.setRtnMsg("SMS 인증을 사용하고 있지 않습니다.");
		}
		
		return authCodeVO;
	}
	
	@Override
	public OpenApiVO createApi(CreateApiVO createApiVO) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", createApiVO.getExchangeId());
		param.put("userId", createApiVO.getUserId());
		
		UserAuthInfoVO userAuthInfoVO = yahobitOpenApiDAO.selectUserAuthInfo(param);
		
		OpenApiVO openApiVO = new OpenApiVO();
		
		// 인증 단계 확인
		if(userAuthInfoVO.getAuthMeansCd() == 1 || userAuthInfoVO.getAuthMeansCd() == 2) {
			AuthCodeVO authCodeVO = new AuthCodeVO();
			
			// otp or sms 번호 확인
			if(userAuthInfoVO.getAuthMeansCd() == 1) {
				// otp 인증
				if(!"".equals(userAuthInfoVO.getOtpKey())) {
					if(!GoogleOTP.checkCode(createApiVO.getAuthCode(), userAuthInfoVO.getOtpKey())) {
						openApiVO.setRtnCd(-5016);
						openApiVO.setRtnMsg("인증코드가 일치하지 않습니다");
					}
				} else {
					openApiVO.setRtnCd(-5017);
					openApiVO.setRtnMsg("OTP 인증을 사용하고 있지 않습니다");
				}
			} else if(userAuthInfoVO.getAuthMeansCd() == 2) {
				// sms 인증
				authCodeVO.setExchangeId(createApiVO.getExchangeId());
				authCodeVO.setUserId(createApiVO.getUserId());
				authCodeVO.setAuthPurposeCd(5);
				authCodeVO.setAuthMeansCd(2);
				authCodeVO.setAuthMeansKeyVal(userAuthInfoVO.getMobile());
				authCodeVO.setAuthCd(createApiVO.getAuthCode());
				
				authCodeVO = yahobitOpenApiDAO.procCheckAuthCode(authCodeVO);
				
				if(authCodeVO.getRtnCd() != 0) {
					openApiVO.setRtnCd(-5016);
					openApiVO.setRtnMsg("인증코드가 일치하지 않습니다");
				}
			}
			
			// 저장 처리
			if(openApiVO.getRtnCd() == 0) {
				String clientId = UUID.randomUUID().toString().substring(0, 30);
				clientId += UUID.randomUUID().toString().substring(0, 30);
				
				String secretKey = UUID.randomUUID().toString().substring(0, 30);
				secretKey += UUID.randomUUID().toString().substring(0, 30);
				
				createApiVO.setClientId(clientId);
				createApiVO.setSecretKey(secretKey);
				
				int cnt = yahobitOpenApiDAO.insertApiInfo(createApiVO);
				
				if(cnt != 1) {
					throw new Exception("api 데이터 생성에 실패하였습니다.");
				} else {
					openApiVO = yahobitOpenApiDAO.selectApiInfo(createApiVO);
				}
			}
		} else {
			openApiVO.setRtnCd(-5050);
			openApiVO.setRtnMsg("잘못된 접근입니다.");
		}
		
		return openApiVO;
	}
	
	@Override
	public int updateApiInfo(ModApiVO modApiVO) throws Exception {
		int cnt = yahobitOpenApiDAO.updateApiInfo(modApiVO);
		
		if(cnt != 1) {
			throw new Exception("api 수정에 실패하였습니다.");
		}
		return cnt;
	}
	
	@Override
	public int deleteApiInfo(ModApiVO modApiVO) throws Exception {
		int cnt = yahobitOpenApiDAO.deleteApiInfo(modApiVO);
				
		if(cnt != 1) {
			throw new Exception("api 삭제에 실패하였습니다.");
		}
		return cnt;
	}
}
