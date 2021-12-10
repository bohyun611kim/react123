/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg002.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg002.vo 
 *    |_ ReqPwChangeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 3:52:39
 * @version : 
 * @author : J
 */
public class ReqChangePwVO {

	private String exchangeId;
	private String userID;
	@NotNull(message="비밀번호는 필수입니다")
	@Size(min=8, max=25, message="비밀번호는 8자리 이상 25자리 이하입니다")
	private String currentPW;
	@NotNull(message="신규 비밀번호는 필수입니다")
	@Size(min=8, max=25, message="비밀번호는 8자리 이상 25자리 이하입니다")
	private String newPW;
	@NotNull(message="신규 비밀번호 확인은 필수입니다")
	@Size(min=8, max=25, message="비밀번호는 8자리 이상 25자리 이하입니다")
	private String confirmnewPW;
	private int    rtnCd;
	private String rtnMsg;
	
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCurrentPW() {
		return currentPW;
	}
	public void setCurrentPW(String currentPW) {
		this.currentPW = currentPW;
	}
	public String getNewPW() {
		return newPW;
	}
	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}
	public String getConfirmnewPW() {
		return confirmnewPW;
	}
	public void setConfirmnewPW(String confirmnewPW) {
		this.confirmnewPW = confirmnewPW;
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
