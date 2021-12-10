/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.vo;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg001.vo
 *    |_ ReqAccessLogVO.java
 *
 * </pre>
 * @date : 2019. 5. 11. 오후 4:15:35
 * @version :
 * @author : Seongcheol
 */
public class ReqAccessLogVO {

	private String exchangeId;
	private String userId;
	@NotNull(message="-5050")
	private int	   pageNum;
	@NotNull(message="-5050")
	private String startDate;
	@NotNull(message="-5050")
	private String endDate;

	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
