import 'package:json_annotation/json_annotation.dart';
part 'bike_station_status.g.dart';

@JsonSerializable()
class BikeStationStatus{
  String station_id;
  int num_bikes_available;
  int num_ebikes_available;
  int num_bikes_disabled;
  int num_docks_available;
  int num_docks_disabled;
  int is_installed;
  int is_renting;
  int is_returning;
  int last_reported;

  BikeStationStatus(
      this.station_id,
      this.num_bikes_available,
      this.num_bikes_disabled,
      this.num_docks_available,
      this.num_docks_disabled,
      this.num_ebikes_available,
      this.is_installed,
      this.is_renting,
      this.is_returning,
      this.last_reported);

  factory BikeStationStatus.fromJson(Map<String, dynamic> json) =>
      _$BikeStationStatusFromJson(json);

  Map<String, dynamic> toJson() => _$BikeStationStatusToJson(this);
}
