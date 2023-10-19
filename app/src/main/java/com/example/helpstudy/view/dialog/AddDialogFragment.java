package com.example.helpstudy.view.dialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.utils.MensagemBar;

public class AddDialogFragment extends DialogFragment {

    private View view;
    private ControllerFlashCard controllerFlashCard;
    private Button bt;
    private TextView viewTitulo, viewResposta;



    public AddDialogFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        controllerFlashCard = new ControllerFlashCard(getContext());
        return inflater.inflate(R.layout.dialog_create_flashcard, container, false);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        viewTitulo = (EditText) view.findViewById(R.id.addTextTituloFlashCard);
        viewResposta = (EditText) view.findViewById(R.id.addTextRespostaFlashcard);

        bt = view.findViewById(R.id.btnCriarFlashcard);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });
    }
    private void validarCampos(){
        if(viewTitulo.getText().toString().isEmpty())
            viewTitulo.setError("Titulo não pode ser vazio!");
        if(viewResposta.getText().toString().isEmpty())
            viewResposta.setError("Resposta não pode ser vazia");
        if(!(viewTitulo.getText().toString().isEmpty()&&viewResposta.getText().toString().isEmpty())){
            String titulo =  viewTitulo.getText().toString();
            String resposta = viewResposta.getText().toString();
            controllerFlashCard.cadastrar(titulo, resposta);

            MensagemBar msg = new MensagemBar(getActivity().findViewById(R.id.layoutFlashcard),"Flashcard adicionado");
            msg.defineSnackLongo();
            msg.mostrar();

            dismiss();
        }
    }

}