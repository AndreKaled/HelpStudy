package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.example.helpstudy.R;

public class CronometroFragment extends Fragment {

    /*
    Chronometer chronometer;
    ImageButton btStart, btStop;
    */
    View view;

    public CronometroFragment() {
        // Required empty public constructor
    }

    public static CronometroFragment newInstance(String param1, String param2) {
        CronometroFragment fragment = new CronometroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cronometro, container, false);
        // Inflate the layout for this fragment
        return view;


    }
}