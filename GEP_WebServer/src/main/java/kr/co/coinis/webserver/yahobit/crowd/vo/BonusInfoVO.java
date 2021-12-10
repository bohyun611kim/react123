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
 *    |_ BonusInfoVO.java
 * 
 * </pre>
 * @date : 2019. 5. 27. 오전 10:03:43
 * @version : 
 * @author : Seongcheol
 */
public class BonusInfoVO {

	private int    bonusTypeCd;
	private String applyStartDt;
	private String applyEndDt;
	private double bonusDstrbtRate;

	public int getBonusTypeCd() {
		return bonusTypeCd;
	}
	public void setBonusTypeCd(int bonusTypeCd) {
		this.bonusTypeCd = bonusTypeCd;
	}
	public String getApplyStartDt() {
		return applyStartDt;
	}
	public void setApplyStartDt(String applyStartDt) {
		this.applyStartDt = applyStartDt;
	}
	public String getApplyEndDt() {
		return applyEndDt;
	}
	public void setApplyEndDt(String applyEndDt) {
		this.applyEndDt = applyEndDt;
	}
	public double getBonusDstrbtRate() {
		return bonusDstrbtRate;
	}
	public void setBonusDstrbtRate(double bonusDstrbtRate) {
		this.bonusDstrbtRate = bonusDstrbtRate;
	}
}
