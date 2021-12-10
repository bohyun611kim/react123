package kr.co.coinis.webserver.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	public static String getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		String result = "";
		
		for(int i=0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			
			if(name.equals(cookie.getName())) {
				result = cookie.getValue();
				break;
			}
		}
		
		return result;
	}
}
