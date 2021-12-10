/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.order.odr002.dao.OutstandingOrdersDAO;
import kr.co.coinis.webserver.coinis.order.odr002.service.OutstandingOrdersService;
import kr.co.coinis.webserver.coinis.order.odr002.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.order.odr002.vo.ReqNonContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.service.impl 
 *    |_ OutstandingOrdersServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:25:22
 * @version : 
 * @author : Seongcheol
 */
@Service("outstandingOrdersService")
public class OutstandingOrdersServiceImpl implements OutstandingOrdersService {

	@Resource(name="outstandingOrdersDAO")
	private OutstandingOrdersDAO outstandingOrdersDAO;
	
	@Override
	public HashMap<String, Object> selectNonContractAll(ReqNonContractVO reqNonContractVO) {
		HashMap<String, Object> result = new HashMap<>();
		
		result.put("size", outstandingOrdersDAO.selectNonContractSize(reqNonContractVO));
		result.put("data", outstandingOrdersDAO.selectNonContractList(reqNonContractVO));
		
		return result;
	}
	
	@Override
	public List<NonContractVO> selectNonContractList(ReqNonContractVO reqNonContractVO) {
		return outstandingOrdersDAO.selectNonContractList(reqNonContractVO);
	}
	
	@Override
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param) {
		return outstandingOrdersDAO.selectOrderCancelInfo(param);
	}
}
