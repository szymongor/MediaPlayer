package com.example.karolinaszymon.mediaplayer;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class playlists extends AppCompatActivity {

    private AudioManager audio;

    Player player;

    ImageButton buttonPlay;

    TextView textViewSongTitle;
    TextView textViewCurrentTime;
    TextView textViewTotalTime;

    SeekBar seekBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        this.player = PlayerManager.getPlayer();
        audio = this.player.getAudioManager();
        init();
    }

    private void init(){
        initUIComponents();

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
