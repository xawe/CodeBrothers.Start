### Kubernets

Os passos abaixo realizam a configuração das aplicações em POD dentro do Kubernetes, especificamente microk8s, um ponto de atenção, ter as imagens para os deploys. A forma de fácil de realizar a tarefa é atravás dos comandos abaixo.

1. Realizar o build da imagem com o docker

```
docker build -t codebrothers-services-customer:latest .
```

    Salvar a imagem em formator ".tar" localmente (fora da gestão do docker) 

```
docker save codebrothers-services-customer:latest > codebrothers-services-customer.tar
```

    Edite o arquivo "/etc/hosts" dentro do wsl e o arquivo 
    "c:\Windows\System32\drivers\etc\hosts" do windows, adicione as linhas abaixo:

```
127.0.0.1   codebrothers-services.com
127.0.0.1   postgresql-code-brother
127.0.0.1   rabbitmq-code-brother
``` 

2. Importar a imagem para o microk8s

```
microk8s ctr image import codebrothers-services-customer.tar
```

3. ConfigMap - Responsável por armazenar endereços e possiveis configurações que serão injetadas no container através de variáveis de ambiente

```
kubectl apply -f codebrothers-configmap.yml
```

4. Secrets - Responsável por armazenar usuários e senhas ou informações que precisam ser criptografadas que serão injetadas no container através de variáveis de ambiente. Para gerar os dados criptografados executar o comando abaixo no bash do linux, copiar e colar no arquivo "codebrothers-secrets.yml" caso queira mudar o valor existente 

```
echo VALOR_A_SER_CRIPTOGRAFADO | base64
```

Criar o secrets no kubernets

```
kubectl apply -f codebrothers-secrets.yml
```

5. PersistentVolume - Realiza a configuração de volume para persitir dados, evitando ao restart do POD perder informações como base de dados, tabelas e etc.

```
kubectl apply -f postgresql-storage.yml
```

6. PostgreSQL - Deploy do PostgreSQL com um POD e exposição do mesmo em services. OBSERVAÇÃO, foi executado o "docker-compose.yml" existente em "../postgresql/docker-compose.yml" para setar alguns valores/parametros da imagem

```
kubectl apply -f postgresql-code-brother-deploy.yml
```

7. PgAdmin - Deploy do PgAdmin com um POD e exposição do mesmo em services. OBSERVAÇÃO, foi executado o "docker-compose.yml" existente em "../postgresql/docker-compose.yml" para setar alguns valores/parametros da imagem

```
kubectl apply -f pgadmin-code-brother-deploy.yml
```

8. Ingress - Responsável por dar acesso externo a serviços no cluster, geralmente para serviços HTTP. Gerar exposição dos serviços de autenticação e customer

```
kubectl apply -f codebrothers-services-ingress.yml
```

9. CodeBrothers-Services-Auth - Deploy do microserviço, com dois PODs e exposição dos mesmos em services. 

```
kubectl apply -f codebrothers-services-auth-deploy.yml
```

10. CodeBrothers-Services-Customer - Deploy do microserviço, com dois PODs e exposição dos mesmos em services. 

```
kubectl apply -f codebrothers-services-customer-deploy.yml
```

11. Validado deploys

    Deploys:

    ```
    kubectl get deployments
    ```

    Services:

    ```
    kubectl get services
    ```

    Ingress:

    ```
    kubectl get ingress
    ```

    Describe do Ingress

    ```
    kubectl describe deployments
    ```