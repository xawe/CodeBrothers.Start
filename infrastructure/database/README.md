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


