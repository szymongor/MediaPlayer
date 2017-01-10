package com.example.karolinaszymon.mediaplayer;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Szymon on 19.12.2016.
 */

public class SongsStorage {

    List<Map<String, String>> data;



    public SongsStorage(){
        data = new ArrayList<Map<String, String>>();
        HashSet<String> sd = getExternalMounts();
        for (String card: sd){
            String cardName = card.split("/")[3];
            File root = new File("/storage/"+cardName);
            makeSongList(findSongs(root));
        }

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        makeSongList(findSongs(root));

    }

    public ArrayList<File> findSongs(File root){
        ArrayList<File> songsList = new ArrayList<File>();
        File[] files = root.listFiles();
        try{
            for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                songsList.addAll(findSongs(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3")){
                    songsList.add(singleFile);
                }
            }
        }
        }catch (Exception e){

        }
        return songsList;
    }

    public void makeSongList(ArrayList<File> songFileList){
        for (File songFile : songFileList){
            Map<String, String> datum = new HashMap<String, String>(3);
            datum.put("title", songFile.getName());
            datum.put("album", getAlbumName(songFile));
            datum.put("path", songFile.getAbsolutePath());
            data.add(datum);
        }
    }

    public static String getAlbumName(File song){
        String[] parent = song.getParent().split("/");
        if(parent.length > 0){
            return parent[parent.length -1];
        }
        else{
            return "";
        }

    }

    public List<Map<String, String>> getSongsList(){
        return data;
    }

    public static HashSet<String> getExternalMounts() {
        final HashSet<String> out = new HashSet<String>();
        String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String s = "";
        try {
            final Process process = new ProcessBuilder().command("mount")
                    .redirectErrorStream(true).start();
            process.waitFor();
            final InputStream is = process.getInputStream();
            final byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                s = s + new String(buffer);
            }
            is.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // parse output
        final String[] lines = s.split("\n");
        for (String line : lines) {
            if (!line.toLowerCase(Locale.US).contains("asec")) {
                if (line.matches(reg)) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        if (part.startsWith("/"))
                            if (!part.toLowerCase(Locale.US).contains("vold"))
                                out.add(part);
                    }
                }
            }
        }
        return out;
    }

    public File getSelectedFile(int index){
        File songFile = new File(data.get(index).get("path"));
        return songFile;
    }
}
