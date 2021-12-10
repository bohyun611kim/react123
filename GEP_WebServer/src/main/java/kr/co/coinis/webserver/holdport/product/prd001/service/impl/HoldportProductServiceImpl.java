/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd001.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.holdport.product.prd001.dao.HoldportProductDAO;
import kr.co.coinis.webserver.holdport.product.prd001.service.HoldportProductService;
import kr.co.coinis.webserver.holdport.product.prd001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.EventVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.NoticeVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.WithdrawLimitVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.prd001.service.impl
 *    |_ NoticeServiceImpl.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:35
 * @version :
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
@Service("holdportProductService")
public class HoldportProductServiceImpl implements HoldportProductService {

	@Resource(name="holdportProductDAO")
	private HoldportProductDAO holdportProductDAO;

	@Override
	public List<NoticeVO> selectNoticeList(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> selectEventList(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> selectEventListCount(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectPointConvertList(Map paramMap) throws Exception {
		return holdportProductDAO.selectPointConvertList(paramMap);
	}

	@Override
	public Map<String, Object> selectCoinBalance(Map paramMap) throws Exception {
		return holdportProductDAO.selectCoinBalance(paramMap);
	}

	@Override
	public HashMap<String, Object> selectCoinTradeAmout(HashMap paramMap) throws Exception {
		return holdportProductDAO.selectCoinTradeAmout(paramMap);
	}

	@Override
	public HashMap<String, Object> updatePointConvertRequest(HashMap<String, Object> paramMap) throws Exception{
		HashMap<String, Object> resultMap = holdportProductDAO.procPointConvertRequest(paramMap);
		return resultMap;
	}

	@Override
	public HashMap<String, Object> procCoinToPointConvert(HashMap<String, Object> paramMap) throws Exception{
		HashMap<String, Object> resultMap = holdportProductDAO.procCoinToPointConvert(paramMap);
		return resultMap;
	}

}
