/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg002.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.dao.HoldportAuthDAO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.service.HoldportAuthService;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqIdCardVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.dao.HoldportWalletDAO;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg002.service.impl
 *    |_ HoldportAuthServiceImpl.java
 *
 * </pre>
 * @date : 2019. 5. 8. 오전 10:45:52
 * @version :
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("holdportAuthService")
public class HoldportAuthServiceImpl implements HoldportAuthService {

	//private static final Logger LOG = LoggerFactory.getLogger(HoldportAuthServiceImpl.class);

	@Resource(name="holdportWalletDAO")
	private HoldportWalletDAO holdportWalletDAO;

	@Resource(name="holdportAuthDAO")
	private HoldportAuthDAO holdportAuthDAO;

	@Override
	public Map<String, Object> call_PR_WAS_SET_OTP_AUTH(Map paramMap) throws SQLException {
		return holdportAuthDAO.call_PR_WAS_SET_OTP_AUTH(paramMap);
	}

	@Override
	public int updateMemberInfoAtOtp(Map paramMap) throws SQLException {
		return holdportAuthDAO.updateMemberInfoAtOtp(paramMap);
	}

	@Override
	public boolean checkUserPassword(Map paramMap) throws SQLException {
		return holdportAuthDAO.checkUserPassword(paramMap);
	}

	@Override
	public AuthLevel4VO selectAuthLevel4Info(Map<String, Object> param) {
		return holdportAuthDAO.selectAuthLevel4Info(param);
	}

	@Override
	public ResultVO uploadIdCard(ReqIdCardVO reqIdCardVO) throws Exception {
		ResultVO result = new ResultVO();

		// 회원 정보 조회
		String userInfo = holdportAuthDAO.selectUserInfo(reqIdCardVO);

		reqIdCardVO.setRegDt(userInfo);

		// 인증 레벨 확인
		String level = holdportAuthDAO.selectAuthLevel(reqIdCardVO);

		level = level == null ? "0":level;

		// 인증 레벨에 따른 분기 처리
		switch(Integer.parseInt(level)) {
		case 3:
			// 추가 정보 저장
			int cnt = holdportAuthDAO.updateLevel4Request(reqIdCardVO);

			// 신분증 파일 저장
			// PR_WAS_INSERT_ID_AUTH_INFO
			if(cnt == 1) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", reqIdCardVO.getExchangeId());
				param.put("userId", reqIdCardVO.getUserId());
				param.put("fileNm", reqIdCardVO.getFileName1());

				param = holdportAuthDAO.procInsertIdAuthInfo(param);

				if((int) param.get("rtnCd") == 0) {
					param.put("fileNm", reqIdCardVO.getFileName2());
					param = holdportAuthDAO.procInsertIdAuthInfo(param);

					if((int) param.get("rtnCd") != 0) {
						throw new Exception("파일 저장 실패");
					}
				} else {
					throw new Exception("파일 저장 실패");
				}
			} else {
				throw new Exception("추가정보 저장 실패");
			}
			break;
		case 0:
			result.setRtnCd(-5025);
			result.setRtnMsg("등록된 정보와 일치하지 않습니다");
			break;
		default:
			result.setRtnCd(-5044);
			result.setRtnMsg("보안 등급이 3일 경우 인증 가능합니다.");
			break;
		}

		return result;
	}

	@Override
	public ResultVO uploadCorpIdCard(ReqIdCardVO reqIdCardVO) throws Exception {
		ResultVO result = new ResultVO();

		// 회원 정보 조회
		String userInfo = holdportAuthDAO.selectUserInfo(reqIdCardVO);

		reqIdCardVO.setRegDt(userInfo);

		// 인증 레벨 확인
		String level = holdportAuthDAO.selectAuthLevelCorp(reqIdCardVO);

		level = level == null ? "0":level;

		// 인증 레벨에 따른 분기 처리
		switch(Integer.parseInt(level)) {
		case 3:
			// 추가 정보 저장
			int cnt = holdportAuthDAO.updateLevel4Request(reqIdCardVO);

			// 신분증 파일 저장
			// PR_WAS_INSERT_ID_AUTH_INFO
			if(cnt == 1) {
				Map<String, Object> param = new HashMap<>();
				param.put("exchangeId", reqIdCardVO.getExchangeId());
				param.put("userId", reqIdCardVO.getUserId());
				param.put("fileNm", reqIdCardVO.getFileName1());

				param = holdportAuthDAO.procInsertIdAuthInfo(param);

				if((int) param.get("rtnCd") == 0) {
					param.put("fileNm", reqIdCardVO.getFileName2());
					param = holdportAuthDAO.procInsertIdAuthInfo(param);

					if((int) param.get("rtnCd") == 0) {
						// 사업자 등록증 저장
						param.put("corpRegNum", reqIdCardVO.getCorpNo());
						param.put("fileNm", reqIdCardVO.getFileName3());
						param = holdportAuthDAO.procInsertMemberCorpAuthInfo(param);

						if((int) param.get("rtnCd") != 0) {
							throw new Exception("사업증 등록증 저장 실패");
						}
					} else {
						throw new Exception("파일 저장 실패");
					}
				} else {
					throw new Exception("파일 저장 실패");
				}
			} else {
				throw new Exception("추가정보 저장 실패");
			}
			break;
		case 0:
			result.setRtnCd(-5043);
			result.setRtnMsg("존재하지 않는 회원입니다.");
			break;
		default:
			result.setRtnCd(-5044);
			result.setRtnMsg("보안 등급이 3일 경우 인증 가능합니다.");
			break;
		}

		return result;
	}

	@Override
	public ResultVO proveResidence(ReqAuthLevel5VO reqAuthLevel5VO) throws Exception {

		ResultVO resultVO = new ResultVO();

		// 주소 저장
		int cnt = holdportAuthDAO.updateExtraAddress(reqAuthLevel5VO);

		// 5레벨 인증 정보 저장
		if(cnt == 1) {
			reqAuthLevel5VO = holdportAuthDAO.procMemberLevel5Mgr(reqAuthLevel5VO);

			if(reqAuthLevel5VO.getRtnCd() != 0) {
				throw new Exception(reqAuthLevel5VO.getRtnMsg());
			}
		} else {
			throw new Exception("주소 저장에 실패하였습니다");
		}

		return resultVO;
	}

	@Override
	public AuthLevel5VO selectAuthLevel5Info(Map<String, Object> param) {
		return holdportAuthDAO.selectAuthLevel5Info(param);
	}
}
