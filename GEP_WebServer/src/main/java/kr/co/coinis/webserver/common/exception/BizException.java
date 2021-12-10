package kr.co.coinis.webserver.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
* <pre>
* 1.  기능		: 공통 BizException 처리        
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 2. 20.
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
public class BizException extends Exception{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(BizException.class);
	private String errCode;
	private String sessionKey;
	private Object data;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public BizException(String errCode, String message, String sessionkey) {
		super(message);
		this.errCode = errCode;
		this.sessionKey = sessionkey;
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public BizException(Throwable cause) {
		super(cause);
	}
}