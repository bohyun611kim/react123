/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.yahobit.exchange.dao.YahobitExchangeDAO;
import kr.co.coinis.webserver.yahobit.exchange.service.YahobitExchangeService;
import kr.co.coinis.webserver.yahobit.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeListVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.RealContractVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.service.impl 
 *    |_ YahobitExchangeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:16:15
 * @version : 
 * @author : Seongcheol
 */
@Service("yahobitExchangeService")
public class YahobitExchangeServiceImpl implements YahobitExchangeService {

	@Resource(name="yahobitExchangeDAO")
	private YahobitExchangeDAO yahobitExchangeDAO;
	
	@Override
	public Map<String, Object> selectItemCodeInfo(Map<String, String> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		String exchangeId = param.get("exchangeId");
		
		// itemCode 기존 정보 조회
		ItemCodeInfoVO info = yahobitExchangeDAO.selectDefaultItemCode(param);
		
		// 화면 상단 select에 들어갈 coinNo 값에 따른 종목 리스트 조회
		Map<String, Object> reqItemCodes = new HashMap<>();
		reqItemCodes.put("exchangeId", exchangeId);
		reqItemCodes.put("coinNo", info.getCoinNo());
		
		List<ItemCodeListVO> list = yahobitExchangeDAO.selectItemCodeOfCoin(reqItemCodes);
		
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
			info.setToQty(yahobitExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqBalance.put("coinNo", info.getBasicCoinNo());
			
			// 거래기준 코인 잔고 조회
			info.setFromQty(yahobitExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqParam.put("userId", param.get("userId"));
			
			result.put("contract", yahobitExchangeDAO.selectContract(reqParam));
			result.put("nonContract", yahobitExchangeDAO.selectNonContract(reqParam));
		}
		
		// 조회한 데이터 담기
		result.put("info", info);
		result.put("list", list);
		result.put("sell", yahobitExchangeDAO.selectSellHogaList(reqParam));
		result.put("buy", yahobitExchangeDAO.selectBuyHogaList(reqParam));
		result.put("real", yahobitExchangeDAO.selectBuyHogaList(reqParam));
		result.put("realContract", yahobitExchangeDAO.selectRealContract(reqParam));
		result.put("daily", yahobitExchangeDAO.selectDailySise(reqParam));
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectMktItemCodeInfo(Map<String, String> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		String exchangeId = param.get("exchangeId");
		
		// itemCode 기존 정보 조회
		ItemCodeInfoVO info = yahobitExchangeDAO.selectDefaultItemCode(param);
		
		// 화면 상단 select에 들어갈 coinNo 값에 따른 종목 리스트 조회
		Map<String, Object> reqItemCodes = new HashMap<>();
		reqItemCodes.put("exchangeId", exchangeId);
		reqItemCodes.put("coinNo", info.getCoinNo());
		
		List<ItemCodeListVO> list = yahobitExchangeDAO.selectItemCodeOfCoin(reqItemCodes);
		
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
			info.setToQty(yahobitExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqBalance.put("coinNo", info.getBasicCoinNo());
			
			// 거래기준 코인 잔고 조회
			info.setFromQty(yahobitExchangeDAO.selectBalanceByCoinNo(reqBalance));
			
			reqParam.put("userId", param.get("userId"));
			
			result.put("contract", yahobitExchangeDAO.selectContract(reqParam));
			result.put("nonContract", yahobitExchangeDAO.selectNonContract(reqParam));
		}
		
		// 조회한 데이터 담기
		result.put("info", info);
		result.put("list", list);
		result.put("sell", yahobitExchangeDAO.selectSellHogaList(reqParam));
		result.put("buy", yahobitExchangeDAO.selectBuyHogaList(reqParam));
		result.put("real", yahobitExchangeDAO.selectBuyHogaList(reqParam));
		result.put("realContract", yahobitExchangeDAO.selectRealContract(reqParam));
		result.put("daily", yahobitExchangeDAO.selectDailySise(reqParam));
		
		return result;
	}
	
	@Override
	public CoinInfoVO selectCoinInfo(int coinNo) {
		return yahobitExchangeDAO.selectCoinInfo(coinNo);
	}
	
	@Override
	public Map<String, Object> getMarket(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("market", yahobitExchangeDAO.selectMarket(param));
		result.put("free", yahobitExchangeDAO.selectFreeMarket(param));
		
		if(param.get("userId") != null) {
			result.put("balance", yahobitExchangeDAO.selectBalance(param));
			//result.put("freeBalance", yahobitExchangeDAO.selectFreeBalance(param));
		}
	
		return result;
	}
	
	@Override
	public List<RealContractVO> selectRealContract(Map<String, String> param) {
		return yahobitExchangeDAO.selectRealContract(param);
	}
	
	@Override
	public List<DailySiseVO> selectDailySise(Map<String, String> param) {
		return yahobitExchangeDAO.selectDailySise(param);
	}
	
	@Override
	public List<ContractVO> selectContract(Map<String, String> param) {
		return yahobitExchangeDAO.selectContract(param);
	}
	
	@Override
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param) {
		return yahobitExchangeDAO.selectOrderCancelInfo(param);
	}

	@Override
	public List<SendOrderCancelVO> selectOrderCancelAllInfo(Map<String, Object> param) {
		return yahobitExchangeDAO.selectOrderCancelAllInfo(param);
	}
}
