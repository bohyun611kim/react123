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
 * kr.co.coinis.webserver.yahobit.holdport.wlt001.vo
 *    |_ CoinBalanceVO.java
 *
 * </pre>
 * @date : 2019. 4. 27. 오후 5:24:57
 * @version :
 * @author : kangn
 */
public class CoinBalanceVO {

	private String EXCHANGE_ID;
	private String USER_ID;
	private int    COIN_NO;
	private String TRADE_DT;
	private double BALANCE_QTY;
	private double AVG_PRICE_BY_BC;
	private double CHG_QTY;
	private int    CHG_REAS_CD;
	private double FEE_AMT;
	private double CHG_QTY_WITH_FEE;
	private String CHG_DT;
	private String TRANSACTION_KEY_VAL;
	private String CONTRACT_DT;

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
	public int getCOIN_NO() {
		return COIN_NO;
	}
	public void setCOIN_NO(int cOIN_NO) {
		COIN_NO = cOIN_NO;
	}
	public String getTRADE_DT() {
		return TRADE_DT;
	}
	public void setTRADE_DT(String tRADE_DT) {
		TRADE_DT = tRADE_DT;
	}
	public double getBALANCE_QTY() {
		return BALANCE_QTY;
	}
	public void setBALANCE_QTY(double bALANCE_QTY) {
		BALANCE_QTY = bALANCE_QTY;
	}
	public double getAVG_PRICE_BY_BC() {
		return AVG_PRICE_BY_BC;
	}
	public void setAVG_PRICE_BY_BC(double aVG_PRICE_BY_BC) {
		AVG_PRICE_BY_BC = aVG_PRICE_BY_BC;
	}
	public double getCHG_QTY() {
		return CHG_QTY;
	}
	public void setCHG_QTY(double cHG_QTY) {
		CHG_QTY = cHG_QTY;
	}
	public int getCHG_REAS_CD() {
		return CHG_REAS_CD;
	}
	public void setCHG_REAS_CD(int cHG_REAS_CD) {
		CHG_REAS_CD = cHG_REAS_CD;
	}
	public double getFEE_AMT() {
		return FEE_AMT;
	}
	public void setFEE_AMT(double fEE_AMT) {
		FEE_AMT = fEE_AMT;
	}
	public double getCHG_QTY_WITH_FEE() {
		return CHG_QTY_WITH_FEE;
	}
	public void setCHG_QTY_WITH_FEE(double cHG_QTY_WITH_FEE) {
		CHG_QTY_WITH_FEE = cHG_QTY_WITH_FEE;
	}
	public String getCHG_DT() {
		return CHG_DT;
	}
	public void setCHG_DT(String cHG_DT) {
		CHG_DT = cHG_DT;
	}
	public String getTRANSACTION_KEY_VAL() {
		return TRANSACTION_KEY_VAL;
	}
	public void setTRANSACTION_KEY_VAL(String tRANSACTION_KEY_VAL) {
		TRANSACTION_KEY_VAL = tRANSACTION_KEY_VAL;
	}
	public String getCONTRACT_DT() {
		return CONTRACT_DT;
	}
	public void setCONTRACT_DT(String cONTRACT_DT) {
		CONTRACT_DT = cONTRACT_DT;
	}

}
