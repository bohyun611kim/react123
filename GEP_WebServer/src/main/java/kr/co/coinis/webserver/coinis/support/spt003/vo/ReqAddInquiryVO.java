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
 *    |_ ReqAddInquiry.java
 * 
 * </pre>
 * @date : 2019. 5. 9. 오전 11:27:20
 * @version : 
 * @author : J
 */
public class ReqAddInquiryVO {
	private String exchange_id;
	private String userid;
	private int inquiry_type_cd;
	private String inquiry_title;
	private String inquiry_contents;
	private int inquiry_no;
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
	public int getInquiry_type_cd() {
		return inquiry_type_cd;
	}
	public void setInquiry_type_cd(int inquiry_type_cd) {
		this.inquiry_type_cd = inquiry_type_cd;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}
	public String getInquiry_contents() {
		return inquiry_contents;
	}
	public void setInquiry_contents(String inquiry_contents) {
		this.inquiry_contents = inquiry_contents;
	}
    public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
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
