import sys
import docker
import subprocess
import os


from Helpers import docker_helper
from Helpers.message_helper import * 
#mport CodeBrothers.Infrastructure


MsgTitle("          Iniciando o ambiente CodeBrothers")

#Parando todos os containeres da máquina antes de iniciar o processo
docker_helper.stopAllContainers()

#Iniciando Infra
exec(open("codebrothers_infrastructure.py").read())

#Construindo e iniciando Auth
exec(open("codebrothers_auth.py").read())

#Construindo e iniciando Customer
exec(open("codebrothers_customer.py").read())


print(" <<<<<<<<<<<<<<<<<<<<<<    Ambiente inicializado com sucesso     >>>>>>>>>>>>>>>>>>>>>>>>")
