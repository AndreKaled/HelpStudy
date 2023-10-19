package com.example.helpstudy.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
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

        bt = view.findViewById(R.id.btnCriarLista);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewTitulo = (EditText) view.findViewById(R.id.editTextTituloList);
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

    private Boolean validaLista(EditText titulo) throws NullPointerException{
        if(titulo.getText().toString().isEmpty()){
            titulo.setError("Título não pode ser vazio.");
            return false;
        } else {
            controlerLista.cadastrar(titulo.getText().toString());
            snackbar.show();
            return true;
        }
    }
}