package com.example.helpstudy.view.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.model.Usuario;
import com.example.helpstudy.utils.ROOT;
import com.example.helpstudy.view.activitys.MainActivity;
import com.example.helpstudy.view.activitys.TelaLogin;
import com.google.android.material.snackbar.Snackbar;

public class AddListFragment extends DialogFragment {

    private View view;
    private ControllerListas controlerLista;
    private Button bt;
    private Snackbar snackbar;

    private EditText viewTitulo;
    public AddListFragment(Snackbar snackbar){

        this.snackbar = snackbar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        controlerLista = new ControllerListas(getContext());
        return inflater.inflate(R.layout.dialog_create_list, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        bt = view.findViewById(R.id.idDialogList);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = "AAA";
                viewTitulo = (EditText) view.findViewById(R.id.editTextTituloList);
                titulo =  viewTitulo.getText().toString();
                try {

                    if(validaLista(viewTitulo) == true){

                        dismiss();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private Boolean validaLista(EditText titulo) throws Exception {

        if(titulo == null){

            Toast.makeText(getActivity(), "Título não encontrado", Toast.LENGTH_SHORT).show();

            return false;
        } else if(titulo.getText().toString().isEmpty()){

            titulo.setError("Título não pode ser vazio.");

            return false;
        } else {

            controlerLista.cadastrar(titulo.getText().toString());
            snackbar.show();
            return true;
        }
    }
}