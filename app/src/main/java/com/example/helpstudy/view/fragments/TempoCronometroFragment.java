package com.example.helpstudy.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.helpstudy.R;
import com.example.helpstudy.view.fragments.CronometroFragment;

public class TempoCronometroFragment extends DialogFragment {

    public final int NUMERO_MAXIMO_SEGUNDO = 59,
            NUMERO_MAXIMO_MINUTO = 59,
            NUMERO_MINIMO_MINUTO = 1,
            NUMERO_MINIMO_SEGUNDO = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tempo_cronometro, container, false);
        NumberPicker numMin = view.findViewById(R.id.editTempoMinutoCronometro);
        NumberPicker numSec = view.findViewById(R.id.editTempoSegundoCronometro);

        numSec.setMinValue(NUMERO_MINIMO_SEGUNDO);
        numSec.setMaxValue(NUMERO_MAXIMO_SEGUNDO);
        numMin.setMinValue(NUMERO_MINIMO_MINUTO);
        numMin.setMaxValue(NUMERO_MAXIMO_MINUTO);

        numMin.setValue(10);
        numSec.setValue(00);

        Button btnConfirma = view.findViewById(R.id.btn_confirmar_tempo_cronometro);
        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                CronometroFragment.mudaText(numMin.getValue(), numSec.getValue());
            }
        });
        return view;
    }
}