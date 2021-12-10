package kr.co.coinis.webserver.framework.core.validator;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.coinis.webserver.framework.core.annatation.ParamCheck;
/**
 * 
 * <pre>
 * 1.  기능		
 *   - Post 파라메타 Validator 한다.   
 * 2.  주의사항	
 *    - Post된 데이타의 Not Null(공백) 유무만 채크한다. 
 * 3.  작성자/작성일	: kim/2018. 2. 20.
 * ====================================
 * - 수정자/수정일/수정내용 	 
    1)
 * ====================================
 * <p/>
 * @author		: kim
 * @version		: v1.0
 * @see			: 
 * @since		: J2EE 1.7
 * </pre>
 */
public class Validator {
	private static final Logger logger=LoggerFactory.getLogger(Validator.class);
    private boolean LOGFLAG = false;
    private Class[] stringType = {String.class};
    private Class[] intType = {int.class};
    
    private String reqItem;
        
    public String getReqItem() {
		return reqItem;
	}

    public void setReqItem(String reqItem) {
		this.reqItem = reqItem;
	}
    /**
     * 
     * <pre>
     * 1.  기능		:  VO 객체에 자동 매칭 
     * 2.  처리개요	: 
     * -  
     * 3.  주의사항	:  
     * ====================================
     * 4.  작성자/작성일	:  kim/2018. 2. 20.
     * ====================================
     * 5.  수정사항
     * - 수정자/수정일 	: 
     * - 수정사유/내역 	:
     * ====================================
     * <p/>
     * @param request
     * @param dto
     * </pre>
     */ 
    public void setAutoMatch(HttpServletRequest request, Object dto){
        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String param = (String) enums.nextElement();
             
            try {
                String methodName = "set"+tofirstUpperCase(param);
                Method mt = getMethod(dto, methodName);
                 
                if(mt != null){
                     
                    String s = mt.getParameterTypes()[0].toString();
                    Object ob = request.getParameter(param);
                    if(s.indexOf("int") >= 0 || s.indexOf("Integer") >= 0){
                        ob = Integer.parseInt(request.getParameter(param));
                    }
                     
                    Object[] args = {ob};
                    mt.invoke(dto, args);
                }
            } catch (Exception e) {
                sys("setAutoMatch Error "+param+" _ "+e);
            } 
        }
    }
    
	/**
	 * 
	 * <pre>
	 * 1.  기능		
     * - 벨류데이션 체크하기
	 * 2.  처리개요	: 
	 * -  
	 * ====================================
	 * 4.  작성자/작성일	:  kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내역 	
     * 1) 
	 * ====================================
	 * <p/>
	 * @param vo
	 * @return
	 * </pre>
	 */
    public boolean checkValidator(Object vo){
        boolean flag = true;
        
        Method tmpM = null;
        for (Method m : vo.getClass().getMethods()) {
            if (m.isAnnotationPresent(ParamCheck.class)) {
                try {
                    ParamCheck v = m.getAnnotation(ParamCheck.class);
                     
                    sys("chkValidation value:\t"+v.value());
                    sys("chkValidation min:\t"+v.min());
                    sys("chkValidation max:\t"+v.max());
                    sys("chkValidation length:\t"+v.length());
                    sys("chkValidation check:\t"+v.check());
                     
                    String s = "get"+m.getName().substring(3); 
                    sys("chkValidation "+s);
                    tmpM = getMethod(vo, s);
                     
                    if(tmpM != null){
                        int intValue = 0;
                        String strReturn = "";
                        Class ret = tmpM.getReturnType();
                         
                        if(ret.toString().indexOf("int") >=0 || ret.toString().indexOf("Integer") >=0){
                            intValue = (Integer) tmpM.invoke(vo, null);
                            sys("chkValidation "+intValue);
                             
                            if(v.min()>intValue || intValue>v.max()){
                                sys("chkValidation NotMatchValue\t"+ m.getName().substring(3) + " __ " + v.min() + " < " + intValue + " < "+v.max());
                                flag = false;
                                break;
                            }
                            
                        /** 문자 입력 유무 체크 **/     
                        }else if( ret.toString().indexOf("String") >=0 ){
                            strReturn = (String) tmpM.invoke(vo, null); 
                            sys("chkValidation "+strReturn);
                            
                            if(strReturn ==null)strReturn="";
                            if(strReturn.equals("")){
                                if(v.check()){
                                    sys("chkValidation check Error\t"+m.getName().substring(3).toLowerCase());
                                    this.setReqItem(m.getName().substring(3).toLowerCase());
                                    flag = false;
                                    break;
                                }
                                sys("value:"+v.value());
                                strReturn = v.value();
                                Object[] args = {strReturn};
                                m.invoke(vo, args);
                            }
                             
                            if(strReturn.length() > v.length()){
                                sys("chkValidation Over Length \t"+m.getName().substring(3));
                                flag = false;
                                break;
                            }
                        }
                    }else{
                        sys("chkValidation Not Method "+s);
                    }
                     
                } catch (Exception e) {
                    sys("chkValidation Error "+e);
                }
            }
        }
         
        return flag;
         
    }
         
	/**
	 * 
	 * 
	 * <pre>
	 * 1.  기능		
     * - 메소트 타입 판별해서 넘김
	 * 2.  처리개요	: 
	 * -  
	 * ====================================
	 * 4.  작성자/작성일	:  kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내역 	
     * 1) 
	 * ====================================
	 * <p/>
	 * @param dto
	 * @param methodName
	 * @return
	 * </pre>
	 */
    private Method getMethod(Object dto, String methodName){
         
        Method mt = null;
        try {
            mt = dto.getClass().getMethod(methodName, stringType);
        } catch (Exception e) {
        } 
             
        if(mt == null){
            try {
                mt = dto.getClass().getMethod(methodName, intType);
            } catch (Exception e) {
            } 
        }
         
        if(mt == null){
            try {
                mt = dto.getClass().getMethod(methodName, null);
            } catch (Exception e) {
            } 
        }
         
        return mt;
    }
         
	/**
	 * 
	 * <pre>
	 * 1.  기능		
	 * -  첫문자를 대문자로
	 * 2.  처리개요	: 
	 * -  
	 * ====================================
	 * 4.  작성자/작성일	:  kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내역 	
	 * 1) 
	 * ====================================
	 * <p/>
	 * @param s
	 * @return
	 * </pre>
	 */
    private String tofirstUpperCase(String s){
        if(s.length()<=0){
            return "";
        }
        String result = Character.toUpperCase(s.toCharArray()[0])+"";
        if (s.length()>1){
            result += s.substring(1);
        }
        return result;
    }
    
    /**
     * <pre>
     * 1.  기능		
     * - 숫자형인지 체크 
     * 2.  처리개요	: 
     * -  
     * ====================================
     * 4.  작성자/작성일	:  kim/2018. 2. 20.
     * ====================================
     * - 수정자/수정일/수정내역 	
     * 1) 
     * ====================================
     * <p/>
     * @param s
     * @return
     * </pre>
     */
    public boolean isNumber(String s){
        boolean flag = false;
        try{
            Integer.parseInt(s);
            flag = true;
        }catch (Exception e) {
        }
        return flag; 
    }
    
    /**
     * 
     * <pre>
     * 1.  기능		
     * - system validator string 
     * 2.  처리개요	: 
     * -  
     * ====================================
     * 4.  작성자/작성일	:  kim/2018. 2. 20.
     * ====================================
     * - 수정자/수정일/수정내역 	
     * 1) 
     * ====================================
     * <p/>
     * @param s
     * </pre>
     */
    private void sys(String s){
        if(LOGFLAG){
           logger.info("Validation:\t"+s);
        }
    }
}
