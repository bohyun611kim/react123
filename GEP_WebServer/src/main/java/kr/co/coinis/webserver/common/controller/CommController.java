/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.common.controller
 *    |_ CommController.java
 *
 * </pre>
 * @date : 2019. 5. 2. 오후 3:06:37
 * @version :
 * @author : Seongcheol
 */
@Controller
public class CommController {

	private static final Logger logger = LoggerFactory.getLogger(CommController.class);

	@Autowired
	private CommService commService;

	@RequestMapping(value="/common/defaultMktGrpId", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> defaultMktGrpId(HttpServletRequest request, HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		try {
			String exchangeId = CommonUtils.getNewExchangeId(request);

			result.put("mktGrpId", commService.selectDefaultMktGrpId(exchangeId));
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return result;
	}

	@RequestMapping(value="/common/mktGrpList", method=RequestMethod.POST)
	@ResponseBody
	public List<String> mktGrpList(HttpServletRequest request, HttpSession session) {

		List<String> result = new ArrayList<String>();

		try {
			String exchangeId = CommonUtils.getNewExchangeId(request);

			result = commService.selectMktGrpList(exchangeId);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return result;
	}

	@RequestMapping(value="/common/getWordBook", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getWordBook(HttpServletRequest request, HttpSession session) {

		List<Map<String, Object>> result = new ArrayList<>();

		try {
			result = commService.selectWordBook();
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return result;
	}
}
