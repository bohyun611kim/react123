package kr.co.coinis.webserver.framework.core.annatation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 /**
 * 
* <pre>
* 1.  기능	: 파라메터 항목 길이 체크 상수 지정 Spring Annotation  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 3. 27.
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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {

	String value() default "";
     
    int min() default Integer.MIN_VALUE;
     
    int max() default Integer.MAX_VALUE;
     
    int length() default Integer.MAX_VALUE;
     
    boolean check() default false;
}
