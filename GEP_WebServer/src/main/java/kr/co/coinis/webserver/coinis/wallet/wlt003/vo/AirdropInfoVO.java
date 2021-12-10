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
 *    |_ AirdropInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 1:59:36
 * @version : 
 * @author : yeonseoo
 */
public class AirdropInfoVO {

	private String airdrop_compl_dt;
	private String coin_symbolic_nm;
	private double airdrop_qty;
	private String title;
	
	public String getAirdrop_compl_dt() {
		return airdrop_compl_dt;
	}
	public void setAirdrop_compl_dt(String airdrop_compl_dt) {
		this.airdrop_compl_dt = airdrop_compl_dt;
	}
	public String getCoin_symbolic_nm() {
		return coin_symbolic_nm;
	}
	public void setCoin_symbolic_nm(String coin_symbolic_nm) {
		this.coin_symbolic_nm = coin_symbolic_nm;
	}
	public double getAirdrop_qty() {
		return airdrop_qty;
	}
	public void setAirdrop_qty(double airdrop_qty) {
		this.airdrop_qty = airdrop_qty;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
