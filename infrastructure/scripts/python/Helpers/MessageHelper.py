from datetime import datetime

trace = "-------------------------------------------------------------------------------"



def MsgTitle(str):
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")    
    print("        " + str + "        ")    
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")
    print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||")    
    print("")

def ErrorTitle(str):
    print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
    print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")    
    print("        " + str + "        ")    
    print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
    print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")    
    print("")

def Msg(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")
    print(trace)
    print("")
    print(current_time + ' >> ' + str)
    print("")

def MsgNoBlock(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")    
    print("")
    print(current_time + ' >> ' + str)
    print("")

def SubMsg(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")    
    print("")
    print("  " + current_time + ' >> ' + str)

    

def SegMsg(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")
    print(trace)
    print("")
    print(current_time + ' >> ' + str)
    print("")
    print(trace)