/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.vo 
 *    |_ ReqUserInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 22. 오전 9:51:04
 * @version : 
 * @author : Seongcheol
 */
public class ReqUserInfoVO {

	private String exchangeId;
	private String userId;

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
}
