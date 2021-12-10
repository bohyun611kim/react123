/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.member.mbr001.dao.RegisterDAO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.CountryCodeVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqInsertMemberInfoVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

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
@Repository("registerDAO")
public class RegisterDAOImpl extends AbstractDefaultMapper implements RegisterDAO {
	
	//private static final Logger logger = LoggerFactory.getLogger(RegisterDAO.class);
	
	@Override
	public String getNamespace() {
		return RegisterDAO.NAMESPACE;
	}
	
	@Override
	public List<CountryCodeVO> selectCountryCode() {
		return getSqlSession().selectList(this.getNamespace() + ".selectCountryCode");
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
	public ReqInsertMemberInfoVO insertMemberCorpInfo(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		getSqlSession().update(this.getNamespace() + ".insertMemberInfoExtra", reqInsertMemberInfoVO);
		return reqInsertMemberInfoVO;
	}
	
	@Override
	public String selectJoinDt(ReqInsertMemberInfoVO reqInsertMemberInfoVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectJoinDt", reqInsertMemberInfoVO);
	}
	
	@Override
	public ResultVO updateEmailAuth(ReqEmailAuthVO reqEmailAuthVO) {
		return null;
	}
}
