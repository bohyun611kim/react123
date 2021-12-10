/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ TemplateVO.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오후 6:41:32
 * @version : 
 * @author : Seongcheol
 */
public class TemplateVO {

	private String title;
	private String msgCont;
	private String exchangeNm;
	private String exchangeUrl;
	private String key;
	private String sndDt;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsgCont() {
		return msgCont;
	}
	public void setMsgCont(String msgCont) {
		this.msgCont = msgCont;
	}
	public String getExchangeNm() {
		return exchangeNm;
	}
	public void setExchangeNm(String exchangeNm) {
		this.exchangeNm = exchangeNm;
	}
	public String getExchangeUrl() {
		return exchangeUrl;
	}
	public void setExchangeUrl(String exchangeUrl) {
		this.exchangeUrl = exchangeUrl;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSndDt() {
		return sndDt;
	}
	public void setSndDt(String sndDt) {
		this.sndDt = sndDt;
	}
}
