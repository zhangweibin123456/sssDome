package com.encryption;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class testEncryption {

	/**
	 * 
	 * M
	 */
	@Test
	public void testMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String content="123";
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] bytes=md.digest(content.getBytes("utf-8"));
		String bateStr= Base64.encodeBase64String(bytes);
		System.out.println(bateStr);
	}
	
	
	
	
	
	
	

}
