package net.dlombard.dlo_notes;

import io.realm.RealmObject;
import io.realm.annotations.Required;
import io.realm.annotations.PrimaryKey;

public class BikeStationStatus extends RealmObject {
    @PrimaryKey
    @Required
    private String station_id;
    private long num_bikes_available;
    private long num_ebikes_available;
    private long num_bikes_disabled;
    private long num_docks_available;
    private long num_docks_disabled;
    private long is_installed;
    private long is_renting;
    private long is_returning;
    private long last_reported;

    public String getStation_id() { return station_id; }

    public void setStation_id(String station_id) { this.station_id = station_id; }

    public long getNum_bikes_available() { return num_bikes_available; }

    public void setNum_bikes_available(long num_bikes_available) { this.num_bikes_available = num_bikes_available; }

    public long getNum_ebikes_available() { return num_ebikes_available; }

    public void setNum_ebikes_available(long num_ebikes_available) { this.num_ebikes_available = num_ebikes_available; }

    public long getNum_bikes_disabled() { return num_bikes_disabled; }

    public void setNum_bikes_disabled(long num_bikes_disabled) { this.num_bikes_disabled = num_bikes_disabled; }

    public long getNum_docks_available() { return num_docks_available; }

    public void setNum_docks_available(long num_docks_available) { this.num_docks_available = num_docks_available; }

    public long getNum_docks_disabled() { return num_docks_disabled; }

    public void setNum_docks_disabled(long num_docks_disabled) { this.num_docks_disabled = num_docks_disabled; }

    public long getIs_installed() { return is_installed; }

    public void setIs_installed(long is_installed) { this.is_installed = is_installed; }

    public long getIs_renting() { return is_renting; }

    public void setIs_renting(long is_renting) { this.is_renting = is_renting; }

    public long getIs_returning() { return is_returning; }

    public void setIs_returning(long is_returning) { this.is_returning = is_returning; }

    public long getLast_reported() { return last_reported; }

    public void setLast_reported(long last_reported) { this.last_reported = last_reported; }


    @Override
    public String toString() {
        return "BikeStationStatus{" +
                "station_id='" + station_id + '\'' +
                ", num_bikes_available=" + num_bikes_available +
                ", num_ebikes_available=" + num_ebikes_available +
                ", num_bikes_disabled=" + num_bikes_disabled +
                ", num_docks_available=" + num_docks_available +
                ", num_docks_disabled=" + num_docks_disabled +
                ", is_installed=" + is_installed +
                ", is_renting=" + is_renting +
                ", is_returning=" + is_returning +
                ", last_reported=" + last_reported +
                '}';
    }

    public String toJson() {
        return "{" +
                "station_id='" + station_id + '\'' +
                ", num_bikes_available=" + num_bikes_available +
                ", num_ebikes_available=" + num_ebikes_available +
                ", num_bikes_disabled=" + num_bikes_disabled +
                ", num_docks_available=" + num_docks_available +
                ", num_docks_disabled=" + num_docks_disabled +
                ", is_installed=" + is_installed +
                ", is_renting=" + is_renting +
                ", is_returning=" + is_returning +
                ", last_reported=" + last_reported +
                '}';
    }

}
