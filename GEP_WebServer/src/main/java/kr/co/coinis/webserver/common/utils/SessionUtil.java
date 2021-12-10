package kr.co.coinis.webserver.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
* <pre>
* 1.  기능	:
*  - Spring에서 제공하는 RequestContextHolder 를 이용하여
*  - request 객체를 service까지 전달하지 않고 사용할 수 있게 해줌  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 3. 29.
* ====================================
* 5.  수정사항
* 5.1 요구사항 ID	:
* - 수정자/수정일 	: 
* - 수정사유/내역 	:
* ====================================
* <p/>
* @author		: 김경주
* @since		: J2EE 1.8
* </pre>
 */
public class SessionUtil {
	
	/**
	* attribute 값을 가져 오기 위한 method
	* 
	* @param String  attribute key name 
	* @return Object attribute obj
	*/
	public static Object getAttribute(String name) throws Exception {
		return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	* attribute 설정 method
	* 
	* @param String  attribute key name 
	* @param Object  attribute obj
	* @return void
	*/
	public static void setAttribute(String name, Object object) throws Exception {
		RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	* 설정한 attribute 삭제 
	* 
	* @param String  attribute key name 
	* @return void
	*/
	public static void removeAttribute(String name) throws Exception {
		RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	* session id 
	* 
	* @param void
	* @return String SessionId 값
	*/
	public static String getSessionId() {
		return CookieUtils.getCookieByName(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest(), "SESSION");
		//return RequestContextHolder.getRequestAttributes().getSessionId();
	}
	
	public static String getJSessionId() {
		return CookieUtils.getCookieByName(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest(), "JSESSIONID"); 
	}
}




