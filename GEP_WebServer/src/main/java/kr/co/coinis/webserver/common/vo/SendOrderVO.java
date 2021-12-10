/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.vo;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * kr.co.coinis.webserver.common.vo 
 *    |_ SendOrderVO.java
 * 
 * </pre>
 * @date : 2019. 4. 30. 오후 4:04:37
 * @version : 
 * @author : Seongcheol
 */
public class SendOrderVO {

	private String exchangeId;	
	private String userId;
	@NotNull(message="-5050")
	private String mktGrpId;
	@NotNull(message="-5050")
	private String mktId;
	private String ordDt;
	@NotNull(message="-5050")
	private String itemCode;
	private int    ordTypeCd;
	private int    ordPriceTypeCd;
	private int    sellBuyRecogCd;
	@NotNull(message="-5051")
	private double ordPrice;
	@NotNull(message="-5052")
	private double ordQty;
	private double ordBonusQty = 0.0;
	private String publicIp;
	private int    autoMiningYn;
	
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
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
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
	public int getOrdPriceTypeCd() {
		return ordPriceTypeCd;
	}
	public void setOrdPriceTypeCd(int ordPriceTypeCd) {
		this.ordPriceTypeCd = ordPriceTypeCd;
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
	public double getOrdBonusQty() {
		return ordBonusQty;
	}
	public void setOrdBonusQty(double ordBonusQty) {
		this.ordBonusQty = ordBonusQty;
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
