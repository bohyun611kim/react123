package kr.co.coinis.webserver.activemq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import com.google.gson.Gson;

import kr.co.coinis.webserver.common.utils.GoogleOTP;

public class TopicTest {
	@Test
	public void opt_test() {
		Map<String, String> res = GoogleOTP.generate("YAHOBIT", "kangnaru@naver.com");
		System.out.println(res);
	}

	@Test
	public void sms_test() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.110.192:61616");
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("sendsms");

			javax.jms.MessageProducer producer = session.createProducer(queue);
			TextMessage msg = session.createTextMessage();
			
			Map<String, Object> msgMap = new HashMap<String, Object>();
			msgMap.put("svrNo", "1001");
			msgMap.put("svrTypeCd", "1000");
			msgMap.put("rcvTypeCd", "9000");
			msgMap.put("msgCode", "902");
			msgMap.put("sndDt", new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
			msgMap.put("ifTransactionId", "WAS1001_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_0000000001");

			Map<String, Object> bodyMap = new HashMap<String, Object>();
			bodyMap.put("exchangeId", "YAHOBIT");
			bodyMap.put("userId", "kangnaru@naver.com");
			bodyMap.put("to", "01063351777");
			bodyMap.put("msg", "회원님의 인증번호는 010123 입니다.");
			bodyMap.put("msgOption", "sms");


			msgMap.put("body", bodyMap);
			String body = new Gson().toJson(msgMap, Map.class);

			msg.setText(body);

			producer.send(msg);
			
			producer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void topic_test() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.110.192:61616");
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("ds_contract");

			javax.jms.MessageProducer producer = session.createProducer(topic);
			TextMessage msg = session.createTextMessage();
			msg.setStringProperty("SERVER_NO", "01");
			msg.setStringProperty("COINKIND", "XLM");
			msg.setStringProperty("ENC_KEY", "test_key");
			msg.setStringProperty("COMMAND", "DW_BALANCE_V2");

			Map<String, Object> bodyMap = new HashMap<String, Object>();
			bodyMap.put("UserID", "kangnaru");
			bodyMap.put("AccNO", "test1234");
			bodyMap.put("AccType", 120);
			bodyMap.put("ReqType", "1");
			bodyMap.put("ReqIdx", "12345");

			bodyMap.put("CoinAccount", "GASHVBOD7IP3OQH7SG26QG3YFYHB3XJAWIQNWD76Y2TML273KD4VHNOG");
//			bodyMap.put("TargetAddress", "rp2diYfVtpbgEMyaoWnuaWgFCAkqCAEg28");
//			bodyMap.put("TargetAddress1", 1154338024);
//			bodyMap.put("RequestAmount", 0.000003);
//			bodyMap.put("UserHeaderID", "test.user");
			bodyMap.put("ITP", 3);
			bodyMap.put("RequestIndex", "req");

			String body = new Gson().toJson(bodyMap, Map.class);

			msg.setText(body);

			producer.send(msg);
			
			producer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void topic_notice_test() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.110.192:61616");
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("users.all");
			
			javax.jms.MessageProducer producer = session.createProducer(topic);
			TextMessage msg = session.createTextMessage();
			msg.setStringProperty("TOPIC_HEADER", "DN_NOTICECONTENT");
//			msg.setStringProperty("TOPIC_HEADER", "DN_NEWSCONTENT");
			
			Map<String, Object> bodyMap = new HashMap<String, Object>();
			bodyMap.put("createDateTime", "20180530184910");
			bodyMap.put("title", "배고프다");
			bodyMap.put("contents", "민대리 배고프다...");
			bodyMap.put("sequenceNO", "1");
			bodyMap.put("urgentYN", "Y");
			bodyMap.put("allYN", "Y");
			bodyMap.put("topDisplayYN", "Y");
			
			Map<String, Map<String, Object>> msgbody = new HashMap<>();
			msgbody.put("body", bodyMap);
			String body = new Gson().toJson(msgbody, Map.class);
			
			msg.setText(body);
			
			producer.send(msg);
			
			producer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
