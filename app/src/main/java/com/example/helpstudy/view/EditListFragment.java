package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.model.Listas;

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
        View view = inflater.inflate(R.layout.dialog_create_list, container, false);
        EditText editTitulo = view.findViewById(R.id.editTextTituloList),
                editDesc = view.findViewById(R.id.editTextDescricao);
        editTitulo.setText(l.getTitulo());
        editDesc.setText("sem descrição");

        Button btn = view.findViewById(R.id.idDialogList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setTitulo(editTitulo.getText().toString());
                ctrl.atualizar(l);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}