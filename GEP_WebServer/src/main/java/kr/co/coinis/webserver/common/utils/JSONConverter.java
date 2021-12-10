package kr.co.coinis.webserver.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
/**
 * 
* <pre>
* 1.  기능		: json to Object, Object to json  변환
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 2. 13.
* ====================================
* 5.  수정사항
* 5.1 요구사항 ID	:
* - 수정자/수정일 	: 
* - 수정사유/내역 	:
* ====================================
* <p/>
* @author		: 김경주
* @since		: J2EE 1.8
* </pre>
 */
public class JSONConverter {
	/**
	 * 
	 * <pre>
	 * 1.  기능		: JSON 문자열을 Value Object 로 변환한다. 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 13.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: kangnaru/2018.03.26
	 * - 수정사유/내역 	: Json Object Mapper는 strict mode marshalling 이므로 json의 키값과 mapping object가 1:1
	 * 					 매핑이 되어야 converting이 된다. 따라서 unstrict mode가 지원되는 Gson으로 변경함.
	 * ====================================
	 * @param str
	 * @param objType
	 * @return
	 * </pre>
	 */
	public static <T> T stringToObject(String str, Class<T> objType) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			/*Gson gson = new GsonBuilder()
                            .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                            .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                            .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                            .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                            .registerTypeAdapter(float.class, new EmptyStringToNumberTypeAdapter())
                            .registerTypeAdapter(Float.class, new EmptyStringToNumberTypeAdapter())
                            .create();*/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setLenient();
			Gson gson = gsonBuilder.create();
			
			return gson.fromJson(str, objType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace(System.err);
			return null;
		}
	}
	/**
	 * 
	 * <pre>
	 * 1.  기능		: Value Object 를 JSON 문자열로 변환한다. 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 13.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param obj
	 * @return
	 * </pre>
	 */
	public static String objectToString(Object obj) {
		if (obj == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1.  기능		: JSON string의 Object 형변환
	 * 2.  처리개요	: Gson lib를 이용하여 형변환
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  kangnaru/2018. 3. 27.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param json : JSON String
	 * @param clazz : mapping object
	 * @return
	 * </pre>
	 */
	public static <T> T stringToJson(String json, Class<T> clazz) {
		if (json == null || json.equals("") || clazz == null) {
			return null;
		}

		try {
			return new Gson().fromJson(json, clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace(System.err);
			return null;
		}
	}
	
	/**
	 * 
	* <pre>
	* 1.  기능	:  Gson type casting할때 빈값을 Numeric으로 형변환할때 java.lang.NumberFormatException: empty String 에러방지를 위해 type adapter를 등록.
	* 2.  처리개요	: 
	* 3.  주의사항	:  
	* 4.  작성자/작성일	:  kangnaru/2018. 4. 12.
	* ====================================
	* 5.  수정사항
	* 5.1 요구사항 ID	:
	* - 수정자/수정일 	: 
	* - 수정사유/내역 	:
	* ====================================
	* <p/>
	* @author		: kangnaru
	* @since		: J2EE 1.8
	* </pre>
	 */
	public static class EmptyStringToNumberTypeAdapter extends TypeAdapter<Number> {
		@Override
		public void write(JsonWriter jsonWriter, Number number) throws IOException {
			if (number == null) {
				jsonWriter.nullValue();
				return;
			}
			jsonWriter.value(number);
		}

		@Override
		public Number read(JsonReader jsonReader) throws IOException {
			if (jsonReader.peek() == JsonToken.NULL) {
				jsonReader.nextNull();
				return null;
			}

			try {
				String value = jsonReader.nextString();
				if ("".equals(value)) {
					return 0;
				}
				return org.apache.commons.lang.math.NumberUtils.createNumber(value);
			} catch (NumberFormatException e) {
				throw new JsonSyntaxException(e);
			}
		}
	}
}
