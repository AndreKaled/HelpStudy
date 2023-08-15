package com.example.helpstudy.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class FlashCardFragment extends Fragment {


    private ListaFlashCardAdapter listaFlashCardAdapter;

    private ListView listViewFlashCard;
    private View view;

//    List<FlashCard> listfla = (List<FlashCard>) ControllerFlashCard.getInstancia().buscarTodos();


//    ArrayAdapter arrayAdapter = new ArrayAdapter<FlashCard>(getActivity(), android.R.layout.simple_list_item_1, listfla);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_flashcard);
        listViewFlashCard = view.findViewById(R.id.lista_de_flashcards);


//      System.out.println(listfla);

//      listViewFlashCard.setAdapter(arrayAdapter);
        //arrayListFlashCard = (ArrayList<FlashCard>) ControllerFlashCard.getInstancia().buscarTodos();
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();
            }
        });
        ListView listView = view.findViewById(R.id.lista_de_flashcards);
        return view;
    }
    private void abrirModal() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi");
    }

}