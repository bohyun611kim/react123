/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ SendSmsVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오후 3:38:47
 * @version : 
 * @author : kangn
 */
public class SendSmsVO {

	private String exchangeId;
	private String userId;
	private String to;
	private String msg;
	private String msgOption;
	
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
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgOption() {
		return msgOption;
	}
	public void setMsgOption(String msgOption) {
		this.msgOption = msgOption;
	}	

}
