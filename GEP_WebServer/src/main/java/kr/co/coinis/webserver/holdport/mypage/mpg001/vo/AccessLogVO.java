/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg001.vo
 *    |_ AccessLogVO.java
 *
 * </pre>
 * @date : 2019. 5. 10. 오후 5:16:02
 * @version :
 * @author : Seongcheol
 */
public class AccessLogVO {

	private String dateTime;
	private String publicIp;
	private String os;
	private String browser;
	private String type;

	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
