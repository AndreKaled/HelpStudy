package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helpstudy.R;
public class LeituraTarefa extends Fragment {

    private View view;


    private TextView tituloTarefa, descricaoTarefa, descricaoTexto, tituloData;

    private EditText data;
    private String tituloBundle, descricaoBundle, dataBundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leitura_tarefa, container, false);
        Bundle bundle = getArguments();
        tituloTarefa = view.findViewById(R.id.tituloTarefa);
        descricaoTarefa = view.findViewById(R.id.descricaoTarefa);
        descricaoTexto = view.findViewById(R.id.descricaoTexto);
        tituloData = view.findViewById(R.id.dataTarefa);
        data = view.findViewById(R.id.editTextDate);

       if(bundle != null){

           tituloBundle = getArguments().getString("tituloTarefa");
           descricaoBundle = getArguments().getString("descricaoTarefa");
           dataBundle = getArguments().getString("dataTarefa");

           tituloTarefa.setText(tituloBundle);
           descricaoTexto.setText(descricaoBundle);
           data.setText(dataBundle);
       }
        return view;
    }
}