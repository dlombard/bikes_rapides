import 'package:bikes_rapides/notes.dart';
import 'package:bikes_rapides/stations.dart';
import 'package:bikes_rapides/bikes.dart';
import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  //Home({Key key}) : super(key: key);

  @override
  HomePageState createState() => new HomePageState();
}

class HomePageState extends State<Home> {
  static var notes = "Notes";
  static var bikes = "Bikes";
  static var stations = "Stations";
  var widgets = [
    {"title": notes, "child": Notes()},
    {"title": bikes, "child": Bikes()},
    {"title": stations, "child": Stations()}
  ];
  var _selectedIndex = 1;
  var _currentTitle = Text("Bikes");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          title: _currentTitle,
          elevation: 4.0,
        ),
        bottomNavigationBar: BottomNavigationBar(
          items: <BottomNavigationBarItem>[
            BottomNavigationBarItem(
                icon: Icon(Icons.home), title: Text(notes)),
            BottomNavigationBarItem(
                icon: Icon(Icons.directions_bike), title: Text(bikes)),
            BottomNavigationBarItem(
                icon: Icon(Icons.stars), title: Text(stations)),
          ],
          currentIndex: _selectedIndex,
          onTap: _onItemTapped,
        ),
        body: Center(
          child: widgets.elementAt(_selectedIndex)["child"],
        ));
  }

  void _onItemTapped(int index) {
    setState(() {
      _currentTitle = Text(widgets.elementAt(index)["title"],
          style: TextStyle(color: Colors.white));
      _selectedIndex = index;
    });
  }
}
