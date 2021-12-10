package kr.co.coinis.webserver.common.exception;
/**
 * 
* <pre>
* 1.  기능	:파라메타 채크를 위한 Exception
*    -> Exception 메세지로 파라메타 필드 항목을 받는다     
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 2. 20.
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
public class ParameterException extends Exception {
	private String itemName;
	 
	public ParameterException(String itemName){
		super(itemName);
		this.itemName=itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
