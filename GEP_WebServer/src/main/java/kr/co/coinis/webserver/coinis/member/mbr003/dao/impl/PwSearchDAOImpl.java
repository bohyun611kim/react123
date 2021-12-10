/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.member.mbr003.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.member.mbr003.dao.PwSearchDAO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.member.mbr003.dao.impl 
 *    |_ PwSearchDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:09:41
 * @version : 
 * @author : Seongcheol
 */
@Repository("pwSearchDAO")
public class PwSearchDAOImpl extends AbstractDefaultMapper implements PwSearchDAO {

	@Override
	public String getNamespace() {
		return PwSearchDAO.NAMESPACE;
	}
}
