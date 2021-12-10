/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.member.mbr002.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.member.mbr002.vo
 *    |_ AuthInfoVO.java
 *
 * </pre>
 * @date : 2019. 5. 20. 오후 9:00:43
 * @version :
 * @author : Seongcheol
 */
public class AuthInfoVO {

	private String mobile;
	private String otpKeyVal;
	private int    authMeansCd;

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtpKeyVal() {
		return otpKeyVal;
	}
	public void setOtpKeyVal(String otpKeyVal) {
		this.otpKeyVal = otpKeyVal;
	}
	public int getAuthMeansCd() {
		return authMeansCd;
	}
	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}
}
