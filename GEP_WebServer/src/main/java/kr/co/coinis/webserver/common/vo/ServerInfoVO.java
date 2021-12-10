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
 *    |_ ServerInfoVO.java
 * 
 * </pre>
 * @date : 2019. 4. 11. 오전 10:30:50
 * @version : 
 * @author : Seongcheol
 */
public class ServerInfoVO {

	private int    svrNo;
	private String svrId;
	private int    svrTypeCd;
	private String svrDescr;
	private String ipAddr;
	private int    parallelHashFactor;
	private int    useYn;
	private String useStartDt;
	private String useEndDt;
	
	public int getSvrNo() {
		return svrNo;
	}
	public void setSvrNo(int svrNo) {
		this.svrNo = svrNo;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public int getSvrTypeCd() {
		return svrTypeCd;
	}
	public void setSvrTypeCd(int svrTypeCd) {
		this.svrTypeCd = svrTypeCd;
	}
	public String getSvrDescr() {
		return svrDescr;
	}
	public void setSvrDescr(String svrDescr) {
		this.svrDescr = svrDescr;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getParallelHashFactor() {
		return parallelHashFactor;
	}
	public void setParallelHashFactor(int parallelHashFactor) {
		this.parallelHashFactor = parallelHashFactor;
	}
	public int getUseYn() {
		return useYn;
	}
	public void setUseYn(int useYn) {
		this.useYn = useYn;
	}
	public String getUseStartDt() {
		return useStartDt;
	}
	public void setUseStartDt(String useStartDt) {
		this.useStartDt = useStartDt;
	}
	public String getUseEndDt() {
		return useEndDt;
	}
	public void setUseEndDt(String useEndDt) {
		this.useEndDt = useEndDt;
	}
}
