import sys
import docker
import subprocess
import os

from Helpers.MessageHelper import * 
#from Helpers import MessageHelper

currentPath = os.getcwd()
#removendo 3 niveis do path para pegar informações do backend
currentPath = os.path.dirname(os.path.dirname(os.path.dirname(currentPath)))
dockerPath = "/infrastructure/postgresql/docker-compose.yml"
logPath = "/tmp/codebrothers.infraestructure.log"
yamlPath = currentPath + dockerPath

#print(yamlPath + "- teste 123")

MsgTitle("Inicializando infraestrutura CodeBrothers")

with open(logPath, "a") as output:    
    retorno = subprocess.call('docker-compose -f '+ yamlPath+ ' up -d', shell=True, stdout=output, stderr=output)
    if(retorno == 0):
        MsgTitle("Infraestrutura Inicializada com sucesso")
    else:
        ErrorTitle("Erro ao inicializar Infraestrutura . Verifique os logs " + logPath)
