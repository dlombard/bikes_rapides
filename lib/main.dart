import 'package:bikes_rapides/home.dart';
import 'package:bikes_rapides/login.dart';
import 'package:bikes_rapides/state_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() => runApp(ChangeNotifierProvider(
      create: (context) => StateModel(),
      child: MyApp(),
    ));

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Bikes Rapide',
        theme: ThemeData(
          buttonTheme: ButtonThemeData(
            buttonColor: Colors.teal,
          ),
          primarySwatch: Colors.teal,
        ),
        initialRoute: '/',
        routes: {
          '/': (context) => Login(),
          '/app': (context) => Home(),
        });
  }
}
