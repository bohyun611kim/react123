/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.vo 
 *    |_ ReqJoinCrowdSaleVO.java
 * 
 * </pre>
 * @date : 2019. 6. 15. 오후 2:34:05
 * @version : 
 * @author : Seongcheol
 */
public class ReqJoinCrowdSaleVO {

	private String exchangeId;
	private String userId;
	@NotNull(message="-5050")
	private int    no;
	@NotNull(message="-5050")
	private int    type;
	@NotNull(message="-5050")
	private int    slType;
	@NotNull(message="-5065")
	private double qty;
	private double slQty;
	@NotNull(message="-5064")
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
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSlType() {
		return slType;
	}
	public void setSlType(int slType) {
		this.slType = slType;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getSlQty() {
		return slQty;
	}
	public void setSlQty(double slQty) {
		this.slQty = slQty;
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
