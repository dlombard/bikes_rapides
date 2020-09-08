package net.dlombard.bikes_rapides;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="station_location")
public class StationLocation extends RealmObject {

    private RealmList<Double> coordinates;
    private String type;

    // Standard getters & setters

    public RealmList<Double> getCoordinates() { return coordinates; }
    public void setCoordinates(RealmList<Double> coordinates) { this.coordinates = coordinates; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
