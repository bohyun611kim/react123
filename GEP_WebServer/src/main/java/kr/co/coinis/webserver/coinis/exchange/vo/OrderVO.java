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
 *    |_ OrderVo.java
 * 
 * </pre>
 * @date : 2019. 6. 16. 오후 3:25:28
 * @version : 
 * @author : Seongcheol
 */
public class OrderVO {

	private String ordNo;
	private String ordDt;
	private int	   sellBuyCd;
	private double ordQty;
	private double ordPrc;
	private double nonContractQty;
	private String ordStatus;
	private String ordOrgNo;

	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	public int getSellBuyCd() {
		return sellBuyCd;
	}
	public void setSellBuyCd(int sellBuyCd) {
		this.sellBuyCd = sellBuyCd;
	}
	public double getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(double ordQty) {
		this.ordQty = ordQty;
	}
	public double getOrdPrc() {
		return ordPrc;
	}
	public void setOrdPrc(double ordPrc) {
		this.ordPrc = ordPrc;
	}
	public double getNonContractQty() {
		return nonContractQty;
	}
	public void setNonContractQty(double nonContractQty) {
		this.nonContractQty = nonContractQty;
	}
	public String getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}
	public String getOrdOrgNo() {
		return ordOrgNo;
	}
	public void setOrdOrgNo(String ordOrgNo) {
		this.ordOrgNo = ordOrgNo;
	}
}
