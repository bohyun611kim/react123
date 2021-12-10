/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo;

import javax.validation.constraints.NotNull;

public class SendCouponOrderVO {

	@NotNull(message="-5052")
	private double ordQty;
	@NotNull(message="-5052")
	private double ordBonusQty;
	@NotNull(message="-5052")
	private int ordCouponAmount;
	
	public double getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(double ordQty) {
		this.ordQty = ordQty;
	}
	public double getOrdBonusQty() {
		return ordBonusQty;
	}
	public void setOrdBonusQty(double ordBonusQty) {
		this.ordBonusQty = ordBonusQty;
	}
	public int getOrdCouponAmount() {
		return ordCouponAmount;
	}
	public void setOrdCouponAmount(int ordCouponAmount) {
		this.ordCouponAmount = ordCouponAmount;
	}

}