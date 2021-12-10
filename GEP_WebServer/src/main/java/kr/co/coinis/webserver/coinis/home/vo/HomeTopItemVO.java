package kr.co.coinis.webserver.coinis.home.vo;

public class HomeTopItemVO {
	private String item_nm;
	private String item_code;
	private double price_dev_amt;
	private double price_dev_rate;
	private double close_price;
	private double trade_vol;
	private int price_dsp_decimal_pnt;

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
	public double getPrice_dev_amt() {
		return price_dev_amt;
	}
	public void setPrice_dev_amt(double price_dev_amt) {
		this.price_dev_amt = price_dev_amt;
	}
	public double getPrice_dev_rate() {
		return price_dev_rate;
	}
	public void setPrice_dev_rate(double price_dev_rate) {
		this.price_dev_rate = price_dev_rate;
	}
	public double getClose_price() {
		return close_price;
	}
	public void setClose_price(double close_price) {
		this.close_price = close_price;
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
}
