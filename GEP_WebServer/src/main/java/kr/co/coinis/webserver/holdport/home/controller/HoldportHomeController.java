/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.home.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.holdport.home.service.HoldportHomeService;

/**
 * <pre>
 * kr.co.coinis.webserver.home.controller
 *    |_ HomeController.java
 *
 * </pre>
 * @date : 2019. 4. 15. 오전 9:38:41
 * @version :
 * @author : yeonseoo
 */
@Controller
public class HoldportHomeController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportHomeController.class);

	@Autowired
	private HoldportHomeService holdportHomeService;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@RequestMapping(value = "/")
	public ModelAndView moveHome(HttpServletRequest request) {

		CustomUserDetails userDetails = null;
		String sessionKey = request.getRequestedSessionId();
		logger.debug(">> HOME ----------------- sessionKey["+sessionKey+"]");


		String exchangeId = CommonUtils.getNewExchangeId(request);

		ModelAndView model = new ModelAndView();
		
		String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
		model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);

		model.setViewName(CommonUtils.getSitePackageKey(request) + "/home/home");
		//model.addObject("event", holdportHomeService.selectBannerList(exchangeId));
		model.addObject("notice", holdportHomeService.selectNoticeList(exchangeId));
		model.addObject("event", holdportHomeService.selectBanner(exchangeId));
		model.addObject("topinfluencer", holdportHomeService.selectInfluencerTop10List());

		return model;
	}

	@RequestMapping(value = "site/api/home/setLocale")
	@ResponseBody
	public ResultVO walletInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "locale", defaultValue = "ko") String strLocale
			) {

		String sessionKey = request.getRequestedSessionId();

		logger.info(">> setLocale sessionKey["+sessionKey+"]");

		String key = "localInfo" + sessionKey;
		session.setAttribute(key, strLocale);
		redisSessionRepository.put(key, strLocale);
		logger.info(">> redisSessionRepository setLocale locale:" + strLocale);


		ResultVO resultVo = new ResultVO();
		resultVo.setRtnCd(1);
		resultVo.setRtnMsg("OK");
		return resultVo;
	}


}
