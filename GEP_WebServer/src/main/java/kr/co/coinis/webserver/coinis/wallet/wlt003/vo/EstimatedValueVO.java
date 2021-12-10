/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt003.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt003.vo 
 *    |_ EstimatedValueVO.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 8:39:51
 * @version : 
 * @author : hanyeonseo-internet
 */
public class EstimatedValueVO {
	
	private double estimated_by_bc;
	private double estimated_by_currency;

	public double getEstimated_by_bc() {
		return estimated_by_bc;
	}
	public void setEstimated_by_bc(double estimated_by_bc) {
		this.estimated_by_bc = estimated_by_bc;
	}
	public double getEstimated_by_currency() {
		return estimated_by_currency;
	}
	public void setEstimated_by_currency(double estimated_by_currency) {
		this.estimated_by_currency = estimated_by_currency;
	}
}
