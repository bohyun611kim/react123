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
 *    |_ RealContractVO.java
 * 
 * </pre>
 * 
 * @date : 2019. 4. 30. 오후 7:58:55
 * @version :
 * @author : Seongcheol
 */
public class RealContractVO {

	private String contractDt;
	private int contractRecogCd;
	private double contractQty;
	private double contractPrc;
	private String ifTransactionId;

	public String getContractDt() {
		return contractDt;
	}

	public void setContractDt(String contractDt) {
		this.contractDt = contractDt;
	}

	public int getContractRecogCd() {
		return contractRecogCd;
	}

	public void setContractRecogCd(int contractRecogCd) {
		this.contractRecogCd = contractRecogCd;
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

	public String getIfTransactionId() {
		return ifTransactionId;
	}

	public void setIfTransactionId(String ifTransactionId) {
		this.ifTransactionId = ifTransactionId;
	}
}
