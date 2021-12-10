/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.vo.CodeVO;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendMailVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.service
 *    |_ CommService.java
 *
 * </pre>
 * @date : 2019. 4. 23. 오후 3:46:23
 * @version :
 * @author : Seongcheol
 */
public interface CommService {

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
	 * 1. 개요 : 메일 전송
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendMail
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param sendMailVO
	 * @return
	 */
	public ResultVO sendMail(SendMailVO sendMailVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 비밀번호 초기화 이메일 전송
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendInitPwMail
	 * @date : 2019. 5. 5.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param sendMailVO
	 * @return
	 * @throws Exception
	 */
	public ResultVO sendInitPwMail(SendMailVO sendMailVO) throws Exception;

	/**
	 *
	 * <pre>
	 * 1. 개요 : SMS 발송
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendSms
	 * @date : 2019. 4. 30.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 30.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param sendMailVO
	 * @return
	 * @throws Exception
	 */
	public ResultVO sendSms(SendSmsVO sendSmsVo) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 신규 주문 전송
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendOrder
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
	 * @throws Exception
	 */
	public ResultVO sendOrder(SendOrderVO sendOrderVO) throws Exception;

	public HashMap<String, Object> sendOrderNew(SendOrderVO sendOrderVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 주문 취소 전송
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendOrderCancel
	 * @date : 2019. 5. 3.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param sendOrderVO
	 * @return
	 * @throws Exception
	 */
	public ResultVO sendOrderCancel(SendOrderCancelVO sendOrderVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 주문 취소 전체(매도, 매수)
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : sendOrderCancelAll
	 * @date : 2019. 5. 3.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 3.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param sendOrderCancelVO
	 * @return
	 * @throws Exception
	 */
	public ResultVO sendOrderCancelAll(List<SendOrderCancelVO> sendOrderCancelVO, String pulbicIp, int autoMiningYn) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 사용자 이력 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : insertUserHistory
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
	 * 1. 개요 : 워드 북 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectWordBook
	 * @date : 2019. 5. 22.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 22.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @return
	 */
	public List<Map<String, Object>> selectWordBook();

	/**
	 *
	 * <pre>
	 * 1. 개요 : 회원에게 메일을 발송한다.
	 * 2. 처리내용 : 메일 내용을 DB에 등록된 TEMPLATE 에 치환하여 HTML 로 발송한다.
	 * </pre>
	 * @Method Name : sendInfoMail
	 * @date : 2019. 5. 22.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 22.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param exchangeId	거래소 ID
	 * @param userId		회원 ID (email address)
	 * @param subject		메일 제목
	 * @param msg			메일 내용
	 * @return
	 * @throws Exception
	 */
	public ResultVO sendInfoMail(String exchangeId, String userId, String subject, String msg, boolean waitresult) throws Exception;

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
}
