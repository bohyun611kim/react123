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
 *    |_ InsertHistoryVO.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오후 9:09:49
 * @version : 
 * @author : Seongcheol
 */
public class InsertHistoryVO {

	private String exchangeId;
	private String userId;
	private int    reqOperCd;
	private int    resultCd;
	private String logContents;
	private String publicIp;
	private String os;
	private String privateIp;
	private String browser;
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
	public int getReqOperCd() {
		return reqOperCd;
	}
	public void setReqOperCd(int reqOperCd) {
		this.reqOperCd = reqOperCd;
	}
	public int getResultCd() {
		return resultCd;
	}
	public void setResultCd(int resultCd) {
		this.resultCd = resultCd;
	}
	public String getLogContents() {
		return logContents;
	}
	public void setLogContents(String logContents) {
		this.logContents = logContents;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getPrivateIp() {
		return privateIp;
	}
	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
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
