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
 *    |_ AuthCodeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 1:24:01
 * @version : 
 * @author : Seongcheol
 */
public class AuthCodeVO {

	private String exchangeId;
	private String userId;
	private int    authPurposeCd;
	private int    authMeansCd;
	private String authMeansKeyVal;
	private int    expireSec;
	private int    tranKeyVal;
	private String encryptAuthCd;
	private String authCd;
	private int    rtnCd;
	private String rtnMsg;

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
	public int getExpireSec() {
		return expireSec;
	}
	public void setExpireSec(int expireSec) {
		this.expireSec = expireSec;
	}
	public int getTranKeyVal() {
		return tranKeyVal;
	}
	public void setTranKeyVal(int tranKeyVal) {
		this.tranKeyVal = tranKeyVal;
	}
	public String getEncryptAuthCd() {
		return encryptAuthCd;
	}
	public void setEncryptAuthCd(String encryptAuthCd) {
		this.encryptAuthCd = encryptAuthCd;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public int getRtnCd() {
		return rtnCd;
	}
	public void setRtnCd(int rtnCd) {
		this.rtnCd = rtnCd;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
}
