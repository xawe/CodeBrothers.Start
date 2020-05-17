from datetime import datetime

trace = "-------------------------------------------------------------------------------"


now = datetime.now()

def MsgTitle(str):
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")    
    print("        " + str + "        ")    
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")    
    print("")

def Msg(str):
    current_time = now.strftime("%H:%M:%S")
    print(trace)
    print("")
    print(current_time + ' >> ' + str)
    print("")

def MsgNoBlock(str):
    current_time = now.strftime("%H:%M:%S")    
    print("")
    print(current_time + ' >> ' + str)
    print("")
    

def SegMsg(str):
    current_time = now.strftime("%H:%M:%S")
    print(trace)
    print("")
    print(current_time + ' >> ' + str)
    print("")
    print(trace)