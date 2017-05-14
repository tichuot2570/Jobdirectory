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

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.db.adapter.CategoryDataSource;
import com.jobdirectory.db.adapter.SpecializationDataSource;

import java.util.ArrayList;

public class ListSpecializationActivity extends AppCompatActivity {

    ArrayList<Specialization> specializations;
    int selectedCategory;
    int selectedSpecializationID;
    String selectedSpecializationName;
    String selectedLocationName;
    int selectedLocationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_specialization);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        CategoryDataSource categoryDataSource = new CategoryDataSource(this);

        ArrayList<Category> categories = categoryDataSource.getAllCategory();


        SpecializationDataSource specializationDataSource = new SpecializationDataSource(this);

        Intent intent = getIntent();

        selectedCategory = intent.getIntExtra("selectedItem", 0);
        String selectedItemName = intent.getStringExtra("selectedItemName");
        selectedLocationName = intent.getStringExtra("selectedLocationName");
        selectedLocationID = intent.getIntExtra("selectedLocationID", 0);

        TextView textTitle = (TextView) findViewById(R.id.title);
        textTitle.setText(selectedItemName);

        specializations = specializationDataSource.getSpecializationsFromCategoryId(selectedCategory);

        ListAdapter searchListAdapter = new AdapterSpecialization(this, specializations);

        ListView CategoryListView = (ListView) findViewById(R.id.listView1);
        CategoryListView.setAdapter(searchListAdapter);

        CategoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String category = String.valueOf(parent.getItemAtPosition(position));

                        Specialization selectedFromList = (Specialization) parent.getAdapter().getItem(position);
                        String str = selectedFromList.getSpec_Title();

                        selectedSpecializationName = selectedFromList.getSpec_Title();
                        selectedSpecializationID = selectedFromList.getIdSpecialization();

                        Toast.makeText(ListSpecializationActivity.this, str, Toast.LENGTH_LONG).show();

                        displayList(view);
                    }
                }
        );
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


    public void displayList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        intent.putExtra("selectedLocationName", selectedLocationName);
        intent.putExtra("selectedLocationID", selectedLocationID);
        intent.putExtra("selectedSpecializationName", selectedSpecializationName);
        intent.putExtra("selectedSpecializationID", selectedSpecializationID);
        //intent.putExtra("selectedLocation", selectedLocation);
        startActivity(intent);
    }

}


