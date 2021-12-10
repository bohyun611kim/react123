package kr.co.coinis.webserver.common.exception.rest;

import org.springframework.http.HttpStatus;

/**
 * 
 * <pre>
 * 1.  기능		
 *   -  Spring RestTemplate 사용자정의 rest exception
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
public class RestCustomException extends RuntimeException {
    private HttpStatus statusCode;

    private String body;
 
    
    public RestCustomException(HttpStatus code, String body, String msg) {
        super(msg);
        this.statusCode = code;
        this.body = body;
    }

	/**
	 * <pre>
	 * 1. 기능             
	 *  - 상태 코드 확인
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @return
	 * </pre>
	 */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 상태코드 설정
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param statusCode
	 * </pre>
	 */
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - Body get
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @return
	 * </pre>
	 */
    public String getBody() {
        return body;
    }

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - body 설정
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param body
	 * </pre>
	 */
    public void setBody(String body) {
        this.body = body;
    }

}
