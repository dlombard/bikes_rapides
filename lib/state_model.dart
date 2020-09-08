import 'dart:convert';

import 'package:bikes_rapides/models/bike_station.dart';
import 'package:bikes_rapides/models/bike_station_status.dart';
import 'package:flutter/material.dart';

class StateModel extends ChangeNotifier {
 // FirebaseUser _user;

  List<BikeStation> _bikeStations;
  List<BikeStationStatus> _bikeStationStatus;

  String deviceId;

  StateModel() {
    init();
  }

  void init() {}

  //FirebaseUser get user => _user;
  List<BikeStation> get bikeStations => _bikeStations;
  List<BikeStationStatus> get bikeStationStatus => _bikeStationStatus;

  /*void setUser(FirebaseUser user) {
    this._user = user;
    notifyListeners();
  }*/

  void setBikeStations(List<BikeStation> bs) {
    if (bs != null) {
      this._bikeStations = bs;
      //notifyListeners();
      print(bs.length);
    }
  }

  void setBikeStationStatus(List<BikeStationStatus> bss) {
    if (bss != null) {
      this._bikeStationStatus = bss;
      // notifyListeners();
      print(bss.length);
    }
  }

  void addToBikeStations(BikeStation bs) {
    this._bikeStations.add(bs);
    //notifyListeners();
    print(this._bikeStations.length);
  }

  void addToBikeStationStatus(BikeStationStatus bss) {
    this._bikeStationStatus.add(bss);
    //notifyListeners();
    print(this._bikeStationStatus.length);
  }
}
