package com.example.helpstudy.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;

public class FlashCardFragment extends Fragment {


    private ListaFlashCardAdapter listaFlashCardAdapter;
    private ListView listViewFlashCard;
    private View view;
    private ControllerFlashCard controleFlashCard = ControllerFlashCard.getInstancia();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_flashcard);
        listViewFlashCard = view.findViewById(R.id.lista_de_flashcards);
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AddDialogFragment dialog = new AddDialogFragment();
                //dialog.show(getActivity().getSupportFragmentManager(), "oi");
                //inicio do modal

                TextView viewTitulo, viewResposta;

                String titulo = "AAA", resposta = "bb";

                viewTitulo = view.findViewById(R.id.edit1);

                titulo = viewTitulo.getText().toString();

                viewResposta = view.findViewById(R.id.edit2);

                resposta = viewResposta.getText().toString();

                controleFlashCard.cadastrar(titulo, resposta);

                atualizarLista();

            }
        });

        ListView listView = (ListView) view.findViewById(R.id.lista_de_flashcards);
        return view;
    }

    private void atualizarLista() {
        listaFlashCardAdapter = new ListaFlashCardAdapter(view.getContext());
        listViewFlashCard.setAdapter(listaFlashCardAdapter);
    }

}