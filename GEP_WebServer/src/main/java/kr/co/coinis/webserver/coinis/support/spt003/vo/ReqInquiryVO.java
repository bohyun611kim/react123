/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.support.spt003.vo 
 *    |_ ReqInquiryVO.java
 * 
 * </pre>
 * @date : 2019. 5. 3. 오전 10:36:25
 * @version : 
 * @author : J
 */
public class ReqInquiryVO {

	private String exchange_id;
	private String userid;
	private int startIndex;
	private int queryDataNum;
	
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
