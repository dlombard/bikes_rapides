package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="station_status")
public class StationStatus extends RealmObject {

    private Boolean eightd_has_available_keys;
    private Double is_installed;
    private Double is_renting;
    private Double is_returning;
    private Double last_reported;
    private String legacy_id;
    private Double num_bikes_available;
    private StationStatusNumBikesAvailableTypes num_bikes_available_types;
    private Double num_bikes_disabled;
    private Double num_docks_available;
    private Double num_docks_disabled;
    private Double num_ebikes_available;
    private String station_id;
    private String station_status;

    // Standard getters & setters
    public Boolean getEightd_has_available_keys() { return eightd_has_available_keys; }
    public void setEightd_has_available_keys(Boolean eightd_has_available_keys) { this.eightd_has_available_keys = eightd_has_available_keys; }
    public Double getIs_installed() { return is_installed; }
    public void setIs_installed(Double is_installed) { this.is_installed = is_installed; }
    public Double getIs_renting() { return is_renting; }
    public void setIs_renting(Double is_renting) { this.is_renting = is_renting; }
    public Double getIs_returning() { return is_returning; }
    public void setIs_returning(Double is_returning) { this.is_returning = is_returning; }
    public Double getLast_reported() { return last_reported; }
    public void setLast_reported(Double last_reported) { this.last_reported = last_reported; }
    public String getLegacy_id() { return legacy_id; }
    public void setLegacy_id(String legacy_id) { this.legacy_id = legacy_id; }
    public Double getNum_bikes_available() { return num_bikes_available; }
    public void setNum_bikes_available(Double num_bikes_available) { this.num_bikes_available = num_bikes_available; }
    public StationStatusNumBikesAvailableTypes getNum_bikes_available_types() { return num_bikes_available_types; }
    public void setNum_bikes_available_types(StationStatusNumBikesAvailableTypes num_bikes_available_types) { this.num_bikes_available_types = num_bikes_available_types; }
    public Double getNum_bikes_disabled() { return num_bikes_disabled; }
    public void setNum_bikes_disabled(Double num_bikes_disabled) { this.num_bikes_disabled = num_bikes_disabled; }
    public Double getNum_docks_available() { return num_docks_available; }
    public void setNum_docks_available(Double num_docks_available) { this.num_docks_available = num_docks_available; }
    public Double getNum_docks_disabled() { return num_docks_disabled; }
    public void setNum_docks_disabled(Double num_docks_disabled) { this.num_docks_disabled = num_docks_disabled; }
    public Double getNum_ebikes_available() { return num_ebikes_available; }
    public void setNum_ebikes_available(Double num_ebikes_available) { this.num_ebikes_available = num_ebikes_available; }
    public String getStation_id() { return station_id; }
    public void setStation_id(String station_id) { this.station_id = station_id; }
    public String getStation_status() { return station_status; }
    public void setStation_status(String station_status) { this.station_status = station_status; }
}
