/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg001.vo
 *    |_ ReqMarketingAgreeVO.java
 *
 * </pre>
 * @date : 2019. 4. 26. 오후 6:07:11
 * @version :
 * @author : Seongcheol
 */
public class ReqMarketingAgreeVO {

	private String exchangeId;
	private String userId;
	private int	   agree;

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
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
}
