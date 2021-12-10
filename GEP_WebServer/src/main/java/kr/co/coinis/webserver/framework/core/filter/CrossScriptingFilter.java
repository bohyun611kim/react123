package kr.co.coinis.webserver.framework.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
* <pre>
* 1.  기능	: cross scripting [특수문자 치환] filter  
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
public class CrossScriptingFilter implements Filter{
	
	 public FilterConfig filterConfig;
	 
	 public void init(FilterConfig filterConfig) throws ServletException { 
	        this.filterConfig = filterConfig;    
	 }     

	 public void destroy() {
	         this.filterConfig = null;     
	 }     

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
	}	

}
