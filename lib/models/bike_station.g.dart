// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'bike_station.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BikeStation _$BikeStationFromJson(Map<String, dynamic> json) {
  return BikeStation(
    json['station_id'] as String,
    json['external_id'] as String,
    json['name'] as String,
    json['short_name'] as String,
    (json['lat'] as num)?.toDouble(),
    (json['lon'] as num)?.toDouble(),
    json['capacity'] as int,
  );
}

Map<String, dynamic> _$BikeStationToJson(BikeStation instance) =>
    <String, dynamic>{
      'station_id': instance.station_id,
      'external_id': instance.external_id,
      'name': instance.name,
      'short_name': instance.short_name,
      'lat': instance.lat,
      'lon': instance.lon,
      'capacity': instance.capacity,
    };
