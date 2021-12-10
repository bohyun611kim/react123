/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.support;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;

/**
 * <pre>
 * kr.co.coinis.webserver.common.support 
 *    |_ SessionExpiredListener.java
 * 
 * </pre>
 * @date : 2019. 7. 25. 오후 6:15:32
 * @version : 
 * @author : Seongcheol
 */
@Component
public class SessionExpiredListener implements ApplicationListener<SessionDestroyedEvent> {
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		String sessionId = event.getId();
		
		// 만료된 세션 값이 redis 있는지 확인
		CustomUserDetails user = redisSessionRepository.findBySession(sessionId);
		
		// redis 에 만료된 세션 정보 가 있을 경우 삭제
		if(user != null) {
			redisSessionRepository.delete(sessionId);
		}
	}

}

