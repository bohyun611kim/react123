package kr.co.coinis.webserver.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils
 *    |_ CommonUtils.java
 *
 * </pre>
 * @date : 2019. 4. 1. 오전 10:12:29
 * @version :
 * @author : Seongcheol
 */
public class CommonUtils {

	/**
	 *
	 * <pre>
	 * 1.  기능		: Object 객체를 받아 null일때 default value를 반환하는 공통함수
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  kangnaru/2018. 4. 5.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param obj
	 * @param defaultVal
	 * @return
	 * </pre>
	 */
	public static String strNlv(Object obj, String defaultVal) {
		if(obj == null || obj.equals("")) return defaultVal;
		else return obj.toString();
	}

   	/**
   	 *
   	 * <pre>
   	 * 1.  기능		: Exception 객체를 print stream으로 변환
   	 * 2.  처리개요	:
   	 * 3.  주의사항	:
   	 * ====================================
   	 * 4.  작성자/작성일	:  kangnaru/2018. 4. 6.
   	 * ====================================
   	 * 5.  수정사항
   	 * 5.1 요구사항 ID	:
   	 * - 수정자/수정일 	:
   	 * - 수정사유/내역 	:
   	 * ====================================
   	 * @param clsObject
   	 * @return
   	 * </pre>
   	 */
	public static String getPrintStackTrace(Object clsObject) {
		ByteArrayOutputStream clsOutStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(clsOutStream);
		((Throwable) clsObject).printStackTrace(printStream);
		return clsOutStream.toString();
	}

	/**
	 * <pre>
	 * 1.  기능		:  클라이언트의 ip 얻기
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  YC/2018. 4. 5.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 강신석 / 2019.07.03
	 * - 수정사유/내역 	: CloudFlare 서비스에서 IP Address가 20자리 넘게 올라오는것 15자리로 자르도록 수정
	 * ====================================
	 * @param db
	 * @return
	 * </pre>
	*/
	public static String getClientIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }

	    if(ip != null && ip.length() > 15) ip = ip.substring(0, 15).trim();
	    return ip;
	}

	/**
	 * session 유무 확인
	 * @param userDetails
	 * @return
	 */
	public static boolean isLogin(CustomUserDetails userDetails) {
		boolean result = true;

		if(userDetails == null) {
			result = false;
		} else if(userDetails.getLoginYn() == 0) {
			result = false;
		}

		return result;
	}

	/**
	 * 파일 확장자 체크(jpg, gif, png만 허용)
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : checkFile
	 * @date : 2019. 3. 27.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 3. 27.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean checkFile(String fileName) {
		if(!fileName.toLowerCase().endsWith("jpg") && !fileName.toLowerCase().endsWith("png")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * header에서 ExchangeID값 가져오기
	 * <pre>
	 * 1. 개요 : 사이트 구분을 위한 header 값 가져오기
	 * 2. 처리내용 : header에서 ExchangeID 값을 가져와 반환하기
	 * </pre>
	 * @Method Name : getExchangeId
	 * @date : 2019. 4. 1.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 1.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @return
	 */
	public static String getExchangeId(HttpServletRequest request) {

		//return request.getHeader("ExchangeID");
		//return "COINIS";
		return "YAHOBIT";
	}

	public static String getNewExchangeId(HttpServletRequest request) {

		//return request.getHeader("ExchangeID");
		return "HOLDPORT";

	}
	public static String getPageKey(HttpServletRequest request) {

		return CommonUtils.getExchangeId(request).toLowerCase();
		//return "site";

	}

	public static String getNewPageKey(HttpServletRequest request) {
		//return CommonUtils.getExchangeId(request).toLowerCase();
		return "site";
	}

	public static String getSitePackageKey(HttpServletRequest request) {
		//return CommonUtils.getExchangeId(request).toLowerCase();
		return "holdport";
	}



	/**
	 *
	 * @Method leftPad
	 * @Comment	왼쪽에 길이만큼 '0' 값을 채워준다
	 * @param originString
	 * @param length
	 * @return
	 * @exception
	 */
	public static String leftPad(String originString, int length) {
		if(originString == null) originString = "";
		String paddedString = originString;
		while (paddedString.length() < length) {
			paddedString = "0" + paddedString ;
		}
		return paddedString.length() > length ? paddedString.substring(0, length) : paddedString;
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : java의 Long, Double, Float type은 64bit (10^19) 이며 javascript의 numeric type은 54bit (10^16)이기때문에 java의 numeric타입을 string type으로 변경함
	 * 2. 처리내용 : Map의 List 형태를 변환
	 * </pre>
	 * @Method Name : numericConvertListMapValue
	 * @date : 2019. 4. 29.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 29.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param ListMap
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> numericConvertListMapValue(List<Map<String, Object>> ListMap) throws Exception {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		try {
			for(Map<String, Object> map : ListMap) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if(entry.getValue() instanceof Long || entry.getValue() instanceof Float || entry.getValue() instanceof Double)
						returnMap.put(entry.getKey(), entry.getValue().toString().trim());
					else
						returnMap.put(entry.getKey(), entry.getValue());
				}
				returnList.add(returnMap);
			}
			return returnList;
		} catch(Exception e) {
			//throw new SQLException(e.getMessage());
			//return returnList;
			return null;
		}
	}
	// 단순 Map 변환
	public static Map<String, Object> numericConvertMapValue(Map<String, Object> map) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if(entry.getValue() instanceof Long || entry.getValue() instanceof Float || entry.getValue() instanceof Double)
					returnMap.put(entry.getKey(), entry.getValue().toString().trim());
				else
					returnMap.put(entry.getKey(), entry.getValue());
			}
			return returnMap;
		} catch(Exception e) {
			//throw new SQLException(e.getMessage());
			//return returnMap;
			return null;
		}
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : OTP Key를 지정한 길이만큼 생성한다.
	 * 2. 처리내용 : 영문 O, I 숫자 1, 0 을 제외한 영문 대문자, 숫자조합으로 생성한다. (Base32 기준)
	 * </pre>
	 * @Method Name : generateOTPKey
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param keyLen
	 * @return
	 */
	public static String generateOTPKey(int keyLen) {
		String chars = "ABCDEFGHJKLMNPQRSTUVWXTZ234567";
		String randomString = "";
		for (int i = 0; i < keyLen; i++) {
			int rnum = (int) Math.floor(Math.random() * chars.length());
			randomString += chars.substring(rnum, rnum + 1);
		}
		return randomString;
	}

	public static String maskingUserId(String strUserId) throws Exception {
		String[] strip = strUserId.split("@");
		if(strip.length < 1) return strUserId;

		String namePart = strip[0].substring(0, strip[0].length() - 3) + "***";
		String urlPart = strip[1].substring(0, strip[1].length() - 4) + "****";
		return namePart + "@" + urlPart;
	}


	/** aa******@naver.com 형식으로 변환*/
	public static String maskingUserId2(String strUserId) throws Exception {
		String[] strip = strUserId.split("@");
		if(strip.length < 1) return strUserId;

		String maskStr = "";
		for(int i=0; i<(strip[0].length()-2); i++) {
			maskStr += "*";
		}
		String namePart = strip[0].substring(0, 2) + maskStr;
		//String urlPart = strip[1].substring(0, strip[1].length() - 4) + "****";
		return namePart + "@" + strip[1];
	}

	public static void adaptLocale(HttpServletRequest request, String locale, ModelAndView model) {
		
		String orgView = model.getViewName();
		String locView = null;
		if (locale == null ||  locale.equalsIgnoreCase("")) {
			locView = orgView.concat(".ko"); // default
		} else {
			locView = orgView.concat("." + locale);
		}

		// check view file exist
		InputStream check = request.getServletContext().getResourceAsStream("/WEB-INF/views/" + locView + ".jsp");
		if (check == null) return; // return original view

		model.setViewName(locView);
	}

}
