package com.example.karolinaszymon.mediaplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class songs_list extends AppCompatActivity {

    ListView songsListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        initUIComponents();
    }

    private void initUIComponents(){
        songsListView = (ListView) findViewById(R.id.songsListView);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> datum = new HashMap<String, String>(2);
        datum.put("title", "Lol");
        datum.put("date", "Bol");

        data.add(datum);

        String[] titles = {"Becon", "Ham", "Tuna", "Potato"};
        //String[] desctiptions = {"Pig","Cow","Fish","Brain"};

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "date"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});


        songsListView.setAdapter(adapter);
    }
}
