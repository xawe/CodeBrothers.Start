package com.codebrothers.services.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.codebrothers.services.auth.security.DataEncryptor;
import com.codebrothers.services.auth.security.DataEncryptorImpl;

import at.favre.lib.bytes.Bytes;
import at.favre.lib.crypto.bcrypt.BCrypt;

//@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void encryptFunctionPassTest() {
		String adminPass = "admin";			
		byte[] salt = new byte[]{0x5E, (byte) 0xFA, (byte) 0xA7, (byte) 0xA3, (byte) 0xD9, (byte) 0xDF, 0x6E, (byte) 0x7F, (byte) 0x8C, 0x78, (byte) 0x96, (byte) 0xB1, 0x7B, (byte) 0xA7, 0x6E, 0x01};
		BCrypt.Hasher bCrypt = BCrypt.withDefaults();		
		for (int i = 4; i < 10; i++) {
            byte[] hash = bCrypt.hash(i, salt, adminPass.getBytes());
            assertEquals(60, hash.length);
            System.out.println(Bytes.wrap(hash).encodeUtf8());
        }		
	}
	
	@Test
	public void DataEncryptorPassTest() {
		DataEncryptor de = new DataEncryptorImpl();
		String text = "kasjhd989123.1..1";
		assertEquals(60,  de.getEncryptedData(text).length());
	}
	@Test public void DataEncryptorVerifyPassTest() {
		DataEncryptor de = new DataEncryptorImpl();
		String text = "kasjhd989123.1..1";
		String encryptedText = de.getEncryptedData(text);	
		assertTrue(de.match(text, encryptedText));		
	}

	
	@Test public void DataEncryptorVerifyDiferentStancesPassTest() {
		DataEncryptor de = new DataEncryptorImpl();
		String text = "kasjhd989123.1..1";
		String encryptedText = de.getEncryptedData(text);
		
		DataEncryptor de2 = new DataEncryptorImpl();
		String encryptedText2 = de2.getEncryptedData(text);		
		
		assertTrue(de.match(text, encryptedText));		
		assertTrue(de.match(text, encryptedText2));
		assertTrue(de2.match(text, encryptedText));		
		assertTrue(de2.match(text, encryptedText2));
	}
}
