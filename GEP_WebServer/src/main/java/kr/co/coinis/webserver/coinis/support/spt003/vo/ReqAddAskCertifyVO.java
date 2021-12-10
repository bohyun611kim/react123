/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.support.spt003.vo 
 *    |_ reqAddAskCertifyVO.java
 * 
 * </pre>
 * 
 * @date : 2019. 5. 14. 오후 6:47:10
 * @version :
 * @author : J
 */

public class ReqAddAskCertifyVO {
	private String exchange_id;
	private String userid;
	private int inquiry_no;
	private int reply_no;
	private String reply_contents;
	private int inquiry_rep_stat_cd;
	private int fileCount;

	@SuppressWarnings("rawtypes")
	private List FileNameList = new ArrayList();

	@SuppressWarnings("rawtypes")
	private List FileFullPathNameList = new ArrayList();

	@SuppressWarnings("rawtypes")
	private List setSavePathList = new ArrayList();

	@SuppressWarnings("rawtypes")
	private List OriginalFileName1List = new ArrayList();

	public String getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getInquiry_no() {
		return inquiry_no;
	}

	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getReply_contents() {
		return reply_contents;
	}

	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
	}

	public int getInquiry_rep_stat_cd() {
		return inquiry_rep_stat_cd;
	}

	public void setInquiry_rep_stat_cd(int inquiry_rep_stat_cd) {
		this.inquiry_rep_stat_cd = inquiry_rep_stat_cd;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	@SuppressWarnings("rawtypes")
	public List getFileNameList() {
		return FileNameList;
	}

	@SuppressWarnings("rawtypes")
	public void setFileNameList(List fileNameList) {
		FileNameList = fileNameList;
	}

	@SuppressWarnings("rawtypes")
	public List getFileFullPathNameList() {
		return FileFullPathNameList;
	}

	@SuppressWarnings("rawtypes")
	public void setFileFullPathNameList(List fileFullPathNameList) {
		FileFullPathNameList = fileFullPathNameList;
	}

	@SuppressWarnings("rawtypes")
	public List getSetSavePathList() {
		return setSavePathList;
	}

	@SuppressWarnings("rawtypes")
	public void setSetSavePathList(List setSavePathList) {
		this.setSavePathList = setSavePathList;
	}

	@SuppressWarnings("rawtypes")
	public List getOriginalFileName1List() {
		return OriginalFileName1List;
	}

	@SuppressWarnings("rawtypes")
	public void setOriginalFileName1List(List originalFileName1List) {
		OriginalFileName1List = originalFileName1List;
	}
}
