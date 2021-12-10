/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.wallet.wlt003.vo 
 *    |_ ReqHoldingsVO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 2:26:01
 * @version : 
 * @author : yeonseoo
 */
public class ReqHoldingsVO {
	
	private String exchange_id;
	private String user_id;
	private int startIndex;
	private int queryDataNum;
	
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
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getQueryDataNum() {
		return queryDataNum;
	}
	public void setQueryDataNum(int queryDataNum) {
		this.queryDataNum = queryDataNum;
	}

}
