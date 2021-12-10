/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.member.mbr002.dao.LoginDAO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.LoginMatchResultVO;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.ReqLoginVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.dao.impl 
 *    |_ LoginDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:03
 * @version : 
 * @author : Seongcheol
 */
@Repository("loginDAO")
public class LoginDAOImpl extends AbstractDefaultMapper implements LoginDAO {

	@Override
	public String getNamespace() {
		return LoginDAO.NAMESPACE;
	}

	@Override
	public LoginMatchResultVO selectLoginMatch(ReqLoginVO reqLoginVO) {
		return (LoginMatchResultVO) getSqlSession().selectOne(this.getNamespace() + ".selectLoginMatch", reqLoginVO);
	}
	
	@Override
	public CustomUserDetails selectMemberInfo(ReqLoginVO reqLoginVO) {
		return (CustomUserDetails) getSqlSession().selectOne(this.getNamespace() + ".selectMemberInfo", reqLoginVO);
	}
	
	@Override
	public FailCntVO procSetFailCnt(FailCntVO failCntVO) {
		getSqlSession().update(this.getNamespace() + ".procSetFailCnt", failCntVO);
		return failCntVO;
	}
}
