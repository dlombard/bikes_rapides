import 'package:bikes_rapides/dlo_notes_channel.dart';
import 'package:bikes_rapides/state_model.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:provider/provider.dart';

class Login extends StatefulWidget {
  @override
  LoginState createState() => new LoginState();
}

class LoginState extends State<Login> {
  final _auth = FirebaseAuth.instance;
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
                  bool r = await login();
                  await loadData();
                  if (r) {
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

  Future<bool> login() async {
    try {
      final AuthResult result = await _auth.signInWithEmailAndPassword(
          email: email, password: password);

      final FirebaseUser user = result.user;

      IdTokenResult idTokenResult = await user.getIdToken();
      String idToken = idTokenResult.token;
      Provider.of<StateModel>(context, listen: false).setUser(user);

      return await DloNotesChannel().login(idToken);
    } catch (e) {
      print(e);
      return false;
    }
  }

  Future<void> loadData() async {
    StateModel sm = new StateModel();
    sm.setBikeStations(await DloNotesChannel.getBikeStations());
    sm.setBikeStationStatus(await DloNotesChannel.getBikeStationsStatus());
  }
}
