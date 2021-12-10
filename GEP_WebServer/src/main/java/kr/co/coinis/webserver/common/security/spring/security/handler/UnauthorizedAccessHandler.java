package kr.co.coinis.webserver.common.security.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.coinis.webserver.common.exception.SessionExpiredException;
import kr.co.coinis.webserver.common.security.spring.http.RequestCheckParam;
import kr.co.coinis.webserver.common.security.spring.http.SuccessBody;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.PropertiesUtils;
/**
 * 
* <pre>
* 1.  기능	: 인증(Consumer)되지 않은 사용자가 허가되지 않은 페이지에 접근할때 요청되는 헨들러.  
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
public class UnauthorizedAccessHandler implements AuthenticationEntryPoint {

	private final String loginFormUrl;
	private boolean redirect = true;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	public UnauthorizedAccessHandler(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		if(RequestCheckParam.isAjax(request)) {
			HttpSession session = request.getSession();
			
			
			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			SuccessBody success = new SuccessBody();
			String data = "";
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			// userDetails 이 널일 경우 세션 만료 판정
			if(userDetails == null) {
				response.setStatus(new SessionExpiredException().getStatus());
				
				data = PropertiesUtils.getProperties("exception.session.expire.msg", request.getLocale());
			} else {
				success.setMessage(exception.getMessage());
				success.setError(true);
				
				ObjectMapper objectMapper = new ObjectMapper();
				data = objectMapper.writeValueAsString(success);
			}
			
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} else {
			if (redirect) {
				response.sendRedirect(request.getContextPath() + loginFormUrl);
			} else {
				request.getRequestDispatcher(request.getContextPath() + loginFormUrl).forward(request, response);
			}
		}
	}
}
