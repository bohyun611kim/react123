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
 *    |_ ReqAddInquiryCertifyVO.java
 * 
 * </pre>
 * @date : 2019. 5. 15. 오후 4:23:28
 * @version : 
 * @author : J
 */
public class ReqAddInquiryCertifyVO {
	private String exchange_id;
	private String userid;
	private int inquiry_type_cd;
	private String inquiry_title;
	private String inquiry_contents;
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

	public int getInquiry_type_cd() {
		return inquiry_type_cd;
	}

	public void setInquiry_type_cd(int inquiry_type_cd) {
		this.inquiry_type_cd = inquiry_type_cd;
	}

	public String getInquiry_title() {
		return inquiry_title;
	}

	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}

	public String getInquiry_contents() {
		return inquiry_contents;
	}

	public void setInquiry_contents(String inquiry_contents) {
		this.inquiry_contents = inquiry_contents;
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
