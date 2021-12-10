/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr002.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr002.vo 
 *    |_ LoginMatchResultVO.java
 * 
 * </pre>
 * @date : 2019. 4. 4. 오후 2:20:42
 * @version : 
 * @author : Seongcheol
 */
public class LoginMatchResultVO {

	private int cnt;
	private int memberStatCd;
	private int authMeansCd;
	private int loginFailCnt;
	private int otpFailCnt;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getMemberStatCd() {
		return memberStatCd;
	}
	public void setMemberStatCd(int memberStatCd) {
		this.memberStatCd = memberStatCd;
	}
	public int getAuthMeansCd() {
		return authMeansCd;
	}
	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}
	public int getLoginFailCnt() {
		return loginFailCnt;
	}
	public void setLoginFailCnt(int loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	public int getOtpFailCnt() {
		return otpFailCnt;
	}
	public void setOtpFailCnt(int otpFailCnt) {
		this.otpFailCnt = otpFailCnt;
	}
}
