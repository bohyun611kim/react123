package kr.co.coinis.webserver.coinis.order.odr001.vo;

public class ContractedListVO {

	private String ord_dt;
	private String ord_seq_no;
	private String item_nm;
	private String sell_buy_recog_cd;
	private double ord_price;
	private double ord_qty;
	private String contract_dt;
	private double contract_price;
	private double contract_qty;
	private String trade_fee_amt;
	private int price_dsp_decimal_pnt;
	private int qty_dsp_decimal_pnt;
	
	public String getOrd_dt() {
		return ord_dt;
	}
	public void setOrd_dt(String ord_dt) {
		this.ord_dt = ord_dt;
	}
	public String getOrd_seq_no() {
		return ord_seq_no;
	}
	public void setOrd_seq_no(String ord_seq_no) {
		this.ord_seq_no = ord_seq_no;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getSell_buy_recog_cd() {
		return sell_buy_recog_cd;
	}
	public void setSell_buy_recog_cd(String sell_buy_recog_cd) {
		this.sell_buy_recog_cd = sell_buy_recog_cd;
	}
	public double getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(double ord_price) {
		this.ord_price = ord_price;
	}
	public double getOrd_qty() {
		return ord_qty;
	}
	public void setOrd_qty(double ord_qty) {
		this.ord_qty = ord_qty;
	}
	public String getContract_dt() {
		return contract_dt;
	}
	public void setContract_dt(String contract_dt) {
		this.contract_dt = contract_dt;
	}
	public double getContract_price() {
		return contract_price;
	}
	public void setContract_price(double contract_price) {
		this.contract_price = contract_price;
	}
	public double getContract_qty() {
		return contract_qty;
	}
	public void setContract_qty(double contract_qty) {
		this.contract_qty = contract_qty;
	}
	public String getTrade_fee_amt() {
		return trade_fee_amt;
	}
	public void setTrade_fee_amt(String trade_fee_amt) {
		this.trade_fee_amt = trade_fee_amt;
	}
	public int getPrice_dsp_decimal_pnt() {
		return price_dsp_decimal_pnt;
	}
	public void setPrice_dsp_decimal_pnt(int price_dsp_decimal_pnt) {
		this.price_dsp_decimal_pnt = price_dsp_decimal_pnt;
	}
	public int getQty_dsp_decimal_pnt() {
		return qty_dsp_decimal_pnt;
	}
	public void setQty_dsp_decimal_pnt(int qty_dsp_decimal_pnt) {
		this.qty_dsp_decimal_pnt = qty_dsp_decimal_pnt;
	}
}
