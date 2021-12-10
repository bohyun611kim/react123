/*
* Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.mypage.mpg002.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.EncryptHelper;
import kr.co.coinis.webserver.common.utils.GoogleOTP;
import kr.co.coinis.webserver.common.utils.HttpSender;
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.service.HoldportAuthService;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.holdport.mypage.mpg002.vo.ReqIdCardVO;
import kr.co.coinis.webserver.holdport.wallet.wlt001.service.HoldportWalletService;
import kr.co.coinis.webserver.holdport.wallet.wlt001.vo.MemberInfoVO;


/**
 * <pre>
 * kr.co.coinis.webserver.holdport.mypage.mpg002.controller
 *    |_ HoldportAuthController.java
 *
 * </pre>
 * @date : 2019. 5. 8. 오전 10:35:01
 * @version :
 * @author : kangn
 */
@Controller
public class HoldportAuthController {

	private static final Logger logger = LoggerFactory.getLogger(HoldportAuthController.class);

	@Value("${attach.file.save.path}")
	private String path;

	@Autowired
	private HoldportAuthService holdportAuthService;

	@Autowired
	private HoldportWalletService holdportWalletService;

	@Autowired
	private CommService commService;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Resource
	private RedisSessionRepository redisSessionRepository;

	/**
	 *
	 * <pre>
	 * 1. 개요 : OTP 인증 설정을 위한 SMS 인증코드 요청
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : requestSmsAuthNumber
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/mypage/mpg002/requestSmsAuthNumber", method = RequestMethod.POST)
	public Map<String, Object> requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();

			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);

			// 인증코드 저장처리 프로시저 호출
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );

			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);				// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용

			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// 휴대폰 번호

			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3분간 유효
			paramMap.put("ARG_TRANSACTION_KEY_VAL", 0);

			Map<String, Object> insertAuthCodeMap = holdportWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);

			int rtnCd = Double.valueOf(CommonUtils.strNlv(insertAuthCodeMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				resultMap.clear();
				resultMap.put("rtnCd", rtnCd);
				resultMap.put("rtnMsg", insertAuthCodeMap.get("V_RTN_MSG"));
				return resultMap;
			}

			String strAuthCode = insertAuthCodeMap.get("V_AUTH_CODE").toString();

			logger.info("sms strAuthCode["+strAuthCode+"]");

			String smsMsg = String.format("[BITA500] OTP 등록 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지", strAuthCode);

			boolean sendFlag = HttpSender.httpSmsRequest(memberInfoVo.getMOBILE() , smsMsg);
			//sendFlag = true;

			ResultVO result= new ResultVO();

			if( sendFlag) {
				logger.info("Http Sms Send Success ["+strAuthCode+"]");
				result.setRtnCd(0);
				result.setRtnMsg("SMS 발신을 성공하였습니다.");

			}else {
				logger.info("Http Sms Send Fail ["+strAuthCode+"]");
				result.setRtnCd(-5503);
				result.setRtnMsg("SMS 발신에 실패하였습니다.");
			}

			resultMap.clear();
			resultMap.put("rtnCd", result.getRtnCd());
			resultMap.put("rtnMsg", result.getRtnMsg());


			return resultMap;


		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resultMap.clear();
			resultMap.put("rtnCd", -5009);
			resultMap.put("rtnMsg", CommonUtils.getPrintStackTrace(e));
			return resultMap;
		}
	}

	/**
	 *
	 * <pre>
	 * 1. 개요 : 사용자의 OTP 인증사용을 위해 OTP Key를 등록한다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : doOtpAuth
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param session
	 * @param strLoginPwd
	 * @param strSmsAuthCode
	 * @param strOtpAuthCode
	 * @param strOtpAuthKey
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/mypage/mpg002/requestOtpAuth", method = RequestMethod.POST)
	public Map<String, Object> doOtpAuth(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "loginPwd", defaultValue = "", required = true) String strLoginPwd
			, @RequestParam(value = "smsAuthCd", defaultValue = "", required = true) String strSmsAuthCode
			, @RequestParam(value = "otpAuthCd", defaultValue = "", required = true) String strOtpAuthCode
			, @RequestParam(value = "otpAuthKey", defaultValue = "", required = true) String strOtpAuthKey
			, @RequestParam(value = "os", defaultValue = "", required = true) String os
			, @RequestParam(value = "browser", defaultValue = "", required = true) String browser
			, @RequestParam(value = "privateIp", defaultValue = "", required = false) String privateIp
			) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();

		String strExchangeId = "";
		String strUserId = "";

		try {

			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			strExchangeId = CommonUtils.getNewExchangeId(request);

			strUserId = user.getUserId();

			logger.info("request strLoginPwd["+strLoginPwd+"],smsAuthCd["+strSmsAuthCode+"], strOtpAuthCode["+strOtpAuthCode+"], strOtpAuthKey["+strOtpAuthKey+"]");

			//  OTP 인증번호를 검증한다.
			boolean check_otp = GoogleOTP.checkCode(strOtpAuthCode, strOtpAuthKey);
			if(!check_otp) {
				resultMap.put("rtnCd", -5016);
				resultMap.put("rtnMsg", "OTP 인증번호가 일치하지 않습니다.");
				return resultMap;
			}

			// 회원 정보 체크
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = holdportWalletService.selectMemberInfo(paramMap);

			// 로그인 비밀번호 체크
			paramMap.put("LOGIN_PASSWD", strLoginPwd );
			boolean check_password = holdportAuthService.checkUserPassword(paramMap);
			if(!check_password) {
				resultMap.put("rtnCd", -5026);
				resultMap.put("rtnMsg", "로그인 비밀번호가 일치하지 않습니다");
				return resultMap;
			}

			// 인증코드 유효성을 검사한다.
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);										// 인증목적 식별코드	1: 회원가입, 2: 핸드폰 인증, 3: 출금인증, 4: 비밀번호 변경
			paramMap.put("ARG_AUTH_MEANS_CD", 2 ); 										// 인증수단 식별코드 1: OTP, 2: SMS, 3: Email, 0: 미사용
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );			// 휴대폰 번호
			paramMap.put("ARG_AUTH_CODE", strSmsAuthCode );


			// SMS 인증번호 체크로직
			Map<String, Object> checkAuthCodeMap = holdportWalletService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
				if(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() == 0) {

				//  OTP 인증코드 코드 등록
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_OTP_YN", 1 );
				paramMap.put("ARG_OTP_KEY_VAL", strOtpAuthKey );
				paramMap.put("ARG_KEY1", strUserId );
				paramMap.put("ARG_KEY2", memberInfoVo.getREG_DT() );

				Map<String, Object> setOtpKeyMap = holdportAuthService.call_PR_WAS_SET_OTP_AUTH(paramMap);
				if(Double.valueOf(setOtpKeyMap.get("V_RTN_CD").toString()).intValue() == 0) {
					paramMap.clear();
					paramMap.put("EXCHANGE_ID", strExchangeId);
					paramMap.put("USER_ID", strUserId );
					paramMap.put("AUTH_LEVEL", 3 );
					paramMap.put("AUTH_MEANS_CD", 1 );					// 인증수단식별코드 1: OTP, 2: SMS, 3: EMAIL, 4: none
					paramMap.put("OTP_YN", 1 );
					holdportAuthService.updateMemberInfoAtOtp(paramMap);

					resultMap.put("rtnCd", 0);
					resultMap.put("rtnMsg", "OTP 등록에 성공하였습니다.");

					user.setAuthMeansCd(1);
					user.setAuthLevel(3);
					session.setAttribute("userInfo", user);
					redisSessionRepository.save(user);
				} else {
					resultMap.put("rtnCd", -5027);
					resultMap.put("rtnMsg", "OTP KEY 등록에 실패하였습니다.");
				}
				return resultMap;
			} else {
				resultMap.clear();
				resultMap.put("rtnCd", checkAuthCodeMap.get("V_RTN_CD"));
				resultMap.put("rtnMsg", checkAuthCodeMap.get("V_RTN_MSG"));
				return resultMap;
			}

		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			// Return Error Message (stack trace)
			resultMap.clear();
			resultMap.put("rtnCd", -5027);
			resultMap.put("rtnMsg", CommonUtils.getPrintStackTrace(e));
			return resultMap;
		} finally {
			InsertHistoryVO insertHistoryVO = new InsertHistoryVO();
			insertHistoryVO.setExchangeId(strExchangeId);
			insertHistoryVO.setUserId(strUserId);
			insertHistoryVO.setReqOperCd(8);
			insertHistoryVO.setOs(os);
			insertHistoryVO.setPrivateIp(privateIp);
			insertHistoryVO.setPublicIp(CommonUtils.getClientIpAddr(request));
			insertHistoryVO.setBrowser(browser);
			insertHistoryVO.setResultCd((int)resultMap.get("rtnCd"));
			insertHistoryVO.setLogContents((String)resultMap.get("rtnMsg"));

			try {
				commService.procInsertUserRequestHist(insertHistoryVO);
			} catch (Exception e) {
				//logger.error("사용자 이력 저장 실패");
				//logger.error(e.getMessage());
			}
		}
	}

	@RequestMapping(value = "site/mypage/mpg002/submitIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitIdcard(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {

		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();

		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

			String exchangeId = CommonUtils.getNewExchangeId(request);
			String userId = user.getUserId();
			String timestamp = DateUtils.getCustomDateTime();
			String fileName = "";

			String userName = mhsr.getParameter("userName");
			String birthday = mhsr.getParameter("calendar");

			logger.info("request submitIdcard ID["+userId+"], userName["+userName+"],birthday["+birthday+"]");

			int idCardType = Integer.parseInt(mhsr.getParameter("radio"));

			// 4레벨 인증 요청 가능 여부 정보 조회
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);
			AuthLevel4VO authLevel4VO = holdportAuthService.selectAuthLevel4Info(param);

			if(authLevel4VO.getIdVerifiResultCd().isEmpty() || authLevel4VO.getIdVerifiResultCd() == null || Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == -1) {
				if(StringUtils.isEmpty(userName)) {
					result.setRtnCd(-5028);
					result.setRtnMsg("성명 미입력");
				} else if(StringUtils.isEmpty(birthday)) {
					result.setRtnCd(-5029);
					result.setRtnMsg("생년월일 미입력");
				} else if(birthday.length() != 8 || !StringUtils.checkNumberOnly(birthday)) {
					result.setRtnCd(-5030);
					result.setRtnMsg("잘못된 생년월일");
				} else if(idCardType < 1 || idCardType > 3) {
					result.setRtnCd(-5033);
					result.setRtnMsg("잘못된 신분증 종류");
				} else {
					// 파일 업로드 성공 시 제출자료 등록
					reqIdCardVO.setExchangeId(exchangeId);
					reqIdCardVO.setUserId(userId);
					reqIdCardVO.setUserName(userName);
					reqIdCardVO.setBirthday(birthday);
					reqIdCardVO.setIdCardType(idCardType);

					// 파일 업로드
					List<MultipartFile> files =  mhsr.getFiles("file");

					logger.info("attach file size["+files.size()+"]");

					if(files.size() == 2) {
						boolean isNext = true;

						// 확장자 검사
						for(int i=0; i<files.size(); i++) {
							String originalfileName = files.get(i).getOriginalFilename();

							if(!originalfileName.toLowerCase().endsWith("jpg")
									&& !originalfileName.toLowerCase().endsWith("png")) {
								isNext = false;
								result.setRtnCd(-5034);
								result.setRtnMsg("jpg, png 파일만 가능합니다");
								break;
							}

							if(files.get(i).getSize() > (6 * 1024 * 1024)) {
								isNext = false;
								result.setRtnCd(-5035);
								result.setRtnMsg("최대 파일 크기는 6MB 입니다");
								break;
							}
						}

						String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";

						// 파일 업로드
						if(isNext) {
							for(int i=0; i<files.size(); i++) {
								genId = UUID.randomUUID().toString();
								origin = files.get(i).getOriginalFilename();
								saveFileName = genId + "." + origin;
								savePath = path + saveFileName;
								newFileName = exchangeId + "_" + userId + "_" + timestamp + "_" + (i+1) + origin.substring(origin.lastIndexOf("."));

								files.get(i).transferTo(new File(savePath));

								moveAttachFile2Encrypt(savePath, path + newFileName);
							}

							fileName = files.get(0).getOriginalFilename();
							reqIdCardVO.setFileName1(exchangeId + "_" + userId + "_" + timestamp + "_1" + fileName.substring(fileName.lastIndexOf(".")));

							fileName = files.get(1).getOriginalFilename();
							reqIdCardVO.setFileName2(exchangeId + "_" + userId + "_" + timestamp + "_2" + fileName.substring(fileName.lastIndexOf(".")));

							result = holdportAuthService.uploadIdCard(reqIdCardVO);

							if(result.getRtnCd() != 0) {
								// 업로드 파일 삭제
								if(reqIdCardVO.getFileName1() != null) {
									File file = new File(path + reqIdCardVO.getFileName1());
									if(file.isFile()) file.delete();
								}

								if(reqIdCardVO.getFileName2() != null) {
									File file = new File(path + reqIdCardVO.getFileName2());
									if(file.isFile()) file.delete();
								}
							}
						}
					} else {
						result.setRtnCd(-5036);
						result.setRtnMsg("인증파일 갯수가 모자랍니다");
					}
				}
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 0) {
				result.setRtnCd(-5071);
				result.setRtnMsg("이미 신분증 인증이 완료 되었습니다");
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 1) {
				result.setRtnCd(-5072);
				result.setRtnMsg("신분증 인증이 진행중 입니다");
			} else {
				result.setRtnCd(-5073);
				result.setRtnMsg("보안인증 4레벨 인증 대상이 아닙니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5037);
			result.setRtnMsg("신분증 제출에 실패하였습니다");

			if(reqIdCardVO.getFileName1() != null) {
				File file = new File(path + reqIdCardVO.getFileName1());
				if(file.isFile()) file.delete();
			}

			if(reqIdCardVO.getFileName2() != null) {
				File file = new File(path + reqIdCardVO.getFileName2());
				if(file.isFile()) file.delete();
			}
		}

		return result;
	}

	@RequestMapping(value = "site/mypage/mpg002/submitCorpIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitCorpIdcard(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {

		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();

		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());

			String exchangeId = CommonUtils.getNewExchangeId(request);
			String userId = user.getUserId();
			String timestamp = DateUtils.getCustomDateTime();
			String fileName = "";

			String corpName = mhsr.getParameter("corpName");
			String representName = mhsr.getParameter("representName");
			String corpNo = mhsr.getParameter("corpNo");
			String address = mhsr.getParameter("address");
			String postal = mhsr.getParameter("postal");
			String userName = mhsr.getParameter("userName");
			int idCardType = Integer.parseInt(mhsr.getParameter("radio"));

			// 4레벨 인증 요청 가능 여부 정보 조회
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);
			AuthLevel4VO authLevel4VO = holdportAuthService.selectAuthLevel4Info(param);

			if(authLevel4VO.getIdVerifiResultCd() == null || Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == -1) {
				if(StringUtils.isEmpty(corpName)) {
					result.setRtnCd(-5038);
					result.setRtnMsg("회사명 미입력");
				} else if(StringUtils.isEmpty(representName)) {
					result.setRtnCd(-5039);
					result.setRtnMsg("대표자 성명 미입력");
				} else if(StringUtils.isEmpty(corpNo)) {
					result.setRtnCd(-5040);
					result.setRtnMsg("사업자 등록번호 미입력");
				}
				/*else if(StringUtils.isEmpty(address)) {
					result.setRtnCd(-5041);
					result.setRtnMsg("회사 주소 미입력");
				} else if(StringUtils.isEmpty(postal)) {
					result.setRtnCd(-5032);
					result.setRtnMsg("우편번호 미입력");
				}*/
				else if(StringUtils.isEmpty(userName)) {
					result.setRtnCd(-5042);
					result.setRtnMsg("담당자명 미입력");
				} else if(idCardType < 1 || idCardType > 3) {
					result.setRtnCd(-5033);
					result.setRtnMsg("잘못된 신분증 종류");
				} else {
					// 파일 업로드 성공 시 제출자료 등록
					reqIdCardVO.setExchangeId(exchangeId);
					reqIdCardVO.setUserId(userId);
					reqIdCardVO.setCorpName(corpName);
					reqIdCardVO.setRepresentName(representName);
					reqIdCardVO.setCorpNo(corpNo);
					reqIdCardVO.setAddress(address);
					reqIdCardVO.setPostal(postal);
					reqIdCardVO.setUserName(userName);
					reqIdCardVO.setIdCardType(idCardType);

					// 파일 업로드
					List<MultipartFile> files =  mhsr.getFiles("file");

					if(files.size() == 3) {
						boolean isNext = true;

						// 확장자 검사
						for(int i=0; i<files.size(); i++) {
							String originalfileName = files.get(i).getOriginalFilename();

							if(!originalfileName.toLowerCase().endsWith("jpg")
									&& !originalfileName.toLowerCase().endsWith("png")) {
								isNext = false;
								result.setRtnCd(-5034);
								result.setRtnMsg("jpg, png 파일만 가능합니다");
								break;
							}

							if(files.get(i).getSize() > (6 * 1024 * 1024)) {
								isNext = false;
								result.setRtnCd(-5035);
								result.setRtnMsg("최대 파일 크기는 6MB 입니다");
								break;
							}
						}

						String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";

						// 파일 업로드
						if(isNext) {
							for(int i=0; i<files.size(); i++) {
								genId = UUID.randomUUID().toString();
								origin = files.get(i).getOriginalFilename();
								saveFileName = genId + "." + origin;
								savePath = path + saveFileName;
								newFileName = exchangeId + "_" + userId + "_" + timestamp + "_" + (i+1) + origin.substring(origin.lastIndexOf("."));

								files.get(i).transferTo(new File(savePath));

								moveAttachFile2Encrypt(savePath, path + newFileName);
							}

							fileName = files.get(0).getOriginalFilename();
							reqIdCardVO.setFileName1(exchangeId + "_" + userId + "_" + timestamp + "_1" + fileName.substring(fileName.lastIndexOf(".")));

							fileName = files.get(1).getOriginalFilename();
							reqIdCardVO.setFileName2(exchangeId + "_" + userId + "_" + timestamp + "_2" + fileName.substring(fileName.lastIndexOf(".")));

							fileName = files.get(2).getOriginalFilename();
							reqIdCardVO.setFileName3(exchangeId + "_" + userId + "_" + timestamp + "_3" + fileName.substring(fileName.lastIndexOf(".")));

							result = holdportAuthService.uploadCorpIdCard(reqIdCardVO);

							if(result.getRtnCd() != 0) {
								// 업로드 파일 삭제
								if(reqIdCardVO.getFileName1() != null) {
									File file = new File(path + reqIdCardVO.getFileName1());
									if(file.isFile()) file.delete();
								}

								if(reqIdCardVO.getFileName2() != null) {
									File file = new File(path + reqIdCardVO.getFileName2());
									if(file.isFile()) file.delete();
								}

								if(reqIdCardVO.getFileName3() != null) {
									File file = new File(path + reqIdCardVO.getFileName3());
									if(file.isFile()) file.delete();
								}
							}
						}
					} else {
						result.setRtnCd(-5036);
						result.setRtnMsg("인증파일 갯수가 모자랍니다");
					}
				}
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 0) {
				result.setRtnCd(-5071);
				result.setRtnMsg("이미 신분증 인증이 완료 되었습니다");
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 1) {
				result.setRtnCd(-5072);
				result.setRtnMsg("신분증 인증이 진행중 입니다");
			} else {
				result.setRtnCd(-5073);
				result.setRtnMsg("보안인증 4레벨 인증 대상이 아닙니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5037);
			result.setRtnMsg("신분증 인증 실패");

			if(reqIdCardVO.getFileName1() != null) {
				File file = new File(path + reqIdCardVO.getFileName1());
				if(file.isFile()) file.delete();
			}

			if(reqIdCardVO.getFileName2() != null) {
				File file = new File(path + reqIdCardVO.getFileName2());
				if(file.isFile()) file.delete();
			}

			if(reqIdCardVO.getFileName3() != null) {
				File file = new File(path + reqIdCardVO.getFileName3());
				if(file.isFile()) file.delete();
			}
		}

		return result;
	}

	@RequestMapping(value = "site/mypage/mpg002/submitProveResidence", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO proveResidence(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {

		ResultVO result = new ResultVO();
		ReqAuthLevel5VO reqAuthLevel5VO = new ReqAuthLevel5VO();

		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

			String exchangeId = CommonUtils.getNewExchangeId(request);
			String userId = user.getUserId();

			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);

			AuthLevel5VO authLevel5VO = holdportAuthService.selectAuthLevel5Info(param);

			if(authLevel5VO == null || Integer.parseInt(authLevel5VO.getApprovalStatCd()) == -1) {
				String timestamp = DateUtils.getCustomDateTime();
				String fileName = "";

				String addr = mhsr.getParameter("addr");
				String type = mhsr.getParameter("type");
				String purpose = mhsr.getParameter("purpose");
				String origin = mhsr.getParameter("origin");
				String tradeCnt = mhsr.getParameter("tradeCnt");
				String tradeAmt = mhsr.getParameter("tradeAmt");

				if(StringUtils.isEmpty(addr)) {
					result.setRtnCd(-5031);
					result.setRtnMsg("주소를 입력하지 않았습니다");
				} else if(StringUtils.isEmpty(type)) {
					result.setRtnCd(-5074);
					result.setRtnMsg("직업을 선택하세요");
				} else if(StringUtils.isEmpty(purpose)) {
					result.setRtnCd(-5075);
					result.setRtnMsg("거래의 목적을 입력하세요");
				} else if(StringUtils.isEmpty(origin)) {
					result.setRtnCd(-5076);
					result.setRtnMsg("자금의 원천 및 출처를 입력하세요");
				} else if(StringUtils.isEmpty(tradeCnt)) {
					result.setRtnCd(-5077);
					result.setRtnMsg("월간 예상 거래 횟수를 입력하세요");
				} else if(StringUtils.isEmpty(tradeAmt)) {
					result.setRtnCd(-5078);
					result.setRtnMsg("월간 예상 거래 금액(원화)를 입력하세요");
				} else {
					if(authLevel5VO == null) {
						reqAuthLevel5VO.setProcFlag("C");
					} else {
						reqAuthLevel5VO.setProcFlag("M");
						reqAuthLevel5VO.setRecSeqNo(authLevel5VO.getRecSeqNo());
						reqAuthLevel5VO.setApprovalStatCd(0);
					}

					// 파일 업로드 성공 시 제출자료 등록
					reqAuthLevel5VO.setExchangeId(exchangeId);
					reqAuthLevel5VO.setUserId(userId);
					reqAuthLevel5VO.setAddr(addr);
					reqAuthLevel5VO.setJobCd(type);
					reqAuthLevel5VO.setPurpose(purpose);
					reqAuthLevel5VO.setOrigin(origin);
					reqAuthLevel5VO.setTradeCnt(tradeCnt);
					reqAuthLevel5VO.setTradeAmt(tradeAmt);

					// 파일 업로드
					MultipartFile multipartFile =  mhsr.getFile("imgInput5");
					MultipartFile multipartFile2 =  mhsr.getFile("imgInput6");

					if(multipartFile != null && multipartFile2 != null) {
						boolean isNext = true;

						// 확장자 검사
						String originalfileName = multipartFile.getOriginalFilename();
						String originalfileName2 = multipartFile2.getOriginalFilename();
						if(!originalfileName.toLowerCase().endsWith("jpg")
								&& !originalfileName.toLowerCase().endsWith("png")
								&& !originalfileName2.toLowerCase().endsWith("jpg")
								&& !originalfileName2.toLowerCase().endsWith("png")) {
							isNext = false;
							result.setRtnCd(-5034);
							result.setRtnMsg("jpg, png 파일만 가능합니다");
						}

						// 파일 크기 검사
						if(multipartFile.getSize() > (6 * 1024 * 1024) || multipartFile2.getSize() > (6 * 1024 * 1024)) {
							isNext = false;
							result.setRtnCd(-5035);
							result.setRtnMsg("최대 파일 크기는 6MB 입니다");
						}

						String genId = "", originFileNm = "", saveFileName = "", savePath = "", newFileName = "";

						// 파일 업로드
						if(isNext) {
							genId = UUID.randomUUID().toString();
							// file 1
							originFileNm = multipartFile.getOriginalFilename();
							saveFileName = genId + "." + originFileNm;
							savePath = path + saveFileName;
							newFileName = exchangeId + "_" + userId + "_1_" + timestamp + originFileNm.substring(originFileNm.lastIndexOf("."));

							multipartFile.transferTo(new File(savePath));
							moveAttachFile2Encrypt(savePath, path + newFileName);

							fileName = multipartFile.getOriginalFilename();
							reqAuthLevel5VO.setFileNm(exchangeId + "_" + userId + "_1_" + timestamp + fileName.substring(fileName.lastIndexOf(".")));

							// file 2
							originFileNm = multipartFile2.getOriginalFilename();
							saveFileName = genId + "." + originFileNm;
							savePath = path + saveFileName;
							newFileName = exchangeId + "_" + userId + "_2_" + timestamp + originFileNm.substring(originFileNm.lastIndexOf("."));

							multipartFile2.transferTo(new File(savePath));
							moveAttachFile2Encrypt(savePath, path + newFileName);

							fileName = multipartFile2.getOriginalFilename();
							reqAuthLevel5VO.setFileNm2(exchangeId + "_" + userId + "_2_" + timestamp + fileName.substring(fileName.lastIndexOf(".")));

							// 5레벨 인증 정보 저장
							result = holdportAuthService.proveResidence(reqAuthLevel5VO);

							// 인증 정보 저장 실패 시 파일 샂게
							if(result.getRtnCd() != 0) {
								// 업로드 파일 삭제
								if(reqAuthLevel5VO.getFileNm() != null) {
									File file = new File(path + reqAuthLevel5VO.getFileNm());
									if(file.isFile()) file.delete();
								}

								if(reqAuthLevel5VO.getFileNm2() != null) {
									File file2 = new File(path + reqAuthLevel5VO.getFileNm2());
									if(file2.isFile()) file2.delete();
								}
							}
						}
					} else {
						result.setRtnCd(-5036);
						result.setRtnMsg("인증파일 갯수가 모자랍니다");
					}
				}
			} else if(Integer.parseInt(authLevel5VO.getApprovalStatCd()) == 0) {
				result.setRtnCd(-5079);
				result.setRtnMsg("거주지 인증이 진행중 입니다");
			} else if(Integer.parseInt(authLevel5VO.getApprovalStatCd()) == 1) {
				result.setRtnCd(-5080);
				result.setRtnMsg("이미 거주지 인증이 완료 되었습니다");
			} else {
				result.setRtnCd(-5081);
				result.setRtnMsg("보안인증 5레벨 인증 대상이 아닙니다");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5093);
			result.setRtnMsg("거주지 인증에 실패하였습니다");

			if(reqAuthLevel5VO.getFileNm() != null) {
				File file = new File(path + reqAuthLevel5VO.getFileNm());
				if(file.isFile()) file.delete();
			}

			if(reqAuthLevel5VO.getFileNm2() != null) {
				File file2 = new File(path + reqAuthLevel5VO.getFileNm2());
				if(file2.isFile()) file2.delete();
			}
		}

		return result;
	}

	@SuppressWarnings("static-access")
	private void moveAttachFile2Encrypt(String original_FilePathName, String new_FilePathName) throws Exception {
		try {
			File oldFile = new File(original_FilePathName);
			File newFile = new File(new_FilePathName);

			int read = 0;
			byte[] buf = new byte[1024];
			FileInputStream fin = new FileInputStream(oldFile);
			FileOutputStream fout = new FileOutputStream(newFile);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((read = fin.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, read);
			}

			byte[] enc = EncryptHelper.getInstance().encrypt(baos.toByteArray(), null);
			fout.write(enc);
			fout.flush();
			fin.close();
			fout.close();
			baos.close();

			try {
				// 원본파일 삭제실패시 다시한번 삭제 시도
				if (!oldFile.delete())
					oldFile.delete();
			} catch (Exception e) {
			}

		} catch (Exception e) {
			throw e;
		}
	}
}
