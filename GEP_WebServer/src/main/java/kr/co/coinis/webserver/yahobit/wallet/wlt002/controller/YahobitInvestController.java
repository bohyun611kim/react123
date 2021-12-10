/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt002.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.service.YahobitWalletService;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt002.service.YahobitInvestService;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt002.controller 
 *    |_ YahobitInvestController.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 2:53:23
 * @version : 
 * @author : kangn
 */
@Controller
public class YahobitInvestController {

	private static final Logger logger = LoggerFactory.getLogger(YahobitInvestController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private YahobitInvestService yahobitInvestService;
	
	@Autowired
	private YahobitWalletService yahobitWalletService;
	
	@Autowired
	private CommService commService;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@RequestMapping(value = "alibit/invest")
	public ModelAndView moveNotice(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt002/invest");
		
		Gson gson = new Gson();
		
		try {
			// 거래소에 상장된 코인정보
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", CommonUtils.getExchangeId(request));
			List<Map<String, Object>> exchangeCoinInfoMap = yahobitWalletService.selectCoinInfoByExchangeId(paramMap);
			model.addObject("exchangeCoinInfo", gson.toJson(exchangeCoinInfoMap) );
			
			// 기본 기축통화 및 USD 관련정보
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			//CoinMgtRefInfoVO coinMgtRefInfo_USD = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(11);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			//defaultCoinInfo.add(coinMgtRefInfo_USD);
			model.addObject("defaultCoinInfo", gson.toJson(defaultCoinInfo) );
			
			// 코인관리 기준정보 내려준다.
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = yahobitWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", gson.toJson(coinMgtRefInfoMap));

			model.addObject("authLevel", user.getAuthLevel());
			model.addObject("authMeanCd", user.getAuthMeansCd());
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		return model;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유자산을 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserPossessionInfo
	 * @date : 2019. 5. 3.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserPossessionInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUserPossessionInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<Map<String, Object>> userPossessionList = yahobitInvestService.retrievePossessionInfo(paramMap);
			
			double possess_grand_total_by_bc = 0;
			double my_all_possess_grand_total_by_bc = 0;
			for(Map<String, Object> possess : userPossessionList) {
				possess_grand_total_by_bc += Double.valueOf( CommonUtils.strNlv(possess.get("TOTAL_USABLE_BY_BC"), "0") );
				my_all_possess_grand_total_by_bc += Double.valueOf( CommonUtils.strNlv(possess.get("TOTAL_BY_BC"), "0") );
			}
			for(Map<String, Object> possess : userPossessionList) {
				//double total_useable_possess_by_bc = Double.valueOf( CommonUtils.strNlv(possess.get("TOTAL_USABLE_BY_BC"), "0") );
				double total_possess_by_bc = Double.valueOf( CommonUtils.strNlv(possess.get("TOTAL_BY_BC"), "0") );
				double poss_percent = ((total_possess_by_bc / my_all_possess_grand_total_by_bc) * 100);
				possess.put("GRAND_ALL_TOTAL_POSS_BY_BC", my_all_possess_grand_total_by_bc);	// 소유코인 Total 환산가
				possess.put("GRAND_TOTAL_POSS_BY_BC", possess_grand_total_by_bc);				// 소유코인 사용가능 Total 환산가
				possess.put("POSS_PERCENT_BY_BC", poss_percent);
			}
			
			return CommonUtils.numericConvertListMapValue(userPossessionList);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유코인의 평가금액/평가손익을 계산하여 가져온다. 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserEstimatedPossessionInfo
	 * @date : 2019. 5. 3.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserEstimatedPossessionInfo", method = RequestMethod.POST)
	public Map<String, Object> selectUserEstimatedPossessionInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> userEstimatedPossessionInfo = yahobitInvestService.retrieveUserEstimatedPossessionInfo(paramMap);
			
			return CommonUtils.numericConvertMapValue(userEstimatedPossessionInfo);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 보유코인별 평가금액/평가손익을 계산하여 가져온다. 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserPossessionCoinList
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserPossessionCoinList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUserPossessionCoinList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			List<Map<String, Object>> userPossessionCoinList = yahobitInvestService.selectUserPossessionCoinList(paramMap);
			
			return CommonUtils.numericConvertListMapValue(userPossessionCoinList);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	// Paging Count.
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserPossessionCoinListCount", method = RequestMethod.POST)
	public Map<String, Object> selectUserPossessionCoinListCount(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> userPossessionCoinListCount = yahobitInvestService.selectUserPossessionCoinListCount(paramMap);
			
			return userPossessionCoinListCount;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 거래내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserTradingHistoryList
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param iOrdTypeCd
	 * @param strStartDate
	 * @param strEndDate
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserTradingHistoryList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUserTradingHistoryList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = true) int iCoinNo
			, @RequestParam(value = "sellBuyRecogCd", defaultValue = "9999", required = true) int iSellBuyRecogCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("SELL_BUY_RECOG_CD", iSellBuyRecogCd );// 1 : 매수, 2: 매도, 9999: ALL
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			List<Map<String, Object>> userPossessionCoinList = yahobitInvestService.selectUserTradingHistoryList(paramMap);
			
			return CommonUtils.numericConvertListMapValue(userPossessionCoinList);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	// Paging Count.
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUserTradingHistoryListCount", method = RequestMethod.POST)
	public Map<String, Object> selectUserTradingHistoryListCount(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = true) int iCoinNo
			, @RequestParam(value = "sellBuyRecogCd", defaultValue = "9999", required = true) int iSellBuyRecogCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID",  strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("SELL_BUY_RECOG_CD", iSellBuyRecogCd );// 1 : 매수, 2: 매도, 9999: ALL
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			Map<String, Object> userPossessionCoinListCount = yahobitInvestService.selectUserTradingHistoryListCount(paramMap);
			
			return CommonUtils.numericConvertMapValue(userPossessionCoinListCount);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 미체결 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUsrNonContractList
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param iSellBuyRecogCd
	 * @param strStartDate
	 * @param strEndDate
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUsrNonContractList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUsrNonContractList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = true) int iCoinNo
			, @RequestParam(value = "sellBuyRecogCd", defaultValue = "9999", required = true) int iSellBuyRecogCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("SELL_BUY_RECOG_CD", iSellBuyRecogCd );// 1 : 매수, 2: 매도, 9999: ALL
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 12 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 12);					// 한페이지에 보여줄 페이지 갯수
			List<Map<String, Object>> userPossessionCoinList = yahobitInvestService.selectUsrNonContractList(paramMap);
			
			return CommonUtils.numericConvertListMapValue(userPossessionCoinList);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	// Paging Count.
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectUsrNonContractListCount", method = RequestMethod.POST)
	public Map<String, Object> selectUsrNonContractListCount(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = true) int iCoinNo
			, @RequestParam(value = "sellBuyRecogCd", defaultValue = "9999", required = true) int iSellBuyRecogCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID",  strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("SELL_BUY_RECOG_CD", iSellBuyRecogCd );// 1 : 매수, 2: 매도, 9999: ALL
			Map<String, Object> userPossessionCoinListCount = yahobitInvestService.selectUsrNonContractListCount(paramMap);
			
			return CommonUtils.numericConvertMapValue(userPossessionCoinListCount);
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 주문취소
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : cancelNonContract
	 * @date : 2019. 5. 4.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 4.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param lNonContractOrderNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/cancelNonContract", method = RequestMethod.POST)
	public ResultVO cancelNonContract(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "nonContractOrderNo", defaultValue = "0", required = true) long lNonContractOrderNo
			) throws Exception {
		
		ResultVO result = new ResultVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("NON_CONTRACT_ORD_NO", lNonContractOrderNo);
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			SendOrderCancelVO vo = yahobitInvestService.selectNonContractInfo(paramMap);
			
			if(vo != null) {
				vo.setPublicIp(CommonUtils.getClientIpAddr(request));
				vo.setAutoMiningYn(0);
				
				result = commService.sendOrderCancel(vo);
				result.setRtnCd(0);
			} else {
				result.setRtnCd(-5508);
				result.setRtnMsg("요청한 주문이 존재하지 않습니다");
			}

			return result;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5509);
			result.setRtnMsg("주문취소 요청에 실패하였습니다");
			return result;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 잔고변경이력 리스트를 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinBalanceChangeHistoryList
	 * @date : 2019. 5. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param strStartDate
	 * @param strEndDate
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectCoinBalanceChangeHistoryList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectCoinBalanceChangeHistoryList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = false) int iCoinNo
			, @RequestParam(value = "chgRsnCd", defaultValue = "9999", required = false) int iChgRsnCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("CHG_REAS_CD", iChgRsnCd );			// 9999: ALL
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			List<Map<String, Object>> coinBalChgHistListMap = yahobitInvestService.selectCoinBalanceChangeHistoryList(paramMap);
			
			return coinBalChgHistListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectCoinBalanceChangeHistoryListCount", method = RequestMethod.POST)
	public Map<String, Object> selectCoinBalanceChangeHistoryListCount(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "9999", required = false) int iCoinNo
			, @RequestParam(value = "chgRsnCd", defaultValue = "9999", required = false) int iChgRsnCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );					// 9999: ALL
			paramMap.put("CHG_REAS_CD", iChgRsnCd );			// 9999: ALL
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			Map<String, Object> coinBalChgHistCntMap = yahobitInvestService.selectCoinBalanceChangeHistoryListCount(paramMap);
			
			return coinBalChgHistCntMap;
			
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : CODE_ID에 해당하는 코드정보를 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfo
	 * @date : 2019. 5. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strCodeId
	 * @param strLangCd
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt002/selectCodeInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> selectCodeInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "codeId", defaultValue = "C0001", required = false) String strCodeId
			, @RequestParam(value = "langCd", defaultValue = "0", required = false) String strLangCd
			) throws Exception {
		
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("CODE_ID", strCodeId);
			paramMap.put("LANG_CD", strLangCd );
			List<Map<String, Object>> codeInfoList = yahobitInvestService.selectCodeInfo(paramMap);
			
			return codeInfoList;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

}
