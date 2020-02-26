// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'bike_station_status.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BikeStationStatus _$BikeStationStatusFromJson(Map<String, dynamic> json) {
  return BikeStationStatus(
    json['station_id'] as String,
    json['num_bikes_available'] as int,
    json['num_bikes_disabled'] as int,
    json['num_docks_available'] as int,
    json['num_docks_disabled'] as int,
    json['num_ebikes_available'] as int,
    json['is_installed'] as int,
    json['is_renting'] as int,
    json['is_returning'] as int,
    json['last_reported'] as int,
  );
}

Map<String, dynamic> _$BikeStationStatusToJson(BikeStationStatus instance) =>
    <String, dynamic>{
      'station_id': instance.station_id,
      'num_bikes_available': instance.num_bikes_available,
      'num_ebikes_available': instance.num_ebikes_available,
      'num_bikes_disabled': instance.num_bikes_disabled,
      'num_docks_available': instance.num_docks_available,
      'num_docks_disabled': instance.num_docks_disabled,
      'is_installed': instance.is_installed,
      'is_renting': instance.is_renting,
      'is_returning': instance.is_returning,
      'last_reported': instance.last_reported,
    };
