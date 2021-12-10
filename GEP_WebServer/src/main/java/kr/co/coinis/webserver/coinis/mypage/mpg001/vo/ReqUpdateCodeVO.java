/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg001.vo 
 *    |_ ReqUpdateAuthMeansCDVO.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오전 10:09:45
 * @version : 
 * @author : yeonseoo
 */
public class ReqUpdateCodeVO {

	private String exchange_id;
	private String user_id;
	private int code;
	
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
