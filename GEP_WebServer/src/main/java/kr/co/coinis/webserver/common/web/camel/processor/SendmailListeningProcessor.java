package kr.co.coinis.webserver.common.web.camel.processor;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.vo.MessageVO;

@Component("sendmailListeningProcessor")
public class SendmailListeningProcessor implements Processor {
	private static final Logger LOG = LoggerFactory.getLogger(SendmailListeningProcessor.class);

	private Gson gson = new Gson();

	@Resource(name="messageDAO")
	private MessageDAO messageDAO;

	@SuppressWarnings("unchecked")
	@Override
	public void process(Exchange exchange) throws Exception {

		Map<String, Object> msgMap = gson.fromJson(exchange.getIn().getBody().toString(), Map.class);
		Map<String, Object> bodyMap = (Map<String, Object>) msgMap.get("body");

		String ifTransactionId = msgMap.get("ifTransactionId").toString();

		LOG.debug("[SendmailListeningProcessor] >> ifTransactionId = " + ifTransactionId);
		LOG.debug("[SendmailListeningProcessor] >> bodyMap = " + bodyMap);

		MessageVO message = new MessageVO();
		message.setIfTransactionId(ifTransactionId);
		message.setBody(gson.toJson(bodyMap));

		messageDAO.save(message);

	}

}
