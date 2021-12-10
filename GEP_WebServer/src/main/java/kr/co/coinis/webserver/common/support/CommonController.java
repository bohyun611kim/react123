package kr.co.coinis.webserver.common.support;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.coinis.webserver.framework.core.web.AbstractBaseController;
/**
 * 
* <pre>
* 1.  기능	: 공통 오류 처리  
* 2.  처리개요	: 
* 3.  주의사항	:  
* 4.  작성자/작성일	:  김경주/2018. 2. 22.
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
@Controller
public class CommonController extends AbstractBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		: 공통 에러 메세지 Redirect  
	 * 2.  처리개요	: 
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  김경주/2018. 2. 22.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param model
	 * @param auth
	 * @param request
	 * @return
	 * </pre>
	 */
	@RequestMapping("/common/error.do")
	public String commonError(Model model, Authentication auth, HttpServletRequest request){
		//model.addAttribute("errorCode", request.getParameter("errorCode"));
		//model.addAttribute("errorMsg", request.getParameter("errorMsg"));
		if(request.getParameter("errorMsg") != null) {
			logger.error(request.getParameter("errorMsg"));
		}
		return "common/error/errors";
	}
}
