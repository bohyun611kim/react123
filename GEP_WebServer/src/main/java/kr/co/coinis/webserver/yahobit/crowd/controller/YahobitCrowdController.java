/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.crowd.controller;

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
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.EncryptHelper;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.yahobit.crowd.service.YahobitCrowdService;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqJoinCrowdSaleVO;
import kr.co.coinis.webserver.yahobit.crowd.vo.ReqKycAuthVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.crowd.controller 
 *    |_ YahobitCrowdController.java
 * 
 * </pre>
 * @date : 2019. 5. 24. 오전 9:14:19
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class YahobitCrowdController {
	
	private static final Logger logger = LoggerFactory.getLogger(YahobitCrowdController.class);

	@Value("${attach.file.save.path}")
	private String path;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private CommService commService;
	
	@Autowired
	private YahobitCrowdService yahobitCrowdService;
	
	@RequestMapping(value="/alibit/crowdSale", method=RequestMethod.GET)
	public ModelAndView moveCrowdSale(HttpServletRequest request, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			result = yahobitCrowdService.selectCrowdSaleList(CommonUtils.getExchangeId(request));
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getPageKey(request) + "/crowdsale/crowdSaleList");
		
		model.addObject("coming", result.get("coming"));
		model.addObject("going", result.get("going"));
		model.addObject("ended", result.get("ended"));
		model.addObject("now", DateUtils.getDateTimeNow());
		
		return model;
	}
	
	@RequestMapping(value="/alibit/crowdSale/join", method=RequestMethod.GET)
	public ModelAndView moveCrowdSaleJoin(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "no", defaultValue="", required = true) String no) {
		
		Map<String, Object> result = null;
		
		ModelAndView model = new ModelAndView();
		
		try {
			
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("userId", user.getUserId());
			param.put("no", no);
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = yahobitCrowdService.selectCrowdSaleInfo(param);
			
			if(result != null) {

				model.setViewName(CommonUtils.getPageKey(request) + "/crowdsale/crowdSaleJoin");
				model.addObject("info", result.get("info"));
				model.addObject("type", result.get("type"));
				model.addObject("onList", result.get("list"));
				model.addObject("now", DateUtils.getDateTimeNow());
			} else {
				RedirectView redirectView = new RedirectView(); // redirect url 설정
				redirectView.setUrl("/alibit/crowdSale");
				
				model.setView(redirectView);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return model;
	}
	
	@RequestMapping(value="/alibit/crowdSale/getPriceInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPriceInfo(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "no", defaultValue="", required = true) String no) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> param = new HashMap<>();
			param.put("exchangeId", CommonUtils.getExchangeId(request));
			param.put("no", no);
			
			if(CommonUtils.isLogin(user)) {
				param.put("userId", user.getUserId());
			}
			
			result = yahobitCrowdService.selectPriceInfo(param);
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/crowdSale/doAuthKyc", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO doAuthKyc(MultipartHttpServletRequest mhsr
			, HttpServletRequest request, HttpSession session
			, @ModelAttribute ReqKycAuthVO reqKycAuthVO) {
		
		ResultVO result = new ResultVO();
		
		try {
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			String exchangeId = CommonUtils.getExchangeId(request);
			String userId = user.getUserId();
			String timestamp = DateUtils.getCustomDateTime();
			String fileName = "";
					
			reqKycAuthVO.setExchangeId(exchangeId);
			reqKycAuthVO.setUserId(userId);
			
			if(reqKycAuthVO.getIncome1() == 0 && reqKycAuthVO.getIncome2() == 0
					&& reqKycAuthVO.getIncome3() == 0 && reqKycAuthVO.getIncome4() == 0
					&& reqKycAuthVO.getIncome5() == 0 && reqKycAuthVO.getIncome6() == 0
					&& reqKycAuthVO.getIncome7() == 0 && reqKycAuthVO.getIncome8() == 0) {
				result.setRtnCd(-5060);
				result.setRtnMsg("자금 출처를 선택하세요");
			} else if(reqKycAuthVO.getLiabilityCd() == 3 && "".equals(reqKycAuthVO.getReasonCont())) {
				result.setRtnCd(-5061);
				result.setRtnMsg("거주 국가를 입력해 주세요");
			} else {
				List<MultipartFile> files =  mhsr.getFiles("file");
				
				if(files.size() == 2) {
					boolean isNext = true;
			
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
						reqKycAuthVO.setFileName1(exchangeId + "_" + userId + "_" + timestamp + "_1" + fileName.substring(fileName.lastIndexOf(".")));
						
						fileName = files.get(1).getOriginalFilename();
						reqKycAuthVO.setFileName2(exchangeId + "_" + userId + "_" + timestamp + "_2" + fileName.substring(fileName.lastIndexOf(".")));
						
						result = yahobitCrowdService.insertKycAuthInfo(reqKycAuthVO);
						
						if(result.getRtnCd() != 0) {
							// 업로드 파일 삭제
							if(reqKycAuthVO.getFileName1() != null) {
								File file = new File(path + reqKycAuthVO.getFileName1());
								if(file.isFile()) file.delete();
							}
							
							if(reqKycAuthVO.getFileName2() != null) {
								File file = new File(path + reqKycAuthVO.getFileName2());
								if(file.isFile()) file.delete();
							}
						}
					}
				} else {
					result.setRtnCd(-5036);
					result.setRtnMsg("인증파일 갯수가 모자랍니다");
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5063);
			result.setRtnMsg("KYC 인증자료 제출에 실패하였습니다");
			
			if(reqKycAuthVO.getFileName1() != null) {
				File file = new File(path + reqKycAuthVO.getFileName1());
				if(file.isFile()) file.delete();
			}
			
			if(reqKycAuthVO.getFileName2() != null) {
				File file = new File(path + reqKycAuthVO.getFileName2());
				if(file.isFile()) file.delete();
			}
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/crowdSale/reqSmsCode", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO reqSmsCode(HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		
		try {
			String exchangeId = CommonUtils.getExchangeId(request);
			
			CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			AuthCodeVO authCodeVO = new AuthCodeVO();
			authCodeVO.setExchangeId(exchangeId);
			authCodeVO.setUserId(userDetails.getUserId());
			authCodeVO.setAuthPurposeCd(5);
			authCodeVO.setAuthMeansCd(2);
			authCodeVO.setExpireSec(3 * 60);
			
			authCodeVO = yahobitCrowdService.requestSmsCode(authCodeVO);
			
			if(authCodeVO.getRtnCd() == 0) {
				SendSmsVO sendSmsVo = new SendSmsVO();
				sendSmsVo.setExchangeId(exchangeId);
				sendSmsVo.setUserId(userDetails.getUserId());
				sendSmsVo.setTo(authCodeVO.getAuthMeansKeyVal());
				String smsMsg = String.format("[ALIBIT] Token Launchpad 인증번호 [%s] (전자금융사기예방) 타인에게 누설금지"
									, authCodeVO.getAuthCd());
				sendSmsVo.setMsg(smsMsg);
				sendSmsVo.setMsgOption("sms");
				
				result = commService.sendSms(sendSmsVo);
			} else {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-1186);
			result.setRtnMsg("인증번호 발송에 실패하였습니다");
		}
		
		return result;
	}
	
	@RequestMapping(value="/alibit/crowdSale/joinCrowd", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO joinCrowd(HttpServletRequest request
			, @ModelAttribute @Valid ReqJoinCrowdSaleVO reqJoinCrowdSaleVO
			, BindingResult bindingResult) {
		
		ResultVO result = new ResultVO();
		
		try {
			if(bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().get(0);
				
				result.setRtnCd(-1);
				result.setRtnMsg(error.getDefaultMessage());
			} else {
				CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
				
				if(CommonUtils.isLogin(user)) {
					reqJoinCrowdSaleVO.setExchangeId(CommonUtils.getExchangeId(request));
					reqJoinCrowdSaleVO.setUserId(user.getUserId());
					
					result = yahobitCrowdService.joinCrowdSale(reqJoinCrowdSaleVO);
				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			
			if(e.getMessage().length() == 5) {
				result.setRtnCd(Integer.parseInt(e.getMessage()));
			} else {
				result.setRtnCd(-5066);
			}
			
			result.setRtnMsg("참여에 실패하였습니다");
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
	
	@ResponseBody
	@RequestMapping(value = "alibit/crowdSale/reqQtyMax", method = RequestMethod.POST)
	public Map<String, Object> reqQtyMax(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "crwd_sl_mgt_no", defaultValue = "0", required = true) int crwd_sl_mgt_no ) throws Exception {
		
		try {			
			CustomUserDetails user = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("crwd_sl_mgt_no", crwd_sl_mgt_no);
			paramMap.put("exchange_id", CommonUtils.getExchangeId(request));
			paramMap.put("user_id", user.getUserId());
			
			Map<String, Object> qtyMax = yahobitCrowdService.reqQtyMax(paramMap);
			return qtyMax;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "alibit/crowdSale/reqBonus", method = RequestMethod.POST)
	public Map<String, Object> reqBonus(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "crwd_sl_mgt_no", defaultValue = "0", required = true) int crwd_sl_mgt_no ) throws Exception {
		
		try {			
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("crwd_sl_mgt_no", crwd_sl_mgt_no);
			
			Map<String, Object> bonus = yahobitCrowdService.reqBonus(paramMap);
			return bonus;
		} catch(Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
		
	}
}
