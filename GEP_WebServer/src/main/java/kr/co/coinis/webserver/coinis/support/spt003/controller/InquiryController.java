/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.coinis.webserver.coinis.support.spt003.service.InquiryService;
import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryCodeVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.OneInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddAskCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqOneInquiryVO;
import kr.co.coinis.webserver.common.security.spring.security.session.RedisSessionRepository;
import kr.co.coinis.webserver.common.security.spring.service.CustomUserDetails;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt003.controller 
 *    |_ InquiryController.java
 * 
 * </pre>
 * 
 * @date : 2019. 3. 21. 오후 2:45:16
 * @version :
 * @author : Seongcheol
 */
@Controller
public class InquiryController {
	
	@Resource
	private RedisSessionRepository redisSessionRepository;

	@Autowired
	private InquiryService inquiryService;

	@Value("${attach.file.save.path}")
	private String ATTACH_FILE_SAVE_PATH;

	@RequestMapping(value = "coinis/inquiry")
	public ModelAndView moveInquiry(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName(CommonUtils.getPageKey(request) + "/support/spt003/inquiry");

		return model;
	}

	@RequestMapping(value = "coinis/inquiry/oneinquiry")
	@ResponseBody
	public OneInquiryVO getoneinquiry(HttpServletRequest request, HttpSession session,
			@ModelAttribute @Valid ReqOneInquiryVO reqOneInquiryVO) {

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		reqOneInquiryVO.setExchange_id(CommonUtils.getExchangeId(request));
		reqOneInquiryVO.setUserid(userDetails.getUserId());
		reqOneInquiryVO.setCode_id("C0041");
		reqOneInquiryVO.setLang_cd("ko");

		OneInquiryVO oneInquiryVO = new OneInquiryVO();
		oneInquiryVO = inquiryService.getoneinquiry(reqOneInquiryVO);

		return oneInquiryVO;
	}

	@RequestMapping(value = "coinis/inquiry/inquiryList")
	@ResponseBody
	public Map<String, Object> getinquiryList(HttpServletRequest request, HttpSession session,
			@ModelAttribute @Valid ReqInquiryVO reqinquiryVO) {

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		reqinquiryVO.setExchange_id(CommonUtils.getExchangeId(request));
		reqinquiryVO.setUserid(userDetails.getUserId());
		int startIndex = (reqinquiryVO.getStartIndex() - 1) * 5;
		reqinquiryVO.setStartIndex(startIndex);

		Map<String, Object> inquiryMap = inquiryService.getInquiryList(reqinquiryVO);

		return inquiryMap;
	}

	@RequestMapping(value = "coinis/inquiry/inquiryCode")
	@ResponseBody
	public List<InquiryCodeVO> getInquiryCode(HttpServletRequest request, HttpSession session,

			@ModelAttribute @Valid InquiryCodeVO inquiryCodeVO) {

		inquiryCodeVO.setCode_id("C0041");
		inquiryCodeVO.setLang_cd("ko");

		List<InquiryCodeVO> inquiryCodeList = new ArrayList<InquiryCodeVO>();
		inquiryCodeList = inquiryService.getInquiryCode(inquiryCodeVO);

		return inquiryCodeList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "coinis/inquiry/addInquiry")
	@ResponseBody
	public ResultVO addInquiry(HttpServletRequest request, HttpSession session, MultipartHttpServletRequest mhsq,
			@RequestParam(value = "CertificationPurpose", defaultValue = "0", required = false) int CertificationPurpose)
			throws Exception {

		ResultVO result = new ResultVO();
		ReqAddInquiryCertifyVO reqAddInquiryCertifyVO = new ReqAddInquiryCertifyVO();

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		String Exchange_id = CommonUtils.getExchangeId(request);
		String userId = userDetails.getUserId();
		String inquiry_type_cd = mhsq.getParameter("inquiry_type_cd");
		String inquiry_title = mhsq.getParameter("inquiry_title");
		String inquiry_contents = mhsq.getParameter("inquiry_contents");

		String realFolder = ATTACH_FILE_SAVE_PATH;
		File dir = new File(realFolder);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
		String cur_time_stamp = simDf.format(new Date(currentTime));

		boolean fileCheck = true;

		// 넘어온 파일을 리스트로 저장
		List<MultipartFile> mf = mhsq.getFiles("uploadfile");

		List FileNameList = new ArrayList();
		List FileFullPathNameList = new ArrayList();
		List SavePathList = new ArrayList();
		List OriginalFileName1List = new ArrayList();

		if (mf.size() > 0) {
			if (fileCheck) {
				for (int i = 0; i < mf.size(); i++) {
					if (!mf.get(i).getOriginalFilename().isEmpty()) {

						// 파일갯수 설정
						reqAddInquiryCertifyVO.setFileCount(i + 1);

						// 파일 중복명 처리
						String genId = UUID.randomUUID().toString();

						// 본래 파일명
						String originalfileName = mf.get(i).getOriginalFilename();

						String saveFileName = genId + "." + originalfileName;

						// 임시로저장되는 파일 이름 (원본파일을 임시로 저장)
						String savePath = realFolder + saveFileName;

						mf.get(i).transferTo(new File(savePath)); // 임시로 파일 저장

						// 암호화되어 저장될 파일이름 규칙 : ExchangeID_UserID_yyyyMMddHHmmss_1.EXT
						String newFileName = Exchange_id + "_" + userId + "_" + cur_time_stamp + "_" + (i + 1) + "."
								+ originalfileName.substring(originalfileName.lastIndexOf(".") + 1);

						// 파일명에 경로까지
						String FileFullPathName = realFolder + newFileName;

						FileNameList.add(newFileName);
						FileFullPathNameList.add(FileFullPathName);
						SavePathList.add(savePath);
						OriginalFileName1List.add(originalfileName);
					}
				}
				reqAddInquiryCertifyVO.setFileNameList(FileNameList);
				reqAddInquiryCertifyVO.setFileFullPathNameList(FileFullPathNameList);
				reqAddInquiryCertifyVO.setSetSavePathList(SavePathList);
				reqAddInquiryCertifyVO.setOriginalFileName1List(OriginalFileName1List);
			}
		}
		reqAddInquiryCertifyVO.setExchange_id(CommonUtils.getExchangeId(request));
		reqAddInquiryCertifyVO.setUserid(userDetails.getUserId());
		reqAddInquiryCertifyVO.setInquiry_type_cd(Integer.parseInt(inquiry_type_cd));
		reqAddInquiryCertifyVO.setInquiry_title(inquiry_title);
		reqAddInquiryCertifyVO.setInquiry_contents(inquiry_contents);

		result = inquiryService.addInquiry(reqAddInquiryCertifyVO);

		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "coinis/inquiry/addreask")
	@ResponseBody
	public ResultVO addReAsk(HttpServletRequest request, HttpSession session, MultipartHttpServletRequest mhsq,
			@RequestParam(value = "CertificationPurpose", defaultValue = "0", required = false) int CertificationPurpose)
			throws Exception {

		ResultVO result = new ResultVO();
		ReqAddAskCertifyVO reqAddAskCertifyVO = new ReqAddAskCertifyVO();

		CustomUserDetails userDetails = (CustomUserDetails) redisSessionRepository.findBySession(request.getRequestedSessionId());

		String Exchange_id = CommonUtils.getExchangeId(request);
		String userId = userDetails.getUserId();
		String inquiry_no = mhsq.getParameter("inquiry_no");
		String reply_contents = mhsq.getParameter("reply_contents");

		String realFolder = ATTACH_FILE_SAVE_PATH;
		File dir = new File(realFolder);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
		String cur_time_stamp = simDf.format(new Date(currentTime));

		boolean fileCheck = true;

		// 넘어온 파일을 리스트로 저장
		List<MultipartFile> mf = mhsq.getFiles("uploadfile");

		List FileNameList = new ArrayList();
		List FileFullPathNameList = new ArrayList();
		List SavePathList = new ArrayList();
		List OriginalFileName1List = new ArrayList();

		if (mf.size() > 0) {
			if (fileCheck) {
				for (int i = 0; i < mf.size(); i++) {
					if (!mf.get(i).getOriginalFilename().isEmpty()) {

						// 파일갯수 설정
						reqAddAskCertifyVO.setFileCount(i + 1);

						// 파일 중복명 처리
						String genId = UUID.randomUUID().toString();

						// 본래 파일명
						String originalfileName = mf.get(i).getOriginalFilename();

						String saveFileName = genId + "." + originalfileName;

						// 임시로저장되는 파일 이름 (원본파일을 임시로 저장)
						String savePath = realFolder + saveFileName;

						mf.get(i).transferTo(new File(savePath)); // 임시로 파일 저장

						// 암호화되어 저장될 파일이름 규칙 : ExchangeID_UserID_yyyyMMddHHmmss_1.EXT
						String newFileName = Exchange_id + "_" + userId + "_" + cur_time_stamp + "_" + (i + 1) + "."
								+ originalfileName.substring(originalfileName.lastIndexOf(".") + 1);

						// 파일명에 경로까지
						String FileFullPathName = realFolder + newFileName;

						FileNameList.add(newFileName);
						FileFullPathNameList.add(FileFullPathName);
						SavePathList.add(savePath);
						OriginalFileName1List.add(originalfileName);
					}
				}
				reqAddAskCertifyVO.setFileNameList(FileNameList);
				reqAddAskCertifyVO.setFileFullPathNameList(FileFullPathNameList);
				reqAddAskCertifyVO.setSetSavePathList(SavePathList);
				reqAddAskCertifyVO.setOriginalFileName1List(OriginalFileName1List);
			}
		}

		reqAddAskCertifyVO.setExchange_id(CommonUtils.getExchangeId(request));
		reqAddAskCertifyVO.setUserid(userDetails.getUserId());
		reqAddAskCertifyVO.setInquiry_no(Integer.parseInt(inquiry_no));
		reqAddAskCertifyVO.setReply_contents(reply_contents);

		result = inquiryService.addReAsk(reqAddAskCertifyVO);

		return result;
	}
}
