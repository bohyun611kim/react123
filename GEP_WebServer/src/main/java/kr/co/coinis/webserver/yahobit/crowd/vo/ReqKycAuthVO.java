/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.vo 
 *    |_ ReqKycAuthVO.java
 * 
 * </pre>
 * @date : 2019. 6. 7. 오후 4:16:57
 * @version : 
 * @author : Seongcheol
 */
public class ReqKycAuthVO {

	private String exchangeId;
	private String userId;
	private int    purpose;
	private int    income1;
	private int    income2;
	private int    income3;
	private int    income4;
	private int    income5;
	private int    income6;
	private int    income7;
	private int    income8;
	private int    ownerYn;
	private int    liabilityCd;
	private String reasonCont;
	private String fileName1;
	private String fileName2;
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
	public int getPurpose() {
		return purpose;
	}
	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
	public int getIncome1() {
		return income1;
	}
	public void setIncome1(int income1) {
		this.income1 = income1;
	}
	public int getIncome2() {
		return income2;
	}
	public void setIncome2(int income2) {
		this.income2 = income2;
	}
	public int getIncome3() {
		return income3;
	}
	public void setIncome3(int income3) {
		this.income3 = income3;
	}
	public int getIncome4() {
		return income4;
	}
	public void setIncome4(int income4) {
		this.income4 = income4;
	}
	public int getIncome5() {
		return income5;
	}
	public void setIncome5(int income5) {
		this.income5 = income5;
	}
	public int getIncome6() {
		return income6;
	}
	public void setIncome6(int income6) {
		this.income6 = income6;
	}
	public int getIncome7() {
		return income7;
	}
	public void setIncome7(int income7) {
		this.income7 = income7;
	}
	public int getIncome8() {
		return income8;
	}
	public void setIncome8(int income8) {
		this.income8 = income8;
	}
	public int getOwnerYn() {
		return ownerYn;
	}
	public void setOwnerYn(int ownerYn) {
		this.ownerYn = ownerYn;
	}
	public int getLiabilityCd() {
		return liabilityCd;
	}
	public void setLiabilityCd(int liabilityCd) {
		this.liabilityCd = liabilityCd;
	}
	public String getReasonCont() {
		return reasonCont;
	}
	public void setReasonCont(String reasonCont) {
		this.reasonCont = reasonCont;
	}
	public String getFileName1() {
		return fileName1;
	}
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	public String getFileName2() {
		return fileName2;
	}
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
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
}
