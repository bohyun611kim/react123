/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt001.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.support.spt001.dao.NoticeDAO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.NoticeVO;
import kr.co.coinis.webserver.coinis.support.spt001.vo.ReqNoticeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt001.dao.impl 
 *    |_ NoticeDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 1:33:03
 * @version : 
 * @author : Seongcheol
 */
@Repository("noticeDAO")
public class NoticeDAOImpl extends AbstractDefaultMapper implements NoticeDAO {

	@Override
	public String getNamespace() {
		return NoticeDAO.NAMESPACE;
	}
	
	@Override
	public int selectNoticeSize(ReqNoticeVO reqNoticeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectNoticeSize", reqNoticeVO);
	}
	
	@Override
	public List<NoticeVO> selectNoticeList(ReqNoticeVO reqNoticeVO) {
		return getSqlSession().selectList(this.getNamespace() + ".selectNoticeList", reqNoticeVO);
	}
	
	@Override
	public NoticeVO selectNoticeOne(ReqNoticeVO reqNoticeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectNoticeOne", reqNoticeVO);
	}
}
