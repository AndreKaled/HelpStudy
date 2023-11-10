package com.example.helpstudy.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.example.helpstudy.R;

public class Musica {
    static Context context;
    public static MediaPlayer mediaPlayer = null;

    public Musica(Context context){
        if(mediaPlayer == null) {
            this.context = context;
            this.mediaPlayer = (MediaPlayer) MediaPlayer.create(context, R.raw.som);
        }
    }
    public void startMusic(){
        if(mediaPlayer.isPlaying() == false){
            mediaPlayer = (MediaPlayer) MediaPlayer.create(context, R.raw.som);
            mediaPlayer.start();
        }
    }
    public Boolean startPause(){
        if(mediaPlayer.isPlaying() == true){
            pauseMusic();
            return true;
        } else {
            startMusic();
            return false;
        }
    }
    public void pauseMusic(){
        if(mediaPlayer.isPlaying()== true){
            mediaPlayer.release();
            mediaPlayer = (MediaPlayer) MediaPlayer.create(context, R.raw.som);
        }
    }
}
