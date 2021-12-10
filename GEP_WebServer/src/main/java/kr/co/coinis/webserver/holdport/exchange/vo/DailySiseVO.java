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
 *    |_ DailySiseVO.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 1:52:36
 * @version : 
 * @author : Seongcheol
 */
public class DailySiseVO {

	private String tradeDt;
	private double closePrc;
	private int    daebiRecogCd;
	private double prcDevAmt;
	private double prcDevRate;
	private double tradeVol;
	private double tradeAmt;

	public String getTradeDt() {
		return tradeDt;
	}
	public void setTradeDt(String tradeDt) {
		this.tradeDt = tradeDt;
	}
	public double getClosePrc() {
		return closePrc;
	}
	public void setClosePrc(double closePrc) {
		this.closePrc = closePrc;
	}
	public int getDaebiRecogCd() {
		return daebiRecogCd;
	}
	public void setDaebiRecogCd(int daebiRecogCd) {
		this.daebiRecogCd = daebiRecogCd;
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
}
