/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.vo 
 *    |_ BalanceVO.java
 * 
 * </pre>
 * @date : 2019. 5. 27. 오전 10:16:24
 * @version : 
 * @author : Seongcheol
 */
public class BalanceVO {

	private int    coinNo;
	private String symbol;
	private double qty;
	private int    pnt;

	public int getCoinNo() {
		return coinNo;
	}
	public void setCoinNo(int coinNo) {
		this.coinNo = coinNo;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public int getPnt() {
		return pnt;
	}
	public void setPnt(int pnt) {
		this.pnt = pnt;
	}
}
