/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.about.abt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.about.abt001.vo 
 *    |_ InquiryVO.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 9:12:30
 * @version : 
 * @author : kangn
 */
public class InquiryVO {

	private int    INQUIRY_NO;
	private String EXCHANGE_ID;
	private String USER_ID;
	private int    INQUIRY_TYPE_CD;
	private String INQUIRY_TITLE;
	private String INQUIRY_CONTENTS;
	private String REG_DT;
	
	public int getINQUIRY_NO() {
		return INQUIRY_NO;
	}
	public void setINQUIRY_NO(int iNQUIRY_NO) {
		INQUIRY_NO = iNQUIRY_NO;
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
	public int getINQUIRY_TYPE_CD() {
		return INQUIRY_TYPE_CD;
	}
	public void setINQUIRY_TYPE_CD(int iNQUIRY_TYPE_CD) {
		INQUIRY_TYPE_CD = iNQUIRY_TYPE_CD;
	}
	public String getINQUIRY_TITLE() {
		return INQUIRY_TITLE;
	}
	public void setINQUIRY_TITLE(String iNQUIRY_TITLE) {
		INQUIRY_TITLE = iNQUIRY_TITLE;
	}
	public String getINQUIRY_CONTENTS() {
		return INQUIRY_CONTENTS;
	}
	public void setINQUIRY_CONTENTS(String iNQUIRY_CONTENTS) {
		INQUIRY_CONTENTS = iNQUIRY_CONTENTS;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
}
