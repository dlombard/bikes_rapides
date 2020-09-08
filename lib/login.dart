import 'package:bikes_rapides/dlo_notes_channel.dart';
import 'package:bikes_rapides/state_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Login extends StatefulWidget {
  @override
  LoginState createState() => new LoginState();
}

class LoginState extends State<Login> {
  var title = 'Login';
  var email = '';
  var password = '';
  var padding = 10.0;

  var _emailController = TextEditingController();
  var _pwdController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Container(
          padding: EdgeInsets.all(padding),
          child: Center(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              TextField(
                controller: _emailController,
                decoration: InputDecoration(
                  hintText: "Email",
                ),
                maxLines: 1,
                onChanged: (changedText) => setState(() {
                  email = changedText;
                }),
              ),
              TextField(
                controller: _pwdController,
                decoration: InputDecoration(
                  hintText: "Password",
                ),
                maxLines: 1,
                onChanged: (changedText) => setState(() {
                  password = changedText;
                }),
              ),
              RaisedButton(
                onPressed: () async {
                  String userId = await login();
                  await loadData();
                  if (userId != null) {
                    Navigator.of(context).pushReplacementNamed('/app');
                  } else {
                    return showDialog(
                        context: context,
                        builder: (_) => new AlertDialog(
                              title: new Text("Error Logging in"),
                              content: new Text(
                                  "You may be using an invalid user or password"),
                              actions: <Widget>[
                                RaisedButton(
                                    onPressed: () {
                                      clearFormFields();
                                      Navigator.pop(context);
                                    },
                                    child: Text("Ok", style: TextStyle(color: Colors.white),))
                              ],
                            ));
                  }
                },
                child: const Text('Login', style: TextStyle(color: Colors.white)),
              )
            ],
          ))),
    );
  }

  void clearFormFields() {
    _emailController.clear();
    _pwdController.clear();
  }

  Future<String> login() async {
    try {
      //Provider.of<StateModel>(context, listen: false).setUser(null);
      String u = await DloNotesChannel().emailLogin(email, password);
      return u;
    } catch (e) {
      print(e);
    }
  }

  Future<void> loadData() async {
    StateModel sm = new StateModel();
    sm.setBikeStations(await DloNotesChannel.getBikeStations());
    sm.setBikeStationStatus(await DloNotesChannel.getBikeStationsStatus());
  }
}
