package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name = "station_eightd_station_services")
public class  StationEightdStationServices extends RealmObject {

    private String bikes_availability;
    private String description;
    private String docks_availability;
    private String id;
    private String link_for_more_info;
    private String name;
    private Double off_dock_bikes_count;
    private Double off_dock_remaining_bike_capacity;
    private String schedule_description;
    private String service_type;

    // Standard getters & setters
    public String getBikes_availability() { return bikes_availability; }
    public void setBikes_availability(String bikes_availability) { this.bikes_availability = bikes_availability; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDocks_availability() { return docks_availability; }
    public void setDocks_availability(String docks_availability) { this.docks_availability = docks_availability; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLink_for_more_info() { return link_for_more_info; }
    public void setLink_for_more_info(String link_for_more_info) { this.link_for_more_info = link_for_more_info; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getOff_dock_bikes_count() { return off_dock_bikes_count; }
    public void setOff_dock_bikes_count(Double off_dock_bikes_count) { this.off_dock_bikes_count = off_dock_bikes_count; }
    public Double getOff_dock_remaining_bike_capacity() { return off_dock_remaining_bike_capacity; }
    public void setOff_dock_remaining_bike_capacity(Double off_dock_remaining_bike_capacity) { this.off_dock_remaining_bike_capacity = off_dock_remaining_bike_capacity; }
    public String getSchedule_description() { return schedule_description; }
    public void setSchedule_description(String schedule_description) { this.schedule_description = schedule_description; }
    public String getService_type() { return service_type; }
    public void setService_type(String service_type) { this.service_type = service_type; }
}
