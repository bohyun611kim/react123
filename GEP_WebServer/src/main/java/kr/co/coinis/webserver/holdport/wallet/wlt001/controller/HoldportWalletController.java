/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.wallet.wlt001.controller;

import java.io.InputStream;
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
import kr.co.coinis.webserver.common.utils.HttpSender;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.holdport.wallet.wlt001.service.HoldportWalletService;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.CodeInfoVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.CoinBalanceVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.DepositWithdrawMgrVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.MemberBankAccntInfoVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.wallet.wlt001.controller
 *    |_ HoldportWalletController.java
 *
 * </pre>
 * @date : 2019. 4. 26. 오후 1:33:19
 * @version :
 * @author : kangn
 */
@Controller
public class HoldportWalletController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportWalletController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldportWalletService holdportWalletService;

	@Autowired
	private CommService commService;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@RequestMapping(value = "site/api/wallet")
	@ResponseBody
	public Map<String, Object> walletInfo(HttpServletRequest request, HttpSession session) {
		ModelAndView model = this.moveNotice(request, session);
		return model.getModel();
	}


	@RequestMapping(value = "site/wallet")
	public ModelAndView moveNotice(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/wallet/wlt001/wallet");

		//logger.error("holdport/site/wallet++++++++++++++" + CommonUtils.getSitePackageKey(request));
		Gson gson = new Gson();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);
			


			CommonUtils.adaptLocale(request, locale, model);
			logger.debug("holdport/site/wallet> locale:" + locale);
			logger.debug("holdport/site/wallet> >>>>:" + model.getViewName());


			// 거래소에 상장된 코인정보
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", CommonUtils.getNewExchangeId(request));

			// 2019-08-19 민성철
			// 입출금 화면 왼쪽 잔고 내역 데이터가 코인 관리기준정보의 거래상태코드 값에 따르도록 변경
			//List<Map<String, Object>> exchangeCoinInfoMap = holdportWalletService.selectCoinInfoByExchangeId(paramMap);
			List<Map<String, Object>> exchangeCoinInfoMap = holdportWalletService.selectCoinInfoByTradeStatCd(paramMap);
			model.addObject("exchangeCoinInfo", gson.toJson(exchangeCoinInfoMap) );

			// 기본 기축통화 및 USD 관련정보
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getNewExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = holdportWalletService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			model.addObject("defaultCoinInfo", gson.toJson(defaultCoinInfo) );

			// 코인관리 기준정보 내려준다.
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = holdportWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", gson.toJson(coinMgtRefInfoMap));

			// 회원의 기본정보를 가져옴
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);
			model.addObject("authLevel", memberInfoVo.getAUTH_LEVEL());
			model.addObject("authMeanCd", memberInfoVo.getAUTH_MEANS_CD());

			// 은행코드 정보를 내려준다.
			paramMap.clear();
			paramMap.put("CODE_ID", "C0058");
			List<CodeInfoVO> bankCodeInfoList = holdportWalletService.selectCodeInfoByCodeId(paramMap);
			model.addObject("bankCodeInfoList", gson.toJson(bankCodeInfoList));

			// 거래소의 입금은행 정보를 내려준다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", CommonUtils.getNewExchangeId(request));
			Map<String, Object> ebaiMap = holdportWalletService.selectExchangeBankAccountInfo(paramMap);
			Map<String, Object> exchangeBankAccntInfoMap = new HashMap<>();
			exchangeBankAccntInfoMap.put("EXCHANGE_ID", ebaiMap.get("EXCHANGE_ID").toString());
			exchangeBankAccntInfoMap.put("BANK_ACCNT_NO", new String((byte[]) ebaiMap.get("BANK_ACCNT_NO")));
			exchangeBankAccntInfoMap.put("ACCNT_HOLDER_NM", new String((byte[])ebaiMap.get("ACCNT_HOLDER_NM")));
			exchangeBankAccntInfoMap.put("BANK_CD", ebaiMap.get("BANK_CD").toString());
			exchangeBankAccntInfoMap.put("DW_PG_MID", ebaiMap.get("DW_PG_MID").toString());
			int len = memberInfoVo.getMOBILE().length();
			exchangeBankAccntInfoMap.put("DEP_CODE", len < 4 ? "0" : memberInfoVo.getMOBILE().substring(len-4, len));
			exchangeBankAccntInfoMap.put("DEP_NM","실명추가");
			model.addObject("exchangeBankAccntInfo", gson.toJson(exchangeBankAccntInfoMap));

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		return model;
	}

	/**
	 * ============================================================
	 * 						OPEN 이전 TEST용!!!!
	 * ============================================================
	 */
	/*@RequestMapping(value = "site/wallet_test")
	public ModelAndView walletPageTest(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/wallet/wlt001/back_wallet");

		Gson gson = new Gson();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			// 거래소에 상장된 코인정보
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", CommonUtils.getNewExchangeId(request));
			List<Map<String, Object>> exchangeCoinInfoMap = holdportWalletService.selectCoinInfoByExchangeId(paramMap);
			model.addObject("exchangeCoinInfo", gson.toJson(exchangeCoinInfoMap) );

			// 기본 기축통화 및 USD 관련정보
			int defaultCoinType = CamelHelper.getDefaultConType(CommonUtils.getNewExchangeId(request));
			model.addObject("defaultCoinType", defaultCoinType );
			CoinMgtRefInfoVO coinMgtRefInfo_Default = holdportWalletService.selectCoinMgtRefInfoByCoinNo(defaultCoinType);
			//CoinMgtRefInfoVO coinMgtRefInfo_USD = holdportWalletService.selectCoinMgtRefInfoByCoinNo(11);
			List<CoinMgtRefInfoVO> defaultCoinInfo = new ArrayList<>();
			defaultCoinInfo.add(coinMgtRefInfo_Default);
			//defaultCoinInfo.add(coinMgtRefInfo_USD);
			model.addObject("defaultCoinInfo", gson.toJson(defaultCoinInfo) );

			// 코인관리 기준정보 내려준다.
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = holdportWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}
			model.addObject("coinMgtRefInfo", gson.toJson(coinMgtRefInfoMap));

			// 회원의 기본정보를 가져옴
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);
			model.addObject("authLevel", memberInfoVo.getAUTH_LEVEL());
			model.addObject("authMeanCd", memberInfoVo.getAUTH_MEANS_CD());

			// 은행코드 정보를 내려준다.
			paramMap.clear();
			paramMap.put("CODE_ID", "C0058");
			List<CodeInfoVO> bankCodeInfoList = holdportWalletService.selectCodeInfoByCodeId(paramMap);
			model.addObject("bankCodeInfoList", gson.toJson(bankCodeInfoList));

			// 거래소의 입금은행 정보를 내려준다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", CommonUtils.getNewExchangeId(request));
			Map<String, Object> ebaiMap = holdportWalletService.selectExchangeBankAccountInfo(paramMap);
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
	 * 1. 개요 : 사용자의 은행 계좌 정보를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectMemberBankAccntInfo
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
	@RequestMapping(value = {"site/wallet/wlt001/selectMemberBankAccntInfo", "site/api/wallet/wlt001/selectMemberBankAccntInfo"}, method = RequestMethod.POST)
	public Map<String, Object> selectMemberBankAccntInfo(HttpServletRequest request, HttpSession session) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			if(!CommonUtils.isLogin(user)) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("result", "-999");
				paramMap.put("msg", "logout");
				return paramMap;
			}
			
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("result", "1");
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> memberBankAccntInfoMap = holdportWalletService.selectMemberBankAccntInfo(paramMap);
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
	 * 1. 개요 : 회원의 은행계좌정보를 입력한다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : insertMemberBankAccntInfo
	 * @date : 2019. 5. 5.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		kangn				최초 작성
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
	@RequestMapping(value = "site/wallet/wlt001/insertMemberBankAccntInfo", method = RequestMethod.POST)
	public ResultVO insertMemberBankAccntInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "bankCd", defaultValue = "001", required = true) String strBankCode
			, @RequestParam(value = "bankAccntNo", defaultValue = "", required = true) String strBankAccountNo
			, @RequestParam(value = "bankAccntHldrNm", defaultValue = "", required = true) String strBankAccountHolderName
			, @RequestParam(value = "bankAccntHldrBthDay", defaultValue = "", required = true) String strBankAccountHolderBirthDay
			) throws Exception {

		ResultVO resVo = new ResultVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			if(!CommonUtils.isLogin(user)) {
				resVo.setRtnCd(-999);
				resVo.setRtnMsg("logout");
				return resVo;
			}
			
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("SERVER_NO", CamelHelper.getInstance().getServerNo() );				// 대소문자 주의!
			paramMap.put("bankCode", strBankCode );
			paramMap.put("acntNo", strBankAccountNo );
			paramMap.put("idNo", strBankAccountHolderBirthDay );
			paramMap.put("acntNm", strBankAccountHolderName );

			// 07.28 bk : 은행 체크 로직 skip
			//Map<String, Object> checkBAResultMap = holdportWalletService.checkBankAccountHolderName(paramMap);
			//int resCd = Double.valueOf(checkBAResultMap.get("resCode").toString()).intValue();
			int resCd = 0; //
			if(resCd == 0) {
				MemberBankAccntInfoVO bankInfoVo = new MemberBankAccntInfoVO();
				bankInfoVo.setEXCHANGE_ID(strExchangeId);
				bankInfoVo.setUSER_ID(strUserId);
				bankInfoVo.setBANK_CD(strBankCode);
				bankInfoVo.setBANK_ACCNT_NO(strBankAccountNo);
				bankInfoVo.setACCNT_HOLDER_NM(strBankAccountHolderName);
				Map<String, Object> insertResultMap = holdportWalletService.insertMemberBankAccntInfo(bankInfoVo);

				logger.debug("insertMemberBankAccntInfo Result >> " + insertResultMap);

				resVo.setRtnCd(Double.valueOf(insertResultMap.get("V_RTN_CD").toString()).intValue());
				resVo.setRtnMsg(insertResultMap.get("V_RTN_MSG").toString());
				return resVo;
			} else {
				resVo.setRtnCd(resCd);
				//resVo.setRtnMsg(checkBAResultMap.get("msg").toString());
				resVo.setRtnMsg("Failed to checkBankAccountHolderName");
				return resVo;
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			resVo.setRtnCd(-5501);
			resVo.setRtnMsg("은행계좌 정보 등록에 실패하였습니다.");
			return resVo;
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
	@RequestMapping(value = "site/wallet/wlt001/getCoinMgtRefInfoList", method = RequestMethod.POST)
	public Map<Integer, Map<String, Object>> getCoinMgtRefInfoList(HttpServletRequest request, HttpSession session) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = holdportWalletService.selectCoinMgtRefInfoList(paramMap);

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
	@RequestMapping(value = "site/wallet/wlt001/selectUserPossessionInfo", method = RequestMethod.POST)
	public List<CoinBalanceVO> selectUserPossessionInfo(HttpServletRequest request, HttpSession session) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<CoinBalanceVO> coinBalanceList = holdportWalletService.selectCoinBalanceByUserId(paramMap);
			return coinBalanceList;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 사용자의 Coin별 보유지갑 정보를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectCoinDepositWalletInfoByUserIdAndCoinNo
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
	 * @param request
	 * @param coinNo		COIN_NO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = {"site/wallet/wlt001/selectCoinDepositWalletInfoByUserIdAndCoinNo", "site/api/wallet/wlt001/selectCoinDepositWalletInfoByUserIdAndCoinNo"}, method = RequestMethod.POST)
	public Map<String, Object> selectCoinDepositWalletInfoByUserIdAndCoinNo(HttpServletRequest request, HttpSession session
				, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			) throws Exception {

		try {
			logger.error("selectCoinDepositWalletInfoByUserIdAndCoinNo+++" + iCoinNo );
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			if(!CommonUtils.isLogin(user)) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("result", "-999");
				paramMap.put("msg", "logout" );
				return paramMap;
			}
			
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			Map<String, Object> coinDepositWalletInfoMap = holdportWalletService.selectCoinDepositWalletInfoByUserIdAndCoinNo(paramMap);
			logger.error("selectCoinDepositWalletInfoByUserIdAndCoinNo---" + new Gson().toJson(coinDepositWalletInfoMap));
			return coinDepositWalletInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : COIN 주소 신규 발급
	 * 2. 처리내용 : Wallet Server쪽에 DW_GETNEWADDRESS_V1	Command를 Queue로 쏘고 결과값을 보낸다.
	 * </pre>
	 * @Method Name : createNewCoinAddress
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = {"site/wallet/wlt001/createNewCoinAddress", "site/api/wallet/wlt001/createNewCoinAddress"}, method = RequestMethod.POST)
	public Map<String, Object> createNewCoinAddress(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			, @RequestParam(value = "coinSymbolicNm", defaultValue = "", required = true) String strCoinSymbolicName
			) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			if(!CommonUtils.isLogin(user)) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("result", "-999");
				paramMap.put("msg", "logout" );
				return paramMap;
			}
			
			
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			List<Map<String, Object>> coinMgtRefInfoList = holdportWalletService.selectCoinMgtRefInfoList(paramMap);
			Map<Integer, Map<String, Object>> coinMgtRefInfoMap = new HashMap<>();
			for(Map<String, Object> map : coinMgtRefInfoList) {
				int coinNo = Double.valueOf(map.get("COIN_NO").toString()).intValue();
				coinMgtRefInfoMap.put(coinNo, map);
			}

			paramMap.clear();
			paramMap.put("exchangeId", strExchangeId);			// 대소문자 주의!
			paramMap.put("userId", strUserId );					// 대소문자 주의!
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("COINKIND", strCoinSymbolicName );
			Map<String, Object> coinDepositWalletInfoMap = holdportWalletService.createNewCoinAddress(paramMap);
			return coinDepositWalletInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 회원의 코인별 출금가능수량을 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkWithdrawQty
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				최초 작성
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
	@RequestMapping(value = "site/wallet/wlt001/checkWithdrawQty", method = RequestMethod.POST)
	public Map<String, Object> checkWithdrawQty(HttpServletRequest request, HttpSession session
				, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
				, @RequestParam(value = "reqQty", defaultValue = "0", required = true) double dReqQty
			) throws Exception {

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			Map<String, Object> coinWithDrawQty = holdportWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);
			return coinWithDrawQty;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 코인별 1회출금가능수량을 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkOnceWthrwQty
	 * @date : 2019. 12. 06.
	 * @author : 강호경
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 06.		강호경				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @param iCoinNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/wallet/wlt001/checkOnceWthrwQty", method = RequestMethod.POST)
	public Map<String, Object> checkOnceWthrwQty(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "coinNo", defaultValue = "10", required = true) int iCoinNo
			) throws Exception {

		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("COIN_NO", iCoinNo );

			Map<String, Object> OnceWthrwQty = holdportWalletService.checkOnceWthrwQty(paramMap);
			return OnceWthrwQty;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 코인 입출금 내역 리스트를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectCoinBalanceHistList
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				최초 작성
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
	@RequestMapping(value = "site/wallet/wlt001/selectCoinBalanceHistList", method = RequestMethod.POST)
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
                case '1': procStatTxt = '입금 요청'; break;
                case '2': procStatTxt = '컨펌 확인중'; break;
                case '3': procStatTxt = '출금 인증 대기중'; break;
                case '4': procStatTxt = ((reqTypeCd == '1') ? '입금':'출금') + '승인 대기중'; break;
                case '5': procStatTxt = '요청 취소'; break;
            }
            if('' + reqStatProcCd == '4' && '' + procStatCd == '1') {
                procStatTxt = '처리 완료';
            }
		 */
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");

			Map<String, Object> paramMap = new HashMap<>();

			if((iSearchOption & 0b111) == 0 || iSearchOption == 0b111) {
				// 모든 건 검색
			} else {
				if((iSearchOption & 0b100) != 0) {
					// '요청'건 인것만 검색
					paramMap.put("REQUEST", "REQUEST");
				}
				if((iSearchOption & 0b010) != 0) {
					// '대기'건 인것만 검색
					paramMap.put("WAIT", "WAIT");
				}
				if((iSearchOption & 0b001) != 0) {
					// '완료'건 인것만 검색
					paramMap.put("FINISH", "FINISH");
				}
			}

			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("DW_REQ_TYPE_CD", iDwRequestTypeCd );
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			List<Map<String, Object>> coinWithDrawMgrList = CommonUtils.numericConvertListMapValue(holdportWalletService.selectDepositWithdrawList(paramMap) );
			return coinWithDrawMgrList;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 코인 입출금 내역 리스트 갯수를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectCoinBalanceHistListCount
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				최초 작성
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
	@RequestMapping(value = "site/wallet/wlt001/selectCoinBalanceHistListCount", method = RequestMethod.POST)
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
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			strStartDate = strStartDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");
			strEndDate = strEndDate.replaceAll("-", "").replaceAll("/", "").replaceAll("[.]", "");

			Map<String, Object> paramMap = new HashMap<>();

			if((iSearchOption & 0b111) == 0 || iSearchOption == 0b111) {
				// 모든 건 검색
			} else {
				if((iSearchOption & 0b100) != 0) {
					// '요청'건 인것만 검색
					paramMap.put("REQUEST", "REQUEST");
				}
				if((iSearchOption & 0b010) != 0) {
					// '대기'건 인것만 검색
					paramMap.put("WAIT", "WAIT");
				}
				if((iSearchOption & 0b001) != 0) {
					// '완료'건 인것만 검색
					paramMap.put("FINISH", "FINISH");
				}
			}

			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("COIN_NO", iCoinNo );
			paramMap.put("DW_REQ_TYPE_CD", iDwRequestTypeCd );
			paramMap.put("START_DT", strStartDate );
			paramMap.put("END_DT", strEndDate );
			paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
			paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
			Map<String, Object> coinWithDrawMgrListCount = holdportWalletService.selectDepositWithdrawListCount(paramMap);
			return coinWithDrawMgrListCount;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

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
	@RequestMapping(value = "site/wallet/wlt001/requestSmsAuthNumber", method = RequestMethod.POST)
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
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			String strClientIp = CommonUtils.getClientIpAddr(request);

			// 최초 원화입금후 72시간 경과를 체크한다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			int check_72hour = holdportWalletService.checkWithdrawOk(paramMap);
			if( check_72hour < 0 && iCoinNo != 10 ) {
				// Roll-back Transaction
				transactionManager.rollback(status);
				resVo.setRtnCd(check_72hour);
				resVo.setRtnMsg("KRW입금후 72시간이 경과하지 않았습니다. (공휴일 제외)");
				return resVo;
			}

			// Coin Target Address의 Validation 체크
			paramMap.clear();
			paramMap.put("COINKIND", strCoinSymbol);
			paramMap.put("CoinAddress", strTargetAddr);
			boolean address_check = (iCoinNo != 10) ? holdportWalletService.checkValidateionUserCoinAddress(paramMap) : true ;
			if(!address_check) {
				// Roll-back Transaction
				transactionManager.rollback(status);
				resVo.setRtnCd(-5502);
				resVo.setRtnMsg("대상 코인 주소가 유효하지 않습니다.");
				return resVo;
			}

			// 회원정보  체크
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);

			// 잔고 체크
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_COIN_NO", iCoinNo );
			paramMap.put("ARG_REQ_QTY", dReqQty );
			Map<String, Object> coinWithDrawQtyCheckMap = holdportWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

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
			Map<String, Object> coinWithdrawRequestMap = holdportWalletService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
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
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// 휴대폰 번호
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", Long.valueOf(coinWithdrawRequestMap.get("V_REQ_SEQ_NO").toString()).longValue() );
			Map<String, Object> insertAuthCodeMap = holdportWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
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


			String smsMsg = String.format("[BITA500] 출금 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지", strAuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");

			boolean sendFlag = HttpSender.httpSmsRequest(memberInfoVo.getMOBILE(), smsMsg);

			//sendFlag = true;

			if(sendFlag) {

				resVo.setRtnCd(0);
				resVo.setRtnMsg("인증번호 발송에 성공하였습니다");

				transactionManager.commit(status);

			}else {

				resVo.setRtnCd(-5009);
				resVo.setRtnMsg("인증번호 발송에 실패하였습니다");

				transactionManager.rollback(status);
			}

			return resVo;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Roll-back Transaction
			transactionManager.rollback(status);
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5503);
			resVo.setRtnMsg("SMS 발신에 실패하였습니다.");
			return resVo;
		}
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : PR_WAS_SET_DW_REQ_STAT_PROC_CD 프로시저를 호출하여 사용자 출금요청 (인증대기중인것) 건을 취소한다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : cancelWithdrawItem
	 * @date : 2019. 5. 1.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 1.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @param lReqSeqNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/wallet/wlt001/cancelWithdrawItem", method = RequestMethod.POST)
	public ResultVO cancelWithdrawItem(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "reqSeqNo", defaultValue = "10", required = true) long lReqSeqNo
			) throws Exception {

		ResultVO resVo = new ResultVO();

		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ARG_REQ_SEQ_NO", lReqSeqNo);
			paramMap.put("ARG_REQ_STAT_PROC_CD", 5 );				// 5: 요청 취소
			Map<String, Object> cancelWithDrawItemMap = holdportWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);

			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			// 사용자가 출금신청 취소를 했을때 TB_MEMBER_AUTH_MGR 테이블에서 인증번호를 삭제한다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId);
			paramMap.put("TRANSACTION_KEY_VAL", lReqSeqNo );	// REQ_SEQ_NO 를 키값으로 삭제
			int res = holdportWalletService.deleteMemberAuthMgrByTxKeyBal(paramMap);

			resVo.setRtnCd(0);
			resVo.setRtnMsg("출금요청이 취소되었습니다.");
			return resVo;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			resVo.setRtnCd(-5504);
			resVo.setRtnMsg("출금요청 취소에 실패하였습니다.");
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
	 * @date : 2019. 5. 1.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 1.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @param strAuthCode
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/wallet/wlt001/doRequestWithDraw", method = RequestMethod.POST)
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
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			String strClientIp = CommonUtils.getClientIpAddr(request);

			// 회원 정보 체크
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);

			// TRANSACTION_KEY_VAL 키값을 얻어온다.
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			paramMap.put("AUTH_PURPOSE_CD", 3);											// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("AUTH_MEANS_CD", memberInfoVo.getAUTH_MEANS_CD() ); 			// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );				// 휴대폰 번호
			Map<String, Object> getTxKeyMap = holdportWalletService.selectMemberAuthMgrTxKeyBal(paramMap);

			if(memberInfoVo.getAUTH_MEANS_CD() == 2) {				// 인증수단 식별코드  2: SMS
				// 인증코드 유효성을 검사한다.
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_AUTH_PURPOSE_CD", 3);										// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
				paramMap.put("ARG_AUTH_MEANS_CD", memberInfoVo.getAUTH_MEANS_CD() ); 		// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
				paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );			// 휴대폰 번호
				paramMap.put("ARG_AUTH_CODE", strAuthCode );
				Map<String, Object> checkAuthCodeMap = holdportWalletService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);

				if(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() == 0) {
					// 인증코드가 유효하면 입출금 승인대기 상태로 상태값을 변경한다.
					paramMap.clear();
					paramMap.put("ARG_REQ_SEQ_NO", getTxKeyMap.get("TRANSACTION_KEY_VAL"));
					paramMap.put("ARG_REQ_STAT_PROC_CD", 4 );		// 입출금 승인대기 :4

					Map<String, Object> setDwReqStatusProcCdMap = holdportWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
				}

				resVo.setRtnCd(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() );
				resVo.setRtnMsg(checkAuthCodeMap.get("V_RTN_MSG").toString());
				return resVo;

			} else if(memberInfoVo.getAUTH_MEANS_CD() == 1) {		// 인증수단 식별코드 1: OTP
				// 제일먼저 OTP 유효성 검증을 한다.
				boolean otp_check = GoogleOTP.checkCode(strAuthCode, memberInfoVo.getOTP_KEY_VAL());
				if(!otp_check) {
					resVo.setRtnCd(-5505);
					resVo.setRtnMsg("OTP 인증번호가 맞지 않습니다.");
					return resVo;
				}

				// 최초 원화입금후 72시간 경과를 체크한다.
				paramMap.clear();
				paramMap.put("EXCHANGE_ID", strExchangeId);
				paramMap.put("USER_ID", strUserId );
				int check_72hour = holdportWalletService.checkWithdrawOk(paramMap);
				if(check_72hour < 0 && iCoinNo != 10) {
					resVo.setRtnCd(check_72hour);
					resVo.setRtnMsg("KRW입금후 72시간이 경과하지 않았습니다. (공휴일 제외)");
					return resVo;
				}

				if(iCoinNo != 10) {
					// Coin Target Address의 Validation 체크 (원화출금을 제외한 코인일때만)
					paramMap.clear();
					paramMap.put("COINKIND", strCoinSymbol);
					paramMap.put("CoinAddress", strTargetAddr);
					//boolean address_check = holdportWalletService.checkValidateionUserCoinAddress(paramMap);
					boolean address_check = true;
					if(!address_check) {
						resVo.setRtnCd(-5502);
						resVo.setRtnMsg("대상 코인 주소가 유효하지 않습니다.");
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
				Map<String, Object> coinWithDrawQtyCheckMap = holdportWalletService.call_PR_WAS_CHECK_WITHDRAW(paramMap);

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
				Map<String, Object> coinWithdrawRequestMap = holdportWalletService.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
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

				Map<String, Object> setDwReqStatusProcCdMap = holdportWalletService.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);

				// Commit Transaction
				transactionManager.commit(status);

				resVo.setRtnCd(0);
				resVo.setRtnMsg("출금신청되었습니다.");
				return resVo;
			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resVo.setRtnCd(-5506);
			resVo.setRtnMsg("출금신청이 실패하였습니다.");
			return resVo;
		}

		return resVo;
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 원화 충전요청을 진행한다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : requestMoneyDeposit
	 * @date : 2019. 5. 6.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 6.		kangn				최초 작성
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
	@RequestMapping(value = "site/wallet/wlt001/requestMoneyDeposit", method = RequestMethod.POST)
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
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			String strReqIP = CommonUtils.getClientIpAddr(request);
			String strReqChanndeCd = "WEB";

			CoinMgtRefInfoVO coinMgtRefInfo_Default = holdportWalletService.selectCoinMgtRefInfoByCoinNo(CamelHelper.getDefaultConType(CommonUtils.getNewExchangeId(request)));

			if(coinMgtRefInfo_Default.getMIN_DEPOSIT_QTY() > dRequestAmount) {
				resultVo.setRtnCd(-5092);
				resultVo.setRtnMsg("최소 입금 수량보다 요청 수량이 작습니다.");
			} else if(user.getAuthLevel() >= 3) {
				// PR_WLL_INSERT_DEPOSIT_REQUEST 프로시저 이용하여 TB_DEPOSIT_WITHDRAW_MGR 테이블 insert
				DepositWithdrawMgrVO dwMgrVo = new DepositWithdrawMgrVO();
				dwMgrVo.setREQ_IP(strReqIP);
				dwMgrVo.setREQ_CHNL_CD(strReqChanndeCd);
				dwMgrVo.setEXCHANGE_ID(strExchangeId);
				dwMgrVo.setUSER_ID(strUserId);
				dwMgrVo.setDW_REQ_TYPE_CD(3);				// 3 : 현금 입금
				dwMgrVo.setFROM_ADDR("");
				dwMgrVo.setPIN_CD(strFinCode);
				dwMgrVo.setTARGET_ADDR(strAccountNumber);
				dwMgrVo.setTARGET_ADDR_ETC1(strBankCode);
				dwMgrVo.setTARGET_ADDR_ETC2(strHolderName);
				dwMgrVo.setCOIN_NO(CamelHelper.getDefaultConType(CommonUtils.getNewExchangeId(request)));
				dwMgrVo.setREQ_QTY(dRequestAmount);
				dwMgrVo.setREQ_MEMO("");
				dwMgrVo.setREQ_STAT_PROC_CD(1);				// 요청진행상태코드 Default=1(요청-원화) or 2(컨펌대기-코인))
				dwMgrVo.setAPPROVAL_STAT_CD(0);				// 승인상태코드 (Default=0 미승인)
				dwMgrVo.setDW_PROC_STAT_CD(0);				// 입출금처리상태코드 (Default=0 미처리)
				dwMgrVo.setTRANSACTION_IDX(0);
				int result = holdportWalletService.insertDepositWithdrawManager(dwMgrVo);

//				// PR_WLL_INSERT_DEPOSIT_REQUEST 프로시저 이용하여 TB_DEPOSIT_WITHDRAW_MGR 테이블 insert
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
//				paramMap.put("ARG_COIN_NO", CamelHelper.getDefaultConType(CommonUtils.getNewExchangeId(request)) );
//				paramMap.put("ARG_REQ_QTY", dRequestAmount );
//				paramMap.put("ARG_REQ_MEMO", "" );
//				paramMap.put("ARG_REQ_STAT_PROC_CD", 1 );		// 요청진행상태코드 Default=1(요청-원화) or 2(컨펌대기-코인))
//				paramMap.put("ARG_APPROVAL_STAT_CD", 0 );		// 승인상태코드 (Default=0 미승인)
//				paramMap.put("ARG_DW_PROC_STAT_CD", 0 );		// 입출금처리상태코드 (Default=0 미처리)
//				paramMap.put("ARG_TRANSACTION_IDX", 0 );
//				paramMap.put("V_RTN_CD", 0 );
//				paramMap.put("V_RTN_MSG", "" );
//				Map<String, Object> resultMap = holdportWalletService.insertRequestMoneyDeposit(paramMap);

				resultVo.setRtnCd((result > 0) ? 0 : -5507);
				resultVo.setRtnMsg((result > 0) ? "입금요청 성공" : "입금요청 실패");
			} else {
				resultVo.setRtnCd(-5091);
				resultVo.setRtnMsg("보안 등급이 레벨 3 이상일 경우 이용 가능합니다");
			}

			return resultVo;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resultVo.setRtnCd(-5507);
			resultVo.setRtnMsg("입금신청이 실패하였습니다." );
			return resultVo;
		}
	}

}
