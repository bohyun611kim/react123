package kr.co.coinis.webserver.common.security.spring.security.handler;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.coinis.webserver.common.security.spring.http.RequestCheckParam;
import kr.co.coinis.webserver.common.security.spring.http.SuccessBody;
/**
 * 
* <pre>
* 1.  기능	: 인증(Consumer)된 사용자가 허가되지 않은 페이지에 접근할때 호출되는 헨들러.  
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
public class AccessFailureHandler implements AccessDeniedHandler {

	private final String loginFormUrl;
	private final String errorPage;
	private String redirectUrlParameter = "redirect_url";
	private boolean redirect = true;

	public AccessFailureHandler(String loginFormUrl, String errorPage) {
		this.loginFormUrl = loginFormUrl;
		this.errorPage = errorPage;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	/**
	 * 이동할 url 값을 담을 약속된 파라메터
	 *
	 * @param redirectUrlParameter the redirect url parameter
	 */
	public void setRedirectUrlParameter(String redirectUrlParameter) {
		this.redirectUrlParameter = redirectUrlParameter;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		if(RequestCheckParam.isAjax(request)) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			SuccessBody success = new SuccessBody(exception.getMessage(), true);

			ObjectMapper objectMapper = new ObjectMapper();
			String data = objectMapper.writeValueAsString(success);

			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} else {
			if (redirect) {
				response.sendRedirect(request.getContextPath() + errorPage);
			} else {
				String url = RequestCheckParam.getPathQueryString(request);
				request.setAttribute(redirectUrlParameter, url);
				request.setAttribute("loginFormUrl", loginFormUrl);
				request.getRequestDispatcher(request.getContextPath() + errorPage).forward(request, response);
			}
		}
	}
}