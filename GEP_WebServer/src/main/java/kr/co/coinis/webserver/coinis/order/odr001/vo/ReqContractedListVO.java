package kr.co.coinis.webserver.coinis.order.odr001.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ReqContractedListVO {

	private String user_id;
	private String exchange_id;
	private String item_nm;
	private String market;
	private String mkt_id;
	private String sell_buy_recog_cd_buy;
	private String sell_buy_recog_cd_sell;
	private String day_s;
	private String day_e;
	private int startIndex;
	private int queryDataNum;
	private int dateToday;
	private int dateYesterday;
	
	/*
	 * @Min(1) private int ord_type_cd_valid = (int)getOrd_type_cd_buy();
	 */
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public String getitem_nm() {
		return item_nm;
	}
	public void setitem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getMkt_id() {
		return mkt_id;
	}
	public void setMkt_id(String mkt_id) {
		this.mkt_id = mkt_id;
	}
	public String getSell_buy_recog_cd_buy() {
		return sell_buy_recog_cd_buy;
	}
	public void setSell_buy_recog_cd_buy(String sell_buy_recog_cd_buy) {
		this.sell_buy_recog_cd_buy = sell_buy_recog_cd_buy;
	}
	public String getSell_buy_recog_cd_sell() {
		return sell_buy_recog_cd_sell;
	}
	public void setSell_buy_recog_cd_sell(String sell_buy_recog_cd_sell) {
		this.sell_buy_recog_cd_sell = sell_buy_recog_cd_sell;
	}
	public String getDay_s() {
		return day_s;
	}
	public void setDay_s(String day_s) {
		this.day_s = day_s;
	}
	public String getDay_e() {
		return day_e;
	}
	public void setDay_e(String day_e) {
		this.day_e = day_e;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getQueryDataNum() {
		return queryDataNum;
	}
	public void setQueryDataNum(int queryDataNum) {
		this.queryDataNum = queryDataNum;
	}
	public String getDateToday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}
	public String getDateYesterday() throws ParseException {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		calendar.setTime(dateFormat.parse(dateFormat.format(new Date())));
		calendar.add(Calendar.DATE, -1);
		
		return dateFormat.format(calendar.getTime());
	}
}
