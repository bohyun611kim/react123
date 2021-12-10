package kr.co.coinis.webserver.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CryptProvider {

  private static final Logger logger = LoggerFactory.getLogger(CryptProvider.class);

  private static Cipher cipher=null;

  public static String getAesEncryptData(String data, String Key, String IV) throws Exception {

    try {

      SecretKeySpec keySpec = getKeySpec(Key);

      if(cipher==null) {
	      if (null != IV) {
	    	  IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
	    	  cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
	    	  cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

	      } else {
	    	  cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    	  cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	      }
      }
      byte[] encryptedBytes=cipher.doFinal(data.getBytes("UTF-8"));
      String encryptString = new String(Base64.encodeBase64(encryptedBytes));

      return encryptString;

    } catch (Exception e) {
    	e.printStackTrace();
    }

    return "";
  }

  private static SecretKeySpec getKeySpec(String Key) throws UnsupportedEncodingException {

    byte[] keyBytes = new byte[16];
    Arrays.fill(keyBytes, (byte) 0x00);

    byte[] inputKeyBytes = Key.getBytes("UTF-8");
    //byte[] inputKeyBytes = hexToByteArray(Key);
    int length = inputKeyBytes.length < keyBytes.length ? inputKeyBytes.length : keyBytes.length;

    System.arraycopy(inputKeyBytes, 0, keyBytes, 0, length);
    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

    return keySpec;
  }

  public static byte[] hexToByteArray(String hex) {

		if (hex == null || hex.length() == 0) {
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
  }

  public static void main(String[] args) throws Exception {

	  //String key="6058644588581810";
	  String key="DIBA4ONEPAYKOREA";

	  String test="test";

	  String encryptData= getAesEncryptData(test, key, null);
	  System.out.println("result["+encryptData+"]");

	  return;
  }

}