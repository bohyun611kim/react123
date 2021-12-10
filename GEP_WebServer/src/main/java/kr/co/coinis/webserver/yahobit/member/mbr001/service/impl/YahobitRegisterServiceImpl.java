/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr001.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.member.mbr001.dao.YahobitRegisterDAO;
import kr.co.coinis.webserver.yahobit.member.mbr001.service.YahobitRegisterService;
import kr.co.coinis.webserver.yahobit.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.yahobit.member.mbr001.vo.ReqInsertMemberInfoVO;

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
@Service("yahobitRegisterService")
public class YahobitRegisterServiceImpl implements YahobitRegisterService {

	@Resource(name="yahobitRegisterDAO")
	private YahobitRegisterDAO yahobitRegisterDAO;
	
	
	@Override
	public ResultVO doRegist(ReqInsertMemberInfoVO reqInsertMemberInfoVO) throws Exception {
		ResultVO result = new ResultVO();
		
		// 유저 정보 저장
		reqInsertMemberInfoVO = yahobitRegisterDAO.insertMemberInfo(reqInsertMemberInfoVO);
		
		// 유저 정보 저장 성공 시
		if(reqInsertMemberInfoVO.getRtnCd() == 0) {
			String regDt = yahobitRegisterDAO.selectJoinDt(reqInsertMemberInfoVO);
			reqInsertMemberInfoVO.setRegDt(regDt);
			
			// 유저 확장 정보 저장
			reqInsertMemberInfoVO = yahobitRegisterDAO.insertMemberInfoExtra(reqInsertMemberInfoVO);
			
			// 유저 확장 정보 저장 성공 후 
			if(reqInsertMemberInfoVO.getRtnCd() == 0 && reqInsertMemberInfoVO.getUserTypeCd() == 2) {
				// 법인 회원 정보 저장
				yahobitRegisterDAO.insertMemberCorpInfo(reqInsertMemberInfoVO);
			} else if(reqInsertMemberInfoVO.getRtnCd() != 0) {
				throw new Exception(reqInsertMemberInfoVO.getRtnMsg());
			}
		} else {
			result.setRtnCd(reqInsertMemberInfoVO.getRtnCd());
			result.setRtnMsg(reqInsertMemberInfoVO.getRtnMsg());
		}
		
		return result;
	}
	
	@Override
	public ResultVO registerEmailAuth(ReqEmailAuthVO reqEmailAuthVO) {
		
		ResultVO result = new ResultVO();
		
		// key 값 검사
		AuthCodeVO authCodeVO = new AuthCodeVO();
		authCodeVO.setExchangeId(reqEmailAuthVO.getExchangeId());
		authCodeVO.setUserId(reqEmailAuthVO.getUserId());
		authCodeVO.setAuthPurposeCd(1);
		authCodeVO.setAuthMeansCd(3);
		authCodeVO.setAuthMeansKeyVal(reqEmailAuthVO.getUserId());
		authCodeVO.setAuthCd(reqEmailAuthVO.getKey());
		authCodeVO = yahobitRegisterDAO.procEmailAuth(authCodeVO);
		
		// 결과가 참일 경우 회원 상태 코드 가입상태로 변경
		if(authCodeVO.getRtnCd() == 0) {
			reqEmailAuthVO = yahobitRegisterDAO.updateMemberStatus(reqEmailAuthVO);
			
			if(reqEmailAuthVO.getRtnCd() != 0) {
				result.setRtnCd(reqEmailAuthVO.getRtnCd());
				result.setRtnMsg(reqEmailAuthVO.getRtnMsg());
			}
		} else {
			result.setRtnCd(authCodeVO.getRtnCd());
			result.setRtnMsg(authCodeVO.getRtnMsg());
		}
		
		return result;
	}
	
	@Override
	public int selectMemberStatCd(Map<String, Object> param) {
		return yahobitRegisterDAO.selectMemberStatCd(param);
	}
}
