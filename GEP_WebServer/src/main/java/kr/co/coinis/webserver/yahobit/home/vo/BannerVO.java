/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.home.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.home.vo 
 *    |_ BannerVO.java
 * 
 * </pre>
 * @date : 2019. 5. 21. 오전 11:09:59
 * @version : 
 * @author : Seongcheol
 */
public class BannerVO {

	private int    seqNo;
	private String title;
	private String eventUrl;
	private String bannerUrl;
	private String contents;
	
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEventUrl() {
		return eventUrl;
	}
	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
