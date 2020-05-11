package com.codebrothers.services.security;

/**
 * Classe responsável por criptografar (na verdade obter um hash ) da string  
 *
 */
public interface DataEncryptor {

	/**
	 * Cria um hash de uma string usando o menor custo (evitar usar em prod)
	 * @param str texto para obter o hash
	 * @return hash de 60 posições da string recebida
	 */
	String getEncryptedData(String str);
	
	/**
	 * Cria um hash de uma string usando o menor custo (evitar usar em prod)
	 * @param str texto para obter o hash
	 * @param cost valor entre 4 e 31 indicando o custo de processamento para gerar o hash
	 * 			Valores maiores indicam maior segurança.
	 * 			Em casos de ataque por força bruta, custos maiores significam mais tempo de processamento por chave, 
	 * 			Necessitando mais recursos de hardware do atacante e inviabilizando bruteforce
	 * @return hash de 60 posições da string recebida
	 */
	String getEncryptedData(String str, int cost);
	
	/**
	 * Verifica se o password bate com o hash
	 * @param str o texto sem criptografia
	 * @param encrypedStr o texto cryptografado
	 * @return retorna true se as informações baterem, do contrário false
	 */
	Boolean match(String str, String encrypedStr);
}
