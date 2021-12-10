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
 *    |_ PossessionInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 4:37:36
 * @version : 
 * @author : yeonseoo
 */
public class PossessionInfoVO {
	private String poss_asset;
	private String total_qty;
	private String usable_qty;
	private String in_use_qty;
	private String total_by_btc;
	
	public String getPoss_asset() {
		return poss_asset;
	}
	public void setPoss_asset(String poss_asset) {
		this.poss_asset = poss_asset;
	}
	public String getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(String total_qty) {
		this.total_qty = total_qty;
	}
	public String getUsable_qty() {
		return usable_qty;
	}
	public void setUsable_qty(String usable_qty) {
		this.usable_qty = usable_qty;
	}
	public String getIn_use_qty() {
		return in_use_qty;
	}
	public void setIn_use_qty(String in_use_qty) {
		this.in_use_qty = in_use_qty;
	}
	public String getTotal_by_btc() {
		return total_by_btc;
	}
	public void setTotal_by_btc(String total_by_btc) {
		this.total_by_btc = total_by_btc;
	}
}
