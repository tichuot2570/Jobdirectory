package com.jobdirectory.jobdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jobdirectory.cloud.EndpointsJobDescriptionAsyncTaskInsert;
import com.jobdirectory.cloud.EndpointsLocationAsyncTaskInsert;
import com.jobdirectory.cloud.EndpointsSpecializationAsyncTaskInsert;
import com.jobdirectory.db.DBHelper;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.getWritableDatabase().close();

        deleteDatabase(DBHelper.DATABASE_NAME);


        // Import Could Data --------

        new EndpointsLocationAsyncTaskInsert().execute();
        new EndpointsSpecializationAsyncTaskInsert().execute();
        new EndpointsJobDescriptionAsyncTaskInsert().execute();

        // Initial Data creation --------
        InsertDataDemo insertDataDemo = new InsertDataDemo();
        insertDataDemo.insertDataDemo(this);


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
