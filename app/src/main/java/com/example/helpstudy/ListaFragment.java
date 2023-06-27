package com.example.helpstudy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListaFragment extends Fragment {

    private View view;
    private ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();

    public ListaFragment() {
        // Required empty public constructor
    }

    public static ListaFragment newInstance(String param1, String param2) {
        ListaFragment fragment = new ListaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        TextView viewNome, viewEmail, viewDataNasc, viewSenha;

        viewNome = view.findViewById(R.id.nome_usuario);
        viewEmail = view.findViewById(R.id.email_usuario);
        viewDataNasc = view.findViewById(R.id.dataNasc_usuario);
        viewSenha = view.findViewById(R.id.senha_usuario);
        Button btCadastrar = view.findViewById(R.id.bt_cadastrar_usuario);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario();
                user.setNome(viewNome.getText().toString());
                user.setDataNasc(viewDataNasc.getText().toString());
                user.setEmail(viewEmail.getText().toString());
                controllerUsuario.cadastrar(user);
                System.out.println(user +"cadastrado! :)");

            }
        });

        return view;
    }
}