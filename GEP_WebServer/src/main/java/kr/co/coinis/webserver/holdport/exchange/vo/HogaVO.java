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
 *    |_ HogaVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오후 1:31:24
 * @version : 
 * @author : Seongcheol
 */
public class HogaVO {

	private double ordPrc;
	private double hogaQty;
	private int    hogaCnt;

	public double getOrdPrc() {
		return ordPrc;
	}
	public void setOrdPrc(double ordPrc) {
		this.ordPrc = ordPrc;
	}
	public double getHogaQty() {
		return hogaQty;
	}
	public void setHogaQty(double hogaQty) {
		this.hogaQty = hogaQty;
	}
	public int getHogaCnt() {
		return hogaCnt;
	}
	public void setHogaCnt(int hogaCnt) {
		this.hogaCnt = hogaCnt;
	}
}
