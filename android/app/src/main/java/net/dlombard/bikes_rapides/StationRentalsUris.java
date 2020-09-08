package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="station_rental_uris")
public class StationRentalsUris extends RealmObject {

    private String android;
    private String ios;

    // Standard getters & setters
    public String getAndroid() { return android; }
    public void setAndroid(String android) { this.android = android; }
    public String getIos() { return ios; }
    public void setIos(String ios) { this.ios = ios; }
}
