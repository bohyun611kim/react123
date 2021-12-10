/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.vo;

import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.support.spt003.vo 
 *    |_ OneInquiryVO.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 6. 오후 4:31:56
 * @version :
 * @author : J
 */
public class OneInquiryVO {
	private String inquiry_type;
	private String inquiry_title;
	private String inquiry_contents;
	private String reg_dt;
	private List<CertifyNameVO> filename;
	private List<RepInquiryVO> answer;

	public String getInquiry_type() {
		return inquiry_type;
	}

	public void setInquiry_type(String inquiry_type) {
		this.inquiry_type = inquiry_type;
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

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public List<CertifyNameVO> getFilename() {
		return filename;
	}

	public void setFilename(List<CertifyNameVO> filename) {
		this.filename = filename;
	}

	public List<RepInquiryVO> getAnswer() {
		return answer;
	}

	public void setAnswer(List<RepInquiryVO> answer) {
		this.answer = answer;
	}

}
