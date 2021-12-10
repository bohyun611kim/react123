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
 *    |_ ContractVo.java
 * 
 * </pre>
 * @date : 2019. 6. 16. 오후 3:25:38
 * @version : 
 * @author : Seongcheol
 */
public class ContractVO {

	private String ordDt;
	private double ordPrc;
	private double ordQty;
	private String contractDt;
	private double contractPrc;
	private double contractQty;
	private double nonContractQty;
	private int    sellBuyCd;
	
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
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
	public String getContractDt() {
		return contractDt;
	}
	public void setContractDt(String contractDt) {
		this.contractDt = contractDt;
	}
	public double getContractPrc() {
		return contractPrc;
	}
	public void setContractPrc(double contractPrc) {
		this.contractPrc = contractPrc;
	}
	public double getContractQty() {
		return contractQty;
	}
	public void setContractQty(double contractQty) {
		this.contractQty = contractQty;
	}
	public double getNonContractQty() {
		return nonContractQty;
	}
	public void setNonContractQty(double nonContractQty) {
		this.nonContractQty = nonContractQty;
	}
	public int getSellBuyCd() {
		return sellBuyCd;
	}
	public void setSellBuyCd(int sellBuyCd) {
		this.sellBuyCd = sellBuyCd;
	}
}
