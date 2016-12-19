package com.example.karolinaszymon.mediaplayer;

/**
 * Created by Szymon on 19.12.2016.
 */

public final class PlayerManager {
    static Player player;

    public static final void setPlayer(Player player){
        PlayerManager.player = player;
    }

    public static final Player getPlayer(){
        return player;
    }

}
