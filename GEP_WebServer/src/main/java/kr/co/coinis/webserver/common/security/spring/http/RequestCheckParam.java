package kr.co.coinis.webserver.common.security.spring.http;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
/**
 * 
* <pre>
* 1.  기능	: HttpRequest 요청 파라메타 체크  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 3. 5.
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
public class RequestCheckParam {
	private RequestCheckParam() {}

	public static String getPathQueryString(HttpServletRequest request) {
		String servlet_path = request.getServletPath();
		String query_string = request.getQueryString();
		query_string = ( StringUtils.isEmpty(query_string) ) ? "" : "?" + query_string;
		String servlet_url = servlet_path + query_string;

		return servlet_url;
	}

	public static boolean isAjax(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		String ajax = request.getHeader("X-Requested-With");

		return ( StringUtils.indexOf(accept, "json") > -1 && StringUtils.isNotEmpty(ajax));
	}
}
