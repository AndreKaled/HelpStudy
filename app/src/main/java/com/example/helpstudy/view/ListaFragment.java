package com.example.helpstudy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private View view;
    private ListView listViewListas, listView;

    private List<Listas> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        Button btCriar = view.findViewById(R.id.btn_criar_listas);
        listView = view.findViewById(R.id.listview_listas);

        list = ControllerListas.getInstancia().buscarTodos();


        ArrayAdapter<Listas> adapter = new ArrayAdapter<>(

                getContext(), android.R.layout.simple_list_item_1, list
        );


        listView.setAdapter(new ListasAdapter(getContext()));
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirModal();

            }
        });
        return view;
    }

    private void abrirModal() {

        AddListFragment dialog = new AddListFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "oi2");
    }
}