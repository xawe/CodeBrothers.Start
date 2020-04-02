# Usando o PostgresSql -

Adicionado docker-compose para utilização do Postgresql

A imagem utilizada é a bitnami/postgresql.

    - docker pull bitnami/postgresql

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
