package kr.co.coinis.webserver.common.security.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.SessionUtil;
import kr.co.coinis.webserver.holdport.member.mbr002.service.HoldportLoginService;
import kr.co.coinis.webserver.holdport.member.mbr002.vo.ReqLoginVO;
/**
 *
* <pre>
* 1.  기능	: Spring Security 사용자 정보 조회 Provider
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
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private HoldportLoginService holdportLoginService;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		String id = (String) authentication.getPrincipal();
		String pw = (String) authentication.getCredentials();
		String realIp =  CommonUtils.getClientIpAddr(request);

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(SessionUtil.getSessionId());
		Collection<? extends GrantedAuthority> authorities;

		logger.info("spring security redis user["+userDetails.getUserId()+"]");

		try {

			if(userDetails != null && userDetails.getLoginYn() == 1) {
				String jsessionId = userDetails.getSessionId();

				// 로그인 처리를 위한 세션이 존재할 경우
				ReqLoginVO reqLoginVO = new ReqLoginVO();
				reqLoginVO.setExchangeId(CommonUtils.getNewExchangeId(request));
				reqLoginVO.setId(userDetails.getUserId());
				//reqLoginVO.setPw(pw);

				// 유저 정보 조회
				userDetails = holdportLoginService.selectMemberInfo(reqLoginVO);
				userDetails.setSessionId(jsessionId);
				userDetails.setLoginIp(realIp);

				logger.info("id : " + id + " / password : ****");

				// 권한 부여
				Role role = new Role();
				role.setName("ROLE_USER");
				List<Role> roles = new ArrayList<Role>();
				roles.add(role);

				userDetails.setAuthorities(roles);

				authorities = userDetails.getAuthorities();

			} else {
				// 로그인 처리를 위한 세션이 없을 경우
				throw new BadCredentialsException("잘못된 접근입니다.");
			}
		} catch (UsernameNotFoundException e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			throw new RuntimeException(e.getMessage());
		}

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDetails, pw, authorities);
		result.setDetails(userDetails);

		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
