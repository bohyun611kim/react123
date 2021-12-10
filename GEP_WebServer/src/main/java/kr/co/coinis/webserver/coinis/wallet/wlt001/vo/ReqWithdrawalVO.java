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
 *    |_ ReqWithdrawalVO.java
 * 
 * </pre>
 * @date : 2019. 5. 4. 오후 6:18:31
 * @version : 
 * @author : yeonseoo
 */
public class ReqWithdrawalVO {
	
	private String arg_exchange_id;
	private String arg_user_id;
	private int arg_coin_no;
	private double arg_req_qty;

	public String getArg_exchange_id() {
		return arg_exchange_id;
	}
	public void setArg_exchange_id(String arg_exchange_id) {
		this.arg_exchange_id = arg_exchange_id;
	}
	public String getArg_user_id() {
		return arg_user_id;
	}
	public void setArg_user_id(String arg_user_id) {
		this.arg_user_id = arg_user_id;
	}
	public int getArg_coin_no() {
		return arg_coin_no;
	}
	public void setArg_coin_no(int arg_coin_no) {
		this.arg_coin_no = arg_coin_no;
	}
	public double getArg_req_qty() {
		return arg_req_qty;
	}
	public void setArg_req_qty(double arg_req_qty) {
		this.arg_req_qty = arg_req_qty;
	}

}
