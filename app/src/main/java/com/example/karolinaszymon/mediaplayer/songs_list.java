package com.example.karolinaszymon.mediaplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class songs_list extends AppCompatActivity {

    ListView songsListView;
    Player player;

    ImageButton buttonPlay;

    TextView textViewSongTitle;
    TextView textViewCurrentTime;
    TextView textViewTotalTime;

    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        this.player = PlayerManager.getPlayer();

        init();
    }

    private void init(){
        initList();
        initUIComponents();

    }

    private void initList(){
        songsListView = (ListView) findViewById(R.id.songsListView);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        final SongsStorage songsStorage = new SongsStorage();

        SimpleAdapter adapter = new SimpleAdapter(this, songsStorage.getSongsList(),
                android.R.layout.simple_list_item_2,
                new String[] {"title", "album"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});


        songsListView.setAdapter(adapter);
        songsListView.setClickable(true);
        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File song = songsStorage.getSelectedFile(position);
                player.startNewSong(song);
                buttonPlay.setActivated(!player.getIsPaused());
            }
        });
    }

    private void initUIComponents() {

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(clickPlay);
        buttonPlay.setActivated(!player.getIsPaused());

        textViewSongTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewCurrentTime = (TextView) findViewById(R.id.textViewCurrentTime);
        //textViewCurrentTime.setText(player.getCurrentTime());

        textViewTotalTime = (TextView) findViewById(R.id.textViewTotalTime);
        textViewTotalTime.setText(player.getTotalDuration());

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        SeekBarListener seekBarListener = PlayerManager.getSeekBarListener();
        seekBarListener.setTextView(textViewCurrentTime);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        //seekBar.setProgress(player.);

    }

    private View.OnClickListener clickPlay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setActivated(!v.isActivated());
            player.playOrStopSound();
            textViewTotalTime.setText(player.getTotalDuration());
        }
    };
}
