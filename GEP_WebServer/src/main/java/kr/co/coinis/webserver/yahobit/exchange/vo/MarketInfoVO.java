/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.vo;

import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.vo 
 *    |_ MarketInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 8:44:46
 * @version : 
 * @author : Seongcheol
 */
public class MarketInfoVO {

	private String mktNm;
	private int    coinNo;
	private List<MarketItemCodeVO> item;

	public String getMktNm() {
		return mktNm;
	}
	public void setMktNm(String mktNm) {
		this.mktNm = mktNm;
	}
	public int getCoinNo() {
		return coinNo;
	}
	public void setCoinNo(int coinNo) {
		this.coinNo = coinNo;
	}
	public List<MarketItemCodeVO> getItem() {
		return item;
	}
	public void setItem(List<MarketItemCodeVO> item) {
		this.item = item;
	}
}
