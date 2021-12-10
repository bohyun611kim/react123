/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg005.service.impl;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.mypage.mpg005.dao.AntiPhisingDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg005.service.AntiPhisingService;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg005.service.impl 
 *    |_ AntiPhisingServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 1:40:57
 * @version : 
 * @author : yeonseoo
 */
@Service("antiPhisingService")
public class AntiPhisingServiceImpl implements AntiPhisingService {

	@Resource(name="antiPhisingDAO")
	private AntiPhisingDAO antiPhisingDAO;

	@Override
	public Map<String, Object> call_PR_WAS_SET_FISH_ANTI_CODE(Map<String, Object> paramMap) throws SQLException {
		return antiPhisingDAO.call_PR_WAS_SET_FISH_ANTI_CODE(paramMap);
	}

	@Override
	public String selectUserRegisterDT(ExchangeIDUserIDPairVO param) {
		return antiPhisingDAO.selectUserRegisterDT(param);
	}
}
