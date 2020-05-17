## Definições de infra estrutura.

Aqui serão armazenadas as definições de infra estrutura.

# Usando o PostgresSql -

Adicionado docker-compose para utilização do Postgresql

A imagem utilizada é a oficial postgres, customizada para o repositorio codebrother.

    - docker pull postgres

Validar se necessário usar a imagem oficial do postgres


# Instruções para execução

Para executar as configurações descritas no arquivo docker-compose.yml

--- 

    docker-compose up -d

---

Para executar o psql via console

---

    docker exec -it id_do_container psql psql -U postgres

---
---

Alguns comandos uteis do psql

    - \l                        --- lista os bancos de dados

    - \c nome_do_banco          --- conecta no banco de dados

    - \dt                       --- lista as tabelas do banco conectado

## 🎈 pgAdmin 4

O pgAdmin é uma plataforma opensource de administração e desenvolvimento para PostgreSQL e seus sistemas de gerenciamento de banco de dados relacionados. Escrito em Python e jQuery, ele suporta todos os recursos encontrados no PostgreSQL. Você pode utilizar o pgAdmin para fazer tudo, desde escrever consultas SQL básicas a monitorar seus bancos de dados e configurar arquiteturas de banco de dados avançadas.

Para ter acesso ao pgAdmin:

---

    http://127.0.0.1:16543/

    User: codebrother@codebrother.com.br

    Pass: CodeBrother@2020

---

## ⛏️ Construindo uma imagem e enviado ao dockerhub

Essa parte é um resumo dos comandos necessários para se enviar uma imagem ao dockerhub.

1. Baixando a imagem base 'docker pull <nome_imagem:tag>':

```
docker pull nginx:alpine
```

2. Gerando sua imagem 'docker tag <imagem_origem:tag> <nome_repo_dockerhub>/<imagem_destino:tag>':

```
docker tag nginx:alpine codebrothers/nginx-codebrothers:latest
```

3. Efetuar login no docker 'docker login -u<usuario> -p<senha>'

```
docker login -u usuario -p senha
```

4. Enviando ao dockerhub 'docker push <nome_repo_dockerhub>/<imagem_destino:tag>':

```
docker push codebrothers/nginx-codebrothers:latest
```

5. Modificando a Tag (versão) da imagem 'docker tag <nome_repo_dockerhub>/<imagem_destino:tag> <nome_repo_dockerhub>/<imagem_destino:nova-tag>':

```
docker tag codebrothers/nginx-codebrothers:latest codebrothers/nginx-codebrothers:v1
```

6. Execute o comando do item 4 para enviar a nova versão ao dockerhub, lembre-se de mudar a tag de versão.

```
docker push codebrothers/nginx-codebrothers:v1
```


# RabbitMQ
Adicionado para fazer a função de message brokers, sendo responsável pela comunicação assincrona dos micros serviços


# Instruções para execução

Para executar as configurações descritas no arquivo docker-compose.yml

    - docker-compose up

Para entrar na administração do RabbitMQ

    - http://localhost:15672

    - user: guest
    - pass: guest


# Instruções Script Python

Incluído scripts para Inicialização de infraestrutura, compilação e geração de imagens.

Os Scripts podem ser combinados para execução de pipeline completa do projeto ( WIP )

    - StartInfra.py
    
        Inicializa os containeres do postgre, pgadmin e rabbitmq. Na pratica, executa o compose localizado em /infrastructure/postgre

    - CodeBrothers.Customer.py
    
        Compila o microserviço de Customer
        Verifica se há um container ativo com o nome de codebrothers.customer e o para
        Remove container e imagem com o nome de codebrothers.customer caso exista
        Cria uma nova imagem com o nome codebrothers.customer
        Sobe um novo container com a imagem recem criada.
        
        Não foi includa a funcionalidade para envio ao HUB.