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
    var _chosenValue="";
    Dialog(
      backgroundColor: Colors.transparent,
      child: Container(
        child: Card(
          shape: RoundedRectangleBorder( borderRadius: BorderRadius.circular(5),),
          color: Colors.white,
          clipBehavior: Clip.antiAliasWithSaveLayer,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Container(
                padding: EdgeInsets.fromLTRB(15, 15, 15, 15),
                child: Column(
                  children: <Widget>[
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end
                      ,children: [
                        Icon(Icons.close),
                      ],
                    ),
                    Divider(height: 15,),
                    Row(
                      children:[
                        DropdownButton<String>(
                          focusColor:Colors.white,
                          value: _chosenValue="",
                          //elevation: 5,
                          style: TextStyle(color: Colors.white),
                          iconEnabledColor:Colors.black,
                          items: <String>[
                            'Android',
                            'IOS',
                            'Flutter',
                            'Node',
                            'Java',
                            'Python',
                            'PHP',
                          ].map<DropdownMenuItem<String>>((String value) {
                            return DropdownMenuItem<String>(
                              value: value,
                              child: Text(value,style:TextStyle(color:Colors.black),),
                            );
                          }).toList(),
                          hint:Text(
                            "Please choose a currency",
                            style: TextStyle(
                                color: Colors.black,
                                fontSize: 14,
                                fontWeight: FontWeight.w500),
                          ),
                          // onChanged: (String value) {
                          //   setState(() {
                          //     _chosenValue = value;
                          //   });
                          // },
                        ),
                        
                      ],
                    ),
                    Divider(height: 10,),
                    Row(

                    ),
                    Divider(height: 10,),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
    // setState(() {
    //
    // });
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
          backgroundColor: Colors.indigo,
        ),
      )
    );
  }

}