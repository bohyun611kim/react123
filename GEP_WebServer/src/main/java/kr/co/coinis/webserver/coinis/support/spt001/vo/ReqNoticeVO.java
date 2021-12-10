/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.vo 
 *    |_ ReqNoticeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 5:13:36
 * @version : 
 * @author : Seongcheol
 */
public class ReqNoticeVO {

	private String exchangeId;
	private int    pageNum;

	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
