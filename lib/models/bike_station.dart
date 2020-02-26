import 'package:json_annotation/json_annotation.dart';
part 'bike_station.g.dart';

@JsonSerializable()
class BikeStation {
  String station_id;
  String external_id;
  String name;
  String short_name;
  double lat;
  double lon;
  @JsonKey(ignore: true)
  List<String> rental_methods;
  int capacity;

  BikeStation(this.station_id, this.external_id, this.name, this.short_name,
      this.lat, this.lon, this.capacity);

  factory BikeStation.fromJson(Map<String, dynamic> json) =>
      _$BikeStationFromJson(json);

  Map<String, dynamic> toJson() => _$BikeStationToJson(this);
}
