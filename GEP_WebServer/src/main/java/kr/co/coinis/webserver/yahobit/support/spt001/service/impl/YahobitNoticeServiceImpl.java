/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.support.spt001.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.yahobit.support.spt001.dao.YahobitNoticeDAO;
import kr.co.coinis.webserver.yahobit.support.spt001.service.YahobitNoticeService;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.EventVO;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.yahobit.support.spt001.vo.WithdrawLimitVO;

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
@Service("yahobitNoticeService")
public class YahobitNoticeServiceImpl implements YahobitNoticeService {

	@Resource(name="yahobitNoticeDAO")
	private YahobitNoticeDAO yahobitNoticeDAO;

	@Override
	public List<NoticeVO> selectNoticeList(Map paramMap) throws Exception {
		return yahobitNoticeDAO.retrieveNoticeList(paramMap);
	}

	@Override
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectNoticeListCount(paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectItemCodeMasterByBasicCoinNo(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectItemCodeMasterByBasicCoinNo(paramMap);
	}

	@Override
	public List<ItemCodeMastVO> selectCoinCodeMasterInitData(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectCoinCodeMasterInitData(paramMap);
	}

	@Override
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoList(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectExchangeMarketInfoList(paramMap);
	}

	@Override
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoList(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectCoinMgtRefInfoList(paramMap);
	}

	@Override
	public List<EventVO> selectEventList(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectEventList(paramMap);
	}

	@Override
	public List<EventVO> selectEventListCount(Map paramMap) throws Exception {
		return yahobitNoticeDAO.selectEventListCount(paramMap);
	}
	
	@Override
	public List<WithdrawLimitVO> selectCoinWithdrawLimit(String exchangeId) throws Exception {
		return yahobitNoticeDAO.selectCoinWithdrawLimit(exchangeId);
	}
}
