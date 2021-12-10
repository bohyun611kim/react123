package kr.co.coinis.webserver.common.client;

import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import kr.co.coinis.webserver.framework.core.client.provider.client.SendTransformProvider;
/**
 * <pre>
 * 1.  기능		: 외부 연계를 위한 restTemplate 클라이언트 
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
public class ClientTransformProvider extends SendTransformProvider {
	public ClientTransformProvider(){}
	private static final Logger logger = LoggerFactory.getLogger(ClientTransformProvider.class);
	
	private String                sslProtocol     = "TLS";
	/**
	 * context-common-bean.xml 선언 
	 */ 
	@Autowired
	private RestTemplate restTemplate;

	public HashMap<String,String> getHeaders(HashMap<String,String> headers) throws Exception{
		return headers;
	}
	
	public String sendTransform(String providerURL, Map<String, String> header,Object body, String method) throws Exception {
		if(this.isHttpsProtocol(providerURL)){
			this.enableSSL();
		}
		return (String)this.sendTransform(providerURL, header, body, method, restTemplate);
	} 
	
    /**
     * 
     * <pre>
     * 1. 기능             
     *  - restTemplate SSL 적용
     * 2. 작성자/작성일: kim/2018. 2. 20.
     * ====================================
     * - 수정자/수정일/수정내용
     * ----------------------------------
        1) 
     * ====================================
     * <p/>
     * 
     * </pre>
     */
    private void enableSSL() throws Exception {
        logger.debug("entry enableSSL");
        try {
            // SSLContext protocols: TLS, SSL, SSLv3
            SSLContext sc = SSLContext.getInstance(this.sslProtocol);
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
                        String authType) throws CertificateException {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
                        String authType) throws CertificateException {
                }
            } };
            
            ClientHttpRequestFactory factory = restTemplate.getRequestFactory();
            if (!(factory instanceof HttpComponentsClientHttpRequestFactory) || factory == null) {
                factory = new HttpComponentsClientHttpRequestFactory();
                restTemplate.setRequestFactory(factory);
            }
            
            sc.init(null, trustAllCerts, null);
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sc, new NoopHostnameVerifier());
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sf).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            ((HttpComponentsClientHttpRequestFactory)factory).setHttpClient(httpClient);
            
        }
        catch ( Exception e ) {
            logger.error("RestClientEx enableSSL error - " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public static boolean isHttpsProtocol(String url) {
        String httpProtocol = url.substring(0, 5);
        if ( httpProtocol.equalsIgnoreCase("https") ) {
            return true;
        }
        return false;
    }
}
