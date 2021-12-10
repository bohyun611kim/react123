/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.exchange.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.exchange.vo 
 *    |_ CoinInfoVO.java
 * 
 * </pre>
 * @date : 2019. 6. 24. 오전 10:41:14
 * @version : 
 * @author : Seongcheol
 */
public class CoinInfoVO {

	private String symbol;
	private String coinEngNm;
	private String foundNm;
	private String foundLoc;
	private String repNm;
	private String homepage;
	private String issueDay;
	private String totSupply;
	private String proofProtocol;
	private String whitePaper;
	private String coinExplanation;

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCoinEngNm() {
		return coinEngNm;
	}
	public void setCoinEngNm(String coinEngNm) {
		this.coinEngNm = coinEngNm;
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
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(String issueDay) {
		this.issueDay = issueDay;
	}
	public String getTotSupply() {
		return totSupply;
	}
	public void setTotSupply(String totSupply) {
		this.totSupply = totSupply;
	}
	public String getProofProtocol() {
		return proofProtocol;
	}
	public void setProofProtocol(String proofProtocol) {
		this.proofProtocol = proofProtocol;
	}
	public String getWhitePaper() {
		return whitePaper;
	}
	public void setWhitePaper(String whitePaper) {
		this.whitePaper = whitePaper;
	}
	public String getCoinExplanation() {
		return coinExplanation;
	}
	public void setCoinExplanation(String coinExplanation) {
		this.coinExplanation = coinExplanation;
	}
}
