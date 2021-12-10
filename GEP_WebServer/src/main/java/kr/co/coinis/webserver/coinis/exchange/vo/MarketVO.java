/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.exchange.vo;

import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.exchange.vo 
 *    |_ MarketVO.java
 * 
 * </pre>
 * @date : 2019. 6. 22. 오후 12:13:50
 * @version : 
 * @author : Seongcheol
 */
public class MarketVO {

	private String mktNm;
	private List<MarketItemVO> item;

	public String getMktNm() {
		return mktNm;
	}
	public void setMktNm(String mktNm) {
		this.mktNm = mktNm;
	}
	public List<MarketItemVO> getItem() {
		return item;
	}
	public void setItem(List<MarketItemVO> item) {
		this.item = item;
	}
}
