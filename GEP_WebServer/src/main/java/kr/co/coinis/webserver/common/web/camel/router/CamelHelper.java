package kr.co.coinis.webserver.common.web.camel.router;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.vo.MessageVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ItemCodeVO;
import kr.co.coinis.webserver.common.vo.ServerInfoVO;

public class CamelHelper {

	private static final Logger logger = LoggerFactory.getLogger(CamelHelper.class);
	private static CamelHelper instance = null;
	private static ProducerTemplate camelTemplate = null;	// ActiveMQ 관련 Camel Producer Template
	private static String activeMqBrokerUrl = "";			// ActiveMQ 관련 Broker Url
	private static String activeMqUserId = "";				// ActiveMQ 관련 User
	private static String activeMqUserPwd = "";				// ActiveMQ 관련 User Pwd
	private static MessageDAO messageDAO = null;			// MessageDAO
	private static ServerInfoVO svrInfoVo;					// 서버 정보 VO
	private static Map<String, String> svrEnv = new HashMap<String, String>();
	private static Map<String, Integer> defaultConType = new HashMap<String, Integer>();
	public static ConcurrentHashMap<String, ItemCodeVO> itemCodeMaster = new ConcurrentHashMap<>();
	
	///////////////////////////////////////////////////////////////////////////////
	// 인터페이스 아이디 채번을 위한 변수들
	private static final String __serial_file_name = System.getProperty("catalina.home") + "/temp/serial_num.dat";
	private static long serial_num = 0;
	private static String __cur_date_str = new SimpleDateFormat("yyyyMMdd").format(new Date());
	///////////////////////////////////////////////////////////////////////////////

	protected CamelHelper() {}
	
	static {
		if(instance == null) {
			instance = new CamelHelper();
			try {
				// Serial Number를 얻어오는 로직 (파일로 저장하고있다가 서버가 재기동 될때 읽어온다. YYYYMMDD|serial 형식 :: 20190327|125 )
				BufferedReader br = new BufferedReader(new FileReader(new File(__serial_file_name)));
				String serial_num_str = "";
				String line = br.readLine();
				serial_num_str += line;
				while(line != null) {
					line = br.readLine();
					if(line != null) serial_num_str += line;
				}
				String[] splt = serial_num_str.trim().split("[|]");
				String date_time_str = splt[0];
				serial_num = Double.valueOf(splt[1]).longValue();
				if(!date_time_str.equalsIgnoreCase(__cur_date_str)) serial_num = 0;
				
				br.close();
			} catch(Exception e) {
				serial_num = 0;
			}

		}
	}
	
	public static CamelHelper getInstance() {
		return instance;
	}
	
	public static void setCamelTemplate(ProducerTemplate template) {
		CamelHelper.camelTemplate  = template;
	}
	public static ProducerTemplate getCamelTemplate() {
		return camelTemplate;
	}
	
	public static String getActiveMqBrokerUrl() {
		return activeMqBrokerUrl;
	}
	public static void setActiveMqBrokerUrl(String activeMqBrokerUrl) {
		CamelHelper.activeMqBrokerUrl = activeMqBrokerUrl;
	}
	public static void setActiveMqUserId(String activeMqUserId) {
		CamelHelper.activeMqUserId = activeMqUserId;
	}
	public static void setActiveMqUserPwd(String activeMqUserPwd) {
		CamelHelper.activeMqUserPwd = activeMqUserPwd;
	}

	public static MessageDAO getMessageDAO() {
		return messageDAO;
	}
	public static void setMessageDAO(MessageDAO messageDAO) {
		CamelHelper.messageDAO = messageDAO;
	}

	public static ServerInfoVO getSvrInfoVo() {
		return svrInfoVo;
	}
	public static void setSvrInfoVo(ServerInfoVO svrInfoVo) {
		CamelHelper.svrInfoVo = svrInfoVo;
	}
	
	public static String getSvrEnv(String key) {
		return svrEnv.get(key);
	}
	public static void setSvrEnv(Map<String, String> svrEnv) {
		CamelHelper.svrEnv = svrEnv;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 기초통화 Coin Type을 가져옴
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getDefaultConType
	 * @date : 2019. 4. 27.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 27.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param strExchangeId
	 * @return
	 */
	public static Integer getDefaultConType(String strExchangeId) {
		return defaultConType.get(strExchangeId);
	}
	public static void setDefaultConType(Map<String, Integer> defaultConType) {
		CamelHelper.defaultConType = defaultConType;
	}

	public static void sendMsgMQ(String queue_name, String body) {
		try {
			//camelTemplate.sendBodyAndHeaders("COINISWEB-MQ" + queue_name, body, headers);
			camelTemplate.asyncSendBody("COINISWEB-MQ:queue:" + queue_name , body);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : ActiveMQ에 Direct로 기록 (headers -> setStringProperty)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendMsgMQ
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param queue_name
	 * @param headers
	 * @param body
	 * @throws Exception
	 */
	public static void sendMsgMQ(String queue_name, Map<String, Object> headers, String body) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(activeMqUserId, activeMqUserPwd, activeMqBrokerUrl);
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(queue_name);
		
		javax.jms.MessageProducer producer = session.createProducer(queue);
		
		TextMessage msg = session.createTextMessage();
		
		Set<String> keySet = headers.keySet();
		for(String keys : keySet) {
			msg.setStringProperty(keys, headers.get(keys).toString());
		}
		
		msg.setText(body);
		
		producer.send(msg);
		
		producer.close();
		session.close();
		connection.close();
	}

	public static void sendMailMsgMQ(String queue_name, String body) {
		try {
			//camelTemplate.sendBodyAndHeaders("COINISWEB-MQ" + queue_name, body, map);
			//camelTemplate.sendBody("COINISWEB-MQ" + queue_name, body);
			camelTemplate.asyncSendBody("COINISWEB-MQ:queue:" + queue_name, body);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
	}
	
	public static void sendMailMsgDirectMQ(String queue_name, String body) throws Exception {
//		ConnectionFactory factory = new ActiveMQConnectionFactory(activeMqUserId, activeMqUserPwd, activeMqBrokerUrl);
//		Connection connection = factory.createConnection();
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		Queue queue = session.createQueue(queue_name);
//
//		javax.jms.MessageProducer producer = session.createProducer(queue);
//
//		TextMessage msg = session.createTextMessage();
//
//
//		msg.setText(body);
//
//		producer.send(msg);
//
//		producer.close();
//		session.close();
//		connection.close();

		logger.debug("[helper] >> sendMailMsgDirectMQ > queue_name=" + queue_name + ", body = " + body);
		camelTemplate.asyncSendBody("COINISWEB-MQ:queue:" + queue_name, body);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : WasServer에서 관리하는 전역 인터페이스 트랜잭션아이디 얻기 (WAS1001_20190327_000000000001 형식의 27byte)
	 * 2. 처리내용 : serial_num을 증가시키며 증가될때마다 __serial_file_name 에 20190327|123 형식으로 저장한다.
	 * </pre>
	 * @Method Name : getWasServerIfTxId
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public synchronized static String getWasServerIfTxId() {
		serial_num += 1;
		
		String strCurDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if(!__cur_date_str.equalsIgnoreCase(strCurDate)) {
			serial_num = 1;
			__cur_date_str = strCurDate;
		}
		writeSerialNo2File(__cur_date_str + "|" + serial_num);

		int SEVER_NO = Double.valueOf(System.getProperty("server.no", "1001")).intValue();

		// 123456789012345678901234567
		// WAS1001_20190428_0000000001
		// 0000000001
		String strIfTxId = String.format("WAS%s_%s_%s", SEVER_NO, strCurDate, CommonUtils.leftPad("" + serial_num, 10) );
		
		return strIfTxId;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : WasServer에서 관리하는 전역 인터페이스 트랜잭션아이디 얻기 (AT_1001_20190428_0000000001 형식의 27byte)
	 * 2. 처리내용 : serial_num을 증가시키며 증가될때마다 __serial_file_name 에 20190327|123 형식으로 저장한다.
	 * </pre>
	 * @Method Name : getWasServerIfTxIdAuto
	 * @date : 2019. 11. 1.
	 * @author : J
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 11. 1.		J				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public synchronized static String getWasServerIfTxIdAuto() {
		
		serial_num += 1;
		
		String strCurDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if(!__cur_date_str.equalsIgnoreCase(strCurDate)) {
			serial_num = 1;
			__cur_date_str = strCurDate;
		}
		writeSerialNo2File(__cur_date_str + "|" + serial_num);

		int SEVER_NO = Double.valueOf(System.getProperty("server.no", "1001")).intValue();
		
		// 123456789012345678901234567
		// AT_1001_20190428_0000000001
		// 0000000001
		String strIfTxId = String.format("AT_%s_%s_%s", SEVER_NO, strCurDate, CommonUtils.leftPad("" + serial_num, 10) );

		return strIfTxId;
	}
	
	private static void writeSerialNo2File(String contents) {
		try {
			FileWriter fw = new FileWriter(new File(__serial_file_name));
			fw.write(contents);
			fw.close();
		} catch(Exception e) {
			// noop
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : WasServer Number를 반환한다.
	 * 2. 처리내용 : catalina.sh 파일에 서버에 해당하는 -Dserver.no=1001 형태로 JAVA_OPTS에 지정되어있다.
	 * </pre>
	 * @Method Name : getServerNo
	 * @date : 2019. 4. 28.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 28.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */
	public static int getServerNo() {
		return Double.valueOf(System.getProperty("server.no", "1001")).intValue();
	}
	
	public static void startAmqListener() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMqUserId, activeMqUserPwd, activeMqBrokerUrl);
		Connection connection = connectionFactory.createConnection();

		connection.setClientID("12345");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue_sms_result = session.createQueue("sendsms_result");
		MessageConsumer consumer = session.createConsumer(queue_sms_result);
		MessageListener listner = new MessageListener() {
			@SuppressWarnings("unchecked")
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						//System.out.println("Received message" + textMessage.getText() + "'");
						Map<String, Object> msgMap = new Gson().fromJson(textMessage.getText().toString(), Map.class);
						Map<String, Object> bodyMap = (Map<String, Object>) msgMap.get("body");
						
						String ifTransactionId = msgMap.get("ifTransactionId").toString();
						logger.debug("[SendmailListeningProcessor] >> ifTransactionId = " + ifTransactionId);
						logger.debug("[SendmailListeningProcessor] >> bodyMap = " + bodyMap);
						MessageVO msg = new MessageVO();
						msg.setIfTransactionId(ifTransactionId);
						msg.setBody(new Gson().toJson(bodyMap));
						
						messageDAO.save(msg);
					}
				} catch (Exception e) {
					logger.debug("[CamelHelper] >> Queue Listener > Error :: " + CommonUtils.getPrintStackTrace(e));
					//e.printStackTrace();
				}
			}
		};

		consumer.setMessageListener(listner);
	}
	
}
