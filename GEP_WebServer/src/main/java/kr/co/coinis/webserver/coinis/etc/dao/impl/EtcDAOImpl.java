/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.etc.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.etc.dao.EtcDAO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.etc.dao.impl 
 *    |_ EtcDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오전 10:01:55
 * @version : 
 * @author : Seongcheol
 */
@Repository("etcDAO")
public class EtcDAOImpl extends AbstractDefaultMapper implements EtcDAO {
	
	@Override
	public String getNamespace() {
		return EtcDAO.NAMESPACE;
	}
}
