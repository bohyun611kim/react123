/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd001.vo;

/**
 * <pre>
 * kr.co.coinis.openapi.agent.camel.processor.order.vo
 *    |_ OrderResultVO.java
 *
 * </pre>
 * @date : 2019. 9. 2. 오전 9:39:03
 * @version :
 * @author : Seongcheol
 */
public class OrderResultVO {

	private int    ordStatusCd;
	private double nonContractQty;

	public int getOrdStatusCd() {
		return ordStatusCd;
	}
	public void setOrdStatusCd(int ordStatusCd) {
		this.ordStatusCd = ordStatusCd;
	}
	public double getNonContractQty() {
		return nonContractQty;
	}
	public void setNonContractQty(double nonContractQty) {
		this.nonContractQty = nonContractQty;
	}

}
