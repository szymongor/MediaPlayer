package com.example.karolinaszymon.mediaplayer;

import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Karo2 on 2016-12-06.
 */

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    TextView textViewCurrentTime;
    Player player;

    SeekBarListener(TextView textViewCurrentTime, Player player) {
        this.textViewCurrentTime = textViewCurrentTime;
        this.player = player;
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Log the progress
        Log.d("DEBUG", "Progress is: "+progress);
        //set textView's text
        textViewCurrentTime.setText(Utilities.getCurrentTime(progress, player.getMilliTotalDuration()));
    }

    public void setTextView(TextView tv){
        this.textViewCurrentTime = tv;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {
        player.setCurrentTime(seekBar.getProgress());
    }
}
