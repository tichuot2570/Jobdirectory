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
import android.widget.Toast;

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.db.adapter.CategoryDataSource;


import java.util.ArrayList;

public class ListCategoryActivity extends AppCompatActivity {

    int selectedLocation;
    int selectedItem;
    String selectedItemName;
    String selectedLocationName;
    int selectedLocationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        CategoryDataSource categoryDataSource = new CategoryDataSource(this);

        ArrayList<Category> categories = categoryDataSource.getAllCategory();

        ListAdapter searchListAdapter = new AdapterCategory(this, categories);

        ListView CategoryListView = (ListView) findViewById(R.id.listView1);

        CategoryListView.setAdapter(searchListAdapter);


        Intent intent = getIntent();
        selectedLocationName = intent.getStringExtra("selectedLocationName");
        selectedLocationID = intent.getIntExtra("selectedLocationID", 0);




        CategoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String category = String.valueOf(parent.getItemAtPosition(position));

                        Category selectedFromList = (Category) parent.getAdapter().getItem(position);
                        String str = selectedFromList.getName_category();
                        selectedItem = selectedFromList.getIdCategory();
                        selectedItemName = selectedFromList.getName_category();

                        Toast.makeText(ListCategoryActivity.this, str, Toast.LENGTH_LONG).show();

                        displaySpecialization(view);
                    }
                }
        );
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


    public void displaySpecialization(View view) {
        Intent intent = new Intent(this, ListSpecializationActivity.class);
        intent.putExtra("selectedLocationName", selectedLocationName);
        intent.putExtra("selectedLocationID", selectedLocationID);
        intent.putExtra("selectedItem", selectedItem);
        intent.putExtra("selectedItemName", selectedItemName);
        startActivity(intent);
    }
}


