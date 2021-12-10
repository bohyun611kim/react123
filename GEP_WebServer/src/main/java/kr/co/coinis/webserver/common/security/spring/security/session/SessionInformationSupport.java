package kr.co.coinis.webserver.common.security.spring.security.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
/**
 * 
* <pre>
* 1.  기능	: 사용자 세션정보 관리(Spring Security)  
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
public class SessionInformationSupport {
	private static final Logger logger = LoggerFactory.getLogger(SessionInformationSupport.class);
	private final SessionRegistry sessionRegistry;

	public SessionInformationSupport(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public List<Information> getSessionInformations() {
		logger.debug("//////////////////session register //////////////////////////////");
		List<SessionInformation> sessionInformations = new ArrayList<>();

		for(Object principal : sessionRegistry.getAllPrincipals()) {
			sessionInformations.addAll(sessionRegistry.getAllSessions(principal, false));
		}

		List<Information> informations = new ArrayList<>();
		for(SessionInformation sessionInformation : sessionInformations) {

			Date lastRequest = sessionInformation.getLastRequest();
			String sessionId = sessionInformation.getSessionId();

			String exchangeId = null;
			String userId = null;
			
			Object principal = sessionInformation.getPrincipal();
			if (principal instanceof CustomUserDetails) {
				CustomUserDetails user = (CustomUserDetails) principal;
				
				exchangeId = user.getExchangeId();
				userId = user.getUserId();
				
				Information information = new Information(exchangeId, userId, sessionId, lastRequest, principal);
				
				informations.add(information);
			}
			
		}

		return informations;
	}
	public String userExists(String username) {
		for(Information information : getSessionInformations()) {
			String _username = information.getUserId();
			String _session_id=information.getSessionId();

			if (username.equals(_username)) {
				logger.debug("userExists :"+_username);
				return _session_id;
			}else {
				logger.debug("not userExists :"+_username);
			}
		}

		return "JSESSIONID";
	}
	
	public HashMap<String,String> userSessionList(String username) {
		HashMap<String,String> sessionId=new HashMap<String,String>();
		for(Information information : getSessionInformations()) {
			String _username = information.getUserId();
			String _session_id=information.getSessionId();
			/** 현재 로그인한 세션 사용자만 가져온다. **/
			if(username.equals(_username)) {
				logger.debug("/////////////////////user session list///////////////////");
				logger.debug("current username {}, old username {}",username,_username);
				sessionId.put(_session_id, _session_id);
			}
		}

		return sessionId;
	}
}
