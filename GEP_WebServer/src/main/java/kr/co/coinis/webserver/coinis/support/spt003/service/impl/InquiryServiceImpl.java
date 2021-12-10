/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.support.spt003.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.coinis.webserver.coinis.support.spt003.dao.InquiryDAO;
import kr.co.coinis.webserver.coinis.support.spt003.service.InquiryService;
import kr.co.coinis.webserver.coinis.support.spt003.vo.InquiryCodeVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.OneInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddAskCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryCertifyVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqAddReAskVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqInquiryVO;
import kr.co.coinis.webserver.coinis.support.spt003.vo.ReqOneInquiryVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.EncryptHelper;
import kr.co.coinis.webserver.common.vo.ResultVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.spt003.service.impl 
 *    |_ InquiryServiceImpl.java
 * 
 * </pre>
 * 
 * @date : 2019. 05. 02. 오후 2:42:54
 * @version :
 * @author : Jungjea
 */
@Service("InquiryService")
public class InquiryServiceImpl implements InquiryService {

	@Resource(name = "inquiryDAO")
	private InquiryDAO inquiryDAO;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Override
	public OneInquiryVO getoneinquiry(ReqOneInquiryVO reqOneInquiryVO) {

		OneInquiryVO oneInquiryVO = new OneInquiryVO();

		oneInquiryVO = inquiryDAO.getoneinquiry(reqOneInquiryVO);

		return oneInquiryVO;
	}

	/* 1:1 문의 List 조회 */
	@Override
	public Map<String, Object> getInquiryList(ReqInquiryVO reqInquiryVO) {

		Map<String, Object> inquiryMap = new HashMap<String, Object>();
		inquiryMap.put("inquirydata", inquiryDAO.getInquiryList(reqInquiryVO));

		int size = inquiryDAO.getInquiryListCount(reqInquiryVO);
		inquiryMap.put("size", size);

		return inquiryMap;
	}

	/* 1:1 문의 내용 답변(재문의), 문의 자료 제출 (재문의) */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public ResultVO addReAsk(ReqAddAskCertifyVO reqAddAskCertifyVO) throws Exception {

		/////////////////////////////////////////////////////////////////////////////////
		// TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("was-request-Add-Re-Ask");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

		ResultVO result = new ResultVO();
		ReqAddReAskVO reqAddReAskVO = new ReqAddReAskVO();
		ReqAddCertifyVO reqAddCertifyVO = new ReqAddCertifyVO();

		try {

			// File관련
			List FileNameList = new ArrayList();
			List FileFullPathNameList = new ArrayList();
			List SavePathList = new ArrayList();
			List OriginalFileName1List = new ArrayList();

			FileNameList = reqAddAskCertifyVO.getFileNameList();
			FileFullPathNameList = reqAddAskCertifyVO.getFileFullPathNameList();
			SavePathList = reqAddAskCertifyVO.getSetSavePathList();
			OriginalFileName1List = reqAddAskCertifyVO.getOriginalFileName1List();

			try {
				if (reqAddAskCertifyVO.getFileCount() > 0) {
					for (int cnt = 0; cnt < reqAddAskCertifyVO.getFileCount(); cnt++) {
						// 암호화하여 새로운 파일로 저장후 원본파일은 삭제한다.
						moveAttachFile2Encrypt(SavePathList.get(cnt).toString(), FileFullPathNameList.get(cnt).toString());
					}
				}
			} catch (Exception e) {
				result.setRtnCd(-9999);
				result.setRtnMsg(CommonUtils.getPrintStackTrace(e));
				return result;
			}

			reqAddReAskVO.setExchange_id(reqAddAskCertifyVO.getExchange_id());
			reqAddReAskVO.setUserid(reqAddAskCertifyVO.getUserid());
			reqAddReAskVO.setInquiry_no(reqAddAskCertifyVO.getInquiry_no());
			reqAddReAskVO.setReply_contents(reqAddAskCertifyVO.getReply_contents());

			reqAddReAskVO = inquiryDAO.addReAsk(reqAddReAskVO);

			result.setRtnCd(reqAddReAskVO.getRtnCd());
			result.setRtnMsg(reqAddReAskVO.getRtnMsg());

			if (reqAddReAskVO.getRtnCd() != 0) {
				transactionManager.rollback(status);
				deleteFile(FileFullPathNameList, reqAddAskCertifyVO.getFileCount());
				return result;
			}

			if (reqAddAskCertifyVO.getFileCount() > 0) {
				reqAddCertifyVO.setExchange_id(reqAddAskCertifyVO.getExchange_id());
				reqAddCertifyVO.setUserid(reqAddAskCertifyVO.getUserid());
				reqAddCertifyVO.setReply_no(reqAddReAskVO.getReply_no());

				for (int cnt = 0; cnt < reqAddAskCertifyVO.getFileCount(); cnt++) {
					reqAddCertifyVO.setAttach_file_nm(OriginalFileName1List.get(cnt).toString());
					reqAddCertifyVO.setSaved_file_nm(FileNameList.get(cnt).toString());

					reqAddCertifyVO = inquiryDAO.addReAskCertify(reqAddCertifyVO);

					result.setRtnCd(reqAddCertifyVO.getRtnCd());
					result.setRtnMsg(reqAddCertifyVO.getRtnMsg());

					if (reqAddCertifyVO.getRtnCd() != 0) {
						transactionManager.rollback(status);
						deleteFile(FileFullPathNameList, reqAddAskCertifyVO.getFileCount());
						return result;
					}
				}
			}

			transactionManager.commit(status);

		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
		}
		
		return result;
	}

	/* 1:1 문의 팝업 내 문의구분 조회 */

	@Override
	public List<InquiryCodeVO> getInquiryCode(InquiryCodeVO inquiryCodeVO) {
		return inquiryDAO.getInquiryCode(inquiryCodeVO);
	}

	/* new 1:1 문의 등록, 문의 자료 제출 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public ResultVO addInquiry(ReqAddInquiryCertifyVO reqAddInquiryCertifyVO) throws Exception {

		/////////////////////////////////////////////////////////////////////////////////
		// TRANSACTION MANAGEMENT
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("was-request-Add-Inquiry");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		/////////////////////////////////////////////////////////////////////////////////

		ResultVO result = new ResultVO();
		ReqAddInquiryVO reqAddInquiryVO = new ReqAddInquiryVO();
		ReqAddCertifyVO reqAddCertifyVO = new ReqAddCertifyVO();

		try {

			// File관련
			List FileNameList = new ArrayList();
			List FileFullPathNameList = new ArrayList();
			List SavePathList = new ArrayList();
			List OriginalFileName1List = new ArrayList();

			FileNameList = reqAddInquiryCertifyVO.getFileNameList();
			FileFullPathNameList = reqAddInquiryCertifyVO.getFileFullPathNameList();
			SavePathList = reqAddInquiryCertifyVO.getSetSavePathList();
			OriginalFileName1List = reqAddInquiryCertifyVO.getOriginalFileName1List();

			try {
				if (reqAddInquiryCertifyVO.getFileCount() > 0) {
					for (int cnt = 0; cnt < reqAddInquiryCertifyVO.getFileCount(); cnt++) {
						// 암호화하여 새로운 파일로 저장후 원본파일은 삭제한다.
						moveAttachFile2Encrypt(SavePathList.get(cnt).toString(), FileFullPathNameList.get(cnt).toString());
					}
				}
			} catch (Exception e) {
				result.setRtnCd(-9999);
				result.setRtnMsg(CommonUtils.getPrintStackTrace(e));
				return result;
			}

			reqAddInquiryVO.setExchange_id(reqAddInquiryCertifyVO.getExchange_id());
			reqAddInquiryVO.setUserid(reqAddInquiryCertifyVO.getUserid());
			reqAddInquiryVO.setInquiry_type_cd(reqAddInquiryCertifyVO.getInquiry_type_cd());
			reqAddInquiryVO.setInquiry_title(reqAddInquiryCertifyVO.getInquiry_title());
			reqAddInquiryVO.setInquiry_contents(reqAddInquiryCertifyVO.getInquiry_contents());

			reqAddInquiryVO = inquiryDAO.addInquiry(reqAddInquiryVO);

			result.setRtnCd(reqAddInquiryVO.getRtnCd());
			result.setRtnMsg(reqAddInquiryVO.getRtnMsg());

			if (reqAddInquiryVO.getRtnCd() != 0) {
				transactionManager.rollback(status);
				deleteFile(FileFullPathNameList, reqAddInquiryCertifyVO.getFileCount());
				return result;
			}

			if (reqAddInquiryCertifyVO.getFileCount() > 0) {
				reqAddCertifyVO.setExchange_id(reqAddInquiryCertifyVO.getExchange_id());
				reqAddCertifyVO.setUserid(reqAddInquiryCertifyVO.getUserid());
				reqAddCertifyVO.setInquiry_no(reqAddInquiryVO.getInquiry_no());

				for (int cnt = 0; cnt < reqAddInquiryCertifyVO.getFileCount(); cnt++) {
					reqAddCertifyVO.setAttach_file_nm(OriginalFileName1List.get(cnt).toString());
					reqAddCertifyVO.setSaved_file_nm(FileNameList.get(cnt).toString());

					reqAddCertifyVO = inquiryDAO.addInquiryCertify(reqAddCertifyVO);

					result.setRtnCd(reqAddCertifyVO.getRtnCd());
					result.setRtnMsg(reqAddCertifyVO.getRtnMsg());

					if (reqAddCertifyVO.getRtnCd() != 0) {
						transactionManager.rollback(status);
						deleteFile(FileFullPathNameList, reqAddInquiryCertifyVO.getFileCount());
						return result;
					}
				}
			}

			transactionManager.commit(status);
			
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
		}

		return result;
	}

	@SuppressWarnings({ "static-access" })
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

	@SuppressWarnings({ "rawtypes" })
	private void deleteFile(List FileFullPathNameList, int FileCount) {
		for (int cnt = 0; cnt < FileCount; cnt++) {
			String new_FilePathName = FileFullPathNameList.get(cnt).toString();
			File newFile = new File(new_FilePathName);

			try {
				// 원본파일 삭제실패시 다시한번 삭제 시도
				if (!newFile.delete())
					newFile.delete();
			} catch (Exception e) {
			}
		}
	}
}
