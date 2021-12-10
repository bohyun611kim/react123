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
 * kr.co.coinis.webserver.holdport.support.spt001.vo 
 *    |_ EventVO.java
 * 
 * </pre>
 * @date : 2019. 5. 17. 오전 10:47:49
 * @version : 
 * @author : kangn
 */
public class EventVO {

	private String EXCHANGE_ID;
	private long   REC_SEQ_NO;
	private String TITLE;
	private int    USE_YN;
	private String EVENT_START_DT;
	private String EVENT_END_DT;
	private String DISPLAY_START_DT;
	private String DISPLAY_END_DT;
	private String EVENT_URL;
	private String BANNER_URL;
	private int    HOME_DISPAY_YN;
	private String EVENT_PAGE_CONTENTS;
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
	public long getREC_SEQ_NO() {
		return REC_SEQ_NO;
	}
	public void setREC_SEQ_NO(long rEC_SEQ_NO) {
		REC_SEQ_NO = rEC_SEQ_NO;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public int getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(int uSE_YN) {
		USE_YN = uSE_YN;
	}
	public String getEVENT_START_DT() {
		return EVENT_START_DT;
	}
	public void setEVENT_START_DT(String eVENT_START_DT) {
		EVENT_START_DT = eVENT_START_DT;
	}
	public String getEVENT_END_DT() {
		return EVENT_END_DT;
	}
	public void setEVENT_END_DT(String eVENT_END_DT) {
		EVENT_END_DT = eVENT_END_DT;
	}
	public String getDISPLAY_START_DT() {
		return DISPLAY_START_DT;
	}
	public void setDISPLAY_START_DT(String dISPLAY_START_DT) {
		DISPLAY_START_DT = dISPLAY_START_DT;
	}
	public String getDISPLAY_END_DT() {
		return DISPLAY_END_DT;
	}
	public void setDISPLAY_END_DT(String dISPLAY_END_DT) {
		DISPLAY_END_DT = dISPLAY_END_DT;
	}
	public String getEVENT_URL() {
		return EVENT_URL;
	}
	public void setEVENT_URL(String eVENT_URL) {
		EVENT_URL = eVENT_URL;
	}
	public String getBANNER_URL() {
		return BANNER_URL;
	}
	public void setBANNER_URL(String bANNER_URL) {
		BANNER_URL = bANNER_URL;
	}
	public int getHOME_DISPAY_YN() {
		return HOME_DISPAY_YN;
	}
	public void setHOME_DISPAY_YN(int hOME_DISPAY_YN) {
		HOME_DISPAY_YN = hOME_DISPAY_YN;
	}
	public String getEVENT_PAGE_CONTENTS() {
		return EVENT_PAGE_CONTENTS;
	}
	public void setEVENT_PAGE_CONTENTS(String eVENT_PAGE_CONTENTS) {
		EVENT_PAGE_CONTENTS = eVENT_PAGE_CONTENTS;
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
