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
import com.jobdirectory.db.adapter.JobDescriptionDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.jobdirectory.DataObjects.Location;

/**
 * Created by Vincent_2 on 15.05.2017.
 */

public class EndpointsJobDescriptionAsyncTaskInsert extends AsyncTask<Void, Void, List<JobDescription>> {
    private static JobDescriptionApi jobDescriptionApiService = null;
    private static final String TAG = EndpointsJobDescriptionAsyncTaskInsert.class.getName();
    private Context context;
    private JobDescription jobDescription;
    String action;
    long jobID;

    public EndpointsJobDescriptionAsyncTaskInsert(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
        action = "insertData";
    }

    public EndpointsJobDescriptionAsyncTaskInsert() {

        action = "getData";
    }

    public EndpointsJobDescriptionAsyncTaskInsert(int jobID) {
        action = "deleteData";
        this.jobID = (long) jobID;
    }


    @Override
    protected List<JobDescription> doInBackground(Void... params) {
        if (jobDescriptionApiService == null) {  // Only do this once
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
            if (action == "insertData") {
                jobDescriptionApiService.insert(jobDescription).execute();
            } else if (action == "deleteData") {
                System.out.println("TTT " + jobID);
                jobDescriptionApiService.remove(jobID).execute();
            }
            Log.i(TAG, "job inserted");
            return jobDescriptionApiService.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<JobDescription>();
        }

    }

    @Override
    protected void onPostExecute(List<JobDescription> jobDescriptions) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        if (action == "getData") {
            for (JobDescription jd : jobDescriptions) {

                long jdID = jd.getIdJobDescription();
                String jobName = jd.getJobName();
                String jobDescription = jd.getJobDescription();
                String jobDescriptionFR = jd.getJobDescriptionFR();
                String jobNameFR = jd.getJobNameFR();
                long specializationId = jd.getFkSpecializationId();
                long locationId = jd.getFkLocationId();
                long companyID = jd.getFkCompanyId();

                JobDescriptionDataSource jobDs = new JobDescriptionDataSource(context);
                com.jobdirectory.DataObjects.JobDescription newJob = new com.jobdirectory.DataObjects.JobDescription();

                newJob.setJobName(jobName);
                newJob.setJobDescription(jobDescription);
                newJob.setJobDescriptionFR(jobDescriptionFR);//notNull constraint
                newJob.setJobNameFR(jobNameFR);
                newJob.setFk_SpecializationId((int) specializationId);
                newJob.setFk_LocationId((int) locationId);
                newJob.setFk_CompanyId((int) companyID);
                newJob.setIdJobDescription((int) jdID);

                System.out.println("UUU " + (int) jdID);

                jobDs.createJob(newJob);

            }
        }
    }
}
