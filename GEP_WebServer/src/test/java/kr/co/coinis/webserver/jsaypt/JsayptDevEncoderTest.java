package kr.co.coinis.webserver.jsaypt;

import java.io.FileInputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
/**
 * 
* <pre>
* 1.  기능	: 개발 서버 Property 암호화  
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
public class JsayptDevEncoderTest {
	@Test
	public void jsaypt() throws Exception {
		/** 암호화 대상 Properties 읽어 들인다. **/
		String propFile = "c:/coinis/property/dev_config.properties";
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(propFile);
        props.load(new java.io.BufferedInputStream(fis));

        /** Property 암호화 encript **/
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(props.getProperty("key"));
	    
	    /** Redis Session **/
        String redis_usePool=encryptor.encrypt(props.getProperty("redis.usePool"));
	    String redis_hostName=encryptor.encrypt(props.getProperty("redis.hostName"));
	    String redis_port=encryptor.encrypt(props.getProperty("redis.port"));
	    String redis_password=encryptor.encrypt(props.getProperty("redis.password"));
	    
	    /** Redis Messaging rts 설정 **/
	    String messaging_redis_hostname = encryptor.encrypt(props.getProperty("messaging.redis.hostname"));
		String messaging_redis_port = encryptor.encrypt(props.getProperty("messaging.redis.port"));
		String messaging_ttl_redis_timeout = encryptor.encrypt(props.getProperty("messaging.ttl.redis.timeout"));
		String messaging_polling_interval = encryptor.encrypt(props.getProperty("messaging.polling.interval"));
		String messaging_repeat_count = encryptor.encrypt(props.getProperty("messaging.repeat.count"));
		String messaging_redis_channel_messages = encryptor.encrypt(props.getProperty("messaging.redis.channel.messages"));
	    
		/** WRts 설정 **/
		String socket_pool_init_size = encryptor.encrypt(props.getProperty("socket.pool.init.size"));
		String socket_pool_max_size = encryptor.encrypt(props.getProperty("socket.pool.max.size"));
		String socket_pool_waiting_time = encryptor.encrypt(props.getProperty("socket.pool.waiting.time"));
		String socket_pool_check_interval_sec = encryptor.encrypt(props.getProperty("socket.pool.check.interval.sec"));
		String wrts_server_host = encryptor.encrypt(props.getProperty("wrts.server.host"));
		String wrts_server_port = encryptor.encrypt(props.getProperty("wrts.server.port"));
		
		/**WRTS ActiveMQ Broker Property  **/
		String activemq_broker_url = encryptor.encrypt(props.getProperty("activemq.broker.url"));
		String activemq_user_id = encryptor.encrypt(props.getProperty("activemq.user.id"));
		String activemq_user_password = encryptor.encrypt(props.getProperty("activemq.user.password"));
		
		/**Path  **/
		String attach_file_save_path = encryptor.encrypt(props.getProperty("attach.file.save.path"));
		
		
		System.out.println("/////////////////Redis Session//////////////////////////////");
		System.out.println("redis.usePool=ENC(" + redis_usePool + ")");
	    System.out.println("redis.hostName=ENC(" + redis_hostName + ")");
	    System.out.println("redis.port=ENC(" + redis_port + ")");
	    System.out.println("redis.password=ENC(" + redis_password + ")");
	    System.out.println();
	    
	    System.out.println("/////////////////Redis Messaging//////////////////////////////");
	    System.out.println("messaging.redis.hostname=ENC(" + messaging_redis_hostname + ")");
	    System.out.println("messaging.redis.port=ENC(" + messaging_redis_port + ")");
	    System.out.println("messaging.ttl.redis.timeout=ENC(" + messaging_ttl_redis_timeout + ")");
	    System.out.println("messaging.polling.interval=ENC(" + messaging_polling_interval + ")");
	    System.out.println("messaging.repeat.count=ENC(" + messaging_repeat_count + ")");
	    System.out.println("messaging.redis.channel.messages=ENC(" + messaging_redis_channel_messages + ")");
	    System.out.println();
	    
	    System.out.println("///////////////////// WRTS //////////////////////////////");
	    System.out.println("socket.pool.init.size=ENC(" + socket_pool_init_size + ")");
	    System.out.println("socket.pool.max.size=ENC(" + socket_pool_max_size + ")");
	    System.out.println("socket.pool.waiting.time=ENC(" + socket_pool_waiting_time + ")");
	    System.out.println("socket.pool.check.interval.sec=ENC(" + socket_pool_check_interval_sec + ")");
	    System.out.println("wrts.server.host=ENC(" + wrts_server_host + ")");
	    System.out.println("wrts.server.port=ENC(" + wrts_server_port + ")");
	    System.out.println();
	    
	    System.out.println("///////////////////// activemq //////////////////////////////");
	    System.out.println("activemq.broker.url=ENC(" + activemq_broker_url + ")");
	    System.out.println("activemq.user.id=ENC(" + activemq_user_id + ")");
	    System.out.println("activemq.user.password=ENC(" + activemq_user_password + ")");
	    System.out.println();
	    
	    System.out.println("///////////////////// path //////////////////////////////");
	    System.out.println("attach.file.save.path=ENC(" + attach_file_save_path + ")");
	    System.out.println();
	}

}
