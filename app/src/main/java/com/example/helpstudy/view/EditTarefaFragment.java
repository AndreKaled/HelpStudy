package com.example.helpstudy.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;

import java.util.Calendar;

public class EditTarefaFragment extends DialogFragment {

    private EditText editTituloTarefa, editDescTarefa, editDataTarefa;
    private Tarefa tarefa;
    private ControllerTarefas c;
    private DatePickerDialog dateDick;

    public EditTarefaFragment(Tarefa tarefa, ControllerTarefas c) {
        this.tarefa = tarefa;
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_tarefa, container, false);
        editTituloTarefa = view.findViewById(R.id.tituloTarefaEdit);
        editDescTarefa = view.findViewById(R.id.descricaoTextoEdit);
        editDataTarefa = view.findViewById(R.id.dataEditTarefa);

        initDatePicker();
        editDataTarefa.setText(getDate());

        editDataTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CALENDARIO", "AAA DETECTADO!");
                dateDick.show();
            }
        });


        editTituloTarefa.setText(tarefa.getNome());
        editDescTarefa.setText(tarefa.getDescricao());
        editDataTarefa.setText(tarefa.getDataEntrega());

        return view;
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia)
            {
                mes = mes + 1;
                String date = makeDateString(dia, mes, ano);
                editDataTarefa.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        dateDick = new DatePickerDialog(getContext(), style, dateSetListener, ano, mes, dia);
        dateDick.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    private String makeDateString(int dia, int mes, int ano)
    {
        return dia +" " +getFormatoMes(mes) + " " + ano;
    }

    private String getFormatoMes(int mes)
    {
        if(mes == 1)
            return "JAN";
        if(mes == 2)
            return "FEV";
        if(mes == 3)
            return "MAR";
        if(mes == 4)
            return "ABR";
        if(mes == 5)
            return "MAIO";
        if(mes == 6)
            return "JUN";
        if(mes == 7)
            return "JUL";
        if(mes == 8)
            return "AGO";
        if(mes == 9)
            return "SET";
        if(mes == 10)
            return "OUT";
        if(mes == 11)
            return "NOV";
        if(mes == 12)
            return "DEZ";

        //default should never happen
        return "JAN";
    }
    private String getDate(){

        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        mes++;
        return  makeDateString(dia, mes, ano);
    }
}