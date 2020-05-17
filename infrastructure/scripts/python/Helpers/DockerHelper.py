import docker
from datetime import datetime

def SubMsg(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")    
    print("")
    print(current_time + '      >> ' + str)

def RemoveContainerByName(name):
    client = docker.from_env()
    try:
        cbr = client.containers.get(name)
        try:
            cbr.stop()
        except :
            pass
        cbr.remove()
        SubMsg("Container " + name + " removido com sucesso")
    except Exception as ex:
        SubMsg("Nenhum container a ser removido com o nome " + name)
        #SubMsg(ex)

def RemoveImageByName(name):
    client = docker.from_env()
    try:
        cbr = client.images.get(name)        
        client.images.remove(name)        
        SubMsg("Imagem " + name + " removida com sucesso")
    except Exception as ex:        
        SubMsg("Nenhuma imagem a ser removida com o nome " + name)
        #SubMsg(ex)

#RemoveImageByName("codebrothers.customer")
#RemoveContainerByName("codebrothers.customer123123")