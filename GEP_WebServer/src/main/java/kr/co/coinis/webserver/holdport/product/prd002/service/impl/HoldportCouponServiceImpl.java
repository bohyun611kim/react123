/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd002.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.holdport.product.prd002.dao.HoldportCouponDAO;
import kr.co.coinis.webserver.holdport.product.prd002.service.HoldportCouponService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.coupon.prd002.service.impl 
 *    |_ HoldportCouponServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:05:29
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("holdportCouponService")
public class HoldportCouponServiceImpl implements HoldportCouponService {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportCouponServiceImpl.class);

	@Resource(name="holdportCouponDAO")
	private HoldportCouponDAO holdportCouponDAO;


	@Override
	public List<Map<String, Object>> selectUserCouponHistoryList(Map paramMap) throws SQLException {
		return holdportCouponDAO.selectUserCouponHistoryList(paramMap);
	}

	@Override
	public Map<String, Object> selectUserCouponAmount(Map paramMap) throws SQLException {
		return holdportCouponDAO.selectUserCouponAmount(paramMap);
	}

}
