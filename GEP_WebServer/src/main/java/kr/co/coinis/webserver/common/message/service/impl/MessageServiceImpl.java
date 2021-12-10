/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.message.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.service.MessageService;
import kr.co.coinis.webserver.common.message.vo.MessageVO;

/**
 * <pre>
 * kr.co.coinis.webserver.common.message.service.impl
 *    |_ MessageServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 2:24:40
 * @version : 
 * @author : Seongcheol
 */
@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Value("${messaging.repeat.count}")
	private int repeatCount;
	
	@Value("${messaging.polling.interval}")
	private Long interval;
	
    @Autowired
    private MessageDAO messageDAO;
    
    @Override
    public MessageVO getDeferredResult(String ifTransactionId) throws Exception {
    	MessageVO message = null;
    	
    	int count=0;
    	
    	while(true) {
    		try {
    			message = messageDAO.get(ifTransactionId);
    			if(message != null) {
    				messageDAO.delete(ifTransactionId);
    				break;
    			} else {
    				Thread.sleep(interval);
    			}
    		} catch(Exception ext) {
    			logger.error(ext.getMessage());
    		} finally {
    			if(count > repeatCount) {
    				logger.error("interface reponse timeout");
    				messageDAO.delete(ifTransactionId);
    				break;
    			}
    			count=count+1;
    		}
    	}
    	
    	return message;
    }
    
    @Override
    public MessageVO getDeferredResult(String ifTransactionId, int timeOut) throws Exception {
    	MessageVO message = null;
    	
    	int count=0;
    	
    	while(true) {
    		try {
    			message = messageDAO.get(ifTransactionId);
    			if(message != null) {
    				messageDAO.delete(ifTransactionId);
    				break;
    			} else {
    				Thread.sleep(interval);
    			}
    		} catch(Exception ext) {
    			logger.error(ext.getMessage());
    		} finally {
    			if((interval * count) > timeOut) {
    				logger.error("interface reponse timeout");
    				messageDAO.delete(ifTransactionId);
    				break;
    			}
    			count=count+1;
    		}
    	}
    	
    	return message;
    }
}
