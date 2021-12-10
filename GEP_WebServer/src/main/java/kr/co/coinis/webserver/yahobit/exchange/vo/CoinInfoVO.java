/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.exchange.vo 
 *    |_ CoinInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 29. 오후 6:28:50
 * @version : 
 * @author : Seongcheol
 */
public class CoinInfoVO {

	private String symbol;
	private String engNm;
	private String korNm;
	private String fullNm;
	private String issueDay;
	private String foundNm;
	private String foundLoc;
	private String representNm;
	private String foundDay;
	private String homepageUrl;
	private String snsUrl;
	private String searchUrl;
	private String paperUrl;
	private String sourceCodeUrl;
	private String protocol;
	private String argorithm;
	private double totSupplyQty;
	private double supplyQtyPerDay;
	private String explanation;

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getEngNm() {
		return engNm;
	}
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
	public String getKorNm() {
		return korNm;
	}
	public void setKorNm(String korNm) {
		this.korNm = korNm;
	}
	public String getFullNm() {
		return fullNm;
	}
	public void setFullNm(String fullNm) {
		this.fullNm = fullNm;
	}
	public String getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(String issueDay) {
		this.issueDay = issueDay;
	}
	public String getFoundNm() {
		return foundNm;
	}
	public void setFoundNm(String foundNm) {
		this.foundNm = foundNm;
	}
	public String getFoundLoc() {
		return foundLoc;
	}
	public void setFoundLoc(String foundLoc) {
		this.foundLoc = foundLoc;
	}
	public String getRepresentNm() {
		return representNm;
	}
	public void setRepresentNm(String representNm) {
		this.representNm = representNm;
	}
	public String getFoundDay() {
		return foundDay;
	}
	public void setFoundDay(String foundDay) {
		this.foundDay = foundDay;
	}
	public String getHomepageUrl() {
		return homepageUrl;
	}
	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}
	public String getSnsUrl() {
		return snsUrl;
	}
	public void setSnsUrl(String snsUrl) {
		this.snsUrl = snsUrl;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public String getPaperUrl() {
		return paperUrl;
	}
	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}
	public String getSourceCodeUrl() {
		return sourceCodeUrl;
	}
	public void setSourceCodeUrl(String sourceCodeUrl) {
		this.sourceCodeUrl = sourceCodeUrl;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getArgorithm() {
		return argorithm;
	}
	public void setArgorithm(String argorithm) {
		this.argorithm = argorithm;
	}
	public double getTotSupplyQty() {
		return totSupplyQty;
	}
	public void setTotSupplyQty(double totSupplyQty) {
		this.totSupplyQty = totSupplyQty;
	}
	public double getSupplyQtyPerDay() {
		return supplyQtyPerDay;
	}
	public void setSupplyQtyPerDay(double supplyQtyPerDay) {
		this.supplyQtyPerDay = supplyQtyPerDay;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
