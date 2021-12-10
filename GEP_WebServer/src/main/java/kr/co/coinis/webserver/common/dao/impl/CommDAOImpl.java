/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.googlecode.ehcache.annotations.Cacheable;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.dao.CommDAO;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.CodeVO;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ItemCodeVO;
import kr.co.coinis.webserver.common.vo.ServerInfoVO;
import kr.co.coinis.webserver.common.vo.TemplateVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.common.dao.impl 
 *    |_ CommDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오전 10:18:02
 * @version : 
 * @author : Seongcheol
 */
@Repository("commDAO")
public class CommDAOImpl extends AbstractDefaultMapper implements CommDAO {

	@Override
	public String getNamespace() {
		return CommDAO.NAMESPACE;
	}
	
	@Override
	public ServerInfoVO selectServerInfo(int serverNo) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectServerInfo", serverNo);
	}
	
	@Override
	public List<Map<String, String>> selectServerEnvInfo(int serverNo) {
		return getSqlSession().selectList(this.getNamespace() + ".selectServerEnvInfo", serverNo);
	}
	
	@Override
	public String selectIfTransactionId(int serverNo) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectIfTransactionId", serverNo);
	}
	
	@Override
	public String selectSndDt() {
		return getSqlSession().selectOne(this.getNamespace() + ".selectSndDt");
	}
	
	@Override
	public TemplateVO selectRegisterTemplate(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectRegisterTemplate", param);
	}
	
	@Override
	public AuthCodeVO procAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procAuthCode", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public int deleteEmailAuthCode(AuthCodeVO authCodeVO) {
		return getSqlSession().delete(this.getNamespace() + ".deleteEmailAuthCode", authCodeVO);
	}
	
	@Override
	public Map<String, Object> procCheckOrder(Map<String, Object> param) {
		getSqlSession().update(this.getNamespace() + ".procCheckOrder", param);
		return param;
	}
	
	@Override
	public Map<String, String> selectOrderQueueNm(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectOrderQueueNm", param);
	}
	
	@Override
	public String selectDefaultMktGrpId(String exchangeId) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectDefaultMktGrpId", exchangeId);
	}
	
	@Override
	public List<String> selectMktGrpList(String exchangeId) {
		return getSqlSession().selectList(this.getNamespace() + ".selectMktGrpList", exchangeId);
	}
	
	@Override
	public Map<String, Object> procPwChange(Map<String, Object> param) {
		getSqlSession().update(this.getNamespace() + ".procChangePassword", param);
		return param;
	}
	
	@Override
	public InsertHistoryVO procInsertUserRequestHist(InsertHistoryVO insertHistoryVO) {
		getSqlSession().update(this.getNamespace() + ".procInsertUserRequestHist", insertHistoryVO);
		return insertHistoryVO;
	}
	
	@Cacheable(cacheName = "wordBook")
	@Override
	public List<Map<String, Object>> selectWordBook() {
		return getSqlSession().selectList(this.getNamespace() + ".selectWordBook");
	}

	@Override
	public Map<String, Object> selectTemplateMsgContent(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectTemplateMsgContent", param);
	}

	@Override
	public List<Map<String, Object>> selectCodeInfo(Map paramMap) {
		return getSqlSession().selectList(this.getNamespace() + ".selectCodeInfo", paramMap);
	}
	
	@Override
	public List<CodeVO> selectCodeInfoList(Map<String, Object> param) {
		return getSqlSession().selectList(this.getNamespace() + ".selectCodeInfoList", param);
	}
	
	@Override
	public void selectItemCodeMaster() {
		List<ItemCodeVO> result = getSqlSession().selectList(this.getNamespace() + ".selectItemCodeMaster");
		
		ConcurrentHashMap<String, ItemCodeVO> temp = new ConcurrentHashMap<String, ItemCodeVO>();
		
		for(int i=0; i<result.size(); i++) {
			temp.put(result.get(i).getItemCode(), result.get(i));
		}
		
		CamelHelper.itemCodeMaster.clear();
		CamelHelper.itemCodeMaster.putAll(temp);
	}
	
	@Override
	public FailCntVO procSetFailCnt(FailCntVO failCntVO) {
		getSqlSession().update(this.getNamespace() + ".procSetFailCnt", failCntVO);
		return failCntVO;
	}
}
