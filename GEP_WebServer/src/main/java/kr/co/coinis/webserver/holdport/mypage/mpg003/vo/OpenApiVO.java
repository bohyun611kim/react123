/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg003.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg003.vo
 *    |_ OpenApiVO.java
 *
 * </pre>
 * @date : 2019. 7. 11. 오후 4:59:38
 * @version :
 * @author : Seongcheol
 */
public class OpenApiVO {

	private String seqNo;
	private String clientId;
	private String secretKey;
	private String createDt;
	private int    activeYn;
	private String userApiNo;
	private int    rtnCd;
	private String rtnMsg;

	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public int getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(int activeYn) {
		this.activeYn = activeYn;
	}
	public String getUserApiNo() {
		return userApiNo;
	}
	public void setUserApiNo(String userApiNo) {
		this.userApiNo = userApiNo;
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
