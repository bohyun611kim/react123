/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt002.dao.impl;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.support.spt002.dao.FaqDAO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt002.dao.impl 
 *    |_ FaqDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 2:40:58
 * @version : 
 * @author : Seongcheol
 */
@Repository("faqDAO")
public class FaqDAOImpl extends AbstractDefaultMapper implements FaqDAO {

	@Override
	public String getNamespace() {
		return FaqDAO.NAMESPACE;
	}
}
