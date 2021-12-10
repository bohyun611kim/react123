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
 *    |_ HogaVO.java
 * 
 * </pre>
 * @date : 2019. 6. 16. 오후 3:08:40
 * @version : 
 * @author : Seongcheol
 */
public class HogaVO {

	private double ordPrc;
	private double hogaQty;
	private double hogaCnt;

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
	public double getHogaCnt() {
		return hogaCnt;
	}
	public void setHogaCnt(double hogaCnt) {
		this.hogaCnt = hogaCnt;
	}
}
