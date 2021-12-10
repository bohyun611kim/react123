/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr002.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.member.mbr002.dao.YahobitLoginDAO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.AuthInfoVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.LoginMatchResultVO;
import kr.co.coinis.webserver.yahobit.member.mbr002.vo.ReqLoginVO;

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
@Repository("yahobitLoginDAO")
public class YahobitLoginDAOImpl extends AbstractDefaultMapper implements YahobitLoginDAO {

	@Override
	public String getNamespace() {
		return YahobitLoginDAO.NAMESPACE;
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
	public Map<String, Object> updateLastLogin(Map<String, Object> param) {
		getSqlSession().selectOne(this.getNamespace() + ".updateLastLogin", param);
		return param;
	}
	
	@Override
	public AuthInfoVO selectUserAuthInfo(AuthCodeVO authCodeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectUserAuthInfo", authCodeVO);
	}
	
	@Override
	public AuthCodeVO procInsertAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procInsertAuthCode", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procCheckAuthCode", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public int updateFailCnt(Map<String, Object> param) {
		return getSqlSession().update(this.getNamespace() + ".updateFailCnt", param);
	}
	
	@Override
	public int selectFailCnt(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectFailCnt", param);
	}
	
	@Override
	public FailCntVO procSetFailCnt(FailCntVO failCntVO) {
		getSqlSession().update(this.getNamespace() + ".procSetFailCnt", failCntVO);
		return failCntVO;
	}
}
