package com.example.helpstudy.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.utils.MensagemBar;
import com.example.helpstudy.view.adapters.ListasAdapter;
import com.example.helpstudy.view.dialog.AddListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ListaFragment extends Fragment {

    private static View view;
    private static ListView listView;
    private Context context;
    static ListasAdapter adapter;

    public ListaFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        FloatingActionButton btCriar = view.findViewById(R.id.btn_criar_listas);
        listView = view.findViewById(R.id.listview_listas);

        adapter = new ListasAdapter(getContext());

        listView.setAdapter(adapter);

        context = getActivity();

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
                new ControllerTarefas(getContext());
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

    public static void updateView(){
        listView.invalidateViews();
    }

    public View findLayoutView(){
        return view;
    }
}