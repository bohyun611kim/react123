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
 *    |_ ContractVO.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 3:31:43
 * @version : 
 * @author : Seongcheol
 */
public class ContractVO {

	private String contractDt;
	private double contractQty;
	private double contractPrc;
	private int    sellBuyRecogCd;
	
	public String getContractDt() {
		return contractDt;
	}
	public void setContractDt(String contractDt) {
		this.contractDt = contractDt;
	}
	public double getContractQty() {
		return contractQty;
	}
	public void setContractQty(double contractQty) {
		this.contractQty = contractQty;
	}
	public double getContractPrc() {
		return contractPrc;
	}
	public void setContractPrc(double contractPrc) {
		this.contractPrc = contractPrc;
	}
	public int getSellBuyRecogCd() {
		return sellBuyRecogCd;
	}
	public void setSellBuyRecogCd(int sellBuyRecogCd) {
		this.sellBuyRecogCd = sellBuyRecogCd;
	}
}
