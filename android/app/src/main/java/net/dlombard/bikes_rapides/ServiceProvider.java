package net.dlombard.bikes_rapides;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass(name= "service_provider")
public class ServiceProvider extends RealmObject {

        @PrimaryKey
        private ObjectId _id;
        private String _partitionKey;
        private String auto_discovery_url;
        private String country_code;
        private ServiceProviderFeeds feeds;
        private String location;
        private String name;
        private String system_id;
        private String url;

        // Standard getters & setters
        public ObjectId get_id() { return _id; }
        public void set_id(ObjectId _id) { this._id = _id; }
        public String get_partitionKey() { return _partitionKey; }
        public void set_partitionKey(String _partitionKey) { this._partitionKey = _partitionKey; }
        public String getAuto_discovery_url() { return auto_discovery_url; }
        public void setAuto_discovery_url(String auto_discovery_url) { this.auto_discovery_url = auto_discovery_url; }
        public String getCountry_code() { return country_code; }
        public void setCountry_code(String country_code) { this.country_code = country_code; }
        public ServiceProviderFeeds getFeeds() { return feeds; }
        public void setFeeds(ServiceProviderFeeds feeds) { this.feeds = feeds; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSystem_id() { return system_id; }
        public void setSystem_id(String system_id) { this.system_id = system_id; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

