package com.example.own_music;

import android.media.MediaPlayer;
import java.io.IOException;
public class playsong {

    MediaPlayer player;
    public void setdatasourse(String path)
    {
        player=new MediaPlayer();
        try {
            player.setDataSource(path);
            //duration.setText(changetime(player.getDuration()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setseckto(int seck)
    {
        player.seekTo(seck);
    }
    public void setstop()
    {
        player.stop();
    }
    public void setpause()
    {
        player.pause();
    }
    public void setplay()
    {

        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }
    public void playsong()
    {player.start();}
    public void setreset()
    {
        player.reset();
    }
    public int getcurrenpossition()
    {
        return player.getCurrentPosition();

    }
    public boolean check()
    {
        if(player.isPlaying())
            return true;
        else
            return false;
    }
    public int getduration()
    {
       return player.getDuration();

    }
    public int getCurrentPosition()
    {
        return player.getCurrentPosition();
    }




}
