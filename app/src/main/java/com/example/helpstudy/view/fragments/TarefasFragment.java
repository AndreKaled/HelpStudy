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

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.view.adapters.TarefaAdapter;
import com.example.helpstudy.view.dialog.AddTarefaFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TarefasFragment extends Fragment {
//    fazer
    private static ListView listView;

    private static TarefaAdapter adapter;
    FloatingActionButton fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        FloatingActionButton btnCriar = view.findViewById(R.id.btn_criar_tarefas);
        listView = view.findViewById(R.id.listview_tarefas);

        adapter = new TarefaAdapter(getContext());

        listView.setAdapter(adapter);

        fb = view.findViewById(R.id.voltarBt);
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abrirModal();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new ListaFragment());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Bundle bundle = new Bundle();
                Tarefa tarefa = new ControllerTarefas(getContext()).buscarPorPosicao(i);
//                bundle.putString("id", tarefa.getNome());
                LeituraTarefa fragmento = new LeituraTarefa(tarefa);
//                fragmento.setArguments(bundle);
                replaceFragment(fragmento);
            }
        });

        return view;
    }

    private void abrirModal() {

        AddTarefaFragment dialog = new AddTarefaFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi2");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public static void updateViews(){
        listView.invalidateViews();
    }
}