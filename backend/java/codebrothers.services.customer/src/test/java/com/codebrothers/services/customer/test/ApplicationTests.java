package com.codebrothers.services.customer.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//ContextConfiguration(classes = ClassToAutowire.class)
//@AutoConfigureMockMvc
@SpringBootTest
class ApplicationTests {

	
	/***
		O Método abaixo é responsável pela conexão ao banco de dados no momento da execução dos testes
		Como não testamos acesso a dados, ele pode ser comentado.
		do contrário, será necessário incluir a seguinte linha no arquivo /etc/hosts
		127.0.0.1       postgresql-code-brother
	 */
    @Test
	void contextLoads() {
	}

}
