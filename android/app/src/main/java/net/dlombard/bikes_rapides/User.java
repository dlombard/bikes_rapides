package net.dlombard.bikes_rapides;

import org.bson.types.ObjectId;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass(name="user")
public class User extends RealmObject {

    @PrimaryKey
    private ObjectId _id;
    private String _partitionKey;
    private UserData data;
    private String id;
    private RealmList<UserIdentities> identities;
    private String type;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String get_partitionKey() { return _partitionKey; }
    public void set_partitionKey(String _partitionKey) { this._partitionKey = _partitionKey; }
    public UserData getData() { return data; }
    public void setData(UserData data) { this.data = data; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public RealmList<UserIdentities> getIdentities() { return identities; }
    public void setIdentities(RealmList<UserIdentities> identities) { this.identities = identities; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
