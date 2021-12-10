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
 *    |_ CodeInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 27. 오후 3:33:58
 * @version : 
 * @author : kangn
 */
public class CodeInfoVO {

	private String CODE_ID;
	private String LANG_CD;
	private long   REC_SEQ_NO;
	private String CODE_VAL_NM;
	private int    CODE_NUM_VAL;
	private String CODE_STR_VAL;
	private int    SORT_ORDER_NO;
	private int    USE_YN;
	private String FILTER_COND1_STR;
	private String FILTER_COND2_STR;
	private String FILTER_COND3_STR;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
	public String getCODE_ID() {
		return CODE_ID;
	}
	public void setCODE_ID(String cODE_ID) {
		CODE_ID = cODE_ID;
	}
	public String getLANG_CD() {
		return LANG_CD;
	}
	public void setLANG_CD(String lANG_CD) {
		LANG_CD = lANG_CD;
	}
	public long getREC_SEQ_NO() {
		return REC_SEQ_NO;
	}
	public void setREC_SEQ_NO(long rEC_SEQ_NO) {
		REC_SEQ_NO = rEC_SEQ_NO;
	}
	public String getCODE_VAL_NM() {
		return CODE_VAL_NM;
	}
	public void setCODE_VAL_NM(String cODE_VAL_NM) {
		CODE_VAL_NM = cODE_VAL_NM;
	}
	public int getCODE_NUM_VAL() {
		return CODE_NUM_VAL;
	}
	public void setCODE_NUM_VAL(int cODE_NUM_VAL) {
		CODE_NUM_VAL = cODE_NUM_VAL;
	}
	public String getCODE_STR_VAL() {
		return CODE_STR_VAL;
	}
	public void setCODE_STR_VAL(String cODE_STR_VAL) {
		CODE_STR_VAL = cODE_STR_VAL;
	}
	public int getSORT_ORDER_NO() {
		return SORT_ORDER_NO;
	}
	public void setSORT_ORDER_NO(int sORT_ORDER_NO) {
		SORT_ORDER_NO = sORT_ORDER_NO;
	}
	public int getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(int uSE_YN) {
		USE_YN = uSE_YN;
	}
	public String getFILTER_COND1_STR() {
		return FILTER_COND1_STR;
	}
	public void setFILTER_COND1_STR(String fILTER_COND1_STR) {
		FILTER_COND1_STR = fILTER_COND1_STR;
	}
	public String getFILTER_COND2_STR() {
		return FILTER_COND2_STR;
	}
	public void setFILTER_COND2_STR(String fILTER_COND2_STR) {
		FILTER_COND2_STR = fILTER_COND2_STR;
	}
	public String getFILTER_COND3_STR() {
		return FILTER_COND3_STR;
	}
	public void setFILTER_COND3_STR(String fILTER_COND3_STR) {
		FILTER_COND3_STR = fILTER_COND3_STR;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getREG_USER_ID() {
		return REG_USER_ID;
	}
	public void setREG_USER_ID(String rEG_USER_ID) {
		REG_USER_ID = rEG_USER_ID;
	}
	public String getUPDT_DT() {
		return UPDT_DT;
	}
	public void setUPDT_DT(String uPDT_DT) {
		UPDT_DT = uPDT_DT;
	}
	public String getUPDT_USER_ID() {
		return UPDT_USER_ID;
	}
	public void setUPDT_USER_ID(String uPDT_USER_ID) {
		UPDT_USER_ID = uPDT_USER_ID;
	}

}
