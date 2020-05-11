### Customer services

Micro serviço responsável pelo cadastrado do Cliente.

 - Endpoints disponíveis

 1. /api/customers/v1   GET
 2. /api/customers/v1/{id}  GET
 3. /api/customers/v1   POST
 4. /api/customers/v1/{id}  DELETE
 5. /api/customers/v1/{id}  PUT

 - Porta padrão: 8081 
 - Docker

 -- Gerando a imagem com uma tag e repository pra imagem: 

### Instruções para Container

    - Para criar uma imagem
        
        docker build -t codebrothers.customer .

    - Para rodar a imagem

        docker run --name codebrothers.customer --network postgresql_postgres-network -p 8080:8080 -p 8081:8081 codebrothers.customer -d

### Auth services

Micro serviço responsável pelo autenticação.

 - Endpoints disponíveis

 1. /api/auth/v1/login   POST
    payload
    {
    	"name":"teste",
    	"email":"teste@teste.com"
    }
 2. /api/auth/v1/validate   POST


 - Endoints disponíveis para tratar usuário

 1. /api/user/v1/findByNameEmail    POST    
 
    Consulta de usuário usando body - Alguns frameworks em versões antigas podem não ter implementado GET com passagem de dados via BODY, por isso este método

    payload
    {
        "name":"teste",
    	"email":"teste@teste.com"
    }

 2. /api/user/v1/{nome}/{email}     GET  Consulta de usuário por nome e e-mail

 3. /api/user/v1/{id}     GET  Consulta de usuário por id

 4. /api/user/v1/     GET  Lista todos os usuários

 5. /api/user/v1/    POST      Criação de usuário 

 6. /api/user/v1/{id}    DELETE  Remoção de usuário por id

 7. /api/user/v1/{id}   PUT      Atualização de usuário
    payload    
    {
        "name": "Teste update",
        "email": "up@date.com",
        "password": "12345"
    }

 - Porta padrão: 8082
 - Docker

 ### Instruções para Container

    - Para criar uma imagem
        
        docker build -t codebrothers.auth .

    - Para rodar a imagem

        docker run --name codebrothers.auth --network postgresql_postgres-network -p 8080:8080 -p 8082:8082 codebrothers.auth -d