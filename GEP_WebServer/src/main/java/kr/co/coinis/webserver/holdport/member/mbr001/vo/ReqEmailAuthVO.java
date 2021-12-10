/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.vo
 *    |_ ReqEmailAuthVO.java
 *
 * </pre>
 * @date : 2019. 4. 24. 오전 11:21:49
 * @version :
 * @author : Seongcheol
 */
public class ReqEmailAuthVO {

	private String exchangeId;
	private String userId;
	private String key;
	private int    memberStatCd;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getMemberStatCd() {
		return memberStatCd;
	}
	public void setMemberStatCd(int memberStatCd) {
		this.memberStatCd = memberStatCd;
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
