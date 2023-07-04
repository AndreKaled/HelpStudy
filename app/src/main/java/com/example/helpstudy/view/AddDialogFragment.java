package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerFlashCard;

public class AddDialogFragment extends DialogFragment {

    private View view;
    private ControllerFlashCard controleFlashCard = ControllerFlashCard.getInstancia();
    private Button bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_create, container, false);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        bt = view.findViewById(R.id.idDialog);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView viewTitulo, viewResposta;

                String titulo = "AAA", resposta = "bb";

                viewTitulo = (EditText) view.findViewById(R.id.editTextNome);

                titulo =  viewTitulo.getText().toString();

                viewResposta = (EditText) view.findViewById(R.id.editTextresp);

                resposta = viewResposta.getText().toString();

                controleFlashCard.cadastrar(titulo, resposta);
                System.out.println("cadastrado");

                dismiss();
            }
        });


    }

}