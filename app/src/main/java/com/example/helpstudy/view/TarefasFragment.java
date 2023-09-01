package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.helpstudy.R;

public class TarefasFragment extends Fragment {
//    fazer

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        Button btnCriar = view.findViewById(R.id.btn_criar_tarefas);
        ListView listView = view.findViewById(R.id.listview_tarefas);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirModal();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return view;
    }

    private void abrirModal() {

        AddTarefaFragment dialog = new AddTarefaFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi2");
    }
}