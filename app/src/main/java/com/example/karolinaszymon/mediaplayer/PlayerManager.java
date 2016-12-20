package com.example.karolinaszymon.mediaplayer;

/**
 * Created by Szymon on 19.12.2016.
 */

public final class PlayerManager {
    static Player player;
    static SeekBarListener seekBarListener;

    public static final void setPlayer(Player player){
        PlayerManager.player = player;
    }

    public static final void setSeekBarListener(SeekBarListener seekBarListener){
        PlayerManager.seekBarListener = seekBarListener;
    }

    public static final Player getPlayer(){
        return player;
    }

    public static final SeekBarListener getSeekBarListener(){
        return seekBarListener;
    }

}
