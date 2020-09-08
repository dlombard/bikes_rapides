package net.dlombard.bikes_rapides;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true, name="user_data")
public class UserData extends RealmObject {

    private String email;

    // Standard getters & setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
