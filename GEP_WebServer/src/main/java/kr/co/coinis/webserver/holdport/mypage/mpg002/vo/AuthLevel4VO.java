/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg002.vo;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg002.vo
 *    |_ AuthLevel4VO.java
 *
 * </pre>
 * @date : 2019. 7. 1. 오전 9:24:26
 * @version :
 * @author : Seongcheol
 */
public class AuthLevel4VO {

	private int    idVerifiYn;			// 신분증 인증 완료 여부 (0 : 미완료, 1 : 완료)
	private String idVerifiResultCd;	// 신분증 인증 결과 코드 (0 : 성공, 1 : 미완료, -1 : 실패)
	private String idVerifiErrDetCd;	// 신분증 인증 에러 상세 코드
	private String memoCont;			// 실패내용

	public int getIdVerifiYn() {
		return idVerifiYn;
	}
	public void setIdVerifiYn(int idVerifiYn) {
		this.idVerifiYn = idVerifiYn;
	}
	public String getIdVerifiResultCd() {
		return idVerifiResultCd;
	}
	public void setIdVerifiResultCd(String idVerifiResultCd) {
		this.idVerifiResultCd = idVerifiResultCd;
	}
	public String getIdVerifiErrDetCd() {
		return idVerifiErrDetCd;
	}
	public void setIdVerifiErrDetCd(String idVerifiErrDetCd) {
		this.idVerifiErrDetCd = idVerifiErrDetCd;
	}
	public String getMemoCont() {
		return memoCont;
	}
	public void setMemoCont(String memoCont) {
		this.memoCont = memoCont;
	}
}
