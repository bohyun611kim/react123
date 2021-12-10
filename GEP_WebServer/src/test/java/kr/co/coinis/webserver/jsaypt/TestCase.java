package kr.co.coinis.webserver.jsaypt;

public class TestCase {

	/*@SuppressWarnings("static-access")
	@Test
	public void moveAttachFile2Encrypt() throws Exception {
		String original_FilePathName = "H:\\98.Temp\\인증사진_암호화파일\\meangom15@naver.com_20180628135817_2.jpg";
		String new_FilePathName = "H:\\\\98.Temp\\\\인증사진_암호화파일\\\\meangom15@naver.com_20180628135817_2_1.jpg";
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

			byte[] dec = EncryptHelper.getInstance().decrypt(baos.toByteArray(), null);
			fout.write(dec);
			fout.flush();
			fin.close();
			fout.close();
			baos.close();

//			try {
//				// 원본파일 삭제실패시 다시한번 삭제 시도
//				if(!oldFile.delete()) oldFile.delete();
//			} catch(Exception e) { }
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Test
	public void decryptFileFolder() throws Exception {
		try {
			File file = new File("H:\\98.Temp\\인증사진_암호화파일\\");
			File[] fileList = file.listFiles();
			for(File f : fileList) {
				if(f.isDirectory()) continue;
				
				String fname = f.getName();
				String fext = fname.substring(fname.lastIndexOf("."));
				String target_name = f.getAbsolutePath() + "_decrypted" + fext;
				
				File oldFile = f;
				File newFile = new File(target_name);

				int read = 0;
				byte[] buf = new byte[1024];
				FileInputStream fin = new FileInputStream(oldFile);
				FileOutputStream fout = new FileOutputStream(newFile);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, read);
				}

				byte[] dec = EncryptHelper.getInstance().decrypt(baos.toByteArray(), null);
				fout.write(dec);
				fout.flush();
				fin.close();
				fout.close();
				baos.close();

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testcase1() {
		String tr = "4111553161614";
		Map headerMap = new HashMap();
		headerMap.put("HeaderReqIdx", tr);
		headerMap.put("HeaderUserID", "space");
		
		try {
			// HeaderReqIdx 와 HeaderUserID를 이용한 strRequestIndx 조합
			String headerReqIdx = "" + Double.valueOf(headerMap.get("HeaderReqIdx").toString()).longValue();
			String headerUserId = headerMap.get("HeaderUserID").toString();
			String randomNum = headerReqIdx.substring(headerReqIdx.length() - 4);
			String timestamp = headerReqIdx.substring(0, headerReqIdx.length() - 4);
			long ts_long = Long.valueOf(timestamp).longValue();
			Date pd = new SimpleDateFormat("MMddHHmmss").parse((ts_long < 1000000000) ? "0" + ts_long : "" + ts_long);
			String parsed_datetime = new SimpleDateFormat("MMddHHmmss").format(pd);
	
			String strRequestIndx = headerUserId + "_" + parsed_datetime + "_" + randomNum;
			
			System.out.println( strRequestIndx);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testcase2() throws Exception {
		
		try {
		
		// double reqDate = 2.0180402E7;  
		// double reqDate = 2.0180117164339408E16;
		double reqDate = 2.0180110131452E13;
		String reqDateStr = CommonUtils.doubleToIntString(reqDate); 
		
		System.out.println("1 reqDateStr::::::::::::::::::::::::::::> " +  reqDateStr );   		
		
		reqDateStr = reqDateStr.replaceAll(",", "");
		System.out.println("2 reqDateStr::::::::::::::::::::::::::::> " +  reqDateStr );   				
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = null;
		
		try {
		
		// dt.setLenient(true);			
		date = dt.parse(reqDateStr);
		} catch (ParseException pe ) {
			date = null;
		}
		
		if ( date == null ) {
			reqDateStr = "";
		} else {
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			reqDateStr = dt1.format(date);
		}
		
	//	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	//	reqDateStr = dt1.format(date);
		
		System.out.println("3 reqDateStr::::::::::::::::::::::::::::> " +  reqDateStr );   		
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}*/

}
