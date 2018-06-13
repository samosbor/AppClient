package com.example.android.appclient;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner storySpinner = findViewById(R.id.story_lines_spinner);
        Spinner treeSpinner = findViewById(R.id.family_tree_lines_spinner);
        Spinner spouseSpinner = findViewById(R.id.spouse_lines_spinner);
        Spinner mapSpinner = findViewById(R.id.map_type_spinner);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Green");
        colors.add("Yellow");
        colors.add("Red");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        storySpinner.setAdapter(adapter);
        treeSpinner.setAdapter(adapter);
        spouseSpinner.setAdapter(adapter);
        ArrayList<String> mapTypes = new ArrayList<>();
        mapTypes.add("Normal");
        mapTypes.add("Hybrid");
        mapTypes.add("Satellite");
        mapTypes.add("Terrain");
        ArrayAdapter<String> adapterMap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mapSpinner.setAdapter(adapterMap);
    }
}
