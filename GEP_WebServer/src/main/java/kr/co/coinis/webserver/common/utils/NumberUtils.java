package kr.co.coinis.webserver.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.github.jaiimageio.impl.common.BogusColorSpace;

public class NumberUtils {
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		: 입력 받은 문자열에 천단위로 ','를 추가하여 반환
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  Seongcheol/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param number
	 * @return
	 * </pre>
	 */
	public static String numberWithCommas(Double number) {
		if(number == null || number == 0) {
			return "0";
		} else {
			return new DecimalFormat("#,###.##").format(number);
		}
	}
	
	public static boolean isStringDouble(String s) {
	    try {
	        Double.parseDouble(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력 받은 value의 소수점 자릿수가 point를 넘기는 지 확인(정상일 경우 true 반환)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkDecimalPoint
	 * @date : 2019. 7. 30.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 30.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param value
	 * @param point
	 * @return
	 */
	public static boolean checkDecimalPoint(double value, int point) {
		boolean result = false;
		
		double pow = Math.pow(10, point);
		double temp = Math.round(value * pow) / pow;
		
		if(value - temp == 0) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 정확한 나머지 연산 처리를 위해 정수 화 후 계산 처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : calModOperation
	 * @date : 2019. 8. 11.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 8. 11.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param value1
	 * @param value2
	 * @param point
	 * @return
	 */
	public static double calModOperation(double value1, double value2, int point) {
		
		double pow = Math.pow(10, point);
		
		return (long) ((value1 * pow) % (value2 * pow));
	}
}
