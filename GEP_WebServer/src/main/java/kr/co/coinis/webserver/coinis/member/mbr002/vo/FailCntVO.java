/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.member.mbr002.vo 
 *    |_ FailCntVO.java
 * 
 * </pre>
 * @date : 2019. 8. 14. 오후 3:27:28
 * @version : 
 * @author : Seongcheol
 */
public class FailCntVO {

	private String procFlag;
	private String exchangeId;
	private String userId;
	private int    rtnCd;
	private String rtnMsg;

	public String getProcFlag() {
		return procFlag;
	}
	public void setProcFlag(String procFlag) {
		this.procFlag = procFlag;
	}
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
