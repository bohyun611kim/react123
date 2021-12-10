package kr.co.coinis.webserver.common.security.spring.http;
/**
 * 
* <pre>
* 1.  기능	: 로그인 요청 Ajax Response Body  
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
public class SuccessBody {

	private String message;
	private boolean error = false;
	private Object data;
	private StatusCode statusCode = StatusCode.OK;
	private int code = StatusCode.OK.ordinal();

	public SuccessBody() {
	}

	public SuccessBody(String message) {
		this.message = message;
	}

	public SuccessBody(String message, boolean error) {
		this.message = message;
		this.error = error;
	}

	public SuccessBody(String message, boolean error, Object data) {
		this.message = message;
		this.error = error;
		setData(data);
	}

	public SuccessBody(String message, boolean error, StatusCode statusCode) {
		this.message = message;
		this.error = error;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public int getCode() {
		return statusCode.value();
	}
}
