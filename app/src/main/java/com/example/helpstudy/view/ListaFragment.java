package com.example.helpstudy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.utils.ROOT;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private View view;
    private ListView listView;

    ListasAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_listas);
        listView = view.findViewById(R.id.listview_listas);

        adapter = new ListasAdapter(getContext());

        listView.setAdapter(adapter);

        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ControllerTarefas.setListaSelecionada(ControllerListas.buscarPorPosicao(i).getId());
                ControllerTarefas.getInstancia();
                new ROOT(getContext()).sincTarefas();
                replaceFragment(new TarefasFragment());
            }
        });

        return view;
    }

    private void abrirModal() {

        AddListFragment dialog = new AddListFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi2");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}