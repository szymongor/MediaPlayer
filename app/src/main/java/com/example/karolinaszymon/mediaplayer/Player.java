package com.example.karolinaszymon.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.widget.SeekBar;

import java.io.File;

/**
 * Created by Karo2 on 2016-12-03.
 */

public class Player {

    private MediaPlayer mp;
    private Context context;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private boolean isPaused = true;

    public Player(Context c, SeekBar sb) {
        context = c;
        seekBar = sb;
        mp = MediaPlayer.create(context, R.raw.marysia);
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
}
