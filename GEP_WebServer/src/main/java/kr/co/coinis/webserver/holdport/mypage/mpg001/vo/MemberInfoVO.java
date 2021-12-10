/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg001.vo
 *    |_ MemberInfoVO.java
 *
 * </pre>
 * @date : 2019. 5. 8. 오전 8:55:22
 * @version :
 * @author : kangn
 */
public class MemberInfoVO {

	private String EXCHANGE_ID;
	private String USER_ID;
	private String LOGIN_PASSWD;
	private String LOGIN_PASSWD_CHG_DT;
	private int    AUTH_LEVEL;
	private int    AUTH_MEANS_CD;
	private int    OTP_YN;
	private byte[] OTP_KEY_VAL;
	private String OTP_SET_DT;
	private int    SMS_USE_YN;
	private String TEL_CD;
	private String MOBILE;
	private String SMS_SET_DT;
	private int    FISH_ANTI_CODE_YN;
	private byte[] FISH_ANTI_CODE_VAL;
	private String LAST_LOGIN_IPADDR;
	private String LAST_LOGIN_APP_VER;
	private String LAST_LOGIN_DT;
	private int    BLACK_LIST_YN;
	private int    AUTO_WTDRW_BLOCK_YN;
	private int    MRKT_AGREE_YN;
	private String JOIN_DT;
	private int    MEMBER_STAT_CD;
	private String UNSUBSCRIBE_DT;
	private int    UNSUBSCRIBE_REAS_CD;
	private String UNSUBSCRIBE_REAS_CONT;
	private String RECOMMENDER_CD;
	private String REG_DT;
	private String UPDT_DT;

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
	public String getLOGIN_PASSWD() {
		return LOGIN_PASSWD;
	}
	public void setLOGIN_PASSWD(String lOGIN_PASSWD) {
		LOGIN_PASSWD = lOGIN_PASSWD;
	}
	public String getLOGIN_PASSWD_CHG_DT() {
		return LOGIN_PASSWD_CHG_DT;
	}
	public void setLOGIN_PASSWD_CHG_DT(String lOGIN_PASSWD_CHG_DT) {
		LOGIN_PASSWD_CHG_DT = lOGIN_PASSWD_CHG_DT;
	}
	public int getAUTH_LEVEL() {
		return AUTH_LEVEL;
	}
	public void setAUTH_LEVEL(int aUTH_LEVEL) {
		AUTH_LEVEL = aUTH_LEVEL;
	}
	public int getAUTH_MEANS_CD() {
		return AUTH_MEANS_CD;
	}
	public void setAUTH_MEANS_CD(int aUTH_MEANS_CD) {
		AUTH_MEANS_CD = aUTH_MEANS_CD;
	}
	public int getOTP_YN() {
		return OTP_YN;
	}
	public void setOTP_YN(int oTP_YN) {
		OTP_YN = oTP_YN;
	}
	public byte[] getOTP_KEY_VAL() {
		return OTP_KEY_VAL;
	}
	public void setOTP_KEY_VAL(byte[] oTP_KEY_VAL) {
		OTP_KEY_VAL = oTP_KEY_VAL;
	}
	public String getOTP_SET_DT() {
		return OTP_SET_DT;
	}
	public void setOTP_SET_DT(String oTP_SET_DT) {
		OTP_SET_DT = oTP_SET_DT;
	}
	public int getSMS_USE_YN() {
		return SMS_USE_YN;
	}
	public void setSMS_USE_YN(int sMS_USE_YN) {
		SMS_USE_YN = sMS_USE_YN;
	}
	public String getTEL_CD() {
		return TEL_CD;
	}
	public void setTEL_CD(String tEL_CD) {
		TEL_CD = tEL_CD;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getSMS_SET_DT() {
		return SMS_SET_DT;
	}
	public void setSMS_SET_DT(String sMS_SET_DT) {
		SMS_SET_DT = sMS_SET_DT;
	}
	public int getFISH_ANTI_CODE_YN() {
		return FISH_ANTI_CODE_YN;
	}
	public void setFISH_ANTI_CODE_YN(int fISH_ANTI_CODE_YN) {
		FISH_ANTI_CODE_YN = fISH_ANTI_CODE_YN;
	}
	public byte[] getFISH_ANTI_CODE_VAL() {
		return FISH_ANTI_CODE_VAL;
	}
	public void setFISH_ANTI_CODE_VAL(byte[] fISH_ANTI_CODE_VAL) {
		FISH_ANTI_CODE_VAL = fISH_ANTI_CODE_VAL;
	}
	public String getLAST_LOGIN_IPADDR() {
		return LAST_LOGIN_IPADDR;
	}
	public void setLAST_LOGIN_IPADDR(String lAST_LOGIN_IPADDR) {
		LAST_LOGIN_IPADDR = lAST_LOGIN_IPADDR;
	}
	public String getLAST_LOGIN_APP_VER() {
		return LAST_LOGIN_APP_VER;
	}
	public void setLAST_LOGIN_APP_VER(String lAST_LOGIN_APP_VER) {
		LAST_LOGIN_APP_VER = lAST_LOGIN_APP_VER;
	}
	public String getLAST_LOGIN_DT() {
		return LAST_LOGIN_DT;
	}
	public void setLAST_LOGIN_DT(String lAST_LOGIN_DT) {
		LAST_LOGIN_DT = lAST_LOGIN_DT;
	}
	public int getBLACK_LIST_YN() {
		return BLACK_LIST_YN;
	}
	public void setBLACK_LIST_YN(int bLACK_LIST_YN) {
		BLACK_LIST_YN = bLACK_LIST_YN;
	}
	public int getAUTO_WTDRW_BLOCK_YN() {
		return AUTO_WTDRW_BLOCK_YN;
	}
	public void setAUTO_WTDRW_BLOCK_YN(int aUTO_WTDRW_BLOCK_YN) {
		AUTO_WTDRW_BLOCK_YN = aUTO_WTDRW_BLOCK_YN;
	}
	public int getMRKT_AGREE_YN() {
		return MRKT_AGREE_YN;
	}
	public void setMRKT_AGREE_YN(int mRKT_AGREE_YN) {
		MRKT_AGREE_YN = mRKT_AGREE_YN;
	}
	public String getJOIN_DT() {
		return JOIN_DT;
	}
	public void setJOIN_DT(String jOIN_DT) {
		JOIN_DT = jOIN_DT;
	}
	public int getMEMBER_STAT_CD() {
		return MEMBER_STAT_CD;
	}
	public void setMEMBER_STAT_CD(int mEMBER_STAT_CD) {
		MEMBER_STAT_CD = mEMBER_STAT_CD;
	}
	public String getUNSUBSCRIBE_DT() {
		return UNSUBSCRIBE_DT;
	}
	public void setUNSUBSCRIBE_DT(String uNSUBSCRIBE_DT) {
		UNSUBSCRIBE_DT = uNSUBSCRIBE_DT;
	}
	public int getUNSUBSCRIBE_REAS_CD() {
		return UNSUBSCRIBE_REAS_CD;
	}
	public void setUNSUBSCRIBE_REAS_CD(int uNSUBSCRIBE_REAS_CD) {
		UNSUBSCRIBE_REAS_CD = uNSUBSCRIBE_REAS_CD;
	}
	public String getUNSUBSCRIBE_REAS_CONT() {
		return UNSUBSCRIBE_REAS_CONT;
	}
	public void setUNSUBSCRIBE_REAS_CONT(String uNSUBSCRIBE_REAS_CONT) {
		UNSUBSCRIBE_REAS_CONT = uNSUBSCRIBE_REAS_CONT;
	}
	public String getRECOMMENDER_CD() {
		return RECOMMENDER_CD;
	}
	public void setRECOMMENDER_CD(String rECOMMENDER_CD) {
		RECOMMENDER_CD = rECOMMENDER_CD;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getUPDT_DT() {
		return UPDT_DT;
	}
	public void setUPDT_DT(String uPDT_DT) {
		UPDT_DT = uPDT_DT;
	}

}
