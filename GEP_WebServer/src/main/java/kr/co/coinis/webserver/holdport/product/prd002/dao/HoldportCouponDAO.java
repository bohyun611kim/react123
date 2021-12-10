/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd002.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.coupon.prd002.dao.impl 
 *    |_ HoldportCouponDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:06:24
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
public interface HoldportCouponDAO {

	public static final String NAMESPACE = HoldportCouponDAO.class.getName();

	public List<Map<String, Object>> selectUserCouponHistoryList(Map paramMap) throws SQLException;
	public Map<String, Object> selectUserCouponAmount(Map paramMap) throws SQLException;

}
