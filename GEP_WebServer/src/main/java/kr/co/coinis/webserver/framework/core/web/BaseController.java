package kr.co.coinis.webserver.framework.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.common.utils.CommonUtils;
/**
 * <pre>
 * 1.  기능		
 *   - Main Root Context 설정
 * 2.  주의사항	
 *     
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
@Controller
public class BaseController extends AbstractBaseController {
														
	/**
	 * 
	 * <pre>
	 * 1. 기능             
	 *  - Default Home Controller
	 * 2. 작성자/작성일: kim/2018. 3. 19.
	 * ====================================
	 * - 수정자/수정일/수정내용
	 * ----------------------------------
	    1) 
	 * ====================================
	 * <p/>
	 * 
	 * @return
	 * @throws Exception
	 * </pre>
	 */ 
	/*@RequestMapping(value="/")
	public String defaultUrl(HttpServletRequest request)  throws Exception{
																							
		logger.debug("==Welcome to COINIS WEB ==");
		return CommonUtils.getPageKey(request) + "/index";
	}*/
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request)  throws Exception{
																							
		logger.debug("Called /login");
		ModelAndView model = new ModelAndView();

		/* 2019.12.06 YAHOBIT 리브랜딩으로 인하여 ALIBIT으로 변경(strPageKey) */
		String strPageKey = CommonUtils.getExchangeId(request).toLowerCase();
		if(strPageKey.equalsIgnoreCase("yahobit"))
			strPageKey = "alibit";
		
		model.setViewName("reidrect:/" + strPageKey + "/login");
		
		return model;
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(HttpServletRequest request)  throws Exception{
																							
		logger.debug("Called /login");
		ModelAndView model = new ModelAndView();

		/* 2019.12.06 YAHOBIT 리브랜딩으로 인하여 ALIBIT으로 변경(strPageKey) */
		String strPageKey = CommonUtils.getExchangeId(request).toLowerCase();
		if(strPageKey.equalsIgnoreCase("yahobit"))
			strPageKey = "alibit";
		
		model.setViewName("reidrect:/" + strPageKey + "/home");
		
		return model;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request)  throws Exception{
																							
		logger.debug("Called /logout");
		ModelAndView model = new ModelAndView();

		/* 2019.12.06 YAHOBIT 리브랜딩으로 인하여 ALIBIT으로 변경(strPageKey) */
		String strPageKey = CommonUtils.getExchangeId(request).toLowerCase();
		if(strPageKey.equalsIgnoreCase("yahobit"))
			strPageKey = "alibit";
		
		model.setViewName("reidrect:/" + strPageKey + "/logout");
		
		return model;
	}
}
