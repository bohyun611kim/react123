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
 * kr.co.coinis.webserver.holdport.support.prd001.vo
 *    |_ WithdrawLimitVO.java
 *
 * </pre>
 * @date : 2019. 7. 23. 오후 5:13:05
 * @version :
 * @author : Seongcheol
 */
public class WithdrawLimitVO {

	private int    	authLevel;
	private double 	dailyLimitQty;
	private int    	pnt;
	private double	maxOnceWthrwQty;

	public int getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}
	public double getDailyLimitQty() {
		return dailyLimitQty;
	}
	public void setDailyLimitQty(double dailyLimitQty) {
		this.dailyLimitQty = dailyLimitQty;
	}
	public int getPnt() {
		return pnt;
	}
	public void setPnt(int pnt) {
		this.pnt = pnt;
	}
	public double getMaxOnceWthrwQty() {
		return maxOnceWthrwQty;
	}
	public void setMaxOnceWthrwQty(double maxOnceWthrwQty) {
		this.maxOnceWthrwQty = maxOnceWthrwQty;
	}
}
