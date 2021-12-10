/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.dao;

import java.util.List;

import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryCodeVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.OneInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddReAskVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqOneInquiryVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt003.dao 
 *    |_ InquiryDAO.java
 * 
 * </pre>
 * @date : 2019. 05. 02. 오후 2:44:07
 * @version : 
 * @author : Jungjea
 */
public interface InquiryDAO {

	public static final String NAMESPACE = InquiryDAO.class.getName();
	
	/* 1:1 문의 조회  */
	public OneInquiryVO getoneinquiry(ReqOneInquiryVO reqOneInquiryVO);

	/* 1:1 문의 List 조회  */
	public List<InquiryVO> getInquiryList(ReqInquiryVO param);
	
	/* 1:1 문의 갯수 조회  */
	public int getInquiryListCount(ReqInquiryVO param);
	
	/* 1:1 문의 내용 답변(재문의) */
	public ReqAddReAskVO addReAsk(ReqAddReAskVO reqAddReAskVO);

	/* 1:1 문의 자료 제출 (재문의)*/
	public ReqAddCertifyVO addReAskCertify(ReqAddCertifyVO reqAddCertifyVO);
	
	/* 1:1 문의 팝업 내 문의구분 조회 */
	public List<InquiryCodeVO> getInquiryCode(InquiryCodeVO inquiryCodeVO);

	/* new 1:1 문의 등록*/
	public ReqAddInquiryVO addInquiry(ReqAddInquiryVO reqAddInquiryVO);
	
	/* new 1:1 문의 자료 제출 */
	public ReqAddCertifyVO addInquiryCertify(ReqAddCertifyVO reqAddCertifyVO);

}
