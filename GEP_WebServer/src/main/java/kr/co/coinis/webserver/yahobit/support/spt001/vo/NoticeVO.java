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
 * kr.co.coinis.webserver.mypage.mpg001.vo 
 *    |_ NoticeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 5:19:25
 * @version : 
 * @author : kangn
 */
public class NoticeVO {

	private long   REC_SEQ_NO;
	private String TITLE;
	private int    URGENT_YN;
	private int    TOP_DISP_YN;
	private int    TARGET_ALL_YN;
	private String EXCHANGE_ID;
	private String CONTENTS;
	private String DISP_START_DT;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
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
	public int getURGENT_YN() {
		return URGENT_YN;
	}
	public void setURGENT_YN(int uRGENT_YN) {
		URGENT_YN = uRGENT_YN;
	}
	public int getTOP_DISP_YN() {
		return TOP_DISP_YN;
	}
	public void setTOP_DISP_YN(int tOP_DISP_YN) {
		TOP_DISP_YN = tOP_DISP_YN;
	}
	public int getTARGET_ALL_YN() {
		return TARGET_ALL_YN;
	}
	public void setTARGET_ALL_YN(int tARGET_ALL_YN) {
		TARGET_ALL_YN = tARGET_ALL_YN;
	}
	public String getEXCHANGE_ID() {
		return EXCHANGE_ID;
	}
	public void setEXCHANGE_ID(String eXCHANGE_ID) {
		EXCHANGE_ID = eXCHANGE_ID;
	}
	public String getCONTENTS() {
		return CONTENTS;
	}
	public void setCONTENTS(String cONTENTS) {
		CONTENTS = cONTENTS;
	}
	public String getDISP_START_DT() {
		return DISP_START_DT;
	}
	public void setDISP_START_DT(String dISP_START_DT) {
		DISP_START_DT = dISP_START_DT;
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
