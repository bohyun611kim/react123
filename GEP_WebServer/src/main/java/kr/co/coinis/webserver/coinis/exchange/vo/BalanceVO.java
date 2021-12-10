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
 *    |_ BalanceVO.java
 * 
 * </pre>
 * @date : 2019. 6. 22. 오후 12:20:20
 * @version : 
 * @author : Seongcheol
 */
public class BalanceVO {

	private int    coinNo;
	private double avgPrcByBc;
	private int    coinDecPnt;
	private String coinSymbol;
	private int    bcCoinNo;
	private int    bcCoinDecPnt;
	private String bcCoinSymbol;
	private String itemCode;
	private double closePrc;
	private double balanceQty;

	public int getCoinNo() {
		return coinNo;
	}
	public void setCoinNo(int coinNo) {
		this.coinNo = coinNo;
	}
	public double getAvgPrcByBc() {
		return avgPrcByBc;
	}
	public void setAvgPrcByBc(double avgPrcByBc) {
		this.avgPrcByBc = avgPrcByBc;
	}
	public int getCoinDecPnt() {
		return coinDecPnt;
	}
	public void setCoinDecPnt(int coinDecPnt) {
		this.coinDecPnt = coinDecPnt;
	}
	public String getCoinSymbol() {
		return coinSymbol;
	}
	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}
	public int getBcCoinNo() {
		return bcCoinNo;
	}
	public void setBcCoinNo(int bcCoinNo) {
		this.bcCoinNo = bcCoinNo;
	}
	public int getBcCoinDecPnt() {
		return bcCoinDecPnt;
	}
	public void setBcCoinDecPnt(int bcCoinDecPnt) {
		this.bcCoinDecPnt = bcCoinDecPnt;
	}
	public String getBcCoinSymbol() {
		return bcCoinSymbol;
	}
	public void setBcCoinSymbol(String bcCoinSymbol) {
		this.bcCoinSymbol = bcCoinSymbol;
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
	public double getBalanceQty() {
		return balanceQty;
	}
	public void setBalanceQty(double balanceQty) {
		this.balanceQty = balanceQty;
	}
}
