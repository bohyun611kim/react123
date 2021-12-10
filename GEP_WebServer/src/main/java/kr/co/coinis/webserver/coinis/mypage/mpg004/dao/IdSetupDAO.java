/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.CorpInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.ExtraUserInfoVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.dao 
 *    |_ IdSetupDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 16. 오전 11:03:22
 * @version : 
 * @author : yeonseoo
 */
public interface IdSetupDAO {

	public static final String NAMESPACE = IdSetupDAO.class.getName();
	
	/**
	 * 국가 이름 영문명 리스트 조회
	 * <pre>
	 * 1. 개요 : 국가 이름 영문명 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSecuritySetStatus
	 * @date : 2019. 5. 16.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  16.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	List<String> selectCountryList();
	
	Map<String, Object> call_PR_WAS_INSERT_ID_AUTH_INFO(Map<String, Object> paramMap) throws SQLException;
	
	Map<String, Object> call_PR_WAS_UPDATE_MEMBER_INFO_EXTRA(Map<String, Object> paramMap) throws SQLException;
	
	/* 프로시저 호출을 위한 회원 추가 정보 조회 */
	ExtraUserInfoVO selectExtraUserInfo(ExchangeIDUserIDPairVO param);
	
	Map<String, Object> call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO(Map<String, Object> paramMap) throws SQLException;
	
	/**
	 * 사업자 등록번호로 법인 정보 조회
	 * <pre>
	 * 1. 개요 : 회원 정보에 등록된 사업자 등록번호를 조회한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSecuritySetStatus
	 * @date : 2019. 5. 16.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5.  17.		yeonseoo					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	CorpInfoVO selectCorpInfo(Map<String, Object> paramMap);
	
	int updateMemberInfoExtraIDAuthInfo(ReqIdCardVO param) throws SQLException;
}
