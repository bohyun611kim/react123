/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt002.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.support.spt002.dao.FaqDAO;
import kr.co.coinis.webserver.coinis.support.spt002.service.FaqService;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt002.service.impl 
 *    |_ FaqServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 2:39:51
 * @version : 
 * @author : Seongcheol
 */
@Service("FaqService")
public class FaqServiceImpl implements FaqService {

	@Resource(name="faqDAO")
	private FaqDAO faqDAO;
}
