/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.coinis.webserver.coinis.wallet.wlt001.service.DepositWithdrawalsService;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinPossessionVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.DepositWithdrawInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.ReqDepositWithdrawInfoVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt001.controller 
 *    |_ DepositWithdrawalsController.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오전 10:45:59
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class DepositWithdrawalsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private DepositWithdrawalsService depositWithdrawalsService;
	
	@Autowired
	private CommService commService;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@RequestMapping(value = "coinis/depositWithdrawals")
	public ModelAndView moveDepositWithdrawals(HttpServletRequest request, HttpSession session) {
		try {
			ModelAndView model = new ModelAndView();
			model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt001/depositWithdrawals");
			
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
			param.setExchange_id(exchange_id);
			param.setUser_id(userInfo.getUserId());
	
			List<CoinPossessionVO> coinPossessionList = depositWithdrawalsService.retrieveCoinPossession(param);
			List<Map<String, Object>> exchangeCoinList = depositWithdrawalsService.selectExchangeCoinList(exchange_id);
			
			//코인관리 기준정보
			// 기본 기초통화 OR USD 관련정보
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = depositWithdrawalsService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			//defaultCoinInfo.add(coinMgtRefInfo_USD);
			model.addObject("defaultCoinInfo", new Gson().toJson(defaultCoinInfo) );
			
			// 코인관리 기준정보 내려준다.
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = userInfo.getUserId();
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = depositWithdrawalsService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", new Gson().toJson(coinMgtRefInfoMap));

			model.addObject("coinPossessionList", coinPossessionList);
			model.addObject("exchangeCoinList", new Gson().toJson(exchangeCoinList));
			
			return model;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 Coin 보유 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserPossessionInfo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "coinis/wallet/wlt001/selectUserPossessionInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> selectUserPossessionInfo(HttpServletRequest request, HttpSession session) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<Map<String, Object>> userAvailableCoinBalanceList = depositWithdrawalsService.selectUserAvailablePossessionInfo(paramMap);
			return userAvailableCoinBalanceList;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 지갑 정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserWalletInfo
	 * @date : 2019. 6. 16
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "coinis/wallet/wlt001/selectUserWalletInfo", method = RequestMethod.POST)
	public DepositWithdrawInfoVO selectUserWalletInfo(HttpServletRequest request, HttpSession session
			, @ModelAttribute ReqDepositWithdrawInfoVO param) throws Exception {
		
		try {
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			param.setExchange_id(exchange_id);
			param.setUser_id(userInfo.getUserId());
	
			DepositWithdrawInfoVO depositWithdrawInfo = depositWithdrawalsService.retrieveDepositWithdrawInfo(param);
			
			return depositWithdrawInfo;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 사용자의 코인관련 출금정보를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserBalanceInfo
	 * @date : 2019. 6. 16
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "coinis/wallet/wlt001/selectUserBalanceInfo", method = RequestMethod.POST)
	public Map<String, Object> selectUserBalanceInfo(HttpServletRequest request, HttpSession session
				, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
				, @RequestParam(value = "reqQty", defaultValue = "0", required = true) double dReqQty
			) throws Exception {

		try {
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", exchange_id);
			paramMap.put("USER_ID", userInfo.getUserId());
			paramMap.put("COIN_NO", iCoinNo);
			paramMap.put("REQ_QTY", dReqQty);
			Map<String, Object> userWithdrawalInfoMap = depositWithdrawalsService.selectUserWithdrawalInfo(paramMap);
			
			return userWithdrawalInfoMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Wallet Server에 신규 지갑주소를 요청한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : createNewCoinAddress
	 * @date : 2019. 6. 18.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 18.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param coinNo
	 * @param coinKind
	 * @return
	 */
	@RequestMapping(value = "coinis/wallet/wlt001/createNewCoinAddress")
	@ResponseBody
	public Map<String, Object> createNewCoinAddress(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "COINNO", defaultValue = "10", required = true) int coinNo
			, @RequestParam(value = "COINKIND", defaultValue = "0", required = true) String coinKind/*@ModelAttribute Map<String, Object> paramMap*/) {
		
		String exchange_id = CommonUtils.getExchangeId(request);
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("COINNO", coinNo);
		paramMap.put("COINKIND", coinKind);
		paramMap.put("exchangeId", exchange_id);		// 대소문자를 주의하래요
		paramMap.put("userId", userInfo.getUserId() );  // 대소문자를 주의하래요
		
		Map<String, Object> coinDepositWalletInfoMap = new HashMap<>();
		try {
			coinDepositWalletInfoMap = depositWithdrawalsService.createNewCoinAddress(paramMap);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coinDepositWalletInfoMap;
	}
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 코인 출금신청에서 SMS 인증번호를 요청한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestSmsAuthNumber
	 * @date : 2019. 4. 30.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		kangn				최초 작성 
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
	@RequestMapping(value = "coinis/wallet/wlt001/requestSmsAuthNumber", method = RequestMethod.POST)
	public ResultVO requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "coinSymbol", defaultValue = "BTC", required = true) String strCoinSymbol
			, @RequestParam(value = "reqQty", defaultValue = "0", required = true) double dReqQty
			, @RequestParam(value = "targetAddr", defaultValue = "", required = true) String strTargetAddr
			, @RequestParam(value = "targetAddrEtc1", defaultValue = "", required = false) String strTargetAddrEtc1
			, @RequestParam(value = "targetAddrEtc2", defaultValue = "", required = false) String strTargetAddrEtc2
			) throws Exception {
		
		ResultVO resVo = new ResultVO();
		
		/////////////////////////////////////////////////////////////////////////////////
		//                           TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def	= new DefaultTransactionDefinition();
		def.setName("was-request-withdraw-auth-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status			= transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			String strClientIp = CommonUtils.getClientIpAddr(request);
			
			// Coin Target Address의 Validation 체크
			paramMap.clear();
			paramMap.put("COINKIND", strCoinSymbol);
			paramMap.put("CoinAddress", strTargetAddr);
			boolean address_check = (iCoinNo != 10) ? depositWithdrawalsService.checkValidateionUserCoinAddress(paramMap) : true ;
			if(!address_check) {
				// Roll-back Transaction
				transactionManager.rollback(status);
				resVo.setRtnCd(-5502);
				resVo.setRtnMsg("Target Withdrawal Address is not valid.");
				return resVo;
			}
			
			// 회원정보  체크
			paramMap.clear();
			paramMap.put("exchange_id", strExchangeId);
			paramMap.put("user_id", strUserId);
			String strMemberMobileNumber = depositWithdrawalsService.getMemberMobile(paramMap);

			// 잔고 체크
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			Map<String, Object> coinWithDrawQtyCheckMap = depositWithdrawalsService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

			int rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithDrawQtyCheckMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				resVo.setRtnCd(rtnCd);
				resVo.setRtnMsg(coinWithDrawQtyCheckMap.get("V_RTN_MSG").toString());
				return resVo;
			}
			
			// 코인출금요청 저장 프로시저 호출
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
			paramMap.put("ARG_REQ_MEMO", "" );
			paramMap.put("ARG_REQ_STAT_PROC_CD", 3 );
			paramMap.put("ARG_APPROVAL_STAT_CD", 0 );
			paramMap.put("ARG_DW_PROC_STAT_CD", 0 );
			paramMap.put("ARG_TRANSACTION_IDX", 0 );
			Map<String, Object> coinWithdrawRequestMap = depositWithdrawalsService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
			rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithdrawRequestMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				// Roll-back Transaction
				transactionManager.rollback(status);

				resVo.setRtnCd(rtnCd);
				resVo.setRtnMsg(coinWithdrawRequestMap.get("V_RTN_MSG").toString());
				return resVo;
			}

			// 인증코드 저장처리 프로시저 호출
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 3);				// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", strMemberMobileNumber );	// 휴대폰 번호
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", Long.valueOf(coinWithdrawRequestMap.get("V_REQ_SEQ_NO").toString()).longValue() );
			Map<String, Object> insertAuthCodeMap = depositWithdrawalsService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
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
			sendSmsVo.setTo(strMemberMobileNumber);
			String smsMsg = String.format("[%s] Dear User, your verify code is [%s] ", strExchangeId, strAuthCode);
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
			LOG.error(CommonUtils.getPrintStackTrace(e));
			// Roll-back Transaction
			transactionManager.rollback(status);
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5503);
			resVo.setRtnMsg("Fail to Send SMS Auth code");
			return resVo;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 인증코드를 이용하여 출금요청을 한다.
	 * 2. 처리내용 : 인증코드 유효성 판단후 출금셋팅을 한다.
	 * </pre>
	 * @Method Name : doRequestWithDraw
	 * @date : 2019. 6. 18.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 18.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strAuthCode
	 * @param iCoinNo
	 * @param strCoinSymbol
	 * @param dReqQty
	 * @param strTargetAddr
	 * @param strTargetAddrEtc1
	 * @param strTargetAddrEtc2
	 * @param strReqMemo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "coinis/wallet/wlt001/doRequestWithDraw", method = RequestMethod.POST)
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
			
			// 회원 정보 체크
			paramMap.clear();
			paramMap.put("exchange_id", strExchangeId);
			paramMap.put("user_id", strUserId);
			String strMobileNumber	= depositWithdrawalsService.getMemberMobile(paramMap);
			int iAuthMeansCd		= Double.valueOf(depositWithdrawalsService.getMemberMeansCD(paramMap)).intValue();

			// TRANSACTION_KEY_VAL 키값을 얻어온다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("AUTH_PURPOSE_CD", 3);												// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("AUTH_MEANS_CD", iAuthMeansCd ); 									// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("AUTH_MEANS_KEY_VAL", strMobileNumber );							// 휴대폰 번호
			Map<String, Object> getTxKeyMap = depositWithdrawalsService.selectMemberAuthMgrTxKeyBal(paramMap);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			// 인증수단 식별코드 2: SMS
			if(iAuthMeansCd == 2) {
				// 인증코드 유효성을 검사한다.
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_AUTH_PURPOSE_CD", 3);										// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
				paramMap.put("ARG_AUTH_MEANS_CD", iAuthMeansCd ); 							// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
				paramMap.put("ARG_AUTH_MEANS_KEY_VAL", strMobileNumber );					// 휴대폰 번호
				paramMap.put("ARG_AUTH_CODE", strAuthCode );
				Map<String, Object> checkAuthCodeMap = depositWithdrawalsService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
	
				if(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() == 0) {
					// 인증코드가 유효하면 입출금 승인대기 상태로 상태값을 변경한다.
					paramMap.clear();
					paramMap.put("ARG_REQ_SEQ_NO", getTxKeyMap.get("TRANSACTION_KEY_VAL"));
					paramMap.put("ARG_REQ_STAT_PROC_CD", 4 );		// 입출금 승인대기 :4
	
					Map<String, Object> setDwReqStatusProcCdMap = depositWithdrawalsService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
				}
				
				resVo.setRtnCd(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() );
				resVo.setRtnMsg(checkAuthCodeMap.get("V_RTN_MSG").toString());
				return resVo;

			/////////////////////////////////////////////////////////////////////////////////////////////////
			// 인증수단 식별코드 1: OTP
			} else if(iAuthMeansCd == 1) {
				// 제일먼저 OTP 유효성 검증을 한다.
				paramMap.clear();
				paramMap.put("exchange_id", strExchangeId);
				paramMap.put("user_id", strUserId);
				String strOtpCode = depositWithdrawalsService.getMemberOTPCD(paramMap);
				boolean otp_check = GoogleOTP.checkCode(strAuthCode, strOtpCode);
				if(!otp_check) {
					resVo.setRtnCd(-5505);
					resVo.setRtnMsg("Not correct OTP verify code");
					return resVo;
				}
				
				if(iCoinNo != 10) {
					// Coin Target Address의 Validation 체크 (원화출금을 제외한 코인일때만)
					paramMap.clear();
					paramMap.put("COINKIND", strCoinSymbol);
					paramMap.put("CoinAddress", strTargetAddr);
					boolean address_check = depositWithdrawalsService.checkValidateionUserCoinAddress(paramMap);
					if(!address_check) {
						resVo.setRtnCd(-5502);
						resVo.setRtnMsg("Target Withdrawal Address is not valid.");
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
				// 잔고 체크
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_COIN_NO", iCoinNo );
				paramMap.put("ARG_REQ_QTY", dReqQty );
				Map<String, Object> coinWithDrawQtyCheckMap = depositWithdrawalsService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

				int rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithDrawQtyCheckMap.get("V_RTN_CD"), "-1")).intValue();
				if(rtnCd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);

					resVo.setRtnCd(rtnCd);
					resVo.setRtnMsg(coinWithDrawQtyCheckMap.get("V_RTN_MSG").toString());
					return resVo;
				}
				
				// 코인출금요청 저장 프로시저 호출
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
				Map<String, Object> coinWithdrawRequestMap = depositWithdrawalsService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
				rtnCd = Double.valueOf(CommonUtils.strNlv(coinWithdrawRequestMap.get("V_RTN_CD"), "-1")).intValue();
				if(rtnCd != 0) {
					// Roll-back Transaction
					transactionManager.rollback(status);

					resVo.setRtnCd(rtnCd);
					resVo.setRtnMsg(coinWithdrawRequestMap.get("V_RTN_MSG").toString());
					return resVo;
				}

				// 입출금 승인대기 상태로 상태값을 변경한다.
				paramMap.clear();
				paramMap.put("ARG_REQ_SEQ_NO", coinWithdrawRequestMap.get("V_REQ_SEQ_NO"));
				paramMap.put("ARG_REQ_STAT_PROC_CD", 4 );		// 입출금 승인대기 :4

				Map<String, Object> setDwReqStatusProcCdMap = depositWithdrawalsService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
				
				// Commit Transaction
				transactionManager.commit(status);
				
				resVo.setRtnCd(0);
				resVo.setRtnMsg("Withdrawal has been requested.");
				return resVo;
			}

		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5506);
			resVo.setRtnMsg("The withdrawal application failed.");
			return resVo;
		}
		
		return resVo;
	}
	
}
