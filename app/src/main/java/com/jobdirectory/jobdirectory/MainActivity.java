package com.jobdirectory.jobdirectory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jobdirectory.DataObjects.CurrentLanguage;
import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper jobDirectoryDB;


    ArrayList<JobDescription> jobDescriptionsSelect = new ArrayList<>();
    int selectedLocation;
    int selectedSpecialization;
    String selectedSpecializationName;
    int selectedSpecializationID;
    String selectedLocationName;
    int selectedLocationID;
    public static final String DEFAULTCOLORVALUE = "#ff2672";
    public static final String DEFAULTLANGUAGE = "EN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(myToolbar);
        jobDirectoryDB = new DBHelper(this);
        CurrentLanguage currentLanguage = new CurrentLanguage();

        String customColorValue = DEFAULTCOLORVALUE;
        String selectedLanguage = DEFAULTLANGUAGE;


        //dataBase creation or update

        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.getWritableDatabase().close();

        //getting sharedPreferences

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);
        String guiPref = sharedPref.getString("guiPref", null);


        //getting intent

        Intent intent = getIntent();
        selectedSpecializationName = intent.getStringExtra("selectedSpecializationName");
        selectedSpecializationID = intent.getIntExtra("selectedSpecializationID", 0);
        selectedLocationName = intent.getStringExtra("selectedLocationName");
        selectedLocationID = intent.getIntExtra("selectedLocationID", 0);

        TextView textViewSpecialization = (TextView) findViewById(R.id.categoryText);
        textViewSpecialization.setText(selectedSpecializationName);

        Intent intent1 = getIntent();
        String selectedLocationName = intent.getStringExtra("selectedLocationName");


        //setting textView

        TextView textViewLocation = (TextView) findViewById(R.id.locationText);
        textViewLocation.setText(selectedLocationName);

        //GUI setting

        if (sharedPref.getString("guiColor", null) != null) {
            customColorValue = sharedPref.getString("guiColor", null);
        } else {
            customColorValue = DEFAULTCOLORVALUE;
        }

        settingGUIColor(customColorValue);


        //Language setting
        if (sharedPref.getString("selectedLanguage", null) != null) {
            selectedLanguage = sharedPref.getString("selectedLanguage", null);
        } else {
            selectedLanguage = DEFAULTLANGUAGE;
        }

        settingLanguage(selectedLanguage);

    }


    public void settingGUIColor(String color) {

        TextView textViewTitle = (TextView) findViewById(R.id.titleText);
        textViewTitle.setTextColor(Color.parseColor(color));

        Button btnCategory = (Button) findViewById(R.id.categoryBtn);
        btnCategory.setBackgroundColor(Color.parseColor(color));

        Button btnLocation = (Button) findViewById(R.id.locationBtn);
        btnLocation.setBackgroundColor(Color.parseColor(color));

        Button btnFind = (Button) findViewById(R.id.findItBtn);
        btnFind.setBackgroundColor(Color.parseColor(color));
        btnFind.setTextColor(Color.parseColor("#FFFFFF"));

    }


    public void settingLanguage(String language) {

        if (language.equals("FR")) {

            TextView textViewTitle = (TextView) findViewById(R.id.titleText);
            textViewTitle.setText(String.format(getString(R.string.main_title_FR)));

            Button btnCategory = (Button) findViewById(R.id.categoryBtn);
            btnCategory.setText(String.format(getString(R.string.main_category_FR)));

            Button btnLocation = (Button) findViewById(R.id.locationBtn);
            btnLocation.setText(String.format(getString(R.string.main_location_FR)));

            Button btnFind = (Button) findViewById(R.id.findItBtn);
            btnFind.setText(String.format(getString(R.string.main_findIt_FR)));

        }

    }


    /**
     * -------- Action bar menu creation
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_main, menu);
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
            /*
            case R.id.action_logout:
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("userName");
                editor.remove("loggedUser");
                editor.commit();
                Intent intent_logout = new Intent(this, MainActivity.class);
                this.startActivity(intent_logout);
                break;
                */
            case R.id.action_about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                this.startActivity(intent_about);
                break;
            case R.id.action_main_data_demo:
                // Demo Data creation --------
                InsertDataDemo insertDataDemo = new InsertDataDemo();
                insertDataDemo.insertDataDemo(this);
                //----------------------------
                Intent intent_main = new Intent(this, MainActivity.class);
                this.startActivity(intent_main);
                break;
            case R.id.action_settings:
                Intent intent_settings = new Intent(this, SettingsActivity.class);
                this.startActivity(intent_settings);
                break;
            /*
            case R.id.action_dbManager:
                Intent intent_dbManager = new Intent(this, AndroidDatabaseManager.class);
                this.startActivity(intent_dbManager);
                break;
                */
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


    /**
     * -------- Activity calls
     */

    public void displayListCategory(View view) {
        Intent intent = new Intent(this, ListCategoryActivity.class);
        intent.putExtra("selectedLocationName", selectedLocationName);
        intent.putExtra("selectedLocationID", selectedLocationID);
        startActivity(intent);
    }

    public void displayListLocation(View view) {
        Intent intent = new Intent(this, ListLocationActivity.class);
        intent.putExtra("selectedSpecializationName", selectedSpecializationName);
        intent.putExtra("selectedSpecializationID", selectedSpecializationID);
        startActivity(intent);
    }

    public void displayListJob(View view) {
        Intent intent = new Intent(this, ListJobActivity.class);
        intent.putExtra("selectedLocationID", selectedLocationID);
        intent.putExtra("selectedSpecializationID", selectedSpecializationID);
        startActivity(intent);

    }

}