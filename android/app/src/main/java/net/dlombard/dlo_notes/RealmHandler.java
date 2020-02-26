package net.dlombard.dlo_notes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import io.realm.annotations.RealmModule;

import static net.dlombard.dlo_notes.Constants.AUTH_URL;
import static net.dlombard.dlo_notes.Constants.BIKES_REALM;

public class RealmHandler {
    private static final String TAG = RealmHandler.class.toString();
    RealmResults<BikeStations> stations;
    RealmResults<BikeStationStatus> stationsStatus;
    Realm bikesRealm;
    private MethodChannel fChannel;
    private EventChannel.EventSink sink;

    public RealmHandler(MainActivity context, MethodChannel fChannel) {
        Realm.init(context);
        this.fChannel = fChannel;


    }

    public void logout(MethodChannel.Result result) {
        for (SyncUser u : SyncUser.all().values()) {

            u.logOut();

        }

        result.success(true);
    }

    public void authenticate(String idToken, MethodChannel.Result result) {

        if(SyncUser.current() == null) {
            Log.d(TAG, "SYNC USER NULL");
            SyncCredentials credentials = SyncCredentials.jwt(idToken);
            SyncUser.logInAsync(credentials, AUTH_URL, new SyncUser.Callback<SyncUser>() {
                @Override
                public void onSuccess(SyncUser user) {
                    Log.d(TAG, "Successfully authenticated to Realm " + user.toJson());
                    configureRealms(result);

                }

                @Override
                public void onError(ObjectServerError error) {

                    Log.e("Login error", error.toString());
                    result.error("LOGIN FAILED", "Failed to login to Realm", error.toString());

                }
            });

        } else{
            Log.d(TAG, "SYNC USER NOT NULL");
            SyncConfiguration config = SyncUser.current().createConfiguration(BIKES_REALM).schemaVersion(2).build();
            bikesRealm = Realm.getInstance(config);
            result.success(true);
        }



    }

    private void configureRealms(MethodChannel.Result result) {
        @RealmModule (classes = {BikeStations.class, BikeStationStatus.class}) class GlobalRealmModule{}

        SyncConfiguration config = SyncUser.current().createConfiguration(BIKES_REALM).schemaVersion(2).readOnly().compactOnLaunch().fullSynchronization().waitForInitialRemoteData().build();

        Realm.getInstanceAsync(config, new Realm.Callback() {

            @Override
            public void onSuccess(Realm realm) {
                bikesRealm = realm;

                result.success(true);

            }

            @Override
            public void onError(Throwable exception) {
                //super.onError(exception);
                Log.d(TAG, exception.getLocalizedMessage());
            }
        });



    }


    public void setEventChannel() {

        stations.addChangeListener(new RealmChangeListener<RealmResults<BikeStations>>() {
            @Override
            public void onChange(RealmResults<BikeStations> bikeStations) {
                List<String> jsonItems = getStationsInJson();
                Log.d(TAG, "THERE ARE " + bikeStations.size() + " BIKE STATIONS");

                //fChannel.invokeMethod("NEW_BIKE_STATION", jsonItems);
                if (sink != null) {
                    sink.success(jsonItems);
                }
            }
        });

        stationsStatus.addChangeListener(new RealmChangeListener<RealmResults<BikeStationStatus>>() {
            @Override
            public void onChange(RealmResults<BikeStationStatus> bikeStationStatuses) {

                List<String> jsonItems = getStationsStatusInJson();
                //fChannel.invokeMethod("NEW_BIKE_STATION_STATUS", jsonItems);
                if (sink != null) {
                    sink.success(jsonItems);
                }
            }

        });

    }

    public RealmResults<BikeStations> getStations() {
        return stations;
    }

    public RealmResults<BikeStationStatus> getStationsStatus() {
        return stationsStatus;
    }

    public void setSink(EventChannel.EventSink sink) {
        this.sink = sink;
    }

    public List<String> getStationsInJson() {

        if(stations == null){
            stations = bikesRealm.where(BikeStations.class)
                    .findAllAsync();
        }

        List<String> jsonItems = new ArrayList<>();
        Log.d(TAG, "THERE ARE " + stations.size() + " BIKE STATIONS");

        for (BikeStations i : stations) {
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


        }
        return jsonItems;
    }

    public List<String> getStationsStatusInJson() {

        if(stationsStatus == null){
            stationsStatus = bikesRealm.where(BikeStationStatus.class)
                    .findAllAsync();
        }

        List<String> jsonItems = new ArrayList<>();
        for (BikeStationStatus i : stationsStatus) {
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

        return jsonItems;
    }


}
