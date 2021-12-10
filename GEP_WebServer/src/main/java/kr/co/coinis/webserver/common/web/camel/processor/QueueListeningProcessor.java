package kr.co.coinis.webserver.common.web.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueListeningProcessor implements Processor {
	private static final Logger LOG = LoggerFactory.getLogger(QueueListeningProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		LOG.debug("Queue Header = " + exchange.getIn().getHeaders());
		LOG.debug("Queue body = " + exchange.getIn().getBody().toString());

		// llll
	}

}
