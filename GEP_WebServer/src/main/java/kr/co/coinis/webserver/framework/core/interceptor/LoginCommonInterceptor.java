package kr.co.coinis.webserver.framework.core.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.coinis.webserver.common.exception.SessionExpiredException;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
/**
* <pre>
* 1.  기능	: 사용자 로그인 Interceptor
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

public class LoginCommonInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	private static final Logger logger = LoggerFactory.getLogger(LoginCommonInterceptor.class);
	/**
	 *
	 * <pre>
	 * 1.  기능		: 1) 로그인 유무 판단
	 * 				  2) 추가 인증 유무 판단
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 3. 29.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param value
	 * @return boolean
	 * </pre>
	 * @throws SessionExpiredException
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, SessionExpiredException {


		String strPageKey = CommonUtils.getExchangeId(request).toLowerCase();
		if(strPageKey.equalsIgnoreCase("yahobit"))
			strPageKey = "alibit";

		strPageKey=CommonUtils.getNewPageKey(request);

		try {

			HttpSession session = request.getSession(true);

			logger.info("LoginCommonInterceptor preHandle Request IP : " + CommonUtils.getClientIpAddr(request)
				+ ", Request URI : " + request.getRequestURI()
				+ ", PageKey : " + strPageKey
				+ ", ContentType : " + request.getContentType()
				+ ", Session :" + session.getId());


			CustomUserDetails userSessionInfo = null;

			// 세션 가져오기
			if(request.getRequestedSessionId() != null) {

				userSessionInfo = redisSessionRepository.findBySession(request.getRequestedSessionId());
				//logger.info("sessionInfo["+userSessionInfo.toString()+"]");

				session.setAttribute("userInfo", userSessionInfo);

			} else {
				session.removeAttribute("userInfo");
			}


			// redis에서 조회한 사용자 정보 존재 유무에 따른 사용자 정보 session에 추가 처리
			if(userSessionInfo == null) {

				if("application/json".equals(request.getHeader("Accept"))) {
					// ajax 요청으로 들어온 경우
					throw new SessionExpiredException();
				} else {
					// page 이동인 경우
					response.sendRedirect(request.getContextPath() + "/" + strPageKey + "/login");

					return false;
				}
			} else {
				if(userSessionInfo.getLoginYn() == 0) {
					// 로그인 되지 않은 사용자 로그인 화면으로 이동
					response.sendRedirect(request.getContextPath() + "/" + strPageKey + "/login");
					return false;
				}
			}

		} catch (Exception e) {
			if("application/json".equals(request.getHeader("Accept"))) {
				throw new SessionExpiredException();

			} else {
				logger.debug("@@ [LoginCommonInterceptor] >> Error > " + CommonUtils.getPrintStackTrace(e) );
				response.sendRedirect(request.getContextPath() + "/" + strPageKey + "/login");
				return false;
			}

		}

		return true;
	}

}
