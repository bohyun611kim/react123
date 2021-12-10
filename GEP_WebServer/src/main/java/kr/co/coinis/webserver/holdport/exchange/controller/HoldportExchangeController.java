/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.exchange.controller;

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
import kr.co.coinis.webserver.common.utils.JSONConverter;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendMktOrderVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;
import kr.co.coinis.webserver.holdport.exchange.service.HoldportExchangeService;
import kr.co.coinis.webserver.holdport.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.holdport.exchange.vo.ContractVO;
import kr.co.coinis.webserver.holdport.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.holdport.exchange.vo.RealContractVO;
import kr.co.coinis.webserver.holdport.home.service.HoldportHomeService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.exchange.controller 
 *    |_ YahobitExchangeController.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:15:28
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class HoldportExchangeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HoldportExchangeController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldportExchangeService holdportExchangeService;
	
	@Autowired
	private HoldportHomeService holdportHomeService;
	
	@Autowired
	private CommService commService; 

	@RequestMapping(value="/site/exchange", method=RequestMethod.GET)
	public ModelAndView moveExchange(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		List<String> mktGrpIdList = new ArrayList<>();
		
		try {
			mktGrpIdList = commService.selectMktGrpList(CommonUtils.getNewExchangeId(request));
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		// 거래소 화면 테마 설정
		String style = "y_light";
		Cookie[] cookie = request.getCookies();
		
		for(Cookie temp : cookie) {
			if("style".equals(temp.getName())) {
				style = temp.getValue();
				break;
			}
		}
		
		ModelAndView model = new ModelAndView();

		logger.error("------------ css:" + style + ", coin" + coin);
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/exchange/exchange");

		try {

			String key = "localInfo" + request.getRequestedSessionId();
			String locale = redisSessionRepository.get(key);
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
			//CommonUtils.adaptLocale(request, locale, model);


			model.addObject("style", style);
			model.addObject("coin", coin);
			model.addObject("notice", holdportHomeService.selectNoticeList(CommonUtils.getNewExchangeId(request)));
			if(!"".equals(itemCode)) {
				model.addObject("itemCode", itemCode + "KRW");
			}
			model.addObject("list", JSONConverter.objectToString(mktGrpIdList));

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return model;
	}
	
	@RequestMapping(value="/site/exchange_new", method=RequestMethod.GET)
	public ModelAndView newExchange(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		ModelAndView model = this.moveExchange(request, session, coin, itemCode);
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/exchange/exchange_new");
		return model;
	}
	
	@RequestMapping(value="/site/exchange_debug", method=RequestMethod.GET)
	public ModelAndView debugExchange(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		ModelAndView model = this.moveExchange(request, session, coin, itemCode);
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/exchange/exchange_debug");
		return model;
	}
	
	@RequestMapping(value="/site/exchange/getItemCodeInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getItemCodeInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getNewExchangeId(request));
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			if(!"".equals(coin)) {
				param.put("itemCode", coin);
			} else if(!"".equals(itemCode)) {
				param.put("itemCode", itemCode);
			}
			
			result = holdportExchangeService.selectItemCodeInfo(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getMktItemCodeInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMktItemCodeInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "mkt", defaultValue="", required = false) String mkt
			, @RequestParam(value = "grp", defaultValue="", required = false) String grp
			, @RequestParam(value = "code", defaultValue="", required = false) String code) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getNewExchangeId(request));
			param.put("mktId", mkt);
			param.put("mktGrpId", grp);
			param.put("itemCode", code);
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = holdportExchangeService.selectMktItemCodeInfo(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getCoinInfo", method=RequestMethod.POST)
	@ResponseBody
	public CoinInfoVO getCoinInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", required = true) int coinNo) {
		
		CoinInfoVO result = null;
		
		try {
			result = holdportExchangeService.selectCoinInfo(coinNo);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getMarket", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMarket(HttpServletRequest request, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getNewExchangeId(request));
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = holdportExchangeService.getMarket(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getRealContract", method=RequestMethod.POST)
	@ResponseBody
	public List<RealContractVO> getRealContract(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "mktGrpId", required = true) String mktGrpId
			, @RequestParam(value = "itemCode", required = true) String itemCode
			, @RequestParam(value = "contractDt", required = true) String contractDt) {
		
		List<RealContractVO> result = null;
		
		try {
			Map<String, String> param = new HashMap<>();
			param.put("mktGrpId", mktGrpId);
			param.put("itemCode", itemCode);
			param.put("contractDt", contractDt);
			
			result = holdportExchangeService.selectRealContract(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getDailySise", method=RequestMethod.POST)
	@ResponseBody
	public List<DailySiseVO> getDailySise(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "mktGrpId", required = true) String mktGrpId
			, @RequestParam(value = "itemCode", required = true) String itemCode
			, @RequestParam(value = "tradeDt", required = true) String tradeDt) {
		
		List<DailySiseVO> result = null;
		
		try {
			Map<String, String> param = new HashMap<>();
			param.put("mktGrpId", mktGrpId);
			param.put("itemCode", itemCode);
			param.put("tradeDt", tradeDt);
			
			result = holdportExchangeService.selectDailySise(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/getContract", method=RequestMethod.POST)
	@ResponseBody
	public List<ContractVO> getContract(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "mktGrpId", required = true) String mktGrpId
			, @RequestParam(value = "itemCode", required = true) String itemCode
			, @RequestParam(value = "contractDt", required = true) String contractDt) {
		
		List<ContractVO> result = new ArrayList<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, String> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("itemCode", itemCode);
				param.put("contractDt", contractDt);
				
				result = holdportExchangeService.selectContract(param);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/doPndSell", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doPndSell(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendOrderVO vo
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
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// 지정가
						vo.setSellBuyRecogCd(2);	// 매도
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5053);
			result.setRtnMsg("매도 주문에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/doPndBuy", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doPndBuy(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendOrderVO vo
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
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// 지정가
						vo.setSellBuyRecogCd(1);	// 매수
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
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
	
	@RequestMapping(value="/site/exchange/doMktSell", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doMktSell(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendMktOrderVO sendMktOrderVO
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
						
						SendOrderVO vo = new SendOrderVO();
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktGrpId(sendMktOrderVO.getMktGrpId());
						vo.setMktId(sendMktOrderVO.getMktId());
						vo.setItemCode(sendMktOrderVO.getItemCode());
						vo.setOrdQty(sendMktOrderVO.getOrdQty());
						vo.setOrdPrice(sendMktOrderVO.getOrdPrice());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(2);	// 시장가
						vo.setSellBuyRecogCd(2);	// 매도
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5053);
			result.setRtnMsg("매도 주문에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/doMktBuy", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doMktBuy(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendMktOrderVO sendMktOrderVO
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
						
						SendOrderVO vo = new SendOrderVO();
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktGrpId(sendMktOrderVO.getMktGrpId());
						vo.setMktId(sendMktOrderVO.getMktId());
						vo.setItemCode(sendMktOrderVO.getItemCode());
						vo.setOrdQty(sendMktOrderVO.getOrdQty());
						vo.setOrdPrice(sendMktOrderVO.getOrdPrice());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(2);	// 시장가
						vo.setSellBuyRecogCd(1);	// 매수
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
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
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : doAutoMining
	 * @date : 2019. 7. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 2.		민곰				최초 작성 
	 *	2019. 7. 5.		kangnaru			자전거래의경우 매수 => 매도 의 순서로 변경함
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param vo
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/site/exchange/doAutoMining", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doAutoMining(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendOrderVO vo
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
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktId(vo.getMktId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// 시장가
						vo.setSellBuyRecogCd(1);	// 매수
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(1);
						
						result = commService.sendOrder(vo);
						
						vo.setSellBuyRecogCd(2);	// 매도
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5055);
			result.setRtnMsg("자동 채굴에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/doCancel", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doCancelSell(HttpServletRequest request, HttpSession session
			, @RequestParam("tranNo") String tranNo
			, @RequestParam("ordNo") String ordNo
			, @RequestParam("excNo") String excNo
			, @RequestParam("itemCode") String itemCode) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("tranNo", tranNo);
				param.put("ordNo", ordNo);
				param.put("excNo", excNo);
				param.put("itemCode", itemCode);
				
				SendOrderCancelVO vo = holdportExchangeService.selectOrderCancelInfo(param);
				
				if(vo != null) {
					vo.setPublicIp(CommonUtils.getClientIpAddr(request));
					vo.setAutoMiningYn(0);
					
					result = commService.sendOrderCancel(vo);
				} else {
					result.setRtnCd(-5056);
					result.setRtnMsg("주문이 존재하지 않습니다");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("주문 취소에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/doCancelPrice", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO cancelSellAll(HttpServletRequest request, HttpSession session
			, @RequestParam("mktGrpId") String mktGrpId
			, @RequestParam("mktId") String mktId
			, @RequestParam("ordPrc") double ordPrc
			, @RequestParam("itemCode") String itemCode) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("mktId", mktId);
				param.put("itemCode", itemCode);
				param.put("ordPrc", ordPrc);
				
				List<SendOrderCancelVO> vo = holdportExchangeService.selectOrderCancelAllInfoOfPrice(param);
				
				if(vo.size() > 0) {
					result = commService.sendOrderCancelAll(vo, CommonUtils.getClientIpAddr(request), 0);
				} else {
					result.setRtnCd(-5057);
					result.setRtnMsg("주문 취소에 실패하였습니다");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("주문 취소 실패");
		}
		
		return result;
	}

	@RequestMapping(value="/site/exchange/cancelSellAll", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO cancelSellAll(HttpServletRequest request, HttpSession session
			, @RequestParam("mktGrpId") String mktGrpId
			, @RequestParam("mktId") String mktId
			, @RequestParam("itemCode") String itemCode) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("mktId", mktId);
				param.put("itemCode", itemCode);
				param.put("sellBuyCd", 2);
				
				List<SendOrderCancelVO> vo = holdportExchangeService.selectOrderCancelAllInfo(param);
				
				if(vo.size() > 0) {
					result = commService.sendOrderCancelAll(vo, CommonUtils.getClientIpAddr(request), 0);
				} else {
					result.setRtnCd(-5058);
					result.setRtnMsg("매도 주문이 존재하지 않습니다");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("주문 취소 실패");
		}
		
		return result;
	}
	
	@RequestMapping(value="/site/exchange/cancelBuyAll", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO cancelBuyAll(HttpServletRequest request, HttpSession session
			, @RequestParam("mktGrpId") String mktGrpId
			, @RequestParam("mktId") String mktId
			, @RequestParam("itemCode") String itemCode) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
			if(CommonUtils.isLogin(user)) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", CommonUtils.getNewExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("mktId", mktId);
				param.put("itemCode", itemCode);
				param.put("sellBuyCd", 1);
				
				List<SendOrderCancelVO> vo = holdportExchangeService.selectOrderCancelAllInfo(param);
				
				if(vo.size() > 0) {
					result = commService.sendOrderCancelAll(vo, CommonUtils.getClientIpAddr(request), 0);
				} else {
					result.setRtnCd(-5059);
					result.setRtnMsg("매수 주문이 존재하지 않습니다");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("주문 취소 실패");
		}
		
		return result;
	}
	
}
