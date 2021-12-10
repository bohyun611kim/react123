
package kr.co.coinis.webserver.common.exception.rest;

import java.io.IOException;
import org.apache.commons.io.IOUtils; 
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * 
 * <pre>
 * 1.  기능		
 *   - Spring RestTemplat 응답에러처리   
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
public class RestResponseErrorHandler  implements ResponseErrorHandler {

    private ResponseErrorHandler myErrorHandler = new DefaultResponseErrorHandler();

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 상태코드 확인
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 * </pre>
	 */
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return myErrorHandler.hasError(response);
    } 

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 상태처리
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param response
	 * @throws IOException
	 * </pre>
	 */
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = IOUtils.toString(response.getBody()); 
        HttpStatus code=response.getStatusCode();
        String message=response.getStatusText(); 
        RestCustomException exception = new RestCustomException(code,body,message);
        throw exception; 
    }
}
