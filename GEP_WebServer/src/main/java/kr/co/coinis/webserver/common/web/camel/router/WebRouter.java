package kr.co.coinis.webserver.common.web.camel.router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import kr.co.coinis.webserver.common.dao.CommDAO;
import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ServerInfoVO;
import kr.co.coinis.webserver.common.web.camel.processor.SendmailListeningProcessor;
import kr.co.coinis.webserver.common.web.camel.processor.WalletServerIfListeningProcessor;

public class WebRouter extends RouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(WebRouter.class);
	
	private static ProducerTemplate camelTemplate = null;
	
	private static final CamelHelper helper = CamelHelper.getInstance();
	
	@Resource
	private CommDAO commDAO;
	
	@Resource(name="messageDAO")
	private MessageDAO messageDAO;

	@Value("${activemq.broker.url}")
	private String ACTIVE_MQ_BROKER_URL;
	
	@Value("${activemq.user.id}")
	private String ACTIVE_MQ_USER_ID;
	
	@Value("${activemq.user.password}")
	private String ACTIVE_MQ_USER_PWD;
	
	@Resource
	SendmailListeningProcessor sendmailListeningProcessor;
	
	@Resource
	WalletServerIfListeningProcessor walletServerIfListeningProcessor;
	
	@SuppressWarnings("static-access")
	@Override
	public void configure() throws Exception {
		LOG.debug("===========================================================================");
		LOG.debug("[WrtsRouter] >> Wrts TR Manager Camel Router Starting...");
		LOG.debug("===========================================================================");

		// 사이트 별 기초 자산 통화 설정
		Map<String, Integer> defaultCoinType = new HashMap<String, Integer>();
		defaultCoinType.put("COINIS",  Integer.parseInt(System.getProperty("coinis.default.coin.type", "20")));
		defaultCoinType.put("YAHOBIT", Integer.parseInt(System.getProperty("yahobit.default.coin.type", "10")));
		defaultCoinType.put("HOLDPORT", Integer.parseInt(System.getProperty("holdport.default.coin.type", "10")));
		helper.setDefaultConType(defaultCoinType);
		
		
		// 전송용 큐
		camelTemplate = getContext().createProducerTemplate();
		
		// template를 등록하여 사용가능하게 export.
		//CamelHelper.getInstance().setCamelTemplate(camelTemplate);
		//CamelHelper.getInstance().setMessageManager(messageManager);
		helper.setCamelTemplate(camelTemplate);
		helper.setActiveMqBrokerUrl(ACTIVE_MQ_BROKER_URL);
		helper.setActiveMqUserId(ACTIVE_MQ_USER_ID);
		helper.setActiveMqUserPwd(ACTIVE_MQ_USER_PWD);
		helper.setMessageDAO(messageDAO);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 1. 전역 Exception 처리
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		onException(Exception.class).continued(true)
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
				String strTraceMsg = CommonUtils.getPrintStackTrace(cause);
				exchange.getOut().setBody(new Exception(strTraceMsg));
				LOG.debug("[WrtsRouter] >> Error : " + strTraceMsg);
			}
		});
		
		// 서버 정보 조회
		ServerInfoVO svrInfoVo = commDAO.selectServerInfo(helper.getServerNo());
		helper.setSvrInfoVo(svrInfoVo);
		
		// 서버 환경 변수 조회
		List<Map<String, String>> svrEnv = commDAO.selectServerEnvInfo(helper.getServerNo());
		Map<String, String> svrEnvMap = new HashMap<>();
		for(Map<String, String> temp : svrEnv) {
			svrEnvMap.put(temp.get("ENV_VAR_NM"), temp.get("ENV_VAR_VAL"));
		}
		helper.setSvrEnv(svrEnvMap);
		
		String ifTransactionId = commDAO.selectIfTransactionId(helper.getServerNo());
		
		if(ifTransactionId != null) {
			String[] info = ifTransactionId.split("_");
			MessageUtils.ifTransactionDt = info[1];
			MessageUtils.ifTransactionSeq = Long.parseLong(info[2]);
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 워드 북 cache 처리
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		commDAO.selectWordBook();
		from("timer://COMMON_CODE_GET_SCHEDULE?period=20000&delay=5000").routeId("COMMON_CODE_GET")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				commDAO.selectWordBook();
			}
		});
		
		// 주문 체크를 위한 소수점 자릿수, 호가 데이터 캐싱
		commDAO.selectItemCodeMaster();
		from("timer://COMMON_CODE_GET_SCHEDULE?period=1m&delay=5000")
		.routeId("API_GET")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				commDAO.selectItemCodeMaster();
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// SendMail 결과 수신 큐
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		from("COINISWEB-MQ:queue:sendmail_result?selector=SERVER_NO='" + helper.getServerNo() + "'")
		.convertBodyTo(String.class)
		.threads(1, 2, "thread-pool-01")
		.process(sendmailListeningProcessor);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// SendSMS 결과 수신 큐
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		from("COINISWEB-MQ:queue:sendsms_result?selector=SERVER_NO='" + helper.getServerNo() + "'")
		.convertBodyTo(String.class)
		.threads(1, 2, "thread-pool-02")
		.process(sendmailListeningProcessor);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Wallet Server 요청 결과 수신 큐
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		from("COINISWEB-MQ:queue:COMMAND_RESPONSE?selector=SERVER_NO='" + helper.getServerNo() + "'")
		.convertBodyTo(String.class)
		.threads(2, 4, "thread-pool-03")
		.process(walletServerIfListeningProcessor);
	}

}
