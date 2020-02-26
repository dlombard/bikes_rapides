import 'package:flutter/material.dart';

class BaseContainer extends StatelessWidget {
  final Widget child;
  BaseContainer({this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
        decoration: BoxDecoration(
           color: Colors.white70),
        child: child);
  }
}
