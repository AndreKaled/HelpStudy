package com.example.helpstudy.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.view.adapters.ListaFlashCardAdapter;
import com.example.helpstudy.view.dialog.AddDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FlashCardFragment extends Fragment {

    private static ListView listViewFlashCard;
    private static View view;
    private static ListaFlashCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        FloatingActionButton btCriar = view.findViewById(R.id.btn_criar_flashcard);
        listViewFlashCard = view.findViewById(R.id.lista_de_flashcards);
        adapter = new ListaFlashCardAdapter(getContext());
        listViewFlashCard.setAdapter(adapter);
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();
                adapter.notifyDataSetChanged();


                Snackbar mySnackBar = Snackbar.make(view.findViewById(R.id.layoutFlashcard), "aa", Snackbar.LENGTH_LONG);
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

        Snackbar mySnackBar = Snackbar.make(view.findViewById(R.id.layoutFlashcard), "Flashcard adicionado!", Snackbar.LENGTH_LONG);
        mySnackBar.setAction("Fechar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Saiu", Toast.LENGTH_SHORT).show();
            }
        });

        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi");
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void mensagemAviso(View view) {

//        View inflateV = getLayoutInflater().inflate(R.layout.fragment_lista, null);
        Snackbar mySnackBar = Snackbar.make(view.findViewById(R.id.layoutFlashcard), "Adicionado", Snackbar.LENGTH_LONG);


        mySnackBar.show();

    }

    public static void updateViews(){
        listViewFlashCard.invalidateViews();
    }
}