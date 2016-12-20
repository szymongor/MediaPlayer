package com.example.karolinaszymon.mediaplayer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.progress;

public class MainActivity extends AppCompatActivity {

    Button buttonSongs;

    ImageButton buttonPlay;

    TextView textViewSongTitle;
    TextView textViewCurrentTime;
    TextView textViewTotalTime;

    SeekBar seekBar;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIComponents();

        player = new Player(this, seekBar);
        SeekBarListener seek = new SeekBarListener(textViewCurrentTime, player);
        seekBar.setOnSeekBarChangeListener(seek);
        PlayerManager.setPlayer(player);
        PlayerManager.setSeekBarListener(seek);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initPlay();
    }

    private void initUIComponents() {
        buttonSongs = (Button) findViewById(R.id.buttonSongs);
        buttonSongs.setOnClickListener(clickButtonSongs);

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(clickPlay);


        textViewSongTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewCurrentTime = (TextView) findViewById(R.id.textViewCurrentTime);
        textViewTotalTime = (TextView) findViewById(R.id.textViewTotalTime);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    private void initPlay(){
        buttonPlay.setActivated(!player.getIsPaused());
        SeekBarListener seekBarListener = PlayerManager.getSeekBarListener();
        seekBarListener.setTextView(textViewCurrentTime);
        //textViewCurrentTime.setText(player.getCurrentTime());
        //textViewTotalTime.setText(player.getTotalDuration());
    }

    private View.OnClickListener clickButtonSongs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,songs_list.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener clickPlay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setActivated(!v.isActivated());
            player.playOrStopSound();
            textViewTotalTime.setText(player.getTotalDuration());
        }
    };


}
