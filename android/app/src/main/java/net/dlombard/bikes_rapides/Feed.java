package net.dlombard.bikes_rapides;

import io.realm.RealmObject;

public class Feed  extends RealmObject {
    private String url;

    // Standard getters & setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
