/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr004.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.member.mbr004.dao.PwResetDAO;
import kr.co.coinis.webserver.coinis.member.mbr004.service.PwResetService;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr004.service.impl 
 *    |_ PwResetServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:11:25
 * @version : 
 * @author : Seongcheol
 */
@Service("pwResetService")
public class PwResetServiceImpl implements PwResetService {

	@Resource(name="pwResetDAO")
	private PwResetDAO pwResetDAO;
}
