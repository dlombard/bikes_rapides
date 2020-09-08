package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="user_identities")
public class UserIdentities extends RealmObject {

    private String id;
    private String provider_type;

    // Standard getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProvider_type() { return provider_type; }
    public void setProvider_type(String provider_type) { this.provider_type = provider_type; }
}
