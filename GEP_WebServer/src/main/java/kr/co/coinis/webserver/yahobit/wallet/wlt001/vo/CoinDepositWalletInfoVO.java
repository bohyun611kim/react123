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
 *    |_ CoinDepositWalletInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 28. 오후 2:27:11
 * @version : 
 * @author : kangn
 */
public class CoinDepositWalletInfoVO {

	private String EXCHANGE_ID;
	private String USER_ID;
	private int    COIN_NO;
	private int    ISSUE_SEQ_NO;
	private String DEPOSIT_WALLET_ADDR;
	private String ADDR_ETC1;
	private String ADDR_ETC2;
	private String WALLET_PRIVATE_KEY;
	private String REG_DT;
	private int    USE_YN;
	
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
	public int getISSUE_SEQ_NO() {
		return ISSUE_SEQ_NO;
	}
	public void setISSUE_SEQ_NO(int iSSUE_SEQ_NO) {
		ISSUE_SEQ_NO = iSSUE_SEQ_NO;
	}
	public String getDEPOSIT_WALLET_ADDR() {
		return DEPOSIT_WALLET_ADDR;
	}
	public void setDEPOSIT_WALLET_ADDR(String dEPOSIT_WALLET_ADDR) {
		DEPOSIT_WALLET_ADDR = dEPOSIT_WALLET_ADDR;
	}
	public String getADDR_ETC1() {
		return ADDR_ETC1;
	}
	public void setADDR_ETC1(String aDDR_ETC1) {
		ADDR_ETC1 = aDDR_ETC1;
	}
	public String getADDR_ETC2() {
		return ADDR_ETC2;
	}
	public void setADDR_ETC2(String aDDR_ETC2) {
		ADDR_ETC2 = aDDR_ETC2;
	}
	public String getWALLET_PRIVATE_KEY() {
		return WALLET_PRIVATE_KEY;
	}
	public void setWALLET_PRIVATE_KEY(String wALLET_PRIVATE_KEY) {
		WALLET_PRIVATE_KEY = wALLET_PRIVATE_KEY;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public int getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(int uSE_YN) {
		USE_YN = uSE_YN;
	}

}
