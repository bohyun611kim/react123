/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.vo 
 *    |_ ReqNonContractVO.java
 * 
 * </pre>
 * @date : 2019. 4. 9. 오전 11:25:01
 * @version : 
 * @author : Seongcheol
 */
public class ReqNonContractVO {

	private String exchangeId;
	private String userId;
	private int	   pageNum;

	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
