package kr.co.coinis.webserver.coinis.exchange.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.exchange.service.ExchangeService;
import kr.co.coinis.webserver.coinis.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.DefaultItemCodeVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.JSONConverter;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;

/**
 * <pre>
 * kr.co.coinis.webserver.exchange.controller 
 *    |_ ExchangeController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:05:03
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class ExchangeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private CommService commService;
	
	/**
	 * <pre>
	 * 1. 개요 : 거래소 화면 이동
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : moveTrade
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/coinis/exchange", method = RequestMethod.GET)
	public ModelAndView moveTrade(HttpSession session, HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView();
		
		try {
			DefaultItemCodeVO defaultItemCodeVO = exchangeService.selectDefaultItemCode(CommonUtils.getExchangeId(request));
			List<String> mktGrpIdList = commService.selectMktGrpList(CommonUtils.getExchangeId(request));
			
			if(defaultItemCodeVO != null) {
				model.setViewName(CommonUtils.getPageKey(request) + "/exchange/exchange");
				model.addObject("mktId", defaultItemCodeVO.getMktId());
				model.addObject("mktGrpId", defaultItemCodeVO.getMktGrpId());
				model.addObject("itemCode", defaultItemCodeVO.getItemCode());
				model.addObject("list", JSONConverter.objectToString(mktGrpIdList));
				
				Map<String, Object> param = new HashMap<>();
				param.put("codeId", "C0019");
				param.put("langCd", "en");
				model.addObject("statusCode", JSONConverter.objectToString(exchangeService.selectCodeInfo(param)));
				
				param.put("codeId", "C0003");
				model.addObject("sellBuyCode", JSONConverter.objectToString(exchangeService.selectCodeInfo(param)));
			} else {
				model.setViewName(CommonUtils.getPageKey(request) + "/exchange/error");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 거래소 화면 이동 itemCode가 있는 경우
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : moveTrade
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @param request
	 * @param itemCode
	 * @return
	 * @throws HttpException
	 */
	@RequestMapping(value = "/coinis/exchange/{itemCode}", method = RequestMethod.GET)
	public ModelAndView moveTrade(HttpSession session, HttpServletRequest request
			, @PathVariable String itemCode) throws HttpException {
		
		ModelAndView model = new ModelAndView();
		
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("itemCode", itemCode);
			
			DefaultItemCodeVO defaultItemCodeVO = exchangeService.checkItemCode(param);
			List<String> mktGrpIdList = commService.selectMktGrpList(CommonUtils.getExchangeId(request));
			
			if(defaultItemCodeVO != null) {
				model.setViewName(CommonUtils.getPageKey(request) + "/exchange/exchange");
				model.addObject("mktId", defaultItemCodeVO.getMktId());
				model.addObject("mktGrpId", defaultItemCodeVO.getMktGrpId());
				model.addObject("itemCode", defaultItemCodeVO.getItemCode());
				model.addObject("list", JSONConverter.objectToString(mktGrpIdList));
				
				Map<String, Object> condition = new HashMap<>();
				condition.put("codeId", "C0019");
				condition.put("langCd", "en");
				model.addObject("statusCode", JSONConverter.objectToString(exchangeService.selectCodeInfo(condition)));
				
				condition.put("codeId", "C0003");
				model.addObject("sellBuyCode", JSONConverter.objectToString(exchangeService.selectCodeInfo(condition)));
			} else {
				model.setViewName(CommonUtils.getPageKey(request) + "/exchange/error");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 마켓 리스트, 보유 코인 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getItemInfo
	 * @date : 2019. 6. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param mktId
	 * @param mktGrpId
	 * @param itemCode
	 * @return
	 */
	@RequestMapping(value="/coinis/exchange/getMarketInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMarketInfo(HttpServletRequest request, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, String> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			
			if(user != null) {
				param.put("userId", user.getUserId());
			}
			
			result = exchangeService.selectMarketList(param);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 :itemCode 에 해당하는 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getItemInfo
	 * @date : 2019. 6. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param mktId
	 * @param mktGrpId
	 * @param itemCode
	 * @return
	 */
	@RequestMapping(value="/coinis/exchange/getItemInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getItemInfo(HttpServletRequest request, HttpSession session
			, @RequestParam String mktId
			, @RequestParam String mktGrpId
			, @RequestParam String itemCode) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("mktId", mktId);
			param.put("mktGrpId", mktGrpId);
			param.put("itemCode", itemCode);
			
			if(user != null) {
				param.put("userId", user.getUserId());
			}
			
			result = exchangeService.selectItemInfo(param);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 매도 주문
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : doPndSell
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param vo
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/coinis/exchange/doSell", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doPndSell(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendOrderVO vo
			, BindingResult bindingResult) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user != null) {
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
						vo.setOrdPriceTypeCd(1);	// 지정가
						vo.setSellBuyRecogCd(2);	// 매도
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5053);
			result.setRtnMsg("매도 주문에 실패하였습니다");
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 매수주문
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : doPndBuy
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param vo
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/coinis/exchange/doBuy", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doPndBuy(HttpServletRequest request, HttpSession session
			, @ModelAttribute @Valid SendOrderVO vo
			, BindingResult bindingResult) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(user != null) {
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
						vo.setOrdPriceTypeCd(1);	// 지정가
						vo.setSellBuyRecogCd(1);	// 매수
						vo.setPublicIp(CommonUtils.getClientIpAddr(request));
						vo.setAutoMiningYn(0);
						
						result = commService.sendOrder(vo);
					}
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5054);
			result.setRtnMsg("매수 주문에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/coinis/exchange/doCancel", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doCancel(HttpServletRequest request, HttpSession session
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
				
				SendOrderCancelVO vo = exchangeService.selectCancelInfo(param);
				
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

	/**
	 * <pre>
	 * 1. 개요 : 코인 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinInfo
	 * @date : 2019. 6. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param coinNo
	 * @return
	 */
	@RequestMapping(value="/coinis/exchange/getCoinInfo", method=RequestMethod.POST)
	@ResponseBody
	public CoinInfoVO selectCoinInfo(HttpServletRequest request, HttpSession session
			, @RequestParam("coinNo") String coinNo) {
	
		CoinInfoVO result = null;
		
		try {
			result = exchangeService.selectCoinInfo(coinNo);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
}
