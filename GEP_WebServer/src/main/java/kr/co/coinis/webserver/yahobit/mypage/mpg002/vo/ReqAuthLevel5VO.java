/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg002.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg002.vo 
 *    |_ ReqAuthLevel5VO.java
 * 
 * </pre>
 * @date : 2019. 7. 1. 오후 2:00:18
 * @version : 
 * @author : Seongcheol
 */
public class ReqAuthLevel5VO {

	private String procFlag;
	private String exchangeId;
	private String userId;
	private int    recSeqNo;
	private String addr;
	private String jobCd;
	private String purpose;
	private String origin;
	private String tradeCnt;
	private String tradeAmt;
	private String fileNm;
	private String fileNm2;
	private int    approvalStatCd;
	private int    rtnCd;
	private String rtnMsg;

	public String getProcFlag() {
		return procFlag;
	}
	public void setProcFlag(String procFlag) {
		this.procFlag = procFlag;
	}
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
	public int getRecSeqNo() {
		return recSeqNo;
	}
	public void setRecSeqNo(int recSeqNo) {
		this.recSeqNo = recSeqNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getJobCd() {
		return jobCd;
	}
	public void setJobCd(String jobCd) {
		this.jobCd = jobCd;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getTradeCnt() {
		return tradeCnt;
	}
	public void setTradeCnt(String tradeCnt) {
		this.tradeCnt = tradeCnt;
	}
	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFileNm2() {
		return fileNm2;
	}
	public void setFileNm2(String fileNm2) {
		this.fileNm2 = fileNm2;
	}
	public int getApprovalStatCd() {
		return approvalStatCd;
	}
	public void setApprovalStatCd(int approvalStatCd) {
		this.approvalStatCd = approvalStatCd;
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
