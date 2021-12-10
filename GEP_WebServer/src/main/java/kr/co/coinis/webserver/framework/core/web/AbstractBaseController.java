package kr.co.coinis.webserver.framework.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import kr.co.coinis.webserver.framework.core.validator.Validator;
/**
 * 
* <pre>
* 1.  기능	: 
*  - Controller 공통 추가 클래스 
*  - 필수로 controller에 추가 할 필요는 없음(optional)
*     
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
public class AbstractBaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 입력항목 파라메타 체크 **/
	@Autowired
	protected Validator validator;
	
	/** Controller 메세지 처리를 위해서 추가[필요할 경우 사용] **/
	/*@Autowired
	protected MessageSourceAccessor messageSource = null;
	
	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		this.messageSource = msAcc;
	}*/

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 코드 key 값에 대한 메세지를 return
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param key
	 * &#64;return
	 * </pre>
	 */
	/*public String getMsg(String key) {
		return messageSource.getMessage(key, Locale.getDefault());
	}*/

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  -  KEY에 해당하는 메세지 반환
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param key
	 * &#64;param objs
	 * &#64;return
	 * </pre>
	 */
	/*public String getMsg(String key, Object[] objs) {
		return messageSource.getMessage(key, objs, Locale.getDefault());
	}*/

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - Session에 정의 된 값을 가져온다.
	 *  - ex : txid(시스템 거래 고유 아이디)
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param name
	 * &#64;return
	 * </pre>
	 */
	public String getRequestAttribute(String name) {
		String txId = (String) RequestContextHolder.currentRequestAttributes().getAttribute(name,
				RequestAttributes.SCOPE_SESSION);
		return txId;
	}

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 신규 Session을 정의한다.
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param name
	 * &#64;param value
	 * </pre>
	 */
	public void setRequestAttribute(String name, Object value) {
		RequestContextHolder.currentRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - Web Context 생성
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;return
	 * </pre>
	 */
	public WebApplicationContext getWebApplicationContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}
}
