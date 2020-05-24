package com.codebrothers.services.customer.infrastructure;

import com.codebrothers.services.customer.dto.User;

public interface CredentialDecoder {

	/**
	 * Recebe a string base64 recebida como Autorizacao Request ( Basic Auth ) e devolve os dados em um objeto User
	 * @param data	Dados encodados em Base64
	 * @return User object com os dados já decodificados
	 */
	public User getUserAuthorizatonData(String data);
}
