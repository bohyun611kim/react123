package kr.co.coinis.webserver.common.security.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.coinis.webserver.coinis.member.mbr002.service.LoginService;
import kr.co.coinis.webserver.coinis.member.mbr002.vo.ReqLoginVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
/**
 * 
* <pre>
* 1.  기능	: 사용자 정보 조회[DB]/외부연동을 통한 가져오기   
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
@Service("userService")
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private LoginService loginService;

	/*public CustomUserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
		return loadUserByUsername(username, password, "", "", "", "");
	}*/
	public CustomUserDetails loadUserByUsername(String exchangeId, String id, String pw, String realIp) throws UsernameNotFoundException {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		logger.debug("userId >> " + attr.getRequest().getParameter("userId"));
		logger.debug("getRemoteHost >> " + attr.getRequest().getRemoteHost());
		logger.debug("user-agent >> " + attr.getRequest().getHeader("user-agent"));

		///////////// TODO 사용자 조회 및 외부연동 개발 ZONE////////////
		//logger.debug("username : " + username);

		CustomUserDetails customUserDetails = new CustomUserDetails();
		//customUserDetails.setUsername(username);
		//customUserDetails.setPassword(password);
		///////////// TODO 사용자 조회 및 외부연동 개발 ZONE////////////
		
		ReqLoginVO reqLoginVO = new  ReqLoginVO();
		reqLoginVO.setExchangeId(exchangeId);
		reqLoginVO.setId(id);
		reqLoginVO.setPw(pw);

		// 로그인 조회
		//CustomUserDetails customUserDetails = loginService.selectMemberInfo(reqLoginVO);
		
		// 세션 vo에 조회 데이터 넣기
		
		// 추가 인증 관련 처리
		
		/////////////////// DEFAULT 사용자 권한 [수정 불가]//////////////////////////
		Role role = new Role();
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		/////////////////// DEFAULT 사용자 권한 [수정 불가]//////////////////////////

		customUserDetails.setAuthorities(roles);
		if (customUserDetails == null) {
			throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");
		}
			
		return customUserDetails;
	}

}
