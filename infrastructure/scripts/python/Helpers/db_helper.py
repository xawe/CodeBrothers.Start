import psycopg2
from datetime import datetime

database_name= 'CodeBrothers_Customer2'

def SubMsg(str):
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")    
    print("")
    print(current_time + '      >> ' + str)

def createDataBase(con):    
    cursor          = con.cursor();
    try:
        sqlCreateDatabase = "create database "+database_name+";"
        cursor.execute(sqlCreateDatabase);
        SubMsg("Banco criado com sucesso >> " + database_name)
    except:
        SubMsg("Não foi possível criar o banco de dados.")

def getConnection():
    connection = None
    try:
        connection = psycopg2.connect("user='postgres' host='postgresql-code-brother' password='CodeBrother@2020' port='5432'")
    except :
        SubMsg('Não foi possível conectar na base de dados')        
        SubMsg('Verifique se o banco de dados está ativo e se as credenciais estão corretas')
    
    return connection

def checkDataBase():
    connection = getConnection()
    try:
        connection = psycopg2.connect("user='postgres' host='postgresql-code-brother' password='CodeBrother@2020' port='5432'")

        
    except :
        SubMsg('Não foi possível conectar na base de dados')        
        SubMsg('Verifique se o banco de dados está ativo e se as credenciais estão corretas')
        return 'invalid-connection'

    if connection is not None:
        connection.autocommit = True
        cur = connection.cursor()
        cur.execute("SELECT datname FROM pg_database;")
        list_database = cur.fetchall()
        database_name = 'CodeBrothers_Customer2'
        if (database_name,) in list_database:
            SubMsg('O banco ' + database_name + " foi encontrado. Prosseguindo o fluxo")
            #print("'{}' Database already exist".format(database_name))
        else:
            SubMsg('O banco ' + database_name + " Não existe. Criando um novo banco de dados")
            createDataBase(connection)
        connection.close()        

#checkDataBase()