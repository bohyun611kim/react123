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
 *    |_ DepositWithdrawInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 2. 오후 2:12:58
 * @version : 
 * @author : yeonseoo
 */
public class DepositWithdrawInfoVO {
	
	private int coin_no;
	private String coin_symbolic_nm;
	private int deposit_page_show_yn;
	private String deposit_wallet_addr;
	private String addr_etc1;
	private String addr_etc2;
	private int coin_deposit_addr_cnt;
    private String addr_etc1_nm;
	private String addr_etc2_nm;
	private int auth_means_cd;
	private int wtdrw_page_show_yn;
	private int wtdrw_decimal_pnt;
	private double qty_withdrawable;
	private double wtdrw_fee;
	private double min_wtdrw_qty;
	private double daily_wthrw_qty_left;
	private double max_once_wthrw_qty;
	
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
	public int getDeposit_page_show_yn() {
		return deposit_page_show_yn;
	}
	public void setDeposit_page_show_yn(int deposit_page_show_yn) {
		this.deposit_page_show_yn = deposit_page_show_yn;
	}
	public String getDeposit_wallet_addr() {
		return deposit_wallet_addr;
	}
	public void setDeposit_wallet_addr(String deposit_wallet_addr) {
		this.deposit_wallet_addr = deposit_wallet_addr;
	}
	public String getAddr_etc1() {
		return addr_etc1;
	}
	public void setAddr_etc1(String addr_etc1) {
		this.addr_etc1 = addr_etc1;
	}
	public String getAddr_etc2() {
		return addr_etc2;
	}
	public void setAddr_etc2(String addr_etc2) {
		this.addr_etc2 = addr_etc2;
	}

	public int getCoin_deposit_addr_cnt() {
		return coin_deposit_addr_cnt;
	}
	public void setCoin_deposit_addr_cnt(int coin_deposit_addr_cnt) {
		this.coin_deposit_addr_cnt = coin_deposit_addr_cnt;
	}
	public String getAddr_etc1_nm() {
		return addr_etc1_nm;
	}
	public void setAddr_etc1_nm(String addr_etc1_nm) {
		this.addr_etc1_nm = addr_etc1_nm;
	}
	public String getAddr_etc2_nm() {
		return addr_etc2_nm;
	}
	public void setAddr_etc2_nm(String addr_etc2_nm) {
		this.addr_etc2_nm = addr_etc2_nm;
	}
	public int getAuth_means_cd() {
		return auth_means_cd;
	}
	public void setAuth_means_cd(int auth_means_cd) {
		this.auth_means_cd = auth_means_cd;
	}
	public int getWtdrw_page_show_yn() {
		return wtdrw_page_show_yn;
	}
	public void setWtdrw_page_show_yn(int wtdrw_page_show_yn) {
		this.wtdrw_page_show_yn = wtdrw_page_show_yn;
	}
	public int getWtdrw_decimal_pnt() {
		return wtdrw_decimal_pnt;
	}
	public void setWtdrw_decimal_pnt(int wtdrw_decimal_pnt) {
		this.wtdrw_decimal_pnt = wtdrw_decimal_pnt;
	}
	public double getQty_withdrawable() {
		return qty_withdrawable;
	}
	public void setQty_withdrawable(double qty_withdrawable) {
		this.qty_withdrawable = qty_withdrawable;
	}
	public double getWtdrw_fee() {
		return wtdrw_fee;
	}
	public void setWtdrw_fee(double wtdrw_fee) {
		this.wtdrw_fee = wtdrw_fee;
	}
	public double getMin_wtdrw_qty() {
		return min_wtdrw_qty;
	}
	public void setMin_wtdrw_qty(double min_wtdrw_qty) {
		this.min_wtdrw_qty = min_wtdrw_qty;
	}
	public double getDaily_wthrw_qty_left() {
		return daily_wthrw_qty_left;
	}
	public void setDaily_wthrw_qty_left(double daily_wthrw_qty_left) {
		this.daily_wthrw_qty_left = daily_wthrw_qty_left;
	}
	public double getMax_once_wthrw_qty() {
		return max_once_wthrw_qty;
	}
	public void setMax_once_wthrw_qty(double max_once_wthrw_qty) {
		this.max_once_wthrw_qty = max_once_wthrw_qty;
	}
} 
                 
                 