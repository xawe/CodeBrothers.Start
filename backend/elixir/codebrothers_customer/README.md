# CodebrothersCustomer

Crud utilizando a base CodeBrothers.

Ainda não foram feitas as implementações existentes no projeto principal

# Para atualizar as dependencias

  - mix deps.get

# Para executar o projeto

  - mix phx.server

# Para executar o projeto deixando o console em modo interativo

  - iex -S mix phx.server

# Para mostrar o observer

  - :observer.start


# Informações úteis

  - Os arquivos de configuração estão na pasta /config . o projeto está configurado para usar o config dev.exs, que é onde estão as credenciais do banco de dados e porta do serviço ( 4000 )

  - o arquivos mix.exs é o "pom" do projeto. Toda nova dependencia incluida lá deve ser obtida através do comando mix deps.get

  - /lib é onde ficarão os códigos desenvolvido para o projeto sendo 
  
    - codebrothers_customer_web referente a parte web
      
      - todo novo endpoint estará dentro de /lib/codebrothers_customer_web/controller
      
      - para que um endpoint seja exposto, será necessário inclui-lo no arquivo /lib/codebrothers_customer_web/router.ex

      - para verificar os endpoints disponíveis, basta executar o comando mix phx.routes
    
    
    - codebrothers_customer referente ao backend

      - O diretório codebrothers_customer/context possui um arquivo fazendo um mapeamento aos atributos no banco de dados. Ele é semelhante aos Entities.

    - O diretorio /lib/priv possui alguns scripts de automação, como as migrations que geram tabelas e atributos, e também podem ser criados registros de modelo usando o /lib/priv/repo/seeds.exs
    
    - para criar o registro que esta em seeds basta executar o comando mix run priv/repo/seeds.exs

  - A parte do modelo de dados, tabela/campos e controllers novos podem ser criadas automaticamente com um único comando 

     - mix phx.gen.json nomeDoContexto NovoController novatabela campos
      
      Exemplo: mix phx.gen.json Vendas PainelVendas painel_vendas nome_produto:string descricao:text tag:string

      O Comando acima criará 
      
      1. um novo contexto chamado vendas, Será uma pasta em codebrothers_customer/vendas onde será adicionado o modelo de dados

      2. codebrothers_customer/vendas/painelvendas.ex que conterá o modelo de dados descrevendo os campos nome_produto, descrição e tag. Também serão incluidos campos de data de criação e alteração. Caso deseje usar campos customizados, basta comentar a linha timestamps() no arquivo

      3. um controler em codebrothers_customer_web/controler/painelvendas_controller.ex , com as implementações padrão do crud. Novas implementações poderão ser acrescentadas lá.

      4. uma migration para a criação da tabela painel_vendas com os atributos nome_produto, descrição, tag e os de data de inclusão e modificação no caso de uso do timestamp()

      Para rodar a migration, executar o comando "mix ecto.migrate" e a tabela será criada.
      Tabém é possível adicionar novos campos na tabela e executar o mesmo comando. Em caso de erro, e possível voltar a versão com "mix ecto.rollback -n 1 

    
  
  



