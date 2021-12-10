/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg003.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.service.YahobitOpenApiService;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.CreateApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.ModApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.OpenApiVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.controller 
 *    |_ YahobitOpenApiController.java
 * 
 * </pre>
 * @date : 2019. 7. 10. 오전 11:12:51
 * @version : 
 * @author : kangn
 */
@Controller
public class YahobitOpenApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitOpenApiController.class);

	@Autowired
	private CommService commService;
	
	@Autowired
	private YahobitOpenApiService yahobitOpenApiService;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@RequestMapping(value = "/alibit/api/reqSmsCodeByApi", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO reqApiSmsAuthCode(HttpServletRequest request, HttpSession session) throws Exception {

		ResultVO result = new ResultVO();
		
		try {
			String exchangeId = CommonUtils.getExchangeId(request);
			
			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(exchangeId);
			authCodeVO.setUserId(userDetails.getUserId());
			authCodeVO.setAuthPurposeCd(5);						// 인증 목적 코드 5: open api
			authCodeVO.setAuthMeansCd(2);						// 인증 수단 식별 코드 2: SMS
			authCodeVO.setExpireSec(3 * 60);					// 인증 코드 유효시간 설정
			
			authCodeVO = yahobitOpenApiService.requestSmsCode(authCodeVO);
			
			if(authCodeVO.getRtnCd() == 0) {
				SendSmsVO sendSmsVo = new SendSmsVO();
				sendSmsVo.setExchangeId(exchangeId);
				sendSmsVo.setUserId(userDetails.getUserId());
				sendSmsVo.setTo(authCodeVO.getAuthMeansKeyVal());
				String smsMsg = String.format("[ALIBIT] Open API 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지"
									, authCodeVO.getAuthCd());
				sendSmsVo.setMsg(smsMsg);
				sendSmsVo.setMsgOption("sms");
				
				result = commService.sendSms(sendSmsVo);
			} else {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-1186);
			result.setRtnMsg("인증번호 발송에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/api/createApi", method = RequestMethod.POST)
	@ResponseBody
	public OpenApiVO createApi(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid CreateApiVO createApiVO
			, BindingResult bindingResult) throws Exception {
		OpenApiVO openApiVO = new OpenApiVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user.getAuthLevel() > 1) {
				if(bindingResult.hasErrors()) {
					ObjectError error = bindingResult.getAllErrors().get(0);
					
					openApiVO.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
					openApiVO.setRtnMsg(error.getDefaultMessage());
				} else {
					
					String regExp = "^([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
							+ "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
							+ "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
							+ "([01]?\\d?\\d|2[0-4]\\d|25[0-5])$";
					
					if( createApiVO.getAccessIp() != null
							&& !"".equals(createApiVO.getAccessIp())
							&& !createApiVO.getAccessIp().matches(regExp)) {
						openApiVO.setRtnCd(-5082);
					} else {
						createApiVO.setExchangeId(CommonUtils.getExchangeId(request));
						createApiVO.setUserId(user.getUserId());
						
						openApiVO = yahobitOpenApiService.createApi(createApiVO);
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			openApiVO.setRtnCd(-5084);
			openApiVO.setRtnMsg("API 등록에 실패하였습니다.");
		}
		
		return openApiVO;
	}
	
	@RequestMapping(value = "/alibit/api/updateApiStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updateApiStatus(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ModApiVO modApiVO
			, BindingResult bindingResult) throws Exception {
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user.getAuthLevel() > 1) {
				if(bindingResult.hasErrors()) {
					ObjectError error = bindingResult.getAllErrors().get(0);
					
					result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
					result.setRtnMsg(error.getDefaultMessage());
				} else {
					modApiVO.setExchangeId(CommonUtils.getExchangeId(request));
					modApiVO.setUserId(user.getUserId());
					
					yahobitOpenApiService.updateApiInfo(modApiVO);
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5085);
			result.setRtnMsg("API 상태 변경에 실패하였습니다.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/alibit/api/deleteApi", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO deleteApi(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ModApiVO modApiVO
			, BindingResult bindingResult) throws Exception {
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user.getAuthLevel() > 1) {
				if(bindingResult.hasErrors()) {
					ObjectError error = bindingResult.getAllErrors().get(0);
					
					result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
					result.setRtnMsg(error.getDefaultMessage());
				} else {
					modApiVO.setExchangeId(CommonUtils.getExchangeId(request));
					modApiVO.setUserId(user.getUserId());
					
					yahobitOpenApiService.deleteApiInfo(modApiVO);
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5086);
			result.setRtnMsg("API 삭제에 실패하였습니다.");
		}
		
		return result;
	}
}
