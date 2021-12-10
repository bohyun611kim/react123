/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.member.mbr001.vo;

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
	@NotNull(message="-5046")
	@Size(max=40, message="-5045")
	private String userId;
	private int    userTypeCd;
	@NotNull(message="-5047")
	@Size(min=8, max=25, message="-5048")
	private String password;
	private int	   mktAgree;
	private String recommanderId;
	private String contryCd;
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
