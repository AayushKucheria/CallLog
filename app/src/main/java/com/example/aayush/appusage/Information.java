package com.example.aayush.appusage;


import java.lang.reflect.Constructor;

/**
 * Created by aayush on 9/3/18.
 */

public class Information {

    private String mNumber, mType, mDate, mDayTime, mDuration, mDir, mName;


     // Constructor to store information about each call log
    Information(String name, String number, String dayTime, String duration, String dir ) {

        mNumber = number;
        mName = name;
        mDayTime = dayTime;
        mDuration = duration;
        mDir = dir;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getName() {

        // To prevent returning of "null"
        if (mName != null)
            return mName;
        else
            return "Unknown";
    }

    public String getDayTime() {
        return mDayTime;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getDir() {
        return mDir;
    }




}
