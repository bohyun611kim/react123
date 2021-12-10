/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.auth.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.auth.dao.AuthDAO;
import kr.co.coinis.webserver.common.auth.vo.ReqChekAuthCodeVO;
import kr.co.coinis.webserver.common.auth.vo.ReqInsertAuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.common.auth.dao.impl 
 *    |_ AuthDao.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 3:08:36
 * @version : 
 * @author : Seongcheol
 */
@Repository("authDAO")
public class AuthDAOImpl extends AbstractDefaultMapper implements AuthDAO {

	@Override
	public String getNamespace() {
		return AuthDAO.NAMESPACE;
	}

	@Override
	public ResultVO gnrtVrfctnCd(ReqInsertAuthCodeVO reqInsertAuthCodeVO) {
		// TODO Auto-generated method stub
		return (ResultVO) getSqlSession().selectOne(this.getNamespace() + "insertAuthCode", reqInsertAuthCodeVO);
	}
	
	@Override
	public ResultVO cnfrmVrfctnCd(ReqChekAuthCodeVO ReqChekAuthCodeVO) {
		// TODO Auto-generated method stub
		return (ResultVO) getSqlSession().selectOne(this.getNamespace() + "chekAuthCode", ReqChekAuthCodeVO);
	}
}
