package net.dlombard.bikes_rapides;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class RealmHandler {
    private static final String TAG = RealmHandler.class.toString();
    private static final String PUBLIC_PARTITION_KEY = "Public";
    RealmResults<Station> stations;
    RealmResults<StationStatus> stationsStatus;

    private App app;
    private User user;
    private MethodChannel fChannel;
    private EventChannel.EventSink sink;
    private Realm userRealm;
    private Realm publicRealm;
    private Realm bikesRealm;

    public RealmHandler(net.dlombard.bikes_rapides.MainActivity context, MethodChannel fChannel) {
        Realm.init(context);
        this.fChannel = fChannel;
        String appId = context.getResources().getString(R.string.mongodb_realm_app_id);
        app = new App(new AppConfiguration.Builder(appId).build());


    }

    public void logout(MethodChannel.Result result) {
        for (User u : app.allUsers().values()) {

            u.logOut();

        }

        result.success(true);
    }

    public void authenticate(String idToken, MethodChannel.Result result) {

        if(app.currentUser() == null) {
            Log.d(TAG, "SYNC USER NULL");
            Credentials credentials = Credentials.jwt(idToken);
            app.loginAsync(credentials, it -> {
                if (it.isSuccess()) {
                    Log.d(TAG, "Successfully authenticated to Realm " +it.get().getId());
                    configureRealms(result);
                }

                else {
                    Log.e(TAG, "Failed to log in. Error: " + it.getError().toString());
                }
            });

        } else{
            Log.d(TAG, "SYNC USER NOT NULL");
            configureRealms(result);
        }



    }

    public void authenticateEmail(String email, String password, MethodChannel.Result result){

        Credentials credentials = Credentials.emailPassword(email, password);

        app.loginAsync(credentials, it -> {
           if(it.isSuccess()){
               Log.d(TAG, "AUTHENTICATION SUCCESSFUL");

               user = app.currentUser();
               configureRealms(result);


           }else {
               Log.e(TAG, "Failed to log in. Error: " + it.getError().toString());
           }
        });
    }

    private void configureRealms(MethodChannel.Result result) {

        User user = app.currentUser();
        String userPartitionKey = user.getId();

        SyncConfiguration pConfig = new SyncConfiguration.Builder(user, PUBLIC_PARTITION_KEY ).build();
        SyncConfiguration uConfig = new SyncConfiguration.Builder(user, userPartitionKey).build();

        publicRealm = Realm.getInstance(pConfig);
        userRealm = Realm.getInstance(uConfig);
        Log.d(TAG, "GET INSTANCE ASYNC");
        Realm.getInstanceAsync(pConfig, new Realm.Callback() {
            @Override
            public void onSuccess(Realm realm) {
                publicRealm = realm;
                Log.d(TAG, "QUERYING SERVICE PROVIDER");
                RealmResults<ServiceProvider> serviceProviders = publicRealm.where(ServiceProvider.class).findAll();

                Log.d(TAG,"SERVICE PROVIDERS REALM " + serviceProviders.first().toString());
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
                Log.e(TAG, "SERVICE PROVIDER ERROR");
                Log.e(TAG, exception.getMessage());
            }
        });
        Realm.getInstanceAsync(uConfig, new Realm.Callback() {
            @Override
            public void onSuccess(Realm realm) {
                userRealm = realm;
                Log.d(TAG, "QUERYING USER");
                RealmResults<net.dlombard.bikes_rapides.User> u = publicRealm.where(net.dlombard.bikes_rapides.User.class).findAll();

                Log.d(TAG,"USER REALM " + u.asJSON());
                result.success(app.currentUser().getId());
            }
            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
                Log.e(TAG, "USER REALM ERROR");
                Log.e(TAG, exception.getMessage());
            }
        });


    }


    public void setEventChannel() {
/*
        stations.addChangeListener(new RealmChangeListener<RealmResults<net.dlombard.bikes_rapides.BikeStations>>() {
            @Override
            public void onChange(RealmResults<net.dlombard.bikes_rapides.BikeStations> bikeStations) {
                List<String> jsonItems = getStationsInJson();
                Log.d(TAG, "THERE ARE " + bikeStations.size() + " BIKE STATIONS");

                //fChannel.invokeMethod("NEW_BIKE_STATION", jsonItems);
                if (sink != null) {
                    sink.success(jsonItems);
                }
            }
        });

        stationsStatus.addChangeListener(new RealmChangeListener<RealmResults<net.dlombard.bikes_rapides.BikeStationStatus>>() {
            @Override
            public void onChange(RealmResults<net.dlombard.bikes_rapides.BikeStationStatus> bikeStationStatuses) {

                List<String> jsonItems = getStationsStatusInJson();
                //fChannel.invokeMethod("NEW_BIKE_STATION_STATUS", jsonItems);
                if (sink != null) {
                    sink.success(jsonItems);
                }
            }

        });*/

    }

    public RealmResults<Station> getStations() {
        return stations;
    }

    public RealmResults<StationStatus> getStationsStatus() {
        return stationsStatus;
    }

    public void setSink(EventChannel.EventSink sink) {
        this.sink = sink;
    }

    public List<String> getStationsInJson() {
/*
        if(stations == null){
            stations = bikesRealm.where(net.dlombard.bikes_rapides.BikeStations.class)
                    .findAllAsync();
        }

        List<String> jsonItems = new ArrayList<>();
        Log.d(TAG, "THERE ARE " + stations.size() + " BIKE STATIONS");

        for (net.dlombard.bikes_rapides.BikeStations i : stations) {
            JSONObject obj = new JSONObject();
            try {
                List<String> s = new ArrayList<>();
                RealmList<String> rm = i.getRental_methods();

                for (int x = 0; x < rm.size(); x++) {
                    s.add(rm.get(x));
                }
                obj.put("station_id", i.getStation_id());
                obj.put("external_id", i.getExternal_id());
                obj.put("name", i.getName());
                obj.put("short_name", i.getShort_name());
                obj.put("lat", i.getLat());
                obj.put("lon", i.getLon());
                obj.put("rental_methods", s);
                obj.put("capacity", i.getCapacity());

                jsonItems.add(obj.toString());
            } catch (JSONException jE) {
                Log.e(TAG, jE.getMessage());
            }


        }*/
        //return jsonItems;
        return null;
    }

    public List<String> getStationsStatusInJson() {


        if(stationsStatus == null){
            //stationsStatus = bikesRealm.where(net.dlombard.bikes_rapides.BikeStationStatus.class).findAllAsync();
        }

        List<String> jsonItems = new ArrayList<>();
        for (StationStatus i : stationsStatus) {
            JSONObject obj = new JSONObject();
            try {

                obj.put("station_id", i.getStation_id());
                obj.put("num_bikes_available", i.getNum_bikes_available());
                obj.put("num_ebikes_available", i.getNum_ebikes_available());
                obj.put("num_bikes_disabled", i.getNum_bikes_available());
                obj.put("num_docks_available", i.getNum_docks_available());
                obj.put("num_docks_disabled", i.getNum_docks_disabled());
                obj.put("is_installed", i.getIs_installed());
                obj.put("is_renting", i.getIs_renting());
                obj.put("is_returning", i.getIs_returning());
                obj.put("last_reported", i.getLast_reported());

                jsonItems.add(obj.toString());
            } catch (JSONException jE) {
                Log.e(TAG, jE.getMessage());
            }
        }

        //return jsonItems;
        return null;
    }


}
