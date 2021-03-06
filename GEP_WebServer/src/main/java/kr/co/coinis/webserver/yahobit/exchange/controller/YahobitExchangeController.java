/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.controller;

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
import kr.co.coinis.webserver.yahobit.exchange.service.YahobitExchangeService;
import kr.co.coinis.webserver.yahobit.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.RealContractVO;
import kr.co.coinis.webserver.yahobit.home.service.YahobitHomeService;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.controller 
 *    |_ YahobitExchangeController.java
 * 
 * </pre>
 * @date : 2019. 4. 29. ?????? 9:15:28
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class YahobitExchangeController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitExchangeController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private YahobitExchangeService yahobitExchangeService;
	
	@Autowired
	private YahobitHomeService yahobitHomeService;
	
	@Autowired
	private CommService commService; 

	@RequestMapping(value="/alibit/exchange", method=RequestMethod.GET)
	public ModelAndView moveExchange(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		List<String> mktGrpIdList = new ArrayList<>();
		
		try {
			mktGrpIdList = commService.selectMktGrpList(CommonUtils.getExchangeId(request));
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		// ????????? ?????? ?????? ??????
		String style = "y_light";
		Cookie[] cookie = request.getCookies();
		
		for(Cookie temp : cookie) {
			if("style".equals(temp.getName())) {
				style = temp.getValue();
				break;
			}
		}
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getPageKey(request) + "/exchange/exchange");
		model.addObject("style", style);
		model.addObject("coin", coin);
		model.addObject("notice", yahobitHomeService.selectNoticeList(CommonUtils.getExchangeId(request)));
		if(!"".equals(itemCode)) {
			model.addObject("itemCode", itemCode + "KRW");
		}
		model.addObject("list", JSONConverter.objectToString(mktGrpIdList));
		
		return model;
	}
	
	@RequestMapping(value="/alibit/exchange/getItemCodeInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getItemCodeInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coin", defaultValue="", required = false) String coin
			, @RequestParam(value = "itemCode", defaultValue="", required = false) String itemCode) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			if(!"".equals(coin)) {
				param.put("itemCode", coin);
			} else if(!"".equals(itemCode)) {
				param.put("itemCode", itemCode);
			}
			
			result = yahobitExchangeService.selectItemCodeInfo(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getMktItemCodeInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMktItemCodeInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "mkt", defaultValue="", required = false) String mkt
			, @RequestParam(value = "grp", defaultValue="", required = false) String grp
			, @RequestParam(value = "code", defaultValue="", required = false) String code) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("mktId", mkt);
			param.put("mktGrpId", grp);
			param.put("itemCode", code);
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = yahobitExchangeService.selectMktItemCodeInfo(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getCoinInfo", method=RequestMethod.POST)
	@ResponseBody
	public CoinInfoVO getCoinInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", required = true) int coinNo) {
		
		CoinInfoVO result = null;
		
		try {
			result = yahobitExchangeService.selectCoinInfo(coinNo);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getMarket", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMarket(HttpServletRequest request, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = yahobitExchangeService.getMarket(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getRealContract", method=RequestMethod.POST)
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
			
			result = yahobitExchangeService.selectRealContract(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getDailySise", method=RequestMethod.POST)
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
			
			result = yahobitExchangeService.selectDailySise(param);
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/getContract", method=RequestMethod.POST)
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
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("itemCode", itemCode);
				param.put("contractDt", contractDt);
				
				result = yahobitExchangeService.selectContract(param);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/doPndSell", method=RequestMethod.POST)
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
						String exchangeId = CommonUtils.getExchangeId(request);
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// ?????????
						vo.setSellBuyRecogCd(2);	// ??????
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("?????? ????????? ?????? 2 ????????? ?????? ?????? ???????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5053);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/doPndBuy", method=RequestMethod.POST)
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
						String exchangeId = CommonUtils.getExchangeId(request);
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// ?????????
						vo.setSellBuyRecogCd(1);	// ??????
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("?????? ????????? ?????? 2 ????????? ?????? ?????? ???????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5054);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/doMktSell", method=RequestMethod.POST)
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
						String exchangeId = CommonUtils.getExchangeId(request);
						
						SendOrderVO vo = new SendOrderVO();
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktGrpId(sendMktOrderVO.getMktGrpId());
						vo.setMktId(sendMktOrderVO.getMktId());
						vo.setItemCode(sendMktOrderVO.getItemCode());
						vo.setOrdQty(sendMktOrderVO.getOrdQty());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(2);	// ?????????
						vo.setSellBuyRecogCd(2);	// ??????
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("?????? ????????? ?????? 2 ????????? ?????? ?????? ???????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5053);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/doMktBuy", method=RequestMethod.POST)
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
						String exchangeId = CommonUtils.getExchangeId(request);
						
						SendOrderVO vo = new SendOrderVO();
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktGrpId(sendMktOrderVO.getMktGrpId());
						vo.setMktId(sendMktOrderVO.getMktId());
						vo.setItemCode(sendMktOrderVO.getItemCode());
						vo.setOrdQty(sendMktOrderVO.getOrdQty());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(2);	// ?????????
						vo.setSellBuyRecogCd(1);	// ??????
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("?????? ????????? ?????? 2 ????????? ?????? ?????? ???????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5054);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : 
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : doAutoMining
	 * @date : 2019. 7. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 2.		??????				?????? ?????? 
	 *	2019. 7. 5.		kangnaru			????????????????????? ?????? => ?????? ??? ????????? ?????????
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param vo
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/alibit/exchange/doAutoMining", method=RequestMethod.POST)
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
						String exchangeId = CommonUtils.getExchangeId(request);
						
						vo.setExchangeId(exchangeId);
						vo.setUserId(user.getUserId());
						vo.setMktId(vo.getMktId());
						vo.setOrdTypeCd(1);
						vo.setOrdPriceTypeCd(1);	// ?????????
						vo.setSellBuyRecogCd(1);	// ??????
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(1);
						
						result = commService.sendOrder(vo);
						
						vo.setSellBuyRecogCd(2);	// ??????
						result = commService.sendOrder(vo);
					}
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("?????? ????????? ?????? 2 ????????? ?????? ?????? ???????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5055);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/doCancel", method=RequestMethod.POST)
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
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("tranNo", tranNo);
				param.put("ordNo", ordNo);
				param.put("excNo", excNo);
				param.put("itemCode", itemCode);
				
				SendOrderCancelVO vo = yahobitExchangeService.selectOrderCancelInfo(param);
				
				if(vo != null) {
					vo.setPublicIp(CommonUtils.getClientIpAddr(request));
					vo.setAutoMiningYn(0);
					
					result = commService.sendOrderCancel(vo);
				} else {
					result.setRtnCd(-5056);
					result.setRtnMsg("????????? ???????????? ????????????");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("?????? ????????? ?????????????????????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/cancelSellAll", method=RequestMethod.POST)
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
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("mktId", mktId);
				param.put("itemCode", itemCode);
				param.put("sellBuyCd", 2);
				
				List<SendOrderCancelVO> vo = yahobitExchangeService.selectOrderCancelAllInfo(param);
				
				if(vo.size() > 0) {
					result = commService.sendOrderCancelAll(vo, CommonUtils.getClientIpAddr(request), 0);
				} else {
					result.setRtnCd(-5058);
					result.setRtnMsg("?????? ????????? ???????????? ????????????");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("?????? ??????");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/exchange/cancelBuyAll", method=RequestMethod.POST)
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
				param.put("exchangeId", CommonUtils.getExchangeId(request));
				param.put("userId", user.getUserId());
				param.put("mktGrpId", mktGrpId);
				param.put("mktId", mktId);
				param.put("itemCode", itemCode);
				param.put("sellBuyCd", 1);
				
				List<SendOrderCancelVO> vo = yahobitExchangeService.selectOrderCancelAllInfo(param);
				
				if(vo.size() > 0) {
					result = commService.sendOrderCancelAll(vo, CommonUtils.getClientIpAddr(request), 0);
				} else {
					result.setRtnCd(-5059);
					result.setRtnMsg("?????? ????????? ???????????? ????????????");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5057);
			result.setRtnMsg("?????? ??????");
		}
		
		return result;
	}
	
}
