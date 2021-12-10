/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg003.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.dao.HoldportOpenApiDAO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.vo.CreateApiVO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.vo.ModApiVO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.vo.OpenApiVO;
import kr.co.coinis.webserver.holdport.mypage.mpg003.vo.UserAuthInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg003.dao.impl
 *    |_ HoldportOpenApiDAOImpl.java
 *
 * </pre>
 * @date : 2019. 7. 9. 오후 2:37:15
 * @version :
 * @author : kangn
 */
@Repository("holdportOpenApiDAO")
public class HoldportOpenApiDAOImpl extends AbstractDefaultMapper implements HoldportOpenApiDAO {

	@Override
	public String getNamespace() {
		return HoldportOpenApiDAO.NAMESPACE;
	}

	@Override
	public List<OpenApiVO> selectApiList(ReqUserInfoVO reqUserInfoVO) {
		return getSqlSession().selectList(this.getNamespace() + ".selectApiList", reqUserInfoVO);
	}

	@Override
	public OpenApiVO selectApiInfo(CreateApiVO createApiVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectApiInfo", createApiVO);
	}

	@Override
	public UserAuthInfoVO selectUserAuthInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectUserAuthInfo", param);
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
	public int insertApiInfo(CreateApiVO createApiVO) {
		return getSqlSession().insert(this.getNamespace() + ".insertApiInfo", createApiVO);
	}

	@Override
	public int updateApiInfo(ModApiVO modApiVO) {
		return getSqlSession().update(this.getNamespace() + ".updateApiInfo", modApiVO);
	}

	@Override
	public int deleteApiInfo(ModApiVO modApiVO) {
		return getSqlSession().delete(this.getNamespace() + ".deleteApiInfo", modApiVO);
	}

}
