package com.example.helpstudy.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Atencao{

    private SensorManager sensor;
    private SensorEventListener listener;
    private boolean condicao = false;
    public Atencao(SensorManager sensor) {
        this.sensor = sensor;
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
            listener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                        if(sensorEvent.values[0] != 0.00||sensorEvent.values[1] != 0.00||sensorEvent.values[2] != 0.00){
                            Log.i("SENSORES", "MOVIMENTOU!");
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
