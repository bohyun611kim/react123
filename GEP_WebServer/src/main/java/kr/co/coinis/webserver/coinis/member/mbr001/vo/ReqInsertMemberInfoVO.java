/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.vo 
 *    |_ ReqInsertMemberInfoVO.java
 * 
 * </pre>
 * @date : 2019. 3. 26. 오후 5:55:24
 * @version : 
 * @author : Seongcheol
 */
public class ReqInsertMemberInfoVO {

	private String exchangeId;
	@NotNull(message="이메일은 필수입니다")
	@Size(max=40, message="이메일은 40자리 이하만 가능합니다")
	private String userId;
	@NotNull(message="회원 유형 선택은 필수입니다")
	@Min(1)
	@Max(2)
	private int    userTypeCd;
	@NotNull(message="비밀번호는 필수입니다")
	@Size(min=8, max=25, message="비밀번호는 8자리 이상 2자리 이하입니다")
	private String password;
	@NotNull(message="잘못된 접근입니다")
	private int	   mktAgree;
	private String recommanderId;
	@NotNull(message="국가선택은 필수입니다")
	@Size(min=2, max=2, message="잘못된 국가 코드 입니다")
	private String contryCd;
	private String postalCode;
	@NotNull(message="주소는 필수입니다")
	private String address;
	@NotNull(message="이름은 필수입니다")
	private String fullName;
	@NotNull(message="생년월일은 필수입니다")
	private String birthday;
	private int    idTypeCd;
	private String corpName;
	private String corpRepr;
	private String corpRegNo;
	private String regDt;
	private int    rtnCd;
	private String rtnMsg;
	
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserTypeCd() {
		return userTypeCd;
	}
	public void setUserTypeCd(int userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMktAgree() {
		return mktAgree;
	}
	public void setMktAgree(int mktAgree) {
		this.mktAgree = mktAgree;
	}
	public String getRecommanderId() {
		return recommanderId;
	}
	public void setRecommanderId(String recommanderId) {
		this.recommanderId = recommanderId;
	}
	public String getContryCd() {
		return contryCd;
	}
	public void setContryCd(String contryCd) {
		this.contryCd = contryCd;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(int idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	public int getRtnCd() {
		return rtnCd;
	}
	public void setRtnCd(int rtnCd) {
		this.rtnCd = rtnCd;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpRepr() {
		return corpRepr;
	}
	public void setCorpRepr(String corpRepr) {
		this.corpRepr = corpRepr;
	}
	public String getCorpRegNo() {
		return corpRegNo;
	}
	public void setCorpRegNo(String corpRegNo) {
		this.corpRegNo = corpRegNo;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
