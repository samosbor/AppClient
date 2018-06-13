package com.example.android.appclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    private String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventID = getIntent().getStringExtra("EventID");

        FragmentManager fm = getSupportFragmentManager();
        Fragment mapFrag;
        mapFrag = new MapFragment();
        fm.beginTransaction().add(R.id.map, mapFrag).commit();

        TextView eventDetails = findViewById(R.id.event_details);
        eventDetails.setText(eventID);
    }
}
