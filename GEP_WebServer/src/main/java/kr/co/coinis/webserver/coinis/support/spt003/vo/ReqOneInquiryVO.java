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
 *    |_ ReqOneInquiryVO.java
 * 
 * </pre>
 * @date : 2019. 5. 10. 오전 10:28:02
 * @version : 
 * @author : J
 */
public class ReqOneInquiryVO {
	private String exchange_id;
	private String userid;
	private int inquiry_no;
	private String code_id;
	private String lang_cd;

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
	public Integer getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(Integer inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getLang_cd() {
		return lang_cd;
	}
	public void setLang_cd(String lang_cd) {
		this.lang_cd = lang_cd;
	}
}
