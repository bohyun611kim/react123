/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.crowd.dao.YahobitCrowdDAO;
import kr.co.coinis.webserver.yahobit.crowd.service.YahobitCrowdService;
import kr.co.coinis.webserver.yahobit.crowd.vo.BalanceVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.BonusInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoByNoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqJoinCrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqKycAuthVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.service.impl 
 *    |_ YahobitCrowdServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오전 9:14:59
 * @version : 
 * @author : Seongcheol
 */
@Service("yahobitCrowdService")
public class YahobitCrowdServiceImpl implements YahobitCrowdService {
	
	private static final Logger LOG = LoggerFactory.getLogger(YahobitCrowdService.class);

	@Resource(name="yahobitCrowdDAO")
	private YahobitCrowdDAO yahobitCrowdDAO;
	
	@Override
	public Map<String, Object> selectCrowdSaleList(String exchangeId) throws Exception {
		
		List<CrowdSaleVO> coming = new ArrayList<>();
		List<CrowdSaleVO> going = new ArrayList<>();
		List<CrowdSaleVO> ended = new ArrayList<>();
		
		List<CrowdSaleVO> list = null;
		
		try {
			list = yahobitCrowdDAO.selectCrowdSaleList(exchangeId);
			
			long now = Long.parseLong(DateUtils.getDateTimeNow());
			
			for(int i=0; i<list.size(); i++) {
				if(now < list.get(i).getStartDt()) {
					// 진행 예정 
					coming.add(list.get(i));
				} else if(now > list.get(i).getEndDt()) {
					// 종료된
					ended.add(list.get(i));
				} else if(now > list.get(i).getStartDt() && now < list.get(i).getEndDt()) {
					// 진행중
					going.add(list.get(i));
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("coming", coming);
		result.put("going", going);
		result.put("ended", ended);
		
		return result;
	}
		
	@Override
	public Map<String, Object> selectCrowdSaleInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		CrowdSaleInfoVO info = yahobitCrowdDAO.selectCrowdSaleInfo(param);
		if(param.get("userId") != null) {
			info.setKycYn(yahobitCrowdDAO.selectKycYn(param));
			info.setAuthMeansCd(yahobitCrowdDAO.selectAuthMeansCd(param));
			String approvalProcMemo = yahobitCrowdDAO.selectApprovalProcMemo(param);
			if(approvalProcMemo != null && approvalProcMemo.equalsIgnoreCase("<br>") == false)
				info.setApprovalProcMemo(approvalProcMemo.split("<br>")[approvalProcMemo.split("<br>").length - 1]);
			else
				info.setApprovalProcMemo("");
		}
		
		String type = info.getCrwdTypeCd() == 1 ? 
						"ICO":info.getCrwdTypeCd() == 2 ? 
						"IEO":info.getCrwdTypeCd() == 3 ? "BS":"";
		
		result.put("info", info);
		result.put("type", type);
		result.put("list", yahobitCrowdDAO.selectCrowdSaleOnList(param));
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectPriceInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<>(); 
		
		// 클라우드 세일 보너스
		List<BonusInfoVO> bonus = yahobitCrowdDAO.selectBonusList(param);
		
		// 클라우드 세일 참여 가능수량
		List<BalanceVO> balance = yahobitCrowdDAO.selectBalance(param);
		
		// 클라우드 세일 가격 정보
		List<PriceInfoVO> price = yahobitCrowdDAO.selectPriceInfo(param);

		result.put("bonus", bonus);
		result.put("balance", balance);
		result.put("price", price);
		
		return result;
	}
	
	@Override
	public ResultVO insertKycAuthInfo(ReqKycAuthVO reqKycAuthVO) throws Exception {
		ResultVO result = new ResultVO();
		
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", reqKycAuthVO.getExchangeId());
		param.put("userId", reqKycAuthVO.getUserId());
		// 인증자료 제출 여부 확인
		int cnt = yahobitCrowdDAO.selectKycYn(param);
		
		// 인증 자료 제출
		if(cnt > 0) {
			result.setRtnCd(-5062);
			result.setRtnMsg("이미 인증자료를 제출 하였습니다");
		} else {
			reqKycAuthVO = yahobitCrowdDAO.procInsertCrowdSaleKycInfo(reqKycAuthVO);
			
			if(reqKycAuthVO.getRtnCd() != 0) {
				result.setRtnCd(reqKycAuthVO.getRtnCd());
				result.setRtnMsg(reqKycAuthVO.getRtnMsg());
			} else {
				param.put("fileNm", reqKycAuthVO.getFileName1());
				param = yahobitCrowdDAO.procInsertCrowdSaleKycPhoto(param);
				
				if((int) param.get("rtnCd") != 0) {
					throw new Exception("KYC 신분증파일 저장 처리 실패");
				} else {
					param.put("fileNm", reqKycAuthVO.getFileName2());
					param = yahobitCrowdDAO.procInsertCrowdSaleKycPhoto(param);
				}
			}
		}
		
		// 첨부 파일 등록
		return result;
	}
	
	@Override
	public AuthCodeVO requestSmsCode(AuthCodeVO authCodeVO) throws Exception {
		String mobile = yahobitCrowdDAO.selectUserAuthInfo(authCodeVO);
		
		if(mobile != null) {
			authCodeVO.setAuthMeansKeyVal(mobile);
			authCodeVO = yahobitCrowdDAO.procInsertAuthCode(authCodeVO);
		} else {
			authCodeVO.setRtnCd(-5015);
			authCodeVO.setRtnMsg("SMS 인증을 사용하고 있지 않습니다.");
		}
		
		return authCodeVO;
	}
	
	@Override
	public ResultVO joinCrowdSale(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO) throws Exception {
		
		ResultVO result = new ResultVO();
		
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", reqJoinCrowdSaleVO.getExchangeId());
		param.put("userId", reqJoinCrowdSaleVO.getUserId());
		
		// 인증수단 조회
		int authMeansCd = yahobitCrowdDAO.selectAuthMeansCd(param);
		
		AuthCodeVO authCodeVO = new AuthCodeVO();
		authCodeVO.setExchangeId(reqJoinCrowdSaleVO.getExchangeId());
		authCodeVO.setUserId(reqJoinCrowdSaleVO.getUserId());
		
		// 인증 번호 검사
		switch(authMeansCd) {
		case 1:	//otp
			String otpKey = yahobitCrowdDAO.selectOtpKey(authCodeVO);
			
			if(!GoogleOTP.checkCode(reqJoinCrowdSaleVO.getAuthCode(), otpKey)) {
				result.setRtnCd(-5016);
				result.setRtnMsg("인증코드가 일치하지 않습니다");
			}
			break;
		case 2:	//sms
			authCodeVO.setAuthPurposeCd(5);
			authCodeVO.setAuthMeansCd(2);
			authCodeVO.setAuthMeansKeyVal( yahobitCrowdDAO.selectUserAuthInfo(authCodeVO) );
			authCodeVO.setAuthCd(reqJoinCrowdSaleVO.getAuthCode());
			
			authCodeVO = yahobitCrowdDAO.procCheckAuthCode(authCodeVO);
			
			if(authCodeVO.getRtnCd() != 0) {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
			break;
		default:
			result.setRtnCd(-1);
			result.setRtnMsg("");
			break;
		}
		
		if(result.getRtnCd() == 0) {
			// 수량 게산
			PriceInfoByNoVO priceInfoByNoVO = yahobitCrowdDAO.selectPriceInfoByNo(reqJoinCrowdSaleVO);
			
			double slQty = (reqJoinCrowdSaleVO.getQty() / priceInfoByNoVO.getPayBascicQty()) * priceInfoByNoVO.getSlBasicQty();
			
			System.out.println( slQty );
			System.out.println( ((slQty * priceInfoByNoVO.getBouns()) / 100) );
			System.out.println( slQty + ((slQty * priceInfoByNoVO.getBouns()) / 100) );
			reqJoinCrowdSaleVO.setSlQty( slQty );
			
			// Hard Cap 을 넘어선경우 return
			/*
			 * Map<String, Object> checkParam = new HashMap<>(); checkParam.put("no",
			 * reqJoinCrowdSaleVO.getNo()); checkParam.put("slQty", slQty);
			 * 
			 * double posibleCrowdSale = yahobitCrowdDAO.posibleCrowdSale(checkParam);
			 * 
			 * if(posibleCrowdSale < 0) { result.setRtnCd(-1);
			 * result.setRtnMsg("Hard Cap 수량을 넘길 수 없습니다. 참여가능한 수량으로 주문해주시기 바랍니다."); return
			 * result; }
			 */
			// 참여하기
			reqJoinCrowdSaleVO = yahobitCrowdDAO.procInsertCrowdSaleOrderHist(reqJoinCrowdSaleVO);
			
			if(reqJoinCrowdSaleVO.getRtnCd() != 0) {
				result.setRtnCd(reqJoinCrowdSaleVO.getRtnCd());
				result.setRtnMsg(reqJoinCrowdSaleVO.getRtnMsg());
				throw new Exception("" + reqJoinCrowdSaleVO.getRtnCd());
			}
		}
		
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> reqQtyMax(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return yahobitCrowdDAO.reqQtyMax(paramMap);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> reqBonus(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return yahobitCrowdDAO.reqBonus(paramMap);
	}
}
