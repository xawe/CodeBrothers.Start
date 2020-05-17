import sys
import docker
import subprocess
import os

from Helpers.MessageHelper import * 

logPath = "/tmp/codebrothers.services.customer.log"
currentPath = os.getcwd()
#removendo 3 niveis do path para pegar informações do backend
currentPath = os.path.dirname(os.path.dirname(os.path.dirname(currentPath)))
customerPath = "/backend/java/codebrothers.services.customer"
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
    retorno = subprocess.call('docker build -f '+ fullPath + '/Dockerfile -t codebrothers.customer ' + fullPath, shell=True, stdout=output, stderr=output)        
    if(retorno == 0):
        MsgNoBlock("Imagem Docker criada com sucesso ")
    return retorno

def StartContainer(output):
    processo = "Start Docker Container"
    Msg("Iniciando o processo " + processo)
    retorno = subprocess.call('docker run --name codebrothers.customer --network postgresql_postgres-network -p 8080:8080 -p 8081:8081 codebrothers.customer -d' + fullPath, shell=True, stdout=output, stderr=output)        
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


MsgTitle("Workflow CodeBrother.Services.Customer finalizado")        

    
