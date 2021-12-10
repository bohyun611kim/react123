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
 *    |_ ItemCodeBalanceVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오후 8:24:04
 * @version : 
 * @author : Seongcheol
 */
public class ItemCodeBalanceVO {

	private double toQty;
	private double fromQty;

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
}
