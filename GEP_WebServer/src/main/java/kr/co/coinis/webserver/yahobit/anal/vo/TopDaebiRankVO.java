/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.vo 
 *    |_ TopDaebiVO.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 10:09:35
 * @version : 
 * @author : Seongcheol
 */
public class TopDaebiRankVO {
	
	private String itemKorNm;
	private String itemEngNm;
	private String itemNm;
	private double rate;

	public String getItemKorNm() {
		return itemKorNm;
	}
	public void setItemKorNm(String itemKorNm) {
		this.itemKorNm = itemKorNm;
	}
	public String getItemEngNm() {
		return itemEngNm;
	}
	public void setItemEngNm(String itemEngNm) {
		this.itemEngNm = itemEngNm;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}
