/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.anal.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.anal.vo 
 *    |_ CoinMarketCapVO.java
 * 
 * </pre>
 * @date : 2019. 5. 23. 오전 10:09:47
 * @version : 
 * @author : Seongcheol
 */
public class CoinMarketCapVO {

	private int    rank;
	private String symbol;
	private String coinNm;
	private double marketCapital;
	private double closePrice;
	private double tradeAmount;
	private double avaulableSupply;
	private double tradeDaebuRate;
	private String itemCode;
	private String updateDt;

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCoinNm() {
		return coinNm;
	}
	public void setCoinNm(String coinNm) {
		this.coinNm = coinNm;
	}
	public double getMarketCapital() {
		return marketCapital;
	}
	public void setMarketCapital(double marketCapital) {
		this.marketCapital = marketCapital;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	public double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public double getAvaulableSupply() {
		return avaulableSupply;
	}
	public void setAvaulableSupply(double avaulableSupply) {
		this.avaulableSupply = avaulableSupply;
	}
	public double getTradeDaebuRate() {
		return tradeDaebuRate;
	}
	public void setTradeDaebuRate(double tradeDaebuRate) {
		this.tradeDaebuRate = tradeDaebuRate;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
}
