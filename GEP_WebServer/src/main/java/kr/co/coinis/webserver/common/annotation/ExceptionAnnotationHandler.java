package kr.co.coinis.webserver.common.annotation;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import kr.co.coinis.webserver.common.exception.DataNotFoundException;
import kr.co.coinis.webserver.common.exception.ParameterException;
import kr.co.coinis.webserver.common.exception.RestTemplateClientException;
import kr.co.coinis.webserver.common.exception.SessionExpiredException;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.PropertiesUtils;
import kr.co.coinis.webserver.framework.core.web.AbstractBaseController;

/**
 * 
 * <pre>
* 1.  기능	: ControllerAdvice Annatation으로 공통 Exception 오류 처리     
* 2.  처리개요	: 
* 3.  주의사항	: 사용자정의 오류일 경우에 ExceptionHandler 추가해서 오류 메세지를 error 페이지로 return 한다.  
* 4.  작성자/작성일	:  김경주/2018. 2. 20.
* ====================================
* 5.  수정사항
* 5.1 요구사항 ID	:
* - 수정자/수정일 	: 
* - 수정사유/내역 	:
* ====================================
* <p/>
* &#64;author		: 김경주
* &#64;since		: J2EE 1.8
 * </pre>
 */
@ControllerAdvice
public class ExceptionAnnotationHandler extends AbstractBaseController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAnnotationHandler.class);

	private static final boolean logable = true;
	/**
	 * 
	 * <pre>
	 * 1.  기능		: 예기치 않은 공통 에러 메세지 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(Exception.class)	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "your message")		
	public ModelAndView defaultExceptionHandler(Exception ext) {
		if(logable) logger.debug("[ExceptionAnnotationHandler] defaultExceptionHandler >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ext.getMessage());
		return model;

	}
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		: 잘못 요청한 Header Type 오류  
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ModelAndView HttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ext) {

		if(logable) logger.debug("[ExceptionAnnotationHandler] HttpMediaTypeNotAcceptableException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ext.getMessage());

		return model;
	}
	/**
	 * 
	 * <pre>
	 * 1.  기능		: 중복 로그인 되었을 때 발생하는 오류 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 26.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param ext
	 * @return
	 * </pre>
	 */
	@ExceptionHandler(SessionAuthenticationException.class)
	public ModelAndView SessionAuthenticationException(SessionAuthenticationException ext) {

		if(logable) logger.debug("[ExceptionAnnotationHandler] SessionAuthenticationException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ext.getMessage());

		return model;
	}
	
	@ExceptionHandler(SocketException.class)
	public ModelAndView SocketException(SocketException ext) {

		if(logable) logger.debug("[ExceptionAnnotationHandler] SocketException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ext.getMessage());

		return model;
	}


	/**
	 * 
	 * <pre>
	 * 1.  기능		: 조회 데이터가 없을 경우 성공 Exception을 보낸다. 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public ModelAndView DataNotFoundException(DataNotFoundException ext) {

		if(logable) logger.debug("[ExceptionAnnotationHandler] DataNotFoundException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "200");
		//model.addObject("errorMsg", this.getMsg("COM.NOT_FOUND.200"));

		return model;
	}

	/**
	 * 
	 * <pre>
	 * 1.  기능		: POST,PUT,DELETE 들어온 데이터 파라메타 체크  
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(ParameterException.class)
	public ModelAndView ParameterException(Exception ext) {
		//ParameterException field = (ParameterException) ext;

		if(logable) logger.debug("[ExceptionAnnotationHandler] ParameterException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", this.getMsg("COM.BAD_REQUEST.400", new Object[] { field.getMessage() }));

		return model;
	}

	/*private void printExceptionInfo(HttpServletRequest request, Exception ex) {
		logger.error("PARMAS: {}", request.getParameterMap());
		for (StackTraceElement s : ex.getStackTrace()) {
			logger.error("STACK: {}", s);
		}
	}*/

	/**
	 * 
	 * <pre>
	 * 1.  기능		: RuntimeException 정의 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView runtimeException(Exception ext) {
		RuntimeException ret = (RuntimeException) ext;

		if(logable) logger.debug("[ExceptionAnnotationHandler] runtimeException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ret.getMessage());
		return model;
	}

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 데이타 엑세스 오류 
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccessException(Exception ext) {
		DataAccessException ret = (DataAccessException) ext;

		if(logable) logger.debug("[ExceptionAnnotationHandler] dataAccessException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ret.getMessage());
		return model;
	}
	/**
	 * 
	 * <pre>
	 * 1.  기능		: Null Pointer Exception 정의 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 26.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param ext
	 * @return
	 * </pre>
	 */
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView NullPointerException(Exception ext) {
		NullPointerException ret = (NullPointerException) ext;

		if(logable) logger.debug("[ExceptionAnnotationHandler] NullPointerException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ret.getMessage());
		return model;
	}

	/**
	 * 
	 * <pre>
	 * 1.  기능		: 잘못된 url 처리 : HTTP STATUS : 404 
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 20.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * &#64;param req
	 * &#64;param ext
	 * &#64;return
	 * </pre>
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView NoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ext) {
		//String errorURL = req.getRequestURL().toString();

		//if(logable) logger.debug("[ExceptionAnnotationHandler] NoHandlerFoundException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		//model.addObject("errorCode", "99");
		//model.addObject("errorMsg", "잘못된 주소 :" + errorURL);
		return model;
	}

	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - restTemplate 클라이언트 연결 Exception
	 *  -  
	 * 2. 작성자/작성일: kim/2018. 2. 20.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * &#64;param ext
	 * &#64;return
	 * </pre> 
	 */
	@ExceptionHandler(RestTemplateClientException.class)
	public ModelAndView RestClientException(Exception ext) {
		RestTemplateClientException ret = (RestTemplateClientException) ext;
		if(logable) logger.debug("[ExceptionAnnotationHandler] RestClientException >> Error >" + CommonUtils.getPrintStackTrace(ext));
		ModelAndView model = new ModelAndView("redirect:/common/error.do");
		model.addObject("errorCode", "99");
		//model.addObject("errorMsg", ret.getMessage());
		
		return model;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - 세션 만료 exception 처리
	 *  -  
	 * 2. 작성자/작성일: seongcheol/2018. 06. 18.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * 
	 * </pre> 
	 * @throws IOException 
	 */
	@ExceptionHandler(SessionExpiredException.class)
	public void SessionExpiredException(Exception ext, HttpServletResponse response, HttpServletRequest request) throws IOException {
		SessionExpiredException see = (SessionExpiredException) ext;
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(see.getStatus());
		
		String data = PropertiesUtils.getProperties("exception.session.expire.msg", request.getLocale());
		
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
	}
	
}
