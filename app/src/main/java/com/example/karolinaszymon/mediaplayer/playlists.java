package com.example.karolinaszymon.mediaplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class playlists extends AppCompatActivity {

    private AudioManager audio;

    Player player;

    PlayListStorage playListStorage;

    ListView playlistListView;

    ImageButton buttonPlay;
    ImageButton buttonAddToPlaylist;

    TextView textViewSongTitle;
    TextView textViewCurrentTime;
    TextView textViewTotalTime;

    SeekBar seekBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        this.player = PlayerManager.getPlayer();
        this.playListStorage = this.player.getPlayListStorage();
        audio = this.player.getAudioManager();
        init();
    }

    private void init(){
        initUIComponents();
        initList();

    }

    private void initUIComponents() {

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(clickPlay);
        buttonPlay.setActivated(!player.getIsPaused());

        buttonAddToPlaylist = (ImageButton) findViewById(R.id.buttonAddToPlaylist);
        buttonAddToPlaylist.setOnClickListener(clickAddSong);

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

    private void initList(){

        playlistListView = (ListView) findViewById(R.id.songsListView);

        List<Map<String, String>> data = playListStorage.getListsNames();

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"PlayListName", "album"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});


        playlistListView.setAdapter(adapter);
        playlistListView.setClickable(true);
        playlistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playListStorage.selectPlayList(position);
                Intent intent = new Intent(playlists.this,songs_list.class);
                startActivity(intent);
                //File song = songsStorage.getSelectedFile(position);
                //player.startNewSong(song);
                //buttonPlay.setActivated(!player.getIsPaused());
            }
        });
    }

    private View.OnClickListener clickPlay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setActivated(!v.isActivated());
            player.playOrStopSound();
            textViewTotalTime.setText(player.getTotalDuration());
        }
    };

    private View.OnClickListener clickAddSong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(playlists.this,songs_list.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            default:
                return false;
        }
    }
}
