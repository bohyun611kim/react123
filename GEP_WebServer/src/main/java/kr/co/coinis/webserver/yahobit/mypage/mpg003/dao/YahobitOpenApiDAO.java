/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg003.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg001.vo.ReqUserInfoVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.CreateApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.ModApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.OpenApiVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg003.vo.UserAuthInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg003.dao 
 *    |_ YahobitOpenApiDAO.java
 * 
 * </pre>
 * @date : 2019. 7. 09. 오전 10:22:55
 * @version : 
 * @author : kangn
 */
public interface YahobitOpenApiDAO {

	public static final String NAMESPACE = YahobitOpenApiDAO.class.getName();

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
	public List<OpenApiVO> selectApiList(ReqUserInfoVO reqUserInfoVO);

	/**
	 * <pre>
	 * 1. 개요 : api 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectApiInfo
	 * @date : 2019. 7. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqUserInfoVO
	 * @return
	 */
	public OpenApiVO selectApiInfo(CreateApiVO createApiVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 유저 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserAuthInfo
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
	 */
	public UserAuthInfoVO selectUserAuthInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : sms 인증 번호 발급
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertAuthCode
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
	 */
	public AuthCodeVO procInsertAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 모바일 인증번호 확인
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procCheckAuthCode
	 * @date : 2019. 7. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : api 정보 추가
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertApiInfo
	 * @date : 2019. 7. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param createApiVO
	 * @return
	 */
	public int insertApiInfo(CreateApiVO createApiVO);
	
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
	public int updateApiInfo(ModApiVO modApiVO);

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
	public int deleteApiInfo(ModApiVO modApiVO);
}
