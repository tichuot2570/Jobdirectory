package com.jobdirectory.cloud;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nam.myapplication.backend.jobDescriptionApi.JobDescriptionApi;
import com.example.nam.myapplication.backend.jobDescriptionApi.model.JobDescription;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

//import com.jobdirectory.DataObjects.Location;

/**
 * Created by Vincent_2 on 15.05.2017.
 */

public class EndpointsJobDescriptionAsyncTaskDelete extends AsyncTask<Void, Void, JobDescription> {
    private static JobDescriptionApi jobDescriptionApiService = null;
    private static final String TAG = EndpointsJobDescriptionAsyncTaskInsert.class.getName();
    private Context context;
    private JobDescription jobDescription;
    private long jobDescriptionID;

    public EndpointsJobDescriptionAsyncTaskDelete(int jobDescriptionID) {
        this.jobDescriptionID = (long) jobDescriptionID;
    }


    @Override
    protected JobDescription doInBackground(Void... params) {
        if (jobDescription == null) {  // Only do this once
            JobDescriptionApi.Builder builder = new JobDescriptionApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            jobDescriptionApiService = builder.build();
        }


        try {
            jobDescriptionApiService.remove(jobDescriptionID);
            Log.i(TAG, "job deleted");
            return jobDescription;
        } catch (IOException e) {
            return new JobDescription();
        }

    }

    @Override
    protected void onPostExecute(JobDescription result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
