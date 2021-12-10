/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.home.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.holdport.home.dao.HoldportHomeDAO;
import kr.co.coinis.webserver.holdport.home.vo.BannerVO;
import kr.co.coinis.webserver.holdport.home.vo.TopNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.home.dao.impl 
 *    |_ YahoBitHomeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오전 9:10:31
 * @version : 
 * @author : Seongcheol
 */

@Repository("holdportHomeDAO")
public class HoldportHomeDAOImpl extends AbstractDefaultMapper implements HoldportHomeDAO {

	@Override
	public String getNamespace() {
		return HoldportHomeDAO.NAMESPACE;
	}
	
	@Override
	public List<BannerVO> selectBannerList(String exchangeId) {
		return getSqlSession().selectList(this.getNamespace() + ".selectBannerList", exchangeId);
	}
	
	@Override
	public BannerVO selectBanner(String exchangeId) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectBanner", exchangeId);
	}
	
	@Override
	public List<TopNoticeVO> selectNoticeList(String exchangeId) {
		return getSqlSession().selectList(this.getNamespace() + ".selectNoticeList", exchangeId);
	}

	@Override
	public List<Map> selectInfluencerTop10List() {
		return getSqlSession().selectList(this.getNamespace() + ".selectInfluencerTop10List");
	}
}
