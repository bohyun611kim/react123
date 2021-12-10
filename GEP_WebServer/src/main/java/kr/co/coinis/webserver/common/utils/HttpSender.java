package kr.co.coinis.webserver.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSender {

	private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);

	public static HttpResponse postHttpRequest(String postUrl, String jsonMessage) throws Exception{


		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);

		StringEntity stringEntity = new StringEntity(jsonMessage, "UTF-8");

		post.setEntity(stringEntity);
		post.setHeader("Content-type", "application/json");
		post.setHeader("cache-control", "no-cache");

		//System.out.println("Request ProtocolVersion["+post.getProtocolVersion()+"], message["+post.toString()+"]");

		HttpResponse  response = httpClient.execute(post);

		return response;

	}

	public static boolean httpSmsRequest(String phoneNo, String message) throws Exception{

		boolean resultFlag=false;
		BufferedReader reader = null;

		try {

			String apiUrl="https://rest.surem.com/sms/v1/json";

			HttpClient   httpClient    = HttpClientBuilder.create().build();
			HttpPost     post          = new HttpPost(apiUrl);

			JSONObject jsonRequest = new JSONObject();

/*
			jsonRequest.put("usercode", "holdport7176");
			jsonRequest.put("deptcode", "U4-JN2-TM");
			jsonRequest.put("text", message);
			jsonRequest.put("from", "01083675850");
*/
			jsonRequest.put("usercode", "isms2021");
			jsonRequest.put("deptcode", "74-F2R-5Y");
			jsonRequest.put("text", message);
			jsonRequest.put("from", "18339078");

			JSONArray messageArray = new JSONArray();

			JSONObject innerJson = new JSONObject();
			innerJson.put("message_id", "1");
			innerJson.put("to", phoneNo);

			messageArray.add(innerJson);
			jsonRequest.put("messages", messageArray);

			logger.info("request json message["+ jsonRequest.toString()+"]");

			post.setHeader("Content-type", "application/json");
			post.setHeader("cache-control", "no-cache");

			post.setEntity(new StringEntity(jsonRequest.toString(), "UTF-8"));

			HttpResponse  response = httpClient.execute(post);

			logger.info("response["+response.toString()+"]");
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {

				StringBuffer resultBuffer = new StringBuffer();
				String inputLine = null;

				reader = new BufferedReader(new InputStreamReader((InputStream)response.getEntity().getContent(), "utf-8"));

				while((inputLine = reader.readLine()) != null) {
					resultBuffer.append(inputLine);
				}

				logger.info("response json message["+ resultBuffer.toString()+"]");

				JSONParser parser = new JSONParser();
				JSONObject jsonResponse = (JSONObject)parser.parse( resultBuffer.toString() );

				String code = (String) jsonResponse.get("code");

				if(code.equals(String.valueOf(HttpStatus.SC_OK))) {
						resultFlag=true;
				}

			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			resultFlag=false;
		} catch (IOException e) {
			e.printStackTrace();
			resultFlag=false;
		}finally {
			if(reader!=null)reader.close();
		}

		return resultFlag;

	}

	public static void main(String [] args) throws Exception {

		//String apiUrl="https://rest.surem.com/intl/text";
		String apiUrl="https://rest.surem.com/sms/v1/json";

		JSONObject json = new JSONObject();

		String message="[VITA500] 본인확인 인증번호는 8888 입니다.";

		json.put("usercode", "holdport7176");
		json.put("deptcode", "U4-JN2-TM");
		json.put("text", message);
		json.put("from", "01083675850");

		JSONArray messageArray = new JSONArray();

		JSONObject innerJson = new JSONObject();
		innerJson.put("message_id", "1");
		innerJson.put("to", "01082173213");

		messageArray.add(innerJson);

		json.put("messages", messageArray);

		//System.out.println("request json message["+ json.toString()+"]");

		//HttpResponse response = postHttpRequest(apiUrl,  json.toString());
		//System.out.println("Response message["+response.toString()+"]");

		httpSmsRequest("01082173213","8887");

		return;
	}

}
