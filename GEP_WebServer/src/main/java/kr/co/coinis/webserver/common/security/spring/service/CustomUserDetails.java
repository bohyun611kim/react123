package kr.co.coinis.webserver.common.security.spring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
* <pre>
* 1.  기능	: Spring Security 사용자 정보 정의
* 2.  처리개요	:
* 3.  주의사항	:  기본 사용자명 'username/password/enabled'는 반드시 필드 항목 명칭 지킬 것
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
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	private String sessionId;
	// DB에서 가져오는 값 ////////////////////
	private String exchangeId;
	private String userId;
	private int    authLevel;
	private int    authMeansCd;
	private String joinDt;
	private int    userTypeCd;
	private int    loginYn;		// 0 : 인증 미완료, 1 : 인증 완료
	private long   loginDt;
	private String loginIp;
	private String privateIp;
	private String lang;

	////////////////////////////////////

	private List<Role> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public int getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}

	public int getAuthMeansCd() {
		return authMeansCd;
	}

	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}

	public String getJoinDt() {
		return joinDt;
	}

	public void setJoinDt(String joinDt) {
		this.joinDt = joinDt;
	}

	public int getUserTypeCd() {
		return userTypeCd;
	}

	public void setUserTypeCd(int userTypeCd) {
		this.userTypeCd = userTypeCd;
	}

	public int getLoginYn() {
		return loginYn;
	}

	public void setLoginYn(int loginYn) {
		this.loginYn = loginYn;
	}

	public long getLoginDt() {
		return loginDt;
	}

	public void setLoginDt(long loginDt) {
		this.loginDt = loginDt;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getPrivateIp() {
		return privateIp;
	}

	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}


	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("\n=================================").append("\n");
		sb.append(" - username[" + username).append("]\n");
		sb.append(" - userId[" + userId).append("]\n");
		sb.append(" - sessionId[" + sessionId).append("]\n");
		sb.append(" - exchangeId[" + exchangeId).append("]\n");
		sb.append(" - authLevel[" + authLevel).append("]\n");
		sb.append(" - authMeansCd[ " + authMeansCd).append("]\n");
		sb.append(" - userTypeCd[" + userTypeCd).append("]\n");
		sb.append(" - loginYn[" + loginYn).append("]\n"); // 0 : 인증 미완료, 1 : 인증 완료
		sb.append(" -  loginDt[" +  loginDt).append("]\n");
		sb.append(" -  loginIp[" +  loginIp).append("]\n");
		sb.append(" =================================").append("\n");

		return sb.toString();
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
