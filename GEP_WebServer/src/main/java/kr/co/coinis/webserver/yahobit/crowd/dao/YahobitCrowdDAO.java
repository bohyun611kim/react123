/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.BalanceVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.BonusInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleOnVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoByNoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqJoinCrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqKycAuthVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.dao 
 *    |_ YahobitCrowdDAO.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오전 9:14:38
 * @version : 
 * @author : Seongcheol
 */
public interface YahobitCrowdDAO {

	public static final String NAMESPACE = YahobitCrowdDAO.class.getName();
	
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
	public List<CrowdSaleVO> selectCrowdSaleList(String exchangeId) throws Exception;

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
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CrowdSaleInfoVO selectCrowdSaleInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 진행중인 클라우드 세일 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCrowdSaleOnList
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
	public List<CrowdSaleOnVO> selectCrowdSaleOnList(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 보너스 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBonusList
	 * @date : 2019. 5. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<BonusInfoVO> selectBonusList(Map<String, Object> param) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 참여 가능 수량 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalance
	 * @date : 2019. 5. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<BalanceVO> selectBalance(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 클라우드 세일 가격 저어보 조회
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
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<PriceInfoVO> selectPriceInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자의 kyc 인증 여부
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectKycYn
	 * @date : 2019. 5. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int selectKycYn(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 인증 수단 식별 코드 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectAuthMeansCd
	 * @date : 2019. 6. 10.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 10.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int selectAuthMeansCd(Map<String, Object> param) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 인증 수단 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUserMeansCd
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public String selectUserAuthInfo(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : otp key 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOtpKey
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public String selectOtpKey(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : KYC 인증자료 정보 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertCrowdSaleKycInfo
	 * @date : 2019. 6. 10.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 10.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param ReqKycAuthVO
	 * @return
	 */
	public ReqKycAuthVO procInsertCrowdSaleKycInfo(ReqKycAuthVO reqKycAuthVO);
	
	/**
	 * <pre>
	 * 1. 개요 : KYC 인증자료 사진 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertCrowdSaleKycPhoto
	 * @date : 2019. 6. 10.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 10.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param ReqKycAuthVO
	 * @return
	 */
	public Map<String, Object> procInsertCrowdSaleKycPhoto(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : SMS 인증번호 요청코드 생성 및 저장 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertAuthCode
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procInsertAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : sms 인증번호 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procCheckAuthCode
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 참여 수량 계산을 위한 가격 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPriceInfoByNo
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqJoinCrowdSaleVO
	 * @return
	 */
	public PriceInfoByNoVO selectPriceInfoByNo(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO);
	
	/**
	 * <pre>
	 * 1. 개요 : crowd sale 참여
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertCrowdSaleOrderHist
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqJoinCrowdSaleVO
	 * @return
	 */
	public ReqJoinCrowdSaleVO procInsertCrowdSaleOrderHist(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO); 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 승인상태처리메모 조회
	 * </pre>
	 * @Method Name : selectApprovalProcMemo
	 * @date : 2019. 12. 16.
	 * @author : J
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 16.		J				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public String selectApprovalProcMemo(Map<String, Object> param);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : Hard Cap 수량이 넘지않게 체크
	 * </pre>
	 * @Method Name : posibleCrowdSale
	 * @date : 2019. 12. 19.
	 * @author : J
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 19.		J				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param checkParam
	 * @return
	 */
	public double posibleCrowdSale(Map<String, Object> checkParam);
	
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
	 * 1. 개요 : 크라우드세일 보너스수량 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : reqBonus
	 * @date : 2019. 12. 23.
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
	public Map<String, Object> reqBonus(Map paramMap) throws Exception;
}
