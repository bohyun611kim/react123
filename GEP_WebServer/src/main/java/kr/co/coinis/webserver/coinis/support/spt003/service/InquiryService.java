/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryCodeVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.OneInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddAskCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqOneInquiryVO;
import kr.co.coinis.webserver.common.vo.ResultVO;


/**
 * <pre>
 * kr.co.coinis.webserver.support.spt003.service 
 *    |_ InquiryService.java
 * 
 * </pre>
 * @date : 2019. 05. 02. 오후 2:42:36
 * @version : 
 * @author : Jungjea
 */
public interface InquiryService {
	
	/* 1:1 문의 조회  */
	public OneInquiryVO getoneinquiry(ReqOneInquiryVO reqOneInquiryVO);
	
	/* 1:1 문의 List 조회  */
	public Map<String, Object> getInquiryList(ReqInquiryVO reqInquiryVO);
		
	/* 1:1 문의 내용 답변(재문의) */
	public ResultVO addReAsk(ReqAddAskCertifyVO reqAddAskCertifyVO) throws Exception;

	/* 1:1 문의 팝업 내 문의구분 조회 */
	public List<InquiryCodeVO> getInquiryCode(InquiryCodeVO inquiryCodeVO);
	
	/* new 1:1 문의 등록*/
	public ResultVO addInquiry(ReqAddInquiryCertifyVO reqAddInquiryCertifyVO) throws Exception;
}
