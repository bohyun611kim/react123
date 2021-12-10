/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.coinis.mypage.mpg004.dao.IdSetupDAO;
import kr.co.coinis.webserver.coinis.mypage.mpg004.service.IdSetupService;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.CorpInfoVO;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.ExtraUserInfoVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.coinis.mypage.mpg004.service.impl 
 *    |_ IdSetupServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 5. 16. 오전 11:10:36
 * @version : 
 * @author : yeonseoo
 */
@Service("idSetupService")
public class IdSetupServiceImpl implements IdSetupService {

	@Resource(name="idSetupDAO")
	private IdSetupDAO idSetupDAO;

	@Override
	public List<String> selectCountryList() {
		return idSetupDAO.selectCountryList();
	}

	@Override
	public ResultVO uploadIdvIdCardFile(ReqIdCardVO reqIdCardVO, int user_type_cd) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		ResultVO result = new ResultVO();
		
		// user_type, auth_level, reg_dt
		ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
		param.setExchange_id(reqIdCardVO.getExchangeId());
		param.setUser_id(reqIdCardVO.getUserId());
		
		ExtraUserInfoVO extraUserInfo = idSetupDAO.selectExtraUserInfo(param);
		// 회원 존재여부 확인
		int check = Double.valueOf(CommonUtils.strNlv(extraUserInfo.getReg_dt(), "-1")).intValue();
		if ( check == -1) {
			result.setRtnCd(-1);
			result.setRtnMsg("User Info does not exist");
			return result;
		}
		// 회원 레벨(2) 확인
		else if (extraUserInfo.getAuth_level() != 2) {
			result.setRtnCd(-1);
			result.setRtnMsg("auth level condition is not qualified");
			return result;
		}
		// 개인 회원 확인
		else if (extraUserInfo.getUser_type_cd() != user_type_cd) {
			result.setRtnCd(-1);
			result.setRtnMsg("you are not an Indivisual member");
			return result;
		}

		// 회원 추가 정보 저장
		paramMap.put("ARG_EXCHANGE_ID", reqIdCardVO.getExchangeId());
		paramMap.put("ARG_USER_ID", reqIdCardVO.getUserId());
		paramMap.put("ARG_USER_TYPE_CD", extraUserInfo.getUser_type_cd());
		paramMap.put("ARG_COUNTRY_CD", reqIdCardVO.getCountry());
		paramMap.put("ARG_POSTAL_CODE", reqIdCardVO.getPostal());
		paramMap.put("ARG_ADDRESS", reqIdCardVO.getAddress());
		paramMap.put("ARG_FULL_NAME", reqIdCardVO.getUserName());
		paramMap.put("ARG_BIRTHDAY", reqIdCardVO.getBirthday());
		paramMap.put("ARG_ID_TYPE_CD", reqIdCardVO.getIdCardType());
		paramMap.put("ARG_ID_VERIFI_YN", null);
		paramMap.put("ARG_ID_VERIFI_RESULT_CD", null);
		paramMap.put("ARG_ID_VERIFI_ERR_DET_CD", null); 
		paramMap.put("ARG_MEMO_CONT", null); 
		paramMap.put("ARG_ID_VERIFI_PROC_ID", null);
		paramMap.put("ARG_KEY1", reqIdCardVO.getUserId());
		paramMap.put("ARG_KEY2", extraUserInfo.getReg_dt());
		paramMap.put("V_RTN_CD", -1);
		paramMap.put("V_RTN_MSG", "");
		
		resultMap.clear();
		resultMap = idSetupDAO.call_PR_WAS_UPDATE_MEMBER_INFO_EXTRA(paramMap);
		int rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
		if( rtn_cd != 0 ) {
			result.setRtnCd(rtn_cd);
			result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
			
			return result;
		}
		
		// 신분증 파일 저장
		paramMap.clear();
		paramMap.put("ARG_EXCHANGE_ID", reqIdCardVO.getExchangeId());
		paramMap.put("ARG_USER_ID", reqIdCardVO.getUserId());
		paramMap.put("ARG_FILE_NM_OF_ID", reqIdCardVO.getFileName1());
		//paramMap.put("V_RTN_CD", null);
		//paramMap.put("V_RTN_MSG", "");
		
		// 첫 번째 파일 저장
		resultMap.clear();
		resultMap = idSetupDAO.call_PR_WAS_INSERT_ID_AUTH_INFO(paramMap);
		rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
		if( rtn_cd == 0 ) {
			// 두번째 파일 저장
			paramMap.put("ARG_FILE_NM_OF_ID", reqIdCardVO.getFileName2());
			resultMap = idSetupDAO.call_PR_WAS_INSERT_ID_AUTH_INFO(paramMap);
			rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
			if( rtn_cd != 0) {
				result.setRtnCd(rtn_cd);
				result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
				
				return result;
			}
			
			result.setRtnCd(rtn_cd);
			result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
		} else {
			result.setRtnCd(rtn_cd);
			result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
		}
		
		return result;
	}

	@Override
	public CorpInfoVO selectCorpInfo(Map<String, Object> paramMap) {
		return idSetupDAO.selectCorpInfo(paramMap);
	}

	@Override
	public ResultVO uploadCorpIdCardFile(ReqIdCardVO reqIdCardVO, int user_type_cd) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		ResultVO result = new ResultVO();
		
		// user_type, auth_level, reg_dt
		ExchangeIDUserIDPairVO param = new ExchangeIDUserIDPairVO();
		param.setExchange_id(reqIdCardVO.getExchangeId());
		param.setUser_id(reqIdCardVO.getUserId());
		
		ExtraUserInfoVO extraUserInfo = idSetupDAO.selectExtraUserInfo(param);
		// 회원 존재여부 확인
		int check = Double.valueOf(CommonUtils.strNlv(extraUserInfo.getReg_dt(), "-1")).intValue();
		if ( check == -1) {
			result.setRtnCd(-1);
			result.setRtnMsg("User Info does not exist");
			return result;
		}
		// 회원 레벨(2) 확인
		else if (extraUserInfo.getAuth_level() != 2) {
			result.setRtnCd(-1);
			result.setRtnMsg("auth level condition is not qualified");
			return result;
		}
		// 법인 회원 확인
		else if (extraUserInfo.getUser_type_cd() != user_type_cd) {
			result.setRtnCd(-1);
			result.setRtnMsg("you are not a Corporate member");
			return result;
		}

		// 신분증 파일 저장
		paramMap.clear();
		paramMap.put("ARG_CORP_REG_NUM", reqIdCardVO.getCorpNo());
		//paramMap.put("V_RTN_CD", null);
		//paramMap.put("V_RTN_MSG", "");
		
		// 첫 번째 파일 저장
		paramMap.put("ARG_FILE_NM_OF_CORP_AUTH", reqIdCardVO.getFileName1());
		resultMap.clear();
		resultMap = idSetupDAO.call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO(paramMap);
		int rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
		
		if( rtn_cd == 0 ) {
			// 두 번째 파일 저장
			paramMap.put("ARG_FILE_NM_OF_CORP_AUTH", reqIdCardVO.getFileName2());
			resultMap = idSetupDAO.call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO(paramMap);
			rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
			
			if( rtn_cd != 0) {
				result.setRtnCd(rtn_cd);
				result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
				
				return result;
			}
			
			// 세 번째 파일 저장
			paramMap.put("ARG_FILE_NM_OF_CORP_AUTH", reqIdCardVO.getFileName3());
			resultMap = idSetupDAO.call_PR_WAS_INSERT_MEMBER_CORP_AUTH_INFO(paramMap);
			rtn_cd = Double.valueOf(CommonUtils.strNlv(resultMap.get("V_RTN_CD"), "-1")).intValue();
			
			if( rtn_cd != 0) {
				result.setRtnCd(rtn_cd);
				result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
				
				return result;
			}
		} else {
			result.setRtnCd(rtn_cd);
			result.setRtnMsg((String) resultMap.get("V_RTN_MSG"));
		}
		
		return result;
	}

	@Override
	public ResultVO updateMemberInfoExtraIDAuthInfo(ReqIdCardVO param) throws SQLException {
		
		ResultVO resultVO = new ResultVO();
		
		int rtn_cd = idSetupDAO.updateMemberInfoExtraIDAuthInfo(param);
		
		if( rtn_cd != 1)
			resultVO.setRtnCd(-1);
		else
			resultVO.setRtnCd(0);
		
		return resultVO;
	}

}
