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
 *    |_ InquiryCodeVO.java
 * 
 * </pre>
 * @date : 2019. 5. 9. 오전 10:00:12
 * @version : 
 * @author : J
 */
public class InquiryCodeVO {
	private String code_id;
	private String lang_cd;
	private String code_val_nm;
	private int code_num_val;
	
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
	public String getCode_val_nm() {
		return code_val_nm;
	}
	public void setCode_val_nm(String code_val_nm) {
		this.code_val_nm = code_val_nm;
	}
	public int getCode_num_val() {
		return code_num_val;
	}
	public void setCode_num_val(int code_num_val) {
		this.code_num_val = code_num_val;
	}
}
