/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.CodeVO;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ServerInfoVO;
import kr.co.coinis.webserver.common.vo.TemplateVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.dao 
 *    |_ CommDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오전 10:20:15
 * @version : 
 * @author : Seongcheol
 */
public interface CommDAO {

	public static final String NAMESPACE = CommDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : 서버 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectServerInfo
	 * @date : 2019. 4. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param serverNo
	 * @return
	 */
	public ServerInfoVO selectServerInfo(int serverNo);
	
	/**
	 * <pre>
	 * 1. 개요 : 서버 환경 변수 값 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectServerEnvInfo
	 * @date : 2019. 4. 26.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param serverNo
	 * @return
	 */
	public List<Map<String, String>> selectServerEnvInfo(int serverNo);

	/**
	 * <pre>
	 * 1. 개요 : 마지막 인터페이스 아이디 값 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectIfTransactionId
	 * @date : 2019. 4. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param serverNo
	 * @return
	 */
	public String selectIfTransactionId(int serverNo);
	
	/**
	 * <pre>
	 * 1. 개요 : 현재시각 가져오기(마이크로 second)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSndDt
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public String selectSndDt();
	
	/**
	 * <pre>
	 * 1. 개요 : 이메일 인증 메일 전송을 위한 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectRegisterTemplate
	 * @date : 2019. 4. 25.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqTemplateVO
	 * @return
	 */
	public TemplateVO selectRegisterTemplate(Map<String, Object> param);
	
	public Map<String, Object> selectTemplateMsgContent(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 인증 코드 발급 procedure 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procAuthCode
	 * @date : 2019. 4. 26.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public AuthCodeVO procAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 기존에 발송된 이메일 인증 코드 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteEmailAuthCode
	 * @date : 2019. 7. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param authCodeVO
	 * @return
	 */
	public int deleteEmailAuthCode(AuthCodeVO authCodeVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 유효 주문 여부 확인
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procCheckOrder
	 * @date : 2019. 4. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param sendOrderVO
	 * @return
	 */
	public Map<String, Object> procCheckOrder(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 주문 대상 queue 명칭 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOrderQueueNm
	 * @date : 2019. 5. 2.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, String> selectOrderQueueNm(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 기초통화에 해당하는 마켓 그룹 ID 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDefaultMktGrpId
	 * @date : 2019. 5. 2.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 2.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public String selectDefaultMktGrpId(String exchangeId);
	
	/**
	 * <pre>
	 * 1. 개요 : 실시간 체결내역 push 연결을 위한 수신 큐 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMktGrpList
	 * @date : 2019. 5. 5.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public List<String> selectMktGrpList(String exchangeId);
	
	/**
	 * <pre>
	 * 1. 개요 : 비밀번호 변경 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procPwChange
	 * @date : 2019. 5. 5.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> procPwChange(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 이력 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procInsertUserRequestHist
	 * @date : 2019. 5. 13.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 13.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqInsHistoryVO
	 * @return
	 */
	public InsertHistoryVO procInsertUserRequestHist(InsertHistoryVO insertHistoryVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 워드북 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectWordBook
	 * @date : 2019. 5. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public List<Map<String, Object>> selectWordBook();
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : TB_CODE_INFO 테이블에서 CODE_ID에 해당하는 CODE_VAL_NM, CODE_NUM_VAL 을 가져와 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfo
	 * @date : 2019. 5. 29.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 29.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param paramMap (CODE_ID, LANG_CD)
	 * @return
	 */
	public List<Map<String, Object>> selectCodeInfo(Map paramMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 코드 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfo
	 * @date : 2019. 7. 8.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 8.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<CodeVO> selectCodeInfoList(Map<String, Object> param);

	/**
	 * <pre>
	 * 1. 개요 : 코드 마스터 정보 조회(캐싱용도)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectItemCodeMaster
	 * @date : 2019. 8. 14.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 14.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 *
	 */
	public void selectItemCodeMaster();
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 실패 카운트 초기화
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : procSetFailCnt
	 * @date : 2019. 8. 14.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 14.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public FailCntVO procSetFailCnt(FailCntVO failCntVO);
}
