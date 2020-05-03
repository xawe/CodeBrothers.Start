import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;

void main() {
  FlutterError.onError = (FlutterErrorDetails details) {
    FlutterError.dumpErrorToConsole(details);
    if (kReleaseMode)
      exit(1);
  };
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Retrieve Text Input',
      home: MyCustomForm(),
    );
  }
}

// Define a custom Form widget.
class MyCustomForm extends StatefulWidget {
  @override
  _MyCustomFormState createState() => _MyCustomFormState();
}

String address ;
// Define a corresponding State class.
// This class holds the data related to the Form.
class _MyCustomFormState extends State<MyCustomForm> {
  // Create a text controller and use it to retrieve the current value
  // of the TextField.
  final addressController = TextEditingController();
  final userControler = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    addressController.dispose();
    userControler.dispose();    
    super.dispose();
  }

  Future<Customer> futureCustomer;
  //String address ;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('CodeBrothers Tag System'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),        
        child: Row(
          children: <Widget>[
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  TextField(
                      controller: addressController,          
                      decoration: InputDecoration(
                      hintText: "Digite o endereço de conexão."
                    ),
                  ),
                  TextField(
                    controller: userControler,
                    decoration: InputDecoration(
                      hintText: 'Digie o usuário'
                    ),

                  )
                ],
              ),
            ),
          ],
        ),
        // child: TextField(
        //   controller: myController,          
        //   decoration: InputDecoration(
        //     hintText: "Digite o endereço de conexão."
        //   ),
        // ),
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () {
          //address = myController.text;
          futureCustomer = fetchCustomer(addressController.text, userControler.text);                    
        },
        tooltip: 'Digite o endereço de conexão.',
        child: Icon(Icons.cast_connected),
      ),
    );
  }  

  void _showDialog(msg) {
    // flutter defined function
    showDialog(
      context: context,
      builder: (BuildContext context) {
        // return object of type Dialog
        return AlertDialog(
          title: new Text("Aviso do sistema"),
          content: new Text(msg),
          actions: <Widget>[
            // usually buttons at the bottom of the dialog
            new FlatButton(
              child: new Text("Fechar"),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  Future<Customer> fetchCustomer(address, user) async{  
  String defaultAddres = 'http://localhost:8081';  
  if(address != null){
    defaultAddres = address;
  }  
  String service = '/api/customers/v1/1';
  final endpoint = defaultAddres + service;
  final response = await http.get(endpoint);

  if(response.statusCode == 200){   
      _showDialog("Usuário conectado com sucesso");             
      return Customer.fromJson(json.decode(response.body));    
    }else{
      _showDialog("Ocorreu um erro. Verifique os dados do usuário");             
      throw Exception('Falha ao obter dados do Customer');
    }
  }
}

class Customer{
  final int id;
  final String nome;
  final String sobreNome;

  Customer({this.id, this.nome, this.sobreNome});

  factory Customer.fromJson(Map<String, dynamic> json){
    Customer cust = Customer(
      id: json['id'],
      nome: json['nome'],
      sobreNome: json['sobrenome'],
    );
    return cust;
  }
}




