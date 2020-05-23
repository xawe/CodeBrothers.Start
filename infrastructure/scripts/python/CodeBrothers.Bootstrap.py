import sys
import docker
import subprocess
import os


from Helpers import DockerHelper
from Helpers.MessageHelper import * 
#mport CodeBrothers.Infrastructure


MsgTitle("          Iniciando o ambiente CodeBrothers")

#Parando todos os containeres da máquina antes de iniciar o processo
DockerHelper.stopAllContainers()

#Iniciando Infra
exec(open("CodeBrothers.Infrastructure.py").read())

#Construindo e iniciando Customer
exec(open("CodeBrothers.Customer.py").read())

#Construindo e iniciando Auth
exec(open("CodeBrothers.Auth.py").read())


print(" <<<<<<<<<<<<<<<<<<<<<<    Ambiente inicializado com sucesso     >>>>>>>>>>>>>>>>>>>>>>>>")
