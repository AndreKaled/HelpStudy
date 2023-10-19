package com.example.helpstudy.view.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.helpstudy.R;
import com.example.helpstudy.utils.Atencao;
import com.example.helpstudy.utils.Musica;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class CronometroFragment extends Fragment {

    private static long START_TIME_IN_MILLIS = 600000;

    private static TextView mTextViewCountDown;
    private FloatingActionButton mButtonStartPause;
    private FloatingActionButton mButtonReset;

    private FloatingActionButton mMusic;

    private static Boolean testar = true;

    private CountDownTimer mCountDownTimer;

    public Musica musica;

    private boolean mTimerRunning;

    private static long mTimeLeftInMillis;
    private long mEndTime;
    private static Context context;

    View view;

    Atencao atencao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cronometro, container, false);
        atencao = new Atencao(getContext());
        context = getContext();

        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);

        mTextViewCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TempoCronometroFragment().show(FragmentManager.findFragment(view).getFragmentManager(),"alalal");
            }
        });

        mButtonStartPause = view.findViewById(R.id.button_start_pause);
        mButtonReset = view.findViewById(R.id.button_reset);
        mMusic = view.findViewById(R.id.button_music);
        musica = new Musica(getContext());

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                resetTimer();
                mMusic.setImageResource(R.drawable.music);
            }
        });


        return view;
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateButtons();
            }
        }.start();

        mTimerRunning = true;
        updateButtons();
        atencao.detectar();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateButtons();
        atencao.naoDetectar();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
//        mTimeLeftInMillis = Long.parseLong(mTextViewCountDown.getText().toString());
    }

    private void updateButtons() {
        if (mTimerRunning) {
            //mButtonReset.setVisibility(View.INVISIBLE);
            //mButtonStartPause.setText("Pause");
            mButtonStartPause.setImageResource(R.drawable.stop);
            mMusic.setEnabled(true);
            mButtonReset.setEnabled(false);
            mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
            mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
            musica.startMusic();
            mMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(musica.startPause() == true){

                        mMusic.setImageResource(R.drawable.music_off);
                        mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
                        mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));

                        //musica.pauseMusic();

                    }else{

                        mMusic.setImageResource(R.drawable.music);
                        mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
                        mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
                        //musica.startMusic();
                    }
                }
            });

        } else {
            //mButtonStartPause.setText("Start");
            mButtonStartPause.setImageResource(R.drawable.play);
            mButtonReset.setEnabled(true);
            mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
            mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
            mMusic.setEnabled(false);

            musica.pauseMusic();
            if (mTimeLeftInMillis < START_TIME_IN_MILLIS) {
                mButtonReset.setEnabled(true);
                mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
            } else {
                mButtonReset.setEnabled(false);
                mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
            }
        }
    }


    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = getContext().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getContext().getSharedPreferences("prefs", MODE_PRIVATE);

        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateButtons();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }
    }

    public static void mudaText(int min, int sec){
        mTextViewCountDown.setText(String.format(Locale.getDefault(), "%02d:%02d", min, sec));
        mTimeLeftInMillis = (min * 60000 + sec * 1000);
        SharedPreferences share = context.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putLong("millisLeft", mTimeLeftInMillis);
        edit.commit();
    }

}