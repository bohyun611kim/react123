/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.about.abt001.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.DateUtils;
import kr.co.coinis.webserver.common.utils.EncryptHelper;
import kr.co.coinis.webserver.holdport.about.abt001.service.HoldportAboutService;

/**
 * <pre>
 * kr.co.coinis.webserver.holdport.about 
 *    |_ AboutController.java
 * 
 * </pre>
 * @date : 2019. 5. 14. 오전 9:38:01
 * @version : 
 * @author : Seongcheol
 */
@Controller
public class AboutController {

	private static final Logger LOG = LoggerFactory.getLogger(AboutController.class);
	
	@Value("${attach.file.save.path}")
	private String path;
	
	@Resource
	private RedisSessionRepository redisSessionRepository;
	
	@Autowired
	private HoldportAboutService holdportAboutService;

	/*
	@RequestMapping(value = "/site/about", method=RequestMethod.GET)
	public ModelAndView moveIntro(HttpServletRequest request, HttpSession session) {
		
		ModelAndView model = new ModelAndView();

		model.setViewName(CommonUtils.getPageKey(request) + "/about/about");
		model.addObject("tab", request.getParameter("tab"));
		
		return model;
	}
	*/
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의내역 리스트를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectInquiryList
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iPageNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/about/abt001/selectInquiryList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectInquiryList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			List<Map<String, Object>> inquiryListMap = new ArrayList<>();
			
			if(user != null) {
				String strExchangeId = CommonUtils.getNewExchangeId(request);
				String strUserId = user.getUserId();
				
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("EXCHANGE_ID", strExchangeId);
				paramMap.put("USER_ID", strUserId );
				paramMap.put("PAGE_INDEX", (iPageNum - 1) * 10 );	// 시작 인덱스
				paramMap.put("PAGE_CONT_NUM", 10);					// 한페이지에 보여줄 페이지 갯수
				inquiryListMap = holdportAboutService.selectInquiryList(paramMap);
			}
			
			return inquiryListMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "site/about/abt001/selectInquiryListCount", method = RequestMethod.POST)
	public Map<String, Object> selectInquiryListCount(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int iPageNum 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> inquiryListCnt = new HashMap<>();
			
			if(user != null) {
				String strExchangeId = CommonUtils.getNewExchangeId(request);
				String strUserId = user.getUserId();
				
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("EXCHANGE_ID", strExchangeId);
				paramMap.put("USER_ID", strUserId );
				inquiryListCnt = holdportAboutService.selectInquiryListCount(paramMap);
			} else {
				inquiryListCnt.put("size", 0);
			}
			
			return inquiryListCnt;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의 답변내용 얻어오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectInquiryReplyList
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param iInquiryNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/about/abt001/selectInquiryReplyList", method = RequestMethod.POST)
	public List<Map<String, Object>> selectInquiryReplyList(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "iInquiryNo", defaultValue = "0", required = true) int iInquiryNo 
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("INQUIRY_NO", iInquiryNo);
			paramMap.put("EXCHANGE_ID", strExchangeId);
			paramMap.put("USER_ID", strUserId );
			List<Map<String, Object>> inquiryReplyListMap = holdportAboutService.selectInquiryReplyList(paramMap);
			
			return inquiryReplyListMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 1:1 문의 등록 요청
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertInquiry
	 * @date : 2019. 5. 21.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 21.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param session
	 * @param strInquiryTitle
	 * @param strInquiryContent
	 * @param strMainCategory
	 * @param strSubCategory
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "site/about/abt001/insertInquiry", method = RequestMethod.POST)
	public Map<String, Object> insertInquiry(HttpServletRequest request, HttpSession session
			, @RequestParam(value = "inquiryTitle", defaultValue = "", required = true) String strInquiryTitle 
			, @RequestParam(value = "inquiryContent", defaultValue = "", required = true) String strInquiryContent
			, @RequestParam(value = "mainCategory", defaultValue = "", required = false) String strMainCategory
			, @RequestParam(value = "subCategory", defaultValue = "", required = false) String strSubCategory
			) throws Exception {
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			String strExchangeId = CommonUtils.getNewExchangeId(request);
			String strUserId = user.getUserId();
			
			int iInquiryTypeCd = 0;
			switch(strMainCategory) {
				case "accnt" :
				case "alimtok" :
					iInquiryTypeCd = 2;
					break;
				case "cacaopay" :
					iInquiryTypeCd = 5;
					break;
				case "depwitd" :
				case "withdrawrequest" :
				case "withdrawrightnow" :
				case "misdeposit" :
					iInquiryTypeCd = 9;
					break;
				case "sbprob" :
					iInquiryTypeCd = 8;
					break;
				
				default: 
					iInquiryTypeCd = 99;
					break;
			}
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
			paramMap.put("ARG_USER_ID", strUserId );
			paramMap.put("ARG_INQUIRY_TYPE_CD", iInquiryTypeCd);
			paramMap.put("ARG_INQUIRY_TITLE", strInquiryTitle);
			paramMap.put("ARG_INQUIRY_CONTENTS", strInquiryContent);
			Map<String, Object> insertInquiryResultMap = holdportAboutService.insertInquiry(paramMap);
			
			return insertInquiryResultMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "site/about/abt001/insertInquiryFile", method = RequestMethod.POST)
	public Map<String, Object> insertInquiryFile(HttpServletRequest request, HttpSession session
			, MultipartHttpServletRequest mhsr
			, @RequestParam(value = "inquiryTitle", defaultValue = "", required = true) String strInquiryTitle 
			, @RequestParam(value = "inquiryContent", defaultValue = "", required = true) String strInquiryContent
			, @RequestParam(value = "mainCategory", defaultValue = "", required = false) String strMainCategory
			, @RequestParam(value = "subCategory", defaultValue = "", required = false) String strSubCategory
			) throws Exception {
		
		List<Map<String, String>> fileList = new ArrayList<>();
		
		List<MultipartFile> files = null;
		
		try {
			CustomUserDetails user = (CustomUserDetails)redisSessionRepository.findBySession(request.getRequestedSessionId());
			
			Map<String, Object> insertInquiryResultMap = new HashMap<>();
			
			if(user != null) {
				String strExchangeId = CommonUtils.getNewExchangeId(request);
				String strUserId = user.getUserId();
				String timestamp = DateUtils.getCustomDateTime();
				
				int iInquiryTypeCd = 0;
				switch(strMainCategory) {
				case "accnt" :
				case "alimtok" :
					iInquiryTypeCd = 2;
					break;
				case "cacaopay" :
					iInquiryTypeCd = 5;
					break;
				case "depwitd" :
				case "withdrawrequest" :
				case "withdrawrightnow" :
				case "misdeposit" :
					iInquiryTypeCd = 9;
					break;
				case "sbprob" :
					iInquiryTypeCd = 8;
					break;
					
				default: 
					iInquiryTypeCd = 99;
					break;
				}
				
				files =  mhsr.getFiles("file");
				long size = 0;
				
				boolean isNext = true;
				
				for(int i=0; i<files.size(); i++) {
					String originalfileName = files.get(i).getOriginalFilename();
					
					if(!originalfileName.toLowerCase().endsWith("jpg") 
							&& !originalfileName.toLowerCase().endsWith("png")) {
						isNext = false;
						break;
					}
					
					size += files.get(i).getSize();
				}
				
				if(isNext && (size/1024/1024) < 50) {
					Map<String, String> fileName = null;
					
					for(int i=0; i<files.size(); i++) {
						String originFile = files.get(i).getOriginalFilename();
						String newFileName = strExchangeId 
								+ "_" + strUserId + "_" 
								+ timestamp + "_" + (i+1) 
								+ originFile.substring(originFile.lastIndexOf("."));
						String tempFile = i + newFileName;
						
						fileName = new HashMap<>();
						
						fileName.put("tempFile", tempFile);
						fileName.put("ARG_ATTACH_FILE_NM", originFile);
						fileName.put("ARG_SAVED_FILE_NM", newFileName);
						fileList.add(fileName);
						
						files.get(i).transferTo(new File(path + tempFile));
						
						moveAttachFile2Encrypt(path + tempFile, path + newFileName);
					}
					
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("ARG_EXCHANGE_ID", strExchangeId);
					paramMap.put("ARG_USER_ID", strUserId );
					paramMap.put("ARG_INQUIRY_TYPE_CD", iInquiryTypeCd);
					paramMap.put("ARG_INQUIRY_TITLE", strInquiryTitle);
					paramMap.put("ARG_INQUIRY_CONTENTS", strInquiryContent);
					insertInquiryResultMap = holdportAboutService.insertInquiryAttachFile(paramMap, fileList);
					
					if((int)insertInquiryResultMap.get("V_RTN_CD") != 0) {
						for(int i=0; i<files.size(); i++) {
							if(fileList.get(i).get("ARG_SAVED_FILE_NM") != null) {
								File file = new File(path + fileList.get(i).get("tempFile"));
								if(file.isFile()) file.delete();
								
								file = new File(path + fileList.get(i).get("ARG_SAVED_FILE_NM"));
								if(file.isFile()) file.delete();
							}
						}
					}
				} else {
					insertInquiryResultMap.put("V_RTN_CD", -1);
					insertInquiryResultMap.put("V_RTN_MSG", "잘못된 첨부파일");
				}
			}
			
			return insertInquiryResultMap;
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			if(files != null) {
				for(int i=0; i<files.size(); i++) {
					if(fileList.get(i).get("ARG_SAVED_FILE_NM") != null) {
						File file = new File(path + fileList.get(i).get("tempFile"));
						if(file.isFile()) file.delete();
						
						file = new File(path + fileList.get(i).get("ARG_SAVED_FILE_NM"));
						if(file.isFile()) file.delete();
					}
				}
			}
			
			return null;
		}
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
