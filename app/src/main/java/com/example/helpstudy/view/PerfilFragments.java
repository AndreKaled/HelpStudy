package com.example.helpstudy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.helpstudy.R;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.utils.Preferencias;

public class PerfilFragments extends Fragment {

    ImageView btBackup, btSair;

    Preferencias pref;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_conquistas, container, false);

        btBackup = rootView.findViewById(R.id.buttonTesteBackup);
        btSair =  rootView.findViewById(R.id.sairBt);
        pref = new Preferencias(getContext());
        btBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataSource(getContext()).fazerBackup();
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Preferencias(getContext()).limparPreferencias();
                intent = new Intent();
                intent.setClass(getActivity(), TelaLogin.class);
                getActivity().startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}