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
 * kr.co.coinis.webserver.coinis.wallet.wlt003.vo 
 *    |_ CompensatedRewardInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 2:06:12
 * @version : 
 * @author : hanyeonseo-internet
 */
public class CompensatedRewardInfoVO {

	private String apply_dt;
	private String coin_symbolic_nm;
	private double proc_qty;
	private String proc_detail;

	public String getApply_dt() {
		return apply_dt;
	}
	public void setApply_dt(String apply_dt) {
		this.apply_dt = apply_dt;
	}
	public String getCoin_symbolic_nm() {
		return coin_symbolic_nm;
	}
	public void setCoin_symbolic_nm(String coin_symbolic_nm) {
		this.coin_symbolic_nm = coin_symbolic_nm;
	}
	public double getProc_qty() {
		return proc_qty;
	}
	public void setProc_qty(double proc_qty) {
		this.proc_qty = proc_qty;
	}
	public String getProc_detail() {
		return proc_detail;
	}
	public void setProc_detail(String proc_detail) {
		this.proc_detail = proc_detail;
	}
}
