package com.jobdirectory.jobdirectory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.db.adapter.JobDescriptionDataSource;

public class JobDetailsActivity extends AppCompatActivity {

    int selectedJobID;
    JobDescription selectedJob;
    String location;
    String company;
    String description;
    JobDescription jobDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        selectedJobID = intent.getIntExtra("selectedJobID", 0);


        JobDescriptionDataSource jobDescriptionDataSource = new JobDescriptionDataSource(this);
        jobDescription = jobDescriptionDataSource.getJobById(selectedJobID);


        /*
        for(int i=0; i<MainActivity.locations.size();i++){
            if(MainActivity.locations.get(i).getIdLocation()==selectedJob.getFk_LocationId()){
                location = MainActivity.locations.get(i).getName_location();
            }
        }


        for(int i=0; i<MainActivity.companies.size();i++){
            if(MainActivity.companies.get(i).getIdCompany()==selectedJob.getFk_CompanyId()){
                company = MainActivity.companies.get(i).getName_company();
            }
        }

        */


        TextView textJobTitle = (TextView) findViewById(R.id.jobTitle);
        textJobTitle.setText(jobDescription.getJobName());


        TextView textLocation = (TextView) findViewById(R.id.jobLocation);
        textLocation.setText(jobDescription.getCompanyName());

        TextView textCompany = (TextView) findViewById(R.id.jobCompany);
        textCompany.setText(jobDescription.getlocationName());


        TextView textDescription = (TextView) findViewById(R.id.jobDescription);
        textDescription.setText(jobDescription.getJobDescription());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    /**
     * -------- Action bar menu listener
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);

        switch(item.getItemId()) {
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

}
