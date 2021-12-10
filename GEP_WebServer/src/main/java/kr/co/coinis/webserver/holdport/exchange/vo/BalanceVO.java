/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.exchange.vo 
 *    |_ BalanceVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 8:46:02
 * @version : 
 * @author : Seongcheol
 */
public class BalanceVO {
	
	private int    coinNo;
	private double balanceQty;
	private double avgPrc;
	private String symbol;
	private String coinKorNm;
	private int    qtyDspDecPnt;
	private int    bsCoinNo;
	private String bsSymbol;
	private int    prcDspDecPnt;
	private String itemCode;
	private double closePrc;
	private double closePrcYep;

	public int getCoinNo() {
		return coinNo;
	}
	public void setCoinNo(int coinNo) {
		this.coinNo = coinNo;
	}
	public double getBalanceQty() {
		return balanceQty;
	}
	public void setBalanceQty(double balanceQty) {
		this.balanceQty = balanceQty;
	}
	public double getAvgPrc() {
		return avgPrc;
	}
	public void setAvgPrc(double avgPrc) {
		this.avgPrc = avgPrc;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCoinKorNm() {
		return coinKorNm;
	}
	public void setCoinKorNm(String coinKorNm) {
		this.coinKorNm = coinKorNm;
	}
	public int getQtyDspDecPnt() {
		return qtyDspDecPnt;
	}
	public void setQtyDspDecPnt(int qtyDspDecPnt) {
		this.qtyDspDecPnt = qtyDspDecPnt;
	}
	public int getBsCoinNo() {
		return bsCoinNo;
	}
	public void setBsCoinNo(int bsCoinNo) {
		this.bsCoinNo = bsCoinNo;
	}
	public String getBsSymbol() {
		return bsSymbol;
	}
	public void setBsSymbol(String bsSymbol) {
		this.bsSymbol = bsSymbol;
	}
	public int getPrcDspDecPnt() {
		return prcDspDecPnt;
	}
	public void setPrcDspDecPnt(int prcDspDecPnt) {
		this.prcDspDecPnt = prcDspDecPnt;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public double getClosePrc() {
		return closePrc;
	}
	public void setClosePrc(double closePrc) {
		this.closePrc = closePrc;
	}
	public double getClosePrcYep() {
		return closePrcYep;
	}
	public void setClosePrcYep(double closePrcYep) {
		this.closePrcYep = closePrcYep;
	}
}
