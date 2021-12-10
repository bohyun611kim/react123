package kr.co.coinis.webserver.coinis.exchange.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.exchange.dao.ExchangeDAO;
import kr.co.coinis.webserver.coinis.exchange.vo.BalanceInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.BalanceVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.ContractVO;
import kr.co.coinis.webserver.coinis.exchange.vo.DefaultItemCodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.HogaVO;
import kr.co.coinis.webserver.coinis.exchange.vo.ItemCodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.MarketVO;
import kr.co.coinis.webserver.coinis.exchange.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.exchange.vo.OrderVO;
import kr.co.coinis.webserver.coinis.exchange.vo.RealContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.exchange.dao.impl 
 *    |_ ExchangeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:05:13
 * @version : 
 * @author : Seongcheol
 */
@Repository("exchangeDAO")
public class ExchangeDAOImpl extends AbstractDefaultMapper implements ExchangeDAO {

	@Override
	public String getNamespace() {
		return ExchangeDAO.NAMESPACE;
	}
	
	@Override
	public DefaultItemCodeVO selectDefaultItemCode(String exchangeId) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectDefaultItemCode", exchangeId);
	}
	
	@Override
	public DefaultItemCodeVO checkItemCode(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".checkItemCode", param);
	}
	
	@Override
	public List<MarketVO> selectMarketList(String exchangId) {
		return getSqlSession().selectList(this.getNamespace() + ".selectMarketList", exchangId);
	}
	
	@Override
	public ItemCodeVO selectItmeCodeInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectItmeCodeInfo", param);
	}
	
	@Override
	public List<HogaVO> selectSellHoga(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectSellHoga", param);
	}
	
	@Override
	public List<HogaVO> selectBuyHoga(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectBuyHoga", param);
	}
	
	@Override
	public List<BalanceVO> selectBalanceList(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectBalanceList", param);
	}

	@Override
	public double selectBalance(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectBalance", param);
	}
	
	@Override
	public BalanceInfoVO selectBalanceInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectBalanceInfo", param);
	}
	
	@Override
	public List<OrderVO> selectOrderList(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectOrderList", param);
	}
	
	@Override
	public List<ContractVO> selectContractList(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectContractList", param);
	}
	
	@Override
	public List<NonContractVO> selectNonContractList(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectNonContractList", param);
	}
	
	@Override
	public List<RealContractVO> selectRealContractList(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectRealContractList", param);
	}
	
	@Override
	public SendOrderCancelVO selectCancelInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectCancelInfo", param);
	}
	
	@Override
	public List<CodeVO> selectCodeInfo(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectCodeInfo", param);
	}
	
	@Override
	public CoinInfoVO selectCoinInfo(String coinNo) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectCoinInfo", coinNo);
	}
}
