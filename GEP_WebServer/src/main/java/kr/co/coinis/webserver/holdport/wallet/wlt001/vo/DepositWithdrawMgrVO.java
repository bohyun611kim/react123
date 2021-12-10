/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.wallet.wlt001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.wallet.wlt001.vo
 *    |_ DepositWithdrawMgrVO.java
 *
 * </pre>
 * @date : 2019. 4. 29. 오후 3:50:20
 * @version :
 * @author : kangn
 */
public class DepositWithdrawMgrVO {

	private long   REQ_SEQ_NO;
	private String REQ_DAY;
	private String REQ_DT;
	private String REQ_IP;
	private String REQ_CHNL_CD;
	private String EXCHANGE_ID;
	private String USER_ID;
	private int    DW_REQ_TYPE_CD;
	private String FROM_ADDR;
	private String PIN_CD;
	private String TARGET_ADDR;
	private String TARGET_ADDR_ETC1;
	private String TARGET_ADDR_ETC2;
	private int    COIN_NO;
	private double PRICE_BY_BC;
	private double REQ_QTY;
	private String REQ_MEMO;
	private int    REQ_STAT_PROC_CD;
	private String REQ_STAT_PROC_DT;
	private int    APPROVAL_STAT_CD;
	private String APPROVAL_PROC_DT;
	private String APPROVAL_PROC_MEMO;
	private String APPROVAL_PROC_ID;
	private double WTDRW_FEE;
	private int    DW_PROC_STAT_CD;
	private int    DW_PROC_DET_ERR_CD;
	private String DW_PROC_DT;
	private int    IS_WITHDRAWING_YN;
	private long   TRANSACTION_IDX;

	public long getREQ_SEQ_NO() {
		return REQ_SEQ_NO;
	}
	public void setREQ_SEQ_NO(long rEQ_SEQ_NO) {
		REQ_SEQ_NO = rEQ_SEQ_NO;
	}
	public String getREQ_DAY() {
		return REQ_DAY;
	}
	public void setREQ_DAY(String rEQ_DAY) {
		REQ_DAY = rEQ_DAY;
	}
	public String getREQ_DT() {
		return REQ_DT;
	}
	public void setREQ_DT(String rEQ_DT) {
		REQ_DT = rEQ_DT;
	}
	public String getREQ_IP() {
		return REQ_IP;
	}
	public void setREQ_IP(String rEQ_IP) {
		REQ_IP = rEQ_IP;
	}
	public String getREQ_CHNL_CD() {
		return REQ_CHNL_CD;
	}
	public void setREQ_CHNL_CD(String rEQ_CHNL_CD) {
		REQ_CHNL_CD = rEQ_CHNL_CD;
	}
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
	public int getDW_REQ_TYPE_CD() {
		return DW_REQ_TYPE_CD;
	}
	public void setDW_REQ_TYPE_CD(int dW_REQ_TYPE_CD) {
		DW_REQ_TYPE_CD = dW_REQ_TYPE_CD;
	}
	public String getFROM_ADDR() {
		return FROM_ADDR;
	}
	public void setFROM_ADDR(String fROM_ADDR) {
		FROM_ADDR = fROM_ADDR;
	}
	public String getPIN_CD() {
		return PIN_CD;
	}
	public void setPIN_CD(String pIN_CD) {
		PIN_CD = pIN_CD;
	}
	public String getTARGET_ADDR() {
		return TARGET_ADDR;
	}
	public void setTARGET_ADDR(String tARGET_ADDR) {
		TARGET_ADDR = tARGET_ADDR;
	}
	public String getTARGET_ADDR_ETC1() {
		return TARGET_ADDR_ETC1;
	}
	public void setTARGET_ADDR_ETC1(String tARGET_ADDR_ETC1) {
		TARGET_ADDR_ETC1 = tARGET_ADDR_ETC1;
	}
	public String getTARGET_ADDR_ETC2() {
		return TARGET_ADDR_ETC2;
	}
	public void setTARGET_ADDR_ETC2(String tARGET_ADDR_ETC2) {
		TARGET_ADDR_ETC2 = tARGET_ADDR_ETC2;
	}
	public int getCOIN_NO() {
		return COIN_NO;
	}
	public void setCOIN_NO(int cOIN_NO) {
		COIN_NO = cOIN_NO;
	}
	public double getPRICE_BY_BC() {
		return PRICE_BY_BC;
	}
	public void setPRICE_BY_BC(double pRICE_BY_BC) {
		PRICE_BY_BC = pRICE_BY_BC;
	}
	public double getREQ_QTY() {
		return REQ_QTY;
	}
	public void setREQ_QTY(double rEQ_QTY) {
		REQ_QTY = rEQ_QTY;
	}
	public String getREQ_MEMO() {
		return REQ_MEMO;
	}
	public void setREQ_MEMO(String rEQ_MEMO) {
		REQ_MEMO = rEQ_MEMO;
	}
	public int getREQ_STAT_PROC_CD() {
		return REQ_STAT_PROC_CD;
	}
	public void setREQ_STAT_PROC_CD(int rEQ_STAT_PROC_CD) {
		REQ_STAT_PROC_CD = rEQ_STAT_PROC_CD;
	}
	public String getREQ_STAT_PROC_DT() {
		return REQ_STAT_PROC_DT;
	}
	public void setREQ_STAT_PROC_DT(String rEQ_STAT_PROC_DT) {
		REQ_STAT_PROC_DT = rEQ_STAT_PROC_DT;
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
	public String getAPPROVAL_PROC_MEMO() {
		return APPROVAL_PROC_MEMO;
	}
	public void setAPPROVAL_PROC_MEMO(String aPPROVAL_PROC_MEMO) {
		APPROVAL_PROC_MEMO = aPPROVAL_PROC_MEMO;
	}
	public String getAPPROVAL_PROC_ID() {
		return APPROVAL_PROC_ID;
	}
	public void setAPPROVAL_PROC_ID(String aPPROVAL_PROC_ID) {
		APPROVAL_PROC_ID = aPPROVAL_PROC_ID;
	}
	public double getWTDRW_FEE() {
		return WTDRW_FEE;
	}
	public void setWTDRW_FEE(double wTDRW_FEE) {
		WTDRW_FEE = wTDRW_FEE;
	}
	public int getDW_PROC_STAT_CD() {
		return DW_PROC_STAT_CD;
	}
	public void setDW_PROC_STAT_CD(int dW_PROC_STAT_CD) {
		DW_PROC_STAT_CD = dW_PROC_STAT_CD;
	}
	public int getDW_PROC_DET_ERR_CD() {
		return DW_PROC_DET_ERR_CD;
	}
	public void setDW_PROC_DET_ERR_CD(int dW_PROC_DET_ERR_CD) {
		DW_PROC_DET_ERR_CD = dW_PROC_DET_ERR_CD;
	}
	public String getDW_PROC_DT() {
		return DW_PROC_DT;
	}
	public void setDW_PROC_DT(String dW_PROC_DT) {
		DW_PROC_DT = dW_PROC_DT;
	}
	public int getIS_WITHDRAWING_YN() {
		return IS_WITHDRAWING_YN;
	}
	public void setIS_WITHDRAWING_YN(int iS_WITHDRAWING_YN) {
		IS_WITHDRAWING_YN = iS_WITHDRAWING_YN;
	}
	public long getTRANSACTION_IDX() {
		return TRANSACTION_IDX;
	}
	public void setTRANSACTION_IDX(long tRANSACTION_IDX) {
		TRANSACTION_IDX = tRANSACTION_IDX;
	}

}
