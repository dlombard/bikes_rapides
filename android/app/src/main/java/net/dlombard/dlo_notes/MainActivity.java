package net.dlombard.dlo_notes;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.realm.RealmResults;

public class MainActivity extends FlutterActivity {
    private static final String TAG = MainActivity.class.toString();
    private static final String CHANNEL = "net.dlombard/dlo_notes";
    private static final String STREAM = "net.dlombard/streams";
    private static RealmHandler rHandler;

    private MethodChannel fChannel;
    private EventChannel eChannel;
    private EventChannel.EventSink eventsSink;

    // TBD Integrate Stitch
    //private static StitchHandler sHandler;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        fChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
        rHandler = new RealmHandler(this, fChannel);
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
                RealmResults<BikeStations> bsRealmResults = rHandler.stations;
                RealmResults<BikeStationStatus> bssRealmResults = rHandler.stationsStatus;

            }

            @Override
            public void onCancel(Object arguments) {

            }
        });

    }
}
