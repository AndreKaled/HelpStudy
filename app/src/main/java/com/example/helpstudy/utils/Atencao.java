package com.example.helpstudy.utils;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.fragment.app.FragmentManager;

import com.example.helpstudy.view.dialog.AlertaEstudo;

public class Atencao{

    private SensorManager sensor;
    private Vibrator vibrador;
    private SensorEventListener listener;
    private boolean condicao = false;
    public static final int TEMPO = 3000;
    private FragmentManager fragment;
    public Atencao(Context context, FragmentManager fragment) {
        sensor = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        vibrador = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        this.sensor = sensor;
        this.fragment = fragment;
        iniciar();
    }

    public void naoDetectar(){
        condicao = false;
    }

    public void detectar(){
        condicao = true;
    }

    public void iniciar(){
        Sensor giroscopio = sensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(giroscopio == null){
            Log.i("SENSORES", "Não há giroscópio no dispositivo!");
        }else{
            Log.i("SENSORES", "Giroscópio detectado no dispositivo!");
            VibrationEffect efeito = VibrationEffect.createOneShot(TEMPO, VibrationEffect.EFFECT_HEAVY_CLICK);
            listener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                        if(condicao&&(sensorEvent.values[0] >= 0.30||sensorEvent.values[1] >= 0.30||sensorEvent.values[2] >= 0.30)){
                            Log.i("SENSORES", "MOVEU!");
                            AlertaEstudo dialog = new AlertaEstudo();
                            dialog.show(fragment, "oi2");
                            vibrador.vibrate(efeito);
                        }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensor.registerListener(listener, giroscopio, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

}
