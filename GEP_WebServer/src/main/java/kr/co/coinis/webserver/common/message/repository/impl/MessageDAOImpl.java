/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.message.repository.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.vo.MessageVO;
import kr.co.coinis.webserver.common.utils.JSONConverter;

/**
 * <pre>
 * kr.co.coinis.webserver.common.message.repository.impl
 *    |_ MessageDAOImpl.java
 *
 * </pre>
 * @date : 2019. 4. 23. 오후 8:53:04
 * @version :
 * @author : Seongcheol
 */
@Repository(value = "messageDAO")
public class MessageDAOImpl implements MessageDAO {

	@Value("${messaging.ttl.redis.timeout}")
	private Long timeOut;

	@Autowired
	RedisTemplate<String,Object> redisMsgTemplate;

	@Override
	public void save(MessageVO messageVO) throws Exception {

		String ifTransactionId = messageVO.getIfTransactionId();

    	redisMsgTemplate.opsForHash().put(ifTransactionId
    			, ifTransactionId
    			, JSONConverter.objectToString(messageVO));

    	redisMsgTemplate.expire(ifTransactionId, timeOut, TimeUnit.MILLISECONDS);
	}

	@Override
	public MessageVO get(String ifTransactionId) throws Exception {
		String data = (String) redisMsgTemplate.opsForHash().get(ifTransactionId, ifTransactionId);

		MessageVO result = null;

		if (data != null || !"".equals(data)) {
			result = JSONConverter.stringToJson(data, MessageVO.class);
		}

		return result;
	}

	@Override
	public void delete(String ifTransactionId) throws Exception {
		redisMsgTemplate.opsForHash().delete(ifTransactionId, ifTransactionId);
	}
}
