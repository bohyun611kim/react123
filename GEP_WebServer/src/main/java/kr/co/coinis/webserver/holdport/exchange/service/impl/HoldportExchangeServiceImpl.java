/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.exchange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.holdport.exchange.dao.HoldportExchangeDAO;
import kr.co.coinis.webserver.holdport.exchange.service.HoldportExchangeService;
import kr.co.coinis.webserver.holdport.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.holdport.exchange.vo.ContractVO;
import kr.co.coinis.webserver.holdport.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.holdport.exchange.vo.ItemCodeInfoVO;
import kr.co.coinis.webserver.holdport.exchange.vo.ItemCodeListVO;
import kr.co.coinis.webserver.holdport.exchange.vo.RealContractVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.exchange.service.impl 
 *    |_ HoldportExchangeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:16:15
 * @version : 
 * @author : Seongcheol
 */
@Service("holdportExchangeService")
public class HoldportExchangeServiceImpl implements HoldportExchangeService {

	@Resource(name="holdportExchangeDAO")
	private HoldportExchangeDAO holdportExchangeDAO;
	
	@Override
	public Map<String, Object> selectItemCodeInfo(Map<String, String> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		String exchangeId = param.get("exchangeId");
		
		// itemCode 기존 정보 조회
		ItemCodeInfoVO info = holdportExchangeDAO.selectDefaultItemCode(param);
		
		// 화면 상단 select에 들어갈 coinNo 값에 따른 종목 리스트 조회
		Map<String, Object> reqItemCodes = new HashMap<>();
		reqItemCodes.put("exchangeId", exchangeId);
		reqItemCodes.put("coinNo", info.getCoinNo());
		
		List<ItemCodeListVO> list = holdportExchangeDAO.selectItemCodeOfCoin(reqItemCodes);
		
		// hoga 조회
		Map<String, String> reqParam = new HashMap<>();
		reqParam.put("exchangeId", exchangeId);
		reqParam.put("mktGrpId", info.getMktGrpId());
		reqParam.put("mktId", info.getMktId());
		reqParam.put("itemCode", info.getItemCode());
		
		// CoinNo, BasicCoinNo 코인 잔고 조회
		if(param.get("userId") != null) {
			Map<String, Object> reqBalance = new HashMap<>();
			reqBalance.put("exchangeId", exchangeId);
			reqBalance.put("userId", param.get("userId"));
			reqBalance.put("coinNo", info.getCoinNo());
			
			// 거래대상 코인 잔고 조회
			info.setToQty(holdportExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqBalance.put("coinNo", info.getBasicCoinNo());
			
			// 거래기준 코인 잔고 조회
			info.setFromQty(holdportExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqParam.put("userId", param.get("userId"));
			
			result.put("contract", holdportExchangeDAO.selectContract(reqParam));
			result.put("nonContract", holdportExchangeDAO.selectNonContract(reqParam));
		}
		
		// 조회한 데이터 담기
		result.put("info", info);
		result.put("list", list);
		result.put("sell", holdportExchangeDAO.selectSellHogaList(reqParam));
		result.put("buy", holdportExchangeDAO.selectBuyHogaList(reqParam));
		result.put("real", holdportExchangeDAO.selectBuyHogaList(reqParam));
		result.put("realContract", holdportExchangeDAO.selectRealContract(reqParam));
		result.put("daily", holdportExchangeDAO.selectDailySise(reqParam));
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectMktItemCodeInfo(Map<String, String> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		String exchangeId = param.get("exchangeId");
		
		// itemCode 기존 정보 조회
		ItemCodeInfoVO info = holdportExchangeDAO.selectDefaultItemCode(param);
		
		// 화면 상단 select에 들어갈 coinNo 값에 따른 종목 리스트 조회
		Map<String, Object> reqItemCodes = new HashMap<>();
		reqItemCodes.put("exchangeId", exchangeId);
		reqItemCodes.put("coinNo", info.getCoinNo());
		
		List<ItemCodeListVO> list = holdportExchangeDAO.selectItemCodeOfCoin(reqItemCodes);
		
		// hoga 조회
		Map<String, String> reqParam = new HashMap<>();
		reqParam.put("exchangeId", exchangeId);
		reqParam.put("mktGrpId", info.getMktGrpId());
		reqParam.put("mktId", info.getMktId());
		reqParam.put("itemCode", info.getItemCode());
		
		// CoinNo, BasicCoinNo 코인 잔고 조회
		if(param.get("userId") != null) {
			Map<String, Object> reqBalance = new HashMap<>();
			reqBalance.put("exchangeId", exchangeId);
			reqBalance.put("userId", param.get("userId"));
			reqBalance.put("coinNo", info.getCoinNo());
			
			// 거래대상 코인 잔고 조회
			info.setToQty(holdportExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqBalance.put("coinNo", info.getBasicCoinNo());
			
			// 거래기준 코인 잔고 조회
			info.setFromQty(holdportExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqParam.put("userId", param.get("userId"));
			
			result.put("contract", holdportExchangeDAO.selectContract(reqParam));
			result.put("nonContract", holdportExchangeDAO.selectNonContract(reqParam));
		}
		
		// 조회한 데이터 담기
		result.put("info", info);
		result.put("list", list);
		result.put("sell", holdportExchangeDAO.selectSellHogaList(reqParam));
		result.put("buy", holdportExchangeDAO.selectBuyHogaList(reqParam));
		result.put("real", holdportExchangeDAO.selectBuyHogaList(reqParam));
		result.put("realContract", holdportExchangeDAO.selectRealContract(reqParam));
		result.put("daily", holdportExchangeDAO.selectDailySise(reqParam));
		
		return result;
	}
	
	@Override
	public CoinInfoVO selectCoinInfo(int coinNo) {
		return holdportExchangeDAO.selectCoinInfo(coinNo);
	}
	
	@Override
	public Map<String, Object> getMarket(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("market", holdportExchangeDAO.selectMarket(param));
		//result.put("free", holdportExchangeDAO.selectFreeMarket(param));
		
		if(param.get("userId") != null) {
			result.put("balance", holdportExchangeDAO.selectBalance(param));
			//result.put("freeBalance", holdportExchangeDAO.selectFreeBalance(param));
		}
	
		return result;
	}
	
	@Override
	public List<RealContractVO> selectRealContract(Map<String, String> param) {
		return holdportExchangeDAO.selectRealContract(param);
	}
	
	@Override
	public List<DailySiseVO> selectDailySise(Map<String, String> param) {
		return holdportExchangeDAO.selectDailySise(param);
	}
	
	@Override
	public List<ContractVO> selectContract(Map<String, String> param) {
		return holdportExchangeDAO.selectContract(param);
	}
	
	@Override
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param) {
		return holdportExchangeDAO.selectOrderCancelInfo(param);
	}

	@Override
	public List<SendOrderCancelVO> selectOrderCancelAllInfoOfPrice(Map<String, Object> param) {
		return holdportExchangeDAO.selectOrderCancelAllInfoOfPrice(param);
	}

	@Override
	public List<SendOrderCancelVO> selectOrderCancelAllInfo(Map<String, Object> param) {
		return holdportExchangeDAO.selectOrderCancelAllInfo(param);
	}
}
