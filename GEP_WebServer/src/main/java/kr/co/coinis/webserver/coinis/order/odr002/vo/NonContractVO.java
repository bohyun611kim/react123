/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.order.odr002.vo; 

/**
 * <pre>
 * kr.co.coinis.webserver.order.odr002.vo 
 *    |_ NonContractVO.java
 * 
 * </pre>
 * @date : 2019. 4. 9. 오전 11:16:27
 * @version : 
 * @author : Seongcheol
 */
public class NonContractVO {

	private String orderDt;
	private String orderNo;
	private String itemCode;
	private String itemName;
	private String sellBuyCd;
	private double orderPrc;
	private double orderQty;
	private double contractQty;
	private double nonContractQty;
	private int	   prcDecPnt;
	private int	   qtyDecPnt;

	public String getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSellBuyCd() {
		return sellBuyCd;
	}
	public void setSellBuyCd(String sellBuyCd) {
		this.sellBuyCd = sellBuyCd;
	}
	public double getOrderPrc() {
		return orderPrc;
	}
	public void setOrderPrc(double orderPrc) {
		this.orderPrc = orderPrc;
	}
	public double getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}
	public double getContractQty() {
		return contractQty;
	}
	public void setContractQty(double contractQty) {
		this.contractQty = contractQty;
	}
	public double getNonContractQty() {
		return nonContractQty;
	}
	public void setNonContractQty(double nonContractQty) {
		this.nonContractQty = nonContractQty;
	}
	public int getPrcDecPnt() {
		return prcDecPnt;
	}
	public void setPrcDecPnt(int prcDecPnt) {
		this.prcDecPnt = prcDecPnt;
	}
	public int getQtyDecPnt() {
		return qtyDecPnt;
	}
	public void setQtyDecPnt(int qtyDecPnt) {
		this.qtyDecPnt = qtyDecPnt;
	}
}
