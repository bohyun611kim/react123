
package kr.co.coinis.webserver.framework.core.client.provider.method.type;
/**
 * 
 * <pre>
 * 1.  기능		: 연계 API Method Type 정의
 * 2.  처리개요	: 
 * 3.  주의사항	:  
 * 4.  작성자/작성일	: kim/2018. 2. 20.
 * ====================================
 * 5.  수정사항
 * - 수정자/수정일 	: 
 * - 수정사유/내역 	:
 * ====================================
 * <p/>
 * @author		: kim
 * @version		: v1.0
 * @see			: 
 * @since		: J2EE 1.7
 * </pre>
 */
public interface MethodType {
	
	/** The GET. */
	public final String GET    = "GET";
	
	/** The POST. */
	public final String POST   = "POST";
	
	/** The PUT. */
	public final String PUT    = "PUT";
	
	/** The DELETE. */
	public final String DELETE = "DELETE";
}
