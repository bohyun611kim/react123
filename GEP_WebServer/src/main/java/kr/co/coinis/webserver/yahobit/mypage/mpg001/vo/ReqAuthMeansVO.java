/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg001.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg001.vo 
 *    |_ ReqAuthMeansVO.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 6:33:12
 * @version : 
 * @author : Seongcheol
 */
public class ReqAuthMeansVO {

	private String exchangeId;
	private String userId;
	private int    type;
	@NotNull(message="-5050")
	private String os;
	@NotNull(message="-5050")
	private String browser;
	private String privateIp;
	@Size(min=6, max=6, message="-5064")
	private String authCode;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getPrivateIp() {
		return privateIp;
	}
	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
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
