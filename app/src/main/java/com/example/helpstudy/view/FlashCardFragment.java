package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;

import java.util.List;

public class FlashCardFragment extends Fragment {


    private ListaFlashCardAdapter listaFlashCardAdapter;
    private ListView listViewFlashCard;
    private View view;

    private List<FlashCard> listaFlash;
    private ListaFlashCardAdapter adapter;
    ControllerFlashCard controllerFlashCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_flashcard);
        adapter = new ListaFlashCardAdapter(getContext());

        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                abrirModal();

                /*TextView viewTitulo, viewResposta;

                String titulo = "AAA", resposta = "bb";

                viewTitulo = view.findViewById(R.id.edit1);

                titulo = viewTitulo.getText().toString();

                viewResposta = view.findViewById(R.id.edit2);

                resposta = viewResposta.getText().toString();

                controleFlashCard.cadastrar(titulo, resposta);

                atualizarLista();*/
            }
        });


        listViewFlashCard.setAdapter(adapter);

        ListView listView = (ListView) view.findViewById(R.id.lista_de_flashcards);
        return view;
    }

    /*private void atualizarLista() {

        listaFlash = ControllerFlashCard.buscarTodos();
        adapter = new ListaFlashCardAdapter(this, R.layout.item_lista_flashcards, listaFlash);
        listViewFlashCard.setAdapter(adapter);
    } */

    private void abrirModal() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi");
    }

}