/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr003.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendMailVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.member.mbr003.controller
 *    |_ PwSearchController.java
 *
 * </pre>
 * @date : 2019. 5. 5. 오후 7:09:59
 * @version :
 * @author : Seongcheol
 */
@Controller
public class HoldportPwSearchController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportPwSearchController.class);

	@Autowired
	private CommService commService;

	@RequestMapping(value="/site/pwSearch", method=RequestMethod.GET)
	public ModelAndView movePwSearch(HttpServletRequest request, HttpSession session) {

		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getSitePackageKey(request) + "/member/mbr003/pwSearch");

		return model;
	}

	@RequestMapping(value="/site/pwSearch/sendMail", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO sendMail(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "address", required = true) String address) {

		ResultVO result = new ResultVO();

		try {
			SendMailVO sendMailVO = new SendMailVO();
			sendMailVO.setExchangeId(CommonUtils.getNewExchangeId(request));
			sendMailVO.setUserId(address);
			sendMailVO.setMailType(1);
			sendMailVO.setMsgOption("html");

			result = commService.sendInitPwMail(sendMailVO);

			if(result.getRtnCd() != 0) {
				result.setRtnCd(-5018);
				result.setRtnMsg("비밀번호 초기화 메일 발송 실패");
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5018);
			result.setRtnMsg("비밀번호 초기화 메일 발송 실패");
		}

		return result;
	}
}
