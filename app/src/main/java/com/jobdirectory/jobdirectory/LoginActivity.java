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

import com.jobdirectory.DataObjects.User;
import com.jobdirectory.db.adapter.UserDataSource;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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


    public void login(View view) {

        Intent intent;


        if (checkUser() == true) {
            intent = new Intent(this, ListJobCompanyActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);

    }

    public boolean checkUser() {

        //--------------------

        User user = new User();
        boolean existingUser;

        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        String userNameString = userNameText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        String passwordString = passwordText.getText().toString();

        UserDataSource userDataSource = new UserDataSource(this);
        ArrayList<User> users = userDataSource.getAllUsers();
        User userBase = new User();

        // fake user in case of an inexisting user
        userBase.setUsername("userBase");
        userBase.setPassword("pwd123**Us#B");
        user = userBase;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(userNameString)) {
                user = users.get(i);
            }
        }

        if (user.getPassword().equals(passwordString)) {
            existingUser = true;

            //Store user in SharedPreferences
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("localPref", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("loggedUser", true);
            editor.putString("userName", user.getUsername());
            editor.commit();
            //--------------------

        } else {
            existingUser = false;
        }

        return existingUser;

    }




}