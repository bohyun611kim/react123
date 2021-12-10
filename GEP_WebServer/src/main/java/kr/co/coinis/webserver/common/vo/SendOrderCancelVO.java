/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ SendOrderCancelVO.java
 * 
 * </pre>
 * @date : 2019. 5. 3. 오전 9:29:39
 * @version : 
 * @author : Seongcheol
 */
public class SendOrderCancelVO {

	private String exchangeId;	
	private String userId;
	private String mktGrpId;
	private String mktId;
	private int    wasSvrNo;
	private String itemCode;
	private int    ordTypeCd;
	private String orgIfTransactionId;
	private String ordsvrOrgOrdNo;
	private String exchgsvrOrgOrdNo;
	private int    sellBuyRecogCd;
	private double ordPrice;
	private double ordQty;
	private String ordChnlCd;
	private String publicIp;
	private int autoMiningYn;

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
	public String getMktGrpId() {
		return mktGrpId;
	}
	public void setMktGrpId(String mktGrpId) {
		this.mktGrpId = mktGrpId;
	}
	public String getMktId() {
		return mktId;
	}
	public void setMktId(String mktId) {
		this.mktId = mktId;
	}
	public int getWasSvrNo() {
		return wasSvrNo;
	}
	public void setWasSvrNo(int wasSvrNo) {
		this.wasSvrNo = wasSvrNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getOrdTypeCd() {
		return ordTypeCd;
	}
	public void setOrdTypeCd(int ordTypeCd) {
		this.ordTypeCd = ordTypeCd;
	}
	public String getOrgIfTransactionId() {
		return orgIfTransactionId;
	}
	public void setOrgIfTransactionId(String orgIfTransactionId) {
		this.orgIfTransactionId = orgIfTransactionId;
	}
	public String getOrdsvrOrgOrdNo() {
		return ordsvrOrgOrdNo;
	}
	public void setOrdsvrOrgOrdNo(String ordsvrOrgOrdNo) {
		this.ordsvrOrgOrdNo = ordsvrOrgOrdNo;
	}
	public String getExchgsvrOrgOrdNo() {
		return exchgsvrOrgOrdNo;
	}
	public void setExchgsvrOrgOrdNo(String exchgsvrOrgOrdNo) {
		this.exchgsvrOrgOrdNo = exchgsvrOrgOrdNo;
	}
	public int getSellBuyRecogCd() {
		return sellBuyRecogCd;
	}
	public void setSellBuyRecogCd(int sellBuyRecogCd) {
		this.sellBuyRecogCd = sellBuyRecogCd;
	}
	public double getOrdPrice() {
		return ordPrice;
	}
	public void setOrdPrice(double ordPrice) {
		this.ordPrice = ordPrice;
	}
	public double getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(double ordQty) {
		this.ordQty = ordQty;
	}
	public String getOrdChnlCd() {
		return ordChnlCd;
	}
	public void setOrdChnlCd(String ordChnlCd) {
		this.ordChnlCd = ordChnlCd;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public int getAutoMiningYn() {
		return autoMiningYn;
	}
	public void setAutoMiningYn(int autoMiningYn) {
		this.autoMiningYn = autoMiningYn;
	}
}
