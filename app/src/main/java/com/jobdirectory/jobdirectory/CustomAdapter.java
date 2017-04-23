package com.jobdirectory.jobdirectory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Vincent_2 on 15.04.2017.
 */

public class CustomAdapter extends ArrayAdapter<String>{

    public CustomAdapter(Context context, String[] foods) {
        super(context,R.layout.custom_row_list_search, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater ListInflater = LayoutInflater.from(getContext());
        View customView = ListInflater.inflate(R.layout.custom_row_list_search, parent, false);

        String singleFoodItem = getItem(position);
        TextView buckyText = (TextView) customView.findViewById(R.id.buckysText);
        CheckBox buckysImage = (CheckBox) customView.findViewById(R.id.imageView2);

        buckyText.setText(singleFoodItem);
        //buckysImage.setCh(R.drawable.chunky);
        return customView;

    }

}
