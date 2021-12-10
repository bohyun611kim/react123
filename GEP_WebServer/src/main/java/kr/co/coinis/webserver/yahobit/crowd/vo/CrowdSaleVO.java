/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.vo 
 *    |_ CrowdSaleVO.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오후 3:09:00
 * @version : 
 * @author : Seongcheol
 */
public class CrowdSaleVO {

	private int    seq;
	private String crwdTypeCd;
	private String coinSymbolicNm;
	private String title;
	private String coinUsage;
	private long   startDt;
	private long   endDt;
	private String txtTarget;
	private String txtPrice;
	private String crowdSchedule;
	private String homepageUrl;
	private String whitepaperUrl;
	private String videoClipUrl;
	private int    listedYn;
	private String hardCap;
	private int    slQtyBasisCoinNo;
	private String slQtyBasisCoinSymbolicNm;
	private int    slCoinNo;
	private String slCoinSymbolicNm;

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCrwdTypeCd() {
		return crwdTypeCd;
	}
	public void setCrwdTypeCd(String crwdTypeCd) {
		this.crwdTypeCd = crwdTypeCd;
	}
	public String getCoinSymbolicNm() {
		return coinSymbolicNm;
	}
	public void setCoinSymbolicNm(String coinSymbolicNm) {
		this.coinSymbolicNm = coinSymbolicNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoinUsage() {
		return coinUsage;
	}
	public void setCoinUsage(String coinUsage) {
		this.coinUsage = coinUsage;
	}
	public long getStartDt() {
		return startDt;
	}
	public void setStartDt(long startDt) {
		this.startDt = startDt;
	}
	public long getEndDt() {
		return endDt;
	}
	public void setEndDt(long endDt) {
		this.endDt = endDt;
	}
	public String getTxtTarget() {
		return txtTarget;
	}
	public void setTxtTarget(String txtTarget) {
		this.txtTarget = txtTarget;
	}
	public String getTxtPrice() {
		return txtPrice;
	}
	public void setTxtPrice(String txtPrice) {
		this.txtPrice = txtPrice;
	}
	public String getCrowdSchedule() {
		return crowdSchedule;
	}
	public void setCrowdSchedule(String crowdSchedule) {
		this.crowdSchedule = crowdSchedule;
	}
	public String getHomepageUrl() {
		return homepageUrl;
	}
	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}
	public String getWhitepaperUrl() {
		return whitepaperUrl;
	}
	public void setWhitepaperUrl(String whitepaperUrl) {
		this.whitepaperUrl = whitepaperUrl;
	}
	public String getVideoClipUrl() {
		return videoClipUrl;
	}
	public void setVideoClipUrl(String videoClipUrl) {
		this.videoClipUrl = videoClipUrl;
	}
	public int getListedYn() {
		return listedYn;
	}
	public void setListedYn(int listedYn) {
		this.listedYn = listedYn;
	}
	public String getHardCap() {
		return hardCap;
	}
	public void setHardCap(String hardCap) {
		this.hardCap = hardCap;
	}
	public int getSlQtyBasisCoinNo() {
		return slQtyBasisCoinNo;
	}
	public void setSlQtyBasisCoinNo(int slQtyBasisCoinNo) {
		this.slQtyBasisCoinNo = slQtyBasisCoinNo;
	}
	public String getSlQtyBasisCoinSymbolicNm() {
		return slQtyBasisCoinSymbolicNm;
	}
	public void setSlQtyBasisCoinSymbolicNm(String slQtyBasisCoinSymbolicNm) {
		this.slQtyBasisCoinSymbolicNm = slQtyBasisCoinSymbolicNm;
	}
	public int getSlCoinNo() {
		return slCoinNo;
	}
	public void setSlCoinNo(int slCoinNo) {
		this.slCoinNo = slCoinNo;
	}
	public String getSlCoinSymbolicNm() {
		return slCoinSymbolicNm;
	}
	public void setSlCoinSymbolicNm(String slCoinSymbolicNm) {
		this.slCoinSymbolicNm = slCoinSymbolicNm;
	}
}
