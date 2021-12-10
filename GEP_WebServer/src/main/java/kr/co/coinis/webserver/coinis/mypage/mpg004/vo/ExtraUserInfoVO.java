/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.vo 
 *    |_ ExtraUserInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 16. 오후 5:46:12
 * @version : 
 * @author : yeonseoo
 */
public class ExtraUserInfoVO {

	private int user_type_cd;
	private int auth_level;
	private String reg_dt;

	public int getUser_type_cd() {
		return user_type_cd;
	}
	public void setUser_type_cd(int user_type_cd) {
		this.user_type_cd = user_type_cd;
	}
	public int getAuth_level() {
		return auth_level;
	}
	public void setAuth_level(int auth_level) {
		this.auth_level = auth_level;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

}
