/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.support.spt001.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;

import kr.co.coinis.webserver.holdport.support.spt001.service.HoldportNoticeService;
import kr.co.coinis.webserver.holdport.support.spt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.EventVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.WithdrawLimitVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.controller 
 *    |_ NoticeController.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:33:19
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class HoldportNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HoldportNoticeController.class);

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private HoldportNoticeService holdportNoticeService;
	
	@RequestMapping(value = "site/support")
	public ModelAndView moveNotice(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getSitePackageKey(request) + "/support/spt001/notice");

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
	@RequestMapping(value = "site/support/spt001/selectNoticeList", method = RequestMethod.POST)
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
		List<NoticeVO> noticeVoList = holdportNoticeService.selectNoticeList(paramMap);
		return noticeVoList;
	}

	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectNoticeInitData", method = RequestMethod.POST)
	public List<NoticeVO> selectNoticeInitData(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId 
			, @RequestParam(value = "searchQry", defaultValue = "", required = false) String strSearchQry 
			) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		if(!strSearchQry.equals("")) paramMap.put("SEARCH_QRY", strSearchQry);
		List<NoticeVO> noticeVoList = holdportNoticeService.selectNoticeListCount(paramMap);
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
	@RequestMapping(value = "site/support/spt001/selectEventList", method = RequestMethod.POST)
	public List<EventVO> selectEventList(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId 
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<EventVO> eventVoList = holdportNoticeService.selectEventList(paramMap);
		return eventVoList;
	}
	
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectEventInitData", method = RequestMethod.POST)
	public List<EventVO> selectEventInitData(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "YAHOBIT", required = true) String strExchangeId 
			) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		List<EventVO> eventVoList = holdportNoticeService.selectEventListCount(paramMap);
		return eventVoList;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_COIN_CODE_MAST 테이블에서 기초자산 코인식별번호에 해당하는 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinCodeMasterList
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
	 * @param iBasicAssetCoinNo
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectCoinCodeMasterList", method = RequestMethod.POST)
	public List<ItemCodeMastVO> selectCoinCodeMasterList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "basicAssetCoinNo", defaultValue = "20", required = true) int iBasicAssetCoinNo 
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		String strExchangeId = CommonUtils.getExchangeId(request);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId );			// 거래소 ID
		paramMap.put("BASIC_ASSET_COIN_NO", iBasicAssetCoinNo);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<ItemCodeMastVO> coinCodeMasterList = holdportNoticeService.selectItemCodeMasterByBasicCoinNo(paramMap);
		return coinCodeMasterList;
	}
	
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectCoinCodeMasterInitData", method = RequestMethod.POST)
	public List<ItemCodeMastVO> selectCoinCodeMasterInitData(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "basicAssetCoinNo", defaultValue = "COINIS", required = true) int iBasicAssetCoinNo  
			) throws Exception {
		
		String strExchangeId = CommonUtils.getExchangeId(request);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId );			// 거래소 ID
		paramMap.put("BASIC_ASSET_COIN_NO", iBasicAssetCoinNo);
		List<ItemCodeMastVO> coinCodeMasterList = holdportNoticeService.selectCoinCodeMasterInitData(paramMap);
		return coinCodeMasterList;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_EXCHANGE_MKT_INFO 테이블에서 기초자산 코인식별번호에 해당하는 수수료 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectExchangeMarketInfoList
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
	 * @param iBasicAssetCoinNo
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectExchangeMarketInfoList", method = RequestMethod.POST)
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoList(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "COINIS", required = true) String strExchangeId 
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<ExchangeMktInfoVO> exchangeMktInfoList = holdportNoticeService.selectExchangeMarketInfoList(paramMap);
		return exchangeMktInfoList;
	}
	
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectExchangeMarketInfoInitData", method = RequestMethod.POST)
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoInitData(HttpSession session
			, @RequestParam(value = "exchangeId", defaultValue = "COINIS", required = true) String strExchangeId 
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId);
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 1000 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 1000);				// 한페이지에 보여줄 페이지 갯수
		List<ExchangeMktInfoVO> exchangeMktInfoList = holdportNoticeService.selectExchangeMarketInfoList(paramMap);
		return exchangeMktInfoList;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 코인관리기준정보 테이블에서 출금수수료, 최소출금수량, 최소입금수량 select
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinMgtRefInfoList
	 * @date : 2019. 4. 26.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param session
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectCoinMgtRefInfoList", method = RequestMethod.POST)
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		String strExchangeId = CommonUtils.getExchangeId(request);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId );		// 거래소 ID
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 20 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 20);					// 한페이지에 보여줄 페이지 갯수
		List<CoinMgtRefInfoVO> coinMgtRefInfoList = holdportNoticeService.selectCoinMgtRefInfoList(paramMap);
		return coinMgtRefInfoList;
	}
	
	@ResponseBody
	@RequestMapping(value = "site/support/spt001/selectCoinMgtRefInfoListInitData", method = RequestMethod.POST)
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoListInitData(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		String strExchangeId = CommonUtils.getExchangeId(request);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("EXCHANGE_ID", strExchangeId );		// 거래소 ID
		paramMap.put("PAGE_INDEX", (iPageNum - 1) * 1000 );	// 시작 인덱스
		paramMap.put("PAGE_CONT_NUM", 1000);				// 한페이지에 보여줄 페이지 갯수
		List<CoinMgtRefInfoVO> coinMgtRefInfoList = holdportNoticeService.selectCoinMgtRefInfoList(paramMap);
		return coinMgtRefInfoList;
	}

	@ResponseBody
	@RequestMapping(value = "site/support/spt001/getOutLimit", method = RequestMethod.POST)
	public List<WithdrawLimitVO> getOutLimit(HttpServletRequest request, HttpSession session) throws Exception {
		
		List<WithdrawLimitVO> result = null;
		
		try {
			result = holdportNoticeService.selectCoinWithdrawLimit(CommonUtils.getExchangeId(request));
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result = new ArrayList<>();
		}
		
		return result;
	}
}
