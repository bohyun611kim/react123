package kr.co.coinis.webserver.framework.core.client.provider.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 
 * <pre>
 * 1.  기능		
 *   - JSON 파싱 관련 유틸    
 * 2.  주의사항	
 *    -  
 * 3.  작성자/작성일	: kim/2018. 2. 20.
 * ====================================
 * - 수정자/수정일/수정내용 	 
    1)
 * ====================================
 * <p/>
 * @author		: kim
 * @version		: v1.0
 * @see			: 
 * @since		: J2EE 1.7
 * </pre>
 */
public class JsonParserUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonParserUtil.class);
	
	public HashMap<String,Object> dataMap;
	
	public JsonParserUtil() {}
	
	public JsonParserUtil(HashMap<String,Object> dataMap) {
		this.dataMap =dataMap;
	}
	
	public HashMap<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(HashMap<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
//	public static HashMap<String,Object> dataMap=new HashMap<String,Object>();
//	
//	/**
//	 * 
//	 * <pre>
//	 * 1. 기능             
//	 *  - 데이타 get
//	 * 2. 작성자/작성일: kim/2018. 2. 20.
//	 * ====================================
//	 * - 수정자/수정일/수정내용
//	 * ----------------------------------
//	    1) 
//	 * ====================================
//	 * <p/>
//	 * 
//	 * @return
//	 * </pre>
//	 */
//	public static HashMap<String, Object> getDataMap() {
//		return dataMap;
//	}

//	/**
//	 * 
//	 * <pre>
//	 * 1. 기능             
//	 *  - 데이터 set
//	 * 2. 작성자/작성일: kim/2018. 2. 20.
//	 * ====================================
//	 * - 수정자/수정일/수정내용
//	 * ----------------------------------
//	    1) 
//	 * ====================================
//	 * <p/>
//	 * 
//	 * @param dataMap
//	 * </pre>
//	 */
//	public static void setDataMap(HashMap<String, Object> dataMap) {
//		JsonParserUtil.dataMap = dataMap;
//	}
//	
//	/**
//	 * <pre>
//	 * 1.  기능		: 요청 JSON validator 
//	 * 2.  처리개요	: <p>
//	 * - 전문표준에 위배 되었을 경우 오류 처리 <p>
//	 * 3.  주의사항	: (작성요) <p>
//	 * ====================================
//	 * 4.  작성자/작성일	:  kim/2018. 2. 20.
//	 * ====================================
//	 * 5.  수정사항
//	 * 5.1 요구사항 ID	:
//	 * - 수정자/수정일 	: 
//	 * - 수정사유/내역 	:
//	 * ====================================
//	 * <p/>
//	 * @param src
//	 * @param dist
//	 * @return
//	 * @throws Exception
//	 * </pre> 
//	 */
//	public static String isPacket(Object src,Object dist) throws Exception{
//	    String src_json = JSONUtils.jsonMashall(src);
//        String dt_json = JSONUtils.jsonMashall(dist);
//        
//        /** Object를 JSON으로 치환하여 field 항목을 찾기위해서 다시 Map에 담는다. **/
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> src_map = new HashMap<String, Object>();
//        Map<String, Object> dist_map = new HashMap<String, Object>();
//
//        src_map = mapper.readValue(src_json, new TypeReference<Map<String, String>>() {});
//        dist_map = mapper.readValue(dt_json, new TypeReference<Map<String, String>>() {});
//        
//        if(CommonUtil.nvl(dist_map).equals("")){
//            logger.debug("요청한 전문이 없습니다."); 
//            return "9999 요청한 전문이 없습니다.";
//        }
//        
//        if(CommonUtil.nvl(src_map).equals("")){
//            logger.debug("요청 전문이 없습니다.");
//            return "9999 요청 전문이 없습니다.";
//        }
//        
//        /** 요청 JSON 파라메타가 갯수가 틀릴 경우 오류 처리 **/
//        if(src_map.size() != dist_map.size()){
//            logger.debug("---------------잘못 요청한 전문입니다.---------------------------");
//            logger.debug("9999 잘못 요청한 전문입니다.  request :"+ src_map.size() +" ,fixed :"+ dist_map.size());
//            return "9999 잘못 요청한 전문입니다.  request :"+ src_map.size() +" ,fixed :"+ dist_map.size();
//        }
//      
//        Set key = dist_map.keySet();
//        String keyName="";
//        String result=""; 
//        for (Iterator iterator = key.iterator(); iterator.hasNext();) {
//            keyName = (String) iterator.next();
//            
//            /** 요청한 JSON에 표준 Key field가 있는지 check **/
//            if (src_map.containsKey(keyName)) {
//                logger.debug("field:"+keyName);
//                result="0000";
//                keyName="";
//            } else {
//                logger.debug("---------------잘못 요청한 전문입니다.---------------------------");
//                logger.debug("전문 위배 field: "+keyName);
//                result="9999"+keyName+" 전문 Field가 잘못 되었습니다. ";
//                break;
//            }
//        }
//        return result;
//	}

//	public static void main(String[] args) throws Exception {
//		dataMap.clear();
//		ArrayList productList=new ArrayList();
//		
//		Product product = new Product("Banana", "123", 23.00);
//		productList.add(product);
//	
//		Product product1=new Product("Bean", "223", 23.11111);
//		productList.add(product1);
//		  
//		ProductList item=new ProductList();
//		item.setItem("list Item");
//		item.setItemName("물건"); 
//		item.setCount(10);
//		item.setProductList(productList);
//
//		//marshall
//		String json=jsonMashall(item);
//		
//		//unmashal
//		jsonUnmashall(json);
//		
//		
//		Set key = dataMap.keySet();
//		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
//			String keyName = (String) iterator.next();
//			Object valueName =  (Object) dataMap.get(keyName);
//
//			if(valueName instanceof ArrayList){
//				Iterator iter=((ArrayList) valueName).iterator();
//				while(iter.hasNext()){
//					HashMap map=(HashMap)iter.next();
//					logger.debug("==>"+ map.get("id"));
//					logger.debug("==>"+ map.get("name"));
//					logger.debug("==>"+ map.get("price"));
//					logger.debug("=======================");
//				}
//			}else{
//				logger.debug(keyName + " = " + valueName);
//			}
//		}
//	
//	}
	
	
//	public static Object fromJsonToJava(String json, Class type) throws Exception {
//		ObjectMapper jsonMapper = new ObjectMapper();
//		return jsonMapper.readValue(json, type);
//	}
	
	


	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - JSON 파싱
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param obj
	 * @return
	 * </pre>
	 */
	public String jsonMashall(Object obj) {
		String json="";
		try {
			Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
			json=gson.toJson(obj);
//			logger.debug(json);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return json.trim();
	}
	
//	/** json 이쁘게 출력 **/
//	public static String toPrettyFormat(String jsonString) {
//		JsonParser parser = new JsonParser();
//		JsonObject json = parser.parse(jsonString).getAsJsonObject();
//
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String prettyJson = gson.toJson(json);
//		logger.info(prettyJson);
//		return prettyJson;
//	}
	
//	public static String jsonMashall(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
//		JsonHierarchicalStreamDriver jsondriver = new JsonHierarchicalStreamDriver() {
//			public HierarchicalStreamWriter createWriter(Writer writer) {
//				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
//			}
//		};
//		
//		XStream xstream = new XStream(jsondriver);
//		
//		xstream.autodetectAnnotations(true);
//		xstream.setMode(XStream.NO_REFERENCES);
//		xstream.alias(obj.getClass().getSimpleName(),obj.getClass());
//		xstream.aliasSystemAttribute(null, "class");
//		 
//		logger.debug(xstream.toXML(obj));
//		logger.debug("===================");
//		logger.debug(fromJavaToJson(obj));
//		return xstream.toXML(obj);
//	}
	
	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - JSON 객체 변환
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param json
	 * @throws Exception
	 * </pre>
	 */
	public void jsonUnmashall(String json) throws Exception {
	    dataMap.clear();
	    JSONParser jsonParser = new JSONParser();
	    JSONObject jsonObj=null;
	    try{
	    	jsonObj = (JSONObject)jsonParser.parse(json);	
	    	logger.debug("----------------START JSON -> OBJECT--------------");
	    }catch(Exception ext){
	    	json="{ \"errorCode\":\"500\",\"errorMsg\":\"Parser Error\"}";
	    	jsonObj = (JSONObject)jsonParser.parse(json);
	    }
	    printJsonObject(jsonObj);
	}
	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - JSON ArrayList,Object 객체를 key=value 쌍으로 분해 
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @param jsonObj
	 * </pre>
	 */
	public void printJsonObject(JSONObject jsonObj) {
		//dataMap.clear();
		for (Object key : jsonObj.keySet()) {
	        String keyStr = (String)key;
	        Object keyvalue = jsonObj.get(keyStr);
	   
	        if(keyvalue instanceof String){
	        	//logger.debug("String ::::  key: "+ keyStr + " value: " + keyvalue);
	        	dataMap.put(keyStr, keyvalue);
	        }
	        if(keyvalue instanceof Integer){
	        	//logger.debug("Integer ::::  key: "+ keyStr + " value: " + keyvalue);
	        	dataMap.put(keyStr, keyvalue);
	        }
	        if(keyvalue instanceof Long){
		       	//logger.debug("Integer ::::  key: "+ keyStr + " value: " + keyvalue);
		       	dataMap.put(keyStr, keyvalue);
	        }  	
	        if(keyvalue instanceof Double){
	        	//logger.debug("Double ::::  key: "+ keyStr + " value: " + keyvalue);
	        	dataMap.put(keyStr, keyvalue);
	        }
	        if (keyvalue instanceof JSONObject){
	        	//logger.debug("object ::::  key: "+ keyStr + " value: " + keyvalue);
	        	printJsonObject((JSONObject)keyvalue);
	        }else if(keyvalue instanceof JSONArray){
				ArrayList arrData=new ArrayList(); 
	        	JSONArray arrObject=(JSONArray) jsonObj.get(keyStr);
				Iterator iter= arrObject.iterator();
				while(iter.hasNext()){
					JSONObject itemData=(JSONObject)iter.next();
					HashMap arrMap =new HashMap();
					 String akey="";
					 Object avalue=null;
					for (Object okey : itemData.keySet()) {
					    akey = (String)okey;
				        avalue = itemData.get(akey);
				        //logger.debug("array ::::  key: "+ akey + " value: " + avalue);
				        if (avalue instanceof JSONObject)
				        	printJsonObject((JSONObject)avalue);
				        arrMap.put(akey, avalue);
				     }
					arrData.add(arrMap);
				}
				dataMap.put(keyStr, arrData);
			}
	    }
	    
	}
	
	/**
	 * <pre>
	 * 1.  기능		: 데이터 처리 결과값 converter 
	 * 2.  처리개요	: 
	 * - HashMap -> (Array)Bean으로 복사 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  kim/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * <p/>
	 * @param result
	 * @param bean
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * </pre>
	 */
//	public static ArrayList<Object> mapConvertArrayBean(HashMap<String,Object> result, java.lang.Object bean){
//		ArrayList<Object> arrData=new ArrayList<Object>();
//		try{
//			HashMap<String,Object> temp=new HashMap<String,Object>(); 
//			Set keySet = result.keySet();
//			for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
//				String key = (String) iterator.next();
//				Object value =  (Object) result.get(key);
//				
//				if(value instanceof ArrayList){
//					Iterator iter=((ArrayList) value).iterator();
//					while(iter.hasNext()){
//						HashMap map=(HashMap)iter.next();
//						BeanUtils.populate(bean, map);
//						arrData.add(bean);
//					}
//				}
//				else{
//					temp.put(key, value);
//				}
//			}
//			
//			arrData.add(temp);
//			
//			Iterator kiter=arrData.iterator();
//			while(kiter.hasNext()){
//				LoadVO vo=(LoadVO)kiter.next();
//				logger.debug("=============================");
//				logger.debug(vo.getNtep());
//				logger.debug(vo.getAprv_num());
//				logger.debug("=============================");
//			}
//				
//		}catch(Exception ext){
//			logger.error(ext.getMessage(),ext);
//		}
//		return arrData;
//	}
	
	/**
	 * <pre>
	 * 1.  기능		: 데이터 처리 결과값 converter 
	 * 2.  처리개요	: 
	 * - HashMap -> (Object)bean으로 복사 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  kim/2018. 2. 18.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * <p/>
	 * @param result
	 * @param bean
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * </pre>
	 */
//	public static void mapConvertObjectBean(HashMap<String,Object> result, Object bean){
//		try{
//			Set keySet = result.keySet();
//			Map<String,Object> data=new HashMap<String,Object>();
//			for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
//				String key = (String) iterator.next();
//				Object value =  (Object) result.get(key);
//				logger.debug(key + " = " + value);
//				
//				if(key.equals(""))
//				data.put(key, value);
//				
//			}
//			BeanUtils.populate(bean, result);
//		}catch(Exception ext){
//			logger.error(ext.getMessage(),ext);
//		}
//	}
}
 