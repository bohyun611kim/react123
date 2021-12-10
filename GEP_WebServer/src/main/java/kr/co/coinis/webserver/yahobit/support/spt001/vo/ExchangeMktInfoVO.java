/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.support.spt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.vo 
 *    |_ ExchangeMktInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오후 8:39:27
 * @version : 
 * @author : kangn
 */
public class ExchangeMktInfoVO {

	private String EXCHANGE_ID;
	private String MKT_ID;
	private String MKT_NM;
	private int    BASIC_ASSET_COIN_NO;
	private double BUY_FEE_VAL;
	private double SELL_FEE_VAL;
	private int    MKT_DISP_ORDER;
	private String MKT_OPEN_DAY;
	private int    MKT_STAT_CD;
	private String STAT_CHG_DT;
	private String MKT_GRP_ID;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
	public String getEXCHANGE_ID() {
		return EXCHANGE_ID;
	}
	public void setEXCHANGE_ID(String eXCHANGE_ID) {
		EXCHANGE_ID = eXCHANGE_ID;
	}
	public String getMKT_ID() {
		return MKT_ID;
	}
	public void setMKT_ID(String mKT_ID) {
		MKT_ID = mKT_ID;
	}
	public String getMKT_NM() {
		return MKT_NM;
	}
	public void setMKT_NM(String mKT_NM) {
		MKT_NM = mKT_NM;
	}
	public int getBASIC_ASSET_COIN_NO() {
		return BASIC_ASSET_COIN_NO;
	}
	public void setBASIC_ASSET_COIN_NO(int bASIC_ASSET_COIN_NO) {
		BASIC_ASSET_COIN_NO = bASIC_ASSET_COIN_NO;
	}
	public double getBUY_FEE_VAL() {
		return BUY_FEE_VAL;
	}
	public void setBUY_FEE_VAL(double bUY_FEE_VAL) {
		BUY_FEE_VAL = bUY_FEE_VAL;
	}
	public double getSELL_FEE_VAL() {
		return SELL_FEE_VAL;
	}
	public void setSELL_FEE_VAL(double sELL_FEE_VAL) {
		SELL_FEE_VAL = sELL_FEE_VAL;
	}
	public int getMKT_DISP_ORDER() {
		return MKT_DISP_ORDER;
	}
	public void setMKT_DISP_ORDER(int mKT_DISP_ORDER) {
		MKT_DISP_ORDER = mKT_DISP_ORDER;
	}
	public String getMKT_OPEN_DAY() {
		return MKT_OPEN_DAY;
	}
	public void setMKT_OPEN_DAY(String mKT_OPEN_DAY) {
		MKT_OPEN_DAY = mKT_OPEN_DAY;
	}
	public int getMKT_STAT_CD() {
		return MKT_STAT_CD;
	}
	public void setMKT_STAT_CD(int mKT_STAT_CD) {
		MKT_STAT_CD = mKT_STAT_CD;
	}
	public String getSTAT_CHG_DT() {
		return STAT_CHG_DT;
	}
	public void setSTAT_CHG_DT(String sTAT_CHG_DT) {
		STAT_CHG_DT = sTAT_CHG_DT;
	}
	public String getMKT_GRP_ID() {
		return MKT_GRP_ID;
	}
	public void setMKT_GRP_ID(String mKT_GRP_ID) {
		MKT_GRP_ID = mKT_GRP_ID;
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
