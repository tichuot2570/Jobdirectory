package com.jobdirectory.jobdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jobdirectory.DataObjects.Category;
import com.jobdirectory.DataObjects.Company;
import com.jobdirectory.DataObjects.CurrentLanguage;
import com.jobdirectory.DataObjects.JobDescription;
import com.jobdirectory.DataObjects.Location;
import com.jobdirectory.DataObjects.Specialization;
import com.jobdirectory.DataObjects.User;
import com.jobdirectory.db.Contract;
import com.jobdirectory.db.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper jobDirectoryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        jobDirectoryDB = new DBHelper(this);

        // Data test creation --------

        Category category1 = new Category(1, "Information technology", "Tchnologies de l'information");
        Category category2 = new Category(2, "Bank", "Banque");
        Category category3 = new Category(3, "Health", "Santé");
        ArrayList<Category> categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        Specialization specialization1 = new Specialization(10, 1, "Web development", "Développement web");
        Specialization specialization2 = new Specialization(11, 1, "Network security", "Sécurité réseau");
        ArrayList<Specialization> specializations = new ArrayList();
        specializations.add(specialization1);
        specializations.add(specialization2);

        Company company1 = new Company(1, "E-Rabbit", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Company company2 = new Company(2, "NewLine Media", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        Location location1 = new Location(1, "Sierre", 3960);
        Location location2 = new Location(2, "Sion", 1950);
        ArrayList<Location> locations = new ArrayList();
        locations.add(location1);
        locations.add(location2);

        JobDescription jobDescription1 = new JobDescription(1, 1, 10, 1, "You will be in charge of...", "Vous serez responsable de...");
        JobDescription jobDescription2 = new JobDescription(2, 2, 11, 2, "You will be in charge of...", "Vous serez responsable de...");
        ArrayList<JobDescription> jobDescriptions = new ArrayList();
        jobDescriptions.add(jobDescription1);
        jobDescriptions.add(jobDescription2);

        User user1 = new User(100, "john123", "John", "Doe", "john123", "john@gmail.com", 1, "Recruiter");
        User user2 = new User(101, "lisa123", "Lisa", "Gold", "lisa123", "lisa@gmail.com", 2, "Human ressources");
        ArrayList<User> users = new ArrayList();
        users.add(user1);
        users.add(user2);

        CurrentLanguage currentLanguage = new CurrentLanguage();

        // -----------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_login:
                Intent intent_login = new Intent(this, LoginAndroidActivity.class);
                this.startActivity(intent_login);
                break;
            case R.id.action_about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                this.startActivity(intent_about);
                break;
            case R.id.action_settings:
                Intent intent_settings = new Intent(this, SettingsActivity.class);
                this.startActivity(intent_settings);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }



    /** Called when the user taps the Send button */
    public void displayList(View view) {
        Intent intent = new Intent(this, ListSearchActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }





}