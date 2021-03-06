/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt001.controller;

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
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.service.YahobitWalletService;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CodeInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinBalanceVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.DepositWithdrawMgrVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberBankAccntInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt001.controller 
 *    |_ YahobitWalletController.java
 * 
 * </pre>
 * @date : 2019. 4. 26. ?????? 1:33:19
 * @version : 
 * @author : kangn
 */
@Controller
public class YahobitWalletController {

	private static final Logger logger = LoggerFactory.getLogger(YahobitWalletController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private YahobitWalletService yahobitWalletService;
	
	@Autowired
	private CommService commService;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@RequestMapping(value = "alibit/wallet")
	public ModelAndView moveNotice(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt001/wallet");
		
		Gson gson = new Gson();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			// ???????????? ????????? ????????????
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", CommonUtils.getExchangeId(request));
			
			// 2019-08-19 ?????????
			// ????????? ?????? ?????? ?????? ?????? ???????????? ?????? ????????????????????? ?????????????????? ?????? ???????????? ??????
			//List<Map<String, Object>> exchangeCoinInfoMap = yahobitWalletService.selectCoinInfoByExchangeId(paramMap);
			List<Map<String, Object>> exchangeCoinInfoMap = yahobitWalletService.selectCoinInfoByTradeStatCd(paramMap);
			model.addObject("exchangeCoinInfo", gson.toJson(exchangeCoinInfoMap) );
			
			// ?????? ???????????? ??? USD ????????????
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			//CoinMgtRefInfoVO coinMgtRefInfo_USD = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(11);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			//defaultCoinInfo.add(coinMgtRefInfo_USD);
			model.addObject("defaultCoinInfo", gson.toJson(defaultCoinInfo) );
			
			// ???????????? ???????????? ????????????.
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = yahobitWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", gson.toJson(coinMgtRefInfoMap));

			// ????????? ??????????????? ?????????
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);
			model.addObject("authLevel", memberInfoVo.getAUTH_LEVEL());
			model.addObject("authMeanCd", memberInfoVo.getAUTH_MEANS_CD());

			// ???????????? ????????? ????????????.
			paramMap.clear();
			paramMap.put("CODE_ID", "C0058");
			List<CodeInfoVO> bankCodeInfoList = yahobitWalletService.selectCodeInfoByCodeId(paramMap);
			model.addObject("bankCodeInfoList", gson.toJson(bankCodeInfoList));
			
			// ???????????? ???????????? ????????? ????????????.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", CommonUtils.getExchangeId(request));
			Map<String, Object> ebaiMap = yahobitWalletService.selectExchangeBankAccountInfo(paramMap);
			Map<String, Object> exchangeBankAccntInfoMap = new HashMap<>();
			exchangeBankAccntInfoMap.put("EXCHANGE_ID", ebaiMap.get("EXCHANGE_ID").toString());
			exchangeBankAccntInfoMap.put("BANK_ACCNT_NO", new String((byte[])ebaiMap.get("BANK_ACCNT_NO")));
			exchangeBankAccntInfoMap.put("ACCNT_HOLDER_NM", new String((byte[])ebaiMap.get("ACCNT_HOLDER_NM")));
			exchangeBankAccntInfoMap.put("BANK_CD", ebaiMap.get("BANK_CD").toString());
			exchangeBankAccntInfoMap.put("DW_PG_MID", ebaiMap.get("DW_PG_MID").toString());
			model.addObject("exchangeBankAccntInfo", gson.toJson(exchangeBankAccntInfoMap));

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		return model;
	}
	
	/**
	 * ============================================================
	 * 						OPEN ?????? TEST???!!!!
	 * ============================================================
	 */
	/*@RequestMapping(value = "alibit/wallet_test")
	public ModelAndView walletPageTest(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt001/back_wallet");
		
		Gson gson = new Gson();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			// ???????????? ????????? ????????????
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", CommonUtils.getExchangeId(request));
			List<Map<String, Object>> exchangeCoinInfoMap = yahobitWalletService.selectCoinInfoByExchangeId(paramMap);
			model.addObject("exchangeCoinInfo", gson.toJson(exchangeCoinInfoMap) );
			
			// ?????? ???????????? ??? USD ????????????
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			//CoinMgtRefInfoVO coinMgtRefInfo_USD = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(11);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			//defaultCoinInfo.add(coinMgtRefInfo_USD);
			model.addObject("defaultCoinInfo", gson.toJson(defaultCoinInfo) );
			
			// ???????????? ???????????? ????????????.
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = yahobitWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", gson.toJson(coinMgtRefInfoMap));
			
			// ????????? ??????????????? ?????????
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);
			model.addObject("authLevel", memberInfoVo.getAUTH_LEVEL());
			model.addObject("authMeanCd", memberInfoVo.getAUTH_MEANS_CD());
			
			// ???????????? ????????? ????????????.
			paramMap.clear();
			paramMap.put("CODE_ID", "C0058");
			List<CodeInfoVO> bankCodeInfoList = yahobitWalletService.selectCodeInfoByCodeId(paramMap);
			model.addObject("bankCodeInfoList", gson.toJson(bankCodeInfoList));
			
			// ???????????? ???????????? ????????? ????????????.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", CommonUtils.getExchangeId(request));
			Map<String, Object> ebaiMap = yahobitWalletService.selectExchangeBankAccountInfo(paramMap);
			Map<String, Object> exchangeBankAccntInfoMap = new HashMap<>();
			exchangeBankAccntInfoMap.put("EXCHANGE_ID", ebaiMap.get("EXCHANGE_ID").toString());
			exchangeBankAccntInfoMap.put("BANK_ACCNT_NO", new String((byte[])ebaiMap.get("BANK_ACCNT_NO")));
			exchangeBankAccntInfoMap.put("ACCNT_HOLDER_NM", new String((byte[])ebaiMap.get("ACCNT_HOLDER_NM")));
			exchangeBankAccntInfoMap.put("BANK_CD", ebaiMap.get("BANK_CD").toString());
			exchangeBankAccntInfoMap.put("DW_PG_MID", ebaiMap.get("DW_PG_MID").toString());
			model.addObject("exchangeBankAccntInfo", gson.toJson(exchangeBankAccntInfoMap));
			
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		return model;
	}*/
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ???????????? ?????? ?????? ????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectMemberBankAccntInfo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/selectMemberBankAccntInfo", method = RequestMethod.POST)
	public Map<String, Object> selectMemberBankAccntInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> memberBankAccntInfoMap = yahobitWalletService.selectMemberBankAccntInfo(paramMap);
			memberBankAccntInfoMap.put("ACCNT_HOLDER_NM", new String((byte[])memberBankAccntInfoMap.get("ACCNT_HOLDER_NM")));
			memberBankAccntInfoMap.put("BANK_ACCNT_NO", new String((byte[])memberBankAccntInfoMap.get("BANK_ACCNT_NO")));
			
			return memberBankAccntInfoMap;
		} catch(Exception e) {
			//logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : ????????? ????????????????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : insertMemberBankAccntInfo
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBankCode
	 * @param strBankAccountNo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/insertMemberBankAccntInfo", method = RequestMethod.POST)
	public ResultVO insertMemberBankAccntInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "bankCd", defaultValue = "001", required = true) String strBankCode
			, @RequestParam(value = "bankAccntNo", defaultValue = "", required = true) String strBankAccountNo
			, @RequestParam(value = "bankAccntHldrNm", defaultValue = "", required = true) String strBankAccountHolderName
			, @RequestParam(value = "bankAccntHldrBthDay", defaultValue = "", required = true) String strBankAccountHolderBirthDay
			) throws Exception {
		
		ResultVO resVo = new ResultVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("SERVER_NO", CamelHelper.getInstance().getServerNo() );				// ???????????? ??????!
			paramMap.put("bankCode", strBankCode );
			paramMap.put("acntNo", strBankAccountNo );
			paramMap.put("idNo", strBankAccountHolderBirthDay );
			paramMap.put("acntNm", strBankAccountHolderName );
			Map<String, Object> checkBAResultMap = yahobitWalletService.checkBankAccountHolderName(paramMap);
			int resCd = Double.valueOf(checkBAResultMap.get("resCode").toString()).intValue();
			
			if(resCd == 0) {
				MemberBankAccntInfoVO bankInfoVo = new MemberBankAccntInfoVO();
				bankInfoVo.setEXCHANGE_ID(strExchangeId);
				bankInfoVo.setUSER_ID(strUserId);
				bankInfoVo.setBANK_CD(strBankCode);
				bankInfoVo.setBANK_ACCNT_NO(strBankAccountNo);
				bankInfoVo.setACCNT_HOLDER_NM(strBankAccountHolderName);
				Map<String, Object> insertResultMap = yahobitWalletService.insertMemberBankAccntInfo(bankInfoVo);
	
				logger.debug("insertMemberBankAccntInfo Result >> " + insertResultMap);
	
				resVo.setRtnCd(Double.valueOf(insertResultMap.get("V_RTN_CD").toString()).intValue());
				resVo.setRtnMsg(insertResultMap.get("V_RTN_MSG").toString());
				return resVo;
			} else {
				resVo.setRtnCd(resCd);
				resVo.setRtnMsg(checkBAResultMap.get("msg").toString());
				return resVo;
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			resVo.setRtnCd(-5501);
			resVo.setRtnMsg("???????????? ?????? ????????? ?????????????????????.");
			return resVo;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ???????????? Coin ?????? ????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectUserPossessionInfo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/getCoinMgtRefInfoList", method = RequestMethod.POST)
	public Map<Integer, Map<String, Object>> getCoinMgtRefInfoList(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = yahobitWalletService.selectCoinMgtRefInfoList(paramMap);

			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			return coinMgtRefInfoMap ;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ???????????? Coin ?????? ????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectUserPossessionInfo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/selectUserPossessionInfo", method = RequestMethod.POST)
	public List<CoinBalanceVO> selectUserPossessionInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<CoinBalanceVO> coinBalanceList = yahobitWalletService.selectCoinBalanceByUserId(paramMap);
			return coinBalanceList;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ???????????? Coin??? ???????????? ????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectCoinDepositWalletInfoByUserIdAndCoinNo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @param request
	 * @param coinNo		COIN_NO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/selectCoinDepositWalletInfoByUserIdAndCoinNo", method = RequestMethod.POST)
	public Map<String, Object> selectCoinDepositWalletInfoByUserIdAndCoinNo(HttpServletRequest request, HttpSession session
				, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			Map<String, Object> coinDepositWalletInfoMap = yahobitWalletService.selectCoinDepositWalletInfoByUserIdAndCoinNo(paramMap);
			return coinDepositWalletInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : COIN ?????? ?????? ??????
	 * 2. ???????????? : Wallet Server?????? DW_GETNEWADDRESS_V1	Command??? Queue??? ?????? ???????????? ?????????.
	 * </pre>
	 * @Method Name : createNewCoinAddress
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/createNewCoinAddress", method = RequestMethod.POST)
	public Map<String, Object> createNewCoinAddress(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "coinSymbolicNm", defaultValue = "", required = true) String strCoinSymbolicName
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = yahobitWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}

			paramMap.clear();
			paramMap.put("exchangeId", strExchangeId);			// ???????????? ??????!
			paramMap.put("userId", strUserId );					// ???????????? ??????!
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("COINKIND", strCoinSymbolicName );
			Map<String, Object> coinDepositWalletInfoMap = yahobitWalletService.createNewCoinAddress(paramMap);
			return coinDepositWalletInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : ????????? ????????? ????????????????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : checkWithdrawQty
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param dReqQty
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/checkWithdrawQty", method = RequestMethod.POST)
	public Map<String, Object> checkWithdrawQty(HttpServletRequest request, HttpSession session
				, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
				, @RequestParam(value = "reqQty", defaultValue = "0", required = true) double dReqQty
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			Map<String, Object> coinWithDrawQty = yahobitWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);
			return coinWithDrawQty;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ????????? 1???????????????????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : checkOnceWthrwQty
	 * @date : 2019. 12. 06.
	 * @author : ?????????
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 06.		?????????				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/checkOnceWthrwQty", method = RequestMethod.POST)
	public Map<String, Object> checkOnceWthrwQty(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			) throws Exception {
		
		try {			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("COIN_NO", iCoinNo );
			
			Map<String, Object> OnceWthrwQty = yahobitWalletService.checkOnceWthrwQty(paramMap);
			return OnceWthrwQty;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : ?????? ????????? ?????? ???????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectCoinBalanceHistList
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param iDwRequestTypeCd
	 * @param strStartDate
	 * @param strEndDate
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/selectCoinBalanceHistList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectCoinBalanceHistList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "dwReqTypeCd", defaultValue = "10", required = true) int iDwRequestTypeCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			, @RequestParam(value = "srchOpt", defaultValue = "0", required = false) int iSearchOption 
			) throws Exception {
		
		/*
		 *  
		    var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD', '');
            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD', '1');

		    switch('' + reqStatProcCd) {
                case '1': procStatTxt = '?????? ??????'; break;
                case '2': procStatTxt = '?????? ?????????'; break;
                case '3': procStatTxt = '?????? ?????? ?????????'; break;
                case '4': procStatTxt = ((reqTypeCd == '1') ? '??????':'??????') + '?????? ?????????'; break;
                case '5': procStatTxt = '?????? ??????'; break;
            }
            if('' + reqStatProcCd == '4' && '' + procStatCd == '1') {
                procStatTxt = '?????? ??????';
            }
		 */
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();
			
			if((iSearchOption & 0b111) == 0 || iSearchOption == 0b111) {
				// ?????? ??? ??????
			} else {
				if((iSearchOption & 0b100) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("REQUEST", "REQUEST");
				}
				if((iSearchOption & 0b010) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("WAIT", "WAIT");
				}
				if((iSearchOption & 0b001) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("FINISH", "FINISH");
				}
			}

			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("DW_REQ_TYPE_CD", iDwRequestTypeCd );
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// ?????? ?????????
			paramMap.put("PAGE_CONT_NUM", 10);					// ??????????????? ????????? ????????? ??????
			List<Map<String, Object>> coinWithDrawMgrList = CommonUtils.numericConvertListMapValue(yahobitWalletService.selectDepositWithdrawList(paramMap) );
			return coinWithDrawMgrList;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : ?????? ????????? ?????? ????????? ????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : selectCoinBalanceHistListCount
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param iDwRequestTypeCd
	 * @param strStartDate
	 * @param strEndDate
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/selectCoinBalanceHistListCount", method = RequestMethod.POST)
	public Map<String, Object> selectCoinBalanceHistListCount(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "dwReqTypeCd", defaultValue = "10", required = true) int iDwRequestTypeCd
			, @RequestParam(value = "startDate", defaultValue = "0", required = false) String strStartDate
			, @RequestParam(value = "endDate", defaultValue = "0", required = false) String strEndDate
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			, @RequestParam(value = "srchOpt", defaultValue = "0", required = false) int iSearchOption 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			
			Map<String, Object> paramMap = new HashMap<>();

			if((iSearchOption & 0b111) == 0 || iSearchOption == 0b111) {
				// ?????? ??? ??????
			} else {
				if((iSearchOption & 0b100) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("REQUEST", "REQUEST");
				}
				if((iSearchOption & 0b010) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("WAIT", "WAIT");
				}
				if((iSearchOption & 0b001) != 0) {
					// '??????'??? ????????? ??????
					paramMap.put("FINISH", "FINISH");
				}
			}

			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("DW_REQ_TYPE_CD", iDwRequestTypeCd );
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// ?????? ?????????
			paramMap.put("PAGE_CONT_NUM", 10);					// ??????????????? ????????? ????????? ??????
			Map<String, Object> coinWithDrawMgrListCount = yahobitWalletService.selectDepositWithdrawListCount(paramMap);
			return coinWithDrawMgrListCount;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ?????? ?????????????????? SMS ??????????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : requestSmsAuthNumber
	 * @date : 2019. 4. 30.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @param dReqQty
	 * @param strTargetAddr
	 * @param strTargetAddrEtc1
	 * @param strTargetAddrEtc2
	 * @param strReqMemo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/requestSmsAuthNumber", method = RequestMethod.POST)
	public ResultVO requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "coinSymbol", defaultValue = "BTC", required = true) String strCoinSymbol
			, @RequestParam(value = "reqQty", defaultValue = "0", required = true) double dReqQty
			, @RequestParam(value = "targetAddr", defaultValue = "", required = true) String strTargetAddr
			, @RequestParam(value = "targetAddrEtc1", defaultValue = "", required = false) String strTargetAddrEtc1
			, @RequestParam(value = "targetAddrEtc2", defaultValue = "", required = false) String strTargetAddrEtc2
			, @RequestParam(value = "reqMemo", defaultValue = "", required = false) String strReqMemo
			) throws Exception {
		
		ResultVO resVo = new ResultVO();
		
		/////////////////////////////////////////////////////////////////////////////////
		//                           TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def	= new DefaultTransactionDefinition();
		def.setName("was-request-withdraw-auth-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status			= transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

//		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			String strClientIp = CommonUtils.getClientIpAddr(request);
			
			// ?????? ??????????????? 72?????? ????????? ????????????.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			int check_72hour = yahobitWalletService.checkWithdrawOk(paramMap);
			if( check_72hour < 0 && iCoinNo != 10 ) {
				// Roll-back Transaction
				transactionManager.rollback(status);
				resVo.setRtnCd(check_72hour);
				resVo.setRtnMsg("KRW????????? 72????????? ???????????? ???????????????. (????????? ??????)");
				return resVo;
			}
			
			// Coin Target Address??? Validation ??????
			paramMap.clear();
			paramMap.put("COINKIND", strCoinSymbol);
			paramMap.put("CoinAddress", strTargetAddr);
			boolean address_check = (iCoinNo != 10) ? yahobitWalletService.checkValidateionUserCoinAddress(paramMap) : true ;
			if(!address_check) {
				// Roll-back Transaction
				transactionManager.rollback(status);
				resVo.setRtnCd(-5502);
				resVo.setRtnMsg("?????? ?????? ????????? ???????????? ????????????.");
				return resVo;
			}
			
			// ????????????  ??????
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);

			// ?????? ??????
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			Map<String, Object> coinWithDrawQtyCheckMap = yahobitWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

			int rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithDrawQtyCheckMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				resVo.setRtnCd(rtnCd);
				resVo.setRtnMsg(coinWithDrawQtyCheckMap.get("V_RTN_MSG").toString());
				return resVo;
			}
			
			// ?????????????????? ?????? ???????????? ??????
			paramMap.clear();
			paramMap.put("ARG_REQ_IP", strClientIp);
			paramMap.put("ARG_REQ_CHNL_CD", "WEB" );
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_FROM_ADDR", "" );
			paramMap.put("ARG_TARGET_ADDR", strTargetAddr );
			paramMap.put("ARG_TARGET_ADDR_ETC1", strTargetAddrEtc1 );
			paramMap.put("ARG_TARGET_ADDR_ETC2", strTargetAddrEtc2 );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			paramMap.put("ARG_REQ_MEMO", strReqMemo );
			paramMap.put("ARG_REQ_STAT_PROC_CD", 3 );
			paramMap.put("ARG_APPROVAL_STAT_CD", 0 );
			paramMap.put("ARG_DW_PROC_STAT_CD", 0 );
			paramMap.put("ARG_TRANSACTION_IDX", 0 );
			Map<String, Object> coinWithdrawRequestMap = yahobitWalletService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
			rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithdrawRequestMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				resVo.setRtnCd(rtnCd);
				resVo.setRtnMsg(coinWithdrawRequestMap.get("V_RTN_MSG").toString());
				return resVo;
			}

			// ???????????? ???????????? ???????????? ??????
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 3);				// ???????????? ????????????	1: ????????????, 2: ????????? ??????, 3: ????????????, 4: ???????????? ??????
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// ???????????? ???????????? 1: OTP, 2: SMS, 3: Email, 0: ?????????
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// ????????? ??????
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3?????? ??????
			paramMap.put("ARG_TRANSACTION_KEY_VAL", Long.valueOf(coinWithdrawRequestMap.get("V_REQ_SEQ_NO").toString()).longValue() );
			Map<String, Object> insertAuthCodeMap = yahobitWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
			rtnCd = Double.valueOf(CommonUtils.strNlv(insertAuthCodeMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				resVo.setRtnCd(rtnCd);
				resVo.setRtnMsg(insertAuthCodeMap.get("V_RTN_MSG").toString());
				return resVo;
			}

			String strAuthCode = insertAuthCodeMap.get("V_AUTH_CODE").toString();
			SendSmsVO sendSmsVo = new SendSmsVO();
			sendSmsVo.setExchangeId(strExchangeId);
			sendSmsVo.setUserId(strUserId);
			sendSmsVo.setTo(memberInfoVo.getMOBILE());
			String smsMsg = String.format("[ALIBIT] ?????? ???????????? [%s] (????????????????????????) ???????????? ????????????", strAuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");
			
			ResultVO resultVo = commService.sendSms(sendSmsVo);
			
			if(resultVo.getRtnCd() == 0) {
				// Commit Transaction
				transactionManager.commit(status);
			} else {
				// Roll-back Transaction
				transactionManager.rollback(status);
			}

			resVo.setRtnCd(resultVo.getRtnCd());
			resVo.setRtnMsg(resultVo.getRtnMsg());
			return resVo;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Roll-back Transaction
			transactionManager.rollback(status);
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5503);
			resVo.setRtnMsg("SMS ????????? ?????????????????????.");
			return resVo;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1. ?????? : PR_WAS_SET_DW_REQ_STAT_PROC_CD ??????????????? ???????????? ????????? ???????????? (?????????????????????) ?????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : cancelWithdrawItem
	 * @date : 2019. 5. 1.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 1.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param lReqSeqNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/cancelWithdrawItem", method = RequestMethod.POST)
	public ResultVO cancelWithdrawItem(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "reqSeqNo", defaultValue = "10", required = true) long lReqSeqNo
			) throws Exception {
		
		ResultVO resVo = new ResultVO();
		
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ARG_REQ_SEQ_NO", lReqSeqNo);
			paramMap.put("ARG_REQ_STAT_PROC_CD", 5 );				// 5: ?????? ??????
			Map<String, Object> cancelWithDrawItemMap = yahobitWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
			
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			// ???????????? ???????????? ????????? ????????? TB_MEMBER_AUTH_MGR ??????????????? ??????????????? ????????????.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			paramMap.put("TRANSACTION_KEY_VAL", lReqSeqNo );	// REQ_SEQ_NO ??? ???????????? ??????
			int res = yahobitWalletService.deleteMemberAuthMgrByTxKeyBal(paramMap);

			resVo.setRtnCd(0);
			resVo.setRtnMsg("??????????????? ?????????????????????.");
			return resVo;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			resVo.setRtnCd(-5504);
			resVo.setRtnMsg("???????????? ????????? ?????????????????????.");
			return resVo;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ??????????????? ???????????? ??????????????? ??????.
	 * 2. ???????????? : ???????????? ????????? ????????? ??????????????? ??????.
	 * </pre>
	 * @Method Name : doRequestWithDraw
	 * @date : 2019. 5. 1.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 1.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strAuthCode
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/doRequestWithDraw", method = RequestMethod.POST)
	public ResultVO doRequestWithDraw(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "authCode", defaultValue = "", required = true) String strAuthCode
			, @RequestParam(value = "coinNo", defaultValue = "10", required = false) int iCoinNo
			, @RequestParam(value = "coinSymbol", defaultValue = "10", required = false) String strCoinSymbol
			, @RequestParam(value = "reqQty", defaultValue = "0", required = false) double dReqQty
			, @RequestParam(value = "targetAddr", defaultValue = "", required = false) String strTargetAddr
			, @RequestParam(value = "targetAddrEtc1", defaultValue = "", required = false) String strTargetAddrEtc1
			, @RequestParam(value = "targetAddrEtc2", defaultValue = "", required = false) String strTargetAddrEtc2
			, @RequestParam(value = "reqMemo", defaultValue = "", required = false) String strReqMemo
			) throws Exception {
		
		ResultVO resVo = new ResultVO();

		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			String strClientIp = CommonUtils.getClientIpAddr(request);
			
			// ?????? ?????? ??????
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);
			
			// TRANSACTION_KEY_VAL ????????? ????????????.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("AUTH_PURPOSE_CD", 3);											// ???????????? ????????????	1: ????????????, 2: ????????? ??????, 3: ????????????, 4: ???????????? ??????
			paramMap.put("AUTH_MEANS_CD", memberInfoVo.getAUTH_MEANS_CD() ); 			// ???????????? ???????????? 1: OTP, 2: SMS, 3: Email, 0: ?????????
			paramMap.put("AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );				// ????????? ??????
			Map<String, Object> getTxKeyMap = yahobitWalletService.selectMemberAuthMgrTxKeyBal(paramMap);
			
			if(memberInfoVo.getAUTH_MEANS_CD() == 2) {				// ???????????? ????????????  2: SMS
				// ???????????? ???????????? ????????????.
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_AUTH_PURPOSE_CD", 3);										// ???????????? ????????????	1: ????????????, 2: ????????? ??????, 3: ????????????, 4: ???????????? ??????
				paramMap.put("ARG_AUTH_MEANS_CD", memberInfoVo.getAUTH_MEANS_CD() ); 		// ???????????? ???????????? 1: OTP, 2: SMS, 3: Email, 0: ?????????
				paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );			// ????????? ??????
				paramMap.put("ARG_AUTH_CODE", strAuthCode );
				Map<String, Object> checkAuthCodeMap = yahobitWalletService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
	
				if(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() == 0) {
					// ??????????????? ???????????? ????????? ???????????? ????????? ???????????? ????????????.
					paramMap.clear();
					paramMap.put("ARG_REQ_SEQ_NO", getTxKeyMap.get("TRANSACTION_KEY_VAL"));
					paramMap.put("ARG_REQ_STAT_PROC_CD", 4 );		// ????????? ???????????? :4
	
					Map<String, Object> setDwReqStatusProcCdMap = yahobitWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
				}
				
				resVo.setRtnCd(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() );
				resVo.setRtnMsg(checkAuthCodeMap.get("V_RTN_MSG").toString());
				return resVo;

			} else if(memberInfoVo.getAUTH_MEANS_CD() == 1) {		// ???????????? ???????????? 1: OTP
				// ???????????? OTP ????????? ????????? ??????.
				boolean otp_check = GoogleOTP.checkCode(strAuthCode, memberInfoVo.getOTP_KEY_VAL());
				if(!otp_check) {
					resVo.setRtnCd(-5505);
					resVo.setRtnMsg("OTP ??????????????? ?????? ????????????.");
					return resVo;
				}
				
				// ?????? ??????????????? 72?????? ????????? ????????????.
				paramMap.clear();
				paramMap.put("EXCHANGE_ID", strExchangeId);
				paramMap.put("USER_ID", strUserId );
				int check_72hour = yahobitWalletService.checkWithdrawOk(paramMap);
				if(check_72hour < 0 && iCoinNo != 10) {
					resVo.setRtnCd(check_72hour);
					resVo.setRtnMsg("KRW????????? 72????????? ???????????? ???????????????. (????????? ??????)");
					return resVo;
				}

				if(iCoinNo != 10) {
					// Coin Target Address??? Validation ?????? (??????????????? ????????? ???????????????)
					paramMap.clear();
					paramMap.put("COINKIND", strCoinSymbol);
					paramMap.put("CoinAddress", strTargetAddr);
					boolean address_check = yahobitWalletService.checkValidateionUserCoinAddress(paramMap);
					if(!address_check) {
						resVo.setRtnCd(-5502);
						resVo.setRtnMsg("?????? ?????? ????????? ???????????? ????????????.");
						return resVo;
					}
				}

				/////////////////////////////////////////////////////////////////////////////////
				//                           TRANSACTION MANAGEMENT
				DefaultTransactionDefinition def	= new DefaultTransactionDefinition();
				def.setName("was-request-withdraw-auth-transaction");
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				TransactionStatus status			= transactionManager.getTransaction(def);
				/////////////////////////////////////////////////////////////////////////////////
				// ?????? ??????
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_COIN_NO", iCoinNo );
				paramMap.put("ARG_REQ_QTY", dReqQty );
				Map<String, Object> coinWithDrawQtyCheckMap = yahobitWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

				int rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithDrawQtyCheckMap.get("V_RTN_CD"), "-1")).intValue();
				if(rtnCd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);

					resVo.setRtnCd(rtnCd);
					resVo.setRtnMsg(coinWithDrawQtyCheckMap.get("V_RTN_MSG").toString());
					return resVo;
				}
				
				// ?????????????????? ?????? ???????????? ??????
				paramMap.clear();
				paramMap.put("ARG_REQ_IP", strClientIp);
				paramMap.put("ARG_REQ_CHNL_CD", "WEB" );
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_FROM_ADDR", "" );
				paramMap.put("ARG_TARGET_ADDR", strTargetAddr );
				paramMap.put("ARG_TARGET_ADDR_ETC1", strTargetAddrEtc1 );
				paramMap.put("ARG_TARGET_ADDR_ETC2", strTargetAddrEtc2 );
				paramMap.put("ARG_COIN_NO", iCoinNo );
				paramMap.put("ARG_REQ_QTY", dReqQty );
				paramMap.put("ARG_REQ_MEMO", strReqMemo );
				paramMap.put("ARG_REQ_STAT_PROC_CD", 3 );
				paramMap.put("ARG_APPROVAL_STAT_CD", 0 );
				paramMap.put("ARG_DW_PROC_STAT_CD", 0 );
				paramMap.put("ARG_TRANSACTION_IDX", 0 );
				Map<String, Object> coinWithdrawRequestMap = yahobitWalletService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
				rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithdrawRequestMap.get("V_RTN_CD"), "-1")).intValue();
				if(rtnCd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);

					resVo.setRtnCd(rtnCd);
					resVo.setRtnMsg(coinWithdrawRequestMap.get("V_RTN_MSG").toString());
					return resVo;
				}

				// ????????? ???????????? ????????? ???????????? ????????????.
				paramMap.clear();
				paramMap.put("ARG_REQ_SEQ_NO", coinWithdrawRequestMap.get("V_REQ_SEQ_NO"));
				paramMap.put("ARG_REQ_STAT_PROC_CD", 4 );		// ????????? ???????????? :4

				Map<String, Object> setDwReqStatusProcCdMap = yahobitWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
				
				// Commit Transaction
				transactionManager.commit(status);
				
				resVo.setRtnCd(0);
				resVo.setRtnMsg("???????????????????????????.");
				return resVo;
			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5506);
			resVo.setRtnMsg("??????????????? ?????????????????????.");
			return resVo;
		}
		
		return resVo;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. ?????? : ?????? ??????????????? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : requestMoneyDeposit
	 * @date : 2019. 5. 6.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 6.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strFinCode
	 * @param strBankCode
	 * @param strAccountNumber
	 * @param strHolderName
	 * @param dRequestAmount
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/wallet/wlt001/requestMoneyDeposit", method = RequestMethod.POST)
	public ResultVO requestMoneyDeposit(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "finCd", defaultValue = "", required = true) String strFinCode
			, @RequestParam(value = "bankCd", defaultValue = "", required = true) String strBankCode
			, @RequestParam(value = "accntNo", defaultValue = "", required = true) String strAccountNumber
			, @RequestParam(value = "holderNm", defaultValue = "", required = true) String strHolderName
			, @RequestParam(value = "reqAmt", defaultValue = "", required = true) double dRequestAmount
			) throws Exception {
		
		ResultVO resultVo = new ResultVO();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			String strReqIP = CommonUtils.getClientIpAddr(request);
			String strReqChanndeCd = "WEB";
			
			CoinMgtRefInfoVO coinMgtRefInfo_Default = yahobitWalletService.selectCoinMgtRefInfoByCoinNo(CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request)));
			
			if(coinMgtRefInfo_Default.getMIN_DEPOSIT_QTY() > dRequestAmount) {
				resultVo.setRtnCd(-5092);
				resultVo.setRtnMsg("?????? ?????? ???????????? ?????? ????????? ????????????.");
			} else if(user.getAuthLevel() >= 3) {
				// PR_WLL_INSERT_DEPOSIT_REQUEST ???????????? ???????????? TB_DEPOSIT_WITHDRAW_MGR ????????? insert
				DepositWithdrawMgrVO dwMgrVo = new DepositWithdrawMgrVO();
				dwMgrVo.setREQ_IP(strReqIP);
				dwMgrVo.setREQ_CHNL_CD(strReqChanndeCd);
				dwMgrVo.setEXCHANGE_ID(strExchangeId);
				dwMgrVo.setUSER_ID(strUserId);
				dwMgrVo.setDW_REQ_TYPE_CD(3);				// 3 : ?????? ??????
				dwMgrVo.setFROM_ADDR("");
				dwMgrVo.setPIN_CD(strFinCode);
				dwMgrVo.setTARGET_ADDR(strAccountNumber);
				dwMgrVo.setTARGET_ADDR_ETC1(strBankCode);
				dwMgrVo.setTARGET_ADDR_ETC2(strHolderName);
				dwMgrVo.setCOIN_NO(CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request)));
				dwMgrVo.setREQ_QTY(dRequestAmount);
				dwMgrVo.setREQ_MEMO("");
				dwMgrVo.setREQ_STAT_PROC_CD(1);				// ???????????????????????? Default=1(??????-??????) or 2(????????????-??????))
				dwMgrVo.setAPPROVAL_STAT_CD(0);				// ?????????????????? (Default=0 ?????????)
				dwMgrVo.setDW_PROC_STAT_CD(0);				// ??????????????????????????? (Default=0 ?????????)
				dwMgrVo.setTRANSACTION_IDX(0);
				int result = yahobitWalletService.insertDepositWithdrawManager(dwMgrVo);
				
//				// PR_WLL_INSERT_DEPOSIT_REQUEST ???????????? ???????????? TB_DEPOSIT_WITHDRAW_MGR ????????? insert
//				paramMap.clear();
//				paramMap.put("ARG_REQ_IP", strReqIP);
//				paramMap.put("ARG_REQ_CHNL_CD", strReqChanndeCd);
//				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
//				paramMap.put("ARG_USER_ID", strUserId);
//				paramMap.put("ARG_FROM_ADDR", "" );
//				paramMap.put("ARG_FIN_CD", strFinCode );
//				paramMap.put("ARG_TARGET_ADDR", strAccountNumber );
//				paramMap.put("ARG_TARGET_ADDR_ETC1", strBankCode );
//				paramMap.put("ARG_TARGET_ADDR_ETC2", strHolderName );
//				paramMap.put("ARG_COIN_NO", CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request)) );
//				paramMap.put("ARG_REQ_QTY", dRequestAmount );
//				paramMap.put("ARG_REQ_MEMO", "" );
//				paramMap.put("ARG_REQ_STAT_PROC_CD", 1 );		// ???????????????????????? Default=1(??????-??????) or 2(????????????-??????))
//				paramMap.put("ARG_APPROVAL_STAT_CD", 0 );		// ?????????????????? (Default=0 ?????????)
//				paramMap.put("ARG_DW_PROC_STAT_CD", 0 );		// ??????????????????????????? (Default=0 ?????????)
//				paramMap.put("ARG_TRANSACTION_IDX", 0 );
//				paramMap.put("V_RTN_CD", 0 );
//				paramMap.put("V_RTN_MSG", "" );
//				Map<String, Object> resultMap = yahobitWalletService.insertRequestMoneyDeposit(paramMap);
				
				resultVo.setRtnCd((result > 0) ? 0 : -5507);
				resultVo.setRtnMsg((result > 0) ? "???????????? ??????" : "???????????? ??????");
			} else {
				resultVo.setRtnCd(-5091);
				resultVo.setRtnMsg("?????? ????????? ?????? 3 ????????? ?????? ?????? ???????????????");
			}
			
			return resultVo;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resultVo.setRtnCd(-5507);
			resultVo.setRtnMsg("??????????????? ?????????????????????." );
			return resultVo;
		}
	}

}
