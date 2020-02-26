package net.dlombard.dlo_notes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.StitchUserProfile;
import com.mongodb.stitch.core.auth.providers.custom.CustomCredential;

import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.MethodChannel;

public class StitchHandler {
    private static final String TAG = RealmHandler.class.toString();
    final StitchAppClient client =
            Stitch.getDefaultAppClient();
    final RemoteMongoClient mongoClient =
            client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
    final RemoteMongoCollection<Document> usersCollection =
            mongoClient.getDatabase("my_doc_shows_up").getCollection("users");
    final RemoteMongoCollection<Document> medCollection =
            mongoClient.getDatabase("my_doc_shows_up").getCollection("medications");
    private MethodChannel fChannel;

    public StitchHandler(MainActivity context, MethodChannel fChannel) {
        this.fChannel = fChannel;
    }

    public void authenticate(String idToken, MethodChannel.Result result) {
        client.getAuth().loginWithCredential(new CustomCredential(idToken)).continueWithTask(
                new Continuation<StitchUser, Task<Document>>() {

                    @Override
                    public Task<Document> then(@NonNull Task<StitchUser> task) throws Exception {
                        if (!task.isSuccessful()) {
                            Log.e("STITCH", "Login failed!");
                            result.error("LOGIN FAILED", "Failed to login to Stitch", task.getException());
                        }

                        Log.d("STITCH", "Succesfully Loggedin to Stitch");
                        StitchUserProfile userProfile = task.getResult().getProfile();
                        Log.d(TAG, userProfile.getEmail());
                        result.success(true);
                        return null;
                    }
                }
        );
    }

    public void getPatients(MethodChannel.Result r) {
        List<String> results = new ArrayList<>();

        List<Document> qResults = new ArrayList<>();

        usersCollection.find(new Document("isDoctor", "false")).sort(new Document("data.email", 1)).into(qResults);

        for (Document doc : qResults) {
            results.add(doc.toJson());
        }
        r.success(results);
    }

    public void getMedication(String searchString, MethodChannel.Result r) {

        List<String> args = new ArrayList<>();
        args.add(searchString);


        client.callFunction("searchMeds", args, BsonArray.class).addOnCompleteListener(new OnCompleteListener<BsonArray>() {
            @Override
            public void onComplete(@NonNull Task<BsonArray> task) {
                if (!task.isSuccessful()) {
                    Log.e("stitch", "Error calling function:", task.getException());
                    r.error(task.getException().getMessage(), task.getException().getCause().getMessage(), task.getException().getStackTrace().toString());
                } else {

                    List<String> results = new ArrayList<>();

                    BsonArray bA = task.getResult();
                    while(bA.iterator().hasNext()){
                        BsonValue bv = bA.iterator().next();
                        bv.asDocument();
                        results.add(bv.asDocument().toJson());
                    }
                    Log.d("stitch", String.format("%s", results.toString())); // Output: 7
                    r.success(results);
                }
            }
        });


    }
}
