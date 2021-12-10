/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ CodeVO.java
 * 
 * </pre>
 * @date : 2019. 7. 8. 오후 5:18:47
 * @version : 
 * @author : Seongcheol
 */
public class CodeVO {

	private String codeValNm;
	private int    codeNumVal;
	private String codeStrVal;

	public String getCodeValNm() {
		return codeValNm;
	}
	public void setCodeValNm(String codeValNm) {
		this.codeValNm = codeValNm;
	}
	public int getCodeNumVal() {
		return codeNumVal;
	}
	public void setCodeNumVal(int codeNumVal) {
		this.codeNumVal = codeNumVal;
	}
	public String getCodeStrVal() {
		return codeStrVal;
	}
	public void setCodeStrVal(String codeStrVal) {
		this.codeStrVal = codeStrVal;
	}
}
