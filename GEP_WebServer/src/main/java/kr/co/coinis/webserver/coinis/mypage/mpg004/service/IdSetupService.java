/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.CorpInfoVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.service 
 *    |_ IdSetupService.java
 * 
 * </pre>
 * @date : 2019. 5. 16. 오전 11:07:40
 * @version : 
 * @author : yeonseoo
 */
public interface IdSetupService {
	
	/* 국가 영문명 리스트 조회 */
	public List<String> selectCountryList();
	
	/**
	 * <pre>
	 * 1. 개요 : 개인 회원 신분증 자료 제출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uploadIdvIdCardFile
	 * @date : 2019. 5. 14.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 14.		yeonseoo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 */
	public ResultVO uploadIdvIdCardFile(ReqIdCardVO reqIdCardVO, int user_type_cd) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사업자 등록번호 조회
	 * 2. 처리내용 : 회원에 해당하는 사업자 등록번호를 조회한다. 조회실패시 null
	 * </pre>
	 * @Method Name : selectCorpInfo
	 * @date : 2019. 5. 14.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 14.		yeonseoo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 */
	public CorpInfoVO selectCorpInfo(Map<String, Object> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 개인 회원 신분증 자료 제출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uploadCorpIdCardFile
	 * @date : 2019. 5. 14.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 14.		yeonseoo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 */
	public ResultVO uploadCorpIdCardFile(ReqIdCardVO reqIdCardVO, int user_type_cd) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 회원 추가정보 신분증 인증정보 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateMemberInfoExtraIDAuthInfo
	 * @date : 2019. 5. 14.
	 * @author : yeonseoo
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 14.		yeonseoo				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param reqIdCardVO
	 * @return
	 */
	public ResultVO updateMemberInfoExtraIDAuthInfo(ReqIdCardVO param) throws SQLException;

}
