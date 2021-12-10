/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.utils; 

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils 
 *    |_ ConvertUtils.java
 * 
 * </pre>
 * @date : 2019. 7. 22. 오전 11:33:44
 * @version : 
 * @author : Seongcheol
 */
public class ConvertUtils {

	public static String jisuToString(double num) {
		BigDecimal strQty = new BigDecimal(num, MathContext.DECIMAL64);
		
		String strResult = strQty.toString();
		String strStzResult = strQty.stripTrailingZeros().toString();
		
		if(strStzResult.toLowerCase().indexOf("e") != -1) {
			return strResult;
		} else {
			return strStzResult;
		}
	}

}
