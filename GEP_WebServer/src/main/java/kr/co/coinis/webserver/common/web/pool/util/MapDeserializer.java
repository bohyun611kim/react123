/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.web.pool.util; 

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Function;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * <pre>
 * kr.co.coinis.webserver.common.wrts.pool.util 
 *    |_ MapDeserializer.java
 * 
 * </pre>
 * @date : 2018. 5. 28. 오전 10:13:57
 * @version : 
 * @author : kangn
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapDeserializer implements JsonDeserializer<Map> {
	@Override
	public HashMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		Entry entry = obj.entrySet().iterator().next();
		HashMap resultMap = new HashMap();
		resultMap.put(entry.getKey(), ParseObjectFromElement.SINGLETON.apply(entry.getValue()));
		return resultMap;
	}

	public enum ParseObjectFromElement implements Function {
		SINGLETON;
		@Override
		public Object apply(Object input) {
			Object value = null;
			if (input == null || ((JsonElement) input).isJsonNull()) {
				value = null;
			} else if (((JsonElement) input).isJsonPrimitive()) {
				JsonPrimitive primitive = ((JsonElement) input).getAsJsonPrimitive();
				if (primitive.isNumber()) {
					value = primitive.getAsBigDecimal();
					//value = primitive.getAsNumber();
				} else if (primitive.isBoolean()) {
					value = primitive.getAsBoolean();
				} else {
					value = primitive.getAsString();
				}
			} else if (((JsonElement) input).isJsonArray()) {
				value = ((JsonElement) input).getAsJsonArray() ;
			} else if (((JsonElement) input).isJsonObject()) {
				value = ((JsonElement) input).getAsJsonObject();
			}
			return value;
		}
	}

	public enum JsonObjectAsMap implements Function {
		INSTANCE;

		private final Field members;

		JsonObjectAsMap() {
			try {
				members = JsonObject.class.getDeclaredField("members");
				members.setAccessible(true);
			} catch (NoSuchFieldException e) {
				throw new UnsupportedOperationException("cannot access gson internals", e);
			}
		}

		@Override
		public Map apply(Object in) {
			try {
				return (Map) members.get(in);
			} catch (IllegalArgumentException e) {
				throw new UnsupportedOperationException("cannot access gson internals", e);
			} catch (IllegalAccessException e) {
				throw new UnsupportedOperationException("cannot access gson internals", e);
			}
		}
	}
}
