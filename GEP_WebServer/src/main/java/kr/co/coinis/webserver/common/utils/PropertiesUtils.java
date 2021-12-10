package kr.co.coinis.webserver.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

public class PropertiesUtils {
	
	/**
	 * 입력 된 문자열을 포함하는 다국어 데이터를 json 형태로 반환 
	 * @param likeString
	 * @param locale
	 * @return
	 * @throws IOException
	 */
	public static String getPropJson(String likeString, Locale locale) throws IOException {
		Properties prop = new Properties();
		
		prop.load(PropertiesUtils.class.getResourceAsStream("/conf/message/messages_" + locale.getLanguage() + ".properties"));
		
		HashMap<String, Object> map = new HashMap<>();
		
		for(final String name: prop.stringPropertyNames()) {
			if(name.indexOf(likeString) != -1) {
				map.put(name, prop.getProperty(name));
			}
		}
		
		return JSONConverter.objectToString(map);
	}
	
	/**
	 * 입력 된 문자열을 포함하는 다국어 데이터를 HashMap 형태로 반환 
	 * @param likeString
	 * @param locale
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String, Object> getPropHashMap(String likeString, Locale locale) throws IOException {
		Properties prop = new Properties();
		
		prop.load(PropertiesUtils.class.getResourceAsStream("/conf/message/messages_" + locale.getLanguage() + ".properties"));
		
		HashMap<String, Object> map = new HashMap<>();
		
		for(final String name: prop.stringPropertyNames()) {
			if(name.indexOf(likeString) != -1) {
				map.put(name, prop.getProperty(name));
			}
		}
		
		return map;
	}
	
	/**
	 * 입력 된 key에 해당하는 다국어 value 값을 반환 
	 * @param key
	 * @param locale
	 * @return
	 * @throws IOException
	 */
	public static String getProperties(String key, Locale locale) throws IOException {
		Properties prop = new Properties();
		
		prop.load(PropertiesUtils.class.getResourceAsStream("/conf/message/messages_" + locale.getLanguage() + ".properties"));
		
		return prop.getProperty(key, "");
	}
	
}
