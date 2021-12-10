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
 *    |_ MarketItemVO.java
 * 
 * </pre>
 * @date : 2019. 6. 22. 오후 12:15:10
 * @version : 
 * @author : Seongcheol
 */
public class MarketItemVO {

	private String mktId;
	private String mktGrpId;
	private String itemCode;
	private String itemNm;
	private double closePrc;
	private double tradeVol;
	private double prcDevRate;
	private int    recogCd;
	private int    prcDspDecPnt;
	private int    volDspDecPnt;

	public String getMktId() {
		return mktId;
	}
	public void setMktId(String mktId) {
		this.mktId = mktId;
	}
	public String getMktGrpId() {
		return mktGrpId;
	}
	public void setMktGrpId(String mktGrpId) {
		this.mktGrpId = mktGrpId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public double getClosePrc() {
		return closePrc;
	}
	public void setClosePrc(double closePrc) {
		this.closePrc = closePrc;
	}
	public double getTradeVol() {
		return tradeVol;
	}
	public void setTradeVol(double tradeVol) {
		this.tradeVol = tradeVol;
	}
	public double getPrcDevRate() {
		return prcDevRate;
	}
	public void setPrcDevRate(double prcDevRate) {
		this.prcDevRate = prcDevRate;
	}
	public int getRecogCd() {
		return recogCd;
	}
	public void setRecogCd(int recogCd) {
		this.recogCd = recogCd;
	}
	public int getPrcDspDecPnt() {
		return prcDspDecPnt;
	}
	public void setPrcDspDecPnt(int prcDspDecPnt) {
		this.prcDspDecPnt = prcDspDecPnt;
	}
	public int getVolDspDecPnt() {
		return volDspDecPnt;
	}
	public void setVolDspDecPnt(int volDspDecPnt) {
		this.volDspDecPnt = volDspDecPnt;
	}
}
