import 'package:bikes_rapides/base_container.dart';
import 'package:flutter/material.dart';

class Notes extends StatefulWidget {
  @override
  NotesState createState() => new NotesState();
}

class NotesState extends State<Notes> {
  var text = "Notes";
  @override
  Widget build(BuildContext context) {
    return BaseContainer(
        child: Stack(
      children: <Widget>[
        Container(
          padding: EdgeInsets.fromLTRB(20, 20, 20, 20),
          child: Center(child: Text(text)),
        ),
        Container(
          padding: EdgeInsets.fromLTRB(0, 0, 20, 20),
          child: Align(
              alignment: Alignment.bottomRight,
              child: FloatingActionButton(
                child: Icon(Icons.add),
                onPressed: () {
                  setState(() {
                    text = text + "t";
                  });
                },
              )),
        )
      ],
    ));
  }
}
