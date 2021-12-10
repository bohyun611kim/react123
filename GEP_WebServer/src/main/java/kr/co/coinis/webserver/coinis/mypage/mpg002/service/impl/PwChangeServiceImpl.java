/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.mypage.mpg002.dao.PwChangeDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg002.service.PwChangeService;
import kr.co.coinis.webserver.coinis.mypage.mpg002.vo.ReqChangePwVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * 
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg002.service.impl 
 *    |_ PwChangeServiceImpl.java
 * 
 * </pre>
 * 
 * @date : 2019. 3. 21. 오전 11:49:23
 * @version :
 * @author : Seongcheol
 */
@Service("pwChangeService")
public class PwChangeServiceImpl implements PwChangeService {

	@Resource(name = "pwChangeDAO")
	private PwChangeDAO pwChangeDAO;

	@Override
	public ResultVO PwChange(ReqChangePwVO reqChangePwVO) {

		ResultVO result = new ResultVO();
		// 기존 비밀번호 일치 여부확인
		int cnt = pwChangeDAO.reqPwMatch(reqChangePwVO);

		// 일치할 경우
		if (cnt == 1) {
			// 신규 비밀번호로 업데이트
			pwChangeDAO.reqPwChange(reqChangePwVO);

			if (reqChangePwVO.getRtnCd() != 0) {
				result.setRtnCd(reqChangePwVO.getRtnCd());
				result.setRtnMsg(reqChangePwVO.getRtnMsg());
			}
		} else {
			result.setRtnCd(-1);
			result.setRtnMsg("기존 비밀번호가 일치하지 않습니다");
		}

		return result;
	}
}