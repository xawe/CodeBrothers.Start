import sys
import docker
import subprocess
import os

#sys.path.insert(0, './Helpers')
from Helpers import DockerHelper
from Helpers.MessageHelper import * 


logPath = "/tmp/codebrothers.services.auth.log"
currentPath = os.getcwd()
#removendo 3 niveis do path para pegar informações do backend
currentPath = os.path.dirname(os.path.dirname(os.path.dirname(currentPath)))
customerPath = "/backend/java/codebrothers.services.auth"
imageName = "codebrothers.auth"
port = "8082"
fullPath = currentPath + customerPath


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
    DockerHelper.RemoveContainerByName(imageName)
    DockerHelper.RemoveImageByName(imageName)
    
    MsgNoBlock("Gerando nova imagem > " + imageName)

    retorno = subprocess.call('docker build -f '+ fullPath + '/Dockerfile -t '+imageName+' ' + fullPath, shell=True, stdout=output, stderr=output)        
    if(retorno == 0):
        MsgNoBlock("Imagem Docker criada com sucesso ")
    return retorno

def StartContainer(output):    
    processo = "Start Docker Container"
    Msg("Iniciando o processo " + processo)
    MsgNoBlock("Container " + imageName + " : Ports 8082:8082  | Network : postgresql_postgres-network")
    retorno = subprocess.call('docker run -d --name '+imageName+' --network postgresql_postgres-network -p '+port+':'+port+' '+ imageName +' ' + fullPath, shell=True, stdout=output, stderr=output, close_fds=True)        
    MsgNoBlock("Docker Container iniciado com sucesso")
    return retorno

MsgTitle("Iniciando o processo de construção do codebrothers.services.customer")

with open(logPath, "a") as output:        
    if(BuildApp(output) == 0):
        if(BuildDockerImage(output) == 0):
            StartContainer(output)            
        else:
            Msg("Ocorreu um erro no Build da imagem Docker. Veja detalhes do erro em " + logPath)
    else:
        Msg("Ocorreu um erro no Build da aplicação. Veja detalhes do erro em " + logPath)


MsgEnd("Workflow CodeBrother.Services.Auth finalizado")

    
