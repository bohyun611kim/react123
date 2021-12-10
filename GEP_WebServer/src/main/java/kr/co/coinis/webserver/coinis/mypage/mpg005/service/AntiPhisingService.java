/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg005.service;

import java.sql.SQLException;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg005.service 
 *    |_ AntiPhisingService.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 1:41:02
 * @version : 
 * @author : yeonseoo
 */
public interface AntiPhisingService {
	
	/* 피싱차단코드 설정상태, 코드 업데이트 */
	public Map<String, Object> call_PR_WAS_SET_FISH_ANTI_CODE(Map<String, Object> paramMap) throws SQLException;
	
	/* 회원 가입 일자 조회 */
	public String selectUserRegisterDT(ExchangeIDUserIDPairVO param);

}
