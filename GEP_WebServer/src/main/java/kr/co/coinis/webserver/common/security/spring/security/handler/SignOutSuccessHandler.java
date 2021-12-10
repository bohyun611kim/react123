package kr.co.coinis.webserver.common.security.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.coinis.webserver.common.security.spring.http.RequestCheckParam;
import kr.co.coinis.webserver.common.security.spring.http.SuccessBody;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
/**
 * 
* <pre>
* 1.  기능	:  로그아웃 성공 후 호출되는 헨들러  
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
public class SignOutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if (RequestCheckParam.isAjax(request)) {
			String targetUrl = determineTargetUrl(request, response);

			logger.debug("targetUrl: " + targetUrl);
			logger.debug("targetUrlParameter name: " + getTargetUrlParameter());
			logger.debug("targetUrlParameter value: " + request.getParameter(getTargetUrlParameter()));

			// Redis에 저장된 session 정보 삭제
			redisSessionRepository.delete(request.getRequestedSessionId());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			SuccessBody success = new SuccessBody();

			response.setStatus(HttpServletResponse.SC_OK);

			success.setError(false);
			success.setData(targetUrl);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(success);

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
			
		} else {
			redisSessionRepository.delete(request.getSession().getId());
			super.handle(request, response, authentication);
		}

	}
}