/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.mypage.mpg001.dao.InfoDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.MemberProfileInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.ReqUpdateCodeVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.dao.impl 
 *    |_ InfoServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 10. 오전 10:22:12
 * @version : 
 * @author : yeonseoo
 */
@Repository("infoDAO")
public class InfoDAOImpl extends AbstractDefaultMapper implements InfoDAO {

	@Override
	public String getNamespace() {
		return InfoDAO.NAMESPACE;
	}

	@Override
	public MemberProfileInfoVO selectMemberProfileInfo(ExchangeIDUserIDPairVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMemberProfileInfo", param);
	}

	@Override
	public int updateMarketingAgree(ReqUpdateCodeVO param) {
		return getSqlSession().update(this.getNamespace() + ".updateMarketingAgree", param);
	}

	@Override
	public int updateAuthMeansCD(ReqUpdateCodeVO param) {
		return getSqlSession().update(this.getNamespace() + ".updateAuthMeansCD", param);
	}
}
