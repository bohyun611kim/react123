/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr002.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.vo 
 *    |_ ReqLoginVO.java
 * 
 * </pre>
 * @date : 2019. 4. 1. 오후 5:57:57
 * @version : 
 * @author : Seongcheol
 */
public class ReqLoginVO {

	private String exchangeId;
	@NotNull(message="-5046")
	@Size(max=40, message="-5045")
	private String id;
	@NotNull(message="-5047")
	private String pw;
	private String publicIp;
	private String appInfo;
	private String os;
	private String privateIp;
	private String response;
	
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public String getAppInfo() {
		return appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
