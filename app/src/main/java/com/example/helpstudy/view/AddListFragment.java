package com.example.helpstudy.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;

public class AddListFragment extends DialogFragment {

    private View view;
    private ControllerListas controlerLista = ControllerListas.getInstancia();
    private Button bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_create_list, container, false);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        bt = view.findViewById(R.id.idDialogList);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView viewTitulo, viewDescricao;

                String titulo = "AAA", descricao = "bb";

                viewTitulo = (EditText) view.findViewById(R.id.editTextTituloList);

                titulo =  viewTitulo.getText().toString();

                viewDescricao = (EditText) view.findViewById(R.id.editTextDescricao);

                descricao = viewDescricao.getText().toString();

                controlerLista.cadastrar(titulo);
                System.out.println("lista cadastrada");

                dismiss();
            }
        });


    }

}