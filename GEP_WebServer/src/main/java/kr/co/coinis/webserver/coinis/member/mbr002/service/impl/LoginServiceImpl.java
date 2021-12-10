/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.member.mbr002.dao.LoginDAO;
import kr.co.coinis.webserver.coinis.member.mbr002.service.LoginService;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.LoginMatchResultVO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.ReqLoginVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.vo.ResultVO;

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
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;
	
	@Override
	public ResultVO selectLoginMatch(ReqLoginVO reqLoginVO) {
		ResultVO result = new ResultVO();
		
		LoginMatchResultVO vo = loginDAO.selectLoginMatch(reqLoginVO);
		
		// 회원 상태 코드에 따른 메시지 분기 처리 
		switch(vo.getMemberStatCd()) {
		case 1:		// 이메일 미인증 상태
			result.setRtnCd(-1);
			result.setRtnMsg("가입 진행중인 계정입니다.");
			break;
		case 2:		// 정상 로그인
			result.setRtnCd(vo.getAuthMeansCd());
			break;
		case 3:		// 사용 중지 상태
			result.setRtnCd(-1);
			result.setRtnMsg("사용 중지중인 계정입니다.");
			break;
		case -1:	// 탈퇴한 상태
			result.setRtnCd(-1);
			result.setRtnMsg("탈퇴한 계정입니다.");
			break;
		default:	// 아이디 혹은 비밀번호 미일치
			result.setRtnCd(-1);
			result.setRtnMsg("회원정보가 일치하지 않습니다");
			break;
		}
		
		return result;
	}
	
	@Override
	public CustomUserDetails selectMemberInfo(ReqLoginVO reqLoginVO) {
		return loginDAO.selectMemberInfo(reqLoginVO);
	}
	
	@Override
	public FailCntVO procSetFailCnt(FailCntVO failCntVO) {
		return loginDAO.procSetFailCnt(failCntVO);
	}
}
