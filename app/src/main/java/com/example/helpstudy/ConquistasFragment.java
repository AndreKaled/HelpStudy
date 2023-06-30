package com.example.helpstudy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.helpstudy.view.TelaCadastro;

public class ConquistasFragment extends Fragment {

    Button bt;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_conquistas, container, false);
        intent = new Intent(getActivity(), TelaCadastro.class);

        bt = (Button) rootView.findViewById(R.id.buttonn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}