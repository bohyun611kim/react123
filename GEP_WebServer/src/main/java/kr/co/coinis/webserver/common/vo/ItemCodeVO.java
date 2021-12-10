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
 *    |_ ItemCodeVO.java
 * 
 * </pre>
 * @date : 2019. 7. 18. 오후 7:14:04
 * @version : 
 * @author : Seongcheol
 */
public class ItemCodeVO {

	private String itemCode;
	private int    qtyCalcPnt;
	private int    amtCalcPnt;
	private double ordPriceUnit;
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getQtyCalcPnt() {
		return qtyCalcPnt;
	}
	public void setQtyCalcPnt(int qtyCalcPnt) {
		this.qtyCalcPnt = qtyCalcPnt;
	}
	public int getAmtCalcPnt() {
		return amtCalcPnt;
	}
	public void setAmtCalcPnt(int amtCalcPnt) {
		this.amtCalcPnt = amtCalcPnt;
	}
	public double getOrdPriceUnit() {
		return ordPriceUnit;
	}
	public void setOrdPriceUnit(double ordPriceUnit) {
		this.ordPriceUnit = ordPriceUnit;
	}
}
