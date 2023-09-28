package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.utils.ROOT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.checkerframework.checker.units.qual.A;

public class TarefasFragment extends Fragment {
//    fazer
    static ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        FloatingActionButton btnCriar = view.findViewById(R.id.btn_criar_tarefas);
        listView = view.findViewById(R.id.listview_tarefas);

        TarefaAdapter adapter = new TarefaAdapter(getContext());

        listView.setAdapter(adapter);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirModal();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                Tarefa tarefa = ControllerTarefas.getInstancia().buscarPorPosicao(i);
                bundle.putString("tituloTarefa", tarefa.getNome());
                bundle.putString("descricaoTarefa", tarefa.getDescricao());
                bundle.putString("dataTarefa", tarefa.getDataEntrega());
                LeituraTarefa fragmento = new LeituraTarefa();
                fragmento.setArguments(bundle);
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

}