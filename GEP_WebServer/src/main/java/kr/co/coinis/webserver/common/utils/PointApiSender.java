package kr.co.coinis.webserver.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointApiSender {

	public static final String SERVICE_SUCCESS="1";
	public static final String SERVICE_FAIL="2";

	public static final String EMPTY_DATA="";

	private static final Logger logger = LoggerFactory.getLogger(PointApiSender.class);

	public static HashMap<String, String> httpGetMemberToken(String postUrl, HashMap<String, String> requestData) throws Exception{

		BufferedReader reader = null;

		HashMap<String, String> resultData = new HashMap<String, String>();

		try {

			HttpClient   httpClient    = HttpClientBuilder.create().build();
			HttpPost     httpPost          = new HttpPost(postUrl);

			List<NameValuePair> form = new ArrayList<>();

	        form.add(new BasicNameValuePair("TradeType", (String)requestData.get("TradeType")));
	        form.add(new BasicNameValuePair("UserID",  (String)requestData.get("UserID")));
	        form.add(new BasicNameValuePair("UserPwd",  (String)requestData.get("UserPwd")));
	        form.add(new BasicNameValuePair("TradeTime",  (String)requestData.get("TradeTime")));

	        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
	        httpPost.setEntity(entity);

	        HttpResponse response = httpClient.execute(httpPost);

        	logger.debug("response message["+response.toString()+"]");

    	 	Header[] headers = response.getAllHeaders();
       	    for (Header header : headers) {
       	    	//logger.debug("Key: " + header.getName()+" ,Value : " + header.getValue());
       	    }

			 int statusCode = response.getStatusLine().getStatusCode();

			 if (statusCode >= HttpStatus.SC_OK && statusCode < 300 ) {

		        	String responseBody = EntityUtils.toString(response.getEntity());
		       	    logger.debug("Reponse Entity["+responseBody+"]");

				 if(responseBody.substring(0,2).equals("00") ) {
					 resultData.put("resultCode", SERVICE_SUCCESS);
					 resultData.put("resultMsg", "success");
					 resultData.put("resultToken", responseBody.substring(2));

				 }else if(responseBody.substring(0,2).equals("XX")){
					 resultData.put("resultCode", SERVICE_FAIL);
					 resultData.put("resultMsg", responseBody.substring(2));
					 resultData.put("resultToken", EMPTY_DATA);
				 }else {
					 resultData.put("resultCode", SERVICE_FAIL);
					 resultData.put("resultMsg", "invalid response code");
					 resultData.put("resultToken", EMPTY_DATA);
				 }

			 } else {
				 resultData.put("resultCode", SERVICE_FAIL);
				 resultData.put("resultMsg", "http response code error");
				 resultData.put("resultToken", EMPTY_DATA);
			 }

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			resultData.put("resultCode", SERVICE_FAIL);
			 resultData.put("resultMsg", e.getMessage());
			resultData.put("resultToken", EMPTY_DATA);
		} catch (IOException e) {
			e.printStackTrace();
			resultData.put("resultCode", SERVICE_FAIL);
			resultData.put("resultMsg", e.getMessage());
			resultData.put("resultToken", EMPTY_DATA);
		}finally {
			if(reader!=null)reader.close();
		}
		return resultData;
	}

	public static HashMap<String, String> httpPointConvertRequest(String postUrl, HashMap<String, String> requestData) throws Exception{

		BufferedReader reader = null;
		HashMap<String, String> resultData = new HashMap<String, String>();

		try {

			HttpClient   httpClient    = HttpClientBuilder.create().build();
			HttpPost     httpPost          = new HttpPost(postUrl);

			List<NameValuePair> form = new ArrayList<>();

	        form.add(new BasicNameValuePair("TradeType", (String)requestData.get("TradeType")));
	        form.add(new BasicNameValuePair("UserID",  (String)requestData.get("UserID")));
	        form.add(new BasicNameValuePair("UserPwd",  (String)requestData.get("UserPwd")));
	        form.add(new BasicNameValuePair("TradeTime",  (String)requestData.get("TradeTime")));
	        form.add(new BasicNameValuePair("UserKey",  (String)requestData.get("UserKey")));
	        form.add(new BasicNameValuePair("TradePoint",  (String)requestData.get("TradePoint")));

	        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

	        httpPost.setEntity(entity);

	        HttpResponse response = httpClient.execute(httpPost);

        	logger.debug("response message["+response.toString()+"]");

        	Header[] headers = response.getAllHeaders();
       	    for (Header header : headers) {
       	    	//logger.debug("Key: " + header.getName()+" ,Value : " + header.getValue());
       	    }
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode >= HttpStatus.SC_OK && statusCode < 300 ) {

		        	String responseBody = EntityUtils.toString(response.getEntity());
		       	    logger.debug("Reponse Entity["+responseBody+"]");

				 if(responseBody.substring(0,2).equals("00") ) {
					 resultData.put("resultCode", SERVICE_SUCCESS);
					 resultData.put("resultMsg", responseBody.substring(2));
				 }else if(responseBody.substring(0,2).equals("XX")){
					 resultData.put("resultCode", SERVICE_FAIL);
					 resultData.put("resultMsg", responseBody.substring(2));

				 }else {
					 resultData.put("resultCode", SERVICE_FAIL);
					 resultData.put("resultMsg", "invalid response code");
				 }

			 } else {
				 resultData.put("resultCode", SERVICE_FAIL);
				 resultData.put("resultMsg", "http response code error");
			 }

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			resultData.put("resultCode", SERVICE_FAIL);
			resultData.put("resultMsg", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			resultData.put("resultCode", SERVICE_FAIL);
			resultData.put("resultMsg", e.getMessage());
		}finally {
			if(reader!=null)reader.close();
		}
		return resultData;
	}

	public static void main(String [] args) throws Exception {

		long beginTime=0, endTime=0;

		String userID="test";
		String userPwd="1111";
		String encryptKey="6058644588581810";

		String apiUrl="http://dibapayad.joy365.kr/External/CoinTrade.asp";

		HashMap<String, String> requestData = new HashMap<String, String>();
		HashMap<String, String> resultData = new HashMap<String, String>();

		beginTime=System.currentTimeMillis();

		// 1. 회원정보연동
		requestData.put("TradeType", "0101");

		requestData.put("UserID", CryptProvider.getAesEncryptData(userID, encryptKey, null));
		//requestData.put("UserID", "zqHZYA/mXaChStN68JVsEg==");

		requestData.put("UserPwd", CryptProvider.getAesEncryptData(userPwd, encryptKey, null));
		//requestData.put("UserPwd", "wku01TeGHCjuRDbPCgZtMw==");

		requestData.put("TradeTime", DateUtils.getSystemDate(0));

		logger.debug("request["+requestData.toString()+"]");
		resultData=httpGetMemberToken(apiUrl, requestData);
		logger.debug("result["+resultData.toString()+"]");


		//2. 포인트 전환
		HashMap<String, String> pointConvertData = new HashMap<String, String>();

		pointConvertData.put("TradeType", "0202");
		pointConvertData.put("UserID", CryptProvider.getAesEncryptData(userID, encryptKey, null));
		pointConvertData.put("UserPwd", CryptProvider.getAesEncryptData(userPwd, encryptKey, null));
		pointConvertData.put("TradeTime",  DateUtils.getSystemDate(0));

		pointConvertData.put("UserKey", resultData.get("resultToken"));
		//pointConvertData.put("UserKey", "ADAC171E9B487A25BEC2DB3F5C87E9E43DC2F7AF9841A261E82F63F70E445923B1604EE01A33B985D256104BFA8DB29D");

		pointConvertData.put("TradePoint", "10000");//KRW

		resultData=httpPointConvertRequest(apiUrl, pointConvertData);

		logger.debug("result["+resultData.toString()+"]");

		endTime=System.currentTimeMillis();

		logger.debug("time["+(endTime-beginTime)+"]ms");

		return;

	}

}
