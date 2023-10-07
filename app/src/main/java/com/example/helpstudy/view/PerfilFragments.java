package com.example.helpstudy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.helpstudy.R;
import com.example.helpstudy.datasource.DataSource;

public class PerfilFragments extends Fragment {

    Button bt;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_conquistas, container, false);

        bt = (Button) rootView.findViewById(R.id.buttonTesteBackup);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataSource(getContext()).fazerBackup();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}