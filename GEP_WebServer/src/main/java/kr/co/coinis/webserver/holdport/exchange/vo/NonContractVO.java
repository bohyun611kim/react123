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
 *    |_ NonContractVO.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 2:09:35
 * @version : 
 * @author : Seongcheol
 */
public class NonContractVO {

	private String tranOrdNo;
	private String ordOrdNo;
	private String excOrdNo;
	private String itemCode;
	private int    sellBuyCd;
	private int	   ordTypeCd;
	private double ordPrc;
	private double nonQty;
	private double contractQty;
	private String ordDt;

	public String getTranOrdNo() {
		return tranOrdNo;
	}
	public void setTranOrdNo(String tranOrdNo) {
		this.tranOrdNo = tranOrdNo;
	}
	public String getOrdOrdNo() {
		return ordOrdNo;
	}
	public void setOrdOrdNo(String ordOrdNo) {
		this.ordOrdNo = ordOrdNo;
	}
	public String getExcOrdNo() {
		return excOrdNo;
	}
	public void setExcOrdNo(String excOrdNo) {
		this.excOrdNo = excOrdNo;
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
	public int getOrdTypeCd() {
		return ordTypeCd;
	}
	public void setOrdTypeCd(int ordTypeCd) {
		this.ordTypeCd = ordTypeCd;
	}
	public double getOrdPrc() {
		return ordPrc;
	}
	public void setOrdPrc(double ordPrc) {
		this.ordPrc = ordPrc;
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
