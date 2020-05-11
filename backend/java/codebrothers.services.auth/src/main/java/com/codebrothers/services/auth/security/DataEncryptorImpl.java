package com.codebrothers.services.auth.security;


import org.springframework.stereotype.Component;

import at.favre.lib.bytes.Bytes;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Classe responsável por criptografar (na verdade obter um hash ) da string  
 *
 */
@Component
public class DataEncryptorImpl implements DataEncryptor{
	
	/**
	 * Cria um hash de uma string usando o menor custo (evitar usar em prod)
	 * @param str texto para obter o hash
	 * @return hash de 60 posições da string recebida
	 */
	public String getEncryptedData(String str) {
			
		//byte[] salt = new byte[]{0x5E, (byte) 0xFA, (byte) 0xA7, (byte) 0xA3, (byte) 0xD9, (byte) 0xDF, 0x6E, (byte) 0x7F, (byte) 0x8C, 0x78, (byte) 0x96, (byte) 0xB1, 0x7B, (byte) 0xA7, 0x6E, 0x01};
		byte[] salt = Bytes.random(16).array();
		BCrypt.Hasher bCrypt = BCrypt.withDefaults();
		//cost = 4 indica qual é o custo para executar o hash - max 31
		//quanto maior o custo, maior o tempo de processamento, porém maior a segurança		
		return Bytes.wrap(bCrypt.hash(4, salt, str.getBytes())).encodeUtf8();		
	}

	/**
	 * Cria um hash de uma string usando o menor custo (evitar usar em prod)
	 * @param str texto para obter o hash
	 * @param cost valor entre 4 e 31 indicando o custo de processamento para gerar o hash
	 * 			Valores maiores indicam maior segurança.
	 * 			Em casos de ataque por força bruta, custos maiores significam mais tempo de processamento por chave, 
	 * 			Necessitando mais recursos de hardware do atacante e inviabilizando bruteforce
	 * @return hash de 60 posições da string recebida
	 */
	public String getEncryptedData(String str, int cost) {
		if(cost < 4 || cost > 31) {
			System.out.println("Class DataEncryptor >> custo incorreto, usando o valor default:4");
			cost = 4;
		}		
		byte[] salt = Bytes.random(16).array();
		BCrypt.Hasher bCrypt = BCrypt.withDefaults();
		//cost = 4 indica qual é o custo para executar o hash - max 31
		//quanto maior o custo, maior o tempo de processamento, porém maior a segurança		
		return Bytes.wrap(bCrypt.hash(cost, salt, str.getBytes())).encodeUtf8();		
	}
	
	/**
	 * Verifica se o password bate com o hash
	 * @param str o texto sem criptografia
	 * @param encrypedStr o texto cryptografado
	 * @return retorna true se as informações baterem, do contrário false
	 */
	public Boolean match(String str, String encrypedStr) {
		BCrypt.Verifyer v = BCrypt.verifyer();		
		return v.verify(str.toCharArray(), encrypedStr).verified;		
	}
}