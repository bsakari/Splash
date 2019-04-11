package com.example.emobilis.elimuassistant;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Previledges extends AppCompatActivity {

    String[] ranks = {"Principal","Bursar","Student","Teacher","Parents"};
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previledges);

        spinner=(Spinner)findViewById(R.id.spRank);

    }
}
