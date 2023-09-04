package com.example.helpstudy.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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

    private ListView listViewFlashCard;
    private View view;
    List<FlashCard> listfla = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_flashcard);
        listViewFlashCard = view.findViewById(R.id.lista_de_flashcards);


        listfla = ControllerFlashCard.getInstancia().buscarTodos();
//        listfla.add(new FlashCard("texto", "oi"));
//        listfla.add(new FlashCard("texto", "oi"));
//        listfla.add(new FlashCard("texto", "oi"));
//        listfla.add(new FlashCard("texto", "oi"));
//        listfla.add(new FlashCard("texto", "oi"));


        ArrayAdapter<FlashCard> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, listfla
        );

//        adapter.notifyDataSetChanged();
        listViewFlashCard.setAdapter(adapter);

        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    private void abrirModal() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi");
    }

}