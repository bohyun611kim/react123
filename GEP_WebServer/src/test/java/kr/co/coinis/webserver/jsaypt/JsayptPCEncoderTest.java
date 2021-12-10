package kr.co.coinis.webserver.jsaypt;

import java.io.FileInputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
/**
 * 
* <pre>
* 1.  기능	: 개발자 PC Property 암호화  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 3. 19.
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
public class JsayptPCEncoderTest {

	@Test
	public void jsaypt() throws Exception{
		/** 암호화 대상 Properties 읽어 들인다. **/
		String propFile = "c:/coinis/property/pc_config.properties";
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(propFile);
        props.load(new java.io.BufferedInputStream(fis));
		
	    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	    encryptor.setPassword(props.getProperty("key"));
	    
	    /** MSSQL JDBC **/
	    String jdbc_url_mssql=encryptor.encrypt(props.getProperty("jdbc.url.mssql"));
	    String jdbc_username_mssql_enc=encryptor.encrypt(props.getProperty("jdbc_username_mssql_enc"));
	    String jdbc_password_mssql_enc=encryptor.encrypt(props.getProperty("jdbc_password_mssql_enc"));
	    
	    /** Redis Session **/
	    String redis_hostName=encryptor.encrypt(props.getProperty("redis_hostName"));
	    String redis_port=encryptor.encrypt(props.getProperty("redis_port"));
	    String redis_password=encryptor.encrypt(props.getProperty("redis_password"));
	    
	    /** Redis Messaging rts 설정 **/
	    String messaging_redis_hostname=encryptor.encrypt(props.getProperty("messaging_redis_hostname"));
	    String messaging_redis_port=encryptor.encrypt(props.getProperty("messaging_redis_port"));
	    
	    /** WRts 설정 **/
	    String wrts_server_host=encryptor.encrypt(props.getProperty("wrts_server_host"));
	    String wrts_server_port=encryptor.encrypt(props.getProperty("wrts_server_port"));
	    
	    /**websocket **/
	    String websocket_url=encryptor.encrypt(props.getProperty("websocket_url"));
	    String websocket_userId=encryptor.encrypt(props.getProperty("websocket_userId"));
	    String websocket_password=encryptor.encrypt(props.getProperty("websocket_password"));
	   	System.out.println();
	   	
	   	/**WRTS ActiveMQ Broker Property  **/
	    String activemq_broker_url=encryptor.encrypt(props.getProperty("activemq.broker.url"));
	    String activemq_user_id=encryptor.encrypt(props.getProperty("activemq.user.id"));
	    String activemq_user_password=encryptor.encrypt(props.getProperty("activemq.user.password"));
	   	
	    System.out.println("//////////////////////////MS-SQL//////////////////////////////");
	    System.out.println("jdbc_url_mssql=" + jdbc_url_mssql);
	    System.out.println("jdbc_username_mssql_enc=" + jdbc_username_mssql_enc);
	    System.out.println("jdbc_password_mssql_enc=" + jdbc_password_mssql_enc);
	    System.out.println();
	    
	    System.out.println("////////////////////Redis Session//////////////////////////////");
	    System.out.println("redis_hostName=" + redis_hostName);
	    System.out.println("redis_port=" + redis_port);
	    System.out.println("redis_password=" + redis_password);
	    System.out.println();
	    
	    System.out.println("//////////////////Redis Messaging//////////////////////////////");
	    System.out.println("messaging_redis_hostname=" + messaging_redis_hostname);
	    System.out.println("messaging_redis_port=" + messaging_redis_port);
//	    System.out.println("messaging_redis_password=");
	    System.out.println();
	    
	    System.out.println("//////////////////////////WRTS 설정//////////////////////////////");
	    System.out.println("wrts_server_host=" + wrts_server_host);
	    System.out.println("wrts_server_port=" + wrts_server_port);
	    System.out.println();
	    
	    System.out.println("/////////////////websocket(ActiveMQ) 설정////////////////////////");
	    System.out.println("websocket_url=" + websocket_url);
	    System.out.println("websocket_userId=" + websocket_userId);
	    System.out.println("websocket_password=" + websocket_password);
	    
	    System.out.println("///////////////////WRTS ActiveMQ Broker Property////////////////");
	    System.out.println("activemq_broker_url=" + activemq_broker_url);
	    System.out.println("activemq_user_id=" + activemq_user_id);
	    System.out.println("activemq_user_password="+activemq_user_password);
	 }
}
