package com.jobdirectory.jobdirectory;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobdirectory.DataObjects.JobDescription;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent_2 on 15.04.2017.
 */

public class AdapterJob extends ArrayAdapter<JobDescription> {

    String locationJob;
    int jobIdClickFav;
    ArrayList<Integer> jobFavorites;
    Context context;
    String favoritesText;
    SharedPreferences sharedPref = getContext().getSharedPreferences("localPref", 0);


    public AdapterJob(Context context, List<JobDescription> jobDescriptions) {
        super(context, R.layout.row_list_job, jobDescriptions);


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater ListInflater = LayoutInflater.from(getContext());
        final View customView = ListInflater.inflate(R.layout.row_list_job, parent, false);

        //String singleFoodItem = getItem(position).getJobName();

        /*

        for(int i = 0; i<MainActivity.locations.size();i++){
            if(MainActivity.locations.get(i).getIdLocation()==getItem(position).getFk_LocationId()) {
                locationJob = MainActivity.locations.get(i).getName_location();
            }
        }

        */


        TextView jobNameText = (TextView) customView.findViewById(R.id.jobText);
        TextView companyText = (TextView) customView.findViewById(R.id.companyText);
        TextView locationText = (TextView) customView.findViewById(R.id.locationText);

        jobIdClickFav = getItem(position).getIdJobDescription();

        ImageView img = (ImageView) customView.findViewById(R.id.btnFav);

        //check Favorits

        SharedPreferences sharedPref = getContext().getSharedPreferences("localPref", 0);

        if (sharedPref.getString("jobFavorites", null) != null) {
            favoritesText = sharedPref.getString("jobFavorites", null);
        } else {
            favoritesText = "[]"; //first time running the app
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> arrayListFav = gson.fromJson(favoritesText, type);

        for (int i = 0; i < arrayListFav.size(); i++) {
            if (arrayListFav.get(i) == jobIdClickFav) {
                ImageButton heart = (ImageButton) customView.findViewById(R.id.btnFav);
                heart.setBackgroundResource(R.drawable.ic_favorite_pink_48dp);
            }
        }

        //------------

        img.setOnClickListener(new View.OnClickListener() {
            int addJobId = jobIdClickFav;

            public void onClick(View v) {

                boolean alreadyInFavorites = false;

                //check Favorits

                SharedPreferences sharedPref = getContext().getSharedPreferences("localPref", 0);

                if (sharedPref.getString("jobFavorites", null) != null) {
                    favoritesText = sharedPref.getString("jobFavorites", null);
                } else {
                    favoritesText = "[]"; //first time running the app
                }

                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Integer>>() {
                }.getType();
                ArrayList<Integer> arrayListFav = gson.fromJson(favoritesText, type);

                for (int i = 0; i < arrayListFav.size(); i++) {
                    if (arrayListFav.get(i) == addJobId) {
                        alreadyInFavorites = true;
                    }
                }

                //------------


                if (alreadyInFavorites == false) {
                    addFavorites(addJobId);
                    ImageButton heart = (ImageButton) customView.findViewById(R.id.btnFav);
                    heart.setBackgroundResource(R.drawable.ic_favorite_pink_48dp);
                } else {
                    deleteFavorites(addJobId);
                    ImageButton heart = (ImageButton) customView.findViewById(R.id.btnFav);
                    heart.setBackgroundResource(R.drawable.ic_favorite_border_pink_48dp);
                }

            }
        });

        //CheckBox buckysImage = (CheckBox) customView.findViewById(R.id.imageView2);

        String jobstr = String.valueOf(jobIdClickFav);

        jobNameText.setText(getItem(position).getJobName());
        companyText.setText(getItem(position).getCompanyName());
        locationText.setText(getItem(position).getlocationName());
        //buckysImage.setCh(R.drawable.chunky);
        return customView;

    }

    public void addFavorites(int idJob) {

        if (jobFavorites == null) {
            jobFavorites = new ArrayList<Integer>();
        }

        jobFavorites.add(idJob);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(jobFavorites);
        editor.putString("jobFavorites", jsonFavorites);
        editor.commit();

        Toast.makeText(getContext(), "added to favorites", Toast.LENGTH_LONG).show();
    }


    public void deleteFavorites(int idJob) {

        //check Favorits

        SharedPreferences sharedPref = getContext().getSharedPreferences("localPref", 0);
        String favoritesText = sharedPref.getString("jobFavorites", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> arrayListFav = gson.fromJson(favoritesText, type);

        //------------
        for (int i = 0; i < arrayListFav.size(); i++) {
            if (arrayListFav.get(i) == idJob) {
                arrayListFav.remove(i);
            }
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        String jsonFavorites = gson.toJson(arrayListFav);
        editor.putString("jobFavorites", jsonFavorites);
        editor.commit();
        Toast.makeText(getContext(), "removed from favorites", Toast.LENGTH_LONG).show();
    }


}
