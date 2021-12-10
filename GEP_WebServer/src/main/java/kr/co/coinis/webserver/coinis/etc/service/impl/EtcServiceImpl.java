/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.etc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.etc.dao.EtcDAO;
import kr.co.coinis.webserver.coinis.etc.service.EtcService;

/**
 * <pre>
 * kr.co.coinis.webserver.etc.service.impl 
 *    |_ EtcServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:02:12
 * @version : 
 * @author : Seongcheol
 */
@Service("etcService")
public class EtcServiceImpl implements EtcService {

	@Resource(name="etcDAO")
	private EtcDAO etcDAO;
}
