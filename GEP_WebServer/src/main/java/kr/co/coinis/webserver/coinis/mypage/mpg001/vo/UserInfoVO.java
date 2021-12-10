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
 * kr.co.coinis.webserver.mypage.mpg001.vo 
 *    |_ UserInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 22. 오전 9:41:32
 * @version : 
 * @author : Seongcheol
 */
public class UserInfoVO {

	private String userId;
	private String mobile;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
