/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr003.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.member.mbr003.dao.PwSearchDAO;
import kr.co.coinis.webserver.coinis.member.mbr003.service.PwSearchService;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr003.service.impl 
 *    |_ PwSearchServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:56
 * @version : 
 * @author : Seongcheol
 */
@Service("pwSearchService")
public class PwSearchServiceImpl implements PwSearchService {

	@Resource(name="pwSearchDAO")
	private PwSearchDAO pwSearchDAO;
}
