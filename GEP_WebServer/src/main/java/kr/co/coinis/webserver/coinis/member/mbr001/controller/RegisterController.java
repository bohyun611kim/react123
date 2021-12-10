/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.member.mbr001.service.RegisterService;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.CountryCodeVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.coinis.member.mbr001.vo.ReqInsertMemberInfoVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.controller 
 *    |_ RegisterController.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 4:01:29
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value = "coinis/register")
	public ModelAndView moveRegister(HttpServletRequest request, HttpSession session) {
		
		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		ModelAndView model = new ModelAndView();
		
		if(CommonUtils.isLogin(userDetails)) {
			// 세션정보가 있을 경우 사이트 초기 페이지로 이동
			model.setViewName("redirect:/" + CommonUtils.getPageKey(request) + "/home");
		} else {
			// 세션이 없을 경우 login page 이동
			List<CountryCodeVO> code = registerService.selectCountryCode();
			
			model.setViewName(CommonUtils.getPageKey(request) + "/member/mbr001/register");
			model.addObject("code", code);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/coinis/register/doRegist", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doRegist(HttpServletRequest request
			, @ModelAttribute @Valid ReqInsertMemberInfoVO vo
			, BindingResult bindingResult) {
		ResultVO result = new ResultVO();
		
		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);
				
				result.setRtnCd(-1);
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				vo.setExchangeId(CommonUtils.getExchangeId(request));
				
				if(vo.getUserTypeCd() == 2) {
					if( StringUtils.isEmpty(vo.getCorpName()) ) {
						result.setRtnCd(-1);
						result.setRtnMsg("법인명은 필수입니다.");
					}
					
					if( StringUtils.isEmpty(vo.getCorpRepr()) ) {
						result.setRtnCd(-1);
						result.setRtnMsg("대표자명은 필수입니다.");
					}
					
					if( StringUtils.isEmpty(vo.getCorpRegNo()) ) {
						result.setRtnCd(-1);
						result.setRtnMsg("사업자 등록번호는 필수 입니다.");
					}
				}
				
				if(result.getRtnCd() == 0) {
					registerService.doRegist(vo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setRtnCd(-1);
			result.setRtnMsg("작업중 오류가 발생하였습니다");
		}
		
		return result;
	}

	@RequestMapping(value = "/coinis/emailAuth", method=RequestMethod.GET)
	public ModelAndView registerEmailAuth(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		ResultVO result = new ResultVO();
		
		try {
			ReqEmailAuthVO vo = new ReqEmailAuthVO();
			
			//result = registerService.registerEmailAuth(vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		model.setViewName("redirect:/" + CommonUtils.getPageKey(request) + "/home");
		
		return model;
	}
}
