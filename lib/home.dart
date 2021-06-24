import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// ignore: camel_case_types
class home extends StatefulWidget{
  @override
  _homestate createState() => _homestate();
}
// ignore: camel_case_types
class _homestate extends State<home>{

  void add_dialog(){
    setState(() {

    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Expense Register"),
          backgroundColor: Colors.indigo,
        ),
        body: Center(),
        floatingActionButton: FloatingActionButton(
          onPressed: add_dialog,
          child: Icon(Icons.add),
        ),
      )
    );
  }

}