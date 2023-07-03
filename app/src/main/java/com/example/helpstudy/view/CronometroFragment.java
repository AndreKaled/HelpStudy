package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.example.helpstudy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CronometroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CronometroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Chronometer chronometer;
    ImageButton btStart, btStop;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CronometroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CronometroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CronometroFragment newInstance(String param1, String param2) {
        CronometroFragment fragment = new CronometroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cronometro, container, false);
        // Inflate the layout for this fragment
        return view;


    }
}