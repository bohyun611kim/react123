/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.member.mbr001.dao.RegisterDAO;
import kr.co.coinis.webserver.coinis.member.mbr001.service.RegisterService;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.CountryCodeVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqInsertMemberInfoVO;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.service.impl 
 *    |_ RegisterServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 4:00:56
 * @version : 
 * @author : Seongcheol
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	@Resource(name="registerDAO")
	private RegisterDAO registerDAO;
	
	@Override
	public List<CountryCodeVO> selectCountryCode() {
		return registerDAO.selectCountryCode();
	}
	
	@Override
	public ResultVO doRegist(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		
		ResultVO result = new ResultVO();
		
		reqInsertMemberInfoVO = registerDAO.insertMemberInfo(reqInsertMemberInfoVO);
		
		if(reqInsertMemberInfoVO.getRtnCd() == 0) {
			String regDt = registerDAO.selectJoinDt(reqInsertMemberInfoVO);
			reqInsertMemberInfoVO.setRegDt(regDt);
			
			reqInsertMemberInfoVO = registerDAO.insertMemberInfoExtra(reqInsertMemberInfoVO);
			
			if(reqInsertMemberInfoVO.getRtnCd() == 0 && reqInsertMemberInfoVO.getUserTypeCd() == 2) {
				registerDAO.insertMemberCorpInfo(reqInsertMemberInfoVO);
			}
			
			//
		} 
		
		result.setRtnCd(reqInsertMemberInfoVO.getRtnCd());
		result.setRtnMsg(reqInsertMemberInfoVO.getRtnMsg());
		
		return result;
	}
	
	@Override
	public ResultVO registerEmailAuth(ReqEmailAuthVO reqEmailAuthVO) {
		return null;
	}
}
