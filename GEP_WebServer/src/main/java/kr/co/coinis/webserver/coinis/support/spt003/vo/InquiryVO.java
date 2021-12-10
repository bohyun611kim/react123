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
 *    |_ InquiryList.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 5:55:12
 * @version : 
 * @author : Jungjea
 */
public class InquiryVO {
	private Integer inquiry_no;
	private String inquiry_title;
	private String reg_dt;
	private String rep_reg_dt;
	private int usertype;
	
	public Integer getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(Integer inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getRep_reg_dt() {
		return rep_reg_dt;
	}
	public void setRep_reg_dt(String rep_reg_dt) {
		this.rep_reg_dt = rep_reg_dt;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
}
