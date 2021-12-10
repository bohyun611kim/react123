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
 *    |_ BalanceInfoVO.java
 * 
 * </pre>
 * @date : 2019. 6. 22. 오후 5:09:01
 * @version : 
 * @author : Seongcheol
 */
public class BalanceInfoVO {

	private double balanceQty;
	private double possibleQty;
	private double nonQty;
	private double convertQty;
	private int    bcCoinPnt;

	public double getBalanceQty() {
		return balanceQty;
	}
	public void setBalanceQty(double balanceQty) {
		this.balanceQty = balanceQty;
	}
	public double getPossibleQty() {
		return possibleQty;
	}
	public void setPossibleQty(double possibleQty) {
		this.possibleQty = possibleQty;
	}
	public double getNonQty() {
		return nonQty;
	}
	public void setNonQty(double nonQty) {
		this.nonQty = nonQty;
	}
	public double getConvertQty() {
		return convertQty;
	}
	public void setConvertQty(double convertQty) {
		this.convertQty = convertQty;
	}
	public int getBcCoinPnt() {
		return bcCoinPnt;
	}
	public void setBcCoinPnt(int bcCoinPnt) {
		this.bcCoinPnt = bcCoinPnt;
	}
}
