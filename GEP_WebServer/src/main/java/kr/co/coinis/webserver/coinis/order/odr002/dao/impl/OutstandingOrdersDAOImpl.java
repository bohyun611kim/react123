/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.order.odr002.dao.OutstandingOrdersDAO;
import kr.co.coinis.webserver.coinis.order.odr002.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.order.odr002.vo.ReqNonContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.dao.impl 
 *    |_ OutstandingOrdersDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:25:34
 * @version : 
 * @author : Seongcheol
 */
@Repository("outstandingOrdersDAO")
public class OutstandingOrdersDAOImpl extends AbstractDefaultMapper implements OutstandingOrdersDAO {

	@Override
	public String getNamespace() {
		return OutstandingOrdersDAO.NAMESPACE;
	}
	
	@Override
	public int selectNonContractSize(ReqNonContractVO reqNonContractVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectNonContractInfo", reqNonContractVO);
	}
	
	@Override
	public List<NonContractVO> selectNonContractList(ReqNonContractVO reqNonContractVO) {
		return getSqlSession().selectList(this.getNamespace() + ".selectNonContractList", reqNonContractVO);
	}
	
	@Override
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectOrderCancelInfo", param);
	}
}
