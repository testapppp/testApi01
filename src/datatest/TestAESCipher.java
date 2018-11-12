package datatest;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;

/** 
 * ClassName: AESCipher <br/> 
 * date: 2017年5月22日 上午11:37:24 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public class TestAESCipher {
	
	//加密方式
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	//
	private static final String IV_STRING = "0102030405060708";
	//默认编码
	private static final String CHARSET = "UTF-8";
	 
	public static String aesEncryptString(String content) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return aesEncryptString(content,IV_STRING);
	}
	
	public static String aesEncryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] contentBytes = content.getBytes(CHARSET);
		byte[] keyBytes = key.getBytes(CHARSET);
		byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
		Encoder encoder = Base64.getEncoder();
	    return encoder.encodeToString(encryptedBytes);
	}

	public static String aesDecryptString(String content) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return aesDecryptString(content,IV_STRING);
	}
	
	public static String aesDecryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Decoder decoder = Base64.getDecoder();
	    byte[] encryptedBytes = decoder.decode(content);
	    byte[] keyBytes = key.getBytes(CHARSET);
		byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
	    return new String(decryptedBytes, CHARSET);		
	}
	
	public static byte[] aesEncryptBytes(byte[] contentBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, IV_STRING.getBytes(CHARSET), Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
	}
	
	public static byte[] aesDecryptBytes(byte[] contentBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, IV_STRING.getBytes(CHARSET), Cipher.DECRYPT_MODE);
	}
	
	private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
	    byte[] initParam = IV_STRING.getBytes(CHARSET);
	    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(mode, secretKey, ivParameterSpec);

 	 	return cipher.doFinal(contentBytes);
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		//加密测试----->将对应的头数据加密成密文
		String code = "{\"channel\":\"yoyo\",\"clientId\":\"5546e19dc781bd343ce158127f86586f\",\"defcityid\":0,\"imei\":\"860928032075807\",\"mobile\":\"\",\"mobile_brand\":\"GiONEE\",\"mobile_model\":\"F103\",\"os_type\":1,\"packageName\":\"com.ydwx.yoyo\",\"token\":\"70e8189d46e8abd9083f88d01a9e9297\",\"userid\":66494830169096448,\"version\":\"2.3.6\",\"versioncode\":19}";
		String encode = aesEncryptString(code);
		System.err.println(encode);
		
		//解密测试------>将对应的加密头数据解密成对应的明文		
		encode = "5KdAVyLCYWMpEV/67SjhMbU4W/2jAojsfMPqdJpwQ74evebCVJe52UUm7Su3rRfZ8ALHj8Lj5ekFPx/VQBfcfocN+XXlV6ql9hTN93a8XII04gQVUwK5vUGuXKwXyjWOofr7jywfjZ8YbaPUmsTCaQZtZKGYYKzk7/kmQ+DuVg99w3MGYGR53qWT34SgP6vCJGUMZGeZRhCx6lkPBAQKwEUycdjfRtqVn6gvYF9Iuv7IPEW9oq6dJNFNVt8ejHIRPb7hD8wAJ/anx+gGJvtLqv3QY8AmtFMvcCQY7DX0H37aEh+OzmJiS57dOY/ZjlP1kaljxRMFgJgMMJkYAPCMfY3QdRhENj3OaYazb4IQnjJItRTfn1CTQF6Wo2+5fbebo/Hc69sxapFlkQdN4j7z2HtxCP1KYhAdDJtmlhAqLOo=";
		code = aesDecryptString(encode);
		System.err.println(code);
	}

}
