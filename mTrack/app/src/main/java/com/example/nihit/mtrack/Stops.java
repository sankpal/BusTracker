package com.example.nihit.mtrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Stops extends AppCompatActivity {

    String stops;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        listView=(ListView)findViewById(R.id.listView2);
        stops = getIntent().getExtras().getString("data");

        String[] path1=stops.split("-");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.item,path1);
        listView.setAdapter(arrayAdapter);

    }
}
