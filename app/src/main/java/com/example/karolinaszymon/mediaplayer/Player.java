package com.example.karolinaszymon.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Karo2 on 2016-12-03.
 */

public class Player {
    private MediaPlayer mp;
    private Context context;

    public Player(Context c) {
        context = c;
        mp = MediaPlayer.create(context, R.raw.laochewojenka);
    }

    public void playOrStopSound(){
        if(mp.isPlaying()) {
            mp.pause();
        }
        else {
            mp.start();
        }
        
    }

    /** jak będzie klasa do szukania plików to wtedy wszystko przebudujemy ;p - pełen profesjonalizm by Karolina **/
    public void connectWithSeekBar() {
        mp.getDuration();

    }
}
