package com.example.helpstudy.view.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.view.fragments.TarefasFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class LeituraTarefa extends Fragment {

    private View view;

    private TextView tituloTarefa, descricaoTarefa, descricaoTexto, tituloData, data;
    private String tituloBundle, descricaoBundle, dataBundle;

    private Tarefa tarefa;
    private DatePickerDialog dataPicker;

    FloatingActionButton fb;

    public LeituraTarefa(Tarefa tarefa){

        this.tarefa = tarefa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leitura_tarefa, container, false);
//        Bundle bundle = getArguments();
        tituloTarefa = view.findViewById(R.id.tituloTarefa);
        descricaoTarefa = view.findViewById(R.id.descricaoTarefa);
        descricaoTexto = view.findViewById(R.id.descricaoTexto);
        tituloData = view.findViewById(R.id.dataTarefa);
        data = view.findViewById(R.id.data);
        initDatePicker();
        data.setText(tarefa.getDataEntrega());
        tituloTarefa.setText(tarefa.getNome());
        descricaoTexto.setText(tarefa.getDescricao());
        data.setTextColor(getResources().getColor(R.color.black));
        fb = view.findViewById(R.id.voltarBt);

//       if(bundle != null){
//
//           tituloBundle = getArguments().getString("tituloTarefa");
//           descricaoBundle = getArguments().getString("descricaoTarefa");
//           dataBundle = getArguments().getString("dataTarefa");
//
//           tituloTarefa.setText(tituloBundle);
//           descricaoTexto.setText(descricaoBundle);
//       }



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new TarefasFragment());
            }
        });


       data.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               openDatePicker();
           }
       });


        return view;
    }

    private String getDate(){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month++;
        return  makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                data.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        dataPicker = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        dataPicker.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }


    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker()
    {
        dataPicker.show();
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}