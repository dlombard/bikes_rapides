package net.dlombard.bikes_rapides;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends FlutterActivity {
    private static final String TAG = MainActivity.class.toString();
    private static final String CHANNEL = "net.dlombard/bikes_rapides";
    private static final String STREAM = "net.dlombard/streams";
    private static net.dlombard.bikes_rapides.RealmHandler rHandler;

    private MethodChannel fChannel;
    private EventChannel eChannel;
    private EventChannel.EventSink eventsSink;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);

    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        fChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
        rHandler = new net.dlombard.bikes_rapides.RealmHandler(this, fChannel);
        //sHandler = new StitchHandler(this, fChannel);

        fChannel.setMethodCallHandler(
                (call, result) -> {
                    switch(call.method){
                        case "LOGIN":
                            String token = call.argument("idToken");
                            System.out.println(token);
                            rHandler.authenticate(token, result);
                            //sHandler.authenticate(token, result);
                            break;
                        case "LOGIN_EMAIL_PASSWORD":
                            String email = call.argument("email");
                            String password = call.argument("password");

                            rHandler.authenticateEmail(email, password, result);

                            break;
                        case "LOGOUT":
                            rHandler.logout(result);
                            //sHandler.logout(result);
                            break;
                        case "GET_BIKE_STATIONS":
                            result.success(rHandler.getStationsInJson());
                            break;
                        case "GET_BIKE_STATIONS_STATUS":
                            result.success(rHandler.getStationsStatusInJson());
                            break;
                        default:
                            break;
                    }
                }
        );

        eChannel = new EventChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), STREAM);
        eChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                eventsSink = events;
                rHandler.setSink(eventsSink);
                RealmResults<net.dlombard.bikes_rapides.BikeStations> bsRealmResults = rHandler.stations;
                RealmResults<net.dlombard.bikes_rapides.BikeStationStatus> bssRealmResults = rHandler.stationsStatus;

            }

            @Override
            public void onCancel(Object arguments) {

            }
        });

    }
}
