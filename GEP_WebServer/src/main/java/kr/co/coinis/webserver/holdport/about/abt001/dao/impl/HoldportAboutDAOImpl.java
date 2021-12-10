/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.about.abt001.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.about.abt001.dao.HoldportAboutDAO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.about.abt001.dao.impl 
 *    |_ HoldportAboutDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:06:45
 * @version : 
 * @author : kangn
 */
@SuppressWarnings({"rawtypes"})
@Repository("holdportAboutDAO")
public class HoldportAboutDAOImpl extends AbstractDefaultMapper implements HoldportAboutDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(HoldportAboutDAOImpl.class);

	@Override
	public String getNamespace() {
		return HoldportAboutDAO.NAMESPACE;
	}

	@Override
	public List<Map<String, Object>> selectInquiryList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAboutDAO] >> selectInquiryList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectInquiryList", paramMap);
	}

	@Override
	public Map<String, Object> selectInquiryListCount(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAboutDAO] >> selectInquiryListCount ");
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectInquiryListCount", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectInquiryReplyList(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAboutDAO] >> selectInquiryReplyList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectInquiryReplyList", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertInquiry(Map paramMap) throws SQLException {
		LOG.debug("[HoldportAboutDAO] >> insertInquiry ");
		paramMap.put("V_INQUIRY_NO", 0);
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		this.getSqlSessionTemplate().update(this.getNamespace() + ".call_PR_WAS_INSERT_INQUIRY", paramMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("V_INQUIRY_NO", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_INQUIRY_NO"), "0")).intValue() );
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue() );
		resultMap.put("V_RTN_MSG", CommonUtils.strNlv(paramMap.get("V_RTN_MSG"), "") );
		
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertInquiryAttachFile(Map paramMap) throws SQLException {
		paramMap.put("V_RTN_CD", 0);
		paramMap.put("V_RTN_MSG", "");
		this.getSqlSessionTemplate().update(this.getNamespace() + ".call_PR_WAS_INSERT_INQUIRY_ATTACH_FILE", paramMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("V_RTN_CD", Double.valueOf(CommonUtils.strNlv(paramMap.get("V_RTN_CD"), "-1")).intValue() );
		resultMap.put("V_RTN_MSG", CommonUtils.strNlv(paramMap.get("V_RTN_MSG"), "") );
		
		return resultMap;
	}

}
