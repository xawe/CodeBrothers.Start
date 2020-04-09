### Customer services

Micro serviço responsável pelo cadastrado do Cliente.

 - Endpoints disponíveis

 1. /api/customers/v1   GET
 
 2. /api/customers/v1/{id}  GET

 3. /api/customers/v1   POST

 4. /api/customers/v1/{id}  DELETE

 5. /api/customers/v1/{id}  PUT

 - Porta padrão: 8081


### Instruções para Container

    - Para criar uma imagem
        
        docker build -t codebrothers-customer:tag .

    - Para rodar a imagem

        docker run --name codebrothers.customer --network postgresql_postgres-network -p 8080:8080 -p 8081:8081 -p 5432 codebrothers-customer -d


