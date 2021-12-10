package kr.co.coinis.webserver.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.coinis.webserver.common.web.pool.util.KISA_SEED_CBC;

public class EncryptHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptHelper.class);

	private static EncryptHelper instance = null;

	private static String iv;
	private static Key keySpec;

	static String charset = "utf-8";
	
	private static byte[] encryptKey = new byte[16];
	
	private static byte bszIV[] = { (byte) 0x27, (byte) 0x28, (byte) 0x27, (byte) 0x6d, (byte) 0x2d, (byte) 0xd5, (byte) 0x4e,
			(byte) 0x29, (byte) 0x2c, (byte) 0x56, (byte) 0xf4, (byte) 0x2a, (byte) 0x65, (byte) 0x2a, (byte) 0xae,
			(byte) 0x08 };


	@SuppressWarnings("static-access")
	protected EncryptHelper() {
		//            12345678901234567890
		String key = "CoinIsWebKeyCypher01";
		this.iv = key.substring(0, 16);
		//byte[] keyBytes = new byte[16];
		byte[] b = null;
		try {
			b = key.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
		}
		int len = b.length;
		if (len > encryptKey.length) {
			len = encryptKey.length;
		}
		System.arraycopy(b, 0, encryptKey, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(encryptKey, "AES");
		this.keySpec = keySpec;
	}
	
	static {
		if(instance == null) {
			instance = new EncryptHelper();
		}
	}
	
	public static EncryptHelper getInstance() {
		return instance;
	}

	public static String getPrintStackTrace(Object clsObject) {
		ByteArrayOutputStream clsOutStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(clsOutStream);
		((Throwable) clsObject).printStackTrace(printStream);
		return clsOutStream.toString();
	}

	/**
	 * Key값 암호화
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String getEncyptStr(String data) throws Exception {
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(data.getBytes("UTF-8"));
			String enStr = new String(Base64.encodeBase64(encrypted));
			
			return enStr;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 복호화
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String getDecryptStr(String data) throws Exception {
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] byteStr = Base64.decodeBase64(data.getBytes());
			return new String(c.doFinal(byteStr), "UTF-8");
		} catch(Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1.  기능		: SEED 알고리즘을 이용하여 암호화 수행
	 * 2.  처리개요	: seedKey 가 없으면 default key를 사용하여 암호화 수행
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  kangnaru/2018. 4. 6.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param str
	 * @param seedKey
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static byte[] encrypt(byte[] dec, byte[] seedKey) throws Exception {
		byte[] enc = null;
		byte[] encKey = new byte[16];

		try {
			if(seedKey == null) encKey = encryptKey;
			else encKey = seedKey;
			
			// 암호화 함수 호출
			enc = KISA_SEED_CBC.SEED_CBC_Encrypt(encKey, bszIV, dec, 0, dec.length);
			return enc;
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			throw e;
		}
	}

	/**
	 * 
	 * <pre>
	 * 1.  기능		: SEED 알고리즘을 이용하여 복호화 수행
	 * 2.  처리개요	: seedKey 가 없으면 default key를 사용하여 복호화 수행
	 * 3.  주의사항	:  
	 * ====================================
	 * 4.  작성자/작성일	:  kangnaru/2018. 4. 6.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	: 
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param enc
	 * @param seedKey
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static byte[] decrypt(byte[] enc, byte[] seedKey) throws Exception {
		byte[] dec = null;
		byte[] encKey = new byte[16];

		try {
			if(seedKey == null) encKey = encryptKey;
			else encKey = seedKey;

			// 복호화 함수 호출
			dec = KISA_SEED_CBC.SEED_CBC_Decrypt(encKey, bszIV, enc, 0, enc.length);
			return dec;
		} catch (Exception e) {
			logger.error(CommonUtils.getPrintStackTrace(e));
			throw e;
		}
	}

}
