package kr.co.coinis.webserver.common.exception;
/**
 * 
 * <pre>
 * 1.  기능		
 *   - 공통 RestTemplate Client Exception
 * 2.  주의사항	
 *    -  
 * 3.  작성자/작성일	: kim/2018. 2. 20.
 * ====================================
 * - 수정자/수정일/수정내용 	 
    1)
 * ====================================
 * <p/>
 * @author		: kim
 * @version		: v1.0
 * @see			: 
 * @since		: J2EE 1.7
 * </pre>
 */
public class RestTemplateClientException extends Exception{
	/**
	 * Error code
	 * @uml.property  name="errCode"
	 */
	private String errCode;

	/**
	 * @uml.property  name="sessionKey"
	 */
	private String sessionKey;

	/**
	 * @uml.property  name="data"
	 */
	private Object data;

	/**
	 * @return
	 * @uml.property  name="errCode"
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 * @uml.property  name="errCode"
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return
	 * @uml.property  name="sessionKey"
	 */
	public String getSessionKey() {
		return sessionKey;
	}

	/**
	 * @param sessionKey
	 * @uml.property  name="sessionKey"
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public RestTemplateClientException() {
		super();
	}

	public RestTemplateClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestTemplateClientException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public RestTemplateClientException(String errCode, String message, String sessionkey) {
		super(message);
		this.errCode = errCode;
		this.sessionKey = sessionkey;
	}

	public RestTemplateClientException(String message) {
		super(message);
	}

	public RestTemplateClientException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public RestTemplateClientException(Throwable cause) {
		super(cause);
	}
}