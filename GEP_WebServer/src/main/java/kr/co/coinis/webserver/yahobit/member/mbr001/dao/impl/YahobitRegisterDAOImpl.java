/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr001.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.member.mbr001.dao.YahobitRegisterDAO;
import kr.co.coinis.webserver.yahobit.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.yahobit.member.mbr001.vo.ReqInsertMemberInfoVO;


/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.dao.impl 
 *    |_ RegisterDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 4:01:17
 * @version : 
 * @author : Seongcheol
 */
@Repository("yahobitRegisterDAO")
public class YahobitRegisterDAOImpl extends AbstractDefaultMapper implements YahobitRegisterDAO {
	
	//private static final Logger logger = LoggerFactory.getLogger(RegisterDAO.class);
	
	@Override
	public String getNamespace() {
		return YahobitRegisterDAO.NAMESPACE;
	}
	
	@Override
	public ReqInsertMemberInfoVO insertMemberInfo(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		getSqlSession().update(this.getNamespace() + ".insertMemberInfo", reqInsertMemberInfoVO);
		return reqInsertMemberInfoVO;
	}
	
	@Override
	public ReqInsertMemberInfoVO insertMemberInfoExtra(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		getSqlSession().update(this.getNamespace() + ".insertMemberInfoExtra", reqInsertMemberInfoVO);
		
		return reqInsertMemberInfoVO;
	}
	
	@Override
	public String selectJoinDt(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectJoinDt", reqInsertMemberInfoVO);
	}
	
	@Override
	public ReqInsertMemberInfoVO insertMemberCorpInfo(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		getSqlSession().update(this.getNamespace() + ".insertMemberCorpInfo", reqInsertMemberInfoVO);
		return reqInsertMemberInfoVO;
	}
	
	@Override
	public AuthCodeVO procEmailAuth(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procEmailAuth", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public ReqEmailAuthVO updateMemberStatus(ReqEmailAuthVO reqEmailAuthVO) {
		getSqlSession().update(this.getNamespace() + ".updateMemberStatus", reqEmailAuthVO);
		return reqEmailAuthVO;
	}
	
	@Override
	public ReqEmailAuthVO updateMemberAuthStatus(ReqEmailAuthVO reqEmailAuthVO) {
		getSqlSession().update(this.getNamespace() + ".updateMemberAuthStatus", reqEmailAuthVO);
		return null;
	}
	
	@Override
	public int selectMemberStatCd(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMemberStatCd", param);
	}

}
