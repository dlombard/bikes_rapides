package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="station_status_num_bikes_available_types")
public class StationStatusNumBikesAvailableTypes extends RealmObject {

    private Double classic;
    private Double electric;
    private Double smart;

    // Standard getters & setters
    public Double getClassic() { return classic; }
    public void setClassic(Double classic) { this.classic = classic; }
    public Double getElectric() { return electric; }
    public void setElectric(Double electric) { this.electric = electric; }
    public Double getSmart() { return smart; }
    public void setSmart(Double smart) { this.smart = smart; }
}
