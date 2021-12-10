/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd002.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.JSONConverter;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendCouponOrderVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;
import kr.co.coinis.webserver.holdport.product.prd002.service.HoldportCouponService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.coupon 
 *    |_ CouponController.java
 * 
 * </pre>
 */
@Controller
public class CouponController {

	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	
	@Value("${attach.file.save.path}")
	private String path;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private HoldportCouponService holdportCouponService;
	
	@Autowired
	private CommService commService; 

	@RequestMapping(value = "/site/product/coupon", method=RequestMethod.GET)
	public ModelAndView moveIntro(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getSitePackageKey(request) + "/product/prd002/coupon");

		try {


			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
			//CommonUtils.adaptLocale(request, locale, model);
			String strUserId = user.getUserId();

			if (locale == null ||  locale.equalsIgnoreCase("")) {
				model.addObject("locale", "ko");
			} else {
				model.addObject("locale", locale);
			}

			Map<String, String> param = new HashMap<>();
			param.put("EXCHANGE_ID", CommonUtils.getNewExchangeId(request));
			param.put("USER_ID", strUserId );

			Map<String, Object> userCouponAmount = holdportCouponService.selectUserCouponAmount(param);

			model.addObject("couponAmount", Double.valueOf(CommonUtils.strNlv(userCouponAmount.get("COUPON_AMOUNT"), "0")));

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		return model;		

	}

	@RequestMapping(value="/site/product/coupon/doBuyCoupon", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doBuyCoupon(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendCouponOrderVO sendCouponOrderVO
			, BindingResult bindingResult) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user.getAuthLevel() > 1) {
				if(bindingResult.hasErrors()) {
					ObjectError error = bindingResult.getAllErrors().get(0);
					
					result.setRtnCd(Integer.parseInt(error.getDefaultMessage()));
					result.setRtnMsg(error.getDefaultMessage());
				} else {
					if(CommonUtils.isLogin(user)) {
						
						String exchangeId = CommonUtils.getNewExchangeId(request);
						
						for (int i = 0 ; i < sendCouponOrderVO.getOrdCouponAmount() ; i++) {
							SendOrderVO vo = new SendOrderVO();
							vo.setExchangeId(exchangeId);
							vo.setUserId(user.getUserId());
							vo.setMktGrpId("MKT_GRP_0005");
							vo.setMktId("HOLDPORT_KRW");
							vo.setItemCode("HPCKRW");
							vo.setOrdQty(sendCouponOrderVO.getOrdQty());
							vo.setOrdBonusQty(sendCouponOrderVO.getOrdBonusQty());
							vo.setOrdPrice(1);
							vo.setOrdTypeCd(1);
							vo.setOrdPriceTypeCd(1);
							vo.setSellBuyRecogCd(1);
							vo.setPublicIp(CommonUtils.getClientIpAddr(request));
							vo.setAutoMiningYn(0);

							result = commService.sendOrder(vo);
						}
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5054);
			result.setRtnMsg("매수 주문에 실패하였습니다");
		}
		
		return result;
	}


	@ResponseBody
	@RequestMapping(value = "site/product/coupon/selectUserCouponAmount", method = RequestMethod.POST)
	public Map<String, Object> selectUserCouponAmount(HttpServletRequest request, HttpSession session
			) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> userCouponAmount = holdportCouponService.selectUserCouponAmount(paramMap);

			return CommonUtils.numericConvertMapValue(userCouponAmount);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "site/product/coupon/selectUserCouponHistoryList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUserCouponHistoryList(HttpServletRequest request, HttpSession session
			) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<Map<String, Object>> userPossessionCoinList = holdportCouponService.selectUserCouponHistoryList(paramMap);

			return CommonUtils.numericConvertListMapValue(userPossessionCoinList);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}




}

