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
 *    |_ CoinMgtRefInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 28. 오전 10:38:53
 * @version : 
 * @author : kangn
 */
public class CoinMgtRefInfoVO {

	private int    COIN_NO;
	private String COIN_SYMBOLIC_NM;
	private int    WTDRW_DECIMAL_PNT;
	private int    CONFIRMATIONS;
	private int    CONFIRM_DELAY_TM;
	private int    DEPOSIT_PAGE_SHOW_YN;
	private int    WTDRW_PAGE_SHOW_YN;
	private int    AUTO_DEPOSIT_YN;
	private int    AUTO_WTDRW_YN;
	private int    SYSTEM_AUTO_WITHDRAW_YN;
	private double WTDRW_FEE;
	private double MIN_WTDRW_QTY;
	private double MIN_DEPOSIT_QTY;
	private double MAX_ONCE_WTHRW_QTY;
	private int    COIN_ADDR_CNT;
	private int    COIN_DEPOSIT_ADDR_CNT;
	private int    COIN_WTDRW_ADDR_CNT;
	private String ADDR_ETC1_NM;
	private String ADDR_ETC2_NM;
	private int    TRADE_STAT_CD;
	private int    COIN_ADDR_CHECK_YN;
	private int    COIN_ADDR_LEN;
	private String COIN_ADDR_CHECK_STR;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
	public int getCOIN_NO() {
		return COIN_NO;
	}
	public void setCOIN_NO(int cOIN_NO) {
		COIN_NO = cOIN_NO;
	}
	public String getCOIN_SYMBOLIC_NM() {
		return COIN_SYMBOLIC_NM;
	}
	public void setCOIN_SYMBOLIC_NM(String cOIN_SYMBOLIC_NM) {
		COIN_SYMBOLIC_NM = cOIN_SYMBOLIC_NM;
	}
	public int getWTDRW_DECIMAL_PNT() {
		return WTDRW_DECIMAL_PNT;
	}
	public void setWTDRW_DECIMAL_PNT(int wTDRW_DECIMAL_PNT) {
		WTDRW_DECIMAL_PNT = wTDRW_DECIMAL_PNT;
	}
	public int getCONFIRMATIONS() {
		return CONFIRMATIONS;
	}
	public void setCONFIRMATIONS(int cONFIRMATIONS) {
		CONFIRMATIONS = cONFIRMATIONS;
	}
	public int getCONFIRM_DELAY_TM() {
		return CONFIRM_DELAY_TM;
	}
	public void setCONFIRM_DELAY_TM(int cONFIRM_DELAY_TM) {
		CONFIRM_DELAY_TM = cONFIRM_DELAY_TM;
	}
	public int getDEPOSIT_PAGE_SHOW_YN() {
		return DEPOSIT_PAGE_SHOW_YN;
	}
	public void setDEPOSIT_PAGE_SHOW_YN(int dEPOSIT_PAGE_SHOW_YN) {
		DEPOSIT_PAGE_SHOW_YN = dEPOSIT_PAGE_SHOW_YN;
	}
	public int getWTDRW_PAGE_SHOW_YN() {
		return WTDRW_PAGE_SHOW_YN;
	}
	public void setWTDRW_PAGE_SHOW_YN(int wTDRW_PAGE_SHOW_YN) {
		WTDRW_PAGE_SHOW_YN = wTDRW_PAGE_SHOW_YN;
	}
	public int getAUTO_DEPOSIT_YN() {
		return AUTO_DEPOSIT_YN;
	}
	public void setAUTO_DEPOSIT_YN(int aUTO_DEPOSIT_YN) {
		AUTO_DEPOSIT_YN = aUTO_DEPOSIT_YN;
	}
	public int getAUTO_WTDRW_YN() {
		return AUTO_WTDRW_YN;
	}
	public void setAUTO_WTDRW_YN(int aUTO_WTDRW_YN) {
		AUTO_WTDRW_YN = aUTO_WTDRW_YN;
	}
	public int getSYSTEM_AUTO_WITHDRAW_YN() {
		return SYSTEM_AUTO_WITHDRAW_YN;
	}
	public void setSYSTEM_AUTO_WITHDRAW_YN(int sYSTEM_AUTO_WITHDRAW_YN) {
		SYSTEM_AUTO_WITHDRAW_YN = sYSTEM_AUTO_WITHDRAW_YN;
	}
	public double getWTDRW_FEE() {
		return WTDRW_FEE;
	}
	public void setWTDRW_FEE(double wTDRW_FEE) {
		WTDRW_FEE = wTDRW_FEE;
	}
	public double getMIN_WTDRW_QTY() {
		return MIN_WTDRW_QTY;
	}
	public void setMIN_WTDRW_QTY(double mIN_WTDRW_QTY) {
		MIN_WTDRW_QTY = mIN_WTDRW_QTY;
	}
	public double getMIN_DEPOSIT_QTY() {
		return MIN_DEPOSIT_QTY;
	}
	public void setMIN_DEPOSIT_QTY(double mIN_DEPOSIT_QTY) {
		MIN_DEPOSIT_QTY = mIN_DEPOSIT_QTY;
	}
	public double getMAX_ONCE_WTHRW_QTY() {
		return MAX_ONCE_WTHRW_QTY;
	}
	public void setMAX_ONCE_WTHRW_QTY(double mAX_ONCE_WTHRW_QTY) {
		MAX_ONCE_WTHRW_QTY = mAX_ONCE_WTHRW_QTY;
	}
	public int getCOIN_ADDR_CNT() {
		return COIN_ADDR_CNT;
	}
	public void setCOIN_ADDR_CNT(int cOIN_ADDR_CNT) {
		COIN_ADDR_CNT = cOIN_ADDR_CNT;
	}
	public int getCOIN_DEPOSIT_ADDR_CNT() {
		return COIN_DEPOSIT_ADDR_CNT;
	}
	public void setCOIN_DEPOSIT_ADDR_CNT(int cOIN_DEPOSIT_ADDR_CNT) {
		COIN_DEPOSIT_ADDR_CNT = cOIN_DEPOSIT_ADDR_CNT;
	}
	public int getCOIN_WTDRW_ADDR_CNT() {
		return COIN_WTDRW_ADDR_CNT;
	}
	public void setCOIN_WTDRW_ADDR_CNT(int cOIN_WTDRW_ADDR_CNT) {
		COIN_WTDRW_ADDR_CNT = cOIN_WTDRW_ADDR_CNT;
	}
	public String getADDR_ETC1_NM() {
		return ADDR_ETC1_NM;
	}
	public void setADDR_ETC1_NM(String aDDR_ETC1_NM) {
		ADDR_ETC1_NM = aDDR_ETC1_NM;
	}
	public String getADDR_ETC2_NM() {
		return ADDR_ETC2_NM;
	}
	public void setADDR_ETC2_NM(String aDDR_ETC2_NM) {
		ADDR_ETC2_NM = aDDR_ETC2_NM;
	}
	public int getTRADE_STAT_CD() {
		return TRADE_STAT_CD;
	}
	public void setTRADE_STAT_CD(int tRADE_STAT_CD) {
		TRADE_STAT_CD = tRADE_STAT_CD;
	}
	public int getCOIN_ADDR_CHECK_YN() {
		return COIN_ADDR_CHECK_YN;
	}
	public void setCOIN_ADDR_CHECK_YN(int cOIN_ADDR_CHECK_YN) {
		COIN_ADDR_CHECK_YN = cOIN_ADDR_CHECK_YN;
	}
	public int getCOIN_ADDR_LEN() {
		return COIN_ADDR_LEN;
	}
	public void setCOIN_ADDR_LEN(int cOIN_ADDR_LEN) {
		COIN_ADDR_LEN = cOIN_ADDR_LEN;
	}
	public String getCOIN_ADDR_CHECK_STR() {
		return COIN_ADDR_CHECK_STR;
	}
	public void setCOIN_ADDR_CHECK_STR(String cOIN_ADDR_CHECK_STR) {
		COIN_ADDR_CHECK_STR = cOIN_ADDR_CHECK_STR;
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
