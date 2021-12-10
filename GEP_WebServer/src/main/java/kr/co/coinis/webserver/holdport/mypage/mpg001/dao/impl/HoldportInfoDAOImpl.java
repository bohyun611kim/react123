/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.mypage.mpg001.dao.HoldportInfoDAO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.AccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.PwChangeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.RecommendInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAccessLogVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthLevelVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqAuthMeansVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqMarketingAgreeVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqSmsAuthVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.holdport.mypage.mpg001.vo.UserInfoVO;


/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.dao.impl
 *    |_ InfoServiceImpl.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오전 10:22:12
 * @version :
 * @author : Seongcheol
 */
@Repository("holdportInfoDAO")
public class HoldportInfoDAOImpl extends AbstractDefaultMapper implements HoldportInfoDAO {

	@Override
	public String getNamespace() {
		return HoldportInfoDAO.NAMESPACE;
	}

	@Override
	public UserInfoVO selectUserInfo(ReqUserInfoVO reqUserInfoVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectUserInfo", reqUserInfoVO);
	}

	@Override
	public RecommendInfoVO selectRecommendInfo(ReqUserInfoVO reqUserInfoVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectRecommendInfo", reqUserInfoVO);
	}

	@Override
	public int checkPassword(PwChangeVO pwChangeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".checkPassword", pwChangeVO);
	}

	@Override
	public ReqAuthMeansVO procChangeAuthMeansCd(ReqAuthMeansVO reqAuthMeansVO) {
		getSqlSession().update(this.getNamespace() + ".procChangeAuthMeansCd", reqAuthMeansVO);
		return reqAuthMeansVO;
	}

	@Override
	public PwChangeVO procChangePassword(PwChangeVO pwChangeVO) {
		getSqlSession().update(this.getNamespace() + ".procChangePassword", pwChangeVO);
		return pwChangeVO;
	}

	@Override
	public int updateMarketingAgree(ReqMarketingAgreeVO reqMarketingAgreeVO) {
		return getSqlSession().update(this.getNamespace() + ".updateMarketingAgree", reqMarketingAgreeVO);
	}

	@Override
	public int checkMobileDuplication(KcpAuthVO kcpAuthVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".checkMobileDuplication", kcpAuthVO);
	}

	@Override
	public UserInfoVO selectAuthLevel(KcpAuthVO kcpAuthVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAuthLevel", kcpAuthVO);
	}

	@Override
	public String selectLastAuthDateTime(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectLastAuthDateTime", param);
	}

	@Override
	public int updateMemberInfo(ReqAuthLevelVO reqAuthLevelVO) {
		return getSqlSession().update(this.getNamespace() + ".updateMemberInfoExtra", reqAuthLevelVO);
	}

	@Override
	public ReqSmsAuthVO procSetSmsAuth(ReqSmsAuthVO reqSmsAuthVO) {
		getSqlSession().update(this.getNamespace() + ".procSetSmsAuth", reqSmsAuthVO);
		return reqSmsAuthVO;
	}

	@Override
	public KcpAuthVO procUpdateMemberInfoExtra(KcpAuthVO kcpAuthVO) {
		getSqlSession().update(this.getNamespace() + ".procUpdateMemberInfoExtra", kcpAuthVO);
		return kcpAuthVO;
	}

	@Override
	public int updatePhoneNo(KcpAuthVO kcpAuthVO) {
		return getSqlSession().update(this.getNamespace() + ".updatePhoneNo", kcpAuthVO);
	}

	@Override
	public List<AccessLogVO> selectAccessLog(ReqAccessLogVO reqAccessLogVO) {
		return getSqlSession().selectList(this.getNamespace() + ".selectAccessLog", reqAccessLogVO);
	}

	@Override
	public int selectAccessLogCnt(ReqAccessLogVO reqAccessLogVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAccessLogCnt", reqAccessLogVO);
	}

	@Override
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procCheckAuthCode", authCodeVO);
		return authCodeVO;
	}

	@Override
	public String selectOtpKey(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectOtpKey", param);
	}

}
