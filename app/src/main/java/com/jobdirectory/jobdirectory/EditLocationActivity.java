package com.jobdirectory.jobdirectory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.cloud.EndpointsLocationAsyncTaskInsert;
import com.jobdirectory.db.DBHelper;
import com.jobdirectory.db.adapter.LocationDataSource;

public class EditLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }


    public void saveLocation(View view) {

        EditText locationNameText = (EditText) findViewById(R.id.locationNameText);
        String locationNameString = locationNameText.getText().toString();

        EditText zipText = (EditText) findViewById(R.id.zipText);
        String zipString = zipText.getText().toString();

        LocationDataSource locationDs = new LocationDataSource(this);

        Location newLocation = new Location();
        newLocation.setName_location(locationNameString);
        newLocation.setZipCode(zipString);
        newLocation.setIdLocation((int) locationDs.createLocation(newLocation));

        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.getWritableDatabase().close();


        //*** insert to cloud ***

        com.example.nam.myapplication.backend.locationApi.model.Location locationCloud = new com.example.nam.myapplication.backend.locationApi.model.Location();

        locationCloud.setIdLocation((long) newLocation.getIdLocation());
        locationCloud.setNameLocation(locationNameString);
        locationCloud.setZipCode(zipString);

        new EndpointsLocationAsyncTaskInsert(locationCloud).execute();


        Toast.makeText(getApplicationContext(), "location added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ListLocationActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
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

}
