/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.home.vo;

import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.home.vo 
 *    |_ MarketsByExchangeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 17. 오후 6:34:22
 * @version : 
 * @author : yeonseoo
 */
public class MarketsByExchangeVO {

	private String mkt_nm;
	private String exchange_id;
	private int	   coin_no;
	private List<ItemSiseListByMarketVO> itemSiseList;

	public String getMkt_nm() {
		return mkt_nm;
	}
	public void setMkt_nm(String mkt_nm) {
		this.mkt_nm = mkt_nm;
	}
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public int getCoin_no() {
		return coin_no;
	}
	public void setCoin_no(int coin_no) {
		this.coin_no = coin_no;
	}
	public List<ItemSiseListByMarketVO> getItemSiseList() {
		return itemSiseList;
	}
	public void setItemSiseList(List<ItemSiseListByMarketVO> itemSiseList) {
		this.itemSiseList = itemSiseList;
	}
}
