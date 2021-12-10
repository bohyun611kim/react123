/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.order.odr002.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.order.odr002.vo.ReqNonContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.dao 
 *    |_ OutstandingOrdersDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:25:28
 * @version : 
 * @author : Seongcheol
 */
public interface OutstandingOrdersDAO {

	public static final String NAMESPACE = OutstandingOrdersDAO.class.getName();
	
	/**
	 * 미체결 내역 사이즈 조회
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContractSize
	 * @date : 2019. 4. 9.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 9.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqNonContractVO
	 * @return
	 */
	public int selectNonContractSize(ReqNonContractVO reqNonContractVO);
	
	/**
	 * 미체결 내역 조회
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContractList
	 * @date : 2019. 4. 9.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 9.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqNonContractVO
	 * @return
	 */
	public List<NonContractVO> selectNonContractList(ReqNonContractVO reqNonContractVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 주문 취소를 위한 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOrderCancelInfo
	 * @date : 2019. 5. 3.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param);
}
