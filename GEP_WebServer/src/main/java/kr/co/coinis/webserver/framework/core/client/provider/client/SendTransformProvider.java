
package kr.co.coinis.webserver.framework.core.client.provider.client;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import kr.co.coinis.webserver.common.exception.RestTemplateClientException;
import kr.co.coinis.webserver.common.exception.rest.RestCustomException;
import kr.co.coinis.webserver.framework.core.client.provider.method.type.MethodType;
import kr.co.coinis.webserver.framework.core.client.provider.parser.JsonParserUtil;

/**
 * 
 * <pre>
 * 1.  기능		
 *   -  공통 API로 수신한 xml,json 데이터를 파싱 없이 원본 그대로 되돌려 준다.
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
public class SendTransformProvider implements MethodType{ 
	private static final Logger logger = LoggerFactory.getLogger(SendTransformProvider.class);

	private static String sourceData;
	   

	public static String getSourceData() {
		return sourceData;
	}

	public static void setSourceData(String sourceData) {
		SendTransformProvider.sourceData = sourceData;
	}

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 인터페이스 연계 구현 함수
	 *  - Spring restTemplate의 Execute 메소드를 재정의 해서 사용
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param providerURL
	 * @param header
	 * @param body
	 * @param method
	 * @param restTemplate
	 * @throws Exception
	 * </pre>
	 */
	public Object sendTransform(String providerURL, Map<String, String> header, Object body, String method, RestTemplate restTemplate) throws Exception {
		logger.debug("");
		logger.debug("---------------------------------");
		logger.debug("요청        서버 :"+providerURL);
		if(header !=null)logger.debug("요청 header:"+header.toString());
		else			 logger.debug("요청 header:");
		logger.debug("요청 METHOD TYPE:"+method); 
	
		if(GET.equalsIgnoreCase(method) || POST.equalsIgnoreCase(method)||PUT.equalsIgnoreCase(method)||DELETE.equalsIgnoreCase(method) )  {
			try{
				String charset="UTF-8";
			   Object responseData=restTemplate.execute(   providerURL, 
															HttpExMethod.getMethod(method), 
															new AsyncRequestCallBack(header,body,method,providerURL) ,
															new PayloadToStringRestResponseExtractor(charset),String.class);
			     
				/** Response 데이터가 없을 경우 **/
				if(responseData==null){ 
					logger.error("데이터가 존재하지 않습니다.");
					throw new RestTemplateClientException("99","데이터가 없습니다.");
				}
			 	return responseData;
			}catch(Exception ex){
			    /** 오류 발생시 restTemplate 사용자 정의 Exception 발생 시킴 **/
			    if(ex instanceof RestCustomException){
			        RestCustomException customException=(RestCustomException)ex;
			        logger.debug("============="+providerURL+"        RestCustomException===============");
			        logger.debug("errorCode :"+customException.getStatusCode());
			        logger.debug("errorMsg  :"+customException.getMessage());
			        
			       throw new RestTemplateClientException("99",customException.getMessage());
			    /** 연결 오류 발생할 경우 **/   
			    }else if(ex instanceof ConnectException){
			        logger.debug("============="+providerURL+"        ConnectException ===============");
			        logger.debug("errorCode:99");
                    logger.debug("errorMsg :"+ex.getMessage());
                    /** Biz Excetion오류로 업무 공통에 되돌려 준다. **/
                    throw new RestTemplateClientException("99",ex.getMessage());

                /** restTemplate 연결 오류 발생할 경우 **/    
			    }else if(ex instanceof ResourceAccessException){
			        logger.debug("//////////////////////////////서버 응답 장애 발생!!///////////////////////////////////");
			        logger.debug("============="+providerURL+"        ResourceAccessException==============");
			        logger.debug("errorCode:99");
                    logger.debug("errorMsg :"+ex.getMessage());
                    
                    throw new RestTemplateClientException("99",ex.getMessage());
                /** 기타 오류 발생 할 경우 **/    
			    }else{
			        logger.debug("============="+providerURL+"==============");
                    logger.debug(ex.getMessage(),ex);
			        throw new RestTemplateClientException("99",ex.getMessage());
			    }
			}
		}else{
		    logger.debug("============="+providerURL+"==============");
			logger.error("METHOD TYPE이 없습니다.");
			throw new RestTemplateClientException("99", "METHOD TYPE이 없습니다.");
		}
		
	}
	
    /**
     * 
     * <pre>
     * 1.  기능		
     *   -   Method Type 정의
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
	public final static class HttpExMethod {
		private HttpExMethod(){}
		public static HttpMethod getMethod(String method) throws Exception{
			if(POST.equalsIgnoreCase(method)){
				return HttpMethod.POST;
			}else if(GET.equalsIgnoreCase(method)){
				return HttpMethod.GET;
			}else if(PUT.equalsIgnoreCase(method)){
				return HttpMethod.PUT;
			}else if(DELETE.equalsIgnoreCase(method)){
				return HttpMethod.DELETE;
			}else{
				throw new RestTemplateClientException("99","METHOD TYPE이 없습니다.");
				//throw new Exception("METHOD TYPE이 없습니다.");
			}
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		
	 *   - Spring Execute  재구현 클래스
	 *   - Async 방식으로 Client 연계
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
	protected class AsyncRequestCallBack implements RequestCallback {
		private final Map<String,String> header;
		private final Object body;
		
		private final String providerURL;
		private final String method;

		public AsyncRequestCallBack(Map<String,String> header,
									Object body,
									String method,
									String providerURL){
			this.header =header;
			this.body = body;
			this.method = method;
			this.providerURL = providerURL;
		}
		
		/**
		 * 
		 * <pre>
		 * 1. 기능             
		 *  - 연계 요청 Overriding 메소드
		 * 2. 작성자/작성일: kim/2018. 2. 20.
		 * ====================================
		 * - 수정자/수정일/수정내용
		 * ----------------------------------
		    1) 
		 * ====================================
		 * <p/>
		 * 
		 * @param clientHttpRequest
		 * </pre>
		 */
        public void doWithRequest(ClientHttpRequest clientHttpRequest){
            try{
            	HttpHeaders headers = clientHttpRequest.getHeaders();
	            
            	logger.debug("clientHttpRequest.getURI()===>"+clientHttpRequest.getURI());
	            /** Header 세팅 **/
	        	if(header !=null){
	    			Iterator iter=header.keySet().iterator();
	    			while(iter.hasNext()){
	    				String headerType=(String)iter.next();
	    				String headerValue=header.get(headerType);
	    				headers.add(headerType, headerValue);
	    			}
	    		}
	        	/** Body 세팅 **/
	    		if(body !=null){ 
	    			JsonParserUtil utils=new JsonParserUtil();
	    			String jsonBodys=utils.jsonMashall(body);
	    			byte requestByte[]=jsonBodys.getBytes("UTF-8");	
		    		clientHttpRequest.getBody().write(requestByte, 0, requestByte.length);
		    	}
            }catch(Exception e){
            	logger.error(e.getMessage(),e);
            }
        }
    }
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		
	 *   - client 연계결과 Return  
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
    protected class PayloadToStringRestResponseExtractor implements ResponseExtractor<Object> {
    	
//    	private HashMap<String,Object> result;
//    	
//    	public void setResult(HashMap<String,Object> result){
//    	    this.result=result;
//    	}
//    	
//    	public  HashMap<String,Object> getResult(){
//    	    return result;
//    	}
//    	
    	private String sourceData;
    	private String charset; 
    	
    	public PayloadToStringRestResponseExtractor(String charset){
    		this.charset=charset;
    	}
    	public String getSourceData() {
			return sourceData;
		}
		public void setSourceData(String sourceData) {
			this.sourceData = sourceData;
		}
		/**
    	 * 
    	 * <pre>
    	 * 1. 기능             
    	 *  - 결과를 받아 처리하는 Overriding 메소드 
    	 * 2. 작성자/작성일: kim/2018. 2. 20.
    	 * ====================================
    	 * - 수정자/수정일/수정내용
    	 * ----------------------------------
    	    1) 
    	 * ====================================
    	 * <p/>
    	 * 
    	 * @param clientHttpResponse
    	 * @return
    	 * </pre>
    	 */
        public Object extractData(ClientHttpResponse clientHttpResponse){
        	HashMap<String,Object> error=null;
        	try{
	        	InputStream is=clientHttpResponse.getBody();
	        	String message=clientHttpResponse.getStatusText();
	        	HttpStatus code =clientHttpResponse.getStatusCode();
	        	
	        	logger.info("[요청 결과           ]"+code.value());
	        	logger.info("[요청 결과 메세지  ]"+message);
	        	String sourceData="";
	        	
//	        	if(200 ==code.value() || 201 ==code.value() ){
	        	    byte[] b = new byte[8192];
	        	    int n;

	        	    try (BufferedInputStream bis = new BufferedInputStream(is);
	        	        	ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
		        	    while((n = bis.read(b)) != -1) {
		        	    	bos.write(b, 0, n);
			            }
		            	sourceData = bos.toString(this.charset);
	        	    }
	            	
//	            	logger.debug("요청 결과 데이터 :" + sourceData);
//	            	JsonParserUtil.jsonUnmashall(sourceData);
//	            	result=JsonParserUtil.getDataMap();
	            	
//	            	this.setResult(result);
	            	this.setSourceData(sourceData);
//	           }else{	
//	        		logger.debug("===============200,201 HTTP 외의 코드 값 ================");
//	            	error.put("errorCode", "99");
//	        		error.put("errorMsg",  message);
//	        		result=error;
//	        	} 
	       }catch(Exception e){
	    	  logger.error(e.getMessage(),e);
        		error=new HashMap<String,Object>();
        		error.put("errorCode", "99");
        		error.put("errorMsg", e.getMessage());
//        		result=error;
//        		this.setResult(result);
        		this.setSourceData("");
			}
        	return this.getSourceData();
        }
    }
    
}
