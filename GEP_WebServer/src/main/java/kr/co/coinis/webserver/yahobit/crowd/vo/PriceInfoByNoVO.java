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
 *    |_ PriceInfoByNoVO.java
 * 
 * </pre>
 * @date : 2019. 6. 15. 오후 4:38:58
 * @version : 
 * @author : Seongcheol
 */
public class PriceInfoByNoVO {

	private double payBascicQty;
	private double slBasicQty;
	private double payOrdUnitQty;
	private double payMinQty;
	private double payMaxQty;
	private double bouns;

	public double getPayBascicQty() {
		return payBascicQty;
	}
	public void setPayBascicQty(double payBascicQty) {
		this.payBascicQty = payBascicQty;
	}
	public double getSlBasicQty() {
		return slBasicQty;
	}
	public void setSlBasicQty(double slBasicQty) {
		this.slBasicQty = slBasicQty;
	}
	public double getPayOrdUnitQty() {
		return payOrdUnitQty;
	}
	public void setPayOrdUnitQty(double payOrdUnitQty) {
		this.payOrdUnitQty = payOrdUnitQty;
	}
	public double getPayMinQty() {
		return payMinQty;
	}
	public void setPayMinQty(double payMinQty) {
		this.payMinQty = payMinQty;
	}
	public double getPayMaxQty() {
		return payMaxQty;
	}
	public void setPayMaxQty(double payMaxQty) {
		this.payMaxQty = payMaxQty;
	}
	public double getBouns() {
		return bouns;
	}
	public void setBouns(double bouns) {
		this.bouns = bouns;
	}
}
