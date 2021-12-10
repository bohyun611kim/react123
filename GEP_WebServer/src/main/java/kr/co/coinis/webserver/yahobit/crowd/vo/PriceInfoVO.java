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
 *    |_ PriceInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 27. 오전 10:44:08
 * @version : 
 * @author : Seongcheol
 */
public class PriceInfoVO {

	private int    payCoinNo;
	private String symbol;
	private double payCoinBasicQty;
	private double slCoinBasicQty;
	private double payCoinUnitQty;
	private double payCoinMinQty;
	private double payCoinMaxQty;

	public int getPayCoinNo() {
		return payCoinNo;
	}
	public void setPayCoinNo(int payCoinNo) {
		this.payCoinNo = payCoinNo;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPayCoinBasicQty() {
		return payCoinBasicQty;
	}
	public void setPayCoinBasicQty(double payCoinBasicQty) {
		this.payCoinBasicQty = payCoinBasicQty;
	}
	public double getSlCoinBasicQty() {
		return slCoinBasicQty;
	}
	public void setSlCoinBasicQty(double slCoinBasicQty) {
		this.slCoinBasicQty = slCoinBasicQty;
	}
	public double getPayCoinUnitQty() {
		return payCoinUnitQty;
	}
	public void setPayCoinUnitQty(double payCoinUnitQty) {
		this.payCoinUnitQty = payCoinUnitQty;
	}
	public double getPayCoinMinQty() {
		return payCoinMinQty;
	}
	public void setPayCoinMinQty(double payCoinMinQty) {
		this.payCoinMinQty = payCoinMinQty;
	}
	public double getPayCoinMaxQty() {
		return payCoinMaxQty;
	}
	public void setPayCoinMaxQty(double payCoinMaxQty) {
		this.payCoinMaxQty = payCoinMaxQty;
	}
}
