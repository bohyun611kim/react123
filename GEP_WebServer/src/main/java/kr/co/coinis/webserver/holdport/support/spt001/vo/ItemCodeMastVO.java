/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.support.spt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.vo 
 *    |_ ItemCodeMastVO.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오후 3:43:57
 * @version : 
 * @author : kangn
 */
public class ItemCodeMastVO {

	private String ITEM_CODE;
	private String ITEM_NM;
	private String ITEM_ENG_NM;
	private String ITEM_DOMESTIC_NM;
	private String ITEM_KOR_NM;
	private int    BASIC_ASSET_COIN_NO;
	private int    COIN_NO;
	private int    QTY_CALC_DECIMAL_PNT;
	private int    AMT_CALC_DECIMAL_PNT;
	private double ORD_PRICE_UNIT;
	private int    PRICE_DSP_DECIMAL_PNT;
	private int    QTY_DSP_DECIMAL_PNT;
	private int    VOL_DSP_DECIMAL_PNT;
	private int    AMT_DECIMAL_PNT;
	private double MIN_ORD_AMT;
	private int    SORT_ORDER_NO;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
	public String getITEM_CODE() {
		return ITEM_CODE;
	}
	public void setITEM_CODE(String iTEM_CODE) {
		ITEM_CODE = iTEM_CODE;
	}
	public String getITEM_NM() {
		return ITEM_NM;
	}
	public void setITEM_NM(String iTEM_NM) {
		ITEM_NM = iTEM_NM;
	}
	public String getITEM_ENG_NM() {
		return ITEM_ENG_NM;
	}
	public void setITEM_ENG_NM(String iTEM_ENG_NM) {
		ITEM_ENG_NM = iTEM_ENG_NM;
	}
	public String getITEM_DOMESTIC_NM() {
		return ITEM_DOMESTIC_NM;
	}
	public void setITEM_DOMESTIC_NM(String iTEM_DOMESTIC_NM) {
		ITEM_DOMESTIC_NM = iTEM_DOMESTIC_NM;
	}
	public String getITEM_KOR_NM() {
		return ITEM_KOR_NM;
	}
	public void setITEM_KOR_NM(String iTEM_KOR_NM) {
		ITEM_KOR_NM = iTEM_KOR_NM;
	}
	public int getBASIC_ASSET_COIN_NO() {
		return BASIC_ASSET_COIN_NO;
	}
	public void setBASIC_ASSET_COIN_NO(int bASIC_ASSET_COIN_NO) {
		BASIC_ASSET_COIN_NO = bASIC_ASSET_COIN_NO;
	}
	public int getCOIN_NO() {
		return COIN_NO;
	}
	public void setCOIN_NO(int cOIN_NO) {
		COIN_NO = cOIN_NO;
	}
	public int getQTY_CALC_DECIMAL_PNT() {
		return QTY_CALC_DECIMAL_PNT;
	}
	public void setQTY_CALC_DECIMAL_PNT(int qTY_CALC_DECIMAL_PNT) {
		QTY_CALC_DECIMAL_PNT = qTY_CALC_DECIMAL_PNT;
	}
	public int getAMT_CALC_DECIMAL_PNT() {
		return AMT_CALC_DECIMAL_PNT;
	}
	public void setAMT_CALC_DECIMAL_PNT(int aMT_CALC_DECIMAL_PNT) {
		AMT_CALC_DECIMAL_PNT = aMT_CALC_DECIMAL_PNT;
	}
	public double getORD_PRICE_UNIT() {
		return ORD_PRICE_UNIT;
	}
	public void setORD_PRICE_UNIT(double oRD_PRICE_UNIT) {
		ORD_PRICE_UNIT = oRD_PRICE_UNIT;
	}
	public int getPRICE_DSP_DECIMAL_PNT() {
		return PRICE_DSP_DECIMAL_PNT;
	}
	public void setPRICE_DSP_DECIMAL_PNT(int pRICE_DSP_DECIMAL_PNT) {
		PRICE_DSP_DECIMAL_PNT = pRICE_DSP_DECIMAL_PNT;
	}
	public int getQTY_DSP_DECIMAL_PNT() {
		return QTY_DSP_DECIMAL_PNT;
	}
	public void setQTY_DSP_DECIMAL_PNT(int qTY_DSP_DECIMAL_PNT) {
		QTY_DSP_DECIMAL_PNT = qTY_DSP_DECIMAL_PNT;
	}
	public int getVOL_DSP_DECIMAL_PNT() {
		return VOL_DSP_DECIMAL_PNT;
	}
	public void setVOL_DSP_DECIMAL_PNT(int vOL_DSP_DECIMAL_PNT) {
		VOL_DSP_DECIMAL_PNT = vOL_DSP_DECIMAL_PNT;
	}
	public int getAMT_DECIMAL_PNT() {
		return AMT_DECIMAL_PNT;
	}
	public void setAMT_DECIMAL_PNT(int aMT_DECIMAL_PNT) {
		AMT_DECIMAL_PNT = aMT_DECIMAL_PNT;
	}
	public double getMIN_ORD_AMT() {
		return MIN_ORD_AMT;
	}
	public void setMIN_ORD_AMT(double mIN_ORD_AMT) {
		MIN_ORD_AMT = mIN_ORD_AMT;
	}
	public int getSORT_ORDER_NO() {
		return SORT_ORDER_NO;
	}
	public void setSORT_ORDER_NO(int sORT_ORDER_NO) {
		SORT_ORDER_NO = sORT_ORDER_NO;
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
