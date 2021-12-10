/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.exception; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.exception 
 *    |_ ErrorResultException.java
 * 
 * </pre>
 * @date : 2019. 7. 7. 오후 2:27:13
 * @version : 
 * @author : kangn
 */
public class ErrorResultException extends Exception {

	private int rtnCd;
	private String rtnMsg;
	
	public ErrorResultException(String rtnMsg) {
		super(rtnMsg);
		this.rtnMsg = rtnMsg;
		this.rtnCd = -9999;
	}
	public ErrorResultException(int rtnCd, String rtnMsg) {
		super(rtnMsg);
		this.rtnMsg = rtnMsg;
		this.rtnCd = rtnCd;
	}
	
	public int getRtnCd() {
		return this.rtnCd;
	}
	public String getRtnMsg() {
		return this.rtnMsg;
	}
}
