1. Spring Cloud Kubernetes precisa de acesso as APIs do Kubernetes para recuperar a lista endereços (IPs internos) dos pods em execução para expor em um unico serviço. O comando garante a permissão necessária para isso:

```
kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
```

2. Ajustar pemissão de acessos a recursos do Kubernetes pelo spring-cloud
#ref: https://cloud.spring.io/spring-cloud-static/spring-cloud-kubernetes/2.0.0.M1/reference/html/#service-account

```
kubectl apply -f clusterrolebinding.yml
```

3. ConfigMap - Responsável por armazenar endereços e possiveis configurações que serão injetadas no container através de variáveis de ambiente

```
kubectl apply -f codebrothers-configmap.yml
```

4. Secrets - Responsável por armazenar usuários e senhas ou informações que precisam ser criptografadas que serão injetadas no container através de variáveis de ambiente.

```
kubectl apply -f codebrothers-secrets.yml
```

5. PersistentVolume - Realiza a configuração de volume para persitir dados, evitando ao restart do POD perder informações como base de dados, tabelas e etc.

```
kubectl apply -f postgresql-storage.yml
```

6. PostgreSQL - Deploy do PostgreSQL com um POD e exposição do mesmo em services.

```
kubectl apply -f postgresql-code-brother-deploy.yml
```

7. PgAdmin - Deploy do PgAdmin com um POD e exposição do mesmo em services.

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