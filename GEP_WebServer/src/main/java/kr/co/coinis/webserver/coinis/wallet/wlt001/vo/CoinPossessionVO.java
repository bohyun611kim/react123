/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.wallet.wlt001.vo 
 *    |_ CoinPossessionVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오전 10:45:59
 * @version : 
 * @author : yeonseoo
 */
public class CoinPossessionVO {
	
	private int coin_no;
	private String coin_symbolic_nm;
	private double balance_qty;
	
	public int getCoin_no() {
		return coin_no;
	}
	public void setCoin_no(int coin_no) {
		this.coin_no = coin_no;
	}
	public String getCoin_symbolic_nm() {
		return coin_symbolic_nm;
	}
	public void setCoin_symbolic_nm(String coin_symbolic_nm) {
		this.coin_symbolic_nm = coin_symbolic_nm;
	}
	public double getBalance_qty() {
		return balance_qty;
	}
	public void setBalance_qty(double balance_qty) {
		this.balance_qty = balance_qty;
	}
	
}
