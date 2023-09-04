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

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private View view;
    private ListView listView;

    List<Listas> listfla = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        Button btCriar = view.findViewById(R.id.btn_criar_listas);
        listView = view.findViewById(R.id.listview_listas);
        listfla = ControllerListas.getInstancia().buscarTodos();

        ArrayAdapter<Listas> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, listfla
        );


//        ListaFlashCardAdapter adapter = new ListaFlashCardAdapter(getContext());
//        listView.setAdapter(adapter);

        listView.setAdapter(new ListasAdapter(getContext()));



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