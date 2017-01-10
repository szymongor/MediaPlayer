package com.example.karolinaszymon.mediaplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Szymon on 10.01.2017.
 */

public class PlayListStorage {

    List<Playlist> playlistListists;
    int selectedPlayListIndex;
    boolean addToSelected = false;

    public PlayListStorage(){
        selectedPlayListIndex = -1;
        playlistListists = new ArrayList<Playlist>();
    }

    public void addPlayList(Playlist pl){
        playlistListists.add(pl);
    }

    public List<Map<String, String>> getListsNames(){
        List<Map<String, String>> names = new ArrayList<Map<String, String>>();
        for (Playlist pl: playlistListists) {
            names.add(pl.getName());
        }
        return names;

    }

    public Playlist getSelectedPlayList(){
        if(selectedPlayListIndex < 0){
            return null;
        }else{
            return playlistListists.get(selectedPlayListIndex);
        }
    }

    public void selectPlayList(int index){
        this.selectedPlayListIndex = index;
    }

    public void setAdding(boolean b){
        addToSelected = b;
    }

    public boolean adding(){
        return addToSelected;
    }
}
