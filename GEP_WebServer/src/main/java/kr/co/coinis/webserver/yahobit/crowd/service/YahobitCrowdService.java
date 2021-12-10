/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.service;

import java.util.Map;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqJoinCrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqKycAuthVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.service 
 *    |_ YahobitCrowdService.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오전 9:15:09
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitCrowdService {

	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCrowdSaleList
	 * @date : 2019. 5. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCrowdSaleList(String exchangeId) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCrowdSaleInfo
	 * @date : 2019. 5. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public Map<String, Object> selectCrowdSaleInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 참여에 필요한 가격 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPriceInfo
	 * @date : 2019. 5. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return param
	 * @throws Exception
	 */
	public Map<String, Object> selectPriceInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : kyc 인증 자료 제출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertKycAuthInfo
	 * @date : 2019. 5. 28.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ResultVO insertKycAuthInfo(ReqKycAuthVO reqKycAuthVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : sms 인증번호 요청
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestSmsCode
	 * @date : 2019. 5. 28.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public AuthCodeVO requestSmsCode(AuthCodeVO authCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 참여
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : joinCrowdSale
	 * @date : 2019. 5. 28.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 28.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ResultVO joinCrowdSale(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 크라우드세일 참여수량 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : reqQtyMax
	 * @date : 2019. 12. 19.
	 * @author : 강호경
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 19.		강호경					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> reqQtyMax(Map paramMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 크라우스세일 보너스수량 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : reqBonus
	 * @date : 2019. 12. 19.
	 * @author : 강호경
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 23.		강호경					최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> reqBonus(Map paramMap) throws Exception;
}
