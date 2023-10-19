package com.example.helpstudy.view.dialog;

import android.content.Context;
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
import com.example.helpstudy.utils.ROOT;
import com.google.android.material.snackbar.Snackbar;

public class AddListFragment extends DialogFragment {

    private View view;
    private ControllerListas controlerLista;
    private Button bt;

    private Context context;


    public AddListFragment(Context context){

        this.context = context;
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


                TextView viewTitulo, viewDescricao;

                String titulo = "AAA", descricao = "bb";

                viewTitulo = (EditText) view.findViewById(R.id.editTextTituloList);

                titulo =  viewTitulo.getText().toString();

                viewDescricao = (EditText) view.findViewById(R.id.editTextDescricao);

                descricao = viewDescricao.getText().toString();

                controlerLista.cadastrar(titulo);
                dismiss();

                mensagemAviso(view);
            }
        });
    }

    private void mensagemAviso(View view) {

//        View inflateV = getLayoutInflater().inflate(R.layout.fragment_lista, null);
        Snackbar mySnackBar = Snackbar.make(view.findViewById(R.id.pop_upAddList), "aa", Snackbar.LENGTH_LONG);
        mySnackBar.show();

    }
}