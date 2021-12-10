/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.mypage.mpg004.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.mypage.mpg004.service.IdSetupService;
import kr.co.coinis.webserver.coinis.mypage.mpg004.vo.CorpInfoVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.EncryptHelper;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.yahobit.mypage.mpg002.vo.ReqIdCardVO;

/**
 * <pre>
 * kr.co.coinis.webserver.mypage.mpg004.controller 
 *    |_ IdSetupController.java
 * 
 * </pre>
 * @date : 2019. 5. 13. 오후 1:17:30
 * @version : 
 * @author : yeonseoo
 */
@Controller
public class IdSetupController {
	
//	private static final Logger LOG = LoggerFactory.getLogger(YahobitAuthController.class);
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Value("D:\\auth_file_path\\")
	private String path;

	@Autowired
	private IdSetupService idSetupService;
	
	@RequestMapping(value = "coinis/idSetup")
	public ModelAndView moveSecuritySetup(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/mypage/mpg004/idSetup");
		
		List<String> countryList = idSetupService.selectCountryList();
		
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		
		model.addObject("countryList", countryList);
		model.addObject("user_id", user_id);
		
		return model;
	}

	@RequestMapping(value="coinis/idSetup/submitIdvIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitIdvIdcard(MultipartHttpServletRequest param
			, HttpServletRequest request, HttpSession session) {
	
		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();
		
		try {
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			String exchange_id = CommonUtils.getExchangeId(request);
			String user_id = userInfo.getUserId();
			String timeStamp = DateUtils.getCustomDateTime();
			
			String userName = param.getParameter("name");
			String birthday = param.getParameter("birthday");
			birthday.replaceAll(".", "");
			String address = param.getParameter("address");
			String postalCode = param.getParameter("postalCode");
			String country = param.getParameter("country");
			int idType = Integer.parseInt(param.getParameter("idType"));


			reqIdCardVO.setExchangeId(exchange_id);
			reqIdCardVO.setUserId(user_id);
			reqIdCardVO.setUserName(userName);
			reqIdCardVO.setBirthday(birthday);
			reqIdCardVO.setAddress(address);
			reqIdCardVO.setPostal(postalCode);
			reqIdCardVO.setCountry(country);
			reqIdCardVO.setIdCardType(idType);
			
			List<MultipartFile> files = param.getFiles("file");
			
			if( files.size() != 2) {
				result.setRtnCd(-1);
				result.setRtnMsg("you should attach two files"); 
			} else {
				// 확장자 검사
				boolean isNext = true;
				
				for( int i = 0; i < files.size(); i++) {
					String originalfileName = files.get(i).getOriginalFilename();
					
					if(!originalfileName.toLowerCase().endsWith("jpg") 
							&& !originalfileName.toLowerCase().endsWith("png")) {
						isNext = false;
						break;
					}
				}
				
				// 파일 업로드
				String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";
				
				if(isNext) {
					for(int i = 0; i < files.size(); i++) {
						genId = UUID.randomUUID().toString();
						origin = files.get(i).getOriginalFilename();
						saveFileName = genId + "." + origin;
						savePath = path + saveFileName;
						newFileName = exchange_id + "_" + user_id + "_" + timeStamp + "_" + (i+1) + origin.substring(origin.lastIndexOf("."));
						
						files.get(i).transferTo(new File(savePath));
						
						// 서버에 파일 저장
						boolean saveResult = saveAttachedFiletoServerasEncrypt(savePath, path + newFileName);
						
						if( !saveResult ) {
							result.setRtnCd(-1);
							result.setRtnMsg("upload has been failed");
						}
					}
					
					// 회원 신분증 인증 정보 저장 
					String fileName = "";
					
					fileName = files.get(0).getOriginalFilename();
					reqIdCardVO.setFileName1(exchange_id + "_" + user_id + "_" + timeStamp + "_1" 
											+ fileName.substring(fileName.lastIndexOf(".")));
					
					fileName = files.get(1).getOriginalFilename();
					reqIdCardVO.setFileName2(exchange_id + "_" + user_id + "_" + timeStamp + "_2" 
											+ fileName.substring(fileName.lastIndexOf(".")));
					
					result = idSetupService.uploadIdvIdCardFile(reqIdCardVO, 1);		// 개인 회원 member_type_cd 1
					
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
						
						result.setRtnCd(-1);
						result.setRtnMsg("upload has been failed");
						return result;
					}
					
					// 회원 추가정보- 신분증 인증 정보 업데이트
					result = idSetupService.updateMemberInfoExtraIDAuthInfo(reqIdCardVO);
					
					if( result.getRtnCd() != 0) {
						result.setRtnCd(-1);
						result.setRtnMsg("upload has been failed");
						return result;
					} else {
						result.setRtnCd(0);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();

			result.setRtnCd(-1);
			result.setRtnMsg("upload has been failed");
		}

		return result;
	}

	/* 사업자등록번호 조회 */
	@RequestMapping(value="coinis/idSetup/authCorpNo", method = RequestMethod.POST)
	@ResponseBody
	public CorpInfoVO authCorpNo(HttpServletRequest request, HttpSession session, @RequestParam String corpNo) {
	
		Map<String, Object> paramMap = new HashMap<>();
		
		CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
		String user_id = userInfo.getUserId();
		paramMap.put("user_id", user_id);
		paramMap.put("corpNo", corpNo);
		
		CorpInfoVO corpInfo = idSetupService.selectCorpInfo(paramMap);
		
		if (corpInfo == null) {
			corpInfo = new CorpInfoVO();
			corpInfo.setCorp_nm("-1");
		}
		
		return corpInfo;
	}
	
	@RequestMapping(value="coinis/idSetup/submitCorpIdcard", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO submitCorpIdcard(MultipartHttpServletRequest param
			, HttpServletRequest request, HttpSession session) {
		
		ResultVO result = new ResultVO();
		ReqIdCardVO reqIdCardVO = new ReqIdCardVO();
		
		try {
			CustomUserDetails userInfo = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());
			String exchange_id = CommonUtils.getExchangeId(request);
			String user_id = userInfo.getUserId();
			String timeStamp = DateUtils.getCustomDateTime();
			
			String corpNo = param.getParameter("corpNo");
			int idType = Integer.parseInt(param.getParameter("idType"));

			reqIdCardVO.setExchangeId(exchange_id);
			reqIdCardVO.setUserId(user_id);
			reqIdCardVO.setCorpNo(corpNo);
			reqIdCardVO.setIdCardType(idType);
			
			List<MultipartFile> files = param.getFiles("file");
			
			if( files.size() != 3) {
				result.setRtnCd(-1);
				result.setRtnMsg("you should attach three files"); 
			} else {
				// 확장자 검사
				boolean isNext = true;
				
				for( int i = 0; i < files.size(); i++) {
					String originalfileName = files.get(i).getOriginalFilename();
					
					if(!originalfileName.toLowerCase().endsWith("jpg") 
							&& !originalfileName.toLowerCase().endsWith("png")) {
						isNext = false;
						break;
					}
				}
				
				// 파일 업로드
				String genId = "", origin = "", saveFileName = "", savePath = "", newFileName = "";
				
				if(isNext) {
					for(int i = 0; i < files.size(); i++) {
						genId = UUID.randomUUID().toString();
						origin = files.get(i).getOriginalFilename();
						saveFileName = genId + "." + origin;
						savePath = path + saveFileName;
						newFileName = exchange_id + "_" + user_id + "_" + timeStamp + "_" + (i+1) + origin.substring(origin.lastIndexOf("."));
						
						files.get(i).transferTo(new File(savePath));
						
						// 서버에 파일 저장
						boolean saveResult = saveAttachedFiletoServerasEncrypt(savePath, path + newFileName);
						
						if( !saveResult ) {
							result.setRtnCd(-1);
							result.setRtnMsg("upload has been failed");
						}
					}
					
					// 법인 신분증 인증 정보 저장 
					String fileName = "";
					
					fileName = files.get(0).getOriginalFilename();
					reqIdCardVO.setFileName1(exchange_id + "_" + user_id + "_" + timeStamp + "_1" 
											+ fileName.substring(fileName.lastIndexOf(".")));
					
					fileName = files.get(1).getOriginalFilename();
					reqIdCardVO.setFileName2(exchange_id + "_" + user_id + "_" + timeStamp + "_2" 
											+ fileName.substring(fileName.lastIndexOf(".")));
					
					fileName = files.get(2).getOriginalFilename();
					reqIdCardVO.setFileName3(exchange_id + "_" + user_id + "_" + timeStamp + "_3" 
											+ fileName.substring(fileName.lastIndexOf(".")));
					
					result = idSetupService.uploadCorpIdCardFile(reqIdCardVO, 2);	// 법인 회원 member_type_cd 2
					
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
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

			result.setRtnCd(-1);
			result.setRtnMsg("upload has been failed");
		}

		return result;
	}


	@SuppressWarnings("static-access")
	private boolean saveAttachedFiletoServerasEncrypt(String original_FilePathName, String new_FilePathName) throws Exception {
		
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
				e.printStackTrace();
				return false;
			}
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
