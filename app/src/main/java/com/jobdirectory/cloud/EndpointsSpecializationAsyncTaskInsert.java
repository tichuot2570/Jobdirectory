package com.jobdirectory.cloud;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nam.myapplication.backend.specializationApi.SpecializationApi;
import com.example.nam.myapplication.backend.specializationApi.model.Specialization;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jobdirectory.db.adapter.SpecializationDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent_2 on 16.05.2017.
 */

public class EndpointsSpecializationAsyncTaskInsert extends AsyncTask<Void, Void, List<Specialization>> {
    private static SpecializationApi specializationApiService = null;
    private static final String TAG = EndpointsLocationAsyncTaskInsert.class.getName();
    private Context context;
    private Specialization specialization;
    String action;

    public EndpointsSpecializationAsyncTaskInsert(Specialization specialization) {
        action = "insertData";
        this.specialization = specialization;
    }

    public EndpointsSpecializationAsyncTaskInsert() {
        action = "getData";
    }


    @Override
    protected List<Specialization> doInBackground(Void... params) {
        if (specializationApiService == null) {  // Only do this once
            SpecializationApi.Builder builder = new SpecializationApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            specializationApiService = builder.build();
        }


        try {
            if (action == "insertData") {
                specializationApiService.insert(specialization).execute();
            }
            Log.i(TAG, "specialization inserted");
            return specializationApiService.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Specialization>();
        }

    }

    @Override
    protected void onPostExecute(List<Specialization> specializations) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        if (action == "getData") {
            for (Specialization s : specializations) {

                long specializationID = s.getIdSpecialization();
                String specializationName = s.getSpecTitle();
                int categoryID = s.getFkCategoryId();
                String nameFR = s.getSpecTitleFR();
                String descriptionFR = s.getSpecDescriptionFR();
                String description = s.getSpecDescription();

                SpecializationDataSource specializationDs = new SpecializationDataSource(context);
                com.jobdirectory.DataObjects.Specialization newSpecialization = new com.jobdirectory.DataObjects.Specialization();

                newSpecialization.setSpec_Title(specializationName);
                newSpecialization.setFk_CategoryId(categoryID);
                newSpecialization.setSpec_TitleFR(nameFR);
                newSpecialization.setSpec_DescriptionFR(descriptionFR);
                newSpecialization.setSpec_Description(description);
                newSpecialization.setIdSpecialization((int) specializationID);

                specializationDs.createSpecialization(newSpecialization);

            }
        }
    }
}
