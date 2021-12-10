/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.web.camel.router;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils 
 *    |_ MessageUtils.java
 * 
 * </pre>
 * @date : 2019. 4. 11. 오전 9:28:32
 * @version : 
 * @author : Seongcheol
 */
public class MessageUtils {
	
	// 한국 날짜 가져오기 (format : yyyyMMdd)
	/*private static DateTimeFormatter fmtDt 
		= DateTimeFormatter.ofPattern( "yyyyMMdd" )
							.withLocale( Locale.KOREA )
							.withZone( ZoneId.of("Asia/Seoul") );*/
	
	// 큐 push 시간 (format : yyyyMMddHHmmssSSSSSS)
	/*private static DateTimeFormatter fmtMicro
		= DateTimeFormatter.ofPattern( "yyyyMMddHHmmssSSSSSS" )
							.withLocale( Locale.KOREA )
							.withZone( ZoneId.systemDefault() );*/
	
	protected static String ifTransactionDt = "";
	protected static long ifTransactionSeq = 1;

	/**
	 * <pre>
	 * 1. 개요 : 서버간 인터페이스를 위한 message header 양식 생성 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMsgHeader
	 * @date : 2019. 4. 12.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 12.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param Type
	 * @return
	 */
	public static Map<String, Object> getMsgHeader(int svrNo, int rcvType, int type, String sndDt) {
		Map<String, Object> header = new HashMap<>();
		
		header.put("svrNo", svrNo);
		header.put("svrTypeCd", 1000);
		header.put("rcvSvrTypeCd", rcvType);
		header.put("msgCode", type);
		header.put("sndDt", sndDt);
		header.put("ifTransactionId", getIfTransactionId(svrNo));
		
		return header;
	}
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 서버간 인터페이스를 위한 message header 양식 생성 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getMsgHeaderAuto
	 * @date : 2019. 11. 1.
	 * @author : J
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 11. 1.		J				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param svrNo
	 * @param rcvType
	 * @param type
	 * @param sndDt
	 * @return
	 */
	public static Map<String, Object> getMsgHeaderAuto(int svrNo, int rcvType, int type, String sndDt) {
		Map<String, Object> header = new HashMap<>();
		
		header.put("svrNo", svrNo);
		header.put("svrTypeCd", 1000);
		header.put("rcvSvrTypeCd", rcvType);
		header.put("msgCode", type);
		header.put("sndDt", sndDt);
		header.put("ifTransactionId", getIfTransactionIdAuto());
		
		return header;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : IfTransactionId 생성
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getIfTransactionId
	 * @date : 2019. 4. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 15.		Seongcheol				최초 작성 
	 *	2019. 4. 28.		kangnaru				CamelHelper에서 얻어오도록 수정함
	 *	-----------------------------------------------------------------------
	 * 
	 * @param svrNo
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static String getIfTransactionId(int svrNo) {
//		if(fmtDt.format(Instant.now()).equals(ifTransactionDt)) {
//			ifTransactionSeq++;
//		} else {
//			ifTransactionDt = fmtDt.format(Instant.now());
//			ifTransactionSeq = 1;
//		}
//		
//		return "WAS" + svrNo + "_" + ifTransactionDt + "_" + String.format("%010d", ifTransactionSeq);
		return CamelHelper.getInstance().getWasServerIfTxId();
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : IfTransactionId 생성
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getIfTransactionIdAuto
	 * @date : 2019. 11. 1.
	 * @author : J
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 11. 1.		J				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param svrNo
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static String getIfTransactionIdAuto() {
		return CamelHelper.getInstance().getWasServerIfTxIdAuto();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력받은 문자열의 아스키 코드 값을 모두 더하여 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getStrAscSum
	 * @date : 2019. 4. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */
	public static int getStrAscSum(String str) {
		int result = 0;
		for(int i=0; i<str.length(); i++) {
			result += str.charAt(i);
	    }
		
		return result;
	}
	
}
