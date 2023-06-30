package com.example.helpstudy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.R;
import com.example.helpstudy.model.Usuario;

public class TelaCadastro extends AppCompatActivity {



    private ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        EditText textNome, textEmail, textDataNasc, textSenha;

        TextView text;

        text = findViewById(R.id.textoLogin);

        textNome = findViewById(R.id.cadastro_nome);
        textEmail = findViewById(R.id.cadastro_email);
        textDataNasc = findViewById(R.id.cadastro_dataNasc);
        textSenha = findViewById(R.id.cadastro_senha);
        Button btCadastrar = findViewById(R.id.btn_cadastro);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(intent);

            }
        });


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario();
                user.setNome(textNome.getText().toString());
                user.setDataNasc(textDataNasc.getText().toString());
                user.setEmail(textEmail.getText().toString());
                user.setSenha(textSenha.getText().toString());
                controllerUsuario.cadastrar(user);
                System.out.println(user + "cadastrado! :)");
                Toast.makeText(TelaCadastro.this, user +" cadastrado! :)",Toast.LENGTH_LONG).show();

            }
        });

    }
}