/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg003.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg003.vo 
 *    |_ SecuritySetStatusVO.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오후 2:56:44
 * @version : 
 * @author : yeonseoo
 */
public class SecuritySetStatusVO {

	private String tel_cd;
	private int sms_use_yn;
	private int otp_yn;
	private String reg_dt;
	private String mobile;
	private String otp_key_val;
	
	public String getTel_cd() {
		return tel_cd;
	}
	public void setTel_cd(String tel_cd) {
		this.tel_cd = tel_cd;
	}
	public int getSms_use_yn() {
		return sms_use_yn;
	}
	public void setSms_use_yn(int sms_use_yn) {
		this.sms_use_yn = sms_use_yn;
	}
	public int getOtp_yn() {
		return otp_yn;
	}
	public void setOtp_yn(int otp_yn) {
		this.otp_yn = otp_yn;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp_key_val() {
		return otp_key_val;
	}
	public void setOtp_key_val(String otp_key_val) {
		this.otp_key_val = otp_key_val;
	}
}
