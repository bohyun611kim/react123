/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.coinis.webserver.coinis.support.spt003.dao.InquiryDAO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryCodeVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.OneInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddReAskVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqOneInquiryVO;
import kr.co.coinis.webserver.framework.core.dao.comm.AbstractDefaultMapper;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt003.dao.impl 
 *    |_ InquiryDAOImpl.java
 * 
 * </pre>
 * 
 * @date : 2019. 05. 02. 오후 2:44:50
 * @version :
 * @author : Jungjea
 */
@Repository("inquiryDAO")
public class InquiryDAOImpl extends AbstractDefaultMapper implements InquiryDAO {

	@Override
	public String getNamespace() {
		return InquiryDAO.NAMESPACE;
	}

	/* 1:1 문의 조회 */
	@Override
	public OneInquiryVO getoneinquiry(ReqOneInquiryVO reqOneInquiryVO) {
		return getSqlSession().selectOne(this.getNamespace() + ".getoneinquiry", reqOneInquiryVO);
	}

	/* 1:1 문의 List 조회 */
	@Override
	public List<InquiryVO> getInquiryList(ReqInquiryVO param) {
		return getSqlSession().selectList(this.getNamespace() + ".getInquiryList", param);
	}

	/* 1:1 문의 갯수 조회 */
	@Override
	public int getInquiryListCount(ReqInquiryVO param) {
		return getSqlSession().selectOne(this.getNamespace() + ".getInquiryListCount", param);
	}

	/* 1:1 문의 내용 답변(재문의) */
	@Override
	public ReqAddReAskVO addReAsk(ReqAddReAskVO reqAddReAskVO) {
		getSqlSession().update(this.getNamespace() + ".addReAsk", reqAddReAskVO);
		return reqAddReAskVO;
	}

	/* 1:1 문의 자료 제출 (재문의) */
	@Override
	public ReqAddCertifyVO addReAskCertify(ReqAddCertifyVO reqAddCertifyVO) {
		getSqlSession().update(this.getNamespace() + ".addReAskCertify", reqAddCertifyVO);
		return reqAddCertifyVO;
	}

	/* 1:1 문의 팝업 내 문의구분 조회 */
	@Override
	public List<InquiryCodeVO> getInquiryCode(InquiryCodeVO inquiryCodeVO) {
		return getSqlSession().selectList(this.getNamespace() + ".getInquiryCode", inquiryCodeVO);
	}

	/* new 1:1 문의 등록 */
	@Override
	public ReqAddInquiryVO addInquiry(ReqAddInquiryVO reqAddInquiryVO) {
		getSqlSession().update(this.getNamespace() + ".addInquiry", reqAddInquiryVO);
		return reqAddInquiryVO;
	}

	/* new 1:1 문의 자료 제출 */
	@Override
	public ReqAddCertifyVO addInquiryCertify(ReqAddCertifyVO reqAddCertifyVO) {
		getSqlSession().update(this.getNamespace() + ".addInquiryCertify", reqAddCertifyVO);
		return reqAddCertifyVO;
	}
}
