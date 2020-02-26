import 'package:bikes_rapides/dlo_notes_channel.dart';
import 'package:bikes_rapides/models/bike_station.dart';
import 'package:flutter/material.dart';

class Stations extends StatefulWidget {
  @override
  StationsState createState() => new StationsState();
}

class StationsState extends State<Stations> {
  final _biggerFont = TextStyle(fontSize: 18.0);

  var stations = new Map<String, BikeStation>();
  var bs = List<BikeStation>();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: getStations(),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          if (snapshot.hasData) {
            if (snapshot.data != null && snapshot.data.length > 0) {
              print('snapshot has data');
              return Container(
                  decoration: BoxDecoration(color: Colors.white),
                  child: _buildList(snapshot));
            }
          }
          if (snapshot.hasError) {
            print(snapshot.error.toString());
            return Text("No Network");
          } else {
            print('snapshot has no data');
            return Container(
                decoration:
                    BoxDecoration(color: Color.fromARGB(255, 249, 249, 249)),
                child: Center(
                    child: CircularProgressIndicator(
                  backgroundColor: Colors.white,
                )));
          }
        });
  }

  Widget _buildList(AsyncSnapshot snapshot) {
    return ListView.separated(
      padding: const EdgeInsets.fromLTRB(5, 10, 5, 0),
      itemCount: snapshot.data.length,
      itemBuilder: (context, i) {
        return _buildRow(snapshot.data.elementAt(i));
      },
      separatorBuilder: (context, i) {
        return Divider(color: Colors.blue, height: 5.0);
      },
    );
  }

  Widget _buildRow(BikeStation bss) {
    return Container(
        color: Colors.white,
        child: Padding(
            padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
            child: ListTile(
              title: Text(bss.name,
                  style: _biggerFont.copyWith(
                      color: Color.fromARGB(255, 65, 67, 68))),
              subtitle: Text(bss.station_id),
              onTap: () {},
            )));
  }

  Future<List<BikeStation>> getStations() async {
    print('getting stations');
    if (bs.isEmpty) {
      var _ = await DloNotesChannel.getBikeStations();

      setState(() {
        bs = _;
      });

      return _;
    }
    return bs;
  }
}
