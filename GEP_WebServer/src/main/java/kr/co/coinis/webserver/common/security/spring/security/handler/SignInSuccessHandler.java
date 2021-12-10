package kr.co.coinis.webserver.common.security.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.coinis.webserver.common.security.spring.http.RequestCheckParam;
import kr.co.coinis.webserver.common.security.spring.http.SuccessBody;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
/**
 *
* <pre>
* 1.  기능	: 로그인 성공 했을 경우 호출되는 핸들러
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
public class SignInSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	//private static final Logger logger = LoggerFactory.getLogger(SignInSuccessHandler.class);
	private boolean redirect = true;

	@Value("${redis.session.timeout.sec}")
	int sessionTimeOut;

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	private String redirectUrl(HttpServletRequest request, HttpServletResponse response) {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			return determineTargetUrl(request, response);
		}

		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);

			return determineTargetUrl(request, response);
		}

		clearAuthenticationAttributes(request);
		return savedRequest.getRedirectUrl();
	}

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private CommService commService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);

		if (RequestCheckParam.isAjax(request)) {
			HttpSession session = request.getSession();

			// CustomAuthenticationProvider에서 전달받은 user 정보
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();

			// 로그인 처리용으로 사용하던 세션 제거
			redisSessionRepository.delete(userDetails.getSessionId());

			// 변경된 session 값으로 업데이트
			userDetails.setSessionId(session.getId());
			redisSessionRepository.update(userDetails);

			// 세션 정보 업데이트
			request.getSession().setAttribute("userInfo", userDetails);
			request.getSession().setMaxInactiveInterval(sessionTimeOut);

			String redirectUrl = redirectUrl(request, response);

			logger.info("onAuthenticationSuccess session update userId["+userDetails.getUserId()+"],redirectUrl["+redirectUrl+"], sessionTimeOut["+sessionTimeOut+"]");

			// 응답 설정
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			Map<String, Object> data = new HashMap<>();
			//data.put("message", username);
			data.put("redirect", redirect);
			data.put("redirectUrl", redirectUrl);

			SuccessBody success = new SuccessBody();
			success.setError(false);
			success.setMessage(String.valueOf("Success"));
			success.setData(data);

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(success);

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();

			try {
				@SuppressWarnings("static-access")

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
				df.setTimeZone(tz);

				String strMainMsg = new String().format("<br>회원님께서는 <br> %s (KST) 에 IP : [%s] 에서 로그인 하셨습니다.<br>만일 본인이 로그인 하지 않으셨다면 비밀번호를 변경해 주세요."
													, df.format(new Date())
													, CommonUtils.getClientIpAddr(request)
													);

				String strExchange = CommonUtils.getNewExchangeId(request);
				if(strExchange.equalsIgnoreCase("YAHOBIT"))
					strExchange = "ALIBIT";

				commService.sendInfoMail(CommonUtils.getNewExchangeId(request), userDetails.getUserId(), "[BITA500] 로그인 알림", strMainMsg, false);

			} catch(Exception e) {
				logger.error("로그인 메일 발송 실패");
				logger.error(e.getMessage());
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}