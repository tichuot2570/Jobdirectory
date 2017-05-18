package com.jobdirectory.jobdirectory;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.DataObjects.User;
import com.jobdirectory.db.adapter.CompanyDataSource;
import com.jobdirectory.db.adapter.JobDescriptionDataSource;
import com.jobdirectory.db.adapter.UserDataSource;

import java.util.ArrayList;

public class ListJobCompanyActivity extends AppCompatActivity {

    ArrayList<JobDescription> jobDescriptions;
    int loggedUserCompanyID;
    int selectedSpecializationID;
    int selectedLocationID;
    int selectedJobID;
    int loggedCompanyId;
    ArrayList<Location> locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job_company);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        String loggedUserName = sharedPref.getString("userName", null);
        titleText.setText("Logged as " + loggedUserName);


        jobDescriptions = new ArrayList<JobDescription>();
        locations = new ArrayList<Location>();
        UserDataSource userDataSource = new UserDataSource(this);
        ArrayList<User> users = userDataSource.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (loggedUserName.equals(users.get(i).getUsername())) {
                loggedCompanyId = users.get(i).getFk_CompanyId();
            }
        }

        CompanyDataSource companyDataSource = new CompanyDataSource(this);

        Intent intent = getIntent();
        selectedSpecializationID = intent.getIntExtra("selectedSpecializationID", 0);
        selectedLocationID = intent.getIntExtra("selectedLocationID", 0);

        JobDescriptionDataSource jobDescriptionDataSource = new JobDescriptionDataSource(this);
        jobDescriptions = jobDescriptionDataSource.getJobDescriptionFromCompany(loggedCompanyId);

        ListAdapter searchListAdapter = new AdapterJob(this, jobDescriptions);
        ListView CategoryListView = (ListView) findViewById(R.id.listView1);
        CategoryListView.setAdapter(searchListAdapter);


        CategoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String category = String.valueOf(parent.getItemAtPosition(position));
                        JobDescription selectedFromList = (JobDescription) parent.getAdapter().getItem(position);
                        String str = selectedFromList.getJobName();
                        selectedJobID = selectedFromList.getIdJobDescription();
                        System.out.println("UUU " + selectedJobID);
                        Toast.makeText(ListJobCompanyActivity.this, str, Toast.LENGTH_LONG).show();
                        displayEditJob(view);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_admin, menu);
        return super.onCreateOptionsMenu(menu);
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


    public void displayEditJob(View view) {
        Intent intent = new Intent(this, EditJobActivity.class);
        intent.putExtra("selectedJobID", selectedJobID);
        startActivity(intent);
    }

    public void displayAddJob(View view) {
        selectedJobID = 0;
        Intent intent = new Intent(this, EditJobActivity.class);
        intent.putExtra("selectedJobID", selectedJobID);
        startActivity(intent);
    }


}

