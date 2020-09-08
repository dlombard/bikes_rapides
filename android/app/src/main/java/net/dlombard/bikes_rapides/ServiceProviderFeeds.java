package net.dlombard.bikes_rapides;


import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.RealmField;

@RealmClass(embedded = true, name="service_provider_feeds")
public class ServiceProviderFeeds extends RealmObject {
    @RealmField(name="service_provider_feeds_free_bike_status")
    private Feed free_bike_status;
    @RealmField(name="service_provider_feeds_gbfs")
    private Feed gbfs;
    @RealmField(name="service_provider_feeds_geofencing_zone_information")
    private Feed geofencing_zone_information;
    @RealmField(name="service_provider_feeds_geofencing_zone_status")
    private Feed geofencing_zone_status;
    @RealmField(name = "service_provider_feeds_monthly_reports")
    private Feed monthly_reports;
    @RealmField(name="service_provider_feeds_nearby_stations")
    private Feed nearby_stations;
    @RealmField(name = "service_provider_feeds_station_information")
    private Feed station_information;
    @RealmField(name="service_provider_feeds_station_status")
    private Feed station_status;
    @RealmField(name="service_provider_feeds_system_alerts")
    private Feed system_alerts;
    @RealmField(name="service_provider_feeds_system_calendar")
    private  Feed system_calendar;
    @RealmField(name = "service_provider_feeds_system_hours")
    private Feed system_hours;
    @RealmField(name="service_provider_feeds_system_information")
    private Feed system_information;
    @RealmField(name="service_provider_feeds_system_pricing_plans")
    private Feed system_pricing_plans;
    @RealmField(name="service_provider_feeds_system_regions")
    private Feed system_regions;

    // Standard getters & setters
    public Feed getFree_bike_status() { return free_bike_status; }
    public void setFree_bike_status(Feed free_bike_status) { this.free_bike_status = free_bike_status; }
    public Feed getGbfs() { return gbfs; }
    public void setGbfs(Feed gbfs) { this.gbfs = gbfs; }
    public Feed getGeofencing_zone_information() { return geofencing_zone_information; }
    public void setGeofencing_zone_information(Feed geofencing_zone_information) { this.geofencing_zone_information = geofencing_zone_information; }
    public Feed getGeofencing_zone_status() { return geofencing_zone_status; }
    public void setGeofencing_zone_status(Feed geofencing_zone_status) { this.geofencing_zone_status = geofencing_zone_status; }
    public Feed getMonthly_reports() { return monthly_reports; }
    public void setMonthly_reports(Feed monthly_reports) { this.monthly_reports = monthly_reports; }
    public Feed getNearby_stations() { return nearby_stations; }
    public void setNearby_stations(Feed nearby_stations) { this.nearby_stations = nearby_stations; }
    public Feed getStation_information() { return station_information; }
    public void setStation_information(Feed station_information) { this.station_information = station_information; }
    public Feed getStation_status() { return station_status; }
    public void setStation_status(Feed station_status) { this.station_status = station_status; }
    public Feed getSystemRegions() { return system_regions; }
    public void setSystemRegions(Feed system_regions) { this.system_regions = system_regions; }
    public Feed getSystem_alerts() { return system_alerts; }
    public void setSystem_alerts(Feed system_alerts) { this.system_alerts = system_alerts; }
    public Feed getSystem_calendar() { return system_calendar; }
    public void setSystem_calendar(Feed system_calendar) { this.system_calendar = system_calendar; }
    public Feed getSystem_hours() { return system_hours; }
    public void setSystem_hours(Feed system_hours) { this.system_hours = system_hours; }
    public Feed getSystem_information() { return system_information; }
    public void setSystem_information(Feed system_information) { this.system_information = system_information; }
    public Feed getSystem_pricing_plans() { return system_pricing_plans; }
    public void setSystem_pricing_plans(Feed system_pricing_plans) { this.system_pricing_plans = system_pricing_plans; }
    public Feed getSystem_regions() { return system_regions; }
    public void setSystem_regions(Feed system_regions) { this.system_regions = system_regions; }
}
