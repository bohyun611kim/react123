/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.exchange.vo 
 *    |_ CodeVO.java
 * 
 * </pre>
 * @date : 2019. 6. 21. 오후 3:53:20
 * @version : 
 * @author : Seongcheol
 */
public class CodeVO {

	private String codeValNm;
	private String codeNumVal;
	private String codeStrVal;

	public String getCodeValNm() {
		return codeValNm;
	}
	public void setCodeValNm(String codeValNm) {
		this.codeValNm = codeValNm;
	}
	public String getCodeNumVal() {
		return codeNumVal;
	}
	public void setCodeNumVal(String codeNumVal) {
		this.codeNumVal = codeNumVal;
	}
	public String getCodeStrVal() {
		return codeStrVal;
	}
	public void setCodeStrVal(String codeStrVal) {
		this.codeStrVal = codeStrVal;
	}
}
