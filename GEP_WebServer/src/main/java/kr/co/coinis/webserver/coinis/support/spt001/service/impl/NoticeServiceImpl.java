/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.support.spt001.dao.NoticeDAO;
import kr.co.coinis.webserver.coinis.support.spt001.service.NoticeService;
import kr.co.coinis.webserver.coinis.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.ReqNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.service.impl 
 *    |_ NoticeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:35
 * @version : 
 * @author : Seongcheol
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;
	
	@Override
	public Map<String, Object> selectNoticeListWithSize(ReqNoticeVO reqNoticeVO) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("list", noticeDAO.selectNoticeList(reqNoticeVO));
		result.put("size", noticeDAO.selectNoticeSize(reqNoticeVO));
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectNoticeList(ReqNoticeVO reqNoticeVO) {
		Map<String, Object> result = new HashMap<>();
		result.put("list", noticeDAO.selectNoticeList(reqNoticeVO));;
		return result;
	}
	
	@Override
	public NoticeVO selectNoticeOne(ReqNoticeVO reqNoticeVO) {
		return noticeDAO.selectNoticeOne(reqNoticeVO);
	}
}
