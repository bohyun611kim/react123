/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.wallet.wlt001.vo 
 *    |_ ResultWithdrawCheck.java
 * 
 * </pre>
 * @date : 2019. 5. 7. 오후 12:27:37
 * @version : 
 * @author : yeonseoo
 */
public class WithdrawCheckResultVO {

	private int rtn_cd;
	private String rtn_msg;
	
	public int getRtn_cd() {
		return rtn_cd;
	}
	public void setRtn_cd(int rtn_cd) {
		this.rtn_cd = rtn_cd;
	}
	public String getRtn_msg() {
		return rtn_msg;
	}
	public void setRtn_msg(String rtn_msg) {
		this.rtn_msg = rtn_msg;
	}
}
