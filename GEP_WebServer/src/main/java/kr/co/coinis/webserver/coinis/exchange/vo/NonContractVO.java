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
 *    |_ NonContractVo.java
 * 
 * </pre>
 * @date : 2019. 6. 16. 오후 3:25:51
 * @version : 
 * @author : Seongcheol
 */
public class NonContractVO {
	
	private String tranNo;
	private String ordNo;
	private String excNo;
	private String itemCode;
	private int sellBuyCd;
	private double ordPrc;
	private double ordQty;
	private double nonQty;
	private double contractQty;
	private String ordDt;

	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getExcNo() {
		return excNo;
	}
	public void setExcNo(String excNo) {
		this.excNo = excNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getSellBuyCd() {
		return sellBuyCd;
	}
	public void setSellBuyCd(int sellBuyCd) {
		this.sellBuyCd = sellBuyCd;
	}
	public double getOrdPrc() {
		return ordPrc;
	}
	public void setOrdPrc(double ordPrc) {
		this.ordPrc = ordPrc;
	}
	public double getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(double ordQty) {
		this.ordQty = ordQty;
	}
	public double getNonQty() {
		return nonQty;
	}
	public void setNonQty(double nonQty) {
		this.nonQty = nonQty;
	}
	public double getContractQty() {
		return contractQty;
	}
	public void setContractQty(double contractQty) {
		this.contractQty = contractQty;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
}
