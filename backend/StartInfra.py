import sys
import docker
import subprocess
import os

currentPath = os.getcwd()
dockerPath = "/infrastructure/postgresql/docker-compose.yml"

yamlPath = currentPath + dockerPath

print(yamlPath + "- teste 123")

with open("/tmp/output.log", "a") as output:
    subprocess.call('docker-compose -f '+ yamlPath+ ' up', shell=True, stdout=output, stderr=output)

