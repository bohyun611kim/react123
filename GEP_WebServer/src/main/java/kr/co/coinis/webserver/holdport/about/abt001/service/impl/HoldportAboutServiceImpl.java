/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.about.abt001.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.holdport.about.abt001.dao.HoldportAboutDAO;
import kr.co.coinis.webserver.holdport.about.abt001.service.HoldportAboutService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.about.abt001.service.impl 
 *    |_ HoldportAboutServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:05:29
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("holdportAboutService")
public class HoldportAboutServiceImpl implements HoldportAboutService {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportAboutServiceImpl.class);

	@Resource(name="holdportAboutDAO")
	private HoldportAboutDAO holdportAboutDAO;

	@Override
	public List<Map<String, Object>> selectInquiryList(Map paramMap) throws SQLException {
		return holdportAboutDAO.selectInquiryList(paramMap);
	}

	@Override
	public Map<String, Object> selectInquiryListCount(Map paramMap) throws SQLException {
		return holdportAboutDAO.selectInquiryListCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectInquiryReplyList(Map paramMap) throws SQLException {
		return holdportAboutDAO.selectInquiryReplyList(paramMap);
	}

	@Override
	public Map<String, Object> insertInquiry(Map paramMap) throws SQLException {
		return holdportAboutDAO.insertInquiry(paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertInquiryAttachFile(Map paramMap, List<Map<String, String>> paramList) throws SQLException {
		Map<String, Object> resultMap = holdportAboutDAO.insertInquiry(paramMap);
		
		int V_INQUIRY_NO = (int) resultMap.get("V_INQUIRY_NO");
		
		if((int)resultMap.get("V_RTN_CD") == 0) {
			for(int i=0; i<paramList.size(); i++) {
				if((int)resultMap.get("V_RTN_CD") == 0) {
					paramMap.put("ARG_INQUIRY_NO", V_INQUIRY_NO);
					paramMap.put("ARG_ATTACH_FILE_NM", paramList.get(i).get("ARG_ATTACH_FILE_NM"));
					paramMap.put("ARG_SAVED_FILE_NM", paramList.get(i).get("ARG_SAVED_FILE_NM"));
					
					resultMap = holdportAboutDAO.insertInquiryAttachFile(paramMap);
				} else {
					throw new SQLException("파일 업로드 실패");
				}
			}
		}
		
		return resultMap;
	}

}
