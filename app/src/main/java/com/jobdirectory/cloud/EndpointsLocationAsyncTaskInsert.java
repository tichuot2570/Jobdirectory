package com.jobdirectory.cloud;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nam.myapplication.backend.locationApi.LocationApi;
import com.example.nam.myapplication.backend.locationApi.model.Location;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jobdirectory.db.adapter.LocationDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.jobdirectory.DataObjects.Location;

/**
 * Created by Vincent_2 on 15.05.2017.
 */

public class EndpointsLocationAsyncTaskInsert extends AsyncTask<Void, Void, List<Location>> {
    private static LocationApi locationApiService = null;
    private static final String TAG = EndpointsLocationAsyncTaskInsert.class.getName();
    private Context context;
    private Location location;
    String action;

    public EndpointsLocationAsyncTaskInsert(Location location) {
        action = "insertData";
        this.location = location;
    }

    public EndpointsLocationAsyncTaskInsert() {
        action = "getData";
    }


    @Override
    protected List<Location> doInBackground(Void... params) {
        if (locationApiService == null) {  // Only do this once
            LocationApi.Builder builder = new LocationApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://job-directory.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            locationApiService = builder.build();
        }


        try {
            if (action == "insertData") {
                locationApiService.insert(location).execute();
            }
            Log.i(TAG, "location inserted");
            return locationApiService.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Location>();
        }

    }

    @Override
    protected void onPostExecute(List<Location> locations) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        if (action == "getData") {
            for (Location l : locations) {

                long locationID = l.getIdLocation();
                String locationName = l.getNameLocation();
                String zipCode = l.getZipCode();

                LocationDataSource locationDs = new LocationDataSource(context);
                com.jobdirectory.DataObjects.Location newLocation = new com.jobdirectory.DataObjects.Location();

                newLocation.setName_location(locationName);
                newLocation.setZipCode(zipCode);
                newLocation.setIdLocation((int) locationID);
                locationDs.createLocation(newLocation);

            }
        }

    }
}
