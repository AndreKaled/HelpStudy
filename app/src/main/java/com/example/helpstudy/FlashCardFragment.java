package com.example.helpstudy;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlashCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashCardFragment extends Fragment {


    private ListaFlashCardAdapter listaFlashCardAdapter;
    private ListView listViewFlashCard;
    private View view;
    private ControllerFlashCard controleFlashCard = ControllerFlashCard.getInstancia();

    public FlashCardFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FlashCardFragment newInstance(String param1, String param2) {
        FlashCardFragment fragment = new FlashCardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

                TextView viewTitulo, viewResposta;
                String titulo = "AAA", resposta = "bb";

                viewTitulo = view.findViewById(R.id.edit1);
                titulo = viewTitulo.getText().toString();

                viewResposta = view.findViewById(R.id.edit2);
                resposta = viewResposta.getText().toString();

                FlashCard flashCard = new FlashCard(titulo, resposta);
                controleFlashCard.cadastrar(flashCard);

                System.out.println(titulo);
                System.out.println(resposta);
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