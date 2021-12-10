/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr001.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendMailVO;
import kr.co.coinis.webserver.holdport.member.mbr001.service.HoldportRegisterService;
import kr.co.coinis.webserver.holdport.member.mbr001.vo.ReqEmailAuthVO;
import kr.co.coinis.webserver.holdport.member.mbr001.vo.ReqInsertMemberInfoVO;

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
public class HoldportRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportRegisterController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldportRegisterService holdportRegisterService;

	@Autowired
	private CommService commService;

	@RequestMapping(value = "site/register")
	public ModelAndView moveRegister(HttpServletRequest request, HttpSession session) {

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		ModelAndView model = new ModelAndView();

		String recommandId = request.getParameter("recommandId");

		//--------------- locale
		String key = "localInfo" + request.getRequestedSessionId();
		String locale = redisSessionRepository.get(key);
		model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
		//---------------

		recommandId = recommandId == null ? "":recommandId;

		if(CommonUtils.isLogin(userDetails)) {
			// 세션정보가 있을 경우 사이트 초기 페이지로 이동
			model.setViewName("redirect:/");
		} else {
			// 세션이 없을 경우 register page 이동
			model.setViewName(CommonUtils.getSitePackageKey(request) + "/member/mbr001/register");
			model.addObject("recommandId", recommandId);
			model.addObject("date", DateUtils.getNow());
		}

		return model;
	}

	@RequestMapping(value = "/site/register/doRegist", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doRegist(HttpServletRequest request
			, @ModelAttribute @Valid ReqInsertMemberInfoVO vo
			, BindingResult bindingResult) {
		ResultVO result = new ResultVO();

		try {

			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);

				result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
				result.setRtnMsg(error.getDefaultMessage());

			} else {
				vo.setExchangeId(CommonUtils.getNewExchangeId(request));

				if(vo.getUserTypeCd() == 2) {
					if( StringUtils.isEmpty(vo.getCorpName()) ) {
						result.setRtnCd(-5000);
						result.setRtnMsg("법인명은 필수입니다");
					}

					if( StringUtils.isEmpty(vo.getCorpRepr()) ) {
						result.setRtnCd(-5001);
						result.setRtnMsg("대표자명은 필수입니다");
					}

					if( StringUtils.isEmpty(vo.getCorpRegNo()) ) {
						result.setRtnCd(-5002);
						result.setRtnMsg("사업자 등록번호는 필수 입니다");
					}
				}

				/*
				if( StringUtils.isEmpty(vo.getUserName()) ) {
					result.setRtnCd(-5005);
					result.setRtnMsg("성명은 필수입니다");
				}
				*/
				vo.setUserName("");

				if(!StringUtils.chekcEmailAddress(vo.getUserId())) {
					result.setRtnCd(-5003);
					result.setRtnMsg("잘못된 이메일 주소입니다");
				}

				if(!StringUtils.checkPassword(vo.getPassword())) {
					result.setRtnCd(-5048);
					result.setRtnMsg("비밀번호는 대소문자, 숫자, 특수문자 포함 8자리 이상 25자리 이하입니다");
				}

				if(result.getRtnCd() == 0) {
					result = holdportRegisterService.doRegist(vo);

					if(result.getRtnCd() == 0) {
						// 입력한 이메일 인증메일 발송
						SendMailVO sendMailVO = new SendMailVO();
						sendMailVO.setExchangeId(vo.getExchangeId());
						sendMailVO.setUserId(vo.getUserId());
						sendMailVO.setMailType(1);
						sendMailVO.setMsgOption("html");

						commService.sendMail(sendMailVO);
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5004);
			result.setRtnMsg("회원가입에 실패하였습니다");
		}

		return result;
	}

	@RequestMapping(value = "/site/emailAuth", method=RequestMethod.GET)
	public ModelAndView registerEmailAuth(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "key", required = true) String key
			, @RequestParam(value = "id", required = true) String id) {


		logger.info("request id["+id+"], key["+key+"]");


		ModelAndView model = new ModelAndView();
		ResultVO result = new ResultVO();
		String exchangeId = CommonUtils.getNewExchangeId(request);

		try {
			ReqEmailAuthVO vo = new ReqEmailAuthVO();
			vo.setExchangeId(exchangeId);
			vo.setUserId(id);
			vo.setMemberStatCd(2);
			vo.setKey(key);

			result = holdportRegisterService.registerEmailAuth(vo);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5090);
			result.setRtnMsg("이메일 인증에 실패하였습니다");
		}

		//--------------- locale
		String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
		model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
		//---------------

		model.setViewName(CommonUtils.getSitePackageKey(request) + "/member/mbr001/emailAuth");
		model.addObject("result", result);

		return model;
	}

	@RequestMapping(value = "/site/joining", method = RequestMethod.GET)
	public ModelAndView doJoining(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();

		//--------------- locale
		String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
		model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
		//---------------
		model.addObject("email", request.getParameter("email"));
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/member/mbr001/joining");

		return model;
	}

	@RequestMapping(value = "/site/joining/reSendMail", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO reEmailSend(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "address", required = true) String address) {

		ResultVO result = new ResultVO();

		String exchangeId = CommonUtils.getNewExchangeId(request);

		try {

			if(StringUtils.chekcEmailAddress(address)) {

				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", exchangeId);
				param.put("userId", address);

				int statCd = holdportRegisterService.selectMemberStatCd(param);

				if(statCd == 1) {
					SendMailVO sendMailVO = new SendMailVO();
					sendMailVO.setExchangeId(exchangeId);
					sendMailVO.setUserId(address);
					sendMailVO.setMailType(1);
					sendMailVO.setMsgOption("html");

					result = commService.sendMail(sendMailVO);

				} else {
					result.setRtnCd(-5005);
					result.setRtnMsg("인증메일 재전송 대상이 아닙니다");
				}
			} else {

				result.setRtnCd(-5006);
				result.setRtnMsg("유효하지 않은 이메일 주소입니다");
			}
		} catch (Exception e) {

			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5007);
			result.setRtnMsg("인증메일 발송에 실패하였습니다");

		}

		return result;
	}
}
