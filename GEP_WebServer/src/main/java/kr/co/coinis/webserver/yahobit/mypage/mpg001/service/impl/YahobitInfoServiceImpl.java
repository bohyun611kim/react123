/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg001.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.dao.YahobitInfoDAO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.service.YahobitInfoService;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqAuthLevelVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.UserInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.service.impl 
 *    |_ InfoServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:23:04
 * @version : 
 * @author : Seongcheol
 */
@Service("yahobitInfoService")
public class YahobitInfoServiceImpl implements YahobitInfoService {

	@Resource(name="yahobitInfoDAO")
	private YahobitInfoDAO yahobitInfoDAO;
	
	@Override
	public UserInfoVO selectUserInfo(ReqUserInfoVO reqUserInfoVO) {
		return yahobitInfoDAO.selectUserInfo(reqUserInfoVO);
	}
	
	@Override
	public RecommendInfoVO selectRecommendInfo(ReqUserInfoVO reqUserInfoVO) {
		return yahobitInfoDAO.selectRecommendInfo(reqUserInfoVO);
	}
	
	@Override
	public ResultVO changePassword(PwChangeVO pwChangeVO) {
		ResultVO result = new ResultVO();
		
		// 기존 비밀번호 일치 여부확인
		int cnt = yahobitInfoDAO.checkPassword(pwChangeVO);
		
		// 일치할 경우
		if(cnt == 1) {
			// 신규 비밀번호로 업데이트
			pwChangeVO = yahobitInfoDAO.procChangePassword(pwChangeVO);
			
			if(pwChangeVO.getRtnCd() != 0) {
				result.setRtnCd(pwChangeVO.getRtnCd());
				result.setRtnMsg(pwChangeVO.getRtnMsg());
			}
		} else {
			result.setRtnCd(-5023);
			result.setRtnMsg("기존 비밀번호가 일치하지 않습니다");
		}
		
		return result;
	}
	
	@Override
	public ResultVO changeAuthMeansCdToSms(ReqAuthMeansVO reqAuthMeansVO) {
		
		ResultVO result = new ResultVO();
		
		ReqUserInfoVO reqUserInfoVO = new ReqUserInfoVO();
		reqUserInfoVO.setExchangeId(reqAuthMeansVO.getExchangeId());
		reqUserInfoVO.setUserId(reqAuthMeansVO.getUserId());
		
		UserInfoVO userInfoVO = yahobitInfoDAO.selectUserInfo(reqUserInfoVO);
		
		if(userInfoVO.getMobile() != null && !"".equals(userInfoVO.getMobile())) {
			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(reqAuthMeansVO.getExchangeId());
			authCodeVO.setUserId(reqAuthMeansVO.getUserId());
			authCodeVO.setAuthPurposeCd(2);
			authCodeVO.setAuthMeansCd(2);
			authCodeVO.setAuthCd(reqAuthMeansVO.getAuthCode());
			authCodeVO.setAuthMeansKeyVal(userInfoVO.getMobile());
			authCodeVO = yahobitInfoDAO.procCheckAuthCode(authCodeVO);
			
			if(authCodeVO.getRtnCd() == 0) {
				reqAuthMeansVO = yahobitInfoDAO.procChangeAuthMeansCd(reqAuthMeansVO);
				
				if(reqAuthMeansVO.getRtnCd() != 0) {
					result.setRtnCd(reqAuthMeansVO.getRtnCd());
					result.setRtnMsg(reqAuthMeansVO.getRtnMsg());
				}
			} else {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
		} else {
			result.setRtnCd(-5015);
			result.setRtnMsg("SMS 인증을 사용하고 있지 않습니다.");
		}
		
		return result;
	}
	
	@Override
	public ResultVO changeAuthMeansCdToOtp(ReqAuthMeansVO reqAuthMeansVO) {
		
		ResultVO result = new ResultVO();
		
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", reqAuthMeansVO.getExchangeId());
		param.put("userId", reqAuthMeansVO.getUserId());
		
		String otpKey = yahobitInfoDAO.selectOtpKey(param);
		
		if(otpKey != null && !"".equals(otpKey)) {
			if(!GoogleOTP.checkCode(reqAuthMeansVO.getAuthCode(), otpKey)) {
				result.setRtnCd(-5016);
				result.setRtnMsg("인증코드가 일치하지 않습니다");
			} else {
				reqAuthMeansVO = yahobitInfoDAO.procChangeAuthMeansCd(reqAuthMeansVO);
				
				if(reqAuthMeansVO.getRtnCd() != 0) {
					result.setRtnCd(reqAuthMeansVO.getRtnCd());
					result.setRtnMsg(reqAuthMeansVO.getRtnMsg());
				}
			}
		} else {
			result.setRtnCd(-5017);
			result.setRtnMsg("OTP 인증을 사용하고 있지 않습니다");
		}
		
		return result;
	}
	
	@Override
	public ResultVO updateMarketingAgree(ReqMarketingAgreeVO reqMarketingAgreeVO) {
		
		int cnt = yahobitInfoDAO.updateMarketingAgree(reqMarketingAgreeVO);
				
		ResultVO result = new ResultVO();
		
		if(cnt != 1) {
			result.setRtnCd(-5021);
			result.setRtnMsg("마케팅 동의 수정에 실패하였습니다");
		}
		
		return result;
	}
	
	@Override
	public boolean checkLastAuthDateTime(Map<String, Object> param) throws NumberFormatException, ParseException {
		boolean result = false;
		
		String lastAuth = yahobitInfoDAO.selectLastAuthDateTime(param);
		
		if(lastAuth != null) {
			int date = Integer.parseInt(lastAuth.substring(0, 8));
			
			if(date < Integer.parseInt(DateUtils.getCustomDate(0))) {
				result = true;
			}
		} else {
			result = true;
		}
		
		return result;
	}
	
	@Override
	public ResultVO updatePhoneNo(KcpAuthVO kcpAuthVO) throws Exception {
		
		ResultVO result = new ResultVO();
		
		int duplication = yahobitInfoDAO.checkMobileDuplication(kcpAuthVO);
		
		if(duplication == 0) {
			// 모바일 사용 여부, 인증레벨 확인
			UserInfoVO userInfoVO = yahobitInfoDAO.selectAuthLevel(kcpAuthVO);
			
			// 미사용 중일 경우(인증 레벨 : 1)
			if(userInfoVO.getAuthLevel() == 1) {
				// 회원 추가 정보 저장
				kcpAuthVO.setRegDt(userInfoVO.getRegDt());
				kcpAuthVO = yahobitInfoDAO.procUpdateMemberInfoExtra(kcpAuthVO);
				
				// 인증 레벨 변경
				if(kcpAuthVO.getRtnCd() == 0) {
					ReqAuthLevelVO reqAuthLevelVO = new ReqAuthLevelVO();
					reqAuthLevelVO.setExchangeId(kcpAuthVO.getExchangeId());
					reqAuthLevelVO.setUserId(kcpAuthVO.getUserId());
					reqAuthLevelVO.setAuthLevel(2);
					reqAuthLevelVO.setMobile(kcpAuthVO.getPhoneNo());
					int cnt = yahobitInfoDAO.updateMemberInfo(reqAuthLevelVO);
					
					if(cnt != 1) {
						throw new Exception("인증 레벨 변경 실패");
					}
					
					// 인증 수단 변경
					/*if(reqAuthLevelVO.getRtnCd() == 0) {
						ReqSmsAuthVO reqSmsAuthVO = new ReqSmsAuthVO();
						reqSmsAuthVO.setExchangeId(kcpAuthVO.getExchangeId());
						reqSmsAuthVO.setUserId(kcpAuthVO.getUserId());
						reqSmsAuthVO.setSmsUseYn(1);
						reqSmsAuthVO.setMobile(kcpAuthVO.getPhoneNo());
						reqSmsAuthVO.setKey2(userInfoVO.getRegDt());
						reqSmsAuthVO = yahobitInfoDAO.procSetSmsAuth(reqSmsAuthVO);
						
						if(reqSmsAuthVO.getRtnCd() != 0) {
							throw new Exception("인증 수단 변경 실패");	
						}
					} else {
						throw new Exception("인증 레벨 변경 실패");
					}*/
				} else {
					result.setRtnCd(kcpAuthVO.getRtnCd());
				}
			} else {
				/*String userName = new String(userInfoVO.getUserName());
				
				if(kcpAuthVO.getUserName().equals(userName)
						&& kcpAuthVO.getBirthDay().equals(userInfoVO.getBirthday())) {
					// 모바일 번호 업데이트
					int cnt = yahobitInfoDAO.updatePhoneNo(kcpAuthVO);
					
					if(cnt != 1) {
						result.setRtnCd(-5024);
						result.setRtnMsg("휴대폰 번호 변경에 실패하였습니다");
					}
				} else {
					result.setRtnCd(-5025);
					result.setRtnMsg("등록된 정보와 일치하지 않습니다");
				}*/
				result.setRtnCd(-5087);
				result.setRtnMsg("이미 휴대폰 본인인증이 완료되었습니다.");
			}
		} else {
			result.setRtnCd(-5067);
			result.setRtnMsg("이미 사용중인 번호 입니다.");
		}
		
		return result;
	}

	@Override
	public Map<String, Object> selectAccessLog(ReqAccessLogVO reqAccessLogVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("size", yahobitInfoDAO.selectAccessLogCnt(reqAccessLogVO));
		result.put("list", yahobitInfoDAO.selectAccessLog(reqAccessLogVO));
		
		return result;
	}

}
