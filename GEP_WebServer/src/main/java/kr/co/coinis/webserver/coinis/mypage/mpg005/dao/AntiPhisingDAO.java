/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg005.dao;

import java.sql.SQLException;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg005.dao 
 *    |_ AntiPhisingDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 1:40:18
 * @version : 
 * @author : yeonseoo
 */
public interface AntiPhisingDAO {

	public static final String NAMESPACE = AntiPhisingDAO.class.getName();
	
	public Map<String, Object> call_PR_WAS_SET_FISH_ANTI_CODE(Map<String, Object> paramMap) throws SQLException;
	
	public String selectUserRegisterDT(ExchangeIDUserIDPairVO param);
}
