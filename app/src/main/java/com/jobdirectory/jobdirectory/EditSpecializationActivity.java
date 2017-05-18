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
import android.widget.TextView;
import android.widget.Toast;

import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.cloud.EndpointsSpecializationAsyncTaskInsert;
import com.jobdirectory.db.DBHelper;
import com.jobdirectory.db.adapter.SpecializationDataSource;

public class EditSpecializationActivity extends AppCompatActivity {

    int selectedCategoryID;
    String selectedCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specialization);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();

        selectedCategoryID = intent.getIntExtra("selectedCategoryID", 0);
        selectedCategoryName = intent.getStringExtra("selectedCategoryName");

        TextView textCategory = (TextView) findViewById(R.id.textCategory);
        textCategory.setText(selectedCategoryName);

    }


    public void saveSpecialization(View view) {

        EditText specializationText = (EditText) findViewById(R.id.specializationText);
        String specializationString = specializationText.getText().toString();

        SpecializationDataSource specializationDs = new SpecializationDataSource(this);

        Specialization newSpecialization = new Specialization();
        newSpecialization.setSpec_Title(specializationString);
        newSpecialization.setFk_CategoryId(selectedCategoryID);
        newSpecialization.setSpec_TitleFR("");
        newSpecialization.setSpec_Description("");
        newSpecialization.setSpec_DescriptionFR("");
        newSpecialization.setIdSpecialization((int) specializationDs.createSpecialization(newSpecialization));


        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.getWritableDatabase().close();


        //*** insert to cloud ***

        com.example.nam.myapplication.backend.specializationApi.model.Specialization specializationCloud = new com.example.nam.myapplication.backend.specializationApi.model.Specialization();

        specializationCloud.setIdSpecialization((long) newSpecialization.getIdSpecialization());
        specializationCloud.setSpecTitle(specializationString);
        specializationCloud.setFkCategoryId(selectedCategoryID);
        specializationCloud.setSpecTitleFR("");
        specializationCloud.setSpecDescription("");
        specializationCloud.setSpecDescriptionFR("");

        new EndpointsSpecializationAsyncTaskInsert(specializationCloud).execute();


        Toast.makeText(getApplicationContext(), "specialization added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ListCategoryActivity.class);
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
