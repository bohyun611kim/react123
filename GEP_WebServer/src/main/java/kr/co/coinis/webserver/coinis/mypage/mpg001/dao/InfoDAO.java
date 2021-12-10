/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.dao;

import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.MemberProfileInfoVO;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.coinis.mypage.mpg001.vo.ReqUpdateCodeVO;;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.dao 
 *    |_ InfoDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:22:55
 * @version : 
 * @author : yeonseoo
 */
public interface InfoDAO {

	public static final String NAMESPACE = InfoDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : 마이페이지 회원정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMemberProfileInfo
	 * @date : 2019. 5. 10.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 10.		yeonseoo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqUserInfoVO
	 * @return
	 */
	public MemberProfileInfoVO selectMemberProfileInfo(ExchangeIDUserIDPairVO param);

	public int updateMarketingAgree(ReqUpdateCodeVO param);
	
	public int updateAuthMeansCD(ReqUpdateCodeVO param);
	

}
