/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.yeprp.controller;

import java.math.BigDecimal;
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

import kr.co.coinis.webserver.common.exception.ErrorResultException;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.yeprp.service.YahobitYepRpService;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.yeprp.controller 
 *    |_ YahobitYepRpController.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오후 3:45:17
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class YahobitYepRpController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitYepRpController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	YahobitYepRpService yahobitYepRpService;
	
	@Autowired
	private CommService commService; 

	@RequestMapping(value = "/alibit/yeprp", method=RequestMethod.GET)
	public ModelAndView moveNotice(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/yeprp/yeprp");
		
		return model;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : YEP 유통현황 데이터를 조회한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDistributionStatus
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectDistributionStatus", method = RequestMethod.POST)
	public Map<String, Object> selectDistributionStatus(HttpServletRequest request, HttpSession session ) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> dstrbtInfoMap = yahobitYepRpService.selectDistributionStatus();
			
			return dstrbtInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 거래소의 YEP Mining 현황을 조회하여 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMiningStatus
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMiningStatus", method = RequestMethod.POST)
	public Map<String, Object> selectYepMiningStatus(HttpServletRequest request, HttpSession session 
				, @RequestParam(value = "baseDate", defaultValue = "", required = true) String strBaseDate
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_DATE", strBaseDate );
			Map<String, Object> miningInfoMap = yahobitYepRpService.selectYepMiningStatus(paramMap);
			
			// TOTAL_MINING_STATUS_MAP					>> 최상단 YEP Mining 형황에 보여줄 데이터
			// PREV_DATE_MINING_DISTRIBUTION_MAP		>> 어제 발생거래 수수료대비 배분량 보여줄 데이터
			// CUR_DATE_MINING_DISTRIBUTION_MAP			>> 오늘 발생거래 수수료대비 배분량 보여줄 데이터
			// TODAY_TOP50_YEP_MINERS_LIST				>> YEP Mining 현황 상위 랭커 리스트 데이터
			// MY_YESTERDAY_MINING_STATUS_MAP			>> 나의 Mining 현황 어제날짜에 보여줄 데이터
			// MY_TODAY_MINING_STATUS_MAP				>> 나의 Mining 현황 오늘날짜에 보여줄 데이터
			// MONTHLY_MINING_LIST						>> 일 Mining 내역에 보여줄 데이터
			// MY_MINING_INFO_LIST						>> 나의 Mining 내역에 보여줄 데이터
			// MY_KRW_POSSES_MAP						>> 나의 Auto Mining 설정(자동채굴기능)에 보여줄 데이터

			return miningInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 Staking 정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepStakingStatus
	 * @date : 2019. 6. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseDate
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepStakingStatus", method = RequestMethod.POST)
	public Map<String, Object> selectYepStakingStatus(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseDate", defaultValue = "", required = true) String strBaseDate
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_DATE", strBaseDate );
			Map<String, Object> stakingInfoMap = yahobitYepRpService.selectYepStakingStatus(paramMap);
			
			// TOTAL_STAKING_STATUS_MAP					>> 상단 종합 Status 자료 select
			// PREV_DATE_STAKING_INFO_MAP				>> 어제의 Staking 참여, Freezing 자료 select
			// CUR_DATE_STAKING_INFO_MAP				>> 오늘의 Staking 참여, Freezing 자료 select
			// TODAY_TOP50_YEP_STAKING_RANKER_LIST		>> 오늘의 YEP Staking 기여도 상위자 리스트 조회
			// MY_YESTERDAY_STAKING_STATUS_MAP			>> 어제의 나의 Staking 현황 조회
			// MY_TODAY_STAKING_STATUS_MAP				>> 오늘의 나의 Staking 현황 조회
			// MY_AVAILABLE_STAKING_INFO_MAP			>> 나의 Staking 설정 (YEP RP 참여) 데이터 조회
			// MY_UNSTAKING_HISTORY_LIST				>> 나의 Unstaking 이력 리스트 조회
			// MONTHLY_STAKING_LIST						>> 일별 Staking 현황 리스트 조회
			// MY_STAKING_INFO_LIST						>> 나의 일별 Staking 내역 리스트 조회

			return stakingInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 Auto Mining 을 요청한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestAutoMining
	 * @date : 2019. 7. 31.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 31.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param dAutoMiningReqAmount
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/requestAutoMining", method = RequestMethod.POST)
	public ResultVO requestAutoMining(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "auto_mining_amount", defaultValue = "", required = true) double dAutoMiningReqAmount
			) throws Exception {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		
			if(user.getAuthLevel() > 1) {
				if(CommonUtils.isLogin(user)) {
					String exchangeId = CommonUtils.getExchangeId(request);
					
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("EXCHANGE_ID", exchangeId);
					paramMap.put("USER_ID", user.getUserId());
					Map<String, Object> myKrwPossessionInfoMap = yahobitYepRpService.selectKrwPossessionInfo(paramMap);
					
					if(myKrwPossessionInfoMap != null) {
						Map<String, Object> a = (HashMap<String, Object>) myKrwPossessionInfoMap.get("MY_KRW_POSSES_MAP");
						BigDecimal b = (BigDecimal) a.get("TOTAL_USABLE_BY_BC");
						
						if(b.doubleValue() >= dAutoMiningReqAmount) {
							paramMap = new HashMap<>();
							paramMap.put("ARG_EXCHANGE_ID", exchangeId);
							paramMap.put("ARG_USER_ID", user.getUserId());
							paramMap.put("ARG_ITEM_CODE", "YEPKRW");
							paramMap.put("ARG_BASIC_ASSET_COIN_AMT", dAutoMiningReqAmount * 1000 / 2);
							paramMap.put("ARG_WAS_SVR_NO", CamelHelper.getInstance().getServerNo());
							paramMap.put("ARG_PUBLIC_IP", CommonUtils.getClientIpAddr(request));
							paramMap.put("ARG_ORD_CHNL_CD", "WAS");
							
							Map<String, Object> resultMap = yahobitYepRpService.requestAutoMining(paramMap);
							
							int resCd = Double.valueOf(resultMap.get("V_RTN_CD").toString()).intValue();
							
							if(resCd == 0) {
								result.setRtnCd(0);
								result.setRtnMsg("Auto Mining에 성공하였습니다.");
							} else {
								result.setRtnCd(resCd);
								result.setRtnMsg(resultMap.get("V_RTN_MSG").toString());
							}
						} else {
							result.setRtnCd(-1007);
							result.setRtnMsg("[WAS서버] 최대 참여가능 수량을 초과하였습니다");
						}
					}
					
					/*
					paramMap = new HashMap<>();
					paramMap.put("ARG_EXCHANGE_ID", exchangeId);
					paramMap.put("ARG_USER_ID", user.getUserId());
					paramMap.put("ARG_ITEM_CODE", "YEPKRW");
					paramMap.put("ARG_BASIC_ASSET_COIN_AMT", dAutoMiningReqAmount * 1000 / 2);
					paramMap.put("ARG_WAS_SVR_NO", CamelHelper.getInstance().getServerNo());
					paramMap.put("ARG_PUBLIC_IP", CommonUtils.getClientIpAddr(request));
					paramMap.put("ARG_ORD_CHNL_CD", "WAS");
					
					Map<String, Object> resultMap = yahobitYepRpService.requestAutoMining(paramMap);
					
					int resCd = Double.valueOf(resultMap.get("V_RTN_CD").toString()).intValue();
					
					if(resCd == 0) {
						result.setRtnCd(0);
						result.setRtnMsg("Auto Mining에 성공하였습니다.");
					} else {
						result.setRtnCd(resCd);
						result.setRtnMsg(resultMap.get("V_RTN_MSG").toString());
					}
					*/
				}
			} else {
				result.setRtnCd(-5070);
				result.setRtnMsg("보안 등급이 레벨 2 이상일 경우 이용 가능합니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5055);
			result.setRtnMsg("Auto Mining에 실패하였습니다");
		}
		
		return result;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 Staking 정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepAirDropStatus
	 * @date : 2019. 6. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseDate
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepAirDropStatus", method = RequestMethod.POST)
	public Map<String, Object> selectYepAirDropStatus(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseDate", defaultValue = "", required = true) String strBaseDate
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_DATE", strBaseDate );
			Map<String, Object> airdropInfoMap = yahobitYepRpService.selectYepAirDropStatus(paramMap);
			
			// TOTAL_AIRDROP_STATUS_MAP							>> 상단 종합 AirDrop 현황 자료 select
			// PREV_DATE_AIRDROP_INFO_MAP						>> 어제의 거래수소료, Staking Reward, 기여보너스 자료 select
			// CUR_DATE_AIRDROP_INFO_MAP						>> 오늘의 거래수소료, Staking Reward, 기여보너스 자료 select
			// TODAY_MINING_STAKING_CONTRIBUTION_INFO_MAP		>> 나의 Mining/Staking 기여도 현황 조회
			// MONTHLY_AIRDROP_LIST								>> 일별 Airdrop 현황 리스트 조회
			// MY_AIRDROP_INFO_LIST								>> 나의 일별 Airdrop 내역 리스트 조회
			// MY_ETC_AIRDROP_INFO_LIST							>> 나의 일별 기타  Airdrop 내역 리스트 조회
			
			return airdropInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Freezing 종합 정보를 가져와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepFreezingStatus
	 * @date : 2019. 6. 24.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 24.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseDate
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepFreezingStatus", method = RequestMethod.POST)
	public Map<String, Object> selectYepFreezingStatus(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseDate", defaultValue = "", required = true) String strBaseDate
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_DATE", strBaseDate );
			Map<String, Object> freezingInfoMap = yahobitYepRpService.selectYepFreezingStatus(paramMap);
			
			// TOTAL_FREEZING_STATUS_MAP						>> 상단 종합 Freezing 현황 자료 select
			// PREV_DATE_FREEZING_INFO_MAP						>> 어제의 어제/오늘 Freezing 수량 및 누계  정보 자료 select
			// CUR_DATE_FREEZING_INFO_MAP						>> 오늘의 어제/오늘 Freezing 수량 및 누계  정보 자료 select
			// MONTHLY_FREEZING_LIST							>> 일별 Freezing 내역 리스트 조회
			
			return freezingInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 회원의 Staking 정보를 얻어와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectStakeInfo
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectStakeInfo", method = RequestMethod.POST)
	public Map<String, Object> selectStakeInfo(HttpServletRequest request, HttpSession session ) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> stakeInfoMap = yahobitYepRpService.selectStakeInfo(paramMap);
			
			return stakeInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 Freezing 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepFreezingDailyHistoryList
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth yyyyMM
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepFreezingDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepFreezingDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> stakeDailyListMap = yahobitYepRpService.selectYepFreezingDailyList(paramMap);
			
			return stakeDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 Mining 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMiningDailyHistoryList
	 * @date : 2019. 5. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth yyyyMM
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMiningDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepMiningDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> stakeDailyListMap = yahobitYepRpService.selectYepMiningDailyList(paramMap);
			
			return stakeDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 나의 Mining 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyMiningDailyHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth yyyyMM
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMyMiningDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepMyMiningDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> miningDailyListMap = yahobitYepRpService.selectYepMyMiningDailyList(paramMap);
			
			return miningDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Staking 요청을 진행한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestStaking
	 * @date : 2019. 7. 07.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 07.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strMarketName default 'KRW'
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/requestStaking", method = RequestMethod.POST)
	public ResultVO requestStaking(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "stake_amount", defaultValue = "0", required = true) double dStakingAmount
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("STAKING_AMT", dStakingAmount );
			ResultVO stakeRequestResult = yahobitYepRpService.processStakingRequest(paramMap);
			
			return stakeRequestResult;
			
		} catch(ErrorResultException ere) {
			ResultVO rstVo = new ResultVO();
			rstVo.setRtnCd(ere.getRtnCd());
			rstVo.setRtnMsg(ere.getRtnMsg());
			return rstVo;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : UnStaking 요청을 진행한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestUnStaking
	 * @date : 2019. 7. 07.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 07.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param unstake_mng_no default '[]'
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/requestUnStaking", method = RequestMethod.POST)
	public ResultVO requestUnStaking(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "unstake_info_list", defaultValue = "", required = true) String lstUnstakingInfo
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			List<Map> unStakingInfoList = new Gson().fromJson(lstUnstakingInfo, List.class);
			
			ResultVO unStakeRequestResult = new ResultVO();
			unStakeRequestResult.setRtnCd(0);
			unStakeRequestResult.setRtnMsg("Staking 해제요청 성공");
			for(Map reqMap: unStakingInfoList) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("STAKING_MGT_NO", Double.valueOf(reqMap.get("staking_mgt_no").toString()).longValue() );
				paramMap.put("REQ_QTY", Double.valueOf(reqMap.get("unstaking_req_qty").toString()).doubleValue() );
				ResultVO eaRst = yahobitYepRpService.processUnStakingRequest(paramMap);
				if(eaRst.getRtnCd() != 0) {
					unStakeRequestResult.setRtnCd(eaRst.getRtnCd());
					unStakeRequestResult.setRtnMsg(unStakeRequestResult.getRtnMsg() + String.format("Staking관리번호 : [%s] , 에러메시지 : %s\n", reqMap.get("staking_mgt_no").toString(), eaRst.getRtnMsg()));
				}
			}
			
			return unStakeRequestResult;
			
		} catch(ErrorResultException ere) {
			ResultVO rstVo = new ResultVO();
			rstVo.setRtnCd(ere.getRtnCd());
			rstVo.setRtnMsg(ere.getRtnMsg());
			return rstVo;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 Staking 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepStakingDailyHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth yyyyMM
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepStakingDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepStakingDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> stakingDailyListMap = yahobitYepRpService.selectYepStakingDailyList(paramMap);
			
			return stakingDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 나의 Staking 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyStakingDailyHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth yyyyMM
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMyStakingDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepMyStakingDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> stakingMyDailyListMap = yahobitYepRpService.selectYepMyStakingDailyList(paramMap);
			
			return stakingMyDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 나의 Staking 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyStakingDailyHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMyStakingAvailableInfo", method = RequestMethod.POST)
	public Map<String, Object> selectYepMyStakingAvailableInfo(HttpServletRequest request, HttpSession session ) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> stakingMyDailyListMap = yahobitYepRpService.selectYepMyStakingAvailableInfo(paramMap);
			
			return stakingMyDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 나의 Staking 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepUnstakingHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepUnstakingHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepUnstakingHistoryList(HttpServletRequest request, HttpSession session ) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> stakingMyDailyListMap = yahobitYepRpService.selectYepUnstakingHistoryList(paramMap);
			
			return stakingMyDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 해당월의 Airdrop summary 일별 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepAirdropDailyHistoryList
	 * @date : 2019. 7. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepAirdropDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepAirdropDailyHistoryList(HttpServletRequest request, HttpSession session 
				, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> airdropDailyListMap = yahobitYepRpService.selectYepAirdropDailyHistoryList(paramMap);
			
			return airdropDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 일별 Airdrop 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyAirdropDailyHistoryList
	 * @date : 2019. 7. 11.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 11.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMyAirdropDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepMyAirdropDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> airdropMyDailyListMap = yahobitYepRpService.selectYepAirdropMyDailyHistoryList(paramMap);
			
			return airdropMyDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 일별 기타 Airdrop 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyAirdropEtcDailyHistoryList
	 * @date : 2019. 7. 11.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 11.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectYepMyAirdropEtcDailyHistoryList", method = RequestMethod.POST)
	public Map<String, Object> selectYepMyAirdropEtcDailyHistoryList(HttpServletRequest request, HttpSession session 
			, @RequestParam(value = "baseMonth", defaultValue = "", required = true) String strBaseMonth
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			paramMap.put("BASE_MONTH", strBaseMonth );
			Map<String, Object> airdropMyEtcDailyListMap = yahobitYepRpService.selectYepAirdropMyEtcDailyHistoryList(paramMap);
			
			return airdropMyEtcDailyListMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 일별 기타 Airdrop 내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectYepMyAirdropEtcDailyHistoryList
	 * @date : 2019. 7. 11.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 11.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strBaseMonth
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/yeprp/selectMyKrwPossession", method = RequestMethod.POST)
	public Map<String, Object> selectMyKrwPossession(HttpServletRequest request, HttpSession session ) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("EXCHANGE_ID", strExchangeId );
			paramMap.put("USER_ID", strUserId );
			Map<String, Object> myKrwPossessionInfoMap = yahobitYepRpService.selectKrwPossessionInfo(paramMap);
			
			return myKrwPossessionInfoMap;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
}
