/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.wallet.wlt002.controller.TransactionController;
import kr.co.coinis.webserver.coinis.wallet.wlt003.service.HoldingsService;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.DailyLimitQtyVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.EstimatedValueVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.PossessionInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt003.vo.ReqHoldingsVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.controller 
 *    |_ HoldingsController.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 5:18:41
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class HoldingsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HoldingsController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldingsService holdingsService;
	
	@RequestMapping(value = "coinis/holdings")
	public ModelAndView moveHolding(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt003/holdings");
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		ReqHoldingsVO reqHoldingsVO = new ReqHoldingsVO();
		reqHoldingsVO.setExchange_id(exchange_id);
		reqHoldingsVO.setUser_id(userInfo.getUserId());
		
		EstimatedValueVO estimatedValueVO = holdingsService.retrieveEstimatedValue(reqHoldingsVO);
		DailyLimitQtyVO dailyLimitQtyVO = holdingsService.retrieveDailyLimitQty(reqHoldingsVO);
		Double totalInUse = holdingsService.retrieveTotalInUse(reqHoldingsVO);
		//List<PossessionInfoVO> possessionInfoList = holdingsService.retrievePossessionInfo(reqHoldingsVO);
		
		model.addObject("estimatedValue", estimatedValueVO);
		model.addObject("dailyLimitQty", dailyLimitQtyVO);
		model.addObject("totalInUse", totalInUse);
		//model.addObject("possessionInfoList", possessionInfoList);
		
		return model;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 보유코인 현황을 얻어온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getUserPossessionList
	 * @date : 2019. 6. 19.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 19.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param reqHoldingsVO
	 * @return
	 */
	@RequestMapping(value = "coinis/wallet/wlt003/userPossession")
	@ResponseBody
	public List<PossessionInfoVO> getUserPossessionList(HttpServletRequest request, HttpSession session) {

		try {
			ReqHoldingsVO reqHoldingsVO = new ReqHoldingsVO();
			reqHoldingsVO.setExchange_id(CommonUtils.getExchangeId(request));
			reqHoldingsVO.setUser_id(((CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId())).getUserId());
			int startIndex = (reqHoldingsVO.getStartIndex() - 1) * 3;
			reqHoldingsVO.setStartIndex(startIndex);
			reqHoldingsVO.setQueryDataNum(reqHoldingsVO.getQueryDataNum());
			
			List<PossessionInfoVO> possessionInfoList = holdingsService.retrievePossessionInfo(reqHoldingsVO);
	
			return possessionInfoList;
			
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Airdrop 리스트를 받아온다 (ajax)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAirdropList
	 * @date : 2019. 6. 19.
	 * @author : yeonseo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 19.		yeonseo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param reqHoldingsVO
	 * @return
	 */
	@RequestMapping(value = "coinis/wallet/wlt003/airdropList")
	@ResponseBody
	public Map<String, Object> getAirdropList(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqHoldingsVO reqHoldingsVO) {
		
		try {
			reqHoldingsVO.setExchange_id(CommonUtils.getExchangeId(request));
			reqHoldingsVO.setUser_id(((CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId())).getUserId());
			int startIndex = (reqHoldingsVO.getStartIndex() - 1) * 3;
			reqHoldingsVO.setStartIndex(startIndex);
			reqHoldingsVO.setQueryDataNum(reqHoldingsVO.getQueryDataNum());
			
			Map<String, Object> resultMap = holdingsService.retrieveAirdropInfo(reqHoldingsVO);
			
			return resultMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 보상코인 정보를 얻어온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getRewardList
	 * @date : 2019. 6. 19.
	 * @author : yeonseo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 19.		yeonseo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param reqHoldingsVO
	 * @return
	 */
	@RequestMapping(value = "coinis/wallet/wlt003/rewardList")
	@ResponseBody
	public Map<String, Object> getRewardList(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid ReqHoldingsVO reqHoldingsVO) {

		try {
			reqHoldingsVO.setExchange_id(CommonUtils.getExchangeId(request));
			reqHoldingsVO.setUser_id(((CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId())).getUserId());
			int startIndex = (reqHoldingsVO.getStartIndex() - 1) * 3;
			reqHoldingsVO.setStartIndex(startIndex);
			reqHoldingsVO.setQueryDataNum(reqHoldingsVO.getQueryDataNum());
			
			Map<String, Object> resultMap = holdingsService.retrieveCompensatedRewardInfo(reqHoldingsVO);
	
			return resultMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
}
