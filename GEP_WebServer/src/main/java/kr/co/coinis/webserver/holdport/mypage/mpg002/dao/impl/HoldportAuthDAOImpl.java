/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg002.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.mypage.mpg002.dao.HoldportAuthDAO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg002.dao.impl
 *    |_ HoldportAuthDAOImpl.java
 *
 * </pre>
 * @date : 2019. 5. 8. 오전 10:38:56
 * @version :
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Repository("holdportAuthDAO")
public class HoldportAuthDAOImpl extends AbstractDefaultMapper implements HoldportAuthDAO {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportAuthDAOImpl.class);

	@Override
	public String getNamespace() {
		return HoldportAuthDAO.NAMESPACE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAuthDAO] >> call_PR_WAS_SET_OTP_AUTH ");

		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		this.getSqlSessionTemplate().update(this.getNamespace() + ".call_PR_WAS_SET_OTP_AUTH", paramMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("V_RTN_CD", paramMap.get("V_RTN_CD"));
		resultMap.put("V_RTN_MSG", paramMap.get("V_RTN_MSG"));

		return resultMap;
	}

	@Override
	public int updateMemberInfoAtOtp(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAuthDAO] >> updateMemberInfoAtOtp ");
		return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateMemberInfoAtOtp", paramMap);
	}

	@Override
	public boolean checkUserPassword(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAuthDAO] >> checkUserPassword ");
		int cnt = this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".checkUserPassword", paramMap);
		if(cnt > 0) return true;
		else return false;
	}

	@Override
	public String selectUserInfo(ReqIdCardVO reqIdCardVO) {
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserInfo", reqIdCardVO);
	}

	@Override
	public String selectAuthLevel(ReqIdCardVO reqIdCardVO) {
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectAuthLevel", reqIdCardVO);
	}

	@Override
	public AuthLevel4VO selectAuthLevel4Info(Map<String, Object> param) {
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectAuthLevel4Info", param);
	}

	@Override
	public String selectAuthLevelCorp(ReqIdCardVO reqIdCardVO) {
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectAuthLevelCorp", reqIdCardVO);
	}

	@Override
	public int updateLevel4Request(ReqIdCardVO reqIdCardVO) {
		return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateLevel4Request", reqIdCardVO);
	}

	@Override
	public Map<String, Object> procInsertIdAuthInfo(Map<String, Object> param) {
		this.getSqlSessionTemplate().update(this.getNamespace() + ".procInsertIdAuthInfo", param);
		return param;
	}

	@Override
	public Map<String, Object> procInsertMemberCorpAuthInfo(Map<String, Object> param) {
		this.getSqlSessionTemplate().update(this.getNamespace() + ".procInsertMemberCorpAuthInfo", param);
		return param;
	}

	@Override
	public AuthLevel5VO selectAuthLevel5Info(Map<String, Object> param) {
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectAuthLevel5Info", param);
	}

	@Override
	public ReqAuthLevel5VO procMemberLevel5Mgr(ReqAuthLevel5VO reqAuthLevel5VO) {
		this.getSqlSessionTemplate().update(this.getNamespace() + ".procMemberLevel5Mgr", reqAuthLevel5VO);
		return reqAuthLevel5VO;
	}

	@Override
	public int updateExtraAddress(ReqAuthLevel5VO reqAuthLevel5VO) {
		return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateExtraAddress", reqAuthLevel5VO);
	}
}
