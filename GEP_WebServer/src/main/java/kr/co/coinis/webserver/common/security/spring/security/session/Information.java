package kr.co.coinis.webserver.common.security.spring.security.session;

import java.util.Date;
/**
 * 
* <pre>
* 1.  기능	: Spring Security 사용자 세션 정보  
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
public class Information {
	private final String exchangeId;
	private final String userId;
	private final String sessionId;
	private final Date lastRequest;
	private final Object principal;

	public Information(String exchangeId, String userId, String sessionId, Date lastRequest, Object principal) {
		this.exchangeId = exchangeId;
		this.userId = userId;
		this.sessionId = sessionId;
		this.lastRequest = lastRequest;
		this.principal = principal;
	}
	
	public String getExchangeId() {
		return exchangeId;
	}

	public String getUserId() {
		return userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public Date getLastRequest() {
		return lastRequest;
	}

	public Object getPrincipal() {
		return principal;
	}
}
