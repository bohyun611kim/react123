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
 *    |_ SendMailVO.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 3:48:02
 * @version : 
 * @author : Seongcheol
 */
public class SendMailVO {
	
	private String exchangeId;
	private String userId;
	private int    mailType;
	private String subject;
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
	public int getMailType() {
		return mailType;
	}
	public void setMailType(int mailType) {
		this.mailType = mailType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
