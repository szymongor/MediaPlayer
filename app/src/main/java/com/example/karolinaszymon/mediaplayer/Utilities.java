package com.example.karolinaszymon.mediaplayer;

/**
 * Created by Karo2 on 2016-12-05.
 */

public class Utilities {

    public static String milliSecondsToTimer(long milliseconds){
        String totalTimeString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            totalTimeString = hours + ":";
        }
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}

        totalTimeString = totalTimeString + minutes + ":" + secondsString;

        return totalTimeString;
    }

    public static String getCurrentTime(int percent, long milliseconds) {
        long currentMilliseconds = (percent * milliseconds) / 100;
        return milliSecondsToTimer(currentMilliseconds);
    }

    public static int getProgressPercentage(long currentTime, long totalTime){
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentTime / 1000);
        long totalSeconds = (int) (totalTime / 1000);

        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;

        // return percentage
        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalTime) {
        int currentTime = 0;
        totalTime = (int) (totalTime / 1000);
        currentTime = (int) ((((double)progress) / 100) * totalTime);

        return currentTime * 1000;
    }
}
