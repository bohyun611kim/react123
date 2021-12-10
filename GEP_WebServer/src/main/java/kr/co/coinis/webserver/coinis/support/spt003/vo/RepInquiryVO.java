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
 *    |_ RepInquiryVO.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 3. 오후 6:00:18
 * @version :
 * @author : J
 */
public class RepInquiryVO {
	private String replycontents;
	private int usertype;
	private String reg_dt;
	private List<CertifyNameVO> filename;

	public String getReplycontents() {
		return replycontents;
	}

	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
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
}
