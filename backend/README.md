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

 - Porta padrão: 8082
 - Docker

 ### Instruções para Container

    - Para criar uma imagem
        
        docker build -t codebrothers.auth .

    - Para rodar a imagem

        docker run --name codebrothers.auth --network postgresql_postgres-network -p 8080:8080 -p 8082:8082 codebrothers.auth -d