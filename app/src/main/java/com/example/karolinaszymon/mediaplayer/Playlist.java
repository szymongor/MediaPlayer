package com.example.karolinaszymon.mediaplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karo2 on 2016-12-06.
 */

public class Playlist {
    private List<Integer> playlist;

    public Playlist() {
        playlist = new ArrayList<Integer>();
        playlist.add(R.raw.budadlaazora);
        playlist.add(R.raw.laochewojenka);
        playlist.add(R.raw.elmismosol);
        playlist.add(R.raw.marysia);
    }

    public void addToPlaylist(int songIndex)
    {
        playlist.add(songIndex);
    }

}
