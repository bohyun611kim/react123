/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.vo 
 *    |_ ItemCodeInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 1:43:58
 * @version : 
 * @author : Seongcheol
 */
public class ItemCodeInfoVO {
	private String mktId;
	private String mktGrpId;
	private String itemCode;
	private String itemNm;
	private String itemEngNm;
	private String itemDomesticNm;
	private String itemKorNm;
	private int    basicCoinNo;
	private int    coinNo;
	private int    qtyCalcDevPnt;
	private int    amtCalcDecPnt;
	private double ordPrcUnit;
	private int    prcDspDecPnt;
	private int    qtyDspDecPnt;
	private int    volDspDecPnt;
	private int    amtDecPnt;
	private double minOrdAmt;
	private double openPrc;
	private double closePrc;
	private double highPrc;
	private double lowPrc;
	private int    daebiRecogCd; 
	private double PrcDevAmt;
	private double PrcDevRate;
	private double prevClosePrc;
	private double tradeVol;
	private double tradeAmt;
	private double toQty;
	private double fromQty;
	private double week52High;
	private String week52HighDay;
	private double week52Low;
	private String week52LowDay;
	private double sellFeeVal;
	private double buyFeeVal;
	
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
	public int getBasicCoinNo() {
		return basicCoinNo;
	}
	public void setBasicCoinNo(int basicCoinNo) {
		this.basicCoinNo = basicCoinNo;
	}
	public int getCoinNo() {
		return coinNo;
	}
	public void setCoinNo(int coinNo) {
		this.coinNo = coinNo;
	}
	public int getQtyCalcDevPnt() {
		return qtyCalcDevPnt;
	}
	public void setQtyCalcDevPnt(int qtyCalcDevPnt) {
		this.qtyCalcDevPnt = qtyCalcDevPnt;
	}
	public int getAmtCalcDecPnt() {
		return amtCalcDecPnt;
	}
	public void setAmtCalcDecPnt(int amtCalcDecPnt) {
		this.amtCalcDecPnt = amtCalcDecPnt;
	}
	public double getOrdPrcUnit() {
		return ordPrcUnit;
	}
	public void setOrdPrcUnit(double ordPrcUnit) {
		this.ordPrcUnit = ordPrcUnit;
	}
	public int getPrcDspDecPnt() {
		return prcDspDecPnt;
	}
	public void setPrcDspDecPnt(int prcDspDecPnt) {
		this.prcDspDecPnt = prcDspDecPnt;
	}
	public int getQtyDspDecPnt() {
		return qtyDspDecPnt;
	}
	public void setQtyDspDecPnt(int qtyDspDecPnt) {
		this.qtyDspDecPnt = qtyDspDecPnt;
	}
	public int getVolDspDecPnt() {
		return volDspDecPnt;
	}
	public void setVolDspDecPnt(int volDspDecPnt) {
		this.volDspDecPnt = volDspDecPnt;
	}
	public int getAmtDecPnt() {
		return amtDecPnt;
	}
	public void setAmtDecPnt(int amtDecPnt) {
		this.amtDecPnt = amtDecPnt;
	}
	public double getMinOrdAmt() {
		return minOrdAmt;
	}
	public void setMinOrdAmt(double minOrdAmt) {
		this.minOrdAmt = minOrdAmt;
	}
	public double getOpenPrc() {
		return openPrc;
	}
	public void setOpenPrc(double openPrc) {
		this.openPrc = openPrc;
	}
	public double getClosePrc() {
		return closePrc;
	}
	public void setClosePrc(double closePrc) {
		this.closePrc = closePrc;
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
	public int getDaebiRecogCd() {
		return daebiRecogCd;
	}
	public void setDaebiRecogCd(int daebiRecogCd) {
		this.daebiRecogCd = daebiRecogCd;
	}
	public double getPrcDevAmt() {
		return PrcDevAmt;
	}
	public void setPrcDevAmt(double prcDevAmt) {
		PrcDevAmt = prcDevAmt;
	}
	public double getPrcDevRate() {
		return PrcDevRate;
	}
	public void setPrcDevRate(double prcDevRate) {
		PrcDevRate = prcDevRate;
	}
	public double getPrevClosePrc() {
		return prevClosePrc;
	}
	public void setPrevClosePrc(double prevClosePrc) {
		this.prevClosePrc = prevClosePrc;
	}
	public double getTradeVol() {
		return tradeVol;
	}
	public void setTradeVol(double tradeVol) {
		this.tradeVol = tradeVol;
	}
	public double getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(double tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public double getToQty() {
		return toQty;
	}
	public void setToQty(double toQty) {
		this.toQty = toQty;
	}
	public double getFromQty() {
		return fromQty;
	}
	public void setFromQty(double fromQty) {
		this.fromQty = fromQty;
	}
	public double getWeek52High() {
		return week52High;
	}
	public void setWeek52High(double week52High) {
		this.week52High = week52High;
	}
	public String getWeek52HighDay() {
		return week52HighDay;
	}
	public void setWeek52HighDay(String week52HighDay) {
		this.week52HighDay = week52HighDay;
	}
	public double getWeek52Low() {
		return week52Low;
	}
	public void setWeek52Low(double week52Low) {
		this.week52Low = week52Low;
	}
	public String getWeek52LowDay() {
		return week52LowDay;
	}
	public void setWeek52LowDay(String week52LowDay) {
		this.week52LowDay = week52LowDay;
	}
	public double getSellFeeVal() {
		return sellFeeVal;
	}
	public void setSellFeeVal(double sellFeeVal) {
		this.sellFeeVal = sellFeeVal;
	}
	public double getBuyFeeVal() {
		return buyFeeVal;
	}
	public void setBuyFeeVal(double buyFeeVal) {
		this.buyFeeVal = buyFeeVal;
	}
}
