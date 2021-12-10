package kr.co.coinis.webserver.common.security.spring.http;
/**
 * 
* <pre>
* 1.  기능		:
* 200 : 성공
* 401 : 로그인하지 않음.
* 402 : 접근권한이 없음.
* 403 : 중복로그인 여부 필요.
* 404 : 비밀번호 기간이 만료됨.
* 480 : 폼 유효성검사 오류
*   
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
public enum StatusCode {
	OK(200),
	Unauthorized(401),
	AccessDenied(402),
	DuplicationLogin(403),
	PasswordUseExpired(404),
	FormValidation(480);

	private final int value;

	StatusCode(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}