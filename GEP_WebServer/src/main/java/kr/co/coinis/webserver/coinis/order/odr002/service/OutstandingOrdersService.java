/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.order.odr002.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.order.odr002.vo.ReqNonContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.service 
 *    |_ OutstandingOrdersService.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 3:25:16
 * @version : 
 * @author : Seongcheol
 */
public interface OutstandingOrdersService {

	/**
	 * 미체결 내역 조회(리스트 사이즈 포함)
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
	public HashMap<String, Object> selectNonContractAll(ReqNonContractVO reqNonContractVO);
	
	/**
	 * 미체결 내역 조회
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContractLList
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
	
	public SendOrderCancelVO selectOrderCancelInfo(Map<String, Object> param);
}
