package com.jobdirectory.jobdirectory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.DataObjects.User;
import com.jobdirectory.cloud.EndpointsJobDescriptionAsyncTaskInsert;
import com.jobdirectory.db.DBHelper;
import com.jobdirectory.db.adapter.JobDescriptionDataSource;
import com.jobdirectory.db.adapter.LocationDataSource;
import com.jobdirectory.db.adapter.SpecializationDataSource;
import com.jobdirectory.db.adapter.UserDataSource;

import java.util.ArrayList;

public class EditJobActivity extends AppCompatActivity {

    int selectedJobID = 0;
    JobDescription jobDescription;
    Button deleteBtn;
    ArrayList<Specialization> specializations;
    ArrayList<Location> locations;
    Spinner spinnerCategory;
    Spinner spinnerLocation;
    int specializationId;
    int locationId;
    User loggedUser;
    JobDescription newJob;
    String jobTitleString;
    String jobDescriptionTextString;
    JobDescription job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();

        selectedJobID = intent.getIntExtra("selectedJobID", 0);


        //Spinner

        SpecializationDataSource specializationDataSource = new SpecializationDataSource(this);
        specializations = specializationDataSource.getAllSpecializations();
        ArrayList<String> specializationsName = new ArrayList<String>();
        for (int i = 0; i < specializations.size(); i++) {
            specializationsName.add(specializations.get(i).getSpec_Title());
        }

        LocationDataSource locoationDataSource = new LocationDataSource(this);
        locations = locoationDataSource.getAllLocations();
        ArrayList<String> locationName = new ArrayList<String>();
        for (int i = 0; i < locations.size(); i++) {
            locationName.add(locations.get(i).getName_location());
        }

        spinnerCategory = (Spinner) findViewById(R.id.specializationSpinner);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, specializationsName);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoriesAdapter);

        spinnerLocation = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<String> locationsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, locationName);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(locationsAdapter);


        UserDataSource userDataSource = new UserDataSource(this);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        String loggedUserName = sharedPref.getString("userName", null);
        loggedUser = userDataSource.getUser(loggedUserName);


        if (selectedJobID != 0) {

            JobDescriptionDataSource jobDescriptionDataSource = new JobDescriptionDataSource(this);
            jobDescription = jobDescriptionDataSource.getJobById(selectedJobID);
            String advertSpecializationName = "";
            String advertLocationName = "";

            for (int i = 0; i < specializations.size(); i++) {
                if (jobDescription.getFk_SpecializationId() == specializations.get(i).getIdSpecialization()) {
                    advertSpecializationName = specializations.get(i).getSpec_Title();
                }
            }

            int selectionPositionSpecialization = categoriesAdapter.getPosition(advertSpecializationName);
            spinnerCategory.setSelection(selectionPositionSpecialization);

            int selectionPositionLocation = locationsAdapter.getPosition(jobDescription.getlocationName());//locationName is already in object
            spinnerLocation.setSelection(selectionPositionLocation);


            EditText jobTitleText = (EditText) findViewById(R.id.jobTitleText);
            jobTitleText.setText(jobDescription.getJobName());
            EditText jobDescriptionText = (EditText) findViewById(R.id.jobDescriptionText);
            jobDescriptionText.setText(jobDescription.getJobDescription());


        } else {

            deleteBtn = (Button) findViewById(R.id.deleteBtn);
            deleteBtn.setVisibility(View.INVISIBLE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * -------- Dialog box
     */

    public void dialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Do you really wanna do that?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteJobConfirm();
                    }
                }).create().show();
    }


    /**
     * -------- Action bar menu listener
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);

        switch (item.getItemId()) {
            case R.id.action_login:
                //Verify if a user is already logged
                boolean checkloggedUser = sharedPref.getBoolean("loggedUser", false);
                Intent intent_login;
                if (checkloggedUser == true) {
                    intent_login = new Intent(this, ListJobCompanyActivity.class);
                } else {
                    intent_login = new Intent(this, LoginActivity.class);
                }
                this.startActivity(intent_login);
                break;
            case R.id.action_logout:
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("userName");
                editor.remove("loggedUser");
                editor.commit();
                Intent intent_logout = new Intent(this, MainActivity.class);
                this.startActivity(intent_logout);
                break;
            case R.id.action_home:
                Intent intent_home = new Intent(this, MainActivity.class);
                this.startActivity(intent_home);
                break;
            case R.id.action_about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                this.startActivity(intent_about);
                break;
            case R.id.action_settings:
                Intent intent_settings = new Intent(this, SettingsActivity.class);
                this.startActivity(intent_settings);
                break;
            case R.id.action_favorite:
                Intent intent_favorite = new Intent(this, ListJobFavoriteActivity.class);
                intent_favorite.putExtra("activityInfo", "favorite");
                this.startActivity(intent_favorite);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

    //----------------------------

    public void deleteJob(View view) {

        dialog();

    }

    public void deleteJobConfirm() {

        JobDescriptionDataSource jobDs = new JobDescriptionDataSource(this);
        jobDs.deleteJob(selectedJobID);

        new EndpointsJobDescriptionAsyncTaskInsert(selectedJobID).execute();

        Intent intent = new Intent(this, ListJobCompanyActivity.class);
        startActivity(intent);

    }


    public void saveJob(View view) {

        EditText jobTitleText = (EditText) findViewById(R.id.jobTitleText);
        jobTitleString = jobTitleText.getText().toString();
        EditText jobDescriptionText = (EditText) findViewById(R.id.jobDescriptionText);
        jobDescriptionTextString = jobDescriptionText.getText().toString();

        JobDescriptionDataSource jobDs = new JobDescriptionDataSource(this);

        String spinnerCategorytext = spinnerCategory.getSelectedItem().toString();
        String spinnerLocationtext = spinnerLocation.getSelectedItem().toString();


        for (int i = 0; i < specializations.size(); i++) {
            if (specializations.get(i).getSpec_Title() == spinnerCategorytext) {
                specializationId = specializations.get(i).getIdSpecialization();
            }
        }

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getName_location() == spinnerLocationtext) {
                locationId = locations.get(i).getIdLocation();
            }
        }


        if (selectedJobID != 0) {

            //---------------- update job

            job = new JobDescription();
            job.setIdJobDescription(selectedJobID);
            job.setJobName(jobTitleString);
            job.setJobDescription(jobDescriptionTextString);
            job.setFk_SpecializationId(specializationId);
            job.setFk_LocationId(locationId);

            job.setIdJobDescription((int) jobDs.updateJob(job));

            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.getWritableDatabase().close();

            updateJobToCloud();

        } else {

            //---------------- add job

            newJob = new JobDescription();
            newJob.setJobName(jobTitleString);
            newJob.setJobDescription(jobDescriptionTextString);
            newJob.setJobDescriptionFR("");//notNull constraint
            newJob.setJobNameFR("");
            newJob.setFk_SpecializationId(specializationId);
            newJob.setFk_LocationId(locationId);
            newJob.setFk_CompanyId(loggedUser.getFk_CompanyId());
            newJob.setIdJobDescription((int) jobDs.createJob(newJob));

            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.getWritableDatabase().close();


            //*** insert to cloud ***

            addJobToCloud();



        }

        Intent intent = new Intent(this, ListJobCompanyActivity.class);
        startActivity(intent);

    }

    public void addJobToCloud() {

        com.example.nam.myapplication.backend.jobDescriptionApi.model.JobDescription jobDescriptionCloud = new com.example.nam.myapplication.backend.jobDescriptionApi.model.JobDescription();

        jobDescriptionCloud.setIdJobDescription((long) newJob.getIdJobDescription());

        jobDescriptionCloud.setJobName(jobTitleString);
        jobDescriptionCloud.setJobDescription(jobDescriptionTextString);
        jobDescriptionCloud.setFkSpecializationId((long) specializationId);

        jobDescriptionCloud.setJobDescriptionFR("");//notNull constraint
        jobDescriptionCloud.setJobNameFR("");

        jobDescriptionCloud.setFkLocationId((long) locationId);
        jobDescriptionCloud.setFkCompanyId((long) loggedUser.getFk_CompanyId());

        new EndpointsJobDescriptionAsyncTaskInsert(jobDescriptionCloud).execute();

    }


    public void updateJobToCloud() {

        com.example.nam.myapplication.backend.jobDescriptionApi.model.JobDescription jobDescriptionCloud = new com.example.nam.myapplication.backend.jobDescriptionApi.model.JobDescription();

        jobDescriptionCloud.setIdJobDescription((long) job.getIdJobDescription());

        jobDescriptionCloud.setJobName(jobTitleString);
        jobDescriptionCloud.setJobDescription(jobDescriptionTextString);
        jobDescriptionCloud.setFkSpecializationId((long) specializationId);

        jobDescriptionCloud.setJobDescriptionFR("");//notNull constraint
        jobDescriptionCloud.setJobNameFR("");

        jobDescriptionCloud.setFkLocationId((long) locationId);
        jobDescriptionCloud.setFkCompanyId((long) loggedUser.getFk_CompanyId());

        new EndpointsJobDescriptionAsyncTaskInsert(jobDescriptionCloud).execute();

    }


}