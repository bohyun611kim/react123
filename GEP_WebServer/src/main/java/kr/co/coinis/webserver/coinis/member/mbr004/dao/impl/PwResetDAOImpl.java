/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr004.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.member.mbr004.dao.PwResetDAO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr004.dao.impl 
 *    |_ PwResetDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:11:10
 * @version : 
 * @author : Seongcheol
 */
@Repository("pwResetDAO")
public class PwResetDAOImpl extends AbstractDefaultMapper implements PwResetDAO {

	@Override
	public String getNamespace() {
		return PwResetDAO.NAMESPACE;
	}
}
