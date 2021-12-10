/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.mypage.mpg001.dao.InfoDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.service.InfoService;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.MemberProfileInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.ReqUpdateCodeVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

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
@Service("infoService")
public class InfoServiceImpl implements InfoService {

	@Resource(name="infoDAO")
	private InfoDAO infoDAO;
	
	@Override
	public ResultVO updateMarketingAgree(ReqUpdateCodeVO param) {
		
		int cnt = infoDAO.updateMarketingAgree(param);
				
		ResultVO result = new ResultVO();
		
		if(cnt != 1) {
			result.setRtnCd(-1);
			result.setRtnMsg("마켓팅 수신동의 수정에 실패하였습니다");
		}
		
		return result;
	}

	@Override
	public MemberProfileInfoVO selectMemberProfileInfo(ExchangeIDUserIDPairVO param) {
		return infoDAO.selectMemberProfileInfo(param);
	}

	@Override
	public ResultVO updateAuthMeansCD(ReqUpdateCodeVO param) {
		
		int cnt = infoDAO.updateAuthMeansCD(param);
		
		ResultVO result = new ResultVO();
		
		if(cnt != 1 ) {
			result.setRtnCd(-1);
			result.setRtnMsg("보안 인증 수단 변경에 실패하였습니다.");
			
		}
		
		return result;
	}
	
}
