/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd002.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.product.prd002.dao.HoldportCouponDAO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.coupon.prd002.dao.impl 
 *    |_ HoldportCouponDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:06:45
 * @version : 
 * @author : kangn
 */
@SuppressWarnings({"rawtypes"})
@Repository("holdportCouponDAO")
public class HoldportCouponDAOImpl extends AbstractDefaultMapper implements HoldportCouponDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(HoldportCouponDAOImpl.class);

	@Override
	public String getNamespace() {
		return HoldportCouponDAO.NAMESPACE;
	}

	@Override
	public List<Map<String, Object>> selectUserCouponHistoryList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportCouponDAO] >> selectUserCouponHistoryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectUserCouponHistoryList", paramMap);
	}
	@Override
	public Map<String, Object> selectUserCouponAmount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportCouponDAO] >> selectUserCouponAmount ");

		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectUserCouponAmount", paramMap);
	}

}
