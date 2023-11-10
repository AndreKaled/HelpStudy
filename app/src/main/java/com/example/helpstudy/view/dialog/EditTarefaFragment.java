package com.example.helpstudy.view.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.utils.MensagemBar;
import com.example.helpstudy.view.fragments.TarefasFragment;

import java.util.Calendar;

public class EditTarefaFragment extends DialogFragment {

    private EditText editTituloTarefa, editDescTarefa;
    private TextView editDataTarefa;
    private Button btnConfirmar;
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
        btnConfirmar = view.findViewById(R.id.buttonConfirmaTarefaEdit);

        initDatePicker();
        editDataTarefa.setText(getDate());

        editDataTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CALENDARIO", "AAA DETECTADO!");
                dateDick.show();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarefa.setNome(editTituloTarefa.getText().toString());
                tarefa.setDescricao(editDescTarefa.getText().toString());
                tarefa.setDataEntrega(editDataTarefa.getText().toString());
                c.atualizar(tarefa);
                TarefasFragment.updateViews();
                dismiss();
                MensagemBar msg = new MensagemBar(new TarefasFragment().findLayoutView(), "Tarefa alterada!");
                msg.defineSnackLongo();
                msg.mostrar();
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
        return dia +"/" +mes + "/" + ano;
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