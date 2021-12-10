/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.controller;

import java.util.HashMap;
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

import kr.co.coinis.webserver.coinis.order.odr002.service.OutstandingOrdersService;
import kr.co.coinis.webserver.coinis.order.odr002.vo.ReqNonContractVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.JSONConverter;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.controller 
 *    |_ OutstandingOrdersController.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:25:42
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class OutstandingOrdersController {
	
	private static final Logger logger = LoggerFactory.getLogger(OutstandingOrdersController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private CommService commService;

	@Autowired
	private OutstandingOrdersService outstandingOrdersService;
	
	@RequestMapping(value = "coinis/outstandingOrders")
	public ModelAndView moveOutstandingOrders(HttpServletRequest request, HttpSession session) {
		
		CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		ReqNonContractVO vo = new ReqNonContractVO();
		vo.setExchangeId(CommonUtils.getExchangeId(request));
		vo.setUserId(user.getUserId());
		vo.setPageNum(0);
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			result = outstandingOrdersService.selectNonContractAll(vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/order/odr002/outstandingOrders");
		model.addObject("size", result.get("size"));
		model.addObject("nonContractList", JSONConverter.objectToString(result.get("data")));
		
		return model;
	}
	
	@RequestMapping(value="/coinis/outstandingOrders/list")
	@ResponseBody
	public Map<String, Object> selectNonContractList(HttpServletRequest request, HttpSession session) {
		CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		pageNum = (pageNum - 1) * 10;
		
		ReqNonContractVO vo = new ReqNonContractVO();
		vo.setExchangeId(CommonUtils.getExchangeId(request));
		vo.setUserId(user.getUserId());
		vo.setPageNum(pageNum);
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			result = outstandingOrdersService.selectNonContractAll(vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/coinis/outstandingOrders/cancelOrder")
	@ResponseBody
	public ResultVO cancelOrder(HttpServletRequest request, HttpSession session
			, @RequestParam("ordNo") long ordNo
			, @RequestParam("itemCode") String itemCode) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("ordNo", ordNo);
				param.put("itemCode", itemCode);
				
				SendOrderCancelVO vo = outstandingOrdersService.selectOrderCancelInfo(param);
				
				if(vo != null) {
					vo.setPublicIp(CommonUtils.getClientIpAddr(request));
					vo.setAutoMiningYn(0);
					
					result = commService.sendOrderCancel(vo);
				} else {
					result.setRtnCd(-1);
					result.setRtnMsg("요청한 주문이 존재하지 않습니다");
				}
			} else {
				result.setRtnCd(-1);
				result.setRtnMsg("로그인 만료");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setRtnCd(-1);
			result.setRtnMsg("주문 취소 실패");
		}
		
		return result;
	}
	
}
