package kr.co.coinis.webserver.coinis.exchange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.exchange.dao.ExchangeDAO;
import kr.co.coinis.webserver.coinis.exchange.service.ExchangeService;
import kr.co.coinis.webserver.coinis.exchange.vo.BalanceInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.DefaultItemCodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.ItemCodeVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.exchange.service.impl 
 *    |_ ExchangeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:05:28
 * @version : 
 * @author : Seongcheol
 */
@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService {

	@Resource(name="exchangeDAO")
	private ExchangeDAO exchangeDAO;
	
	@Override
	public DefaultItemCodeVO selectDefaultItemCode(String exchangeId) {
		return exchangeDAO.selectDefaultItemCode(exchangeId);
	}
	
	@Override
	public DefaultItemCodeVO checkItemCode(Map<String, Object> param) {
		return exchangeDAO.checkItemCode(param);
	}
	
	@Override
	public Map<String, Object> selectMarketList(Map<String, String> param) {
		Map<String, Object> result = new HashMap<>(); 
		
		result.put("mkt", exchangeDAO.selectMarketList(param.get("exchangeId")));
		
		if(param.get("userId") != null) {
			result.put("balance", exchangeDAO.selectBalanceList(param));
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> selectItemInfo(Map<String, Object> param) {
		
		Map<String, Object> result = new HashMap<>();
		
		// itemCode 정보 조회
		ItemCodeVO itemCodeVO = exchangeDAO.selectItmeCodeInfo(param);
		
		if(param.get("userId") != null) {
			//balance 정보 조회
			param.put("coinNo", itemCodeVO.getBasicCoinNo());
			itemCodeVO.setFromQty(exchangeDAO.selectBalance(param));
			
			param.put("coinNo", itemCodeVO.getCoinNo());
			//itemCodeVO.setToQty(exchangeDAO.selectBalance(param));
			
			BalanceInfoVO balanceInfoVO = exchangeDAO.selectBalanceInfo(param);
			itemCodeVO.setToQty(balanceInfoVO.getPossibleQty());
			
			result.put("order", exchangeDAO.selectOrderList(param));				// 주문 내역 조회
			result.put("contract", exchangeDAO.selectContractList(param));			// 체결 내역 조회
			result.put("nonContract", exchangeDAO.selectNonContractList(param));	// 미체결 재역 조회
			result.put("balance", balanceInfoVO);									// 보유 잔고 정보
		}
		
		result.put("info", itemCodeVO);
		result.put("sell", exchangeDAO.selectSellHoga(param));		// 매도 호가 조회 
		result.put("buy", exchangeDAO.selectBuyHoga(param));		// 매수 호가 조회
		result.put("real", exchangeDAO.selectRealContractList(param));		// 매수 호가 조회
		
		return result;
	}
	
	@Override
	public SendOrderCancelVO selectCancelInfo(Map<String, Object> param) {
		return exchangeDAO.selectCancelInfo(param);
	}
	
	@Override
	public List<CodeVO> selectCodeInfo(Map<String, Object> param) {
		return exchangeDAO.selectCodeInfo(param);
	}
	
	@Override
	public CoinInfoVO selectCoinInfo(String coinNo) {
		return exchangeDAO.selectCoinInfo(coinNo);
	}
}
