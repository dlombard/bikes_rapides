package net.dlombard.bikes_rapides;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class BikeStations extends RealmObject {
    @PrimaryKey
    @Required
    private String station_id;
    @Required
    private String external_id;
    @Required
    private String name;
    @Required
    private String short_name;
    private double lat;
    private double lon;
    private RealmList<String> rental_methods;
    private long capacity;

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public RealmList<String> getRental_methods() {
        return rental_methods;
    }

    public void setRental_methods(RealmList<String> rental_methods) {
        this.rental_methods = rental_methods;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "BikeStations{" +
                "station_id='" + station_id + '\'' +
                ", external_id='" + external_id + '\'' +
                ", name='" + name + '\'' +
                ", short_name='" + short_name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", rental_methods=" + rental_methods +
                ", capacity=" + capacity +
                '}';
    }

    public String toJson() {
        return "{" +
                "'station_id':'" + station_id + '\'' +
                ", 'external_id':'" + external_id + '\'' +
                ", 'name':'" + name + '\'' +
                ", 'short_name':'" + short_name + '\'' +
                ", 'lat':" + lat +
                ", 'lon':" + lon +
                ", 'rental_methods':" + rental_methods +
                ", 'capacity':" + capacity +
                '}';
    }


}
