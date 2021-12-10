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
 *    |_ AuthLevel5VO.java
 * 
 * </pre>
 * @date : 2019. 7. 1. 오후 1:20:42
 * @version : 
 * @author : Seongcheol
 */
public class AuthLevel5VO {

	private int    recSeqNo;
	private String approvalStatCd;
	private String approvalProcMemo;

	public int getRecSeqNo() {
		return recSeqNo;
	}
	public void setRecSeqNo(int recSeqNo) {
		this.recSeqNo = recSeqNo;
	}
	public String getApprovalStatCd() {
		return approvalStatCd;
	}
	public void setApprovalStatCd(String approvalStatCd) {
		this.approvalStatCd = approvalStatCd;
	}
	public String getApprovalProcMemo() {
		return approvalProcMemo;
	}
	public void setApprovalProcMemo(String approvalProcMemo) {
		this.approvalProcMemo = approvalProcMemo;
	}
}
