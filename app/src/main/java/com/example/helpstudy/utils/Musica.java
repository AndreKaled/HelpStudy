package com.example.helpstudy.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.example.helpstudy.R;

public class Musica {


    Context context;
    public static MediaPlayer mediaPlayer;

    public Musica(Context context){


        this.context = context;
        this.mediaPlayer =(MediaPlayer) MediaPlayer.create(context, R.raw.som);
    }
    public void startMusic(){

                mediaPlayer = (MediaPlayer) MediaPlayer.create(context, R.raw.som);


            if(mediaPlayer.isPlaying() == false){

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
            mediaPlayer = null;
            mediaPlayer = (MediaPlayer) MediaPlayer.create(context, R.raw.som);
        }
    }
}
