/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001 
 *    |_ NoticeVO.java
 * 
 * </pre>
 * @date : 2019. 4. 24. 오후 3:42:59
 * @version : 
 * @author : Seongcheol
 */
public class NoticeVO {
	private long   no;
	private int    topYn;
	private String title;
	private String contents;
	private String regDt;

	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public int getTopYn() {
		return topYn;
	}
	public void setTopYn(int topYn) {
		this.topYn = topYn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
