/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg003.vo;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg003.vo
 *    |_ ModApiVO.java
 *
 * </pre>
 * @date : 2019. 7. 12. 오후 6:32:26
 * @version :
 * @author : Seongcheol
 */
public class ModApiVO {

	private String exchangeId;
	private String userId;
	@NotNull(message="-5050")
	private String seqNo;
	@NotNull(message="-5050")
	private int    useYn;
	@NotNull(message="-5050")
	private String clientId;
	@NotNull(message="-5050")
	private String secretKey;

	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public int getUseYn() {
		return useYn;
	}
	public void setUseYn(int useYn) {
		this.useYn = useYn;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
