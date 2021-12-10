/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg001.vo;

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
	private byte[] userName;
	private String birthday;
	private int    mrktAgreeYn;
	private int    authLevel;
	private int    authMeansCd;
	private int    userTypeCd;
	private int    otpYn;
	private String regDt;
	
	// 신분증 인증관련 항목 추가 (강신석 - 2019.07.05)
	private int    idTypeCd;
	private int    idVerifiYn;
	private int    idVerifiResultCd;
	private int    idVerifiErrDetCd;
	private String idVerifiMemoCont;
	
	// 5레벨 인증 관련 필드
	private String approvalStatCd;
	private String approvalProcMemo;
	
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
	public byte[] getUserName() {
		return userName;
	}
	public void setUserName(byte[] userName) {
		this.userName = userName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getMrktAgreeYn() {
		return mrktAgreeYn;
	}
	public void setMrktAgreeYn(int mrktAgreeYn) {
		this.mrktAgreeYn = mrktAgreeYn;
	}
	public int getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}
	public int getAuthMeansCd() {
		return authMeansCd;
	}
	public void setAuthMeansCd(int authMeansCd) {
		this.authMeansCd = authMeansCd;
	}
	public int getUserTypeCd() {
		return userTypeCd;
	}
	public void setUserTypeCd(int userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	public int getOtpYn() {
		return otpYn;
	}
	public void setOtpYn(int otpYn) {
		this.otpYn = otpYn;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public int getIdVerifiYn() {
		return idVerifiYn;
	}
	public void setIdVerifiYn(int idVerifiYn) {
		this.idVerifiYn = idVerifiYn;
	}
	public int getIdVerifiResultCd() {
		return idVerifiResultCd;
	}
	public void setIdVerifiResultCd(int idVerifiResultCd) {
		this.idVerifiResultCd = idVerifiResultCd;
	}
	public int getIdVerifiErrDetCd() {
		return idVerifiErrDetCd;
	}
	public void setIdVerifiErrDetCd(int idVerifiErrDetCd) {
		this.idVerifiErrDetCd = idVerifiErrDetCd;
	}
	public int getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(int idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	public String getApprovalStatCd() {
		return approvalStatCd;
	}
	public void setApprovalStatCd(String approvalStatCd) {
		this.approvalStatCd = approvalStatCd;
	}
	public String getIdVerifiMemoCont() {
		return idVerifiMemoCont;
	}
	public void setIdVerifiMemoCont(String idVerifiMemoCont) {
		this.idVerifiMemoCont = idVerifiMemoCont;
	}
	public String getApprovalProcMemo() {
		return approvalProcMemo;
	}
	public void setApprovalProcMemo(String approvalProcMemo) {
		this.approvalProcMemo = approvalProcMemo;
	}
}
