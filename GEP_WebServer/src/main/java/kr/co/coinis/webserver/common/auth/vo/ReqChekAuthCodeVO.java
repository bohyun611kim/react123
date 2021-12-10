/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.auth.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.auth.vo 
 *    |_ ReqChekAuthCodeVO.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 3:12:48
 * @version : 
 * @author : Seongcheol
 */
public class ReqChekAuthCodeVO {

	private String exchangeId;
	private String userId;
	private int    authPurposeCd;
	private int    authMeansCd;
	private String authMeansKeyVal;
	private String authCode;

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
	public int getAuthPurposeCd() {
		return authPurposeCd;
	}
	public void setAuthPurposeCd(int authPurposeCd) {
		this.authPurposeCd = authPurposeCd;
	}
	public int getAuthMeansCd() {
		return authMeansCd;
	}
	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}
	public String getAuthMeansKeyVal() {
		return authMeansKeyVal;
	}
	public void setAuthMeansKeyVal(String authMeansKeyVal) {
		this.authMeansKeyVal = authMeansKeyVal;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
