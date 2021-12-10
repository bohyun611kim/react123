package kr.co.coinis.webserver.common.security.spring.security.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
/**
 * 
* <pre>
* 1.  기능	: Spring Security Session Manager 설정  
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
public class ConcurrentSessionControlAuthenticationStrategySupport extends ConcurrentSessionControlAuthenticationStrategy {

	public ConcurrentSessionControlAuthenticationStrategySupport(SessionRegistry sessionRegistry, int maximumSessions, boolean exceptionIfMaximumExceeded) {
		super(sessionRegistry);
		super.setMaximumSessions(maximumSessions);
		super.setExceptionIfMaximumExceeded(exceptionIfMaximumExceeded);
	}

	/**
	 * 중복로그인 비활성화인 경우 동시 세션 설정을 수정한다.
	 *
	 * @param duplicationLoginDisable the duplication login disable
	 */
	public void setDuplicationLoginDisable(boolean duplicationLoginDisable) {
		if (duplicationLoginDisable) {
			super.setMaximumSessions(1);
			super.setExceptionIfMaximumExceeded(false);
		}
	}

	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		super.onAuthentication(authentication, request, response);
	}
}
