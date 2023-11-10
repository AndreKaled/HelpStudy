package com.example.helpstudy.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.utils.MensagemBar;

public class AddTarefaFragment extends DialogFragment {

    private View view;
    private ControllerTarefas controlerTarefas;
    private Button bt;
    private int cont = 0;

    private TextView viewTitulo, viewDescricao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_create_tarefa, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        controlerTarefas = new ControllerTarefas(getContext());

        viewTitulo = view.findViewById(R.id.addTextTituloTarefa);
        viewDescricao = view.findViewById(R.id.addTextRespostaTarefa);

        bt = view.findViewById(R.id.btn_criar_tarefas);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });


    }

    private void validarCampos() {
        String titulo = viewTitulo.getText().toString();
        String descricao = viewDescricao.getText().toString();
        if (viewTitulo.getText().toString().isEmpty())
            viewTitulo.setError("O título não pode ser vazia!");
        if (viewDescricao.getText().toString().isEmpty() && cont == 0){
            viewDescricao.setError("Está certo de que não deve ter uma descrição?");
            cont++;
        }else{
            controlerTarefas.cadastrar(titulo,descricao, null, false);
            cont= 0;
            MensagemBar msg = new MensagemBar(getActivity().findViewById(R.id.fragment_tarefas),"Tarefa adicionada");
            msg.defineSnackLongo();
            msg.mostrar();

            dismiss();
        }
    }

}
