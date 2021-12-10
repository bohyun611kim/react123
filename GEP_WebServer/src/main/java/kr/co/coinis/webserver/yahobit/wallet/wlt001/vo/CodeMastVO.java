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
 *    |_ CodeMastVO.java
 * 
 * </pre>
 * @date : 2019. 4. 27. 오후 3:33:34
 * @version : 
 * @author : kangn
 */
public class CodeMastVO {

	private String CODE_ID;
	private String LANG_CD;
	private String CODE_NM;
	private int    VALUE_TYPE_CD;
	private String CODE_DESCR;
	private String REG_DT;
	private String REG_USER_ID;
	private String UPDT_DT;
	private String UPDT_USER_ID;
	
	public String getCODE_ID() {
		return CODE_ID;
	}
	public void setCODE_ID(String cODE_ID) {
		CODE_ID = cODE_ID;
	}
	public String getLANG_CD() {
		return LANG_CD;
	}
	public void setLANG_CD(String lANG_CD) {
		LANG_CD = lANG_CD;
	}
	public String getCODE_NM() {
		return CODE_NM;
	}
	public void setCODE_NM(String cODE_NM) {
		CODE_NM = cODE_NM;
	}
	public int getVALUE_TYPE_CD() {
		return VALUE_TYPE_CD;
	}
	public void setVALUE_TYPE_CD(int vALUE_TYPE_CD) {
		VALUE_TYPE_CD = vALUE_TYPE_CD;
	}
	public String getCODE_DESCR() {
		return CODE_DESCR;
	}
	public void setCODE_DESCR(String cODE_DESCR) {
		CODE_DESCR = cODE_DESCR;
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
