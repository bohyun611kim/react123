/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.vo; 


/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.vo 
 *    |_ coprInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 17. 오후 2:42:48
 * @version : 
 * @author : yeonseoo
 */
public class CorpInfoVO {

	private String corp_nm;
	private String represent_nm;
	private String corp_addr;
	private String corp_tel_no;
	
	public String getCorp_nm() {
		return corp_nm;
	}
	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}
	public String getRepresent_nm() {
		return represent_nm;
	}
	public void setRepresent_nm(String represent_nm) {
		this.represent_nm = represent_nm;
	}
	public String getCorp_addr() {
		return corp_addr;
	}
	public void setCorp_addr(String corp_addr) {
		this.corp_addr = corp_addr;
	}
	public String getCorp_tel_no() {
		return corp_tel_no;
	}
	public void setCorp_tel_no(String corp_tel_no) {
		this.corp_tel_no = corp_tel_no;
	}
}
