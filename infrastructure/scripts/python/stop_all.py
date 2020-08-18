import sys
import docker
import subprocess
import os


from Helpers import docker_helper


#Parando todos os containeres da máquina antes de iniciar o processo
docker_helper.stopAllContainers()