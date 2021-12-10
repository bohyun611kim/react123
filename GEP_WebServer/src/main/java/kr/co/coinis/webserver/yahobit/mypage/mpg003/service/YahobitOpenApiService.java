/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg003.service;

import java.util.List;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.CreateApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.ModApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.OpenApiVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg003.service 
 *    |_ YahobitOpenApiService.java
 * 
 * </pre>
 * @date : 2019. 7. 9. 오후 2:22:59
 * @version : 
 * @author : kangn
 */
public interface YahobitOpenApiService {

	/**
	 * <pre>
	 * 1. 개요 : api 리스트 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectApiList
	 * @date : 2019. 7. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqUserInfoVO
	 * @return
	 * @throws Exception
	 */
	public List<OpenApiVO> selectApiList(ReqUserInfoVO reqUserInfoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 인증번호 요청
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : requestSmsCode
	 * @date : 2019. 7. 11.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 11.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 * @throws Exception
	 */
	public AuthCodeVO requestSmsCode(AuthCodeVO authCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : api 생성
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : createApi
	 * @date : 2019. 7. 11.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 11.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public OpenApiVO createApi(CreateApiVO createApiVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : api 상태 변경
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateApiInfo
	 * @date : 2019. 7. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param modApiVO
	 * @return
	 */
	public int updateApiInfo(ModApiVO modApiVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : api 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteApiInfo
	 * @date : 2019. 7. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param modApiVO
	 * @return
	 */
	public int deleteApiInfo(ModApiVO modApiVO) throws Exception;
}
