package com.example.aayush.appusage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import static android.Manifest.permission.READ_CALL_LOG;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    static InformationAdapter itemsAdapter;
    // Arraylist to store all info
    final ArrayList<Information> info = new ArrayList<Information>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The layout of the list as a whole
        setContentView(R.layout.overview_list);

        // Checking permissions
        if (!checkPermission()) {
            requestPermission();
        }

        // The listview which will display everything
        ListView listView = (ListView) findViewById(R.id.overview_list);

        listView.setAdapter(itemsAdapter);
    }


    // Gets call details
    private InformationAdapter getCallDetails(Context context) {


        @SuppressLint("MissingPermission") Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int name = (cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));

        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            String callDayTime = new Date(Long.valueOf(callDate)).toString();
            String callDuration = cursor.getString(duration);
            String callerName = cursor.getString(name);


            String dir = null;
            int dircode = Integer.parseInt(callType);

            switch (dircode) {

                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "Missed";
                    break;
            }
            // Adding all the parameters to the list through {@link info constructor}
            info.add(new Information(callerName, phNumber, callDayTime, callDuration, dir));
        }

        // Storing all the information in the adapter
        InformationAdapter mItemsAdapter = new InformationAdapter(this, info);
        return mItemsAdapter;
    }


    // Checking if permission is given by user or not
    private boolean checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            // If yes, then continue
            itemsAdapter = getCallDetails(this);
            return true;
        }
    }


    // Requesting permission
    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{READ_CALL_LOG}, PERMISSION_REQUEST_CODE);
    }


    // When the use reacts to the request permission dialog box
    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResult) {

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    startActivity(getIntent());
                } else finish();
        }
    }

}
