/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt002.controller;

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

import kr.co.coinis.webserver.coinis.wallet.wlt001.service.DepositWithdrawalsService;
import kr.co.coinis.webserver.coinis.wallet.wlt002.service.TransactionService;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt002.controller 
 *    |_ TransactionController.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:11:00
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class TransactionController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private DepositWithdrawalsService depositWithdrawalsService;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@RequestMapping(value = "coinis/transaction")
	public ModelAndView moveTransaction(HttpServletRequest request) {
		
		try {
			ModelAndView model = new ModelAndView();
			model.setViewName(CommonUtils.getPageKey(request) + "/wallet/wlt002/transaction");
			
			String exchange_id = CommonUtils.getExchangeId(request);
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
	
			List<Map<String, Object>> exchangeCoinList = depositWithdrawalsService.selectExchangeCoinList(exchange_id);
			model.addObject("exchangeCoinList", new Gson().toJson(exchangeCoinList));

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

			return model;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 코인 입출금 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinDepositWithdrawalList
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
	@RequestMapping(value = "coinis/wallet/wlt002/selectCoinDepositWithdrawalList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectCoinDepositWithdrawalList(HttpServletRequest request, HttpSession session
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
			String strExchangeId = CommonUtils.getExchangeId(request);
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
			List<Map<String, Object>> coinWithDrawMgrList = CommonUtils.numericConvertListMapValue(transactionService.selectDepositWithdrawList(paramMap) );
			return coinWithDrawMgrList;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 코인 입출금 내역 리스트 갯수를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinDepositWithdrawalListCount
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
	@RequestMapping(value = "coinis/wallet/wlt002/selectCoinDepositWithdrawalListCount", method = RequestMethod.POST)
	public Map<String, Object> selectCoinDepositWithdrawalListCount(HttpServletRequest request, HttpSession session
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
			Map<String, Object> coinWithDrawMgrListCount = transactionService.selectDepositWithdrawListCount(paramMap);
			return coinWithDrawMgrListCount;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
}
