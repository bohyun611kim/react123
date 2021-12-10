package kr.co.coinis.webserver.common.utils;

import java.util.regex.Pattern;

public class StringUtils {
	
	/**
	 * <pre>
	 * 1.  기능		:  문자열이 null 혹은 "" 인 경우를 검사 
	 * 2.  처리개요	: 				
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param str
	 * @return
	 * </pre>
	*/
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * 1.  기능		:  문자열 nvl 처리 
	 * 2.  처리개요	: 				
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param str
	 * @return
	 * </pre>
	*/
	public static String nvl(String str) {
		if(str == null || "".equals(str)) {
			return "";
		}else {
			return str;
		}
	}
	
	/**
	 * <pre>
	 * 1.  기능		:  모바일 번호 마스킹 처리 
	 * 2.  처리개요	: 				
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param mobile
	 * @return
	 * </pre>
	*/
	public static String mobileMasking(String mobile) {
		if(isEmpty(mobile)) {
			return "";
		} else if(mobile.length() == 11 ) {
			return mobile.substring(0, 3) + "-" + mobile.substring(3, 5) + "**-**" + mobile.substring(9) + "";
		} else {
			return "";
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 이메일 주소 유효 여부 확인
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : chekcEmailAddress
	 * @date : 2019. 5. 6.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 6.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param email
	 * @return
	 */
	public static boolean chekcEmailAddress(String email) {
		String regex = "[\\w\\-\\.]{2,}[@][\\w\\-]{2,}([.]([\\w\\-]{2,})){1,3}$";
		
		return email.matches(regex);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 숫자만 존재하는지 확인
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkNumberOnly
	 * @date : 2019. 5. 21.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param number
	 * @return
	 */
	public static boolean checkNumberOnly(String number) {
		String regex = "^[0-9]*$";

		return number.matches(regex);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 비밀번호 문자 검사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkPassword
	 * @date : 2019. 6. 25.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 25.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param pw
	 * @return
	 */
	public static boolean checkPassword(String pw) {
		String chkNumber = "([0-9])";
	    String smallLetter = "([a-z])";
	    String capitalLetter = "([A-Z])";
	    //String specialChar = "([?=.*[$@$!%*#?&]])";
	    String specialChar = "[`~\\[\\]_\\-+/!@#$%^&*,.?\\\":{}|]";
	    
	    if(Pattern.compile(chkNumber).matcher(pw).find() 
	    		&& Pattern.compile(smallLetter).matcher(pw).find() 
	    		&& Pattern.compile(capitalLetter).matcher(pw).find()
	    		&& Pattern.compile(specialChar).matcher(pw).find()
	    		&& pw.length() >= 8 && pw.length() <= 25) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
}
