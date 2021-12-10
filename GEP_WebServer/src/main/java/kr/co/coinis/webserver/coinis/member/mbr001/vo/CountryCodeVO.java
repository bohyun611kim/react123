/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr001.vo 
 *    |_ CountryCodeVO.java
 * 
 * </pre>
 * @date : 2019. 3. 29. 오전 9:51:50
 * @version : 
 * @author : Seongcheol
 */
public class CountryCodeVO {

	private String countryCd;
	private String engNm;
	
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getEngNm() {
		return engNm;
	}
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
}
