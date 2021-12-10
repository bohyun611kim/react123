/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg003.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg003.vo
 *    |_ CreateApiVO.java
 *
 * </pre>
 * @date : 2019. 7. 12. 오후 3:32:43
 * @version :
 * @author : Seongcheol
 */
public class CreateApiVO {

	private String exchangeId;
	private String userId;
	@NotNull(message="-5083")
	private String apiNo;
	@Size(min=0, max=15, message="-5082")
	private String accessIp;
	@NotNull(message="-5064")
	private String authCode;
	private String clientId;
	private String secretKey;

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
	public String getApiNo() {
		return apiNo;
	}
	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
