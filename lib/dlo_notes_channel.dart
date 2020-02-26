import 'dart:convert';

import 'package:bikes_rapides/models/bike_station.dart';
import 'package:bikes_rapides/models/bike_station_status.dart';
import 'package:bikes_rapides/state_model.dart';
import 'package:flutter/services.dart';

class DloNotesChannel {
  static const platform = const MethodChannel('net.dlombard/dlo_notes');
  static const eChannel = const EventChannel('net.dlombard/streams');
  static Stream<String> bStream;

  static Stream<String> get bikeStationStream {
    bStream ??= eChannel.receiveBroadcastStream();
    return bStream;
  }

  static StateModel s = new StateModel();
  DloNotesChannel() {
    // platform.setMethodCallHandler(this._handleMessage);
  }

  static List<BikeStation> toBikeStationList(List<dynamic> bs) {
    var items = List<BikeStation>();
    bs.forEach((b) {
      Map<String, dynamic> json = jsonDecode(b.toString());
      BikeStation _ = BikeStation.fromJson(json);
      items.add(_);
    });
    return items;
  }

  static List<BikeStationStatus> toBikeStationStatusList(List<dynamic> bss) {
    var items = List<BikeStationStatus>();
    bss.forEach((b) {
      Map<String, dynamic> json = jsonDecode(b.toString());

      BikeStationStatus _ = BikeStationStatus.fromJson(json);

      items.add(_);
    });
    return items;
  }

  MethodChannel getPlatform() {
    return platform;
  }

  Future<List<dynamic>> getMeds(String searchString) async {
    try {
      final List<dynamic> docs = await platform.invokeMethod(
          "GET_MEDS", <String, dynamic>{'searchString': searchString});
      print(docs.toString());
      return docs;
    } catch (e) {
      throw e.toString();
    }
  }

  Future<bool> login(String idToken) async {
    try {
      bool isDoctor = await platform
          .invokeMethod('LOGIN', <String, dynamic>{'idToken': idToken});

      print(isDoctor);
      return isDoctor;
    } catch (e) {
      print(e.toString());
    }
  }

  Future<void> logout() async {
    try {
      await platform.invokeMethod('LOGOUT');
    } catch (e) {
      print(e.toString());
    }
  }

  static Future<List<BikeStation>> getBikeStations() async {
    try {
      List<dynamic> bs = await platform.invokeMethod('GET_BIKE_STATIONS');
      return toBikeStationList(bs);
    } catch (e) {
      print(e.toString());
    }
  }

  static Future<List<BikeStationStatus>> getBikeStationsStatus() async {
    try {
      List<dynamic> bs =
          await platform.invokeMethod('GET_BIKE_STATIONS_STATUS');
      return toBikeStationStatusList(bs);
    } catch (e) {
      print(e.toString());
    }
  }

  static Future<void> addReservation(BikeStation b) async {
    try {
      await platform.invokeMethod('ADD_RESERVATION',
          <String, dynamic>{'station': b.toJson().toString()});
    } catch (e) {
      print(e.toString());
    }
  }
}
