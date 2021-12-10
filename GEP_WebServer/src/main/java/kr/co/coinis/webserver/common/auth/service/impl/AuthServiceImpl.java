/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.auth.service.impl;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.auth.dao.AuthDAO;
import kr.co.coinis.webserver.common.auth.service.AuthService;
import kr.co.coinis.webserver.common.auth.vo.ReqChekAuthCodeVO;
import kr.co.coinis.webserver.common.auth.vo.ReqInsertAuthCodeVO;
import kr.co.coinis.webserver.common.utils.AuthCode;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.auth.service.impl 
 *    |_ AuthServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 3:08:22
 * @version : 
 * @author : Seongcheol
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Resource(name="authDAO")
	private AuthDAO authDAO;
	
	@Override
	public ResultVO gnrtVrfctnCd(ReqInsertAuthCodeVO reqInsertAuthCodeVO) throws Exception {
		ResultVO result = new ResultVO();
		
		reqInsertAuthCodeVO.setAuthCode(AuthCode.getAuthCode(6));
		
		Set<ConstraintViolation<ReqInsertAuthCodeVO>> constraintViolation = validator.validate(reqInsertAuthCodeVO);
		
		if(constraintViolation.size() == 0) {
			//result = authDAO.gnrtVrfctnCd(reqInsertAuthCodeVO);
		} else {
			for(ConstraintViolation<ReqInsertAuthCodeVO> temp : constraintViolation) {
				result.setRtnCd(-1);
				result.setRtnMsg("Bad parameter request");
			}
		}
		
		return result;
	}
	
	@Override
	public ResultVO cnfrmVrfctnCd(ReqChekAuthCodeVO ReqChekAuthCodeVO) {
		// TODO Auto-generated method stub
		return null;
	}
}
