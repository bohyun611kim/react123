/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;
import kr.co.coinis.webserver.yahobit.crowd.dao.YahobitCrowdDAO;
import kr.co.coinis.webserver.yahobit.crowd.vo.BalanceVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.BonusInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleOnVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.CrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoByNoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.PriceInfoVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqJoinCrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqKycAuthVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.dao.impl 
 *    |_ YahobitCrowdDAOImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오전 9:14:27
 * @version : 
 * @author : Seongcheol
 */
@Repository("yahobitCrowdDAO")
public class YahobitCrowdDAOImpl extends AbstractDefaultMapper implements YahobitCrowdDAO {

	@Override
	public String getNamespace() {
		return YahobitCrowdDAO.NAMESPACE;
	}
	
	@Override
	public List<CrowdSaleVO> selectCrowdSaleList(String exchangeId) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectCrowdSaleList", exchangeId);
	}
	
	@Override
	public CrowdSaleInfoVO selectCrowdSaleInfo(Map<String, Object> param) throws Exception {
		return getSqlSession().selectOne(this.getNamespace() + ".selectCrowdSaleInfo", param);
	}
	
	@Override
	public List<CrowdSaleOnVO> selectCrowdSaleOnList(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectCrowdSaleOnList", param);
	}
	
	@Override
	public List<BonusInfoVO> selectBonusList(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectBonusList", param);
	}
	
	@Override
	public List<BalanceVO> selectBalance(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectBalance", param);
	}
	
	@Override
	public List<PriceInfoVO> selectPriceInfo(Map<String, Object> param) throws Exception {
		return getSqlSession().selectList(this.getNamespace() + ".selectPriceInfo", param);
	}
	
	@Override
	public int selectKycYn(Map<String, Object> param) throws Exception {
		
		Integer approval_stat_cd = getSqlSession().selectOne(this.getNamespace() + ".selectKycYn", param);
		
		if(approval_stat_cd == null)
			approval_stat_cd = -1;
		
		return approval_stat_cd;
	}
	
	@Override
	public int selectAuthMeansCd(Map<String, Object> param) throws Exception {
		return getSqlSession().selectOne(this.getNamespace() + ".selectAuthMeansCd", param);
	}
	
	@Override
	public String selectUserAuthInfo(AuthCodeVO authCodeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectUserAuthInfo", authCodeVO);
	}
	
	@Override
	public String selectOtpKey(AuthCodeVO authCodeVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectOtpKey", authCodeVO);
	}
	
	@Override
	public ReqKycAuthVO procInsertCrowdSaleKycInfo(ReqKycAuthVO reqKycAuthVO) {
		getSqlSession().update(this.getNamespace() + ".procInsertCrowdSaleKycInfo", reqKycAuthVO);
		return reqKycAuthVO;
	}
	
	@Override
	public Map<String, Object> procInsertCrowdSaleKycPhoto(Map<String, Object> param) {
		getSqlSession().update(this.getNamespace() + ".procInsertCrowdSaleKycPhoto", param);
		return param;
	}
	
	@Override
	public AuthCodeVO procInsertAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procInsertAuthCode", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public AuthCodeVO procCheckAuthCode(AuthCodeVO authCodeVO) {
		getSqlSession().update(this.getNamespace() + ".procCheckAuthCode", authCodeVO);
		return authCodeVO;
	}
	
	@Override
	public PriceInfoByNoVO selectPriceInfoByNo(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectPriceInfoByNo", reqJoinCrowdSaleVO);
	}
	
	@Override
	public ReqJoinCrowdSaleVO procInsertCrowdSaleOrderHist(ReqJoinCrowdSaleVO reqJoinCrowdSaleVO) {
		getSqlSession().update(this.getNamespace() + ".procInsertCrowdSaleOrderHist", reqJoinCrowdSaleVO);
		return reqJoinCrowdSaleVO;
	}

	@Override
	public String selectApprovalProcMemo(Map<String, Object> param) {
		return getSqlSession().selectOne(this.getNamespace() + ".selectApprovalProcMemo", param);
	}

	@Override
	public double posibleCrowdSale(Map<String, Object> checkParam) {
		return getSqlSession().selectOne(this.getNamespace() + ".posibleCrowdSale", checkParam);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> reqQtyMax(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".reqQtyMax", paramMap);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> reqBonus(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".reqBonus", paramMap);
	}
}
