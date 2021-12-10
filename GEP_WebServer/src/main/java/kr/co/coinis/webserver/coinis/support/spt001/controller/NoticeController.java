/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.support.spt001.service.NoticeService;
import kr.co.coinis.webserver.coinis.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.ReqNoticeVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.controller 
 *    |_ NoticeController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:33:19
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "coinis/notice")
	public ModelAndView moveNotice(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String nowTime = dateFormat.format(date);
		
			
		try {
			ReqNoticeVO vo = new ReqNoticeVO();
			vo.setExchangeId(CommonUtils.getExchangeId(request));
			vo.setPageNum(0);
			
			result = noticeService.selectNoticeListWithSize(vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		model.setViewName(CommonUtils.getPageKey(request) + "/support/spt001/notice");
		model.addObject("size", result.get("size"));
		model.addObject("data", result.get("list"));
		model.addObject("time", nowTime);
		
		return model;
	}
	
	@RequestMapping(value="coinis/notice/content", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView selectNoticeContent(HttpServletRequest request) {
		
		NoticeVO noticeVO = new NoticeVO();
		
		ModelAndView model = new ModelAndView();
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			ReqNoticeVO vo = new ReqNoticeVO();
			vo.setExchangeId(CommonUtils.getExchangeId(request));
			vo.setPageNum(no);
			
			noticeVO = noticeService.selectNoticeOne(vo);
			
			if(noticeVO != null) {
				model.setViewName(CommonUtils.getPageKey(request) + "/support/spt001/content");
				model.addObject("data", noticeVO);
			} else {
				model.setViewName("redirect:/notice");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}
	
	@RequestMapping(value="/coinis/notice/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectNoticeList(HttpServletRequest request
			, @RequestParam(value = "pageNum", required = true) int pageNum
			, @RequestParam(value = "type", defaultValue="1", required = false) int type) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		pageNum = (pageNum - 1) * 10;
		
		try {
			ReqNoticeVO vo = new ReqNoticeVO();
			vo.setExchangeId(CommonUtils.getExchangeId(request));
			vo.setPageNum(pageNum);
			
			if(type == 1) {
				result = noticeService.selectNoticeList(vo);				
			} else {
				result = noticeService.selectNoticeListWithSize(vo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
}
