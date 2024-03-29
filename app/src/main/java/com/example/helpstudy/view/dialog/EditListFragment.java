package com.example.helpstudy.view.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.utils.MensagemBar;
import com.example.helpstudy.view.fragments.ListaFragment;

public class EditListFragment extends DialogFragment {

    private long idLista;
    private ControllerListas ctrl;
    private Listas l;

    public EditListFragment(Listas listas, ControllerListas c){
        l = listas;
        ctrl = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_list_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTitulo = view.findViewById(R.id.editTextTituloList);
        editTitulo.setText(l.getTitulo());
        Button btn = view.findViewById(R.id.btnCriarLista);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setTitulo(editTitulo.getText().toString());
                ctrl.atualizar(l);
                ListaFragment.updateView();
                dismiss();
                MensagemBar msg = new MensagemBar(new ListaFragment().findLayoutView(), "Lista alterada!");
                msg.defineSnackLongo();
                msg.mostrar();
            }
        });
    }
}