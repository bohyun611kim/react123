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
 *    |_ TodayTopAmtVO.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 10:09:16
 * @version : 
 * @author : Seongcheol
 */
public class TodayTopAmtVO {
	
	private int    daebiRecogCd;
	private double priceDevRate;
	private String itemKorNm;
	private String itemEngNm;
	private String itemNm;
	private int    pnt;
	private double tradeAmtKrw;
	private double tradeAmtKrwYes;

	public int getDaebiRecogCd() {
		return daebiRecogCd;
	}
	public void setDaebiRecogCd(int daebiRecogCd) {
		this.daebiRecogCd = daebiRecogCd;
	}
	public double getPriceDevRate() {
		return priceDevRate;
	}
	public void setPriceDevRate(double priceDevRate) {
		this.priceDevRate = priceDevRate;
	}
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
	public int getPnt() {
		return pnt;
	}
	public void setPnt(int pnt) {
		this.pnt = pnt;
	}
	public double getTradeAmtKrw() {
		return tradeAmtKrw;
	}
	public void setTradeAmtKrw(double tradeAmtKrw) {
		this.tradeAmtKrw = tradeAmtKrw;
	}
	public double getTradeAmtKrwYes() {
		return tradeAmtKrwYes;
	}
	public void setTradeAmtKrwYes(double tradeAmtKrwYes) {
		this.tradeAmtKrwYes = tradeAmtKrwYes;
	}
}
