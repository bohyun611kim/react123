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
 *    |_ ReqAuthLevelVO.java
 *
 * </pre>
 * @date : 2019. 5. 13. 오후 7:03:47
 * @version :
 * @author : Seongcheol
 */
public class ReqAuthLevelVO {

	private String exchangeId;
	private String userId;
	private int    authLevel;
	private String mobile;
	private String userName;
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
	public int getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
