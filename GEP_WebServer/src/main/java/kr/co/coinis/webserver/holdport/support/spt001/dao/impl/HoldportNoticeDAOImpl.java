/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.support.spt001.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.support.spt001.dao.HoldportNoticeDAO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.EventVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.WithdrawLimitVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.dao.impl 
 *    |_ NoticeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:33:03
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
@Repository("holdportNoticeDAO")
public class HoldportNoticeDAOImpl extends AbstractDefaultMapper implements HoldportNoticeDAO {

	private static final Logger LOG = LoggerFactory.getLogger(HoldportNoticeDAOImpl.class);
	
	@Override
	public String getNamespace() {
		return HoldportNoticeDAO.NAMESPACE;
	}

	@Override
	public List<NoticeVO> retrieveNoticeList(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> retrieveNoticeList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveNoticeList", paramMap);
	}

	@Override
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectNoticeListCount ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveNoticeListCount", paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectItemCodeMasterByBasicCoinNo(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectItemCodeMasterByBasicCoinNo ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveItemCodeMasterByBasicCoinNo", paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectCoinCodeMasterInitData(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectCoinCodeMasterInitData ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveCoinCodeMasterCount", paramMap);
	}

	@Override
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoList(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectExchangeMarketInfoList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveExchangeMarketInfoExchangeId", paramMap);
	}

	@Override
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoList(Map paramMap) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectCoinMgtRefInfoList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveCoinMgtRefInfoList", paramMap);
	}

	@Override
	public List<EventVO> selectEventList(Map paramMap) throws Exception {
		LOG.debug("[NoticeDAO] >> selectEventList ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveEventList", paramMap);
	}

	@Override
	public List<EventVO> selectEventListCount(Map paramMap) throws Exception {
		LOG.debug("[NoticeDAO] >> selectEventListCount ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".retrieveEventListCount", paramMap);
	}
	
	@Override
	public List<WithdrawLimitVO> selectCoinWithdrawLimit(String exchangeId) throws SQLException {
		LOG.debug("[NoticeDAO] >> selectCoinWithdrawLimit ");
		return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCoinWithdrawLimit", exchangeId);
	}
}
