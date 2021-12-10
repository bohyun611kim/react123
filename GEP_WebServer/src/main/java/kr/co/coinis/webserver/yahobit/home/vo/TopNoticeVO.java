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
 *    |_ TopNoticeVO.java
 * 
 * </pre>
 * @date : 2019. 8. 6. 오후 2:17:06
 * @version : 
 * @author : Seongcheol
 */
public class TopNoticeVO {

	private long   recSeqNo;
	private String title;

	public long getRecSeqNo() {
		return recSeqNo;
	}

	public void setRecSeqNo(long recSeqNo) {
		this.recSeqNo = recSeqNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
