import sys
import docker
import subprocess
import os
import time

#sys.path.insert(0, './Helpers')
from Helpers import docker_helper
from Helpers.message_helper import * 


logPath = "/tmp/codebrothers.services.customer.log"
currentPath = os.getcwd()
#removendo 3 niveis do path para pegar informações do backend
currentPath = os.path.dirname(os.path.dirname(os.path.dirname(currentPath)))
customerPath = "/backend/java/codebrothers.services.customer"
imageName = "codebrothers.customer"
fullPath = currentPath + customerPath
port = "8081"
dependencies = ["codebrothers.auth"]


def CheckDependency(output):
    r = True
    for dep in dependencies:
        r = TryCheckContainerActive(output, dep)
        if(r == False):
            msg = "O container " + dep + " parece não estar ativo. Verifique se o mesmo está funcionado normalmente e se está registrado no Service Discovery antes de tentar novamente."            
                        
            output.writelines(msg)
            MsgNoBlock(msg)
            r = False

    return r
def TryCheckContainerActive(output, dependencia):
    tentativas = 5
    segundosBase = 3
    r = False
    for t in range(tentativas):
        tempo = segundosBase * (t+1)
        r = docker_helper.isContainerActive(dependencia)
        if(r):
            r = True
            break
        else:
            MsgNoBlock("Dependência " + dependencia + " não está ativa")
            MsgNoBlock("Tentaremos mais " + str(tentativas - t) +" vez(es). Aguarando " + str(tempo) + " segundos antes da nova tentativa") 
            time.sleep(tempo)
    return r


def BuildApp(output):        
    processo = "mvn clean install"
    Msg("Iniciando o processo " + processo)
    retorno = subprocess.call('mvn -f '+ fullPath + ' clean install', shell=True, stdout=output, stderr=output)        
    if(retorno ==0):
        MsgNoBlock("Processo | " + processo + " | finalizado com sucesso")
    return retorno

def BuildDockerImage(output):
    processo = "Build Docker Image"
    Msg("Iniciando o processo " + processo)
    MsgNoBlock("Verificando se existe um container " + imageName)
    docker_helper.RemoveContainerByName(imageName)
    docker_helper.RemoveImageByName(imageName)
    
    MsgNoBlock("Gerando nova imagem > " + imageName)

    retorno = subprocess.call('docker build -f '+ fullPath + '/Dockerfile -t '+imageName+' ' + fullPath, shell=True, stdout=output, stderr=output)        
    if(retorno == 0):
        MsgNoBlock("Imagem Docker criada com sucesso ")
    return retorno

def StartContainer(output):
    processo = "Start Docker Container"
    Msg("Iniciando o processo " + processo)
    MsgNoBlock("Container " + imageName + " : Ports 8080:8080 8081:8081  | Network : postgresql_postgres-network")
    retorno = subprocess.call('docker run -d --name '+imageName+' --network postgresql_postgres-network -p 8080:8080 -p '+port+':'+port+' '+ imageName +' ' + fullPath, shell=True, stdout=output, stderr=output, close_fds=True)        
    MsgNoBlock("Docker Container iniciado com sucesso")
    return retorno

with open(logPath, "a") as output:        
    MsgTitle("Iniciando o processo de construção do codebrothers.services.customer")
    Msg("Iniciando a validação de dependências")
    isDependencyOk = CheckDependency(output)
    
    if(isDependencyOk == True):        
        MsgNoBlock("Dependências inicializadas. Prosseguindo o processo")
        if(BuildApp(output) == 0):
            if(BuildDockerImage(output) == 0):
                StartContainer(output)            
            else:
                Msg("Ocorreu um erro no Build da imagem Docker. Veja detalhes do erro em " + logPath)
        else:
            Msg("Ocorreu um erro no Build da aplicação. Veja detalhes do erro em " + logPath)
    else:
        MsgEnd("Processo finalizado prematuramente. Ajuste os problemas e tente novamente. Log >> " + logPath)    
    MsgEnd("Workflow CodeBrother.Services.Customer finalizado")

