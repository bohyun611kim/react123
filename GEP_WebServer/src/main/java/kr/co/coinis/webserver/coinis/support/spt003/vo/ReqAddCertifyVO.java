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
 *    |_ ReqAddCertifyVO.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 13. 오후 1:30:58
 * @version :
 * @author : J
 */
public class ReqAddCertifyVO {
	private String exchange_id;
	private String userid;
	private int inquiry_no;
	private int reply_no;

	private String attach_file_nm;
	private String saved_file_nm;

	private int    rtnCd;
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

	public String getAttach_file_nm() {
		return attach_file_nm;
	}

	public void setAttach_file_nm(String attach_file_nm) {
		this.attach_file_nm = attach_file_nm;
	}

	public String getSaved_file_nm() {
		return saved_file_nm;
	}

	public void setSaved_file_nm(String saved_file_nm) {
		this.saved_file_nm = saved_file_nm;
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
