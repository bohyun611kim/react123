/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. (Confidential Information).
 */
package kr.co.coinis.webserver.holdport.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.exchange.vo 
 *    |_ MarketItemCodeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 8:46:54
 * @version : 
 * @author : Seongcheol
 */
public class MarketItemCodeVO {

	private String mktId;
	private String mktGrpId;
	private String itemCode;
	private String itemNm;
	private String itemEngNm;
	private String itemDomesticNm;
	private String itemKorNm;
	private double closePrc;
	private double tradeAmt;
	private double tradeVol;
	private double prcDevAmt;
	private double prcDevRate;
	private int    prcDspDecPnt; 
	private int    amtDecPnt;
	private int    volDecPnt;
	private int	   daebiCd;
	private double basicClosePrc;
	private double closePrcYep;
	private double highPrc; //++2021/08/11 coben
	private double lowPrc; //++2021/08/11 coben
	private double minOrdAmt; //++2021/08/13
	private double minOrdQty; //++2021/08/13

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
	public String getItemEngNm() {
		return itemEngNm;
	}
	public void setItemEngNm(String itemEngNm) {
		this.itemEngNm = itemEngNm;
	}
	public String getItemDomesticNm() {
		return itemDomesticNm;
	}
	public void setItemDomesticNm(String itemDomesticNm) {
		this.itemDomesticNm = itemDomesticNm;
	}
	public String getItemKorNm() {
		return itemKorNm;
	}
	public void setItemKorNm(String itemKorNm) {
		this.itemKorNm = itemKorNm;
	}
	public double getClosePrc() {
		return closePrc;
	}
	public void setClosePrc(double closePrc) {
		this.closePrc = closePrc;
	}
	public double getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(double tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public double getTradeVol() {
		return tradeVol;
	}
	public void setTradeVol(double tradeVol) {
		this.tradeVol = tradeVol;
	}
	public double getPrcDevAmt() {
		return prcDevAmt;
	}
	public void setPrcDevAmt(double prcDevAmt) {
		this.prcDevAmt = prcDevAmt;
	}
	public double getPrcDevRate() {
		return prcDevRate;
	}
	public void setPrcDevRate(double prcDevRate) {
		this.prcDevRate = prcDevRate;
	}
	public int getPrcDspDecPnt() {
		return prcDspDecPnt;
	}
	public void setPrcDspDecPnt(int prcDspDecPnt) {
		this.prcDspDecPnt = prcDspDecPnt;
	}
	public int getAmtDecPnt() {
		return amtDecPnt;
	}
	public void setAmtDecPnt(int amtDecPnt) {
		this.amtDecPnt = amtDecPnt;
	}
	public int getVolDecPnt() {
		return volDecPnt;
	}
	public void setVolDecPnt(int volDecPnt) {
		this.volDecPnt = volDecPnt;
	}
	public int getDaebiCd() {
		return daebiCd;
	}
	public void setDaebiCd(int daebiCd) {
		this.daebiCd = daebiCd;
	}
	public double getBasicClosePrc() {
		return basicClosePrc;
	}
	public void setBasicClosePrc(double basicClosePrc) {
		this.basicClosePrc = basicClosePrc;
	}
	public double getClosePrcYep() {
		return closePrcYep;
	}
	public void setClosePrcYep(double closePrcYep) {
		this.closePrcYep = closePrcYep;
	}
	public double getHighPrc() {
		return highPrc;
	}
	public void setHighPrc(double highPrc) {
		this.highPrc = highPrc;
	}
	public double getLowPrc() {
		return lowPrc;
	}
	public void setLowPrc(double lowPrc) {
		this.lowPrc = lowPrc;
	}
	public double getMinOrdAmt() {
		return minOrdAmt;
	}
	public void setMinOrdAmt(double minOrdAmt) {
		this.minOrdAmt = minOrdAmt;
	}
	public double getMinOrdQty() {
		return minOrdQty;
	}
	public void setMinOrdQty(double minOrdQty) {
		this.minOrdQty = minOrdQty;
	}

	
}
