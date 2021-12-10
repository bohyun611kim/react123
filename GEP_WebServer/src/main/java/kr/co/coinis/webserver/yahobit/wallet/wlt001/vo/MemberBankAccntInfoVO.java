/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt001.vo 
 *    |_ MemberBankAccntInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 5. 오후 1:32:56
 * @version : 
 * @author : kangn
 */
public class MemberBankAccntInfoVO {

	private String EXCHANGE_ID;
	private String USER_ID;
	private long   REC_SEQ_NO;
	private String USE_START_DT;
	private String USE_END_DT;
	private int    USE_YN;
	private String BANK_CD;
	private String BANK_ACCNT_NO;
	private String ACCNT_HOLDER_NM;
	private int    APPROVAL_STAT_CD;
	private String APPROVAL_PROC_DT;
	
	public String getEXCHANGE_ID() {
		return EXCHANGE_ID;
	}
	public void setEXCHANGE_ID(String eXCHANGE_ID) {
		EXCHANGE_ID = eXCHANGE_ID;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public long getREC_SEQ_NO() {
		return REC_SEQ_NO;
	}
	public void setREC_SEQ_NO(long rEC_SEQ_NO) {
		REC_SEQ_NO = rEC_SEQ_NO;
	}
	public String getUSE_START_DT() {
		return USE_START_DT;
	}
	public void setUSE_START_DT(String uSE_START_DT) {
		USE_START_DT = uSE_START_DT;
	}
	public String getUSE_END_DT() {
		return USE_END_DT;
	}
	public void setUSE_END_DT(String uSE_END_DT) {
		USE_END_DT = uSE_END_DT;
	}
	public int getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(int uSE_YN) {
		USE_YN = uSE_YN;
	}
	public String getBANK_CD() {
		return BANK_CD;
	}
	public void setBANK_CD(String bANK_CD) {
		BANK_CD = bANK_CD;
	}
	public String getBANK_ACCNT_NO() {
		return BANK_ACCNT_NO;
	}
	public void setBANK_ACCNT_NO(String bANK_ACCNT_NO) {
		BANK_ACCNT_NO = bANK_ACCNT_NO;
	}
	public String getACCNT_HOLDER_NM() {
		return ACCNT_HOLDER_NM;
	}
	public void setACCNT_HOLDER_NM(String aCCNT_HOLDER_NM) {
		ACCNT_HOLDER_NM = aCCNT_HOLDER_NM;
	}
	public int getAPPROVAL_STAT_CD() {
		return APPROVAL_STAT_CD;
	}
	public void setAPPROVAL_STAT_CD(int aPPROVAL_STAT_CD) {
		APPROVAL_STAT_CD = aPPROVAL_STAT_CD;
	}
	public String getAPPROVAL_PROC_DT() {
		return APPROVAL_PROC_DT;
	}
	public void setAPPROVAL_PROC_DT(String aPPROVAL_PROC_DT) {
		APPROVAL_PROC_DT = aPPROVAL_PROC_DT;
	}
	@Override
	public String toString() {
		return "MemberBankAccntInfoVO [EXCHANGE_ID=" + EXCHANGE_ID + ", USER_ID=" + USER_ID + ", REC_SEQ_NO="
				+ REC_SEQ_NO + ", USE_START_DT=" + USE_START_DT + ", USE_END_DT=" + USE_END_DT + ", USE_YN=" + USE_YN
				+ ", BANK_CD=" + BANK_CD + ", BANK_ACCNT_NO=" + BANK_ACCNT_NO + ", ACCNT_HOLDER_NM=" + ACCNT_HOLDER_NM
				+ ", APPROVAL_STAT_CD=" + APPROVAL_STAT_CD + ", APPROVAL_PROC_DT=" + APPROVAL_PROC_DT + "]";
	}

}
