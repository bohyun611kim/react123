/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd001.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import kr.co.coinis.webserver.common.utils.CryptProvider;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.PointApiSender;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;
import kr.co.coinis.webserver.holdport.product.prd001.service.HoldportProductService;
import kr.co.coinis.webserver.holdport.product.prd001.vo.EventVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.NoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.product.prd001.controller
 *    |_ NoticeController.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오후 1:33:19
 * @version :
 * @author : Seongcheol
 */
@Controller
public class HoldportProductController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportProductController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldportProductService holdportProductService;

	@Autowired
	private CommService commService;

	@RequestMapping(value = "site/product")
	public ModelAndView moveNotice(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/product/prd001/product");

		try {
			String locale = redisSessionRepository.get("localInfo" + request.getRequestedSessionId());
			model.addObject("locale", locale == null ||  locale.equalsIgnoreCase("") ? "ko" : locale);

			CommonUtils.adaptLocale(request, locale, model);

			String tab = request.getParameter("tab");
			String no = request.getParameter("no");

			if(tab != null && !"".equals(tab)) {
				model.addObject("tab", tab);
				model.addObject("no", no);
			}

		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}

		return model;
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_NOTICE 테이블에서 공지사항 리스트를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectNoticeList
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
	 * @param strExchangeId
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/product/prd001/selectNoticeList", method = RequestMethod.POST)
	public List<NoticeVO> selectNoticeList(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId
			, @RequestParam(value = "searchQry", defaultValue = "", required = false) String strSearchQry
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum
			) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		if(!strSearchQry.equals("")) paramMap.put("SEARCH_QRY", strSearchQry);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<NoticeVO> noticeVoList = holdportProductService.selectNoticeList(paramMap);
		return noticeVoList;
	}

	@ResponseBody
	@RequestMapping(value = "site/product/prd001/selectNoticeInitData", method = RequestMethod.POST)
	public List<NoticeVO> selectNoticeInitData(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId
			, @RequestParam(value = "searchQry", defaultValue = "", required = false) String strSearchQry
			) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		if(!strSearchQry.equals("")) paramMap.put("SEARCH_QRY", strSearchQry);
		List<NoticeVO> noticeVoList = holdportProductService.selectNoticeListCount(paramMap);
		return noticeVoList;
	}


	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_EVENT 테이블에서 이벤트 리스트를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectNoticeList
	 * @date : 2019. 5. 17.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 17.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param session
	 * @param strExchangeId
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/product/prd001/selectEventList", method = RequestMethod.POST)
	public List<EventVO> selectEventList(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum
			) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<EventVO> eventVoList = holdportProductService.selectEventList(paramMap);
		return eventVoList;
	}

	@ResponseBody
	@RequestMapping(value = "site/product/prd001/selectEventInitData", method = RequestMethod.POST)
	public List<EventVO> selectEventInitData(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId
			) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		List<EventVO> eventVoList = holdportProductService.selectEventListCount(paramMap);
		return eventVoList;
	}


	@RequestMapping(value = "/site/product/prd001/test")
	public ModelAndView moveTest(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/product/prd001/test");

		return model;

	}

	@ResponseBody
	@RequestMapping(value = "/site/product/prd001/pointConvertRequestXX", method = RequestMethod.POST)
	public Map<String, Object> convertPointRequestXX(HttpServletRequest request, HttpSession session) throws Exception {

		String encryptKey="6058644588581810";
		String apiUrl="http://dibapayad.joy365.kr/External/CoinTrade.asp";

		Map<String, Object> resultMap = new HashMap<>();

		//ModelAndView model = new ModelAndView();
		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

		String pointUserId=request.getParameter("PointUserId");
		String pointUserPwd=request.getParameter("PointUserPwd");
		String tradePoint=request.getParameter("TradePoint");
		String requestIp=request.getParameter("RequestIp");

		logger.debug("pointUserId["+pointUserId+"],pointUserPwd["+pointUserPwd+"],tradePoint["+tradePoint+"], requestIp["+requestIp+"]");

		try {

			// check parameter validation
			if(pointUserId==null || pointUserPwd==null||tradePoint==null  ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				return resultMap;
			}

			if(Integer.parseInt(tradePoint) < 1000 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				return resultMap;
			}

			//check user balance
			Map<String, Object> map = new HashMap<>();

			map.put("exchangeId", CommonUtils.getNewExchangeId(request));
			map.put("userId", user.getUserId() );
			map.put("coinNo", 10);

			Map<String, Object> result=holdportProductService.selectCoinBalance(map);

			logger.info("result map["+result.toString()+"]");

			BigDecimal balance = (BigDecimal) result.get("BALANCE_QTY");

			if(balance.doubleValue() < Double.parseDouble(tradePoint) ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "insufficient balance");
				return resultMap;
			}

			HashMap<String, String> requestData = new HashMap<String, String>();
			HashMap<String, String> responseData = new HashMap<String, String>();

			long beginTime=System.currentTimeMillis();

			String encryptedMemberId=CryptProvider.getAesEncryptData(pointUserId, encryptKey, null);
			String encryptedMemberPwd=CryptProvider.getAesEncryptData(pointUserPwd, encryptKey, null);

			// 1. 회원정보연동(bita500<-> bidapay(wrdpay))
			requestData.put("TradeType", "0101");
			requestData.put("UserID", encryptedMemberId);
			requestData.put("UserPwd", encryptedMemberPwd);
			requestData.put("TradeTime", DateUtils.getSystemDate(0));

			logger.debug("token request["+requestData.toString()+"]");
			responseData=PointApiSender.httpGetMemberToken(apiUrl, requestData);
			logger.debug("result["+responseData.toString()+"]");

			if(responseData.get("resultCode").equals(PointApiSender.SERVICE_FAIL)) {
				resultMap.put("resultCode", responseData.get("resultCode"));
				resultMap.put("resultMsg", responseData.get("resultMsg"));

				return resultMap;

			}

			//2. 포인트전환연동(bita500<-> bidapay(wrdpay))
			HashMap<String, String> pointConvertData = new HashMap<String, String>();

			pointConvertData.put("TradeType", "0202");
			pointConvertData.put("UserID", encryptedMemberId);
			pointConvertData.put("UserPwd", encryptedMemberPwd);
			pointConvertData.put("TradeTime",  DateUtils.getSystemDate(0));
			pointConvertData.put("UserKey", responseData.get("resultToken"));
			pointConvertData.put("TradePoint", tradePoint);

			logger.debug("point request["+requestData.toString()+"]");
			responseData=PointApiSender.httpPointConvertRequest(apiUrl, pointConvertData);
			logger.debug("result["+responseData.toString()+"]");

			long endTime=System.currentTimeMillis();
			logger.debug("process time["+(endTime-beginTime)+"]ms");

			if(responseData.get("resultCode").equals(PointApiSender.SERVICE_FAIL)) {
				resultMap.put("resultCode", responseData.get("resultCode"));
				resultMap.put("resultMsg", responseData.get("resultMsg"));
				return resultMap;
			}

			//3. Table Update
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("exchangeId", CommonUtils.getNewExchangeId(request));
			paramMap.put("userId", user.getUserId());
			paramMap.put("pointUserId", pointUserId);
			paramMap.put("coinNo", 10);
			paramMap.put("reqQty", Integer.parseInt(tradePoint));
			paramMap.put("requestIp", requestIp);

			holdportProductService.updatePointConvertRequest(paramMap);

			logger.debug("db update result["+paramMap.toString()+"]");


			if( (int)paramMap.get("rtnCd")==0 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_SUCCESS);
				resultMap.put("resultMsg", "service success");
			}else {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "service fail");
			}

			return resultMap;

		}catch(Exception e) {

			e.printStackTrace();

			resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
			resultMap.put("resultMsg", "service fail");
			return resultMap;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/site/product/prd001/pointConvertReq", method = RequestMethod.POST)
	public Map<String, Object> convertPointReq(HttpServletRequest request, HttpSession session) throws Exception {

		String apiUrl="http://dibapayad.joy365.kr/External/CoinTrade.asp";

		//String encryptKey="6058644588581810";
		String encryptKey="DIBA4ONEPAYKOREA";

		Map<String, Object> resultMap = new HashMap<>();

		//ModelAndView model = new ModelAndView();
		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

		String pointUserId=request.getParameter("PointUserId");
		String pointUserPwd=request.getParameter("PointUserPwd");
		String tradePoint=request.getParameter("TradePoint");
		String requestIp=request.getParameter("RequestIp");

		logger.debug("pointUserId["+pointUserId+"],pointUserPwd["+pointUserPwd+"],tradePoint["+tradePoint+"], requestIp["+requestIp+"]");

		try {

			// check parameter validation
			if(pointUserId==null || pointUserPwd==null||tradePoint==null  ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				return resultMap;
			}

			if(Integer.parseInt(tradePoint) < 1000 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				return resultMap;
			}

			//check user balance
			Map<String, Object> map = new HashMap<>();

			map.put("exchangeId", CommonUtils.getNewExchangeId(request));
			map.put("userId", user.getUserId() );
			map.put("coinNo", 10);

			Map<String, Object> result=holdportProductService.selectCoinBalance(map);

			logger.info("result map["+result.toString()+"]");

			BigDecimal balance = (BigDecimal) result.get("BALANCE_QTY");

			if(balance.doubleValue() < Double.parseDouble(tradePoint) ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "insufficient balance");
				return resultMap;
			}

			HashMap<String, String> requestData = new HashMap<String, String>();
			HashMap<String, String> responseData = new HashMap<String, String>();

			String encryptedMemberId=CryptProvider.getAesEncryptData(pointUserId, encryptKey, null);
			String encryptedMemberPwd=CryptProvider.getAesEncryptData(pointUserPwd, encryptKey, null);

			// 1. 회원정보연동(bita500<-> bidapay(wrdpay))
			requestData.put("TradeType", "0101");
			requestData.put("UserID", encryptedMemberId);
			requestData.put("UserPwd", encryptedMemberPwd);
			requestData.put("TradeTime", DateUtils.getSystemDate(0));

			logger.debug("token request["+requestData.toString()+"]");
			responseData=PointApiSender.httpGetMemberToken(apiUrl, requestData);
			logger.debug("result["+responseData.toString()+"]");

			if(responseData.get("resultCode").equals(PointApiSender.SERVICE_FAIL)) {
				resultMap.put("resultCode", responseData.get("resultCode"));
				resultMap.put("resultMsg", responseData.get("resultMsg"));

				return resultMap;
			}

			//1. Table Update
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("exchangeId", CommonUtils.getNewExchangeId(request));
			paramMap.put("userId", user.getUserId());
			paramMap.put("pointUserId", encryptedMemberId);
			paramMap.put("pointUserPwd", encryptedMemberPwd);
			paramMap.put("coinNo", 10);
			paramMap.put("reqQty", Integer.parseInt(tradePoint));
			paramMap.put("requestIp", requestIp);

			holdportProductService.updatePointConvertRequest(paramMap);

			logger.debug("db update result["+paramMap.toString()+"]");


			if( (int)paramMap.get("rtnCd")==0 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_SUCCESS);
				resultMap.put("resultMsg", "service success");
			}else {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "service fail");
			}

			return resultMap;

		}catch(Exception e) {

			e.printStackTrace();

			resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
			resultMap.put("resultMsg", "service fail");
			return resultMap;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/site/product/prd001/convertCoinToPointReq", method = RequestMethod.POST)
	public Map<String, Object> convertCoinToPointReq(HttpServletRequest request, HttpSession session) throws Exception {

		String apiUrl="http://wrdpayad.joy365.kr/External/CoinTrade.asp";
		//String apiUrl="http://dibapayad.joy365.kr/External/CoinTrade.asp";


		String encryptKey="DIBA4ONEPAYKOREA";

		Map<String, Object> resultMap = new HashMap<>();

		//ModelAndView model = new ModelAndView();
		CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

		String mktId = request.getParameter("MktId");
		String mktGrpId = request.getParameter("MktGrpId");
		String itemCode=request.getParameter("ItemCode");
		String ordQty=request.getParameter("OrdQty");

		//String ordPrice=request.getParameter("OrdPrice");
		String ordPrice="1.0";

		String pointUserId=request.getParameter("PointUserId");
		String pointUserPwd=request.getParameter("PointUserPwd");

		String requestIp=request.getParameter("RequestIp");


		logger.debug("MktId["+mktId+"],mktGrpId["+mktGrpId+"]itemCode["+itemCode+"], ordPrice["+ordPrice+"], ordQty["+ordQty+"]");
		logger.debug("pointUserId["+pointUserId+"],pointUserPwd["+pointUserPwd+"], requestIp["+requestIp+"]");

		try {

			if(mktId==null || mktGrpId==null|| itemCode==null || ordPrice==null|| pointUserId==null || pointUserPwd==null ) {

				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				resultMap.put("convertPoint", "0");

				return resultMap;
			}

			if(user.getAuthLevel() <= 1) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg","보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
				resultMap.put("convertPoint", "0");
				return resultMap;
			}

			double sellPrice= Double.parseDouble(ordPrice);
			double sellQty= Double.parseDouble(ordQty);

			if(sellPrice <=0.0 || sellQty<= 0.0 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "invalid parameter");
				resultMap.put("convertPoint", "0");
				return resultMap;
			}

			String encryptedMemberId=CryptProvider.getAesEncryptData(pointUserId, encryptKey, null);
			String encryptedMemberPwd=CryptProvider.getAesEncryptData(pointUserPwd, encryptKey, null);


			// 1. 회원정보연동(bita500<-> bidapay(wrdpay))

			HashMap<String, String> requestData = new HashMap<String, String>();
			HashMap<String, String> responseData = new HashMap<String, String>();

			requestData.put("TradeType", "0101");
			requestData.put("UserID", encryptedMemberId);
			requestData.put("UserPwd", encryptedMemberPwd);
			requestData.put("TradeTime", DateUtils.getSystemDate(0));

			logger.debug("token request["+requestData.toString()+"]");
			responseData=PointApiSender.httpGetMemberToken(apiUrl, requestData);
			logger.debug("result["+responseData.toString()+"]");

			if(responseData.get("resultCode").equals(PointApiSender.SERVICE_FAIL)) {
				resultMap.put("resultCode", responseData.get("resultCode"));
				resultMap.put("resultMsg", responseData.get("resultMsg"));
				resultMap.put("convertPoint", "0");
				return resultMap;
			}

			// 2. COIN 매도처리
			SendOrderVO vo= new SendOrderVO();

			String exchangeId = CommonUtils.getNewExchangeId(request);

			vo.setExchangeId(exchangeId);
			vo.setUserId(user.getUserId());
			vo.setMktGrpId(mktGrpId);
			vo.setMktId(mktId);
			vo.setItemCode(itemCode);
			vo.setOrdQty(sellQty);
			vo.setOrdPrice(sellPrice);
			vo.setOrdTypeCd(1); // 1: 신규 2: 취소
			vo.setOrdPriceTypeCd(2); // 1: 지정가 2 시장가
			vo.setSellBuyRecogCd(2); // 1: 매수 2: 매도
			vo.setPublicIp(CommonUtils.getClientIpAddr(request));
			vo.setAutoMiningYn(0);

			HashMap<String, Object> orderMap = commService.sendOrderNew(vo);

			logger.debug("sendOrderNew result["+orderMap.toString()+"]");

			Thread.sleep(3000);

			// 3. 체결주문조회
			HashMap<String, Object> tradeMap=holdportProductService.selectCoinTradeAmout(orderMap);

			int coinTradeKrw= ((BigDecimal)tradeMap.get("TRADE_AMT")).intValue();
			double tradeQty= ((BigDecimal)tradeMap.get("TRADE_QTY")).doubleValue();

			logger.debug(">>Point ::  coinTradeKrw["+coinTradeKrw+"],tradeQty["+tradeQty+"]");

			if(coinTradeKrw < 1  ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "order fail");
				resultMap.put("convertPoint", "0");
				return resultMap;
			}

			//4. Check Current Balance
			Map<String, Object> map = new HashMap<>();

			map.put("exchangeId", CommonUtils.getNewExchangeId(request));
			map.put("userId", user.getUserId() );
			map.put("coinNo", 10);

			Map<String, Object> result=holdportProductService.selectCoinBalance(map);
			logger.info("result map["+result.toString()+"]");

			BigDecimal balance = (BigDecimal) result.get("BALANCE_QTY");

			if(balance.doubleValue() < coinTradeKrw ) {

				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "insufficient balance");
				resultMap.put("convertPoint", "0");

				return resultMap;
			}

			//5. Table Update( TB_DEPOSIT_WITHDRAW_MGR)
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("exchangeId", CommonUtils.getNewExchangeId(request));
			paramMap.put("userId", user.getUserId());
			paramMap.put("pointUserId", encryptedMemberId);
			paramMap.put("pointUserPwd", encryptedMemberPwd);
			paramMap.put("fromAddr", itemCode);
			paramMap.put("coinNo", 10);
			paramMap.put("reqQty", coinTradeKrw);
			paramMap.put("pinCd",  String.valueOf(tradeQty));
			paramMap.put("requestIp", requestIp);

			holdportProductService.procCoinToPointConvert(paramMap);
			logger.debug("db update result["+paramMap.toString()+"]");

			if( (int)paramMap.get("rtnCd")==0 ) {
				resultMap.put("resultCode", PointApiSender.SERVICE_SUCCESS);
				resultMap.put("resultMsg", "service success");
				resultMap.put("convertPoint", String.valueOf(coinTradeKrw));

			}else {

				resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
				resultMap.put("resultMsg", "service fail");
				resultMap.put("convertPoint", "0");
			}

			return resultMap;

		}catch(Exception e) {

			e.printStackTrace();

			resultMap.put("resultCode", PointApiSender.SERVICE_FAIL);
			resultMap.put("resultMsg", "service fail");
			resultMap.put("convertPoint", "0");

			return resultMap;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/site/product/prd001/pointConvertList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectPointConvertInfo(HttpServletRequest request, HttpSession session) throws Exception {

		Gson gson = new Gson();

		ModelAndView model = new ModelAndView();
		String itemCode = request.getParameter("ItemCode");

		if(itemCode==null) {
			itemCode="";
		}


		logger.debug("selectPointConvertInfo :: ItemCode["+itemCode+"]");

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("exchangeId", strExchangeId);
			paramMap.put("userId", strUserId );
			paramMap.put("fromAddr", itemCode );

			logger.debug("selectPointConvertInfo :: paramMap["+paramMap.toString()+"]");

			List<Map<String, Object>> pointConvertList = holdportProductService.selectPointConvertList(paramMap);
			model.addObject("pointConvertInfo", gson.toJson(pointConvertList) );

			return pointConvertList;

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}

	}

}
