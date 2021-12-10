/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.utils;

import java.util.Random;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils 
 *    |_ AuthCode.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 4:22:57
 * @version : 
 * @author : Seongcheol
 */
public class AuthCode {

	/**
	 * 인증목적식별코드 : 회원가입
	 */
	public static int PURPOSE_REGIST = 1;
	
	/**
	 * 인증목적식별코드 : 휴대폰 인증
	 */
	public static int PURPOSE_MOBILE = 2;
	
	/**
	 * 인증목적식별코드 : 출금 인증
	 */
	public static int PURPOSE_WITHDRAW = 3;
	
	/**
	 * 인증목적식별코드 : 비밀번호 변경
	 */
	public static int PURPOSE_PW_CHANGE = 4;
	
	/**
	 * 인증목적식별코드 : 회원탈퇴
	 */
	public static int PURPOSE_DROP = 5;

	/**
	 * 인증수단식별코드 : 미사용
	 */
	public static int MEANS_NONE 	= 0;
	/**
	 * 인증수단식별코드 : OTP
	 */
	public static int MEANS_OTP 	= 1;
	/**
	 * 인증수단식별코드 : SMS
	 */
	public static int MEANS_SMS 	= 2;
	/**
	 * 인증수단식별코드 : Email
	 */
	public static int MEANS_EMAIL	= 3;
	
	private static Random random = new Random();

	/**
	 * Random 클래스를 이용하여 입력 받은 입자값의 길이를 가진 난수 형태의 문자열 생성
	 * ex) 인자 값이 6인 경우 12345의 경우 012345를 문자열 형태로 반환한다
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAuthCode
	 * @date : 2019. 3. 27.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 27.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param length
	 * @return
	 */
	public static String getAuthCode(int length) {
		int limit = (int) Math.pow(10, length);
		return String.format("%06d", random.nextInt(limit));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 15 자리 임시 비밀번호 생성
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getTempPw
	 * @date : 2019. 5. 5.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 5.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public static String getTempPw() {
		Random rnd = new Random();

		StringBuffer buf = new StringBuffer();

		for(int i=0; i<15; i++){
		    if(rnd.nextBoolean()) {
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    } else {
		        buf.append((rnd.nextInt(10)));
		    }
		}

		return buf.toString();
	}
}
