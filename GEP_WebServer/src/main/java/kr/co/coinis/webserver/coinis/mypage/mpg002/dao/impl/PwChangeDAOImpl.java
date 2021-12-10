/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.mypage.mpg002.dao.PwChangeDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg002.vo.ReqChangePwVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * 
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg002.dao.impl 
 *    |_ PwChangeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 11:48:50
 * @version : 
 * @author : Jungjea
 */
@Repository("pwChangeDAO")
public class PwChangeDAOImpl extends AbstractDefaultMapper implements PwChangeDAO {

	@Override
	public String getNamespace() {
		return PwChangeDAO.NAMESPACE;
	}

	@Override
	public int reqPwMatch(ReqChangePwVO reqChangePwVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".PwMatch", reqChangePwVO);
	}

	@Override
	public ReqChangePwVO reqPwChange(ReqChangePwVO reqChangePwVO) {
		getSqlSession().update(this.getNamespace() + ".PwChange", reqChangePwVO);
		return reqChangePwVO;
	}
}
