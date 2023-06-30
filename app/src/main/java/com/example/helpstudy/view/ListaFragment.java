package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;

public class ListaFragment extends Fragment {

    private View view;
    private ListView listView;
    private ListasAdapter listasAdapter;
    private ControllerListas controllerListas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        return view;
    }
}