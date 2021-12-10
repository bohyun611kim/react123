/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.activemq;

import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * <pre>
 * kr.co.coinis.webserver.activemq 
 *    |_ TestCase.java
 * 
 * </pre>
 * @date : 2019. 5. 10. 오후 5:37:34
 * @version : 
 * @author : kangn
 */
public class TestCase {

	@Test
	public void test1() {
		String  strJson = "{\"sndDt\":\"20190510173524000063\",\"svrTypeCd\":3000,\"rcvSvrTypeCd\":6000,\"svrNo\":3001,\"body\":{\"tradeAmt\":\"2.0220709993682323E9\",\"mktGrpId\":\"MKT_GRP_0005\",\"ordsvrOrdNo\":\"200120190510000000000055\",\"exchgsvrOrdNo\":\"300120190510000000000055\",\"contractNo\":\"100000000000110048\",\"itemCode\":\"BTCKRW\",\"wasSvrNo\":\"1001.0\",\"openPrice\":\"6659000.0\",\"tradeVol\":\"1545.1213855893\",\"contractRecogCd\":\"1.0\",\"priceDevAmt\":\"1000.0\",\"lowPrice\":\"6448000.0\",\"highPrice\":\"6660000.0\",\"prevCurPrice\":\"0.0\",\"mktId\":\"YAHOBIT_KRW\",\"weeks52LowDay\":\"20190508\",\"contractQty\":\"0.1\",\"weeks52Low\":\"6448000.0\",\"userId\":\"kangnaru@naver.com\",\"devRecogCd\":\"1.0\",\"priceDevRate\":\"0.0150172699\",\"exchangeId\":\"YAHOBIT\",\"exchgSvrNo\":\"3001.0\",\"contractDt\":\"20190510173524010516\",\"weeks52High\":\"6660000.0\",\"ordSvrNo\":\"2001.0\",\"prevClosePrice\":\"6659000.0\",\"weeks52HighDay\":\"20190510\",\"closePrice\":\"6660000.0\"},\"msgCode\":106,\"ifTransactionId\":\"EXH3001201905100000004050\"}";
		
		Map jsonMap = new Gson().fromJson(strJson, Map.class);
		Map<String, String> bodyMap = (Map)jsonMap.get("body");
		
		System.out.println(jsonMap);
		System.out.println(bodyMap);
	}
}
