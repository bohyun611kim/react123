/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg001.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg001.vo
 *    |_ RecommendInfoVO.java
 *
 * </pre>
 * @date : 2019. 4. 22. 오전 10:11:53
 * @version :
 * @author : Seongcheol
 */
public class RecommendInfoVO {

	private String recommandCd;
	private String recommandLink;
	private int    completeCnt;
	private int    totalCnt;

	public String getRecommandCd() {
		return recommandCd;
	}
	public void setRecommandCd(String recommandCd) {
		this.recommandCd = recommandCd;
	}
	public String getRecommandLink() {
		return recommandLink;
	}
	public void setRecommandLink(String recommandLink) {
		this.recommandLink = recommandLink;
	}
	public int getCompleteCnt() {
		return completeCnt;
	}
	public void setCompleteCnt(int completeCnt) {
		this.completeCnt = completeCnt;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
}
