package com.example.aayush.appusage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aayush on 9/3/18.
 */

public class InformationAdapter extends ArrayAdapter<Information> {


    //Default Constructor
    public InformationAdapter(Activity context, ArrayList<Information> informationList) {
        super(context, 0, informationList);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        //Check if existing view is being reused, else inflate the view
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Get the current information
        Information currentInfo = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        TextView phone = (TextView)  listItemView.findViewById(R.id.phone);
        TextView dayTime = (TextView) listItemView.findViewById(R.id.dayTime);
        TextView duration = (TextView) listItemView.findViewById(R.id.duration);
        TextView dir = (TextView) listItemView.findViewById(R.id.dir);

        // Set all the info to display
        name.setText("Name: " + currentInfo.getName());
        phone.setText("Number: " + currentInfo.getNumber());
        dayTime.setText("Date: " + currentInfo.getDayTime());
        duration.setText("Duration: " + currentInfo.getDuration() + " " + "seconds");
        dir.setText("Type: " + currentInfo.getDir());

        return listItemView;
    }

}
