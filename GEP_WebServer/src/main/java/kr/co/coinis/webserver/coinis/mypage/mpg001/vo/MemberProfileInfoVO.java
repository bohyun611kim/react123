/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg001.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg001.vo 
 *    |_ MemberInfOVO.java
 * 
 * </pre>
 * @date : 2019. 5. 10. 오후 4:01:58
 * @version : 
 * @author : yeonseoo
 */
public class MemberProfileInfoVO {
	
	private String user_id;
	private String auth_level;
	private double daily_limit_qty;
	private String coin_symbolic_nm;
	private int otp_yn;
	private int sms_use_yn;
	private int auth_means_cd;
	private int mrkt_agree_yn;
	private int fish_anti_code_yn;
	private String fish_anti_code_val;
	private String last_login_dt;
	private String last_login_app_ver;
	private String last_login_ipaddr;

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAuth_level() {
		return auth_level;
	}
	public void setAuth_level(String auth_level) {
		this.auth_level = auth_level;
	}
	public double getDaily_limit_qty() {
		return daily_limit_qty;
	}
	public void setDaily_limit_qty(double daily_limit_qty) {
		this.daily_limit_qty = daily_limit_qty;
	}
	public String getCoin_symbolic_nm() {
		return coin_symbolic_nm;
	}
	public void setCoin_symbolic_nm(String coin_symbolic_nm) {
		this.coin_symbolic_nm = coin_symbolic_nm;
	}
	public int getOtp_yn() {
		return otp_yn;
	}
	public void setOtp_yn(int otp_yn) {
		this.otp_yn = otp_yn;
	}
	public int getSms_use_yn() {
		return sms_use_yn;
	}
	public void setSms_use_yn(int sms_use_yn) {
		this.sms_use_yn = sms_use_yn;
	}
	public int getAuth_means_cd() {
		return auth_means_cd;
	}
	public void setAuth_means_cd(int auth_means_cd) {
		this.auth_means_cd = auth_means_cd;
	}
	public int getMrkt_agree_yn() {
		return mrkt_agree_yn;
	}
	public void setMrkt_agree_yn(int mrkt_agree_yn) {
		this.mrkt_agree_yn = mrkt_agree_yn;
	}
	public int getFish_anti_code_yn() {
		return fish_anti_code_yn;
	}
	public void setFish_anti_code_yn(int fish_anti_code_yn) {
		this.fish_anti_code_yn = fish_anti_code_yn;
	}
	public String getFish_anti_code_val() {
		return fish_anti_code_val;
	}
	public void setFish_anti_code_val(String fish_anti_code_val) {
		this.fish_anti_code_val = fish_anti_code_val;
	}
	public String getLast_login_dt() {
		return last_login_dt;
	}
	public void setLast_login_dt(String last_login_dt) {
		this.last_login_dt = last_login_dt;
	}
	public String getLast_login_app_ver() {
		return last_login_app_ver;
	}
	public void setLast_login_app_ver(String last_login_app_ver) {
		this.last_login_app_ver = last_login_app_ver;
	}
	public String getLast_login_ipaddr() {
		return last_login_ipaddr;
	}
	public void setLast_login_ipaddr(String last_login_ipaddr) {
		this.last_login_ipaddr = last_login_ipaddr;
	}

}
