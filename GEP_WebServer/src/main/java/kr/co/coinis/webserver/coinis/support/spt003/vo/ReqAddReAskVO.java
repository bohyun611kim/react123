/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.support.spt003.vo 
 *    |_ ReqAddReAsk.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 8. 오후 1:54:31
 * @version :
 * @author : J
 */
public class ReqAddReAskVO {
	private String exchange_id;
	private String userid;
	private int inquiry_no;
	private int reply_no;
	private String reply_contents;

	private int rtnCd;
	private String rtnMsg;

	public String getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getInquiry_no() {
		return inquiry_no;
	}

	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getReply_contents() {
		return reply_contents;
	}

	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
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
