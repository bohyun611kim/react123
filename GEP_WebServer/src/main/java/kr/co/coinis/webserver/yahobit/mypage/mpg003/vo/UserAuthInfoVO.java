/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg003.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg003.vo 
 *    |_ UserAuthInfoVO.java
 * 
 * </pre>
 * @date : 2019. 7. 12. 오후 4:40:09
 * @version : 
 * @author : Seongcheol
 */
public class UserAuthInfoVO {

	private int authMeansCd;
	private String mobile;
	private String otpKey;

	public int getAuthMeansCd() {
		return authMeansCd;
	}
	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtpKey() {
		return otpKey;
	}
	public void setOtpKey(String otpKey) {
		this.otpKey = otpKey;
	}
}
