/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr002.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.security.spring.security.handler.SignInSuccessHandler;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.holdport.member.mbr002.dao.HoldportLoginDAO;
import kr.co.coinis.webserver.holdport.member.mbr002.service.HoldportLoginService;
import kr.co.coinis.webserver.holdport.member.mbr002.vo.AuthInfoVO;
import kr.co.coinis.webserver.holdport.member.mbr002.vo.LoginMatchResultVO;
import kr.co.coinis.webserver.holdport.member.mbr002.vo.ReqLoginVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.service.impl
 *    |_ LoginServiceImpl.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:18
 * @version :
 * @author : Seongcheol
 */
@Service("holdportLoginService")
public class HoldportLoginServiceImpl implements HoldportLoginService {

	private static final Logger logger = LoggerFactory.getLogger(HoldportLoginServiceImpl.class);

	@Resource(name="holdportLoginDAO")
	private HoldportLoginDAO holdportLoginDAO;

	@Override
	public ResultVO selectLoginMatch(ReqLoginVO reqLoginVO) {

		ResultVO result = new ResultVO();

		if(reqLoginVO.getPublicIp() != null && reqLoginVO.getPublicIp().length() > 19) {
			reqLoginVO.setPublicIp(reqLoginVO.getPublicIp().substring(0, 19));
		}

		/*Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", reqLoginVO.getExchangeId());
		param.put("userId", reqLoginVO.getId());
		param.put("lastLoginIpAddr", reqLoginVO.getPublicIp());
		param.put("lastLoginAppVer", reqLoginVO.getAppInfo());
		param.put("os", reqLoginVO.getOs());

		param = holdportLoginDAO.updateLastLogin(param);

		if((int) param.get("rtnCd") != 0) {
			logger.error(param.get("rtnMsg").toString());
		}*/


		Map<String, Object> fail = new HashMap<>();
		fail.put("exchangeId", reqLoginVO.getExchangeId());
		fail.put("userId", reqLoginVO.getId());
		int failCnt = 0;

		logger.info("selectLoginMatch Login exchangeId["+reqLoginVO.getExchangeId()+"],userId["+reqLoginVO.getId()+"]");

		try {
			failCnt = holdportLoginDAO.selectFailCnt(fail);
		} catch (NullPointerException e) {
			logger.info("잘못된 ID로의 로그인 시도");
		}

		if(failCnt > 4) {
			result.setRtnCd(-5095);
			result.setRtnMsg("비밀번호를 연속 5회 이상 틀리셨습니다.<br>비밀번호 초기화 후 다시 시도하시기 바랍니다.");
		} else {
			LoginMatchResultVO vo = holdportLoginDAO.selectLoginMatch(reqLoginVO);

			// 회원 상태 코드에 따른 메시지 분기 처리
			switch(vo.getMemberStatCd()) {
			case 1:		// 이메일 미인증 상태
				result.setRtnCd(-5011);
				result.setRtnMsg("가입 진행중인 계정입니다");
				break;
			case 2:		// 정상 로그인
				result.setRtnCd(vo.getAuthMeansCd());

				// 마지막 로그인 일시 업데이트
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", reqLoginVO.getExchangeId());
				param.put("userId", reqLoginVO.getId());
				param.put("lastLoginIpAddr", reqLoginVO.getPublicIp());
				param.put("lastLoginAppVer", reqLoginVO.getAppInfo());
				param.put("os", reqLoginVO.getOs());
				param = holdportLoginDAO.updateLastLogin(param);
				break;
			case 3:		// 사용 중지 상태
				result.setRtnCd(-5012);
				result.setRtnMsg("사용 중지중인 계정입니다");
				break;
			case -1:	// 탈퇴한 상태
				result.setRtnCd(-5013);
				result.setRtnMsg("탈퇴한 계정입니다");
				break;
			default:	// 아이디 혹은 비밀번호 미일치
				holdportLoginDAO.updateFailCnt(fail);

				result.setRtnCd(-5014);
				result.setRtnMsg("회원정보가 일치하지 않습니다<br>비밀번호를 연속 5회 이상 틀릴경우 접속이 제한됩니다.");
				break;
			}
		}

		return result;
	}

	@Override
	public CustomUserDetails selectMemberInfo(ReqLoginVO reqLoginVO) {
		return holdportLoginDAO.selectMemberInfo(reqLoginVO);
	}

	@Override
	public AuthCodeVO requestSmsCode(AuthCodeVO authCodeVO) {

		AuthInfoVO authInfoVO = holdportLoginDAO.selectUserAuthInfo(authCodeVO);

		if(authInfoVO.getAuthMeansCd() == 2) {
			authCodeVO.setAuthMeansKeyVal(authInfoVO.getMobile());
			authCodeVO = holdportLoginDAO.procInsertAuthCode(authCodeVO);
		} else {
			authCodeVO.setRtnCd(-5015);
			authCodeVO.setRtnMsg("SMS 인증을 사용하고 있지 않습니다");
		}

		return authCodeVO;
	}

	@Override
	public AuthCodeVO confirmSmsAuthCode(AuthCodeVO authCodeVO) {

		AuthInfoVO authInfoVO = holdportLoginDAO.selectUserAuthInfo(authCodeVO);

		if(authInfoVO.getAuthMeansCd() == 2) {
			authCodeVO.setAuthMeansKeyVal(authInfoVO.getMobile());
			authCodeVO = holdportLoginDAO.procCheckAuthCode(authCodeVO);
		} else {
			authCodeVO.setRtnCd(-5015);
			authCodeVO.setRtnMsg("SMS 인증을 사용하고 있지 않습니다.");
		}

		return authCodeVO;
	}

	@Override
	public ResultVO confirmOtpAuthCode(AuthCodeVO authCodeVO) {

		ResultVO result = new ResultVO();

		AuthInfoVO authInfoVO = holdportLoginDAO.selectUserAuthInfo(authCodeVO);

		if(authInfoVO.getAuthMeansCd() == 1 && !"".equals(authInfoVO.getOtpKeyVal())) {

			if(!GoogleOTP.checkCode(authCodeVO.getAuthCd(), authInfoVO.getOtpKeyVal())) {
				result.setRtnCd(-5016);
				result.setRtnMsg("인증코드가 일치하지 않습니다");
			}
		} else {
			result.setRtnCd(-5017);
			result.setRtnMsg("OTP 인증을 사용하고 있지 않습니다");
		}

		return result;

	}

	@Override
	public FailCntVO procSetFailCnt(FailCntVO failCntVO) {
		return holdportLoginDAO.procSetFailCnt(failCntVO);
	}

}
