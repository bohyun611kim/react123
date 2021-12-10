package kr.co.coinis.webserver.common.security.spring.security.session;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;

public interface RedisSessionRepository {
	// 기본 CRUD 구현 repository
	void save(CustomUserDetails userDetails);
	CustomUserDetails findBySession(String sessionId);
	CustomUserDetails findOneByUserId(String userId);
	List<CustomUserDetails> findByUserId(String userId);
	Map<String, CustomUserDetails> findAll();
	void update(CustomUserDetails userDetails);
	void delete(String sessionId);
	
	Map<String, String> userSessionList(String userId);
	void deleteUserSessionList(String userId, String sessionId);
	
	
	void put(String key, String value);
	String get(String key);
	
}
