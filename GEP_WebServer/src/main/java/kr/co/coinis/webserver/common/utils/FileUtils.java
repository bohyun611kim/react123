/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils 
 *    |_ FileUtils.java
 * 
 * </pre>
 * @date : 2019. 4. 23. 오후 4:32:17
 * @version : 
 * @author : Seongcheol
 */
public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * <pre>
	 * 1. 개요 : path 에 해당하는 파일 읽어 문자열로 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getTemplate
	 * @date : 2019. 4. 23.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 23.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getTemplate(String path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		String result = "";
		
		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
			baos = new ByteArrayOutputStream();
			
			int size;
			byte[] bufferdByte = new byte[512];
			while((size = bis.read(bufferdByte)) != -1) {
				baos.write(bufferdByte, 0, size);
			}
			
			result = baos.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception("fail to read template");
		} finally {
			if(baos != null ) {baos.close();}
			if(bis != null ) {bis.close();}
			if(fis != null ) {fis.close();}
		}
		
		return result;
	}
}
