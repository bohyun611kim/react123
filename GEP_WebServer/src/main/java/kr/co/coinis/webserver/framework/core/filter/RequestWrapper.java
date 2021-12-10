package kr.co.coinis.webserver.framework.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 
* <pre>
* 1.  기능	: CrossScripting Fitler RequestWrapper 구현  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 3. 29.
* ====================================
* 5.  수정사항
* 5.1 요구사항 ID	:
* - 수정자/수정일 	: 
* - 수정사유/내역 	:
* ====================================
* <p/>
* @author		: 김경주
* @since		: J2EE 1.8
* </pre>
 */
public class RequestWrapper extends HttpServletRequestWrapper {
	
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);

		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}

		return encodedValues;
	}

	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);

		if (value == null) {
			return null;
		}

		return cleanXSS(value);
	}

	public String getHeader(String name) {

		String value = super.getHeader(name);

		if (value == null) {
			return null;
		}

		return cleanXSS(value);
	}
	
	/**
	 * 			
	 * <pre>
	 * 1.  기능		: 치환할 특수문자 구현
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 3. 29.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:								
	 * ====================================
	 * @param value
	 * @return
	 * </pre>
	 */
	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		//value = value.replaceAll("script", "");

		return value;
	}
}
