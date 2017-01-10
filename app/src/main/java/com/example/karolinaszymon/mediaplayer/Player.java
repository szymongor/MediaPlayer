package com.example.karolinaszymon.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karo2 on 2016-12-03.
 */

public class Player {

    private AudioManager audio;
    private MediaPlayer mp;
    private Context context;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private boolean isPaused = true;

    PlayListStorage playListStorage;

    public Player(Context c, SeekBar sb, AudioManager audio) {
        context = c;
        seekBar = sb;
        mp = MediaPlayer.create(context, R.raw.marysia);
        this.audio = audio;
        this.playListStorage = new PlayListStorage();

        //init values
        this.playListStorage.addPlayList(new Playlist("Play list 1"));
        this.playListStorage.addPlayList(new Playlist("Play list 2"));
        this.playListStorage.addPlayList(new Playlist("Play list 4"));
        //
    }

    public void playOrStopSound(){
        if(mp.isPlaying()) {
            mp.pause();
            isPaused = true;
            handler.removeCallbacks(onEverySecond);
        }
        else {
            mp.start();
            isPaused = false;
            handler.postDelayed(onEverySecond, 1000);
        }
    }

    Runnable onEverySecond = new Runnable() {

        @Override
        public void run() {
            if(!isPaused){
                seekBar.setProgress(Utilities.getProgressPercentage(getCurrentTime(),mp.getDuration()));
                handler.postDelayed(this, 1000);
            }
        }
    };

    public void setCurrentTime(int progress) {
        mp.seekTo(mp.getDuration()*progress/100);
    }

    public int getCurrentTime() {
        return mp.getCurrentPosition();
    }

    public String getTotalDuration()
    {
        return Utilities.milliSecondsToTimer(mp.getDuration());
    }

    public long getMilliTotalDuration()
    {
        return mp.getDuration();
    }

    public boolean getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean is) {
        isPaused = is;
    }

    public void startNewSong(File song){
        //playOrStopSound();
        mp.pause();
        Uri uriSong = Uri.fromFile(song);
        mp = MediaPlayer.create(context,uriSong);
        playOrStopSound();
    }

    public AudioManager getAudioManager(){
        return audio;
    }

    public PlayListStorage getPlayListStorage(){
        return playListStorage;
    }
}
