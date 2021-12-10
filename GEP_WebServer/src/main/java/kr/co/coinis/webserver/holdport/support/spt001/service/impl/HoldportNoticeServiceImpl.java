/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.support.spt001.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.holdport.support.spt001.dao.HoldportNoticeDAO;
import kr.co.coinis.webserver.holdport.support.spt001.service.HoldportNoticeService;
import kr.co.coinis.webserver.holdport.support.spt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.EventVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.holdport.support.spt001.vo.WithdrawLimitVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.service.impl 
 *    |_ NoticeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:35
 * @version : 
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
@Service("holdportNoticeService")
public class HoldportNoticeServiceImpl implements HoldportNoticeService {

	@Resource(name="holdportNoticeDAO")
	private HoldportNoticeDAO holdportNoticeDAO;

	@Override
	public List<NoticeVO> selectNoticeList(Map paramMap) throws Exception {
		return holdportNoticeDAO.retrieveNoticeList(paramMap);
	}

	@Override
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectNoticeListCount(paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectItemCodeMasterByBasicCoinNo(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectItemCodeMasterByBasicCoinNo(paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectCoinCodeMasterInitData(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectCoinCodeMasterInitData(paramMap);
	}

	@Override
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoList(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectExchangeMarketInfoList(paramMap);
	}

	@Override
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoList(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectCoinMgtRefInfoList(paramMap);
	}

	@Override
	public List<EventVO> selectEventList(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectEventList(paramMap);
	}

	@Override
	public List<EventVO> selectEventListCount(Map paramMap) throws Exception {
		return holdportNoticeDAO.selectEventListCount(paramMap);
	}
	
	@Override
	public List<WithdrawLimitVO> selectCoinWithdrawLimit(String exchangeId) throws Exception {
		return holdportNoticeDAO.selectCoinWithdrawLimit(exchangeId);
	}
}
