import 'dart:async';

import 'package:bikes_rapides/base_container.dart';
import 'package:bikes_rapides/dlo_notes_channel.dart';
import 'package:bikes_rapides/models/bike_station.dart';
import 'package:bikes_rapides/models/bike_station_status.dart';
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class Bikes extends StatefulWidget {
  @override
  BikesState createState() => new BikesState();
}

class BikesState extends State<Bikes> {
  var markers = Set<Marker>();
  var mInfo = Map<String, BikeStation>();
  var stationInfo = Map<String, BikeStationStatus>();

  var _controller = Completer();

  static final CameraPosition _kGooglePlex = CameraPosition(
    target: LatLng(41.881832, -87.623177),
    zoom: 13.4746,
  );

  //setMarkers();
  @override
  Widget build(BuildContext context) {
    setMarkers();
    return BaseContainer(
        child: Stack(
      children: <Widget>[
        GoogleMap(
          mapType: MapType.normal,
          initialCameraPosition: _kGooglePlex,
          onMapCreated: (GoogleMapController controller) {
            _controller.complete(controller);
          },
          markers: markers,
        )
      ],
    ));
  }

  Future<void> _goToLocation(MarkerId id, LatLng ll) async {
    print("New Location");
    return showDialog(
        context: context,
        builder: (_) => new AlertDialog(
              elevation: 24.0,
              title: new Text(mInfo[id.value].name),
              content: SingleChildScrollView(
                child: ListBody(
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Text(
                          "Capacity: ",
                          style: TextStyle(
                              fontSize: 16, fontWeight: FontWeight.bold),
                        ),
                        Text(mInfo[id.value].capacity.toString()),
                      ],
                    ),
                    Row(
                      children: <Widget>[
                        Text(
                          "Bikes Available: ",
                          style: TextStyle(
                              fontSize: 16, fontWeight: FontWeight.bold),
                        ),
                        Text(stationInfo[id.value]
                            .num_bikes_available
                            .toString()),
                      ],
                    )
                  ],
                ),
              ),
              actions: <Widget>[
                RaisedButton(
                    onPressed: () {
                      DloNotesChannel.addReservation(mInfo[id.value]);
                    },
                    child: Text("Book"))
              ],
            ));
  }

  Future<void> setMarkers() async {
    if (markers.length < 1) {
      List<BikeStation> bs = await DloNotesChannel.getBikeStations();
      List<BikeStationStatus> statuses =
          await DloNotesChannel.getBikeStationsStatus();

      Set<Marker> _ = Set<Marker>();
      Map mI = Map<String, BikeStation>();
      Map sI = Map<String, BikeStationStatus>();

      bs.forEach((station) {
        mI[station.station_id] = station;
        var lat = station.lat;
        var lon = station.lon;
        var id = station.station_id;

        _.add(new Marker(
            position: LatLng(lat, lon),
            markerId: MarkerId(id),
            onTap: () => _goToLocation(MarkerId(id), LatLng(lat, lon))));
      });

      statuses.forEach((e) {
        sI[e.station_id] = e;
      });

      setState(() {
        markers = _;
        mInfo = mI;
        stationInfo = sI;
      });
    }
  }
}
