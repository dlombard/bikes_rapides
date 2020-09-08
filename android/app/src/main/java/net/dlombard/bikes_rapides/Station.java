package net.dlombard.bikes_rapides;

import org.bson.types.ObjectId;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Station extends RealmObject {

    @PrimaryKey
    private ObjectId _id;
    private String _partitionKey;
    private String address;
    private Double capacity;
    private Date created_at;
    private Boolean eightd_has_key_dispenser;
    private RealmList<StationEightdStationServices> eightd_station_services;
    private Boolean electric_bike_surcharge_waiver;
    private String external_id;
    private Boolean has_kiosk;
    private Long last_updated;
    private Double lat;
    private String legacy_id;
    private StationLocation location;
    private Double lon;
    private String name;
    private ServiceProvider provider_id;
    private String region_id;
    private RealmList<String> rental_methods;
    private StationRentalsUris rental_uris;
    private String rental_url;
    private String short_name;
    private String station_id;
    private String station_type;
    private StationStatus status;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String get_partitionKey() { return _partitionKey; }
    public void set_partitionKey(String _partitionKey) { this._partitionKey = _partitionKey; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Double getCapacity() { return capacity; }
    public void setCapacity(Double capacity) { this.capacity = capacity; }
    public Date getCreated_at() { return created_at; }
    public void setCreated_at(Date created_at) { this.created_at = created_at; }
    public Boolean getEightd_has_key_dispenser() { return eightd_has_key_dispenser; }
    public void setEightd_has_key_dispenser(Boolean eightd_has_key_dispenser) { this.eightd_has_key_dispenser = eightd_has_key_dispenser; }
    public RealmList<StationEightdStationServices> getEightd_station_services() { return eightd_station_services; }
    public void setEightd_station_services(RealmList<StationEightdStationServices> eightd_station_services) { this.eightd_station_services = eightd_station_services; }
    public Boolean getElectric_bike_surcharge_waiver() { return electric_bike_surcharge_waiver; }
    public void setElectric_bike_surcharge_waiver(Boolean electric_bike_surcharge_waiver) { this.electric_bike_surcharge_waiver = electric_bike_surcharge_waiver; }
    public String getExternal_id() { return external_id; }
    public void setExternal_id(String external_id) { this.external_id = external_id; }
    public Boolean getHas_kiosk() { return has_kiosk; }
    public void setHas_kiosk(Boolean has_kiosk) { this.has_kiosk = has_kiosk; }
    public Long getLast_updated() { return last_updated; }
    public void setLast_updated(Long last_updated) { this.last_updated = last_updated; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public String getLegacy_id() { return legacy_id; }
    public void setLegacy_id(String legacy_id) { this.legacy_id = legacy_id; }
    public StationLocation getLocation() { return location; }
    public void setLocation(StationLocation location) { this.location = location; }
    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ServiceProvider getProvider_id() { return provider_id; }
    public void setProvider_id(ServiceProvider provider_id) { this.provider_id = provider_id; }
    public String getRegion_id() { return region_id; }
    public void setRegion_id(String region_id) { this.region_id = region_id; }
    public RealmList<String> getRental_methods() { return rental_methods; }
    public void setRental_methods(RealmList<String> rental_methods) { this.rental_methods = rental_methods; }
    public StationRentalsUris getRental_uris() { return rental_uris; }
    public void setRental_uris(StationRentalsUris rental_uris) { this.rental_uris = rental_uris; }
    public String getRental_url() { return rental_url; }
    public void setRental_url(String rental_url) { this.rental_url = rental_url; }
    public String getShort_name() { return short_name; }
    public void setShort_name(String short_name) { this.short_name = short_name; }
    public String getStation_id() { return station_id; }
    public void setStation_id(String station_id) { this.station_id = station_id; }
    public String getStation_type() { return station_type; }
    public void setStation_type(String station_type) { this.station_type = station_type; }
    public StationStatus getStatus() { return status; }
    public void setStatus(StationStatus status) { this.status = status; }
}
