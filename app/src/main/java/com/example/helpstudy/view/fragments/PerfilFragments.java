package com.example.helpstudy.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Usuario;
import com.example.helpstudy.utils.Notificacao;
import com.example.helpstudy.utils.Preferencias;
import com.example.helpstudy.view.activitys.TelaLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilFragments extends Fragment {

    ImageView btBackup, btSair;

    Preferencias pref;
    Intent intent;

    Boolean apertado;
    EditText editTextNome, editTextEmail, editTextSenha;

    TextView textNome,textEmail, textSenha, emailPerfil, nomePerfil, senhaPerfil;

    FloatingActionButton btEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_conquistas, container, false);

        apertado = false;

        //instanciacoes dos textos e botoes
        btBackup = rootView.findViewById(R.id.buttonTesteBackup);
        btSair = rootView.findViewById(R.id.sairBt);
        btEditar = rootView.findViewById(R.id.btEditar);
        pref = new Preferencias(getContext());
        textNome = rootView.findViewById(R.id.editNome);
        editTextNome = rootView.findViewById(R.id.editTextNome);
        textEmail = rootView.findViewById(R.id.editEmail);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        textSenha  = rootView.findViewById(R.id.editSenha);
        editTextSenha = rootView.findViewById(R.id.editTextSenha);
        nomePerfil = rootView.findViewById(R.id.nomePerfil);
        emailPerfil = rootView.findViewById(R.id.emailPerfil);
        senhaPerfil = rootView.findViewById(R.id.senhaPerfil);
        btBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new DataSource(getContext()).fazerBackup();
                    new Notificacao(getContext()).notificar("Backup", "Seu backup foi realizado com sucesso!", 1);
                }catch (Exception e){
                    new Notificacao(getContext()).notificar("Backup", "Seu backup deu pau! se fodeu", 1);
                }
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Preferencias(getContext()).limparPreferencias();
                intent = new Intent();
                intent.setClass(getActivity(), TelaLogin.class);
                getActivity().startActivity(intent);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //quando termina a edição
                if(apertado){

                    textNome.setVisibility(View.VISIBLE);
                    textEmail.setVisibility(View.VISIBLE);
                    textSenha.setVisibility(View.VISIBLE);
                    editTextEmail.setVisibility(View.INVISIBLE);
                    editTextNome.setVisibility(View.INVISIBLE);
                    editTextSenha.setVisibility((View.INVISIBLE));
                    btEditar.setImageResource(R.drawable.baseline_edit_24);
                    apertado = false;

                }else{


                    //quando habilita a edição

                    textNome.setVisibility(View.INVISIBLE);
                    textEmail.setVisibility(View.INVISIBLE);
                    textSenha.setVisibility(View.INVISIBLE);
                    editTextEmail.setVisibility(View.VISIBLE);
                    editTextNome.setVisibility(View.VISIBLE);
                    editTextSenha.setVisibility((View.VISIBLE));
                    btEditar.setImageResource(R.drawable.baseline_edit_off_24);
                    apertado = true;


                }
            }
        });
        ControllerUsuario ctrlUsuario = ControllerUsuario.getInstancia(getContext());
        Usuario user = ctrlUsuario.buscarPorId(pref.getIdUsuario());

        textNome.setText(user.getNome());
        textEmail.setText(user.getEmail());
        textSenha.setText(user.getSenha());
        editTextEmail.setText(user.getNome());
        editTextEmail.setText(user.getEmail());
        editTextSenha.setText(user.getSenha());
        // Inflate the layout for this fragment
        return rootView;
    }


}