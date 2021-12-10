/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.home.vo 
 *    |_ ItemListByMarketVO.java
 * 
 * </pre>
 * @date : 2019. 4. 17. 오후 6:43:23
 * @version : 
 * @author : yeonseoo
 */
public class ItemSiseListByMarketVO {

	private String item_nm;
	private String item_code;
	private double close_price;
	private double price_dev_rate;
	private double high_price;
	private double low_price;
	private double trade_vol;
	private int price_dsp_decimal_pnt;
	private int vol_dsp_decimal_pnt;
	
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public double getClose_price() {
		return close_price;
	}
	public void setClose_price(double close_price) {
		this.close_price = close_price;
	}
	public double getPrice_dev_rate() {
		return price_dev_rate;
	}
	public void setPrice_dev_rate(double price_dev_rate) {
		this.price_dev_rate = price_dev_rate;
	}
	public double getHigh_price() {
		return high_price;
	}
	public void setHigh_price(double high_price) {
		this.high_price = high_price;
	}
	public double getLow_price() {
		return low_price;
	}
	public void setLow_price(double low_price) {
		this.low_price = low_price;
	}
	public double getTrade_vol() {
		return trade_vol;
	}
	public void setTrade_vol(double trade_vol) {
		this.trade_vol = trade_vol;
	}
	public int getPrice_dsp_decimal_pnt() {
		return price_dsp_decimal_pnt;
	}
	public void setPrice_dsp_decimal_pnt(int price_dsp_decimal_pnt) {
		this.price_dsp_decimal_pnt = price_dsp_decimal_pnt;
	}
	public int getVol_dsp_decimal_pnt() {
		return vol_dsp_decimal_pnt;
	}
	public void setVol_dsp_decimal_pnt(int vol_dsp_decimal_pnt) {
		this.vol_dsp_decimal_pnt = vol_dsp_decimal_pnt;
	}

}
