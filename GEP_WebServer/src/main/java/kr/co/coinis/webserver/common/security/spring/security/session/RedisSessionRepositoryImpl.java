package kr.co.coinis.webserver.common.security.spring.security.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;

@Repository
public class RedisSessionRepositoryImpl implements RedisSessionRepository {

	//private static final Logger logger = LoggerFactory.getLogger(RedisSessionRepositoryImpl.class);

	private static final String KEY = "GlobalWeb";
	private HashOperations<String, String, CustomUserDetails> hashOperations;
	private HashOperations<String, String, String> sessionOperations;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
		sessionOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void save(CustomUserDetails userDetails) {
		hashOperations.put(KEY, userDetails.getSessionId(), userDetails);
	}

	@Override
	public CustomUserDetails findBySession(String sessionId) {
		try {
			return hashOperations.get(KEY, sessionId);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<CustomUserDetails> findByUserId(String userId) {
		List<CustomUserDetails> userDetails = new ArrayList<CustomUserDetails>();
		
		Map<String, CustomUserDetails> userDetailMap = hashOperations.entries(KEY);
		for(String key : userDetailMap.keySet()) {
			CustomUserDetails user = hashOperations.get(KEY, key);
			if(user.getUserId() != null && user.getUserId().equalsIgnoreCase(userId)) {
				userDetails.add(user);
			}
		}
		return userDetails;
	}

	@Override
	public Map<String, CustomUserDetails> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void update(CustomUserDetails userDetails) {
		hashOperations.put(KEY, userDetails.getSessionId(), userDetails);
	}

	@Override
	public void delete(String sessionId) {
		hashOperations.delete(KEY, sessionId);
	}

	@Override
	public Map<String, String> userSessionList(String userId) {
		Map<String, String> sessionList = new HashMap<String, String>();
		Map<String, CustomUserDetails> userDetailMap = hashOperations.entries(KEY);
		for(String key : userDetailMap.keySet()) {
			CustomUserDetails user = hashOperations.get(KEY, key);
			if(user.getUserId() != null && user.getUserId().equalsIgnoreCase(userId)) {
				sessionList.put(key, key);
			}
		}

		return sessionList;
	}
	
	@Override
	public void deleteUserSessionList(String userId, String sessionId) {
		Map<String, CustomUserDetails> userDetailMap = hashOperations.entries(KEY);
		for(String key : userDetailMap.keySet()) {
			CustomUserDetails user = hashOperations.get(KEY, key);
			if(user.getUserId() != null && user.getUserId().equalsIgnoreCase(userId)) {
				if(!sessionId.equals(key)) {
					delete(key);
				}
			}
		}
	}
	
	@Override
	public CustomUserDetails findOneByUserId(String userId) {
		Map<String, CustomUserDetails> userDetailMap = hashOperations.entries(KEY);
		for(String key : userDetailMap.keySet()) {
			CustomUserDetails user = hashOperations.get(KEY, key);
			if(user.getUserId() != null && user.getUserId().equalsIgnoreCase(userId)) {
				return user;
			}
		}
		
		return null;
	}

	@Override
	public void put(String key, String value) {
		sessionOperations.put(KEY, key, value);
	}

	@Override
	public String get(String key) {
		return sessionOperations.get(KEY, key);
	}

}
