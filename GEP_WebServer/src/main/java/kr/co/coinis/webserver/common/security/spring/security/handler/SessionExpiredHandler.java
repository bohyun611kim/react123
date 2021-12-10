package kr.co.coinis.webserver.common.security.spring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
/**
 * 
* <pre>
* 1.  기능     : 중복 로그인 세션 expired 될 때 호출되는 Handler  
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
@Component
public class SessionExpiredHandler implements SessionInformationExpiredStrategy {
	private static final Logger logger = LoggerFactory.getLogger(SessionExpiredHandler.class);
	private String expiredUrl;
	
	public SessionExpiredHandler(String expiredUrl) {
		this.expiredUrl = expiredUrl;
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
		HttpServletRequest request = sessionInformationExpiredEvent.getRequest();
		HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
		logger.debug("session expired url :"+ expiredUrl);
		
		response.sendRedirect(request.getContextPath() + expiredUrl);
	}
} 