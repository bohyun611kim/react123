/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr002.vo;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.vo 
 *    |_ ReqLoginVO.java
 * 
 * </pre>
 * @date : 2019. 4. 1. 오후 5:57:57
 * @version : 
 * @author : Seongcheol
 */
public class ReqLoginVO {

	private String exchangeId;
	@NotNull(message="이메일은 필수입니다")
	private String id;
	@NotNull(message="비밀번호는 필수입니다")
	private String pw;
	
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
