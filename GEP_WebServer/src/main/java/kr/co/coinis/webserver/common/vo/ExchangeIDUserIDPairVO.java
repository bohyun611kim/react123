/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ ExchangeIDUserIDPairVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오전 10:51:59
 * @version : 
 * @author : yeonseoo
 */
public class ExchangeIDUserIDPairVO {

	private String exchange_id;
	private String user_id;
	
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
}
