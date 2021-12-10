/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.exchange.dao.YahobitExchangeDAO;
import kr.co.coinis.webserver.yahobit.exchange.vo.BalanceVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.DailySiseVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.HogaVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.ItemCodeListVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.MarketInfoVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.NonContractVO;
import kr.co.coinis.webserver.yahobit.exchange.vo.RealContractVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.dao.impl 
 *    |_ YahobitExchangeDaoImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오전 9:15:48
 * @version : 
 * @author : Seongcheol
 */
@Repository("yahobitExchangeDAO")
public class YahobitExchangeDAOImpl extends AbstractDefaultMapper implements YahobitExchangeDAO {

	@Override
	public String getNamespace() {
		return YahobitExchangeDAO.NAMESPACE;
	}
	
	@Override
	public ItemCodeInfoVO selectDefaultItemCode(Map<String, String> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectDefaultItemCode", param);
	}
	
	@Override
	public ItemCodeInfoVO selectItemCodeInfo(Map<String, String> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectMktItemCode", param);
	}
	
	@Override
	public List<ItemCodeListVO> selectItemCodeOfCoin(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectItemCodeOfCoin", param);
	}
	
	@Override
	public CoinInfoVO selectCoinInfo(int coinNo) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectCoinInfo", coinNo);
	}
	
	@Override
	public List<MarketInfoVO> selectMarket(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectMarket", param);
	}
	
	@Override
	public List<MarketInfoVO> selectFreeMarket(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectFreeMarket", param);
	}
	
	@Override
	public List<BalanceVO> selectBalance(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectBalance", param);
	}
	
	@Override
	public List<BalanceVO> selectFreeBalance(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectFreeBalance", param);
	}
	
	@Override
	public double selectBalanceByCoinNo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectBalanceByCoinNo", param);
	}
	
	@Override
	public List<HogaVO> selectSellHogaList(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectSellHogaList", param);
	}
	
	@Override
	public List<HogaVO> selectBuyHogaList(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectBuyHogaList", param);
	}

	@Override
	public List<RealContractVO> selectRealContract(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectRealContract", param);
	}
	
	@Override
	public List<DailySiseVO> selectDailySise(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectDailySise", param);
	}
	
	@Override
	public List<ContractVO> selectContract(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectContract", param);
	}
	
	@Override
	public List<NonContractVO> selectNonContract(Map<String, String> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectNonContract", param);
	}
	
	@Override
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectOrderCancelInfo", param);
	}
	
	@Override
	public List<SendOrderCancelVO> selectOrderCancelAllInfo(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectOrderCancelAllInfo", param);
	}
}
