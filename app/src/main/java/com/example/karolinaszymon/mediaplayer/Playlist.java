package com.example.karolinaszymon.mediaplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karo2 on 2016-12-06.
 */

public class Playlist {
    List<Map<String, String>> songsList;

    private List<Integer> playlist;
    String playListName;

    public Playlist() {
        songsList = new ArrayList<Map<String, String>>();
        playlist = new ArrayList<Integer>();
        playlist.add(R.raw.budadlaazora);
        playlist.add(R.raw.laochewojenka);
        playlist.add(R.raw.elmismosol);
        playlist.add(R.raw.marysia);
        playListName = "a";
    }

    public Playlist(String name) {
        songsList = new ArrayList<Map<String, String>>();
        playlist = new ArrayList<Integer>();
        playListName = name;
    }

    public void addToPlaylist(int songIndex)
    {
        playlist.add(songIndex);
    }


    public void addSong(String a, String b, String c){
        Map<String, String> datum = new HashMap<String, String>(3);
        datum.put("title", a);
        datum.put("album", b);
        datum.put("path", c);
        songsList.add(datum);
    }

    public void addSong(File song){
        Map<String, String> datum = new HashMap<String, String>(3);
        datum.put("title", song.getName());
        datum.put("album", SongsStorage.getAlbumName(song));
        datum.put("path", song.getAbsolutePath());
        songsList.add(datum);
    }

    public Map<String, String> getName(){
        Map<String, String> mapName = new HashMap<String, String>(3);
        mapName.put("PlayListName", playListName);
        mapName.put("album", "");
        mapName.put("path", "");

        return mapName;
    }

    public ArrayList<Map<String, String>> getSongs(){
        return (ArrayList<Map<String, String>>)songsList;
    }

}
