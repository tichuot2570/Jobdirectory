package com.jobdirectory.jobdirectory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jobdirectory.DataObjects.Specialization;

import java.util.List;

/**
 * Created by Vincent_2 on 15.04.2017.
 */

public class AdapterSpecialization extends ArrayAdapter<Specialization> {

    public AdapterSpecialization(Context context, List<Specialization> specializations) {
        super(context, R.layout.row_list_search, specializations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater ListInflater = LayoutInflater.from(getContext());
        View customView = ListInflater.inflate(R.layout.row_list_search, parent, false);

        String singleFoodItem = getItem(position).getSpec_Title();
        TextView buckyText = (TextView) customView.findViewById(R.id.buckysText);
        //CheckBox buckysImage = (CheckBox) customView.findViewById(R.id.imageView2);

        buckyText.setText(singleFoodItem);
        //buckysImage.setCh(R.drawable.chunky);
        return customView;

    }

}
