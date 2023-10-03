package com.example.helpstudy.view;

import android.app.Dialog;
import android.os.BaseBundle;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        FloatingActionButton btCriar = view.findViewById(R.id.btn_criar_flashcard);
        listViewFlashCard = view.findViewById(R.id.lista_de_flashcards);
        ArrayAdapter<FlashCard> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, listfla
        );

        listfla = new ControllerFlashCard(getContext()).buscarTodos();
        listViewFlashCard.setAdapter(new ListaFlashCardAdapter(getContext()));
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();
                adapter.notifyDataSetChanged();
            }
        });

        listViewFlashCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle args = new Bundle();
                FlashCard flashCard = new ControllerFlashCard(getContext()).buscarPorPosicao(i);
                args.putString("front", flashCard.getTitulo());
                args.putString("back", flashCard.getDescricao());
                ViewFlashCardFragment fragment = new ViewFlashCardFragment();
                fragment.setArguments(args);
                replaceFragment(fragment);
            }
        });
        return view;
    }
    private void abrirModal() {

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}