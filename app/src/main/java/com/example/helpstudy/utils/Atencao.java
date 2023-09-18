package com.example.helpstudy.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Atencao implements Runnable{

    private SensorManager sensor;
    private SensorEventListener listener;
    public Atencao(SensorManager sensor) {
        this.sensor = sensor;
    }

    public void finalizar(){
        try {
            this.finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Sensor giroscopio = sensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(giroscopio == null){
            Log.i("SENSORES", "Não há giroscópio no dispositivo!");
        }else{
            Log.i("SENSORES", "Giroscópio detectado no dispositivo!");
            listener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float[] val = sensorEvent.values.clone();
                    if(val[0] != 0.00||val[1] != 0.00||val[2] != 0.00){
                        //pop up aqui
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }
    }
}
