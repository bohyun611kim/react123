/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.auth.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.vo 
 *    |_ ReqinsertAuthCodeVO.java
 * 
 * </pre>
 * @date : 2019. 3. 27. 오후 2:38:12
 * @version : 
 * @author : Seongcheol
 */
public class ReqInsertAuthCodeVO {

	@NotNull
	@Size(max=20)
	private String exchangeId;
	@NotNull
	@Email(message="wrong email address")
	@Size(max=40)
	private String userId;
	@NotNull
	private int    authPurposeCd;
	@NotNull
	private int    authMeansCd;
	@NotNull
	@Size(max=65)
	private String authMeansKeyVal;
	@NotNull
	private String authCode;
	@NotNull
	private int    expireSec;
	private String trandsactionKeyVal;
	
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
	public int getExpireSec() {
		return expireSec;
	}
	public void setExpireSec(int expireSec) {
		this.expireSec = expireSec;
	}
	public String getTrandsactionKeyVal() {
		return trandsactionKeyVal;
	}
	public void setTrandsactionKeyVal(String trandsactionKeyVal) {
		this.trandsactionKeyVal = trandsactionKeyVal;
	}
}
