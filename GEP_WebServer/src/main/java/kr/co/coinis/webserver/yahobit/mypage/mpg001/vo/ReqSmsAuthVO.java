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
 * kr.co.coinis.webserver.yahobit.mypage.mpg001.vo 
 *    |_ ReqSmsAuthVO.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오후 7:31:33
 * @version : 
 * @author : Seongcheol
 */
public class ReqSmsAuthVO {

	private String exchangeId;
	private String userId;
	private int    smsUseYn;
	private String mobile;
	private String key2;
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
	public int getSmsUseYn() {
		return smsUseYn;
	}
	public void setSmsUseYn(int smsUseYn) {
		this.smsUseYn = smsUseYn;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
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
