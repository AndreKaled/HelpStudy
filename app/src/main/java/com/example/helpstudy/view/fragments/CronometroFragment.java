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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private static Boolean ativado;
    private static CountDownTimer mCountDownTimer;
    public Musica musica;
    private TextView textoComplementar;
    private static boolean mTimerRunning;
    private static long mTimeLeftInMillis;
    private static long mEndTime;
    private static Context context;
    View view;
    Atencao atencao;

    ImageView update_timer, break_time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cronometro, container, false);
        atencao = new Atencao(getContext());
        ativado = false;
        update_timer = view.findViewById(R.id.update_timer);
        context = getContext();
        break_time = view.findViewById(R.id.break_time);
        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        textoComplementar = view.findViewById(R.id.texto_informativo_cronometro);

        update_timer.setOnClickListener(new View.OnClickListener() {
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

                if(ativado == false){

                    ativado = true;
                    mTimerRunning = false;
                    mTimeLeftInMillis = 5 * 60000;
                    textoComplementar.setText("Hora da pausa!");
                    updateCountDownText();
                    updateButtons();
                    update_timer.setVisibility(View.GONE);
                    break_time.setVisibility(View.VISIBLE);

                } else {

                    ativado = false;
                    textoComplementar.setText("Clique para iniciar o pomodoro");
                    mTimeLeftInMillis = 20 * 60000;
                    update_timer.setVisibility(View.VISIBLE);
                    break_time.setVisibility(View.GONE);
                    updateCountDownText();
                    updateButtons();

                    if(mButtonReset.isEnabled() == false){

                        mButtonReset.setEnabled(true);
                    }
                }
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

    protected void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateButtons() {
        if (mTimerRunning) {
            //mButtonReset.setVisibility(View.INVISIBLE);
            //mButtonStartPause.setText("Pause");
            mButtonStartPause.setImageResource(R.drawable.stop);
            if(ativado == false){

                textoComplementar.setText("Hora de focar!");
            } else{

                textoComplementar.setText("Hora da Pausa!");
                mButtonReset.setEnabled(false);
            }
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

                    }else{

                        mMusic.setImageResource(R.drawable.music);
                        mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
                        mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));
                    }
                }
            });


            // quando o cronometro n estiver rodando
        } else {


            if(ativado == false){

                textoComplementar.setText("Clique para iniciar");
                mButtonReset.setEnabled(true);
                mButtonReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryPrimary)));

            } else{

                textoComplementar.setText("Clique para iniciar a pausa");
            }
            //mButtonStartPause.setText("Start");
            mButtonStartPause.setImageResource(R.drawable.play);

            mMusic.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondaryVariant)));
            mMusic.setEnabled(false);
            musica.pauseMusic();


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
                mTimerRunning = false;
                updateButtons();
            } else {
                startTimer();
            }
        }
    }
    public static void mudaText(int min, int sec) {
        mTextViewCountDown.setText(String.format(Locale.getDefault(), "%02d:%02d", min, sec));
        mTimeLeftInMillis = (min * 60000 + sec * 1000);
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;  // Atualize mEndTime
        SharedPreferences share = context.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putLong("millisLeft", mTimeLeftInMillis);
        edit.commit();
    }
}