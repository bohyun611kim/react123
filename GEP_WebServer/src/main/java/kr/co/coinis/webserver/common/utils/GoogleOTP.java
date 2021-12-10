package kr.co.coinis.webserver.common.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

/**
 *
* <pre>
* 1.  기능		:  Google OTP
* 2.  처리개요	:
* 3.  주의사항	:
* 4.  작성자/작성일	:  bikejjang/2018. 2. 28.
* ====================================
* 5.  수정사항
* 5.1 요구사항 ID	:
* - 수정자/수정일 	:
* - 수정사유/내역 	:
* ====================================
* <p/>
* @author		: bikejjang
* @since		: J2EE 1.8
* </pre>
 */
public class GoogleOTP {

	/**
	 *
	 * <pre>
	 * 1.  기능		: 사용자, 사이트명으로 암호화키 생성
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  bikejjang/2018. 2. 28.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param strExchangeId
	 * @param strUserId
	 * @return	encodedKey, url (MAP)
	 * </pre>
	 */

	public static HashMap<String, String> generate(String strExchangeId, String strUserId) {

		HashMap<String, String> map = new HashMap<String, String>();
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 20);
		byte[] bEncodedKey = codec.encode(secretKey);

		String encodedKey = new String(bEncodedKey);
		String url = getQRBarcodeURL(encodedKey, strExchangeId, strUserId);

		map.put("encodedKey", encodedKey);
		map.put("url", url);
		return map;
	}

	/**
	 *
	 * <pre>
	 * 1.  기능		: OTP 인증번호 확인
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  bikejjang/2018. 2. 28.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param userCode
	 * @param otpkey
	 * @return
	 * </pre>
	 */
	public static boolean checkCode(String userCode, String otpkey) {

		long otpnum = Integer.parseInt(userCode); // Google OTP 앱에 표시되는 6자리 숫자
		long wave = new Date().getTime() / 30000; // Google OTP의 주기는 30초
		boolean result = false;
		try {
			Base32 codec = new Base32();
			byte[] decodedKey = codec.decode(otpkey);
			int window = 2;

			for (int i = -((window - 1) / 2); i <= window / 2; ++i) {
				long hash = verify_code(decodedKey, wave + i);
				if (hash == otpnum)
					result = true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			// We are dealing with signed bytes:
			// we just keep the first byte.
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}

	/**
	 *
	 * <pre>
	 * 1.  기능		: QR코드 주소 생성
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  bikejjang/2018. 2. 28.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param user
	 * @param host
	 * @param secret
	 * @return
	 * </pre>
	 */
	public static String getQRBarcodeURL(String secret, String exchangeId, String userId) {
		String format = "http://chart.apis.google.com/chart?cht=qr&amp;chs=300x300&amp;chl=otpauth://totp/%s%%3Fsecret%%3D%s&amp;chld=H|0";
		//String format2 = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(format, exchangeId + "-" + userId, secret);
	}
}

