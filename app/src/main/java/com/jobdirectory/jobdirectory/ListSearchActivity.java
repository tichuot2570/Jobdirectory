package com.jobdirectory.jobdirectory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_search);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String[] categories = {"Information technology", "Bank", "Health", "Design", "Hospitality"};
        ListAdapter searchListAdapter = new CustomAdapter(this, categories);
        ListView CategoryListView = (ListView) findViewById(R.id.listView1);
        CategoryListView.setAdapter(searchListAdapter);


        CategoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String category = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ListSearchActivity.this, category, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return  super.onCreateOptionsMenu(menu);
    }

}
