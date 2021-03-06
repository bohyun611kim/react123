/*
* Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.mypage.mpg002.controller;

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
import kr.co.coinis.webserver.common.utils.StringUtils;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.service.YahobitAuthService;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.AuthLevel4VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.AuthLevel5VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqAuthLevel5VO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.service.YahobitWalletService;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.mypage.mpg002.controller 
 *    |_ YahobitAuthController.java
 * 
 * </pre>
 * @date : 2019. 5. 8. ?????? 10:35:01
 * @version : 
 * @author : kangn
 */
@Controller
public class YahobitAuthController {

	private static final Logger logger = LoggerFactory.getLogger(YahobitAuthController.class);
	
	@Value("${attach.file.save.path}")
	private String path;

	@Autowired
	private YahobitAuthService yahobitAuthService;
	
	@Autowired
	private YahobitWalletService yahobitWalletService;
	
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
	 * 1. ?????? : OTP ?????? ????????? ?????? SMS ???????????? ??????
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : requestSmsAuthNumber
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				?????? ?????? 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alibit/mypage/mpg002/requestSmsAuthNumber", method = RequestMethod.POST)
	public Map<String, Object> requestSmsAuthNumber(HttpServletRequest request, HttpSession session
			) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getExchangeId(request);
			String strUserId = user.getUserId();
			
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);

			// ???????????? ???????????? ???????????? ??????
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId );
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);				// ???????????? ????????????	1: ????????????, 2: ????????? ??????, 3: ????????????, 4: ???????????? ??????
			paramMap.put("ARG_AUTH_MEANS_CD", 2 );				// ???????????? ???????????? 1: OTP, 2: SMS, 3: Email, 0: ?????????
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );	// ????????? ??????
			paramMap.put("ARG_EXPIRE_SEC", 3 * 60 );		// 3?????? ??????
			paramMap.put("ARG_TRANSACTION_KEY_VAL", 0);
			Map<String, Object> insertAuthCodeMap = yahobitWalletService.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
			int rtnCd = Double.valueOf(CommonUtils.strNlv(insertAuthCodeMap.get("V_RTN_CD"), "-1")).intValue();
			if(rtnCd != 0) {
				resultMap.clear();
				resultMap.put("rtnCd", rtnCd);
				resultMap.put("rtnMsg", insertAuthCodeMap.get("V_RTN_MSG"));
				return resultMap;
			}

			String strAuthCode = insertAuthCodeMap.get("V_AUTH_CODE").toString();
			SendSmsVO sendSmsVo = new SendSmsVO();
			sendSmsVo.setExchangeId(strExchangeId);
			sendSmsVo.setUserId(strUserId);
			sendSmsVo.setTo(memberInfoVo.getMOBILE());
			String smsMsg = String.format("[ALIBIT] OTP ?????? ???????????? [%s] (????????????????????????) ???????????? ????????????", strAuthCode);
			sendSmsVo.setMsg(smsMsg);
			sendSmsVo.setMsgOption("sms");
			
			ResultVO resultVo = commService.sendSms(sendSmsVo);
			
			resultMap.clear();
			resultMap.put("rtnCd", resultVo.getRtnCd());
			resultMap.put("rtnMsg", resultVo.getRtnMsg());
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
	 * 1. ?????? : ???????????? OTP ??????????????? ?????? OTP Key??? ????????????.
	 * 2. ???????????? : 
	 * </pre>
	 * @Method Name : doOtpAuth
	 * @date : 2019. 5. 8.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	?????????				?????????						????????????  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 8.		kangn				?????? ?????? 
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
	@RequestMapping(value = "alibit/mypage/mpg002/requestOtpAuth", method = RequestMethod.POST)
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
			strExchangeId = CommonUtils.getExchangeId(request);
			strUserId = user.getUserId();
			
			// ?????? OTP ??????????????? ????????????.
			boolean check_otp = GoogleOTP.checkCode(strOtpAuthCode, strOtpAuthKey);
			if(!check_otp) {
				resultMap.put("rtnCd", -5016);
				resultMap.put("rtnMsg", "OTP ??????????????? ???????????? ????????????.");
				return resultMap;
			}
			
			// ?????? ?????? ??????
			paramMap.clear();
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			MemberInfoVO memberInfoVo = yahobitWalletService.selectMemberInfo(paramMap);
			
			// ????????? ???????????? ??????
			paramMap.put("LOGIN_PASSWD", strLoginPwd );
			boolean check_password = yahobitAuthService.checkUserPassword(paramMap);
			if(!check_password) {
				resultMap.put("rtnCd", -5026);
				resultMap.put("rtnMsg", "????????? ??????????????? ???????????? ????????????");
				return resultMap;
			}
			
			// ???????????? ???????????? ????????????.
			paramMap.clear();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_AUTH_PURPOSE_CD", 2);										// ???????????? ????????????	1: ????????????, 2: ????????? ??????, 3: ????????????, 4: ???????????? ??????
			paramMap.put("ARG_AUTH_MEANS_CD", 2 ); 										// ???????????? ???????????? 1: OTP, 2: SMS, 3: Email, 0: ?????????
			paramMap.put("ARG_AUTH_MEANS_KEY_VAL", memberInfoVo.getMOBILE() );			// ????????? ??????
			paramMap.put("ARG_AUTH_CODE", strSmsAuthCode );
			Map<String, Object> checkAuthCodeMap = yahobitWalletService.call_PR_WAS_CHECK_AUTH_CODE(paramMap);

			if(Double.valueOf(checkAuthCodeMap.get("V_RTN_CD").toString()).intValue() == 0) {
				//  OTP ???????????? ?????? ??????
				paramMap.clear();
				paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
				paramMap.put("ARG_USER_ID", strUserId );
				paramMap.put("ARG_OTP_YN", 1 );
				paramMap.put("ARG_OTP_KEY_VAL", strOtpAuthKey );
				paramMap.put("ARG_KEY1", strUserId );
				paramMap.put("ARG_KEY2", memberInfoVo.getREG_DT() );
				
				Map<String, Object> setOtpKeyMap = yahobitAuthService.call_PR_WAS_SET_OTP_AUTH(paramMap);
				if(Double.valueOf(setOtpKeyMap.get("V_RTN_CD").toString()).intValue() == 0) {
					paramMap.clear();
					paramMap.put("EXCHANGE_ID", strExchangeId);
					paramMap.put("USER_ID", strUserId );
					paramMap.put("AUTH_LEVEL", 3 );
					paramMap.put("AUTH_MEANS_CD", 1 );					// ???????????????????????? 1: OTP, 2: SMS, 3: EMAIL, 4: none
					paramMap.put("OTP_YN", 1 );
					yahobitAuthService.updateMemberInfoAtOtp(paramMap);

					resultMap.put("rtnCd", 0);
					resultMap.put("rtnMsg", "OTP ????????? ?????????????????????.");
					
					user.setAuthMeansCd(1);
					user.setAuthLevel(3);
					session.setAttribute("userInfo", user);
					redisSessionRepository.save(user);
				} else {
					resultMap.put("rtnCd", -5027);
					resultMap.put("rtnMsg", "OTP KEY ????????? ?????????????????????.");
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
				//logger.error("????????? ?????? ?????? ??????");
				//logger.error(e.getMessage());
			}
		}
	}
	
	@RequestMapping(value = "alibit/mypage/mpg002/submitIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitIdcard(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			String exchangeId = CommonUtils.getExchangeId(request);
			String userId = user.getUserId();
			String timestamp = DateUtils.getCustomDateTime();
			String fileName = "";
			
			String userName = mhsr.getParameter("userName");
			String birthday = mhsr.getParameter("calendar");
			int idCardType = Integer.parseInt(mhsr.getParameter("radio"));
			
			// 4?????? ?????? ?????? ?????? ?????? ?????? ??????
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);
			AuthLevel4VO authLevel4VO = yahobitAuthService.selectAuthLevel4Info(param);
			
			if(authLevel4VO.getIdVerifiResultCd().isEmpty() || authLevel4VO.getIdVerifiResultCd() == null || Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == -1) {
				if(StringUtils.isEmpty(userName)) {
					result.setRtnCd(-5028);
					result.setRtnMsg("?????? ?????????");
				} else if(StringUtils.isEmpty(birthday)) {
					result.setRtnCd(-5029);
					result.setRtnMsg("???????????? ?????????");
				} else if(birthday.length() != 8 || !StringUtils.checkNumberOnly(birthday)) {
					result.setRtnCd(-5030);
					result.setRtnMsg("????????? ????????????");
				} else if(idCardType < 1 || idCardType > 3) {
					result.setRtnCd(-5033);
					result.setRtnMsg("????????? ????????? ??????");
				} else {
					// ?????? ????????? ?????? ??? ???????????? ??????
					reqIdCardVO.setExchangeId(exchangeId);
					reqIdCardVO.setUserId(userId);
					reqIdCardVO.setUserName(userName);
					reqIdCardVO.setBirthday(birthday);
					reqIdCardVO.setIdCardType(idCardType);
					
					// ?????? ????????? 
					List<MultipartFile> files =  mhsr.getFiles("file");
					
					if(files.size() == 2) {
						boolean isNext = true;
						
						// ????????? ??????
						for(int i=0; i<files.size(); i++) {
							String originalfileName = files.get(i).getOriginalFilename();
							
							if(!originalfileName.toLowerCase().endsWith("jpg") 
									&& !originalfileName.toLowerCase().endsWith("png")) {
								isNext = false;
								result.setRtnCd(-5034);
								result.setRtnMsg("jpg, png ????????? ???????????????");
								break;
							}
							
							if(files.get(i).getSize() > (6 * 1024 * 1024)) {
								isNext = false;
								result.setRtnCd(-5035);
								result.setRtnMsg("?????? ?????? ????????? 6MB ?????????");
								break;
							}
						}
						
						String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";
						
						// ?????? ????????? 
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
							
							result = yahobitAuthService.uploadIdCard(reqIdCardVO);
							
							if(result.getRtnCd() != 0) {
								// ????????? ?????? ??????
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
						result.setRtnMsg("???????????? ????????? ???????????????");
					}
				}
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 0) {
				result.setRtnCd(-5071);
				result.setRtnMsg("?????? ????????? ????????? ?????? ???????????????");
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 1) {
				result.setRtnCd(-5072);
				result.setRtnMsg("????????? ????????? ????????? ?????????");
			} else {
				result.setRtnCd(-5073);
				result.setRtnMsg("???????????? 4?????? ?????? ????????? ????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5037);
			result.setRtnMsg("????????? ????????? ?????????????????????");
			
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
	
	@RequestMapping(value = "alibit/mypage/mpg002/submitCorpIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitCorpIdcard(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			String exchangeId = CommonUtils.getExchangeId(request);
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
			
			// 4?????? ?????? ?????? ?????? ?????? ?????? ??????
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);
			AuthLevel4VO authLevel4VO = yahobitAuthService.selectAuthLevel4Info(param);
			
			if(authLevel4VO.getIdVerifiResultCd() == null || Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == -1) {
				if(StringUtils.isEmpty(corpName)) {
					result.setRtnCd(-5038);
					result.setRtnMsg("????????? ?????????");
				} else if(StringUtils.isEmpty(representName)) {
					result.setRtnCd(-5039);
					result.setRtnMsg("????????? ?????? ?????????");
				} else if(StringUtils.isEmpty(corpNo)) {
					result.setRtnCd(-5040);
					result.setRtnMsg("????????? ???????????? ?????????");
				}
				/*else if(StringUtils.isEmpty(address)) {
					result.setRtnCd(-5041);
					result.setRtnMsg("?????? ?????? ?????????");
				} else if(StringUtils.isEmpty(postal)) {
					result.setRtnCd(-5032);
					result.setRtnMsg("???????????? ?????????");
				}*/
				else if(StringUtils.isEmpty(userName)) {
					result.setRtnCd(-5042);
					result.setRtnMsg("???????????? ?????????");
				} else if(idCardType < 1 || idCardType > 3) {
					result.setRtnCd(-5033);
					result.setRtnMsg("????????? ????????? ??????");
				} else {
					// ?????? ????????? ?????? ??? ???????????? ??????
					reqIdCardVO.setExchangeId(exchangeId);
					reqIdCardVO.setUserId(userId);
					reqIdCardVO.setCorpName(corpName);
					reqIdCardVO.setRepresentName(representName);
					reqIdCardVO.setCorpNo(corpNo);
					reqIdCardVO.setAddress(address);
					reqIdCardVO.setPostal(postal);
					reqIdCardVO.setUserName(userName);
					reqIdCardVO.setIdCardType(idCardType);
					
					// ?????? ????????? 
					List<MultipartFile> files =  mhsr.getFiles("file");
					
					if(files.size() == 3) {
						boolean isNext = true;
						
						// ????????? ??????
						for(int i=0; i<files.size(); i++) {
							String originalfileName = files.get(i).getOriginalFilename();
							
							if(!originalfileName.toLowerCase().endsWith("jpg") 
									&& !originalfileName.toLowerCase().endsWith("png")) {
								isNext = false;
								result.setRtnCd(-5034);
								result.setRtnMsg("jpg, png ????????? ???????????????");
								break;
							}
							
							if(files.get(i).getSize() > (6 * 1024 * 1024)) {
								isNext = false;
								result.setRtnCd(-5035);
								result.setRtnMsg("?????? ?????? ????????? 6MB ?????????");
								break;
							}
						}
						
						String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";
						
						// ?????? ????????? 
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
							
							result = yahobitAuthService.uploadCorpIdCard(reqIdCardVO);
							
							if(result.getRtnCd() != 0) {
								// ????????? ?????? ??????
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
						result.setRtnMsg("???????????? ????????? ???????????????");
					}
				}
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 0) {
				result.setRtnCd(-5071);
				result.setRtnMsg("?????? ????????? ????????? ?????? ???????????????");
			} else if(Integer.parseInt(authLevel4VO.getIdVerifiResultCd()) == 1) {
				result.setRtnCd(-5072);
				result.setRtnMsg("????????? ????????? ????????? ?????????");
			} else {
				result.setRtnCd(-5073);
				result.setRtnMsg("???????????? 4?????? ?????? ????????? ????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5037);
			result.setRtnMsg("????????? ?????? ??????");
			
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
	
	@RequestMapping(value = "alibit/mypage/mpg002/submitProveResidence", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO proveResidence(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		ReqAuthLevel5VO reqAuthLevel5VO = new ReqAuthLevel5VO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			String exchangeId = CommonUtils.getExchangeId(request);
			String userId = user.getUserId();
			
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", exchangeId);
			param.put("userId", userId);
			
			AuthLevel5VO authLevel5VO = yahobitAuthService.selectAuthLevel5Info(param);
			
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
					result.setRtnMsg("????????? ???????????? ???????????????");
				} else if(StringUtils.isEmpty(type)) {
					result.setRtnCd(-5074);
					result.setRtnMsg("????????? ???????????????");
				} else if(StringUtils.isEmpty(purpose)) {
					result.setRtnCd(-5075);
					result.setRtnMsg("????????? ????????? ???????????????");
				} else if(StringUtils.isEmpty(origin)) {
					result.setRtnCd(-5076);
					result.setRtnMsg("????????? ?????? ??? ????????? ???????????????");
				} else if(StringUtils.isEmpty(tradeCnt)) {
					result.setRtnCd(-5077);
					result.setRtnMsg("?????? ?????? ?????? ????????? ???????????????");
				} else if(StringUtils.isEmpty(tradeAmt)) {
					result.setRtnCd(-5078);
					result.setRtnMsg("?????? ?????? ?????? ??????(??????)??? ???????????????");
				} else {
					if(authLevel5VO == null) {
						reqAuthLevel5VO.setProcFlag("C");
					} else {
						reqAuthLevel5VO.setProcFlag("M");
						reqAuthLevel5VO.setRecSeqNo(authLevel5VO.getRecSeqNo());
						reqAuthLevel5VO.setApprovalStatCd(0);
					}
					
					// ?????? ????????? ?????? ??? ???????????? ??????
					reqAuthLevel5VO.setExchangeId(exchangeId);
					reqAuthLevel5VO.setUserId(userId);
					reqAuthLevel5VO.setAddr(addr);
					reqAuthLevel5VO.setJobCd(type);
					reqAuthLevel5VO.setPurpose(purpose);
					reqAuthLevel5VO.setOrigin(origin);
					reqAuthLevel5VO.setTradeCnt(tradeCnt);
					reqAuthLevel5VO.setTradeAmt(tradeAmt);
					
					// ?????? ????????? 
					MultipartFile multipartFile =  mhsr.getFile("imgInput5");
					MultipartFile multipartFile2 =  mhsr.getFile("imgInput6");
					
					if(multipartFile != null && multipartFile2 != null) {
						boolean isNext = true;
						
						// ????????? ??????
						String originalfileName = multipartFile.getOriginalFilename();
						String originalfileName2 = multipartFile2.getOriginalFilename();
						if(!originalfileName.toLowerCase().endsWith("jpg") 
								&& !originalfileName.toLowerCase().endsWith("png")
								&& !originalfileName2.toLowerCase().endsWith("jpg") 
								&& !originalfileName2.toLowerCase().endsWith("png")) {
							isNext = false;
							result.setRtnCd(-5034);
							result.setRtnMsg("jpg, png ????????? ???????????????");
						}
						
						// ?????? ?????? ??????
						if(multipartFile.getSize() > (6 * 1024 * 1024) || multipartFile2.getSize() > (6 * 1024 * 1024)) {
							isNext = false;
							result.setRtnCd(-5035);
							result.setRtnMsg("?????? ?????? ????????? 6MB ?????????");
						}
						
						String genId = "", originFileNm = "", saveFileName = "", savePath = "", newFileName = "";
						
						// ?????? ????????? 
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
							
							// 5?????? ?????? ?????? ??????
							result = yahobitAuthService.proveResidence(reqAuthLevel5VO);
							
							// ?????? ?????? ?????? ?????? ??? ?????? ??????
							if(result.getRtnCd() != 0) {
								// ????????? ?????? ??????
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
						result.setRtnMsg("???????????? ????????? ???????????????");
					}
				}
			} else if(Integer.parseInt(authLevel5VO.getApprovalStatCd()) == 0) {
				result.setRtnCd(-5079);
				result.setRtnMsg("????????? ????????? ????????? ?????????");
			} else if(Integer.parseInt(authLevel5VO.getApprovalStatCd()) == 1) {
				result.setRtnCd(-5080);
				result.setRtnMsg("?????? ????????? ????????? ?????? ???????????????");
			} else {
				result.setRtnCd(-5081);
				result.setRtnMsg("???????????? 5?????? ?????? ????????? ????????????");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5093);
			result.setRtnMsg("????????? ????????? ?????????????????????");
			
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
				// ???????????? ??????????????? ???????????? ?????? ??????
				if (!oldFile.delete())
					oldFile.delete();
			} catch (Exception e) {
			}

		} catch (Exception e) {
			throw e;
		}
	}
}
