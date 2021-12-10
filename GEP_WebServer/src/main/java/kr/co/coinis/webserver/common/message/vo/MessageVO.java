/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.message.vo;

import java.io.Serializable;

/**
 * <pre>
 * kr.co.coinis.webserver.common.message.vo 
 *    |_ MessageVO.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 8:44:32
 * @version : 
 * @author : Seongcheol
 */
public class MessageVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ifTransactionId;
	private String body;
	
	public String getIfTransactionId() {
		return ifTransactionId;
	}
	public void setIfTransactionId(String ifTransactionId) {
		this.ifTransactionId = ifTransactionId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
